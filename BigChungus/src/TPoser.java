

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class TPoser extends Enemy implements Serializable {
    private int lastKeyReleased;
    Handler handler;
    private boolean battleReady;
    TBHandler tbHandler;
    String text;
    private Player player;
    private transient BufferedImage[] currentImages;


    public TPoser(float x, float y, float height, float width, Handler handler, Game game, ID id, int speed, TBHandler tbHandler, String text, Player player, boolean battleReady){
        super(x, y, height, width,handler,game, id, speed, tbHandler, "DOY DOY DOY", player,ID.TPoser,5,5);
        this.handler = handler;
        this.game = game;
        this.tbHandler = tbHandler;
        this.text = text;
        this.player = player;
        this.battleReady=battleReady;
        this.health = 10;
        setName("TPoser");
        this.nameColor = Color.blue.brighter();
        this.maxhealth=this.health;
        this.moves = 2;
        this.runnable=false;

// to be replaced
       // this.walkRight = new Animation(speed, tex.BigChungus_WalkRight[1], tex.BigChungus_WalkRight[2]);


    //    this.currentImages= tex.BigChungus_WalkRight;
        this.isVisible = true;
       // this.Face = tex.BigChungusFace[0];
        this.BattleForm = tex.TposerBF;
    }

    public void Turn(){

    }




    public void tick(){
        x+=velX;
        y+=velY;



        //walkRight.runAnimation();
/*
        if(velX > 0 && velY==0){
            walkRight.runAnimation();
        }else if(velX < 0 && velY==0){
            walkLeft.runAnimation();
        }else if(velY > 0){
            walkDown.runAnimation();
        }else if(velY < 0){
            walkUp.runAnimation();
        }*/



    }
    public void render(Graphics g){

        //walkRight.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
        //Rectangle temp = this.getBounds();
        try{
            g.drawImage(currentImages[0], (int) this.x, (int)this.y, (int)width, (int)height, null);
        }catch(Exception e){
            tex=Game.getInstance();

           // this.walkRight = new Animation(speed, tex.BigChungus_WalkRight[1], tex.BigChungus_WalkRight[2]);


            this.currentImages= tex.BigChungus_WalkRight;
            this.isVisible = true;
           // this.Face = tex.BigChungusFace[0];
            this.BattleForm = tex.TposerBF;
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
            currentImages=tex.BigChungus_WalkRight;

        }else if(player.getCurrentImages()==tex.Player_WalkRight){
            currentImages=tex.BigChungus_WalkLeft;
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
