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
    public Spells(String name, String desc, Magic magic,float x, float y, float height, float width, ID id, Game game,int manaREQ){
        super(x, y, height, width, id);
        this.name = name;
        this.desc = desc;
        this.magic = magic;
        this.game=game;
        this.manaREQ=manaREQ;
    }


    public void use(){
        if(name.equals("Auto kill")){
            game.getCurrentBattle().getEnemy().setHealth(0);
        }else if(name.equals("Basic Heal")&&game.getPlayer().getMana()>=manaREQ){
            game.getPlayer().setHealth(game.getPlayer().getHealth()+manaREQ);
            game.getPlayer().setMana(game.getPlayer().getMana()-manaREQ);
        }else if(name.equals("Boogie Woogie")&&game.getPlayer().getMana()>=manaREQ){
            if(!(game.getCurrentBattle()==null)){
                game.getPlayer().setAttack(game.getPlayer().getAttack()+5);
                game.getPlayer().setMana(game.getPlayer().getMana()-manaREQ);
            }

        }else if(name.equals("Enie Meni Minie Mo")&&game.getPlayer().getMana()>=manaREQ){
            int temp= ThreadLocalRandom.current().nextInt(4);
            if(temp==0){
                game.getPlayer().setHealth(game.getPlayer().getHealth()-20);
            }else if(temp==1){
                game.getPlayer().setHealth(game.getPlayer().getHealth()-5);
            }else if(temp==2){
                game.getCurrentBattle().getEnemy().setHealth(game.getCurrentBattle().getEnemy().getHealth()-20);
            }else if(temp==3){
                game.getCurrentBattle().getEnemy().setHealth(game.getCurrentBattle().getEnemy().getHealth()-5);
            }
            game.getPlayer().setMana(game.getPlayer().getMana()-manaREQ);
        }else if(name.equals("Yeetus Deletus")&&game.getPlayer().getMana()>=manaREQ){
            game.getCurrentBattle().getEnemy().setHealth(game.getCurrentBattle().getEnemy().getHealth()-15);
            game.getPlayer().setMana(game.getPlayer().getMana()-manaREQ);

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
}
