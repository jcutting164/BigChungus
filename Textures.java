import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

public class Textures {

    SpriteSheet SS_Player;
    SpriteSheet SS_FirstArea;
    SpriteSheet SS_Knuckles;

    private BufferedImage BI_Player;
    public BufferedImage BI_FirstArea;
    private BufferedImage BI_Knuckles;

    public BufferedImage[] Player_WalkLeft = new BufferedImage[2];
    public BufferedImage[] Player_WalkRight = new BufferedImage[2];
    public BufferedImage[] Player_WalkUp = new BufferedImage[3];
    public BufferedImage[] Player_WalkDown = new BufferedImage[3];
    public BufferedImage[] Player_Face = new BufferedImage[1];


    public BufferedImage[] Knuckles_WalkLeft = new BufferedImage[8];
    public BufferedImage[] Knuckles_WalkRight = new BufferedImage[8];
    public BufferedImage Knuckles_Face;
    public BufferedImage Knuckles_BattleForm;


    public Textures(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            BI_Player = loader.loadImage("res/SS_Player.png");


            BI_FirstArea = loader.loadImage("res/FirstArea.png");


            BI_Knuckles = loader.loadImage("res/SS_Knuckles.png");





        }catch(Exception e){
            e.printStackTrace();
        }

        SS_Player = new SpriteSheet(BI_Player);
        SS_FirstArea = new SpriteSheet(BI_FirstArea);
        SS_Knuckles = new SpriteSheet(BI_Knuckles);

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



        Knuckles_WalkLeft[0] = SS_Knuckles.getSubImage(178+128+161,0,17, 23);
        Knuckles_WalkLeft[1] = SS_Knuckles.getSubImage(178+128+139, 0,19, 23);
        Knuckles_WalkLeft[2] = SS_Knuckles.getSubImage(178+128+115, 0, 23, 24);
        Knuckles_WalkLeft[3] = SS_Knuckles.getSubImage(178+128+92, 0, 21, 24);
        Knuckles_WalkLeft[4] = SS_Knuckles.getSubImage(178+128+71, 0, 17, 24);
        Knuckles_WalkLeft[5] = SS_Knuckles.getSubImage(178+128+51, 0, 17, 24);
        Knuckles_WalkLeft[6] = SS_Knuckles.getSubImage(178+128+23, 0, 24, 24);
        Knuckles_WalkLeft[7] = SS_Knuckles.getSubImage(178+128+0, 0,21, 24);

        Knuckles_WalkRight[0] = SS_Knuckles.getSubImage(128+0, 0, 17, 23);
        Knuckles_WalkRight[1] = SS_Knuckles.getSubImage(128+23, 0, 19, 23);
        Knuckles_WalkRight[2] = SS_Knuckles.getSubImage(128+51, 0, 23, 24);
        Knuckles_WalkRight[3] = SS_Knuckles.getSubImage(128+71, 0, 21, 24);
        Knuckles_WalkRight[4] = SS_Knuckles.getSubImage(128+92, 0, 17, 24);
        Knuckles_WalkRight[5] = SS_Knuckles.getSubImage(128+115, 0, 17, 24);
        Knuckles_WalkRight[6] = SS_Knuckles.getSubImage(128+139, 0, 24, 24);
        Knuckles_WalkRight[7] = SS_Knuckles.getSubImage(128+161, 0, 21, 24);

        Knuckles_Face = SS_Knuckles.grabImage(1, 1, 128, 128);
        Knuckles_BattleForm = SS_Knuckles.getSubImage(0, 128, 780, 610);





    }






}
