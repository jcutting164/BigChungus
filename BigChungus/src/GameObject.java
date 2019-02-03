
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected float x, y, height, width;
    protected float velX, velY;
    protected ID id;
    protected boolean isVisible;

    public GameObject(float x, float y, float height, float width, ID id) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.id = id;
        this.isVisible = true;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }


}
