import java.awt.Graphics;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class S_BasicAttack extends Usables implements Serializable {

    public S_BasicAttack(float x, float y, float height, float width, ID id, Handler handler, Battle battle, Enemy enemy){
        super(x, y, height, width, id, handler, battle, enemy);
        this.name = "Basic Attack";
        this.desc = "Not really even a spell, does basic damage and can help you stall so you can make a small amount of mana";
        this.velY=-3;


    }


    public void use(){
        handler.addObject(this);
        track = 0;
        battle.setEffectTime(true);
        battle.setFirstTimeInTurn(false);



    }

    public void effect(){
        timeNow = System.currentTimeMillis();
        time = timeNow - timeOfLastShot;
        if(time > 100) {
            if(battle.getEnemyVisable()){
                battle.setEnemyVisable(false);
                timeOfLastShot = timeNow;
                track++;
            }else{
                battle.setEnemyVisable(true);
                timeOfLastShot = timeNow;
                track++;
            }
        }
        if(track==7){
            battle.setEffectTime(false);
            track = 0;
            timeOfLastShot = 0;
        }


    }
    public void end(){

        handler.removeObject(this);
        // will update damage calculation based on attack / defense, for now is one value

        int attackPower=0;
        if(battle.getPlayer().getWeapon()!=null)
            attackPower= battle.getPlayer().getAttack() + battle.getPlayer().getWeapon().getDamageFactor();
        else
            attackPower=battle.getPlayer().getAttack();
        int damage = ThreadLocalRandom.current().nextInt(attackPower,(int)(attackPower*1.5+1));
        if(!(battle.getPlayer().getBackwards())){
            enemy.setHealth(enemy.getHealth() - damage);
        }else{
            battle.getPlayer().setHealth(battle.getPlayer().getHealth()-damage);
        }

        battle.setPlayerTurn(false);
        // will do same for player when enemy uses this function for end
        battle.setEnemyTurnStart(true);




    }

    public void tick(){

        y+=velY;


    }
    public void render(Graphics g){

    }



}
