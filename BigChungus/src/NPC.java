import java.awt.*;
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
        }else if(sID==ID.Alien1){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.aliens[0];
            this.Face=tex.alienFaces[1];

        }else if(sID==ID.Alien2){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.aliens[1];
            this.Face=tex.alienFaces[0];

        }else if(sID==ID.Alien3){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.aliens[2];
            this.Face=tex.alienFaces[2];

        }else if(sID==ID.Alien4){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.aliens[3];
            this.Face=tex.alienFaces[3];

        }else if(sID==ID.Guard1){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.guards[1];
            this.Face=tex.guardFaces[1];

        }else if(sID==ID.Guard2){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.guards[2];
            this.Face=tex.guardFaces[2];


        }else if(sID==ID.Guard3){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.guards[3];
            this.Face=tex.guardFaces[3];

        }else if(sID==ID.Guard4){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.guards[3];
            this.Face=tex.guardFaces[3];

        }else if(sID==ID.redSwitch1 || sID==ID.redSwitch2 || sID==ID.redSwitch3 || sID==ID.redSwitch4||sID==ID.redSwitch5 || sID==ID.redSwitch6 || sID==ID.redSwitch7 || sID==ID.redSwitch8){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.redSwitch[0];
            this.Face=tex.redSwitch[0];
        }else if(sID==ID.greenSwitch){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.greenSwitch[0];
            this.Face=tex.greenSwitch[0];
        }else if(sID==ID.WHO1 || sID==ID.WHO2){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.WHO;
            this.Face=tex.WHO;
        }else if(sID==ID.Computer){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.computer;
            this.Face=tex.computer;
        }else if(sID==ID.grave){
            this.currentImages=new BufferedImage[1];
            this.currentImages[0]=tex.grave;
            this.Face=tex.grave;
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
        if(!(sID==ID.Alien1 || sID==ID.Alien2 || sID==ID.Alien3 || sID==ID.Alien4 )){
            if(!text.equals("")){
                TextBox tb = new TextBox(this, text,0,0,0,0,ID.TextBox,tbHandler);
                tbHandler.addObject(tb);
            }
        }else if(game.getCurrentRoom().equals("Room3_1")){
            if(xBounds().intersects(player.getBounds())){
                if(!text.equals("")){
                    TextBox tb = new TextBox(this, text,0,0,0,0,ID.TextBox,tbHandler);
                    tbHandler.addObject(tb);
                }
            }
        }else if(!game.getCurrentRoom().equals("Room3_1") && sID==ID.Alien2){
            if(!text.equals("")){
                TextBox tb = new TextBox(this, text,0,0,0,0,ID.TextBox,tbHandler);
                tbHandler.addObject(tb);
            }
        }else if(sID==ID.WHO1){
            if(handler.object.contains(game.getWhoT1())){
                handler.removeObject(game.getWhoT1());
            }
        }else if(sID==ID.WHO2){
            if(handler.object.contains(game.getWhoT2())){
                handler.removeObject(game.getWhoT2());
            }
        }

        if(sID==ID.redSwitch1 || sID==ID.redSwitch2 || sID==ID.redSwitch3 || sID==ID.redSwitch4||sID==ID.redSwitch5 || sID==ID.redSwitch6 || sID==ID.redSwitch7 || sID==ID.redSwitch8 || sID==ID.greenSwitch){
            if(sID==ID.redSwitch1){
                game.setA51_Switch1(!(game.isA51_Switch1()));
            }else if(sID==ID.redSwitch2) {
                game.setA51_Switch2(!(game.isA51_Switch2()));
            }else if(sID==ID.redSwitch3) {
                game.setA51_Switch3(!(game.isA51_Switch3()));
            }else if(sID==ID.redSwitch4) {
                game.setA51_Switch4(!(game.isA51_Switch4()));
            }else if(sID==ID.redSwitch5) {
                game.setA51_Switch5(!(game.isA51_Switch5()));
                if(game.isA51_Switch6() && game.isA51_Switch7()&& game.isA51_Switch8()&& game.isA51_Switch5()){
                    game.getHandler().removeObject(game.getBarrier());
                }
            }else if(sID==ID.redSwitch6) {
                game.setA51_Switch6(!(game.isA51_Switch6()));
                if(game.isA51_Switch6() && game.isA51_Switch7()&& game.isA51_Switch8()&& game.isA51_Switch5()){
                    game.getHandler().removeObject(game.getBarrier());
                }
            }else if(sID==ID.redSwitch7) {
                game.setA51_Switch7(!(game.isA51_Switch7()));
                if(game.isA51_Switch6() && game.isA51_Switch7()&& game.isA51_Switch8()&& game.isA51_Switch5()){
                    game.getHandler().removeObject(game.getBarrier());
                }
            }else if(sID==ID.redSwitch8) {
                game.setA51_Switch8(!(game.isA51_Switch8()));
                if(game.isA51_Switch6() && game.isA51_Switch7()&& game.isA51_Switch8()&& game.isA51_Switch5()){
                    game.getHandler().removeObject(game.getBarrier());
                }
            }else if(sID==ID.greenSwitch)
                game.setAliensFreed(true);

            if(!(sID==ID.greenSwitch)){
                if(currentImages[0]==tex.redSwitch[0])
                    currentImages[0]=tex.redSwitch[1];
                else
                    currentImages[0]=tex.redSwitch[0];
            }else{
                System.out.println("test");
                if(currentImages[0]==tex.greenSwitch[0])
                    currentImages[0]=tex.greenSwitch[1];
                else
                    currentImages[0]=tex.greenSwitch[0];
            }






        }else if(sID==ID.Computer){
            if(!game.isSpecialGameWin()){
                game.setCurrentState(Game.STATE.SpecialGame);
                game.getHandler().clear();
                game.setBackground(Color.black);
                game.getHandler().addObject(new GamePlayer(Game.WIDTH/2,Game.HEIGHT-100,32,32,ID.GamePlayer,game.getHandler(),game));
                game.setLevel(1);
                game.setScore(0);
                game.setGameHealth(10);
                game.setSpawn(new Spawn(game.getHandler(), game));
                AudioPlayer.getMusic("GAME").loop();
            }

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

    // extends x interaction bounds
    public Rectangle xBounds(){
        return new Rectangle((int)x-300,(int)y, (int)width+1200, (int)height+200);
    }
}
