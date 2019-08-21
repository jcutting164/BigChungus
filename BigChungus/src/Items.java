import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class Items extends GameObject implements Serializable {

    private String name;
    private String desc;
    private boolean usable;
    private boolean obtained=false;
    private transient BufferedImage img;
    private Game game;
    private int attack;
    private int defense;
    private boolean healing;
    private boolean damaging;
    private int healFactor;
    private int damageFactor;
    String desc2="";
    Textures tex=Game.getInstance();
    public Items(String name, String desc,float x, float y, float height, float width, ID id,Game game, int attack, int defense, boolean healing, boolean damaging,int healFactor, int damageFactor){
        super(x, y, height, width, id);
        this.name = name;
        this.desc = desc;
        this.game=game;
        this.healing=healing;
        this.damaging=damaging;
        this.healFactor=healFactor;
        this.damageFactor=damageFactor;
        this.img=tex.Orb;
    }
    public Items(String name, String desc,String desc2,float x, float y, float height, float width, ID id,Game game, int attack, int defense, boolean healing, boolean damaging,int healFactor, int damageFactor){
        super(x, y, height, width, id);
        this.desc2=desc2;
        this.name = name;
        this.desc = desc;
        this.game=game;
        this.healing=healing;
        this.damaging=damaging;
        this.healFactor=healFactor;
        this.damageFactor=damageFactor;
    }


    public void use(Character user, Character enemy){

        AudioPlayer.getSound("Select").play();




        if(name.equals("Basic HP")){
            System.out.println("YAY");
            user.getInv().removeItem(this);
        }else if(name.equals("Empty Water Bottle")){
            int damage= ThreadLocalRandom.current().nextInt(5,11);
            if(!game.getPlayer().getBackwards())
                enemy.setHealth(enemy.getHealth()-damage);
            else
                user.setHealth(user.getHealth()-damage);
            user.getInv().removeItem(this);
        }else if(name.equals("Lipton Iced Tea")){
            if(!game.getPlayer().getBackwards())
                user.setHealth(user.getHealth()+healFactor);
            else
                enemy.setHealth(enemy.getHealth()+healFactor);
            user.getInv().removeItem(this);
        }else if(name.equals("Tide Pod")){
            if(!game.getPlayer().getBackwards())
                user.setHealth(user.getHealth()+healFactor);
            else
                enemy.setHealth(enemy.getHealth()+healFactor);

            user.getInv().removeItem(this);
        }else if(name.equals("Light Saber")){
            if(user.getWeapon()==null){
                user.setWeapon(this);
                user.getInv().removeItem(this);
            }else{
                user.getInv().addItem(game.getPlayer().getWeapon());
                user.setWeapon(this);
                user.getInv().removeItem(this);

            }
        }else if(name.equals("Alien Hat")){
            if(user.getArmor()==null){
                user.setArmor(this);
                user.getInv().removeItem(this);

            }else{
                user.getInv().addItem(game.getPlayer().getArmor());
                user.setArmor(this);
                user.getInv().removeItem(this);


            }
        }else if(name.equals("Wizard Wand")){
            if(user.getWeapon()==null){
                user.setWeapon(this);
                user.getInv().removeItem(this);

            }else{
                user.getInv().addItem(game.getPlayer().getWeapon());
                user.setWeapon(this);
                user.getInv().removeItem(this);


            }
        }else if(name.equals("Wizard Cloak")){
            if(user.getArmor()==null){
                user.setArmor(this);
                user.getInv().removeItem(this);

            }else{
                user.getInv().addItem(game.getPlayer().getArmor());
                user.setArmor(this);
                user.getInv().removeItem(this);


            }
        }else if(name.equals("Chicken Nugget")){
            if(!game.getPlayer().getBackwards())
                user.setHealth(user.getHealth()+healFactor);
            else
                enemy.setHealth(enemy.getHealth()+healFactor);
            user.getInv().removeItem(this);



        }else if(name.equals("Insta Protein")){
            if(!(game.getCurrentBattle()==null)){
                if(!game.getPlayer().getBackwards())
                    user.setAttack(user.getAttack()+15);
                else
                    enemy.setHealth(enemy.getHealth()+healFactor);
                user.getInv().removeItem(this);

            }




        }else if(name.equals("Big Bopper")){
            if(!game.getPlayer().getBackwards())
                user.setHealth(user.getHealth()+healFactor);
            else
                enemy.setHealth(enemy.getHealth()+healFactor);
            user.getInv().removeItem(this);



        }else if(name.equals("Space Food")){
            int heal = user.getMaxHealth();
            if(!game.getPlayer().getBackwards())
                user.setHealth(user.getHealth()+heal);
            else
                enemy.setHealth(enemy.getHealth()+heal);
            user.getInv().removeItem(this);



        }else if(name.equals("CIT Shirt")){

            if(user.getArmor()==null){
                user.setArmor(this);
                user.getInv().removeItem(this);

            }else {
                user.getInv().addItem(game.getPlayer().getArmor());
                user.setArmor(this);
                user.getInv().removeItem(this);
            }

        }else if(name.equals("YuGiOh Card")){
            if(!(game.getCurrentBattle()==null)){
                if(!game.getPlayer().getBackwards())
                    enemy.setHealth(enemy.getHealth()-damageFactor);
                else
                    user.setHealth(user.getHealth()-damageFactor);
                user.getInv().removeItem(this);
            }


        }else if(name.equals("Nokia")){
            if(user.getWeapon()==null){
                user.setWeapon(this);
                user.getInv().removeItem(this);

            }else {
                user.getInv().addItem(game.getPlayer().getWeapon());
                user.setWeapon(this);
                user.getInv().removeItem(this);
            }
        }else if(name.equals("Uno Reverse")){
            if(!(game.getCurrentBattle()==null)){
                if(!game.getPlayer().getBackwards())
                    enemy.setHealth(enemy.getHealth()-enemy.getAttack()*2);
                else
                    user.setHealth(user.getHealth()-user.getAttack()*2);
                user.getInv().removeItem(this);
            }

        }else if(name.equals("Bunny Ears")){
            if(user.getArmor()==null){
                user.setArmor(this);
                user.getInv().removeItem(this);

            }else {
                user.getInv().addItem(game.getPlayer().getArmor());
                user.setArmor(this);
                user.getInv().removeItem(this);
            }
        }else if(name.equals("Orb Of Resurrection")){
            game.setEndgame(true);
            game.getPlayer().setLimited(true);
            game.getTbHandler().addObject(new TextBox(game.getBigChungus(),"Thank you, hero. Perhaps we can move on together as one.",0,0,0,0,ID.TextBox,game.getTbHandler()));
            user.getInv().removeItem(this);
            game.getPlayer().getInv().setOpen(false);
            game.setHero(true);
        }else if(name.equals("Orb Of Destruction")){
            game.setEndgame(true);
            game.getPlayer().setLimited(true);
            game.getTbHandler().addObject(new TextBox(game.getBigChungus(),"Oh no, what did you do?",0,0,0,0,ID.TextBox,game.getTbHandler()));
            game.getPlayer().getInv().setOpen(false);
            game.setHero(false);
            user.getInv().removeItem(this);

        }else if(name.equals("Bleach")){
            user.setMaxMana(user.getMaxMana()+25);
            user.setMana(user.getMaxMana());
            user.getInv().removeItem(this);

        }else if(name.equals("Chicken Tender")){
            user.setMaxHealth(user.getMaxHealth()+25);
            user.setHealth(user.getMaxhealth());
            user.getInv().removeItem(this);

        }
    }


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public boolean getUsable(){
        return usable;
    }
    public void setUsable(boolean usable){
        this.usable = usable;
    }

    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }

    public void tick(){

    }
    public void render(Graphics g){
        tex=Game.getInstance();
        if(!obtained)
            g.drawImage(tex.Orb,(int)x,(int)y,(int)height,(int)width,null);

    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,(int)width,(int)height);

    }

    public boolean getObtained() {
        return obtained;
    }

    public void setObtained(boolean obtained) {
        this.obtained = obtained;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isHealing() {
        return healing;
    }

    public void setHealing(boolean healing) {
        this.healing = healing;
    }

    public boolean isDamaging() {
        return damaging;
    }

    public void setDamaging(boolean damaging) {
        this.damaging = damaging;
    }

    public int getHealFactor() {
        return healFactor;
    }

    public void setHealFactor(int healFactor) {
        this.healFactor = healFactor;
    }

    public int getDamageFactor() {
        return damageFactor;
    }

    public void setDamageFactor(int damageFactor) {
        this.damageFactor = damageFactor;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public void setGame(Game game){
        this.game=game;
    }

}
