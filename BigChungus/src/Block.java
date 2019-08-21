import java.awt.*;
import java.io.Serializable;

public class Block extends GameObject implements Serializable {

    Handler handler;
    Game game;
    Player player;
    transient Image img;
    ID sID=ID.BlackGround;

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
        else if(id==ID.sideRail)
            img=tex.area51Tiles[0];
        else if(id==ID.bottomRail)
            img=tex.area51Tiles[1];
        else if(id==ID.Ice)
            img=tex.Ice;
        else if(id==ID.invisWall)
            img=null;
        else if(id==ID.Tree2)
            img=tex.Tree2;
        else if(id==ID.grave)
            img=tex.grave;
        else if(id==ID.dirt)
            img=tex.dirt;
        else if(id==ID.whiteKey)
            img=tex.whiteKey;
        else if(id==ID.blackKey)
            img=tex.blackKey;
        else if(id==ID.backPiano)
            img=tex.pianoBack;

        //walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);

    }

    public Block(float x, float y, float height, float width, Handler handler, Game game, ID id, ID sID){
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
        else if(id==ID.sideRail)
            img=tex.area51Tiles[0];
        else if(id==ID.bottomRail)
            img=tex.area51Tiles[1];
        else if(id==ID.Ice)
            img=tex.Ice;
        else if(id==ID.invisWall)
            img=null;
        else if(id==ID.Tree2)
            img=tex.Tree2;
        else if(id==ID.grave)
            img=tex.grave;
        else if(id==ID.dirt)
            img=tex.dirt;
        else if(id==ID.whiteKey)
            img=tex.whiteKey;
        else if(id==ID.blackKey)
            img=tex.blackKey;
        else if(id==ID.backPiano)
            img=tex.pianoBack;

        this.sID=sID;

    }


    public void tick(){

    }

    public void render(Graphics g){



        if(img!=null){
            if(img!=tex.Room1_1){
                g.drawImage(img,(int)x,(int)y,(int)width,(int)height,null);
            }else{
                if(id==ID.GrayGround){
                    g.setColor(Color.gray);
                    g.fillRect((int)x, (int)y, (int)width, (int)height);
                }else if(id==ID.BlackGround){
                    g.setColor(Color.black);
                    g.fillRect((int)x, (int)y, (int)width, (int)height);
                }


            }
        }else{

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
            else if(id==ID.sideRail)
                img=tex.area51Tiles[0];
            else if(id==ID.bottomRail)
                img=tex.area51Tiles[1];
            else if(id==ID.Ice)
                img=tex.Ice;
            else if(id==ID.invisWall)
                img=null;
            else if(id==ID.Tree2)
                img=tex.Tree2;
            else if(id==ID.grave)
                img=tex.grave;
            else if(id==ID.dirt)
                img=tex.dirt;
            else if(id==ID.whiteKey)
                img=tex.whiteKey;
            else if(id==ID.blackKey)
                img=tex.blackKey;
            else if(id==ID.backPiano)
                img=tex.pianoBack;


            if(id==ID.Simon) {
                if (sID == ID.green) {
                    g.setColor(new Color(0,162,0));
                    g.fillRect((int) x, (int) y, (int) width, (int) height);

                } else if (sID == ID.red) {
                    g.setColor(new Color(162,0,0));
                    g.fillRect((int) x, (int) y, (int) width, (int) height);

                } else if (sID == ID.blue) {
                    g.setColor(Color.blue);
                    g.fillRect((int) x, (int) y, (int) width, (int) height);

                } else if (sID == ID.yellow) {
                    g.setColor(new Color(156, 159,0));
                    g.fillRect((int) x, (int) y, (int) width, (int) height);

                } else if (sID == ID.lightGreen) {
                    g.setColor(Color.green.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter());
                    g.fillRect((int) x, (int) y, (int) width, (int) height);

                } else if (sID == ID.lightRed) {
                    g.setColor(Color.red.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter());
                    g.fillRect((int) x, (int) y, (int) width, (int) height);

                } else if (sID == ID.lightBlue) {
                    g.setColor(Color.cyan);
                    g.fillRect((int) x, (int) y, (int) width, (int) height);

                } else if (sID == ID.lightYellow) {
                    g.setColor(Color.yellow.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter());
                    g.fillRect((int) x, (int) y, (int) width, (int) height);

                }
            }
        }




    }
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public ID getsID() {
        return sID;
    }

    public void setsID(ID sID) {
        this.sID = sID;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
