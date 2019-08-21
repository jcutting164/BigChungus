import java.awt.*;
import java.io.Serializable;


public abstract class Usables extends GameObject implements Serializable {
    protected String name;
    protected String desc;
    protected Handler handler;
    protected Battle battle;
    protected long timeNow, time, timeOfLastShot;
    protected int track;
    protected Enemy enemy;


    public Usables(float x, float y, float height, float width, ID id, Handler handler, Battle battle, Enemy enemy){

        super(x, y, height, width, id);
        this.handler = handler;
        this.battle = battle;
        this.timeOfLastShot = 0;
        this.enemy = enemy;

    }

    public void tick(){

    }

    public void render(Graphics g){

    }

    public Rectangle getBounds(){


        return new Rectangle((int)x, (int)y, (int)width, (int)height);


    }

    public abstract void use();
    public abstract void effect();
    public abstract void end();




    public void collision(){

        for(int i =0; i<handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if(temp.getId()==ID.Enemy || temp.getId()==ID.Knuckles){
                effect();

            }
        }

    }



}
