import java.awt.Color;

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

        if(movement.equals("DYKDW")){
            float diffX = x - bPlayer.getX() - 8;
            float diffY = y - bPlayer.getY() - 8;
            float distance = (float) Math.sqrt((x-bPlayer.getX()) * (x-bPlayer.getX()) + (y-bPlayer.getY()) * (y-bPlayer.getY()));

            velX = (float)  ((-2.0 / distance) * diffX);
            velY = (float) ((-2.0 / distance) * diffY);
        }

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
    public void randomProtShot(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
        int randomNum2 = ThreadLocalRandom.current().nextInt(1, 3);
        if(randomNum==0){

            if(randomNum2==1){
                x=700;
                y=500;
                velY=6;
            }else if(randomNum2==2){
                x=600;
                y=500;
                velY=6;
            }

        }else if(randomNum==1){
            if(randomNum2==0){
                x=700;
                y=1200;
                velY=-6;
            }else if(randomNum2==2){
                x=600;
                y=1200;
                velY=-6;
            }

        }else if(randomNum==2){
            if(randomNum2==1){
                x=900;
                y=600;
                velX=-6;
            }else if(randomNum2==2){
                x=900;
                y=700;
                velX=-6;
            }

        }else if(randomNum==3){
            if(randomNum2==1){
                x=100;
                y=700;
                velX=6;
            }else if(randomNum2==2){
                x=100;
                y=600;
                velX=6;
            }


        }else{
            if(randomNum2==1){
                x=100;
                y=700;
                velX=6;
            }else if(randomNum2==2){
                x=100;
                y=600;
                velX=6;
            }
        }

        movement = "randomProtShot";

    }
    public void DYKDW(){
        x= 600;
        y=200;
        movement = "DYKDW";
    }
    public void LS(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        if(randomNum==0){
            y=550;
            x=1200;
            velX=-5;
        }else if(randomNum==1){
            y=450;
            x=-150;
            velX=5;
        }

        movement="LS";
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
        if(movement.equals("LS")){

            Rectangle temp = getBounds();
            temp.setBounds((int)temp.getX()+50, (int)temp.getY()+50, (int)temp.getWidth()-100, (int)temp.getHeight()-100);

            if(temp.intersects(bPlayer.getBounds()) && !bPlayer.getCooldown()){
                System.out.println("YES");
                // add real damage calculation later based on attack/defense
                player.setHealth(player.getHealth()-10);
                bPlayer.setCooldown(true);
                bPlayer.setTimeOfLastShot(System.currentTimeMillis());
                bPlayer.setTrack(0);
                handler.removeObject(this);

            }
        }else{
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


}
