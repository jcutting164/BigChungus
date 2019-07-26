import sun.security.krb5.internal.TGSRep;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.chrono.ThaiBuddhistEra;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyAttackItem extends GameObject implements Serializable {
    private BufferedImage[] images;
    private Animation animation;
    private float scaleX, scaleY;
    private String movement;
    private BattlePlayer bPlayer;
    private Player player;
    private Handler handler;
    private int way;
    private boolean original=false;
    Textures tex = Game.getInstance();
    Rectangle playerBounds = new Rectangle(522, 538, 246, 246);
    Rectangle expandedPlayerBounds= new Rectangle(482, 492, 286,286);
    Rectangle topPlayerBounds= new Rectangle(482, 492, 286,1);
    Rectangle rightPlayerBounds= new Rectangle(768, 492, 1,286);
    Rectangle leftPlayerBounds= new Rectangle(482, 492, 1,286);
    Rectangle bottomPlayerBounds= new Rectangle(482, 778, 286,1);

    Rectangle EtopPlayerBounds= new Rectangle(522, 538, 246,1);
    Rectangle ErightPlayerBounds= new Rectangle(768, 538, 1,246);
    Rectangle EleftPlayerBounds= new Rectangle(522, 538, 1,246);
    Rectangle EbottomPlayerBounds= new Rectangle(522, 784, 286,1);

    private long time, timeNow, timeOfLastShot=0;
    private Battle battle;
    private float extra=1;
    public EnemyAttackItem(float x, float y, float height, float width, ID id, BufferedImage[] images, int speed, float scaleX, float scaleY, BattlePlayer bPlayer, Player player, Handler handler,Battle battle){
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
        this.battle=battle;
    }


    public void tick(){

        if(battle.getLowHealth()){
            extra=(float)1.75;
        }

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
                    EnemyAttackItem temp1 = new EnemyAttackItem(this.getX(), this.getY(), 97, 96, ID.EnemyAttackItem, tex.Pikachu_A3, 6, 96, 97, bPlayer, player, handler,battle);
                    temp1.setVelX(ThreadLocalRandom.current().nextInt(-7, 6 + 1)*extra);
                    temp1.setVelY(ThreadLocalRandom.current().nextInt(-7, 6 + 1)*extra);
                    temp1.movement="none";
                    handler.addObject(temp1);
                    handler.removeObject(this);
                }



            }
        }else if(movement.equals("PikachuShot")){
            timeNow = System.currentTimeMillis();
            time = timeNow - timeOfLastShot;
            if(time<2000){
                EnemyAttackItem temp1 = new EnemyAttackItem(this.getX()+30, this.getY()+ThreadLocalRandom.current().nextInt(5, 20 + 1), 17, 16, ID.EnemyAttackItem, tex.Pikachu_A2, 6, 16, 17, bPlayer, player, handler,battle);
                temp1.setVelX(-10*extra);
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
                    EnemyAttackItem temp1 = new EnemyAttackItem(this.getX(), this.getY(), 97, 54, ID.EnemyAttackItem, tex.BigChungus_A4, 4, 54, 97, bPlayer, player, handler,battle);
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
        }else if(movement.equals("eggAttack")){
            if(this.getBounds().intersects(expandedPlayerBounds)){
                EnemyAttackItem temp1=new EnemyAttackItem(this.getX(), this.getY(), 105, 92, ID.EnemyAttackItem, tex.FatYoshi_A1, 4, 92, 105, bPlayer, player, handler,battle);
                temp1.setVelX(this.velX*2);
                temp1.setVelY(this.velY*2);
                temp1.movement="none";
                handler.addObject(temp1);
                handler.removeObject(this);


            }
        }else if(movement.equals("giraffe")){
            if(velX==4){
                if(this.getBounds().intersects(rightPlayerBounds)){
                    velX=-8;
                }
            }else if(velY==4){
                if(this.getBounds().intersects(bottomPlayerBounds)){
                    velY=-8;
                }
            }else if(velX==-4){
                if(this.getBounds().intersects(leftPlayerBounds)){
                    velX=8;
                }
            }else if(velY==-4){
                if(this.getBounds().intersects(topPlayerBounds)){
                    velY=8;
                }
            }

        }else if(movement.equals("frog")){
            if(x>=768 || x<=522){
                velX*=-1;
            }
        }else if(movement.equals("lipton")){
            if(y>=300){
                velY=20;
            }
        }else if(movement.equals("lipton2")){
            if(y>=300 && !(y>325))
                velX*=-1;
            else if(y>=500 && !(y>525))
                velX*=-1;
            else if(y>=700 && !(y>725))
                velX*=-1;
        }else if(movement.equals("bongo")){
            if(getBounds().intersects(bottomPlayerBounds)){

                velY*=-1;
            }
            if(getBounds().intersects(rightPlayerBounds) || getBounds().intersects(leftPlayerBounds))
                velX*=-1;
        }else if(movement.equals("crab")){
            if(getBounds().intersects(EleftPlayerBounds)){
                velX=2;
                velY=5;
            }
            if(getBounds().intersects(EtopPlayerBounds)|| getBounds().intersects(EbottomPlayerBounds)){
                velY*=-1;
            }
        }else if(movement.equals("crabArm")){
            if(velY>0){
                velY+=.1;
            }else
                velY-=.1;

            if(y<538){
                velY=3;
            }else if(y>784){
                velY=-3;
            }

        }else if(movement.equals("magicBlast3")){
            width++;
            height++;
            scaleX++;
            scaleY++;
        }else if(movement.equals("banana")){
            if(velY>0 && y<1000)
                velY+=.5;
            else if(velY<0 && y>100)
                velY-=.5;

            if(y>1000){
                velY=-5;
            }else if(y<100)
                velY=5;

        }else if(movement.equals("rifle")){
            if(x>200){
                velX+=1;
                x++;
                scaleX++;
                y++;
                scaleY++;
                width++;
                height++;
            }

        }else if(movement.equals("lizard")){
            if(scaleX>250){
                way=-2;
            }else if(scaleX<20){
                way=2;
            }

            scaleX+=way;
            scaleY+=way;
            width+=way;
            height+=way;
            x+=way;
            y+=way;
        }else if(movement.equals("facebook")){
            if(x<500){
                velX=0;
                velY=20;
            }

            if(ThreadLocalRandom.current().nextInt(10)==0 && original){
                EnemyAttackItem myobj = new EnemyAttackItem(2000, 100, 80, 85, ID.EnemyAttackItem, tex.Zuck_A2, 3, 85, 80, bPlayer, player, handler,battle);
                myobj.movement="facebook";
                myobj.setVelX(-10);
                myobj.setVelY(0);
                handler.addObject(myobj);
            }



        }else if(movement.equals("facebook2")){
            if(x<600){
                velX=0;
                velY=20;
            }

            if(ThreadLocalRandom.current().nextInt(10)==0&& original){
                EnemyAttackItem myobj = new EnemyAttackItem(2000, 100, 80, 85, ID.EnemyAttackItem, tex.Zuck_A2, 3, 85, 80, bPlayer, player, handler,battle);
                myobj.movement="facebook2";
                myobj.setVelX(-10);
                myobj.setVelY(0);
                handler.addObject(myobj);
            }

        }else if(movement.equals("facebook3")){
            if(x<700){
                velX=0;
                velY=20;
            }

            if(ThreadLocalRandom.current().nextInt(10)==0&& original){
                EnemyAttackItem myobj = new EnemyAttackItem(2000, 100, 80, 85, ID.EnemyAttackItem, tex.Zuck_A2, 3, 85, 80, bPlayer, player, handler,battle);
                myobj.movement="facebook3";
                myobj.setVelX(-10);
                myobj.setVelY(0);
                handler.addObject(myobj);
            }

        }else if(movement.equals("kazoo1")){
            if(ThreadLocalRandom.current().nextInt(20)==0){
                velY*=-1;
            }
        }else if(movement.equals("kazoo2")){
            if(ThreadLocalRandom.current().nextInt(20)==0)
                velX*=-1;
        }else if(movement.equals("boot")){
            if(scaleX>35){
                width-=2;
                height-=2;
                scaleX-=2;
                scaleY-=2;
            }

        }else if(movement.equals("yodelnote")){
            isVisible=!isVisible;

            velX=ThreadLocalRandom.current().nextInt(3,10);
            velY=ThreadLocalRandom.current().nextInt(2,4);
            if(ThreadLocalRandom.current().nextInt(2)==0)
                velY*=-1;

        }else if(movement.equals("krabbypatty")){
            if(ThreadLocalRandom.current().nextInt(50)==0){
                tex=Game.getInstance();
                EnemyAttackItem temp = new EnemyAttackItem(this.x, this.y, 44, 49,ID.EnemyAttackItem,tex.Spongebob_A1,2,49,44,bPlayer,player,handler,battle);
                temp.velX=ThreadLocalRandom.current().nextInt(3,6);
                temp.velY=ThreadLocalRandom.current().nextInt(3,6);
                if(ThreadLocalRandom.current().nextInt(2)==0)
                    temp.velX*=-1;
                if(ThreadLocalRandom.current().nextInt(2)==0)
                    temp.velY*=-1;
                temp.movement="krabbypatty2";
                handler.addObject(temp);
            }
            scaleX--;
            scaleY--;
            width--;
            height--;
            if(scaleX<20)
                handler.removeObject(this);

        }else if(movement.equals("net")){
            if(y>500)
                velY*=-1;
            if(y<600)
                velY*=-1;
        }else if(movement.equals("L")){
            if(x<=500){
                velX=0;
                velY=-10;
            }
        }else if(movement.equals("racket")){
            if(y>=675){
                velY=0;
                velX=ThreadLocalRandom.current().nextInt(7,10);
                if(ThreadLocalRandom.current().nextInt(2)==0)
                    velX*=-1;
                movement="racketMoved";
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
        velX=randomNum*extra;

        randomNum = ThreadLocalRandom.current().nextInt(4, 6 + 1);
        if(ThreadLocalRandom.current().nextInt(0, 2)==0){
            randomNum*=-1;
        }
        velY=randomNum*extra;

        movement="boxBounce";

    }
    public void randomProtShot(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
        int randomNum2 = ThreadLocalRandom.current().nextInt(1, 3);
        if(randomNum==0){

            if(randomNum2==1){
                x=700;
                y=500;
                velY=6*extra;
            }else if(randomNum2==2){
                x=600;
                y=500;
                velY=6*extra;
            }

        }else if(randomNum==1){
            if(randomNum2==0){
                x=700;
                y=1200;
                velY=-6*extra;
            }else if(randomNum2==2){
                x=600;
                y=1200;
                velY=-6*extra;
            }

        }else if(randomNum==2){
            if(randomNum2==1){
                x=900;
                y=600;
                velX=-6*extra;
            }else if(randomNum2==2){
                x=900;
                y=700;
                velX=-6*extra;
            }

        }else if(randomNum==3){
            if(randomNum2==1){
                x=100;
                y=700;
                velX=6*extra;
            }else if(randomNum2==2){
                x=100;
                y=600;
                velX=6*extra;
            }


        }else{
            if(randomNum2==1){
                x=100;
                y=700;
                velX=6*extra;
            }else if(randomNum2==2){
                x=100;
                y=600;
                velX=6*extra;
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
            velX=-5*extra;
        }else if(randomNum==1){
            y=450;
            x=-150;
            velX=5*extra;
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
        velY=5*extra;
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
        this.velY=2*extra;
    }

    public void CarrotShot(){
        x=0;
        y=ThreadLocalRandom.current().nextInt(0, 500);
        velX=9*extra;
        velY=6*extra;

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
            velX=12*extra;
            velY=12*extra;
        }else if(temp==1){
            x=800;
            y=300;
            velX=-12*extra;
            velY=12*extra;
        }else if(temp==2){
            x=200;
            y=1200;
            velX=12*extra;
            velY=-12*extra;
        }else if(temp==3){
            x=800;
            y=1200;
            velX=-10*extra;
            velY=-14*extra;
        }
        movement="CardAttack";
    }

    public void Gottem(){
        int temp = ThreadLocalRandom.current().nextInt(0,4);
        if(temp==0){
            x=200;
            y=300;
            velX=7*extra;
            velY=7*extra;
        }else if(temp==1){
            x=800;
            y=300;
            velX=-7*extra;
            velY=7*extra;
        }else if(temp==2){
            x=0;
            y=1200;
            velX=7*extra;
            velY=-7*extra;
        }else if(temp==3){
            x=1000;
            y=1200;
            velX=-5*extra;
            velY=-9*extra;
        }

        movement="Gottem";
    }

    public void Fireball(){
        int temp = ThreadLocalRandom.current().nextInt(500,800);
        y=temp;
        x=300;
        velX=5;
        velY=0;
        movement="Fireball";
    }
    public void Mosquito(){
        int temp = ThreadLocalRandom.current().nextInt(2);
        if(temp==0){

            int temp2 = ThreadLocalRandom.current().nextInt(100,200);
            x=temp2;
            y=400;
            velY=ThreadLocalRandom.current().nextInt(1,8);
            velX=ThreadLocalRandom.current().nextInt(3,7);
        }else if(temp==1){
            int temp2 = ThreadLocalRandom.current().nextInt(600,800);
            x=temp2;
            y=300;
            velY=ThreadLocalRandom.current().nextInt(1,8);
            velX=ThreadLocalRandom.current().nextInt(1,7)*-1;

        }
        movement="Mosquito";
    }
    public void letterT(){

        int temp = ThreadLocalRandom.current().nextInt(2);
        if(temp==0){
            x=340;
            y=350;
            velX=6;
            velY=6;

        }else if(temp==1){
            x=890;
            y=350;
            velX=-6;
            velY=6;

        }

        movement="letterT";
    }
    public void teaCup(){

        int temp = ThreadLocalRandom.current().nextInt(3);
        if(temp==0){
            x=500;
            y=300;
            velX=0;
            velY=6;

        }else if(temp==1){
            x=750;
            y=300;
            velX=0;
            velY=6;

        }else if(temp==2){
            x=625;
            y=300;
            velX=0;
            velY=6;
        }


        movement="teaCup";
    }
    public void eggAttack(){
        int temp = ThreadLocalRandom.current().nextInt(4);
        if(temp==0){
            x=ThreadLocalRandom.current().nextInt(450,600);
            y=300;
            velX=0;
            velY=3;

        }else if(temp==1){
            x=100;
            y=ThreadLocalRandom.current().nextInt(400,800);
            velX=3;
            velY=0;

        }else if(temp==2){
            x=1200;
            y=ThreadLocalRandom.current().nextInt(400,800);;
            velX=-3;
            velY=0;
        }else if(temp==3){
            x=ThreadLocalRandom.current().nextInt(450,600);
            y=1000;
            velX=0;
            velY=-3;
        }

        movement="eggAttack";
    }
    public void eggAttack2(){
        int temp = ThreadLocalRandom.current().nextInt(3);
        if(temp==0){
            x=500;
            y=200;
            velX=0;
            velY=5;
        }else if(temp==1){
            x=600;
            y=200;
            velX=0;
            velY=5;

        }else if(temp==2){
            x=750;
            y=200;
            velX=0;
            velY=5;

        }


        movement="eggAttack2";
    }

    public void giraffe(){
        int temp = ThreadLocalRandom.current().nextInt(4);
        if(temp==0){
            x=300;
            y=ThreadLocalRandom.current().nextInt(538,750);
            velX=4;
            velY=0;
        }else if(temp==1){
            x=ThreadLocalRandom.current().nextInt(522,750);
            y=200;
            velY=4;
            velX=0;


        }else if(temp==2){
            x=900;
            y=ThreadLocalRandom.current().nextInt(538,750);
            velX=-4;
            velY=0;

        }else if(temp==3){
            x=ThreadLocalRandom.current().nextInt(522,750);
            y=1000;
            velY=-4;
            velX=0;

        }
        movement="giraffe";

    }

    public void guitar(){
        x=ThreadLocalRandom.current().nextInt(1000,1200);
        y=ThreadLocalRandom.current().nextInt(150,300);
        velX=ThreadLocalRandom.current().nextInt(2,6);
        velX*=-1;
        velY=ThreadLocalRandom.current().nextInt(3,5);


        movement="guitar";
    }

    public void frog(){
        x=ThreadLocalRandom.current().nextInt(522,800);
        y=300;
        velY=3;
        velX=3;
        if(ThreadLocalRandom.current().nextInt(2)==0)
            velX*=-1;
        movement="frog";
    }
    public void unicycle(){
        Textures tex = Game.getInstance();
        EnemyAttackItem temp = new EnemyAttackItem(600, 600, 107, 57, ID.EnemyAttackItem, tex.DatBoi_A1, 3, 57, 107, bPlayer, player, handler,battle);
        temp.movement="unicycle";
        if(ThreadLocalRandom.current().nextInt(2)==0){
            x=ThreadLocalRandom.current().nextInt(200,500);

            temp.setX(x+200);
            velY=5;
            temp.setVelY(5);
            y=300;
            temp.setY(300);
            handler.addObject(temp);

        }else{
            x=100;
            temp.setX(100);
            velX=5;
            temp.setVelX(5);
            y=ThreadLocalRandom.current().nextInt(250,800);
            temp.setY(y+200);
            handler.addObject(temp);
        }
        movement="unicycle";


    }
    public void lipton(){
        x=ThreadLocalRandom.current().nextInt(500,800);
        y=200;
        velY=1;
        movement="lipton";

    }
    public void lipton2(){
        y=ThreadLocalRandom.current().nextInt(100,300);
        x=ThreadLocalRandom.current().nextInt(400,600);
        velY=4;
        velX=4;
        if(ThreadLocalRandom.current().nextInt(2)==0){
            velX*=-1;
        }
        movement="lipton2";
    }
    public void bongo(){
        x=ThreadLocalRandom.current().nextInt(600,800);
        velX=ThreadLocalRandom.current().nextInt(1,2);
        if(ThreadLocalRandom.current().nextInt(2)==0)
            velX*=-1;
        velY=ThreadLocalRandom.current().nextInt(4,5);
        y=300;

        movement="bongo";

    }
    public void note(){
        int temp = ThreadLocalRandom.current().nextInt(2);
        if(temp==0){
            EnemyAttackItem newattack = new EnemyAttackItem(-100, 0, 152, 106, ID.EnemyAttackItem, tex.BongoCat_A2, 3, 106, 152, bPlayer, player, handler,battle);
            newattack.movement="note";
            x=1000;
            y=400;
            newattack.setX(1000);
            newattack.setY(800);
            velX=-7;
            newattack.setVelX(-7);
            handler.addObject(newattack);
        }else{
            x=1000;
            y=600;
            velX=-7;

        }
        movement="note";
    }
    public void crab(){
          x=900;
          velX=-4;
          y=ThreadLocalRandom.current().nextInt(550,700);
          movement="crab";

    }
    public void crabArm(){
        x=0;
        y=ThreadLocalRandom.current().nextInt(300,800);
        velX=2;
        velY=3;
        if(ThreadLocalRandom.current().nextInt(2)==0)
            velY*=-1;
        movement="crabArm";
    }

    public void magicBlast1(){
        movement="magicBlast1";
        int temp = ThreadLocalRandom.current().nextInt(2);
        if(temp==0){
            EnemyAttackItem tempAttack = new EnemyAttackItem(600, 600, 53, 67, ID.AntiHero, tex.AntiHero_A1, 3, 67, 53, bPlayer, player, handler,battle);
            x=150;
            y=300;
            tempAttack.setX(1200);
            tempAttack.setY(200);
            velX=8;
            velY=6;
            tempAttack.setVelX(-8);
            tempAttack.setVelY(6);
            tempAttack.movement="magicBlast1";
            handler.addObject(tempAttack);
        }else{
            EnemyAttackItem tempAttack = new EnemyAttackItem(600, 600, 53, 67, ID.AntiHero, tex.AntiHero_A1, 3, 67, 53, bPlayer, player, handler,battle);
            x=300;
            y=bPlayer.getY();
            tempAttack.setX(1000);
            tempAttack.setY(y);
            velX=8;
            tempAttack.setVelX(-8);
            tempAttack.movement="magicBlast1";

            handler.addObject(tempAttack);
        }
    }
    public void magicBlast2(){
        x=ThreadLocalRandom.current().nextInt(400,750);
        y=0;
        velY=ThreadLocalRandom.current().nextInt(6,10);
        movement="magicBlast2";

    }
    public void magicBlast3(){
        x=ThreadLocalRandom.current().nextInt(50,100);
        y=200;
        velX=ThreadLocalRandom.current().nextInt(5,7);
        velY=ThreadLocalRandom.current().nextInt(4,6);
        movement="magicBlast3";

    }
    public void banana(){
        x=1200;
        y=ThreadLocalRandom.current().nextInt(100,800);
        velX=-5;
        if(ThreadLocalRandom.current().nextInt(2)==0){
            velY=5;
        }else
            velY=-5;
        movement="banana";
    }
    public void rifle(){
        x=100;
        y=ThreadLocalRandom.current().nextInt(500,800);
        velX=1;
        movement="rifle";

    }

    public void lizard(){
        x=ThreadLocalRandom.current().nextInt(400,800);
        y=0;
        velY=4;
        way=2;
        velX=ThreadLocalRandom.current().nextInt(1,3);
        if(ThreadLocalRandom.current().nextInt(2)==0){
            velX*=-1;
        }
        movement="lizard";

    }
    public void facebook(){

         x=2000;
         y=100;
         velX=-10;
         velY=0;

         int temp =ThreadLocalRandom.current().nextInt(3);

         if(temp==0)
             movement="facebook";
         else if(temp==1)
             movement="facebook2";
         else if(temp==2)
             movement="facebook3";

         original=true;

    }

    public void kazoo1(){
        x= 100;
        y=500;
        velX=5;
        velY=ThreadLocalRandom.current().nextInt(3,6);
        if(ThreadLocalRandom.current().nextInt(2)==0)
            velY*=-1;
        movement="kazoo1";
    }
    public void kazoo2(){
        x=ThreadLocalRandom.current().nextInt(450,700);
        y=400;
        velY=5;
        velX=ThreadLocalRandom.current().nextInt(3,6);
        if(ThreadLocalRandom.current().nextInt(2)==0)
            velX*=-1;

        movement="kazoo2";
    }
    public void boot(){
        x=1200;
        y=0;
        velX=ThreadLocalRandom.current().nextInt(4,7);
        velX*=-1;
        velY=ThreadLocalRandom.current().nextInt(3,10);


        movement="boot";
    }
    public void yodelnote(){

        x=100;
        y=ThreadLocalRandom.current().nextInt(500,800);
        velX=ThreadLocalRandom.current().nextInt(3,10);
        velY=ThreadLocalRandom.current().nextInt(1,3);


        movement="yodelnote";
    }
    public void krabbypatty(){
        x=ThreadLocalRandom.current().nextInt(400,700);
        y=10;
        velX=ThreadLocalRandom.current().nextInt(1,3);
        velY=ThreadLocalRandom.current().nextInt(5,8);


        movement="krabbypatty";
    }
    public void net(){
        x= 1200;
        y=ThreadLocalRandom.current().nextInt(500,600);
        velX=ThreadLocalRandom.current().nextInt(4,9);
        velX*=-1;
        velY=ThreadLocalRandom.current().nextInt(2,4);
        if(ThreadLocalRandom.current().nextInt(2)==0){
            velY*=-1;
        }
        movement="net";

    }
    public void L(){
        x=1200;
        y=ThreadLocalRandom.current().nextInt(400,900);
        velX=-4;
        velY=0;

        movement="L";

    }
    public void racket(){
        x=ThreadLocalRandom.current().nextInt(500,800);
        y=0;
        velY=4;
        velX=0;

        movement="racket";
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
                int damage=Math.max(5,battle.getEnemy().getAttack()-player.getDefense());
                // add real damage calculation later based on attack/defense
                if(!player.getBackwards()){

                    player.setHealth(player.getHealth()-damage);

                }else{
                    battle.getEnemy().setHealth(battle.getEnemy().getHealth()-damage);
                }

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
