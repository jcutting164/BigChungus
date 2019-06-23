import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Spells extends GameObject implements Serializable {

    private String name;
    private String desc;
    private boolean usable;
    private boolean obtained=false;
    private BufferedImage img = Game.tex.Orb;
    private Magic magic;
    private Game game;
    public Spells(String name, String desc, Magic magic,float x, float y, float height, float width, ID id, Game game){
        super(x, y, height, width, id);
        this.name = name;
        this.desc = desc;
        this.magic = magic;
        this.game=game;
    }


    public void use(){
        if(name.equals("Auto kill")){
            game.getCurrentBattle().getEnemy().setHealth(0);
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

}
