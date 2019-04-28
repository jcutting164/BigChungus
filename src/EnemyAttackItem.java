import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyAttackItem extends GameObject implements Serializable {
    private BufferedImage[] images;
    private Animation animation;
    private float scaleX, scaleY;
    private String movement;
    private BattlePlayer bPlayer;
    private Player player;
    private Handler handler;
    Textures tex = Game.getInstance();
    Rectangle playerBounds = new Rectangle(522, 538, 246, 246);
    private long time, timeNow, timeOfLastShot=0;

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
        }else if(movement.equals("Pokeball")){
            if(y>500){
                velY=0;
                for(int i = 0; i<5; i++){
                    EnemyAttackItem temp1 = new EnemyAttackItem(this.getX(), this.getY(), 97, 96, ID.EnemyAttackItem, tex.Pikachu_A3, 6, 96, 97, bPlayer, player, handler);
                    temp1.setVelX(ThreadLocalRandom.current().nextInt(-7, 6 + 1));
                    temp1.setVelY(ThreadLocalRandom.current().nextInt(-7, 6 + 1));
                    temp1.movement="none";
                    handler.addObject(temp1);
                    handler.removeObject(this);
                }



            }
        }else if(movement.equals("PikachuShot")){
            timeNow = System.currentTimeMillis();
            time = timeNow - timeOfLastShot;
            if(time<2000){
                EnemyAttackItem temp1 = new EnemyAttackItem(this.getX()+30, this.getY()+ThreadLocalRandom.current().nextInt(5, 20 + 1), 17, 16, ID.EnemyAttackItem, tex.Pikachu_A2, 6, 16, 17, bPlayer, player, handler);
                temp1.setVelX(-10);
                temp1.setVelY(0);
                temp1.movement="none";
                handler.addObject(temp1);

            }else if(time>=4000){
                timeOfLastShot=timeNow;
            }

            if(this.getY()>800 && this.getVelY()!=-2){
                this.setVelY(-1);
            }else if(this.getY()<500 && this.getVelY()!=2){
                this.setVelY(1);
            }


        }else if(movement.equals("Gottem")){
            if(this.getBounds().intersects(playerBounds)){
                for(int i = 0; i<5; i++){
                    EnemyAttackItem temp1 = new EnemyAttackItem(this.getX(), this.getY(), 97, 54, ID.EnemyAttackItem, tex.BigChungus_A4, 4, 54, 97, bPlayer, player, handler);
                    if(this.velX==7 && this.velY==7){
                        temp1.setVelX(4-i);
                        temp1.setVelY(4+i);
                    }else if(this.velX==-7){
                        temp1.setVelX(-4+i);
                        temp1.setVelY(4+i);

                    }else if(this.velX==7 && this.velY==-7){
                        temp1.setVelX(4+i);
                        temp1.setVelY(-4+i);

                    }else if(this.velX==-5){
                        temp1.setVelX(-4-i);
                        temp1.setVelY(-4+i);

                    }
                    temp1.movement="none";
                    handler.addObject(temp1);
                    handler.removeObject(this);
                }
            }
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

    public void LightningShot(int num){

        if(num==0){
            x=300;
            y=400;
            velX=0;
            velY=0;
        }else if(num==1){
            x=500;
            y=400;
            velX=0;
            velY=0;

        }else if(num==2){
            x=800;
            y=400;
            velX=0;
            velY=0;

        }else if(num==3){
            x=1000;
            y=400;
            velX=0;
            velY=0;

        }
        movement="LightningShot";

    }

    public void Pokeball(){
        x=600;
        y=200;
        velY=5;
        movement="Pokeball";
    }

    public void LargeLightningShot(){
        int randomNum = ThreadLocalRandom.current().nextInt(500, 700);
        x=randomNum;
        y=500;
        movement="LargeLightningShot";
    }

    public void PikachuShot(){
        x=800;
        y=ThreadLocalRandom.current().nextInt(500, 700);
        movement="PikachuShot";
        this.velY=2;
    }

    public void CarrotShot(){
        x=0;
        y=ThreadLocalRandom.current().nextInt(0, 500);
        velX=9;
        velY=6;

        movement="CarrotShot";
    }
    public int CoinFlip(){
        x=800;
        y=600;
        movement="CoinFlip";
        return ThreadLocalRandom.current().nextInt(0,5);

    }

    public void CardAttack(){
        int temp = ThreadLocalRandom.current().nextInt(0,4);
        if(temp==0){
            x=200;
            y=300;
            velX=12;
            velY=12;
        }else if(temp==1){
            x=800;
            y=300;
            velX=-12;
            velY=12;
        }else if(temp==2){
            x=200;
            y=1200;
            velX=12;
            velY=-12;
        }else if(temp==3){
            x=800;
            y=1200;
            velX=-10;
            velY=-14;
        }
        movement="CardAttack";
    }

    public void Gottem(){
        int temp = ThreadLocalRandom.current().nextInt(0,4);
        if(temp==0){
            x=200;
            y=300;
            velX=7;
            velY=7;
        }else if(temp==1){
            x=800;
            y=300;
            velX=-7;
            velY=7;
        }else if(temp==2){
            x=0;
            y=1200;
            velX=7;
            velY=-7;
        }else if(temp==3){
            x=1000;
            y=1200;
            velX=-5;
            velY=-9;
        }

        movement="Gottem";
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

    public void setImages(BufferedImage[] temp){
        this.images=temp;

        this.animation=new Animation(1, this.images);
    }


}
