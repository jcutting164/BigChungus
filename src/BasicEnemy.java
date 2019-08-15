

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject{

    private Handler handler;
    private Color col;
    private Random r;
    private long timeNow;
    private long end;
    private int tempVelX, tempVelY;
    private int hold;
    private Game game;
    public BasicEnemy(float x, float y, float height, float width, ID id, Handler handler, Color col, Game game)  {
        super(x, y, height, width, id);
        // TODO Auto-generated constructor stub

        this.handler = handler;
        this.col = col;
        this.r = new Random();
        this.hold = 1000;
        this.timeNow = System.currentTimeMillis();
        this.end = this.timeNow + hold;
        this.game=game;


        // temp velocities until pattern figured out
        velX = 3;
        velY = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public void tick() {
        // TODO Auto-generated method stub
        if(y >= 720) {
            handler.addObject(new SmartEnemy(getX(), getY(), getHeight(), getWidth(), ID.SmartEnemy, handler, col));
            handler.removeObject(this);
        }

        x+=velX;
        y+=velY;


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








        // building better AI that is not as glitchy







        //	if(y <= 0 || y>= Game.HEIGHT - 32) velY *= -1;
        //	if(x <= 0 || x>= Game.WIDTH -16) velX *= -1;
	/*
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
		} */






        //	handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.01f, handler));

    }

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
