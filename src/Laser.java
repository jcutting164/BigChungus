


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Laser extends GameObject{

    private GameObject user;
    private Handler handler;
    private Color col;
    private Game game;

    public Laser(float x, float y, float height, float width, ID id, GameObject user, Handler handler, Color col, Game game) {
        super(x, y, height, width, id);

        this.user = user;
        this.velY = 4;
        this.handler = handler;
        this.col = col;
        this.game=game;

    }

    public void tick() {
        if(user.getId() == ID.GamePlayer) {
            y -= velY;
        }else if(user.getId() == ID.BasicEnemy || user.getId() == ID.EnemyBoss ) {
            y += velY;
        }


        collision();

    }

    public void render(Graphics g) {

        g.setColor(col);
        g.fillRect((int) x, (int) y, (int) 3, (int) 16);

    }

    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle((int) x, (int) y, (int) height, (int) width);
    }


    private void collision() {


        for(int i = 0; i<handler.object.size(); i++) {


            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.GamePlayer && (user.getId() == ID.BasicEnemy || user.getId()== ID.EnemyBoss  || user.getId() == ID.EndEnemy)) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    game.setGameHealth(game.getGameHealth()-1);
                    handler.removeObject(this);
                }


            }else if((tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss || tempObject.getId() == ID.EndEnemy) && user.getId() == ID.GamePlayer){

                // looks to hurt enemy

                if(getBounds().intersects(tempObject.getBounds())) {

                    handler.removeObject(handler.object.get(i));

                    handler.removeObject(this);

                    game.setScore(game.getScore()+50);


                    AudioPlayer.getSound("DEATH_SOUND").play();

                }
            }else if(getX() >= Game.WIDTH || getX() <= 0 || getY() >= Game.HEIGHT || getY() <= 0) {
                handler.removeObject(this);
            }
        }
    }


}
