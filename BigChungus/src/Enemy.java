import org.newdawn.slick.Music;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;


public  class Enemy extends NPC implements Serializable {
    private int lastKeyReleased;
    Handler handler;
    private boolean battleReady;
    TBHandler tbHandler;
    String text;
    private Player player;
    private transient BufferedImage[] currentImages;
    private transient BufferedImage BattleForm;

    private String name;
    protected Color nameColor;
    private int currentMove;
    protected int moves;
    private boolean runnable;
    private boolean defeated = false;
    private transient Animation walkLeftB;
    private transient Animation walkRightB;
    private transient Animation walkDownB;
    private transient Animation walkUpB;
    private transient BufferedImage FaceB;
    private transient BufferedImage BattleFormB;

    public Enemy(float x, float y, float height, float width, Handler handler, Game game, ID id, int speed, TBHandler tbHandler, String text, Player player,ID sId,int attack, int defense,int moves,BufferedImage BattleForm,Color nameColor, int health){
        super(x, y, height, width,handler,game, id, speed, tbHandler, "CLICK CLICK", player, sId);
        this.handler = handler;
        this.game = game;
        this.tbHandler = tbHandler;
        this.text = text;
        this.player = player;
        this.runnable=true;
        this.attack=attack;
        this.defense=defense;
        this.moves=moves;
        this.BattleForm=BattleForm;
        this.name=text;
        this.nameColor=nameColor;
        this.health=health;
        this.maxhealth=health;
        this.battleReady=true;

// to be replaced in actual class
        tex=Game.getInstance();
        if(!(id==ID.Knuckles)&&!(id==ID.Pikachu)&&!(id==ID.AntiHero)&&!(id==ID.BigChungus)){
            this.walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
            this.walkRight = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
            this.walkUp = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
            this.walkDown = new Animation(speed, tex.Player_WalkDown[0], tex.Player_WalkDown[1]);
            this.currentImages= tex.Player_WalkDown;
            this.isVisible = true;
            this.Face = tex.Player_Face[0];
            this.battleReady=true;


            this.walkLeftB = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
            this.walkRightB = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
            this.walkUpB = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
            this.walkDownB = new Animation(speed, tex.Player_WalkDown[0], tex.Player_WalkDown[1]);
            this.FaceB = tex.Player_Face[0];
        }else if(id==ID.Knuckles){
            this.walkLeft = new Animation(speed, tex.Knuckles_WalkLeft[0], tex.Knuckles_WalkLeft[1], tex.Knuckles_WalkLeft[2], tex.Knuckles_WalkLeft[3], tex.Knuckles_WalkLeft[4], tex.Knuckles_WalkLeft[5], tex.Knuckles_WalkLeft[6], tex.Knuckles_WalkLeft[7]);
            this.walkRight = new Animation(speed, tex.Knuckles_WalkRight[0], tex.Knuckles_WalkRight[1], tex.Knuckles_WalkRight[2], tex.Knuckles_WalkRight[3], tex.Knuckles_WalkRight[4], tex.Knuckles_WalkRight[5], tex.Knuckles_WalkRight[6], tex.Knuckles_WalkRight[7]);

            this.currentImages= tex.Knuckles_WalkLeft;

            this.Face = tex.Knuckles_Face;
            this.BattleForm = tex.Knuckles_BattleForm;

        }else if(id==ID.Pikachu){
            this.walkLeft = new Animation(speed, tex.Pikachu_WalkLeft[0], tex.Pikachu_WalkLeft[1], tex.Pikachu_WalkLeft[2]);
            this.walkRight = new Animation(speed, tex.Pikachu_WalkRight[0], tex.Pikachu_WalkRight[1], tex.Pikachu_WalkRight[2]);
            this.walkUp = new Animation(speed, tex.Pikachu_WalkUp[0], tex.Pikachu_WalkUp[1], tex.Pikachu_WalkUp[2]);
            this.walkDown = new Animation(speed, tex.Pikachu_WalkDown[0], tex.Pikachu_WalkDown[1], tex.Pikachu_WalkDown[2]);

            this.currentImages= tex.Pikachu_WalkDown;
            this.isVisible = true;
            this.Face = tex.PikachuFace[0];
            this.BattleForm = tex.PikachuBF[0];

        }else if(id==ID.AntiHero){
            this.inv = new Inventory(game);
            this.magic=new Magic();
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

        }else if(id==ID.BigChungus){
            this.nameColor = Color.gray;
            this.maxhealth=this.health;
            this.moves = 4;
            this.runnable=false;

// to be replaced
            this.walkRight = new Animation(speed, tex.BigChungus_WalkRight[1], tex.BigChungus_WalkRight[2]);


            this.currentImages= tex.BigChungus_WalkRight;
            this.isVisible = true;
            this.Face = tex.BigChungusFace[0];
            this.BattleForm = tex.BigChungusBF[0];
        }




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

            if(!(id==ID.Knuckles)&&!(id==ID.Pikachu)&&!(id==ID.AntiHero)&&!(id==ID.BigChungus)){
                this.walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
                this.walkRight = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
                this.walkUp = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
                this.walkDown = new Animation(speed, tex.Player_WalkDown[0], tex.Player_WalkDown[1]);
                this.currentImages= tex.Player_WalkDown;
                this.isVisible = true;
                this.Face = tex.Player_Face[0];
                this.battleReady=true;


                this.walkLeftB = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);
                this.walkRightB = new Animation(speed, tex.Player_WalkRight[0], tex.Player_WalkRight[1]);
                this.walkUpB = new Animation(speed, tex.Player_WalkUp[1], tex.Player_WalkUp[2]);
                this.walkDownB = new Animation(speed, tex.Player_WalkDown[0], tex.Player_WalkDown[1]);
                this.FaceB = tex.Player_Face[0];
            }else if(id==ID.Knuckles){
                this.walkLeft = new Animation(speed, tex.Knuckles_WalkLeft[0], tex.Knuckles_WalkLeft[1], tex.Knuckles_WalkLeft[2], tex.Knuckles_WalkLeft[3], tex.Knuckles_WalkLeft[4], tex.Knuckles_WalkLeft[5], tex.Knuckles_WalkLeft[6], tex.Knuckles_WalkLeft[7]);
                this.walkRight = new Animation(speed, tex.Knuckles_WalkRight[0], tex.Knuckles_WalkRight[1], tex.Knuckles_WalkRight[2], tex.Knuckles_WalkRight[3], tex.Knuckles_WalkRight[4], tex.Knuckles_WalkRight[5], tex.Knuckles_WalkRight[6], tex.Knuckles_WalkRight[7]);

                this.currentImages= tex.Knuckles_WalkLeft;

                this.Face = tex.Knuckles_Face;
                this.BattleForm = tex.Knuckles_BattleForm;

            }else if(id==ID.Pikachu){
                this.walkLeft = new Animation(speed, tex.Pikachu_WalkLeft[0], tex.Pikachu_WalkLeft[1], tex.Pikachu_WalkLeft[2]);
                this.walkRight = new Animation(speed, tex.Pikachu_WalkRight[0], tex.Pikachu_WalkRight[1], tex.Pikachu_WalkRight[2]);
                this.walkUp = new Animation(speed, tex.Pikachu_WalkUp[0], tex.Pikachu_WalkUp[1], tex.Pikachu_WalkUp[2]);
                this.walkDown = new Animation(speed, tex.Pikachu_WalkDown[0], tex.Pikachu_WalkDown[1], tex.Pikachu_WalkDown[2]);

                this.currentImages= tex.Pikachu_WalkLeft;
                this.isVisible = true;
                this.Face = tex.PikachuFace[0];
                this.BattleForm = tex.PikachuBF[0];

            }else if(id==ID.AntiHero){
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

            }else if(id==ID.BigChungus){
                this.nameColor = Color.gray;
                this.maxhealth=this.health;
                this.moves = 4;
                this.runnable=false;

// to be replaced
                this.walkRight = new Animation(speed, tex.BigChungus_WalkRight[1], tex.BigChungus_WalkRight[2]);


                this.currentImages= tex.BigChungus_WalkRight;
                this.isVisible = true;
                this.Face = tex.BigChungusFace[0];
                this.BattleForm = tex.BigChungusBF[0];
            }
        }


        //g.drawImage(currentImages[0], (int) this.x, (int)this.y, (int)this.width, (int)this.height, null);


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
            currentImages=tex.Player_WalkRight;

        }else if(player.getCurrentImages()==tex.Player_WalkRight){
            currentImages=tex.Player_WalkLeft;
        }else if(player.getCurrentImages()==tex.Player_WalkUp){
            currentImages=tex.Player_WalkDown;
        }else if(player.getCurrentImages()==tex.Player_WalkDown){
            currentImages=tex.Player_WalkUp;
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
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Color getNameColor(){
        return nameColor;
    }
    public void setNameColor(Color nameColor){
        this.nameColor = nameColor;
    }


    public void chooseMove(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, moves);
        this.currentMove=randomNum;
    }


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isBattleReady() {
        return battleReady;
    }

    public TBHandler getTbHandler() {
        return tbHandler;
    }

    public void setTbHandler(TBHandler tbHandler) {
        this.tbHandler = tbHandler;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BufferedImage getBattleForm() {
        return BattleForm;
    }

    public void setBattleForm(BufferedImage battleForm) {
        BattleForm = battleForm;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(int currentMove) {
        this.currentMove = currentMove;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public boolean isRunnable() {
        return runnable;
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public Animation getWalkLeftB() {
        return walkLeftB;
    }

    public void setWalkLeftB(Animation walkLeftB) {
        this.walkLeftB = walkLeftB;
    }

    public Animation getWalkRightB() {
        return walkRightB;
    }

    public void setWalkRightB(Animation walkRightB) {
        this.walkRightB = walkRightB;
    }

    public Animation getWalkDownB() {
        return walkDownB;
    }

    public void setWalkDownB(Animation walkDownB) {
        this.walkDownB = walkDownB;
    }

    public Animation getWalkUpB() {
        return walkUpB;
    }

    public void setWalkUpB(Animation walkUpB) {
        this.walkUpB = walkUpB;
    }

    public BufferedImage getFaceB() {
        return FaceB;
    }

    public void setFaceB(BufferedImage faceB) {
        FaceB = faceB;
    }

    public BufferedImage getBattleFormB() {
        return BattleFormB;
    }

    public void setBattleFormB(BufferedImage battleFormB) {
        BattleFormB = battleFormB;
    }
}
