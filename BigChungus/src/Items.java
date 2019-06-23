import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Items extends GameObject implements Serializable {

    private String name;
    private String desc;
    private boolean usable;
    private Inventory inv;
    private boolean obtained=false;
    private BufferedImage img= Game.tex.Orb;
    public Items(String name, String desc, Inventory inv,float x, float y, float height, float width, ID id){
        super(x, y, height, width, id);
        this.name = name;
        this.desc = desc;
        this.inv = inv;
    }


    public void use(){
        if(name.equals("Basic HP")){
            System.out.println("YAY");
            inv.removeItem(this);
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
