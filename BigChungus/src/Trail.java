

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

public class Trail extends GameObject implements Serializable {


    private float alpha = 1;
    private Handler handler;
    private Color color;

    private float width;
    private float life;

    // life = 0.001 - 0.1

    public Trail(float x, float y, ID id, Color color, float width, float height, float life, Handler handler) {
        super(x, y, 1, 1, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public void tick() {
        if(alpha > life) {
            alpha -= (life - 0.0008f);
        }else handler.removeObject(this);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int) x, (int) y, (int) width, (int) width);

        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        float type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance((int) type, alpha));
    }

    public Rectangle getBounds() {
        return null;
    }

}
