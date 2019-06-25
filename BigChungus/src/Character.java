import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;


public abstract class Character extends GameObject implements Serializable {

    Handler handler;
    protected Game game;
    int tempImage;
    protected int health;
    protected int maxhealth;

    protected transient Animation walkLeft;
    protected transient Animation walkRight;
    protected transient Animation walkDown;
    protected transient Animation walkUp;
    protected transient BufferedImage Face;
    protected int speed;
    protected ID sID;

    Textures tex = Game.getInstance();

    public Character(float x, float y, float height, float width, Handler handler, Game game, ID id, int speed){
        super(x, y, height, width, id);
        this.handler = handler;
        this.game = game;
        this.tempImage = 0;
        this.speed = speed;

        //walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);

    }


    public void tick(){
        x+=velX;
        y+=velY;
        //playerWalk.runAnimation();
        //walkLeft.runAnimation();

    }
    public void render(Graphics g){



        /*

        g.drawImage(tex.Player_WalkLeft[0], (int) this.x, (int)this.y, game);
        if(velX != 0){
            walkLeft.drawAnimation(g, (int)x, (int)y);
        }else{
            g.drawImage(tex.Player_WalkLeft[0], (int)x, (int)y,null);
        }
        */

    }
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }


    public int getSpeed(){
        return this.speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getHealth(){
        return this.health;
    }
    public void setHealth(int health){
        this.health = health;
    }

    public void drawHealthBar(int y, Graphics g){
        g.setColor(Color.red.darker());
        g.fillRect(540, y, 200, 32);
        if(health >= maxhealth*.5){
            g.setColor(Color.green);
        }else if(health <= maxhealth*.5){
            g.setColor(Color.yellow);
        }else{
            g.setColor(Color.red);
        }

        g.fillRect(540, y, health*(200/maxhealth), 32);
        g.setColor(Color.white);
        g.drawRect(540, y, 200, 32);



    }
    public void drawHealthBar(int x,int y, Graphics g){
        g.setColor(Color.red.darker());
        g.fillRect(x, y, 200, 32);
        if(health >= maxhealth*.5){
            g.setColor(Color.green);
        }else if(health <= maxhealth*.5){
            g.setColor(Color.yellow);
        }else{
            g.setColor(Color.red);
        }

        g.fillRect(x, y, health*(200/maxhealth), 32);
        g.setColor(Color.white);
        g.drawRect(x, y, 200, 32);



    }

    public int getMaxHealth(){
        return this.maxhealth;
    }
    public void setMaxHealth(int maxhealth){
        this.maxhealth = maxhealth;
    }

    public Game getGame(){
        return this.game;
    }
    public void setGame(Game game){
        this.game = game;
    }

    public ID getsID() {
        return sID;
    }

    public void setsID(ID sID) {
        this.sID = sID;
    }
}
