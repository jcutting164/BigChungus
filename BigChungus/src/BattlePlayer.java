
import java.awt.*;
import java.io.Serializable;


public class BattlePlayer extends GameObject implements Serializable {
    private Game game;
    private boolean cooldown;
    private long time, timeNow, timeOfLastShot;
    private int track;
    private Player player;
    private AudioPlayer ap;


    public BattlePlayer(float x, float y, float height, float width, ID id, Player player, Game game, AudioPlayer ap){
        super(x, y, height, width, id);
        this.velX = 0;
        this.velY = 0;
        this.isVisible=true;
        this.cooldown=false;
        this.timeOfLastShot=0;
        this.track=0;
        this.player =player;
        this.game = game;
        this.ap = ap;

    }



    public void tick(){
    /*	if(player.getHealth() <= 0){
    		game.setCurrentState(Game.STATE.GameOver);
    		game.setSwitch(true);
    		//ap.load();
    		ap.getMusic("Knuckles").stop();
            ap.getMusic("Pikachu").stop();
            ap.getMusic("BigChungus").stop();
            ap.getSound("GameOver").play();


    	}*/

        x+=velX;
        y+=velY;

        x = Game.clamp(x, 512, 752);
        y = Game.clamp(y, 528, 768);


        if(cooldown){
            timeNow = System.currentTimeMillis();
            time = timeNow - timeOfLastShot;
            if(time > 100) {
                if(isVisible){
                    isVisible=false;
                    timeOfLastShot = timeNow;
                    track++;
                }else{
                    isVisible=true;
                    timeOfLastShot = timeNow;
                    track++;
                }
            }
            if(track==14){
                track = 0;
                timeOfLastShot = 0;
                cooldown=false;
                isVisible=true;
            }
        }



    }
    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void collision(){

    }

    public boolean getCooldown(){
        return this.cooldown;
    }
    public void setCooldown(boolean cooldown){
        this.cooldown = cooldown;
    }
    public long getTimeOfLastShot() {
        return timeOfLastShot;
    }

    public void setTimeOfLastShot(long timeOfLastShot) {
        this.timeOfLastShot = timeOfLastShot;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public Rectangle getSpecialBounds(){
        Rectangle temp = getBounds();
        temp.setBounds((int)temp.getX()-100, (int)temp.getY()-100, (int)temp.getWidth()+200, (int)temp.getHeight()+200);
        return temp;
    }


}
