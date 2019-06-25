import java.awt.Graphics;
import java.awt.Rectangle;
public class Transition extends GameObject {

    private int newPlayerX;
    private int newPlayerY;
    private boolean dangerZone;
    private String destination;
    public Transition(float x, float y, float height, float width, ID id, String destination, int newPlayerX, int newPlayerY,Player player,boolean dangerZone){
        super(x, y, height, width, id);
        this.destination=destination;
        this.newPlayerX = newPlayerX;
        this.newPlayerY=newPlayerY;
        this.dangerZone=dangerZone;

    }


    public void tick(){

    }
    public void render(Graphics g){

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    public String getDestination(){
        return this.destination;
    }


    public int getNewPlayerX() {
        return newPlayerX;
    }

    public void setNewPlayerX(int newPlayerX) {
        this.newPlayerX = newPlayerX;
    }

    public int getNewPlayerY() {
        return newPlayerY;
    }

    public void setNewPlayerY(int newPlayerY) {
        this.newPlayerY = newPlayerY;
    }

    public boolean getDangerZone() {
        return dangerZone;
    }

    public void setDangerZone(boolean dangerZone) {
        this.dangerZone = dangerZone;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
