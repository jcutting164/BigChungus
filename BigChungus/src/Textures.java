

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_REDPeer;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;


public class Textures implements Serializable {

    SpriteSheet SS_Player;
    SpriteSheet SS_FirstArea;
    SpriteSheet SS_Knuckles;
    SpriteSheet SSA_Knuckles_1;
    SpriteSheet SSA_Knuckles_2;
    SpriteSheet SS_PikachuMovement;
    SpriteSheet SS_PikachuBF;
    SpriteSheet SS_PikachuFace;
    SpriteSheet SSA_Pikachu_1;
    SpriteSheet SSA_Pikachu_2;
    SpriteSheet SSA_Pikachu_3;
    SpriteSheet SSA_Pikachu_4;
    SpriteSheet SS_BigChungus;
    SpriteSheet SSA_BigChungus_1;
    SpriteSheet SSA_BigChungus_2;
    SpriteSheet SS_Save_Icon;
    SpriteSheet SS_Pavement;
    SpriteSheet SS_Room1_1;
    SpriteSheet SS_Room2_1;
    SpriteSheet SS_Room2_1O;
    SpriteSheet SS_Room2_2;
    SpriteSheet SS_Room2_3;
    SpriteSheet SS_People;
    SpriteSheet SS_Forest;

    SpriteSheet SS_ExtraItems;
    SpriteSheet SS_Malario;
    SpriteSheet SSA_Malario;
    SpriteSheet SS_Tposer;




    private transient BufferedImage BI_Player;
    public transient BufferedImage BI_FirstArea;
    private transient BufferedImage BI_Knuckles;
    private transient BufferedImage BI_A_Knuckles_1;
    private transient BufferedImage BI_A_Knuckles_2;
    private transient BufferedImage BI_PikachuMovement;
    private transient BufferedImage BI_PikachuBF;
    private transient BufferedImage BI_PikachuFace;
    private transient BufferedImage BI_A_Pikachu_1;
    private transient BufferedImage BI_A_Pikachu_2;
    private transient BufferedImage BI_A_Pikachu_3;
    private transient BufferedImage BI_A_Pikachu_4;
    private transient BufferedImage BI_BigChungus;
    private transient BufferedImage BI_A_BigChungus_1;
    private transient BufferedImage BI_A_BigChungus_2;
    private transient BufferedImage BI_Save_Icon;
    private transient BufferedImage BI_Pavement;
    private transient BufferedImage BI_Room1_1;
    private transient BufferedImage BI_People;
    private transient BufferedImage BI_Forest;
    private transient BufferedImage BI_Room2_1;
    private transient BufferedImage BI_Room2_1O;
    private transient BufferedImage BI_Room2_2;
    private transient BufferedImage BI_Room2_3;
    private transient BufferedImage BI_ExtraItems;
    private transient BufferedImage BI_Malario;
    private transient BufferedImage BI_A_Malario;
    private transient BufferedImage BI_Tposer;



    private transient InputStream IS_BI_Player;
    private transient InputStream IS_BI_FirstArea;
    private transient InputStream IS_BI_Knuckles;
    private transient InputStream IS_A_Knuckles_1;
    private transient InputStream IS_A_Knuckles_2;
    private transient InputStream IS_BI_PikachuMovement;
    private transient InputStream IS_BI_PikachuBF;
    private transient InputStream IS_BI_PikachuFace;
    private transient InputStream IS_A_Pikachu_1;
    private transient InputStream IS_A_Pikachu_2;
    private transient InputStream IS_A_Pikachu_3;
    private transient InputStream IS_A_Pikachu_4;
    private transient InputStream IS_BI_BigChungus;
    private transient InputStream IS_A_BigChungus_1;
    private transient InputStream IS_A_BigChungus_2;
    private transient InputStream IS_Save_Icon;
    private transient InputStream IS_Pavement;
    private transient InputStream IS_Room1_1;
    private transient InputStream IS_People;
    private transient InputStream IS_Forest;


    private transient InputStream IS_Room2_1;
    private transient InputStream IS_Room2_1O;
    private transient InputStream IS_Room2_2;
    private transient InputStream IS_Room2_3;

    private transient InputStream IS_ExtraItems;
    private transient InputStream IS_Malario;
    private transient InputStream IS_A_Malario;
    private transient InputStream IS_Tposer;


    public transient BufferedImage[] Player_WalkLeft = new BufferedImage[2];
    public transient BufferedImage[] Player_WalkRight = new BufferedImage[2];
    public transient BufferedImage[] Player_WalkUp = new BufferedImage[3];
    public transient BufferedImage[] Player_WalkDown = new BufferedImage[3];
    public transient BufferedImage[] Player_Face = new BufferedImage[1];



    public transient BufferedImage[] Knuckles_WalkLeft = new BufferedImage[8];
    public transient BufferedImage[] Knuckles_WalkRight = new BufferedImage[8];
    public transient BufferedImage Knuckles_Face;
    public transient BufferedImage Knuckles_BattleForm;
    public transient BufferedImage[] Knuckles_A1 = new BufferedImage[4];
    public transient BufferedImage[] Knuckles_A2 = new BufferedImage[1];


    public transient BufferedImage[] Pikachu_WalkLeft = new BufferedImage[3];
    public transient BufferedImage[] Pikachu_WalkRight = new BufferedImage[3];
    public transient BufferedImage[] Pikachu_WalkUp = new BufferedImage[3];
    public transient BufferedImage[] Pikachu_WalkDown = new BufferedImage[3];
    public transient BufferedImage[] PikachuBF = new BufferedImage[1];
    public transient BufferedImage[] PikachuFace = new BufferedImage[1];
    public transient BufferedImage[] Pikachu_A1 = new BufferedImage[2];
    public transient BufferedImage[] Pikachu_A1S = new BufferedImage[1];
    public transient BufferedImage[] Pikachu_A2 = new BufferedImage[1];
    public transient BufferedImage[] Pikachu_A3 = new BufferedImage[1];

    public transient BufferedImage[] BigChungus_WalkRight = new BufferedImage[3];
    public transient BufferedImage[] BigChungus_WalkLeft = new BufferedImage[3];
    public transient BufferedImage[] BigChungusFace = new BufferedImage[1];
    public transient BufferedImage[] BigChungusBF = new BufferedImage[1];
    public transient BufferedImage[] BigChungus_A1 = new BufferedImage[1];
    public transient BufferedImage[] BigChungus_A2 = new BufferedImage[4];
    public transient BufferedImage[] BigChungus_A3 = new BufferedImage[2];
    public transient BufferedImage[] BigChungus_A4 = new BufferedImage[4];

    public transient BufferedImage[] SaveIcon = new BufferedImage[12];

    public transient BufferedImage[] Pavement = new BufferedImage[1];

    public transient BufferedImage Room1_1;
    public transient BufferedImage Room2_1;
    public transient BufferedImage Room2_1O;
    public transient BufferedImage Room2_2;
    public transient BufferedImage Room2_2O;
    public transient BufferedImage Room2_3;
    public transient BufferedImage Room2_3O;

    public transient BufferedImage TheLastEntity;
    public transient BufferedImage TheLastEntityFace;
    public transient BufferedImage Book;

    public transient BufferedImage Grass;
    public transient BufferedImage Tree;
    public transient BufferedImage Orb;

    public transient BufferedImage MalarioBF;
    public transient BufferedImage[] Malario_A1=new BufferedImage[1];
    public transient BufferedImage[] Malario_A2=new BufferedImage[1];

    public transient BufferedImage TposerBF;
    public transient BufferedImage[] Tposer_A1=new BufferedImage[1];
    public transient BufferedImage[] Tposer_A2=new BufferedImage[1];

    public Textures(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            //BI_Player = loader.loadImage("res/SS_Player.png");

            IS_BI_Player = new FileInputStream("res/Player/SS_Player.png");
            BI_Player = ImageIO.read(IS_BI_Player);

            IS_BI_FirstArea = new FileInputStream("res/FirstArea.png");
            BI_FirstArea = ImageIO.read(IS_BI_FirstArea);

            IS_BI_Knuckles = new FileInputStream("res/Knuckles/SS_Knuckles.png");
            BI_Knuckles = ImageIO.read(IS_BI_Knuckles);

            IS_A_Knuckles_1 = new FileInputStream("res/Attacks/EnemyAttacks/Knuckles/A_Knuckles_1.png");
            BI_A_Knuckles_1 = ImageIO.read(IS_A_Knuckles_1);

            IS_A_Knuckles_2 = new FileInputStream("res/Attacks/EnemyAttacks/Knuckles/A_Knuckles_2.png");
            BI_A_Knuckles_2 = ImageIO.read(IS_A_Knuckles_2);


            IS_BI_PikachuFace = new FileInputStream("res/Pikachu/SS_PikachuFace.png");
            BI_PikachuFace = ImageIO.read(IS_BI_PikachuFace);

            IS_BI_PikachuBF = new FileInputStream("res/Pikachu/SS_PikachuBF.png");
            BI_PikachuBF = ImageIO.read(IS_BI_PikachuBF);

            IS_BI_PikachuMovement = new FileInputStream("res/Pikachu/SS_PikachuMovement.png");
            BI_PikachuMovement = ImageIO.read(IS_BI_PikachuMovement);

            IS_A_Pikachu_1 = new FileInputStream("res/Attacks/EnemyAttacks/Pikachu/A_Pikachu_1.png");
            BI_A_Pikachu_1 = ImageIO.read(IS_A_Pikachu_1);

            IS_A_Pikachu_2 = new FileInputStream("res/Attacks/EnemyAttacks/Pikachu/A_Pikachu_2.png");
            BI_A_Pikachu_2 = ImageIO.read(IS_A_Pikachu_2);

            IS_A_Pikachu_3 = new FileInputStream("res/Attacks/EnemyAttacks/Pikachu/A_Pikachu_3.png");
            BI_A_Pikachu_3 = ImageIO.read(IS_A_Pikachu_3);

            IS_A_Pikachu_4 = new FileInputStream("res/Attacks/EnemyAttacks/Pikachu/A_Pikachu_4.png");
            BI_A_Pikachu_4 = ImageIO.read(IS_A_Pikachu_4);

            IS_BI_BigChungus = new FileInputStream("res/BigChungus/SS_BigChungus2.png");
            BI_BigChungus = ImageIO.read(IS_BI_BigChungus);

            IS_A_BigChungus_1 = new FileInputStream("res/Attacks/EnemyAttacks/BigChungus/carrot.png");
            BI_A_BigChungus_1 = ImageIO.read(IS_A_BigChungus_1);

            IS_A_BigChungus_2 = new FileInputStream("res/Attacks/EnemyAttacks/BigChungus/BCattacks.png");
            BI_A_BigChungus_2 = ImageIO.read(IS_A_BigChungus_2);

            IS_Save_Icon = new FileInputStream("res/SaveIcon/SaveIconF.png");
            BI_Save_Icon = ImageIO.read(IS_Save_Icon);

            IS_Pavement = new FileInputStream("res/Rooms/BrickPavement.jpg");
            BI_Pavement = ImageIO.read(IS_Pavement);


            IS_Room1_1 = new FileInputStream("res/Rooms/Room1_1.png");
            BI_Room1_1 = ImageIO.read(IS_Room1_1);

            IS_People = new FileInputStream("res/PeopleSprites.png");
            BI_People = ImageIO.read(IS_People);

            IS_Forest = new FileInputStream("res/Forest.png");
            BI_Forest= ImageIO.read(IS_Forest);

            IS_Room2_1 = new FileInputStream("res/Rooms/Room2_1.png");
            BI_Room2_1 = ImageIO.read(IS_Room2_1);

            IS_Room2_1O = new FileInputStream("res/Rooms/Room2_1O.png");
            BI_Room2_1O = ImageIO.read(IS_Room2_1O);

            IS_ExtraItems = new FileInputStream("res/Orb.png");
            BI_ExtraItems = ImageIO.read(IS_ExtraItems);

            IS_Malario = new FileInputStream("res/Malario/Malario.png");
            BI_Malario=ImageIO.read(IS_Malario);

            IS_A_Malario = new FileInputStream("res/Attacks/EnemyAttacks/Malario/MalarioAttacks.png");
            BI_A_Malario = ImageIO.read(IS_A_Malario);

            IS_Tposer=new FileInputStream("res/Tposer/TPoser.png");
            BI_Tposer=ImageIO.read(IS_Tposer);

            IS_Room2_2=new FileInputStream("res/Rooms/Room2_2.png");
            BI_Room2_2=ImageIO.read(IS_Room2_2);

            IS_Room2_3=new FileInputStream("res/Rooms/Room2_3.png");
            BI_Room2_3=ImageIO.read(IS_Room2_3);

        }catch(Exception e){
            e.printStackTrace();
        }

        SS_Player = new SpriteSheet(BI_Player);
        SS_FirstArea = new SpriteSheet(BI_FirstArea);
        SS_Knuckles = new SpriteSheet(BI_Knuckles);
        SSA_Knuckles_1 = new SpriteSheet(BI_A_Knuckles_1);
        SSA_Knuckles_2 = new SpriteSheet(BI_A_Knuckles_2);
        SS_PikachuMovement = new SpriteSheet(BI_PikachuMovement);
        SS_PikachuFace = new SpriteSheet(BI_PikachuFace);
        SS_PikachuBF = new SpriteSheet(BI_PikachuBF);
        SSA_Pikachu_1 = new SpriteSheet(BI_A_Pikachu_1);
        SSA_Pikachu_2 = new SpriteSheet(BI_A_Pikachu_2);
        SSA_Pikachu_3 = new SpriteSheet(BI_A_Pikachu_3);
        SSA_Pikachu_4 = new SpriteSheet(BI_A_Pikachu_4);
        SS_BigChungus = new SpriteSheet(BI_BigChungus);
        SSA_BigChungus_1 = new SpriteSheet(BI_A_BigChungus_1);
        SSA_BigChungus_2 = new SpriteSheet(BI_A_BigChungus_2);
        SS_Save_Icon = new SpriteSheet(BI_Save_Icon);
        SS_Pavement = new SpriteSheet(BI_Pavement);
        SS_Room1_1 = new SpriteSheet(BI_Room1_1);
        SS_People = new SpriteSheet(BI_People);
        SS_Forest = new SpriteSheet(BI_Forest);
        SS_Room2_1 = new SpriteSheet(BI_Room2_1);
        SS_Room2_1O = new SpriteSheet(BI_Room2_1O);
        SS_ExtraItems = new SpriteSheet(BI_ExtraItems);
        SS_Malario=new SpriteSheet(BI_Malario);
        SSA_Malario=new SpriteSheet(BI_A_Malario);
        SS_Tposer=new SpriteSheet(BI_Tposer);
        SS_Room2_2=new SpriteSheet(BI_Room2_2);
        SS_Room2_3=new SpriteSheet(BI_Room2_3);
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

        Knuckles_A1[0] = SSA_Knuckles_1.grabImage(1, 1, 18, 18);
        Knuckles_A1[1] = SSA_Knuckles_1.grabImage(2, 1, 18, 18);
        Knuckles_A1[2] = SSA_Knuckles_1.grabImage(3, 1, 18, 18);
        Knuckles_A1[3] = SSA_Knuckles_1.grabImage(4, 1, 18, 18);

        Knuckles_A2[0] = SSA_Knuckles_2.grabImage(1, 1, 105, 34);



        Pikachu_WalkDown[1] = SS_PikachuMovement.getSubImage(0, 0, 19, 24);
        Pikachu_WalkDown[0] = SS_PikachuMovement.getSubImage(37, 0, 19, 25);
        Pikachu_WalkDown[2] = SS_PikachuMovement.getSubImage(71, 0, 20, 26);

        Pikachu_WalkUp[1] = SS_PikachuMovement.getSubImage(1, 32, 23, 25);
        Pikachu_WalkUp[0] = SS_PikachuMovement.getSubImage(32, 30, 21, 24);
        Pikachu_WalkUp[2] = SS_PikachuMovement.getSubImage(70, 30, 20, 26);

        Pikachu_WalkLeft[1] = SS_PikachuMovement.getSubImage(0, 64, 21, 22);
        Pikachu_WalkLeft[0] = SS_PikachuMovement.getSubImage(35, 61, 23, 24);
        Pikachu_WalkLeft[2] = SS_PikachuMovement.getSubImage(71, 61, 21, 24);

        Pikachu_WalkRight[1] = SS_PikachuMovement.getSubImage(1, 94, 21, 22);
        Pikachu_WalkRight[0] = SS_PikachuMovement.getSubImage(34, 95, 22, 24);
        Pikachu_WalkRight[2] = SS_PikachuMovement.getSubImage(68, 92, 21, 24);

        PikachuBF[0] = SS_PikachuBF.getSubImage(0,0,474, 474);
        PikachuFace[0] = SS_PikachuFace.getSubImage(0,0,391, 313);

        Pikachu_A1[0] = SSA_Pikachu_1.getSubImage(0, 0, 220, 320);
        Pikachu_A1[1] = SSA_Pikachu_2.getSubImage(0, 0, 220, 320);

        Pikachu_A1S[0] = SSA_Pikachu_1.getSubImage(0, 0, 220, 320);

        Pikachu_A2[0] = SSA_Pikachu_3.getSubImage(0,0, 966, 970);
        Pikachu_A3[0] = SSA_Pikachu_4.getSubImage(0, 0, 370, 360);



       // BigChungus_WalkRight[0]= SS_BigChungus.getSubImage(0, 0,450,834);
       // BigChungus_WalkRight[1]= SS_BigChungus.getSubImage(452,9, 518, 866);
       // BigChungus_WalkRight[2]= SS_BigChungus.getSubImage(986,6,484,802);

        BigChungus_WalkRight[0]= SS_BigChungus.getSubImage(0, 3,449,830);
        BigChungus_WalkRight[1]= SS_BigChungus.getSubImage(462,11, 447, 824);
        BigChungus_WalkRight[2]= SS_BigChungus.getSubImage(940,6,450,830);
        BigChungus_WalkLeft[0]= SS_BigChungus.getSubImage(1484, 1030,454,842);
        BigChungus_WalkLeft[1]= SS_BigChungus.getSubImage(1019,1045, 452, 812);
        BigChungus_WalkLeft[2]= SS_BigChungus.getSubImage(543,1028,454,834);
        BigChungusBF[0] = SS_BigChungus.getSubImage(14,875,451,839);
        BigChungusFace[0] = SS_BigChungus.getSubImage(525,888,311,135);
        BigChungus_A1[0] = SSA_BigChungus_1.getSubImage(0,0,471,144);

        BigChungus_A2[0] = SSA_BigChungus_2.getSubImage(0,0,377,518);
        BigChungus_A2[1] = SSA_BigChungus_2.getSubImage(441,87,535,365);
        BigChungus_A2[2] = SSA_BigChungus_2.getSubImage(1108,3,377,518);
        BigChungus_A2[3] = SSA_BigChungus_2.getSubImage(1538,83,520,365);

        BigChungus_A3[0] = SSA_BigChungus_2.getSubImage(48,705,512,512);
        BigChungus_A3[1] = SSA_BigChungus_2.getSubImage(589,721,512,512);

        BigChungus_A4[0] = SSA_BigChungus_2.getSubImage(105,1392,272,488);
        BigChungus_A4[1] = SSA_BigChungus_2.getSubImage(472,1504,495,272);
        BigChungus_A4[2] = SSA_BigChungus_2.getSubImage(1039,1385,272,495);
        BigChungus_A4[3] = SSA_BigChungus_2.getSubImage(1460,1533,495,272);

        for(int i = 0; i<12; i++)
            SaveIcon[i]=SS_Save_Icon.getSubImage(i*768, 0, 768, 768);

        Pavement[0]= SS_Pavement.getSubImage(0,0,1600,1600);



        Room1_1 = SS_Room1_1.getSubImage(0,0,32,32);
        Room2_1= SS_Room2_1.getSubImage(0,0,32,32);
        Room2_1O=SS_Room2_1O.getSubImage(0,0,32,32);
        Room2_2=SS_Room2_2.getSubImage(0,0,20,15);
        Room2_2O=SS_Room2_2.getSubImage(0,15,20,15);
        Room2_3=SS_Room2_3.getSubImage(0,0,96,96);
        Room2_3O=SS_Room2_3.getSubImage(0,96,96,96);


        TheLastEntity = SS_People.getSubImage(500,11,64,71);
        TheLastEntityFace=SS_People.getSubImage(510,11,50,40);
        Book=SS_People.getSubImage(243,10,18,25);

        Grass=SS_Forest.getSubImage(0,0,32,31);
        Tree = SS_Forest.getSubImage(31,28,32,34);
        Orb = SS_ExtraItems.getSubImage(0,0,200,200);
        MalarioBF=SS_Malario.getSubImage(0,0,184,184);
        Malario_A1[0]=SSA_Malario.getSubImage(0,0,422,185);
        Malario_A2[0]=SSA_Malario.getSubImage(0,207,422,350);

        TposerBF=SS_Tposer.getSubImage(0,0,239,305);
        Tposer_A1[0] = SS_Tposer.getSubImage(320,0,273,302);
        Tposer_A2[0] = SS_Tposer.getSubImage(390,438,198,161);


    }
    //private void assignTex(Game game){
   //     this.game.knuckles;
   // }






}
