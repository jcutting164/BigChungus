import java.awt.Graphics;

public class M_Knuckles_1 extends Usables_E {

    private long timeNow2;
    private long time2;
    private long timeOfLastShot2;
    Textures tex = Game.getInstance();
    private BattlePlayer bPlayer;
    private Player player;

    public M_Knuckles_1(float x, float y, float height, float width, ID id, Handler handler, Battle battle, Enemy enemy, BattlePlayer bPlayer, Player player){
        super(x, y, height, width, id, handler, battle, enemy);
        this.bPlayer=bPlayer;
        this.player = player;


    }


    public void tick(){

    }

    public void render(Graphics g){


    }

    public void use(){
        track = 0;
        timeOfLastShot=System.currentTimeMillis();
        timeOfLastShot2=System.currentTimeMillis();
        // double timing system to stop and start movea
        // add

        // process:
        // time to add new objects / how many
        // time to stop actual turn
        // time to tell when objects to start moving





    }

    public void effect(){
        timeNow = System.currentTimeMillis();
        time = timeNow - timeOfLastShot;

        if(time > 10000) {
            timeOfLastShot=0;
            end();
        }else{
            timeNow2 = System.currentTimeMillis();
            time2 = timeNow2 - timeOfLastShot2;
            if(time2>2000 && track < 4){
                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Knuckles_A1, 3, 18, 18, bPlayer, player, handler);
                temp.boxBounce();
                handler.addObject(temp);
                timeOfLastShot2=timeNow2;
                track++;
            }
        }


    }

    public void end(){
        handler.removeObject(this);
        // will update damage calculation based on attack / defense, for now is one value
        battle.setPlayerTurn(true);
        // will do same for player when enemy uses this function for end
        battle.setPlayerTurnStart(true);

    }


}
