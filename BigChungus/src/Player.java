

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

public class Player extends Character implements Serializable {
    private Random rand = new Random();


    private  int lastKeyReleased;
    private  int lastKeyHit;
    private Handler handler;
    private  boolean downStop;
    private  Rectangle range;
    private  boolean limited;
    private transient BufferedImage[] currentImages;
    private  String name;
    private  Inventory inv;
    private  Magic magic;
    private Items armor=null;
    private Items weapon=null;
    private int xp=0;
    private int level=1;
    public boolean allowed=true;
    private boolean backwards=false;
    private int kills;




    public Player(float x, float y, float height, float width, Handler handler, Game game, ID id, int speed, Inventory inv, Magic magic){
        super(x, y, height, width,handler,game, id, speed);
        this.handler = handler;
        this.game = game;
        this.inv = inv;
        this.magic = magic;
        this.mana=100;
        this.maxMana=100;


        this.walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
        this.walkRight = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
        this.walkUp = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
        this.walkDown = new Animation(speed, tex.Player_WalkDown[1], tex.Player_WalkDown[2]);
        this.isVisible = true;
        this.Face = tex.Player_Face[0];
        this.health = 100;
        this.maxhealth=this.health;
        this.name="Filler";
        this.attack=10000;
        this.defense=5;
        this.kills=0;
    }




    public void tick(){

        health=(int)(Game.clamp(health,0,maxhealth));
        mana=(int)(Game.clamp(mana,0,maxMana));

        x+=velX;
        y+=velY;
        collision();

        if(velX > 0 && velY==0){
            walkRight.runAnimation();
        }else if(velX < 0 && velY==0){
            walkLeft.runAnimation();
        }else if(velY > 0){
            walkDown.runAnimation();
        }else if(velY < 0){
            walkUp.runAnimation();
        }

        range = new Rectangle((int)x/2, (int)y/2, 2*(int)width, 2*(int)height);

        if((velX>0 || velY>0) && game.getEndangered()){
            int value = rand.nextInt(500);
            if(value==1){
                int value2 = rand.nextInt(4);

                if(game.getCurrentArea().equals("wae")){
                    if(value2==0){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Malario,0,game.getTbHandler(),"Malario",game.getPlayer(),ID.Malario,10,10,2,tex.MalarioBF,Color.red,20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==1){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.TPoser,0,game.getTbHandler(),"TPoser",game.getPlayer(),ID.TPoser,10,10,2,tex.TposerBF,Color.blue.brighter(),20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==2){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.FatYoshi,0,game.getTbHandler(),"Fat Yoshi",game.getPlayer(),ID.FatYoshi,10,10,2,tex.FatYoshiBF,Color.green,20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==3){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.JoshuaGiraffe,0,game.getTbHandler(),"Joshua Giraffe",game.getPlayer(),ID.JoshuaGiraffe,10,10,2,tex.JoshuaGiraffeBF,Color.yellow,20), game.getHandler(), game,  game.getAp()));
                    }
                }else if(game.getCurrentArea().equals("51")){
                    if(value2==0){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.BongoCat,0,game.getTbHandler(),"Bongo Cat",game.getPlayer(),ID.BongoCat,10,10,2,tex.BongoCatBF,Color.white,20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==1){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Zuck,0,game.getTbHandler(),"Zuckerberg",game.getPlayer(),ID.Zuck,10,10,2,tex.ZuckBF,Color.blue.brighter(),20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==2){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.DatBoi,0,game.getTbHandler(),"Dat Boi",game.getPlayer(),ID.DatBoi,10,10,2,tex.DatBoiBF,Color.green,20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==3){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Spongebob,0,game.getTbHandler(),"SpOnGeBoB",game.getPlayer(),ID.Spongebob,10,10,2,tex.SpongebobBF,Color.yellow,20), game.getHandler(), game,  game.getAp()));

                    }
                }else if(game.getCurrentArea().equals("back")){
                    if(value2==0){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Crab,0,game.getTbHandler(),"Crab Rave",game.getPlayer(),ID.Crab,10,10,2,tex.CrabBF,Color.red.brighter(),20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==1){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Kermit,0,game.getTbHandler(),"Kermit",game.getPlayer(),ID.Kermit,10,10,2,tex.KermitBF,Color.green.brighter(),20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==2){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.KazooKid,0,game.getTbHandler(),"The Kazoo Kid",game.getPlayer(),ID.KazooKid,10,10,2,tex.KazooKidBF,Color.red,20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==3){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.YodelBoy,0,game.getTbHandler(),"Walmart Yodel Boy",game.getPlayer(),ID.YodelBoy,10,10,2,tex.YodelBoyBF,Color.blue.brighter(),20), game.getHandler(), game,  game.getAp()));
                    }
                }else if(game.getCurrentArea().equals("dead")){
                    if(value2==0){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Harambe,0,game.getTbHandler(),"Harambe",game.getPlayer(),ID.Harambe,10,10,2,tex.HarambeBF,Color.white,20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==1){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Waluigi,0,game.getTbHandler(),"Waluigi",game.getPlayer(),ID.Waluigi,10,10,2,tex.WaluigiBF,new Color(204, 0, 164),20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==2){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Arthur,0,game.getTbHandler(),"Arthur",game.getPlayer(),ID.Arthur,10,10,2,tex.ArthurBF,new Color(255,219,172),20), game.getHandler(), game,  game.getAp()));
                    }else if(value2==3){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, new Enemy(0,0,0,0,game.getHandler(),game,ID.Spaget,0,game.getTbHandler(),"Spaget",game.getPlayer(),ID.Spaget,10,10,2,tex.SpagetBF,Color.green.brighter().brighter(),20), game.getHandler(), game,  game.getAp()));
                    }
                }






            }
        }
    }
    public void render(Graphics g){





        //g.drawImage(tex.Player_WalkLeft[0], (int) this.x, (int)this.y, 38, 148, null);
        try{
            if(velX != 0 && velY==0){
                if(velX < 0){
                    width=22;
                    height=92;
                    walkLeft.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
                    currentImages = tex.Player_WalkLeft;

                }else if(velX > 0){
                    width=22;
                    height=92;
                    walkRight.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
                    currentImages = tex.Player_WalkRight;
                }


                //deals with sprites when player stops moving
            }else if(velX==0 && velY==0){
                if(this.currentImages == tex.Player_WalkLeft)
                    g.drawImage(tex.Player_WalkLeft[0], (int)x, (int)y, (int)width, (int)height, null);
                else if(this.currentImages==tex.Player_WalkRight)
                    g.drawImage(tex.Player_WalkRight[0], (int)x, (int)y, (int)width, (int)height, null);
                else if(this.currentImages==tex.Player_WalkUp){
                    g.drawImage(tex.Player_WalkUp[0], (int)x, (int)y, (int)width, (int)height, null);
                    width=24;
                    height=92;
                }
                else if(this.currentImages==tex.Player_WalkDown){
                    g.drawImage(tex.Player_WalkDown[0], (int)x, (int)y, (int)width, (int)height, null);
                    width=23;
                    height=92;
                }
                else{
                    g.drawImage(tex.Player_WalkDown[0], (int)x, (int)y, (int)width, (int)height, null);
                }
                // deals with pure y moving animation
            }else if(velX==0 && velY!=0){
                if(velY < 0){
                    width=24;
                    height=92;
                    walkUp.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
                    currentImages = tex.Player_WalkUp;

                }else if(velY > 0){
                    width=22;
                    height=92;
                    walkDown.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
                    currentImages = tex.Player_WalkDown;
                }

                //deals with diagonal movement
            }else if(velX!=0 && velY!=0){

                if((velY < 0 && velX < 0) || (velY<0 && velX>0)){
                    width=24;
                    height=92;
                    walkUp.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
                    currentImages = tex.Player_WalkUp;

                }else if((velX > 0 && velY > 0) || (velY>0 && velX<0)){
                    width=22;
                    height=92;
                    walkDown.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
                    currentImages = tex.Player_WalkDown;
                }
            }
        }catch(Exception e){
            tex=Game.getInstance();
            this.handler=game.getHandler();
            walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
            walkRight = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
            walkUp = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
            walkDown = new Animation(speed, tex.Player_WalkDown[1], tex.Player_WalkDown[2]);
            Face = tex.Player_Face[0];
        }

        // deals with pure x moving animation



    }

    public void setLastKeyReleased(int temp){
        this.lastKeyReleased = temp;
    }
    public int getLastKeyReleased(){
        return this.lastKeyReleased;
    }

    public void setLastKeyHit(int temp){
        this.lastKeyHit = temp;
    }
    public int getLastKeyHit(){
        return this.lastKeyHit;
    }


    public void collision(){
        if(allowed){
            // IDEA FOR RENDERING ONLY NEEDED THINGS
            // WHILE COLLISION CHEKCING, CHECK EVERYTHING AGAINST SPECIAL BOUNDS AND ASSIGN VISIBILITY

            for(int i = 0; i < this.game.getHandler().object.size(); i++){

                GameObject tempObject = this.game.getHandler().object.get(i);

               // System.out.println(tempObject);
               // System.out.println(tempObject.getId());

                if(tempObject.getId() == ID.BlackGround || tempObject.getId()==ID.Tree || tempObject.getId()==ID.Mushroom || tempObject.getId()==ID.bottomRail || tempObject.getId()==ID.sideRail || tempObject.getId()==ID.invisWall || tempObject.getId()==ID.Tree2 || tempObject.getId()==ID.blackKey || tempObject.getId()==ID.whiteKey || tempObject.getId()==ID.backPiano){
                    Rectangle tempRect = getBounds();
                    if(tempRect.getBounds().intersects(tempObject.getBounds())){
                        if(limited){
                            x+=velX*-1;
                            y+=velY*-1;
                            velX=0;
                            velY=0;

                            limited=false;
                        }

                        x+= velX * -1;
                        y+= velY*-1;


                    }
                }else if(tempObject.getId() == ID.NPC){


                    NPC tempNPC = (NPC) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)width,(int)height-5);
                    Rectangle tempRect2 = tempNPC.getBounds();
                    tempRect2.setSize((int)tempRect2.getWidth(), (int)(tempRect2.getHeight()/2));

                    if(!(game.getCurrentRoom().equals("Room3_1"))){
                        if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                            x+= velX * -1;
                            y+= velY*-1;
                            if(tempNPC.getBattleReady()){
                                game.setSwitch(false);
                                game.setCurrentState(Game.STATE.Battle);
                            }
                        }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                            tempNPC.interaction();
                            //x+= velX * -1;
                            // y+= velY*-1;
                            lastKeyHit=100;
                        }
                    }else{
                        if(tempRect2.intersects(getBounds())){
                            x+= velX * -1;
                            y+= velY*-1;
                        }
                        if(tempNPC.xBounds().intersects(getBounds()) && (lastKeyHit==4)){
                            tempNPC.interaction();
                            lastKeyHit=100;
                        }
                    }


                }else if(tempObject.getId() == ID.Knuckles || tempObject.getId()==ID.Spongebob || tempObject.getId()==ID.Waluigi || tempObject.getId()==ID.Spaget || tempObject.getId()==ID.Arthur){

                    Enemy tempKnuckles = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){

                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempKnuckles.getBattleReady()){

                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempKnuckles, game.getHandler(), game,  game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                        //tempKnuckles.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.Pikachu){
                    Enemy tempPikachu = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempPikachu.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempPikachu, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                        //tempPikachu.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.BigChungus){
                    Enemy tempBigChungus = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(tempBigChungus.getBounds())){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempBigChungus.getBattleReady() && !tempBigChungus.isDefeated()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempBigChungus, game.getHandler(), getGame(), getGame().getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                        //tempBigChungus.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId()==ID.SaveIcon){
                    SaveObject tempSaveIcon = (SaveObject) tempObject;
                    Rectangle tempRect = tempSaveIcon.getBounds();
                    Rectangle interactRect = new Rectangle((int)tempRect.getX(), (int)tempRect.getY(), (int)tempRect.getWidth(), (int)tempRect.getHeight()/2);
                    tempRect.setRect((int)tempRect.getX(), (int)tempRect.getY(), (int)tempRect.getWidth(), (int)tempRect.getHeight());

                    //tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()/2);
                    if(interactRect.intersects(getBounds())){
                        x+= velX*-1;
                        y+= velY*-1;

                    }

                    if(getBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){
                        tempSaveIcon.interaction(game);

                        lastKeyHit=100;
                    }

                }else if(tempObject.getId()==ID.Transition){
                    Transition tempTransition = (Transition) tempObject;
                    if(getBounds().intersects(tempTransition.getBounds())){
                        game.getHandler().clear();
                        game.getPlayer().setLimited(false);
                        game.getPlayer().setVelX(0);
                        game.getPlayer().setVelY(0);
                        System.out.println("here4");
                        System.out.println("trans: "+tempTransition.getDestination());

                        game.setCurrentRoom(tempTransition.getDestination());
                        game.setCurrentLevel(game.getRooms().get(tempTransition.getDestination()));
                        game.loadLevel(tempTransition.getDestination());
                        game.getHandler().addObject(game.getPlayer());
                        game.getPlayer().setX(tempTransition.getNewPlayerX());
                        game.getPlayer().setY(tempTransition.getNewPlayerY());
                        game.setEndangered(tempTransition.getDangerZone());
                        game.setCurrentArea(tempTransition.getArea());

                        System.out.println("after trans: "+game.getCurrentRoom());


                    }

                /*if(tempTransition.getDestination().equals("Room2_1") && getBounds().intersects(tempTransition.getBounds())){
                    handler.clear();
                    game.setCurrentRoom("Room2_1");
                    game.setCurrentLevel(tex.Room2_1);

                    game.loadLevel("Room2_1");


                    handler.addObject(game.getPlayer());
                    game.getPlayer().setX(tempTransition.getNewPlayerX());
                    game.getPlayer().setY(tempTransition.getNewPlayerY());
                    game.setEndangered(true);

                }else if(tempTransition.getDestination().equals("Room1_1") && getBounds().intersects(tempTransition.getBounds())){
                    handler.clear();
                    game.setCurrentRoom("Room1_1");
                    game.setCurrentLevel(tex.Room1_1);

                    game.loadLevel("Room1_1");


                    handler.addObject(game.getPlayer());
                    game.getPlayer().setX(tempTransition.getNewPlayerX());
                    game.getPlayer().setY(tempTransition.getNewPlayerY());
                    game.setEndangered(false);
                }*/



                }else if((tempObject.getId()==ID.Item || tempObject.getId()==ID.Spell) && getBounds().intersects(tempObject.getBounds())){
                    if(tempObject.getId()==ID.Item){
                        Items temp = (Items) (tempObject);
                        if(!temp.getObtained()){
                            temp.setObtained(true);
                            game.getPlayer().inv.addItem(temp);
                            game.getPlayer().setVelX(0);
                            game.getPlayer().setVelY(0);
                            game.getPlayer().setLimited(true);
                            if(temp.getName().charAt(0)=='e'||temp.getName().charAt(0)=='i'||temp.getName().charAt(0)=='o'||temp.getName().charAt(0)=='u'||temp.getName().charAt(0)=='a'||temp.getName().charAt(0)=='E'||temp.getName().charAt(0)=='I'||temp.getName().charAt(0)=='O'||temp.getName().charAt(0)=='U'||temp.getName().charAt(0)=='A'){
                                game.getTbHandler().addObject(new TextBox(game.getPlayer(),"You just got an "+temp.getName()+". Check your inventory with C to look at it!",0,0,0,0,ID.TextBox,game.getTbHandler()));

                            }else{
                                game.getTbHandler().addObject(new TextBox(game.getPlayer(),"You just got a "+temp.getName()+". Check your inventory with C to look at it!",0,0,0,0,ID.TextBox,game.getTbHandler()));

                            }
                        }
                    }else if(tempObject.getId()==ID.Spell){
                        Spells temp = (Spells) (tempObject);
                        if(!temp.getObtained()){
                            temp.setObtained(true);
                            game.getPlayer().magic.addItem(temp);
                            game.getPlayer().setVelX(0);
                            game.getPlayer().setVelY(0);
                            game.getPlayer().setLimited(true);
                            if(temp.getName().charAt(0)=='e'||temp.getName().charAt(0)=='i'||temp.getName().charAt(0)=='o'||temp.getName().charAt(0)=='u'||temp.getName().charAt(0)=='a'||temp.getName().charAt(0)=='E'||temp.getName().charAt(0)=='I'||temp.getName().charAt(0)=='O'||temp.getName().charAt(0)=='U'||temp.getName().charAt(0)=='A'){
                                game.getTbHandler().addObject(new TextBox(game.getPlayer(),"You just got an "+temp.getName()+" spell. Check your spell books with V to look at it!",0,0,0,0,ID.TextBox,game.getTbHandler()));

                            }else{
                                game.getTbHandler().addObject(new TextBox(game.getPlayer(),"You just got a "+temp.getName()+" spell. Check your spell books with V to look at it!",0,0,0,0,ID.TextBox,game.getTbHandler()));

                            }
                        }
                    }


                }else if(tempObject.getId() == ID.Malario){
                    Enemy tempMalario = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempMalario.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempMalario, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                       // tempMalario.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId()==ID.TPoser){
                    Enemy tempTposer = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempTposer.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempTposer, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                      //  tempTposer.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId()==ID.TextBox){
                    TextBox tempTB = (TextBox) tempObject;
                    if(getBounds().intersects(tempTB.getBounds())){
                        tempTB.setInteracted(true);
                        game.getHandler().removeObject(tempTB);
                        game.getTbHandler().addObject(tempTB);
                        game.getPlayer().setLimited(true);
                        game.getPlayer().setVelY(0);
                        game.getPlayer().setVelX(0);
                        this.velX=0;
                        this.velY=0;
                        this.limited=true;
                    }

                }else if(tempObject.getId() == ID.FatYoshi){
                    Enemy tempFatYoshi = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempFatYoshi.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempFatYoshi, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                       // tempFatYoshi.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.JoshuaGiraffe){
                    Enemy tempJoshuaGiraffe = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempJoshuaGiraffe.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempJoshuaGiraffe, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                       // tempJoshuaGiraffe.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.Kermit){
                    Enemy tempKermit = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempKermit.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempKermit, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                       // tempKermit.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.DatBoi){
                    Enemy tempDatBoi = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempDatBoi.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempDatBoi, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                      //  tempDatBoi.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.BongoCat){
                    Enemy tempBongoCat = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempBongoCat.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempBongoCat, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                      //  tempBongoCat.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.Crab){
                    Enemy tempCrab = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempCrab.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempCrab, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                      //  tempCrab.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.AntiHero){
                    Enemy tempAntiHero = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempAntiHero.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempAntiHero, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                        tempAntiHero.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.Zuck){
                    y+= velY*-1;

                    Enemy tempZuck = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempZuck.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempZuck, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                      //  tempZuck.interaction();
                        x+= velX * -1;                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.Harambe){
                    Enemy tempHarambe = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempHarambe.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempHarambe, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                      //  tempHarambe.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.KazooKid){
                    Enemy tempKazooKid = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempKazooKid.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempKazooKid, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                       // tempKazooKid.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId() == ID.YodelBoy){
                    Enemy tempYodelBoy = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                        x+= velX * -1;
                        y+= velY*-1;
                        if(tempYodelBoy.getBattleReady()){
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempYodelBoy, game.getHandler(), game, game.getAp()));
                        }
                    }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                       // tempYodelBoy.interaction();
                        x+= velX * -1;
                        y+= velY*-1;
                        lastKeyHit=100;
                    }
                }else if(tempObject.getId()==ID.Ice){
                    if(tempObject.getBounds().intersects(getBounds())){
                        if(velX!=0 || velY!=0)
                            limited=true;
                        else
                            limited=false;
                        /*
                        if(velX>0 && velY==0){
                            // right

                        }else if(velX<0 && velY==0){
                            // left
                        }else if(velX==0 && velY>0){
                            // down
                        }else if(velX==0 && velY<0){
                            // up
                        }else if(velX>0 && velY>0){
                            // down right

                        }else if(velX<0 && velY>0){
                            // down left
                        }else if(velX>0 && velY<0){
                            // up right
                        }else if(velX<0 && velY<0){
                            // up left
                        }*/
                    }
                }else if(tempObject.getId()==ID.RedGround){
                    if(tempObject.getBounds().intersects(getBounds())){
                        if(limited){
                            velX=0;
                            velY=0;
                            limited=false;
                        }
                    }
                }else if(tempObject.getId()==ID.Shaggy){
                    Enemy tempShaggy = (Enemy) tempObject;
                    Rectangle tempRect = getBounds();
                    tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                    if(tempRect.getBounds().intersects(NPC_RECT(tempObject))) {
                        x += velX * -1;
                        y += velY * -1;
                        if (tempShaggy.getBattleReady()) {
                            game.setSwitch(false);
                            game.setCurrentState(Game.STATE.Battle);
                            game.setCurrentBattle(new Battle(this, tempShaggy, game.getHandler(), game, game.getAp()));
                        }
                    }
                }
            }
        }

    }


    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    public Rectangle getSpecialBounds(){
        Rectangle tempRect = getBounds();
        tempRect.setBounds((int)tempRect.getX()-10, (int)tempRect.getY()-10, (int)tempRect.getWidth()+20, (int)tempRect.getHeight()+20);
        return tempRect;
    }

    public Rectangle NPC_RECT(GameObject tempObject){
        Rectangle tempRect = tempObject.getBounds();
        tempRect.setBounds((int)tempRect.getX(), (int)tempRect.getY(), (int)tempRect.getWidth(), (int)tempRect.getHeight()/2);
        return tempRect;
    }

    public boolean getLimited(){
        return this.limited;
    }
    public void setLimited(boolean limited){
        this.limited = limited;
    }
    public BufferedImage[] getCurrentImages(){
        return currentImages;
    }
    public void setCurrentImages(BufferedImage[] currentImages){
        this.currentImages = currentImages;
    }



    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Inventory getInv(){
        return this.inv;
    }
    public Magic getMagic(){
        return this.magic;
    }

    public void drawManaBar(int y, Graphics g){
        g.setColor(Color.red.darker());
        g.fillRect(540, y, 200, 32);
        if(mana >= maxMana*.5){
            g.setColor(Color.blue.brighter().brighter().brighter());
        }else{
            g.setColor(Color.black);
        }

        if(mana==maxMana){
            g.fillRect(540, y, 200, 32);
            if(this.getBackwards()){
                g.setColor(Color.black);
            }else
                g.setColor(Color.white);

            g.drawRect(540, y, 200, 32);
            if(this.getBackwards()){
                g.setColor(Color.white);
            }else
                g.setColor(Color.black);
            g.drawRect(539,y-1,202,34);
        }else{
            g.fillRect(540, y, mana*(200/maxMana), 32);
            if(!this.getBackwards()){
                g.setColor(Color.black);
            }else{
                g.setColor(Color.white);
            }
            g.drawRect(540, y, 200, 32);
            if(this.getBackwards()){
                g.setColor(Color.white);
            }else
                g.setColor(Color.black);
            g.drawRect(539,y-1,202,34);
        }


    }

    public void drawManaBar(int x,int y, Graphics g){
        g.setColor(Color.red.darker());
        g.fillRect(x, y, 200, 32);
        if(mana >= maxMana*.5){
            g.setColor(Color.blue.brighter().brighter().brighter());
        }else{
            g.setColor(Color.red.darker().darker().darker());
        }


        if(mana==maxMana){
            g.fillRect(x, y, 200, 32);
            if(this.getBackwards()){
                g.setColor(Color.black);
            }else
                g.setColor(Color.white);
            g.drawRect(x, y, 200, 32);
        }else{
            g.fillRect(x, y, mana*(200/maxMana), 32);
            if(this.getBackwards()){
                g.setColor(Color.black);
            }else
            if(this.getBackwards()){
                g.setColor(Color.black);
            }else{
                if(this.getBackwards()){
                    g.setColor(Color.black);
                }else
                    g.setColor(Color.white);
            }
            g.drawRect(x, y, 200, 32);
        }

    }



    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }



    public Items getArmor() {
        return armor;
    }

    public void setArmor(Items armor) {
        this.armor = armor;
    }

    public Items getWeapon() {
        return weapon;
    }

    public void setWeapon(Items weapon) {
        this.weapon = weapon;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void LevelUp(){
        xp=xp-level*100;
        level++;
        attack+=3;
        defense+=3;
        maxhealth+=5;
        maxMana+=5;
        health=maxhealth;
        mana=maxMana;

    }

    public boolean getBackwards() {
        return backwards;
    }

    public void setBackwards(boolean backwards) {
        this.backwards = backwards;
    }

    public void addOneKill(){
        this.kills++;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
