import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;


public class NPC extends Character implements Serializable {
    private int lastKeyReleased;
    Handler handler;
    private boolean battleReady;
    TBHandler tbHandler;
    String text;
    private Player player;
    private transient BufferedImage[] currentImages;
    private String spec;
    private ID sID;


    public NPC(float x, float y, float height, float width, Handler handler, Game game, ID id, int speed, TBHandler tbHandler, String text, Player player, ID sID){
        super(x, y, height, width,handler,game, id, speed);
        this.handler = handler;
        this.game = game;
        this.tbHandler = tbHandler;
        this.text = text;
        this.player = player;
        this.spec=spec;
        this.sID = sID;


        if(sID==ID.TheLastEntity){
            this.walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
            this.walkRight = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
            this.walkUp = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
            this.walkDown = new Animation(speed, tex.Player_WalkDown[0], tex.Player_WalkDown[1]);
            System.out.println(tex.TheLastEntity);
            System.out.println(this.currentImages);
            this.currentImages = new BufferedImage[1];
            this.currentImages[0] = tex.TheLastEntity;
            this.Face = tex.TheLastEntityFace;

        }else if(sID==ID.Book){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.Book;
            this.Face=tex.Player_Face[0];
        }
        else{
            this.walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
            this.walkRight = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
            this.walkUp = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
            this.walkDown = new Animation(speed, tex.Player_WalkDown[0], tex.Player_WalkDown[1]);
            this.currentImages= tex.Player_WalkDown;
            this.Face = tex.Player_Face[0];
        }


        this.isVisible = true;
        this.battleReady=false;
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
            g.drawImage(currentImages[0], (int) this.x, (int)this.y, (int)this.width, (int)this.height, null);
        }catch(Exception e){
            tex=Game.getInstance();

            this.walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
            this.walkRight = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
            this.walkUp = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
            this.walkDown = new Animation(speed, tex.Player_WalkDown[0], tex.Player_WalkDown[1]);
            this.currentImages= tex.Player_WalkDown;
            this.isVisible = true;
            if(this.spec.equals("The Last Entity")){
                this.Face = tex.TheLastEntityFace;

            }
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
            //currentImages=tex.Player_WalkRight;

        }else if(player.getCurrentImages()==tex.Player_WalkRight){
            //currentImages=tex.Player_WalkLeft;
        }else if(player.getCurrentImages()==tex.Player_WalkUp){
            //currentImages=tex.Player_WalkDown;
        }else if(player.getCurrentImages()==tex.Player_WalkDown){
            //currentImages=tex.Player_WalkUp;
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

    public ID getsID() {
        return sID;
    }

    public void setsID(ID sID) {
        this.sID = sID;
    }
}
