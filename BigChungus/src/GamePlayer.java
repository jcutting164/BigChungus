

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Random;
public class GamePlayer extends GameObject implements Serializable {

    Random r = new Random();
    Handler handler;
    private Game game;

    public GamePlayer(float x, float y, float height, float width, ID id, Handler handler, Game game) {
        super(x, y, height, width, id);
        this.handler = handler;
        this.velX=0;
        this.velY=0;
        this.game=game;

    }

    public Rectangle getBounds() {
        // fix
        return new Rectangle((int) x, (int) y, (int) height, (int) width);
    }


    public void tick() {
        System.out.println("velx is:          "+velX);
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH -35);
        y = Game.clamp(y, Game.HEIGHT -200, Game.HEIGHT -56);

        collision();


    }

    private void collision() {
        for(float i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get((int) i);

            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss || tempObject.getId()==ID.Laser) {
                // collision code
                if(getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(handler.object.get((int) i));
                    game.setGameHealth(game.getGameHealth()-1);
                }
            }
        }
    }

    public void render(Graphics g) {





        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, (int) height, (int) width);



    }


}
