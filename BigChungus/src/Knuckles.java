import org.newdawn.slick.Music;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Knuckles extends Enemy implements Serializable {
    private int lastKeyReleased;
    Handler handler;
    private boolean battleReady;
    TBHandler tbHandler;
    String text;
    private Player player;
    private transient BufferedImage[] currentImages;

    public Knuckles(float x, float y, float height, float width, Handler handler, Game game, ID id, int speed, TBHandler tbHandler, String text, Player player, boolean battleReady){
        super(x, y, height, width,handler,game, id, speed, tbHandler, "CLICK CLICK", player, ID.Knuckles);
        this.handler = handler;
        this.game = game;
        this.tbHandler = tbHandler;
        this.text = text;
        this.player = player;
        this.battleReady=battleReady;
        this.health = 100;
        setName("Knuckles");
        this.nameColor = Color.red;
        this.maxhealth=this.health;
        this.moves = 4;
        this.runnable=false;

// to be replaced
        this.walkLeft = new Animation(speed, tex.Knuckles_WalkLeft[0], tex.Knuckles_WalkLeft[1], tex.Knuckles_WalkLeft[2], tex.Knuckles_WalkLeft[3], tex.Knuckles_WalkLeft[4], tex.Knuckles_WalkLeft[5], tex.Knuckles_WalkLeft[6], tex.Knuckles_WalkLeft[7]);
        this.walkRight = new Animation(speed, tex.Knuckles_WalkRight[0], tex.Knuckles_WalkRight[1], tex.Knuckles_WalkRight[2], tex.Knuckles_WalkRight[3], tex.Knuckles_WalkRight[4], tex.Knuckles_WalkRight[5], tex.Knuckles_WalkRight[6], tex.Knuckles_WalkRight[7]);

        this.currentImages= tex.Knuckles_WalkLeft;

        this.Face = tex.Knuckles_Face;
        this.BattleForm = tex.Knuckles_BattleForm;
    }

    public void Turn(){

    }




    public void tick(){
        x+=velX;
        y+=velY;



        if(velX > 0 && velY==0){
            walkRight.runAnimation();
        }else if(velX < 0 && velY==0){
            walkLeft.runAnimation();
        }else if(velY > 0){
            walkDown.runAnimation();
        }else if(velY < 0){
            walkUp.runAnimation();
        }



    }
    public void render(Graphics g){
        try{
            g.drawImage(currentImages[0], (int) this.x, (int)this.y, 48, 96, null);
        }catch(Exception e){
            tex=Game.getInstance();

            walkLeft = new Animation(speed, tex.Knuckles_WalkLeft[0], tex.Knuckles_WalkLeft[1], tex.Knuckles_WalkLeft[2], tex.Knuckles_WalkLeft[3], tex.Knuckles_WalkLeft[4], tex.Knuckles_WalkLeft[5], tex.Knuckles_WalkLeft[6], tex.Knuckles_WalkLeft[7]);
            walkRight = new Animation(speed, tex.Knuckles_WalkRight[0], tex.Knuckles_WalkRight[1], tex.Knuckles_WalkRight[2], tex.Knuckles_WalkRight[3], tex.Knuckles_WalkRight[4], tex.Knuckles_WalkRight[5], tex.Knuckles_WalkRight[6], tex.Knuckles_WalkRight[7]);

            currentImages= tex.Knuckles_WalkLeft;

            Face = tex.Knuckles_Face;
            BattleForm = tex.Knuckles_BattleForm;
        }






    }

    public void setLastKeyReleased(int temp){
        this.lastKeyReleased = temp;
    }
    public int getLastKeyReleased(){
        return this.lastKeyReleased;
    }


    public void collision(){
        for(int i = 0; i < this.handler.object.size(); i++){
            GameObject tempObject = this.handler.object.get(i);

            if(tempObject.getId() == ID.BlackGround){
                Rectangle tempRect = getBounds();
                tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                if(tempRect.getBounds().intersects(tempObject.getBounds())){
                    x+= velX * -1;
                    y+= velY*-1;
                }
            }
        }
    }

    public void interaction(){
        if(player.getCurrentImages()==tex.Player_WalkLeft){
            currentImages=tex.Knuckles_WalkRight;

        }else if(player.getCurrentImages()==tex.Player_WalkRight){
            currentImages=tex.Knuckles_WalkLeft;
        }else if(player.getCurrentImages()==tex.Player_WalkUp){
        }else if(player.getCurrentImages()==tex.Player_WalkDown){
        }
        player.setLimited(true);
        player.setVelX(0);
        player.setVelY(0);
        for(int i= 0; i<game.getKeyInput().getKeyDown().length; i++){
            game.getKeyInput().getKeyDown()[i] = false;
        }
        if(!text.equals("")){
            TextBox tb = new TextBox(this, text);
            tbHandler.addObject(tb);
        }

        if(battleReady==true){
            //battle function
        }


    }


    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 38, 148);
    }

    public void setCurrentImages(BufferedImage[] currentImages){
        this.currentImages = currentImages;
    }
    public BufferedImage[] getCurrentImages(){
        return currentImages;
    }
    public boolean getBattleReady(){
        return battleReady;
    }
    public void setBattleReady(boolean battleReady){
        this.battleReady = battleReady;
    }

}