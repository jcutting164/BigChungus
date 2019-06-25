import java.io.Serializable;

public class Camera implements Serializable {

    private float x, y;
    private int rangeX, rangeY;
    private Game game;


    public Camera(float x, float y, Game game){
        this.x = x;
        this.y = y;
        this.game=game;
    }

    public void tick(GameObject object){
        System.out.println(rangeX);
        x+= ((object.getX() - x) - 1280/2 );
        y+= ((object.getY() - y) - 960/2 );
        System.out.println(x+" "+y);

        if(x <= 0) x=0;
        if( x >= rangeX) x = rangeX;
        if(y <= 0) y=0;
        if(y >= rangeY) y =rangeY;

    }

    public void setX(float x){
        this.x = x;
    }
    public float getX(){
        return x;
    }
    public void setY(float y){
        this.y = y;
    }
    public float getY(){
        return y;
    }

    public int getRangeX() {
        return rangeX;
    }

    public void setRangeX(int rangeX) {
        this.rangeX = rangeX;
    }

    public int getRangeY() {
        return rangeY;
    }

    public void setRangeY(int rangeY) {
        this.rangeY = rangeY;
    }
    public void updateRange(String temp){
        rangeX=game.getRanges().get(temp).get(0);
        rangeY=game.getRanges().get(temp).get(1);
    }
}
