import org.newdawn.slick.Music;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class DatBoi extends Enemy implements Serializable {
    private int lastKeyReleased;
    Handler handler;
    private boolean battleReady;
    TBHandler tbHandler;
    String text;
    private Player player;
    private transient BufferedImage[] currentImages;

    public DatBoi(float x, float y, float height, float width, Handler handler, Game game, ID id, int speed, TBHandler tbHandler, String text, Player player, boolean battleReady){
        super(x, y, height, width,handler,game, id, speed, tbHandler, "Here I come!", player,ID.DatBoi,20,20);
        this.handler = handler;
        this.game = game;
        this.tbHandler = tbHandler;
        this.text = text;
        this.player = player;
        this.battleReady=battleReady;
        this.health = 20;
        setName("Dat Boi");
        this.nameColor = Color.green;
        this.maxhealth=this.health;
        this.moves = 2;

// to be replaced
       /* this.walkLeft = new Animation(speed, tex.Pikachu_WalkLeft[0], tex.Pikachu_WalkLeft[1], tex.Pikachu_WalkLeft[2]);
        this.walkRight = new Animation(speed, tex.Pikachu_WalkRight[0], tex.Pikachu_WalkRight[1], tex.Pikachu_WalkRight[2]);
        this.walkUp = new Animation(speed, tex.Pikachu_WalkUp[0], tex.Pikachu_WalkUp[1], tex.Pikachu_WalkUp[2]);
        this.walkDown = new Animation(speed, tex.Pikachu_WalkDown[0], tex.Pikachu_WalkDown[1], tex.Pikachu_WalkDown[2]); */

        //this.currentImages= tex.FatYoshiBF;
        this.isVisible = true;
        //this.Face = tex.PikachuFace[0];
        this.BattleForm = tex.DatBoiBF;
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

            this.walkLeft = new Animation(speed, tex.Pikachu_WalkLeft[0], tex.Pikachu_WalkLeft[1], tex.Pikachu_WalkLeft[2]);
            this.walkRight = new Animation(speed, tex.Pikachu_WalkRight[0], tex.Pikachu_WalkRight[1], tex.Pikachu_WalkRight[2]);
            this.walkUp = new Animation(speed, tex.Pikachu_WalkUp[0], tex.Pikachu_WalkUp[1], tex.Pikachu_WalkUp[2]);
            this.walkDown = new Animation(speed, tex.Pikachu_WalkDown[0], tex.Pikachu_WalkDown[1], tex.Pikachu_WalkDown[2]);

            this.currentImages= tex.Pikachu_WalkLeft;
            this.Face = tex.PikachuFace[0];
            this.BattleForm = tex.DatBoiBF;
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
        /*
        if(player.getCurrentImages()==tex.Player_WalkLeft){
            currentImages=tex.Pikachu_WalkRight;

        }else if(player.getCurrentImages()==tex.Player_WalkRight){
            currentImages=tex.Pikachu_WalkLeft;
        }else if(player.getCurrentImages()==tex.Player_WalkUp){
            currentImages=tex.Pikachu_WalkDown;
        }else if(player.getCurrentImages()==tex.Player_WalkDown){
            currentImages=tex.Pikachu_WalkUp;
        }
        player.setLimited(true);
        player.setVelX(0);
        player.setVelY(0);
        for(int i= 0; i<game.getKeyInput().getKeyDown().length; i++){
            game.getKeyInput().getKeyDown()[i] = false;
        }
        if(!text.equals("")){
            TextBox tb = new TextBox(this, text,0,0,0,0,ID.TextBox,tbHandler);
            tbHandler.addObject(tb);
        }

        if(battleReady==true){
            //battle function
        }
        */


    }


    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
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
