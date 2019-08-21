

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public class SmartEnemy extends GameObject implements Serializable {

    private Handler handler;
    private GameObject gp;
    private Color col;

    public SmartEnemy(float x, float y, float height, float width, ID id, Handler handler, Color col)  {
        super(x, y, height, width, id);
        // TODO Auto-generated constructor stub

        this.handler = handler;
        this.col = col;

        for(int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ID.GamePlayer) gp = handler.object.get(i);
        }



        // temp velocities until pattern figured out

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public void tick() {
        // TODO Auto-generated method stub
        x+=velX;
        y+=velY;

        float diffX = x - gp.getX() - 8;
        float diffY = y - gp.getY() - 8;
        float distance = (float) Math.sqrt((x-gp.getX()) * (x-gp.getX()) + (y-gp.getY()) * (y-gp.getY()));

        velX = (float)  ((-1.0 / distance) * diffX);
        velY = (float) ((-1.0 / distance) * diffY);


        if(y <= 0 || y>= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x>= Game.WIDTH -16) velX *= -1;




        handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.01f, handler));

        // TODO: OK SO: ADD HEALTH BAR AND COLLOSIONS WITH SMART ENEMIES + MAKE REST OF SPECIAL GAME
        // TODO: WITH GETTING OUT OF THE GAME TOOOOOOOOOOO

    }

    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(col);
        g.fillRect((int) x, (int) y, (int) height, (int) width);
    }



}
