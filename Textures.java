import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

public class Textures {

    SpriteSheet SS_Player;
    SpriteSheet SS_FirstArea;

    private BufferedImage BI_Player;
    public BufferedImage BI_FirstArea;

    public BufferedImage[] Player_WalkLeft = new BufferedImage[2];
    public BufferedImage[] Player_WalkRight = new BufferedImage[2];
    public BufferedImage[] Player_WalkUp = new BufferedImage[3];
    public BufferedImage[] Player_WalkDown = new BufferedImage[3];
    public BufferedImage[] Player_Face = new BufferedImage[1];


    public Textures(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            BI_Player = loader.loadImage("res/SS_Player.png");


            BI_FirstArea = loader.loadImage("res/FirstArea.png");





        }catch(Exception e){
            e.printStackTrace();
        }

        SS_Player = new SpriteSheet(BI_Player);
        SS_FirstArea = new SpriteSheet(BI_FirstArea);

        getTextures();

    }

    private void getTextures(){
        Player_WalkLeft[0] = SS_Player.grabImage(3, 1, 19, 74);
        Player_WalkLeft[1] = SS_Player.grabImage(5, 1, 19, 74);

        Player_WalkRight[0] = SS_Player.grabImage(4, 1, 19, 74);
        Player_WalkRight[1] = SS_Player.grabImage(2, 1, 19, 74);

        Player_WalkUp[0] = SS_Player.grabImage(1, 2, 19, 74);
        Player_WalkUp[1] = SS_Player.grabImage(2, 2, 19, 74);
        Player_WalkUp[2] = SS_Player.grabImage(3, 2, 19, 74);


        Player_WalkDown[0] = SS_Player.grabImage(4, 2, 19, 74);
        Player_WalkDown[1] = SS_Player.grabImage(1, 1, 19, 74);
        Player_WalkDown[2] = SS_Player.grabImage(6, 1, 19, 74);
        Player_Face[0] = SS_Player.getSubImage(0, 148, 128, 128);



    }






}
