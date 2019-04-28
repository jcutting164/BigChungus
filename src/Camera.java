import java.io.Serializable;

public class Camera implements Serializable {

    private float x, y;

    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object){
        x+= ((object.getX() - x) - 1280/2 )* 0.05f;
        y+= ((object.getY() - y) - 960/2 )* 0.05f;

        if(x <= 0) x=0;
        if( x >= 1270) x = 1270;
        if(y <= 0) y=0;
        if(y >= 970) y =970;

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


}
