import java.awt.*;
import java.io.Serializable;


public abstract class Usables_E extends GameObject implements Serializable {
    protected String name;
    protected String desc;
    protected Handler handler;
    protected Battle battle;
    protected long timeNow, time, timeOfLastShot;
    protected int track;
    protected Enemy enemy;

    public Usables_E(float x, float y, float height, float width, ID id, Handler handler, Battle battle, Enemy enemy){

        super(x, y, height, width, id);
        this.handler = handler;
        this.battle = battle;
        this.timeOfLastShot = 0;
        this.enemy = enemy;
        this.velY=1;

    }

    public void tick(){
        y+=velY;
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

        if(getBounds().intersects(0,950,1280,1)){
            end();
        }

    }



}
