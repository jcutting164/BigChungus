

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

public class Spawn implements Serializable {

    private Handler handler;
    private float scoreKeep = 0;
    private long timeNow, time, timeOfLastSpawn;
    private int startTime = 1000;
    private GameObject temp;
    private Random r;
    private Game game;

    public Spawn(Handler handler, Game game) {
        this.handler = handler;
        this.timeOfLastSpawn = 0;
        this.r = new Random();
        this.game=game;
    }


    public void tick() {
        scoreKeep = game.getScore();

        if(scoreKeep >= 300 * (game.getLevel()*1.5)) {
            scoreKeep = 0;
            game.setLevel(game.getLevel() + 1);
            handler.clearEnemys();
        }


        if(game.getLevel() == 5 || game.getLevel() == 10 || game.getLevel() ==  15 || game.getLevel() ==   20) {
            temp = new EnemyBoss(15, 15, 16, 16, ID.EnemyBoss, handler, Color.red,game);
            timeNow = System.currentTimeMillis();
            time = timeNow - timeOfLastSpawn;
            if(time > startTime) {
                handler.addObject(temp);
                timeOfLastSpawn = timeNow;

            }
        }else {
            // handler function to detect if enemies exist
            try{
                if(handler.detectEnemies() == false) {
                    for(int i =0; i<game.getLevel(); i++) {
                        if(handler.getEnemyAMT() % 2 == 0) {
                            handler.addObject(new BasicEnemy(r.nextInt((1240 - 40) + 1) + 40, r.nextInt((200 - 50) + 1) + 50, 16, 16, ID.BasicEnemy, handler, Color.green, game));
                        }else {
                            handler.addObject(new CoolEnemy(r.nextInt((1240 - 40) + 1) + 40, r.nextInt((200 - 50) + 1) + 50, 16, 16, ID.BasicEnemy, handler, Color.green,game));
                        }

                    }
                }
            }catch (Exception e){
                handler.addObject(new BasicEnemy(r.nextInt((1240 - 40) + 1) + 40, r.nextInt((200 - 50) + 1) + 50, 16, 16, ID.BasicEnemy, handler, Color.green, game));
            }



        }


    }

}
