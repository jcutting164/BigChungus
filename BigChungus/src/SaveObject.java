import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class SaveObject extends GameObject implements Serializable {

    BufferedImage saveimage;
    private Game game;

    public SaveObject(float x, float y, float height, float width,Game game){
        super(x, y, height, width, ID.SaveIcon);
        Textures tex = Game.getInstance();
        saveimage= tex.SaveIcon[0];
        this.game = game;
    }

    public void tick() {

    }

    public void render(Graphics g){
        g.drawImage(saveimage, (int)x, (int)y, (int)width, (int)height, null);

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }


    public void interaction(){
        game.save();
    }





}
