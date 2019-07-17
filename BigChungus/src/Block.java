import java.awt.*;
import java.io.Serializable;

public class Block extends GameObject implements Serializable {

    Handler handler;
    Game game;
    Player player;
    Image img;


    transient Textures  tex = Game.getInstance();


    public Block(float x, float y, float height, float width, Handler handler, Game game, ID id){
        super(x, y, height, width, id);
        this.handler = handler;
        this.game = game;
        this.player = player;
        this.isVisible = true;

        if(id == ID.GrayGround){
            img=tex.Room1_1;
        }else if(id == ID.BlackGround){
            img=tex.Room1_1;
        }else if(id==ID.Pavement){
            img=tex.Pavement[0];
        }else if(id==ID.Grass)
            img=tex.Grass;
        else if(id==ID.Tree)
            img=tex.Tree;
        else if(id==ID.Mushroom)
            img=tex.Mushroom;
        else if(id==ID.RedGround)
            img=tex.RedGround;
        //walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);

    }


    public void tick(){

    }

    public void render(Graphics g){



        if(img!=tex.Room1_1){
            g.drawImage(img,(int)x,(int)y,(int)height,(int)width,null);
        }else{
            if(id==ID.GrayGround){
                g.setColor(Color.gray);
                g.fillRect((int)x, (int)y, (int)width, (int)height);
            }else if(id==ID.BlackGround){
                g.setColor(Color.black);
                g.fillRect((int)x, (int)y, (int)width, (int)height);
            }

        }



    }
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }







}
