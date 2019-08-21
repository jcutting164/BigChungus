

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Random;

public class CoolEnemy extends GameObject implements Serializable {

    private Handler handler;
    private long timeNow, time, timeOfLastShot;
    private Color col;

    private Random r;
    private long end;
    private int tempVelX, tempVelY;
    private int hold;
    private Game game;
    public CoolEnemy(float x, float y, float height, float width, ID id, Handler handler, Color col, Game game)  {
        super(x, y, height, width, id);
        // TODO Auto-generated constructor stub

        this.handler = handler;
        this.col = col;
        this.game=game;




        this.r = new Random();
        this.hold = 1000;
        this.timeNow = System.currentTimeMillis();
        this.end = this.timeNow + hold;


        // temp velocities until pattern figured out
        velX = 3;
        velY = 3;


    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public void tick() {

        if(y >= 720) {
            handler.addObject(new SmartEnemy(getX(), getY(), getHeight(), getWidth(), ID.SmartEnemy, handler, col));
            handler.removeObject(this);
        }

        x+=velX;
        y+=velY;

        //	if(y <= 0 || y>= Game.HEIGHT - 32) velY *= -1;
        //	if(x <= 0 || x>= Game.WIDTH -16) velX *= -1;

        timeNow = System.currentTimeMillis();
        time = timeNow - timeOfLastShot;
        if(time > 400) {
            handler.addObject(new Laser(this.getX() + (this.getWidth() / 2), this.getY() + (this.getHeight() - 1), 16, 4, ID.Laser, this, handler, Color.green,game));
            timeOfLastShot = timeNow;
        }



        //x+=velX;
        //y+=velY;


        timeNow = System.currentTimeMillis();

        if(timeNow < end) {
            collision();
            setVelX(tempVelX);
            setVelY(tempVelY);
        }else {
            setVelX(0);
            setVelY(0);
            if(timeNow > end + hold) {
                hold = 100 + r.nextInt(1000);
                end += hold*2;
                tempVelX = 1+ r.nextInt((int) game.getLevel() + 1);
                tempVelX = (int) Game.clamp(tempVelX, 1, 7);
                if(r.nextInt(2) == 1) {
                    tempVelX *= -1;
                }
                tempVelY = 1+ r.nextInt(3);
            }

        }


    }






    //	handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.01f, handler));



    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(col);
        g.fillRect((int) x, (int) y, (int) height, (int) width);
    }

    public void collision() {
        if(x >= Game.WIDTH - this.width || x<=0) {
            tempVelX *=-1;
        }

        if(y>= Game.HEIGHT || y<= 0)
            handler.removeObject(this);
    }

}
