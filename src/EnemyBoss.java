

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBoss extends GameObject{

    private Handler handler;
    private int track;
    private long timeNow, time, timeOfLastShot;
    private Color col;
    private Game game;
    public EnemyBoss(float x, float y, float height, float width, ID id, Handler handler, Color col,Game game)  {
        super(x, y, height, width, id);
        // TODO Auto-generated constructor stub
        this.x = 15;
        this.y = 15;
        this.handler = handler;
        this.track = 0;
        this.col = col;
        this.game=game;

        // temp velocities until pattern figured out
        velX = 0;
        velY = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public void tick() {
        // TODO Auto-generated method stub
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

        if(track == 5) {
            handler.addObject(new SmartEnemy(getX(), getY(), getHeight(), getWidth(), ID.SmartEnemy, handler, col));
            handler.addObject(new SmartEnemy(getX(), getY() - 75, getHeight() , getWidth() , ID.SmartEnemy, handler, col));
            handler.addObject(new SmartEnemy(getX(), getY() - 50, getHeight(), getWidth(), ID.SmartEnemy, handler, col));
            handler.removeObject(this);
        }


        setX(Game.clampJ(x, Game.WIDTH - 1265, Game.WIDTH - 40));

        if(getX() <= Game.WIDTH - 40 && (track == 0 || track == 2 || track == 4)) {

            if(track == 0)
                setVelX(2);
            else if(track == 2)
                setVelX(4);
            else if(track == 4)
                setVelX(6);




            if(getX() == Game.WIDTH - 40) {
                setVelX(0);
                if(getY() <= getY() + (150 * (track+1))) {
                    setVelY(5);
                    setY(Game.clamp(y, getY(), getY() + (150 * (track+1))));
                    if(getY() == (150 * (track+1))) {
                        setVelY(0);
                        track++;
                    }
                }
            }

        }else if(getX() >= Game.WIDTH - 1265 && (track == 1 || track == 3)) {
            if(track == 1)
                setVelX(-3);
            else if(track == 3)
                setVelX(-5);

            setX(Game.clamp(x, Game.WIDTH - 1265, Game.WIDTH - 40));
            if(getX() == Game.WIDTH - 1265) {
                setVelX(0);
                if(getY() <= getY() + (150 * (track+1))) {
                    setVelY(5);
                    setY(Game.clamp(y, getY(), getY() + (150 * (track+1))));
                    if(getY() == (150 * (track+1))) {
                        setVelY(0);
                        track++;
                    }
                }
            }
        }
    }






    //	handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.01f, handler));



    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(col);
        g.fillRect((int) x, (int) y, (int) height, (int) width);
    }



}
