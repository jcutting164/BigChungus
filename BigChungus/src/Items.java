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
    private BufferedImage img= Game.tex.Orb;
    private Game game;
    private int attack;
    private int defense;
    private boolean healing;
    private boolean damaging;
    private int healFactor;
    private int damageFactor;
    public Items(String name, String desc,float x, float y, float height, float width, ID id,Game game, int attack, int defense, boolean healing, boolean damaging,int healFactor, int damageFactor){
        super(x, y, height, width, id);
        this.name = name;
        this.desc = desc;
        this.game=game;
        this.healing=healing;
        this.damaging=damaging;
        this.healFactor=healFactor;
        this.damageFactor=damageFactor;
    }


    public void use(Character user, Character enemy){



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
        if(!obtained)
            g.drawImage(img,(int)x,(int)y,(int)height,(int)width,null);

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
}
