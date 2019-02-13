import java.awt.*;

public class Block extends GameObject{

    Handler handler;
    Game game;
    Player player;


    Textures tex = Game.getInstance();


    public Block(float x, float y, float height, float width, Handler handler, Game game, ID id){
        super(x, y, height, width, id);
        this.handler = handler;
        this.game = game;
        this.player = player;
        this.isVisible = true;


        //walkLeft = new Animation(speed, tex.Player_WalkLeft[0], tex.Player_WalkLeft[1]);

    }


    public void tick(){

    }

    public void render(Graphics g){
        if(id == ID.GrayGround){
            g.setColor(Color.gray);
            g.fillRect((int)x, (int)y, (int)width, (int)height);
        }else if(id == ID.BlackGround){
            g.setColor(Color.black);
            g.fillRect((int)x, (int)y, (int)width, (int)height);
        }




    }
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }







}
