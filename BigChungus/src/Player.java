

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable {

    private int lastKeyReleased;
    private int lastKeyHit;
    Handler handler;
    private boolean downStop;
    private Rectangle range;
    private boolean limited;
    private BufferedImage[] currentImages;
    private String name;
    private Inventory inv;
    private Magic magic;
    private int mana, maxMana;



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
    }




    public void tick(){
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

        range = new Rectangle((int)x/2, (int)y/2, 2*38, 2*148);


    }
    public void render(Graphics g){

        //g.drawImage(tex.Player_WalkLeft[0], (int) this.x, (int)this.y, 38, 148, null);


        // deals with pure x moving animation
        if(velX != 0 && velY==0){
            if(velX < 0){
                walkLeft.drawAnimation(g, (int)x, (int)y, 38, 148);
                currentImages = tex.Player_WalkLeft;

            }else if(velX > 0){
                walkRight.drawAnimation(g, (int)x, (int)y, 38, 148);
                currentImages = tex.Player_WalkRight;
            }


        //deals with sprites when player stops moving
        }else if(velX==0 && velY==0){
            if(this.currentImages == tex.Player_WalkLeft)
                g.drawImage(tex.Player_WalkLeft[0], (int)x, (int)y, 38, 148, null);
            else if(this.currentImages==tex.Player_WalkRight)
                g.drawImage(tex.Player_WalkRight[0], (int)x, (int)y, 38, 148, null);
            else if(this.currentImages==tex.Player_WalkUp)
                g.drawImage(tex.Player_WalkUp[0], (int)x, (int)y, 38, 148, null);
            else if(this.currentImages==tex.Player_WalkDown)
                g.drawImage(tex.Player_WalkDown[0], (int)x, (int)y, 38, 148, null);
            else{
                g.drawImage(tex.Player_WalkDown[0], (int)x, (int)y, 38, 148, null);
            }
            // deals with pure y moving animation
        }else if(velX==0 && velY!=0){
            if(velY < 0){
                walkUp.drawAnimation(g, (int)x, (int)y, 38, 148);
                currentImages = tex.Player_WalkUp;

            }else if(velY > 0){
                walkDown.drawAnimation(g, (int)x, (int)y, 38, 148);
                currentImages = tex.Player_WalkDown;
            }

            //deals with diagonal movement
        }else if(velX!=0 && velY!=0){
            if((velY < 0 && velX < 0) || (velY<0 && velX>0)){
                walkUp.drawAnimation(g, (int)x, (int)y, 38, 148);
                currentImages = tex.Player_WalkUp;

            }else if((velX > 0 && velY > 0) || (velY>0 && velX<0)){
                walkDown.drawAnimation(g, (int)x, (int)y, 38, 148);
                currentImages = tex.Player_WalkDown;
            }
        }


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
        // IDEA FOR RENDERING ONLY NEEDED THINGS
        // WHILE COLLISION CHEKCING, CHECK EVERYTHING AGAINST SPECIAL BOUNDS AND ASSIGN VISIBILITY
        for(int i = 0; i < this.handler.object.size(); i++){
            GameObject tempObject = this.handler.object.get(i);

            if(tempObject.getId() == ID.BlackGround){
                Rectangle tempRect = getBounds();
                tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                if(tempRect.getBounds().intersects(tempObject.getBounds())){

                    x+= velX * -1;
                    y+= velY*-1;
                }
            }else if(tempObject.getId() == ID.NPC){
                NPC tempNPC = (NPC) tempObject;
                Rectangle tempRect = getBounds();
                tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                    x+= velX * -1;
                    y+= velY*-1;
                    if(tempNPC.getBattleReady()){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                    }
                }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                    tempNPC.interaction();
                    x+= velX * -1;
                    y+= velY*-1;
                    lastKeyHit=100;
                }
            }else if(tempObject.getId() == ID.Knuckles){
                Knuckles tempKnuckles = (Knuckles) tempObject;
                Rectangle tempRect = getBounds();
                tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                    x+= velX * -1;
                    y+= velY*-1;
                    if(tempKnuckles.getBattleReady()){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, tempKnuckles, handler, game,  game.getAp()));
                    }
                }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                    tempKnuckles.interaction();
                    x+= velX * -1;
                    y+= velY*-1;
                    lastKeyHit=100;
                }
            }else if(tempObject.getId() == ID.Pikachu){
                Pikachu tempPikachu = (Pikachu) tempObject;
                Rectangle tempRect = getBounds();
                tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                    x+= velX * -1;
                    y+= velY*-1;
                    if(tempPikachu.getBattleReady()){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, tempPikachu, handler, game, game.getAp()));
                    }
                }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                    tempPikachu.interaction();
                    x+= velX * -1;
                    y+= velY*-1;
                    lastKeyHit=100;
                }
            }else if(tempObject.getId() == ID.BigChungus){
                BigChungus tempBigChungus = (BigChungus) tempObject;
                Rectangle tempRect = getBounds();
                tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()-5);
                if(tempRect.getBounds().intersects(NPC_RECT(tempObject))){
                    x+= velX * -1;
                    y+= velY*-1;
                    if(tempBigChungus.getBattleReady()){
                        game.setSwitch(false);
                        game.setCurrentState(Game.STATE.Battle);
                        game.setCurrentBattle(new Battle(this, tempBigChungus, handler, game, game.getAp()));
                    }
                }if(getSpecialBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){

                    tempBigChungus.interaction();
                    x+= velX * -1;
                    y+= velY*-1;
                    lastKeyHit=100;
                }
            }else if(tempObject.getId()==ID.SaveIcon){
                SaveObject tempSaveIcon = (SaveObject) tempObject;
                Rectangle tempRect = tempSaveIcon.getBounds();
                tempRect.setSize((int)tempRect.getWidth(), (int)tempRect.getHeight()/2);
                if(tempRect.intersects(getBounds())){
                    x+= velX*-1;
                    y+= velY*-1;

                }

                if(getBounds().intersects(tempObject.getBounds()) && (lastKeyHit==4)){
                    tempSaveIcon.interaction();
                    x+= velX*-1;
                    y+= velY*-1;
                    lastKeyHit=100;
                }

            }
        }
    }


    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 38, 148);
    }

    public Rectangle getSpecialBounds(){
        Rectangle tempRect = getBounds();
        tempRect.setBounds((int)tempRect.getX()-10, (int)tempRect.getY(), (int)tempRect.getWidth()+20, (int)tempRect.getHeight()+10);
        return tempRect;
    }

    public Rectangle NPC_RECT(GameObject tempObject){
        Rectangle tempRect = tempObject.getBounds();
        tempRect.setBounds((int)tempRect.getX(), (int)tempRect.getY(), (int)tempRect.getWidth(), (int)tempRect.getHeight()- 75);
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
            g.setColor(Color.red);
        }

        g.fillRect(540, y, mana*(200/maxMana), 32);
        g.setColor(Color.white);
        g.drawRect(540, y, 200, 32);
    }


}
