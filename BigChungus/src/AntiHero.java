import org.newdawn.slick.Music;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class AntiHero extends Enemy implements Serializable {
    private int lastKeyReleased;
    Handler handler;
    private boolean battleReady;
    TBHandler tbHandler;
    String text;
    private Player player;


    private transient BufferedImage[] currentImages;


    public AntiHero(float x, float y, float height, float width, Handler handler, Game game, ID id, int speed, TBHandler tbHandler, String text, Player player, boolean battleReady){
        super(x, y, height, width,handler,game, id, speed, tbHandler, "...", player,ID.AntiHero,70,70);
        this.handler = handler;
        this.game = game;
        this.tbHandler = tbHandler;
        this.text = text;
        this.player = player;
        this.battleReady=battleReady;
        this.health = 30;
        setName("The Antihero");
        this.nameColor = Color.white;
        this.maxhealth=this.health;
        this.moves = 4;
        this.attack=game.getPlayer().getAttack();
        this.defense=game.getPlayer().getDefense();
        this.inv = new Inventory();
        for(int i= 0; i<player.getInv().inv.size(); i++){
            Items temp = player.getInv().inv.get(i);
            this.inv.addItem(new Items(temp.getName(),temp.getDesc(),temp.getX(),temp.getY(),temp.getHeight(),temp.getWidth(),temp.getId(),game,temp.getAttack(),temp.getDefense(),temp.isHealing(),temp.isDamaging(),temp.getHealFactor(),temp.getDamageFactor()));
        }
        this.magic=new Magic();
        for(int i= 0; i<player.getMagic().magic.size(); i++){
            Spells temp = player.getMagic().magic.get(i);
            this.magic.addItem(new Spells(temp.getName(),temp.getDesc(),this.magic,temp.getX(),temp.getY(),temp.getHeight(),temp.getWidth(),temp.getId(),game,temp.getManaREQ(),temp.getHealing(),temp.getDamaging(),temp.getHealFactor(),temp.getDamageFactor()));
        }



// to be replaced
        this.walkLeft = new Animation(speed, tex.AntiHero_WalkLeft[0], tex.AntiHero_WalkLeft[1]);
        this.walkRight = new Animation(speed, tex.AntiHero_WalkRight[0], tex.AntiHero_WalkRight[1]);
        this.walkUp = new Animation(speed, tex.AntiHero_WalkUp[0], tex.AntiHero_WalkUp[1], tex.AntiHero_WalkUp[2]);
        this.walkDown = new Animation(speed, tex.AntiHero_WalkDown[0], tex.AntiHero_WalkDown[1], tex.AntiHero_WalkDown[2]);

        this.currentImages= tex.AntiHero_WalkDown;
        this.isVisible = true;
        this.Face = tex.AntiHero_Face[0];
        this.BattleForm = tex.AntiHeroBF;


        this.walkLeftB = new Animation(speed, tex.AntiHero_WalkLeftB[0], tex.AntiHero_WalkLeftB[1]);
        this.walkRightB = new Animation(speed, tex.AntiHero_WalkRightB[0], tex.AntiHero_WalkRightB[1]);
        this.walkUpB = new Animation(speed, tex.AntiHero_WalkUpB[0], tex.AntiHero_WalkUpB[1], tex.AntiHero_WalkUpB[2]);
        this.walkDownB = new Animation(speed, tex.AntiHero_WalkDownB[0], tex.AntiHero_WalkDownB[1], tex.AntiHero_WalkDownB[2]);

        this.FaceB = tex.AntiHero_FaceB[0];
        this.BattleFormB = tex.AntiHeroBFB;
        this.mana=player.getMaxMana();
        this.maxMana=player.getMaxMana();

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
            g.drawImage(currentImages[0], (int) this.x, (int)this.y, (int)(width), (int)(height), null);
        }catch(Exception e){
            tex=Game.getInstance();

            this.walkLeft = new Animation(speed, tex.AntiHero_WalkLeft[0], tex.AntiHero_WalkLeft[1]);
            this.walkRight = new Animation(speed, tex.AntiHero_WalkRight[0], tex.AntiHero_WalkRight[1]);
            this.walkUp = new Animation(speed, tex.AntiHero_WalkUp[0], tex.AntiHero_WalkUp[1], tex.AntiHero_WalkUp[2]);
            this.walkDown = new Animation(speed, tex.AntiHero_WalkDown[0], tex.AntiHero_WalkDown[1], tex.AntiHero_WalkDown[2]);

            this.currentImages= tex.AntiHero_WalkDown;
            this.Face = tex.AntiHero_Face[0];
            this.BattleForm = tex.AntiHeroBF;


            this.walkLeftB = new Animation(speed, tex.AntiHero_WalkLeftB[0], tex.AntiHero_WalkLeftB[1]);
            this.walkRightB = new Animation(speed, tex.AntiHero_WalkRightB[0], tex.AntiHero_WalkRightB[1]);
            this.walkUpB = new Animation(speed, tex.AntiHero_WalkUpB[0], tex.AntiHero_WalkUpB[1], tex.AntiHero_WalkUpB[2]);
            this.walkDownB = new Animation(speed, tex.AntiHero_WalkDownB[0], tex.AntiHero_WalkDownB[1], tex.AntiHero_WalkDownB[2]);

            this.isVisible = true;
            this.FaceB = tex.AntiHero_FaceB[0];
            this.BattleFormB = tex.AntiHeroBFB;

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
            currentImages=tex.AntiHero_WalkRight;

        }else if(player.getCurrentImages()==tex.Player_WalkRight){
            currentImages=tex.AntiHero_WalkLeft;
        }else if(player.getCurrentImages()==tex.Player_WalkUp){
            currentImages=tex.AntiHero_WalkDown;
        }else if(player.getCurrentImages()==tex.Player_WalkDown){
            currentImages=tex.AntiHero_WalkUp;
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
