import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Spells extends GameObject implements Serializable {

    private String name;
    private String desc;
    private boolean usable;
    private boolean obtained=false;
    private BufferedImage img = Game.tex.Orb;
    private Magic magic;
    private Game game;
    private int manaREQ;
    private boolean damaging;
    private boolean healing;
    private int healFactor;
    private int damageFactor;
    private String desc2="";
    public Spells(String name, String desc, Magic magic,float x, float y, float height, float width, ID id, Game game,int manaREQ, boolean healing, boolean damaging, int healFactor, int damageFactor){
        super(x, y, height, width, id);
        this.name = name;
        this.desc = desc;
        this.magic = magic;
        this.game=game;
        this.manaREQ=manaREQ;
        this.damaging=damaging;
        this.healing=healing;
        this.healFactor=healFactor;
        this.damageFactor=damageFactor;
    }

    public Spells(String name, String desc, String desc2, Magic magic,float x, float y, float height, float width, ID id, Game game,int manaREQ, boolean healing, boolean damaging, int healFactor, int damageFactor){
        super(x, y, height, width, id);
        this.name = name;
        this.desc = desc;
        this.magic = magic;
        this.game=game;
        this.manaREQ=manaREQ;
        this.damaging=damaging;
        this.healing=healing;
        this.healFactor=healFactor;
        this.damageFactor=damageFactor;
        this.desc2=desc2;

    }


    public void use(Character user, Character enemy){
        if(name.equals("Auto kill")){
            game.getCurrentBattle().getEnemy().setHealth(0);
        }else if(name.equals("Basic Heal")&&user.getMana()>=manaREQ){
            if(!game.getPlayer().getBackwards())
                user.setHealth(user.getHealth()+healFactor);
            else
                enemy.setHealth(enemy.getHealth()+healFactor);

            user.setMana(user.getMana()-manaREQ);////// NOTE: CURRENTLY ADAPTING SPELLS TO ACCOUNT FOR AN ENEMY USER
        }else if(name.equals("Boogie Woogie")&&user.getMana()>=manaREQ){
            if(!(game.getCurrentBattle()==null)){
                user.setAttack(user.getAttack()+5);
                user.setMana(user.getMana()-manaREQ);
            }

        }else if(name.equals("Enie Meni Minie Mo")&&user.getMana()>=manaREQ){
            if(!(game.getCurrentBattle()==null)){
                int temp= ThreadLocalRandom.current().nextInt(4);
                if(temp==0){
                    user.setHealth(user.getHealth()-25);
                }else if(temp==1){
                    user.setHealth(user.getHealth()-5);
                }else if(temp==2){
                    enemy.setHealth(enemy.getHealth()-25);
                }else if(temp==3){
                    enemy.setHealth(enemy.getHealth()-5);
                }
                user.setMana(user.getMana()-manaREQ);
            }

        }else if(name.equals("Yeetus Deletus")&&user.getMana()>=manaREQ){
            if(!(game.getCurrentBattle()==null)){

                if(!game.getPlayer().getBackwards()){
                    enemy.setHealth(enemy.getHealth()-damageFactor);

                }else
                    user.setHealth(user.getHealth()-damageFactor);

                user.setMana(user.getMana()-manaREQ);
            }


        }else if(name.equals("Mana Throw")){
            if(!(game.getCurrentBattle()==null)){

                if(!game.getPlayer().getBackwards()){
                    enemy.setHealth(enemy.getHealth()-user.getMana());

                }else
                    user.setHealth(user.getHealth()-user.getMana());

                user.setMana(0);
            }
        }else if(name.equals("Earthquake")){
            if(!(game.getCurrentBattle()==null)){

                if(enemy.getId()==ID.Pikachu){
                    enemy.setHealth(enemy.getHealth()-50);
                }else{
                    if(!game.getPlayer().getBackwards()){
                        enemy.setHealth(enemy.getHealth()-5);

                    }else
                        user.setHealth(user.getHealth()-5);
                }



                user.setMana(user.getMana()-manaREQ);
            }
        }else if(name.equals("Focus Beam")){
            if(!(game.getCurrentBattle()==null)){
                int damage=0;
                if(user.getWeapon()!=null)
                    damage=user.getAttack()+user.getWeapon().getDamageFactor();
                else
                    damage=user.getAttack();

                if(!game.getPlayer().getBackwards()){

                    enemy.setHealth(enemy.getHealth()-damage);

                }else
                    user.setHealth(user.getHealth()-damage);




                user.setMana(user.getMana()-manaREQ);
            }

        }else if(name.equals("Laser")){
            if(!(game.getCurrentBattle()==null)){

                if(!game.getPlayer().getBackwards()){
                    enemy.setHealth(enemy.getHealth()-damageFactor);

                }else
                    user.setHealth(user.getHealth()-damageFactor);

                user.setMana(user.getMana()-manaREQ);
            }

        }else if(name.equals("Screech")){
            int damage=ThreadLocalRandom.current().nextInt(1,200);
            if(!(game.getCurrentBattle()==null)){

                if(!game.getPlayer().getBackwards()){
                    enemy.setHealth(enemy.getHealth()-damage);

                }else
                    user.setHealth(user.getHealth()-damage);

                user.setMana(user.getMana()-manaREQ);
            }

        }else if(name.equals("Take it back now")){

            if(!(game.getCurrentBattle()==null)){
                int damage=user.getMaxHealth()-user.getHealth();

                if(!game.getPlayer().getBackwards()){
                    enemy.setHealth(enemy.getHealth()-damage);

                }else
                    user.setHealth(user.getHealth()-damageFactor);

                user.setMana(user.getMana()-manaREQ);
            }

        }else if(name.equals("Skert")){
            int damage=ThreadLocalRandom.current().nextInt(1,20);
            if(!(game.getCurrentBattle()==null)){

                if(!game.getPlayer().getBackwards()){
                    enemy.setHealth(enemy.getHealth()-damage);

                }else
                    user.setHealth(user.getHealth()-damage);

                user.setMana(user.getMana()-manaREQ);
            }

        }else if(name.equals("Full Heal")){
            int heal=user.getMaxHealth();

            if(!game.getPlayer().getBackwards()){
                enemy.setHealth(enemy.getHealth()+heal);

            }else
                user.setHealth(user.getHealth()+heal);

            user.setMana(user.getMana()-manaREQ);

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

    public int getManaREQ() {
        return manaREQ;
    }

    public void setManaREQ(int manaREQ) {
        this.manaREQ = manaREQ;
    }

    public boolean getDamaging() {
        return damaging;
    }

    public void setDamaging(boolean damaging) {
        this.damaging = damaging;
    }

    public boolean getHealing() {
        return healing;
    }

    public void setHealing(boolean healing) {
        this.healing = healing;
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
}
