import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyAttackItem extends GameObject {
    private BufferedImage[] images;
    private Animation animation;
    private float scaleX, scaleY;
    private String movement;
    private BattlePlayer bPlayer;
    private Player player;
    private Handler handler;

    public EnemyAttackItem(float x, float y, float height, float width, ID id, BufferedImage[] images, int speed, float scaleX, float scaleY, BattlePlayer bPlayer, Player player, Handler handler){
        super(x, y, height, width, id);

        this.scaleX=scaleX;
        this.scaleY=scaleY;
        this.velX=0;
        this.velY=0;
        this.isVisible=true;

        this.animation=new Animation(speed, images);
        this.bPlayer = bPlayer;
        this.player = player;
        this.handler = handler;
    }


    public void tick(){
        collision();
        x+=velX;
        y+=velY;
        animation.runAnimation();

    }

    public void render(Graphics g){
        animation.drawAnimation(g, (int)x, (int)y, (int)scaleX, (int)scaleY);

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }


    public void boxBounce(){
        int randomNum = ThreadLocalRandom.current().nextInt(4, 6 + 1);
        if(ThreadLocalRandom.current().nextInt(0, 2)==0){
            randomNum*=-1;
        }
        velX=randomNum;

        randomNum = ThreadLocalRandom.current().nextInt(4, 6 + 1);
        if(ThreadLocalRandom.current().nextInt(0, 2)==0){
            randomNum*=-1;
        }
        velY=randomNum;

        movement="boxBounce";

    }

    public void collision(){
        if(movement.equals("boxBounce")){
            if(x<=512 || x>=752){
                velX*=-1;
            }
            if(y<=528 || y>=768){
                velY*=-1;
            }


        }
        if(getBounds().intersects(bPlayer.getBounds()) && !bPlayer.getCooldown()){
            // add real damage calculation later based on attack/defense
            player.setHealth(player.getHealth()-10);
            bPlayer.setCooldown(true);
            bPlayer.setTimeOfLastShot(System.currentTimeMillis());
            bPlayer.setTrack(0);
            handler.removeObject(this);

        }

    }


}
