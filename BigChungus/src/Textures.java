


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
    SpriteSheet SS_Shaggy;
    SpriteSheet SSA_BigChungus_2;
    SpriteSheet SS_Save_Icon;
    SpriteSheet SS_Pavement;

    SpriteSheet SS_Room0_1;
    SpriteSheet SS_Room1_1;
    SpriteSheet SS_Room1_2;
    SpriteSheet SS_Room1_3;
    SpriteSheet SS_Room1_4;
    SpriteSheet SS_Room2_1;
    SpriteSheet SS_Room2_1O;
    SpriteSheet SS_Room2_2;
    SpriteSheet SS_Room2_3;
    SpriteSheet SS_Room2_4;
    SpriteSheet SS_Room3_1;
    SpriteSheet SS_Room3_2;
    SpriteSheet SS_Room3_3;
    SpriteSheet SS_Room3_4;
    SpriteSheet SS_Room3_5;
    SpriteSheet SS_Room3_6;
    SpriteSheet SS_Room3_7;
    SpriteSheet SS_Room3_8;
    SpriteSheet SS_Room3_9;
    SpriteSheet SS_Room4_1;
    SpriteSheet SS_Room4_2;
    SpriteSheet SS_Room4_3;
    SpriteSheet SS_Room4_4;
    SpriteSheet SS_Room4_5;
    SpriteSheet SS_Room4_6;
    SpriteSheet SS_Room4_7;
    SpriteSheet SS_Room5_1;
    SpriteSheet SS_Room5_2;
    SpriteSheet SS_Room5_3;
    SpriteSheet SS_Room5_4;
    SpriteSheet SS_Room5_5;
    SpriteSheet SS_Room5_6;



    SpriteSheet SS_People;
    SpriteSheet SS_Forest;
    SpriteSheet SS_Tiles;

    SpriteSheet SS_ExtraItems;
    SpriteSheet SS_Malario;
    SpriteSheet SSA_Malario;
    SpriteSheet SS_Tposer;
    SpriteSheet SS_FatYoshi;
    SpriteSheet SS_JoshuaGiraffe;
    SpriteSheet SS_MiscJFAttacks;
    SpriteSheet SS_DatBoiKermit;
    SpriteSheet SS_BongoCatCrab;
    SpriteSheet SS_AntiHero;
    SpriteSheet SS_AntiHeroBackwards;
    SpriteSheet SS_AntiHeroAttacks;
    SpriteSheet SS_ZuckHarambe;
    SpriteSheet SS_YodelKazoo;
    SpriteSheet SS_WaluigiBob;
    SpriteSheet SS_ArthurSpaget;
    SpriteSheet SS_Area51;
    SpriteSheet SS_Dead;
    SpriteSheet SS_Piano;

    SpriteSheet SS_Josh;
    SpriteSheet SS_TitleScreen;
    SpriteSheet SS_ControlScreen;

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

    private transient BufferedImage BI_People;
    private transient BufferedImage BI_Forest;
    private transient BufferedImage BI_Tiles;

    private transient BufferedImage BI_Room0_1;
    private transient BufferedImage BI_Room1_1;
    private transient BufferedImage BI_Room1_2;
    private transient BufferedImage BI_Room1_3;
    private transient BufferedImage BI_Room1_4;
    private transient BufferedImage BI_Room2_1;
    private transient BufferedImage BI_Room2_1O;
    private transient BufferedImage BI_Room2_2;
    private transient BufferedImage BI_Room2_3;
    private transient BufferedImage BI_Room2_4;
    private transient BufferedImage BI_Room3_1;
    private transient BufferedImage BI_Room3_2;
    private transient BufferedImage BI_Room3_3;
    private transient BufferedImage BI_Room3_4;
    private transient BufferedImage BI_Room3_5;
    private transient BufferedImage BI_Room3_6;
    private transient BufferedImage BI_Room3_7;
    private transient BufferedImage BI_Room3_8;
    private transient BufferedImage BI_Room3_9;
    private transient  BufferedImage BI_Room4_1;
    private transient BufferedImage BI_Room4_2;
    private transient BufferedImage BI_Room4_3;
    private transient BufferedImage BI_Room4_4;
    private transient BufferedImage BI_Room4_5;
    private transient BufferedImage BI_Room4_6;
    private transient BufferedImage BI_Room4_7;
    private transient BufferedImage BI_Room5_1;
    private transient BufferedImage BI_Room5_2;
    private transient BufferedImage BI_Room5_3;
    private transient BufferedImage BI_Room5_4;
    private transient BufferedImage BI_Room5_5;
    private transient BufferedImage BI_Room5_6;



    private transient BufferedImage BI_ExtraItems;
    private transient BufferedImage BI_Malario;
    private transient BufferedImage BI_A_Malario;
    private transient BufferedImage BI_Tposer;
    private transient BufferedImage BI_FatYoshi;
    private transient BufferedImage BI_JoshuaGiraffe;

    private transient BufferedImage BI_MiscJFAttacks;
    private transient BufferedImage BI_DatBoiKermit;
    private transient BufferedImage BI_BongoCatCrab;
    private transient BufferedImage BI_AntiHero;
    private transient BufferedImage BI_AntiHeroBackwards;
    private transient BufferedImage BI_AntiHeroAttacks;
    private transient BufferedImage BI_ZuckHarambe;
    private transient BufferedImage BI_YodelKazoo;
    private transient BufferedImage BI_WaluigiBob;
    private transient BufferedImage BI_ArthurSpaget;
    private transient BufferedImage BI_Area51;
    private transient BufferedImage BI_Dead;
    private transient BufferedImage BI_Piano;
    private transient BufferedImage BI_Josh;
    private transient BufferedImage BI_Shaggy;
    private transient BufferedImage BI_TitleScreen;
    private transient BufferedImage BI_ControlScreen;


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

    private transient InputStream IS_People;
    private transient InputStream IS_Forest;

    private transient InputStream IS_Room0_1;
    private transient InputStream IS_Room1_1;
    private transient InputStream IS_Room1_2;
    private transient InputStream IS_Room1_3;
    private transient InputStream IS_Room1_4;
    private transient InputStream IS_Room2_1;
    private transient InputStream IS_Room2_1O;
    private transient InputStream IS_Room2_2;
    private transient InputStream IS_Room2_3;
    private transient InputStream IS_Room2_4;
    private transient InputStream IS_Room3_1;
    private transient InputStream IS_Room3_2;
    private transient InputStream IS_Room3_3;
    private transient InputStream IS_Room3_4;
    private transient InputStream IS_Room3_5;
    private transient InputStream IS_Room3_6;
    private transient InputStream IS_Room3_7;
    private transient InputStream IS_Room3_8;
    private transient InputStream IS_Room3_9;
    private transient InputStream IS_Room4_1;
    private transient InputStream IS_Room4_2;
    private transient InputStream IS_Room4_3;
    private transient InputStream IS_Room4_4;
    private transient InputStream IS_Room4_5;
    private transient InputStream IS_Room4_6;
    private transient InputStream IS_Room4_7;
    private transient InputStream IS_Room5_1;
    private transient InputStream IS_Room5_2;
    private transient InputStream IS_Room5_3;
    private transient InputStream IS_Room5_4;
    private transient InputStream IS_Room5_5;
    private transient InputStream IS_Room5_6;





    private transient InputStream IS_ExtraItems;
    private transient InputStream IS_Malario;
    private transient InputStream IS_A_Malario;
    private transient InputStream IS_Tposer;

    private transient InputStream IS_Tiles;

    private transient InputStream IS_JoshuaGiraffe;
    private transient InputStream IS_FatYoshi;
    private transient InputStream IS_MiscJFAttacks;
    private transient InputStream IS_DatBoiKermit;
    private transient InputStream IS_BongoCatCrab;
    private transient InputStream IS_AntiHero;
    private transient InputStream IS_AntiHeroBackwards;
    private transient InputStream IS_AntiHeroAttacks;
    private transient InputStream IS_ZuckHarambe;
    private transient InputStream IS_YodelKazoo;
    private transient InputStream IS_WaluigiBob;
    private transient InputStream IS_ArthurSpaget;
    private transient InputStream IS_Area51;
    private transient InputStream IS_Dead;
    private transient InputStream IS_Piano;
    private transient InputStream IS_Josh;
    private transient InputStream IS_Shaggy;

    private transient InputStream IS_TitleScreen;
    private transient InputStream IS_ControlScreen;

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


    public transient BufferedImage[] AntiHero_WalkLeft = new BufferedImage[2];
    public transient BufferedImage[] AntiHero_WalkRight = new BufferedImage[2];
    public transient BufferedImage[] AntiHero_WalkUp = new BufferedImage[3];
    public transient BufferedImage[] AntiHero_WalkDown = new BufferedImage[3];
    public transient BufferedImage[] AntiHero_Face = new BufferedImage[1];
    public transient BufferedImage AntiHeroBF;

    public transient BufferedImage[] AntiHero_WalkLeftB = new BufferedImage[2];
    public transient BufferedImage[] AntiHero_WalkRightB = new BufferedImage[2];
    public transient BufferedImage[] AntiHero_WalkUpB = new BufferedImage[3];
    public transient BufferedImage[] AntiHero_WalkDownB = new BufferedImage[3];
    public transient BufferedImage[] AntiHero_FaceB = new BufferedImage[1];
    public transient BufferedImage AntiHeroBFB;
    public transient BufferedImage[] AntiHero_A1=new BufferedImage[1];
    public transient BufferedImage[] AntiHero_A2=new BufferedImage[1];
    public transient BufferedImage[] AntiHero_A3=new BufferedImage[1];
    public transient BufferedImage[] AntiHero_A4=new BufferedImage[1];


    public transient BufferedImage ShaggyBF;
    public transient BufferedImage ShaggyFace;
    public transient BufferedImage[] Shaggy_WalkRight=new BufferedImage[1];

    public transient BufferedImage Room0_1;
    public transient BufferedImage Room1_1;
    public transient BufferedImage Room1_2;
    public transient BufferedImage Room1_3;
    public transient BufferedImage Room1_4;
    public transient BufferedImage Room2_1;
    public transient BufferedImage Room2_1O;
    public transient BufferedImage Room2_2;
    public transient BufferedImage Room2_2O;
    public transient BufferedImage Room2_3;
    public transient BufferedImage Room2_3O;
    public transient BufferedImage Room2_4;
    public transient BufferedImage Room2_4O;
    public transient BufferedImage Room3_1;
    public transient BufferedImage Room3_1O;
    public transient BufferedImage Room3_2;
    public transient BufferedImage Room3_2O;
    public transient BufferedImage Room3_3;
    public transient BufferedImage Room3_3O;
    public transient BufferedImage Room3_4;
    public transient BufferedImage Room3_4O;
    public transient BufferedImage Room3_5;
    public transient BufferedImage Room3_5O;
    public transient BufferedImage Room3_6;
    public transient BufferedImage Room3_6O;
    public transient BufferedImage Room3_7;
    public transient BufferedImage Room3_7O;
    public transient BufferedImage Room3_8;
    public transient BufferedImage Room3_8O;
    public transient BufferedImage Room3_9;
    public transient BufferedImage Room3_9O;
    public transient BufferedImage Room4_1;
    public transient BufferedImage Room4_1O;
    public transient BufferedImage Room4_2;
    public transient BufferedImage Room4_2O;
    public transient BufferedImage Room4_3;
    public transient BufferedImage Room4_3O;
    public transient BufferedImage Room4_4;
    public transient BufferedImage Room4_4O;
    public transient BufferedImage Room4_5;
    public transient BufferedImage Room4_5O;
    public transient BufferedImage Room4_6;
    public transient BufferedImage Room4_6O;
    public transient BufferedImage Room4_7;
    public transient BufferedImage Room4_7O;
    public transient BufferedImage Room5_1;
    public transient BufferedImage Room5_1O;
    public transient BufferedImage Room5_2;
    public transient BufferedImage Room5_2O;
    public transient BufferedImage Room5_3;
    public transient BufferedImage Room5_3O;
    public transient BufferedImage Room5_4O;
    public transient BufferedImage Room5_4;
    public transient BufferedImage Room5_5;
    public transient BufferedImage Room5_5O;
    public transient BufferedImage Room5_6O;
    public transient BufferedImage Room5_6;


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

    public transient BufferedImage RedGround;
    public transient BufferedImage Mushroom;
    public transient BufferedImage Ice;

    public transient BufferedImage FatYoshiBF;
    public transient BufferedImage JoshuaGiraffeBF;

    public transient BufferedImage[] FatYoshi_A1=new BufferedImage[1];
    public transient BufferedImage[] JoshuaGiraffe_A1=new BufferedImage[1];
    public transient BufferedImage[] JoshuaGiraffe_A2=new BufferedImage[1];

    public transient BufferedImage KermitBF;
    public transient BufferedImage DatBoiBF;
    public transient BufferedImage[] Kermit_A1=new BufferedImage[1];
    public transient BufferedImage[] DatBoi_A1=new BufferedImage[1];
    public transient BufferedImage[] DatBoi_A2=new BufferedImage[1];

    public transient BufferedImage BongoCatBF;
    public transient BufferedImage CrabBF;
    public transient BufferedImage[] BongoCat_A1=new BufferedImage[1];
    public transient BufferedImage[] BongoCat_A2=new BufferedImage[1];
    public transient BufferedImage[] Crab_A1=new BufferedImage[1];
    public transient BufferedImage[] Crab_A2=new BufferedImage[1];

    public transient BufferedImage ZuckBF;
    public transient BufferedImage HarambeBF;
    public transient BufferedImage[] Zuck_A1=new BufferedImage[1];
    public transient BufferedImage[] Zuck_A2=new BufferedImage[1];
    public transient BufferedImage[] Harambe_A1=new BufferedImage[1];
    public transient BufferedImage[] Harambe_A2=new BufferedImage[1];

    public transient BufferedImage YodelBoyBF;
    public transient BufferedImage KazooKidBF;
    public transient BufferedImage[] YodelBoy_A1=new BufferedImage[1];
    public transient BufferedImage[] YodelBoy_A2=new BufferedImage[1];
    public transient BufferedImage[] KazooKid_A1=new BufferedImage[1];
    public transient BufferedImage[] KazooKid_A2=new BufferedImage[1];

    public transient BufferedImage SpongebobBF;
    public transient BufferedImage WaluigiBF;
    public transient BufferedImage[] Spongebob_A1= new BufferedImage[1];
    public transient BufferedImage[] Spongebob_A2= new BufferedImage[1];
    public transient BufferedImage[] Waluigi_A1= new BufferedImage[1];
    public transient BufferedImage[] Waluigi_A2= new BufferedImage[1];

    public transient BufferedImage ArthurBF;
    public transient BufferedImage SpagetBF;
    public transient BufferedImage[] Arthur_A1 = new BufferedImage[1];
    public transient BufferedImage[] Arthur_A2 = new BufferedImage[1];
    public transient BufferedImage[] Spaget_A1 = new BufferedImage[1];
    public transient BufferedImage[] Spaget_A2 = new BufferedImage[1];

    public transient BufferedImage[] aliens=new BufferedImage[4];
    public transient BufferedImage[] guards=new BufferedImage[4];
    public transient BufferedImage[] area51Tiles=new BufferedImage[2];

    public transient BufferedImage[] alienFaces=new BufferedImage[4];
    public transient BufferedImage[] guardFaces = new BufferedImage[4];

    public transient BufferedImage[] redSwitch= new BufferedImage[2];
    public transient BufferedImage[] greenSwitch= new BufferedImage[2];

    public transient BufferedImage WHO;

    public transient BufferedImage computer;

    public transient BufferedImage Tree2;
    public transient BufferedImage grave;
    public transient BufferedImage dirt;

    public transient BufferedImage whiteKey;
    public transient BufferedImage blackKey;
    public transient BufferedImage whiteKeyPressed;
    public transient BufferedImage blackKeyPressed;
    public transient BufferedImage pianoBack;
    public transient BufferedImage josh;

    public transient BufferedImage TitleScreen;
    public transient BufferedImage ControlScreen;


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

            IS_Room2_4=new FileInputStream("res/Rooms/Room2_4.png");
            BI_Room2_4=ImageIO.read(IS_Room2_4);



            IS_Room3_1=new FileInputStream("res/Rooms/Room3_1.png");
            BI_Room3_1=ImageIO.read(IS_Room3_1);


            IS_Tiles=new FileInputStream("res/Tiles.png");
            BI_Tiles = ImageIO.read(IS_Tiles);

            IS_FatYoshi=new FileInputStream("res/FatYoshi/FatYoshiBF.png");
            BI_FatYoshi=ImageIO.read(IS_FatYoshi);

            IS_JoshuaGiraffe=new FileInputStream("res/JoshuaGiraffe/JoshuaGiraffeBF.png");
            BI_JoshuaGiraffe=ImageIO.read(IS_JoshuaGiraffe);

            IS_MiscJFAttacks=new FileInputStream("res/Attacks/EnemyAttacks/YoshiGiraffeAttacks.png");
            BI_MiscJFAttacks = ImageIO.read(IS_MiscJFAttacks);

            IS_DatBoiKermit=new FileInputStream("res/DatBoiKermit/KermitDatBoi.png");
            BI_DatBoiKermit=ImageIO.read(IS_DatBoiKermit);

            IS_BongoCatCrab=new FileInputStream("res/BongoCatCrab/BongoCatCrab.png");
            BI_BongoCatCrab=ImageIO.read(IS_BongoCatCrab);

            IS_AntiHero=new FileInputStream("res/Player/SS_AntiPlayer.png");
            BI_AntiHero=ImageIO.read(IS_AntiHero);

            IS_AntiHeroBackwards=new FileInputStream("res/Player/SS_AntiPlayerBackwards.png");
            BI_AntiHeroBackwards=ImageIO.read(IS_AntiHeroBackwards);

            IS_AntiHeroAttacks=new FileInputStream("res/Player/MagicBlasts.png");
            BI_AntiHeroAttacks=ImageIO.read(IS_AntiHeroAttacks);

            IS_ZuckHarambe=new FileInputStream("res/ZuckHarambe/ZuckHarambe.png");
            BI_ZuckHarambe=ImageIO.read(IS_ZuckHarambe);

            IS_YodelKazoo=new FileInputStream("res/YodelKazoo/YodelKazoo.png");
            BI_YodelKazoo=ImageIO.read(IS_YodelKazoo);

            IS_WaluigiBob=new FileInputStream("res/WaluigiBob/WaluigiBob.png");
            BI_WaluigiBob=ImageIO.read(IS_WaluigiBob);

            IS_ArthurSpaget=new FileInputStream("res/ArthurSpaget/ArthurSpaget.png");
            BI_ArthurSpaget=ImageIO.read(IS_ArthurSpaget);

            IS_Area51=new FileInputStream("res/Area51.png");
            BI_Area51=ImageIO.read(IS_Area51);

            IS_Room3_2=new FileInputStream("res/Rooms/Room3_2.png");
            BI_Room3_2=ImageIO.read(IS_Room3_2);

            IS_Room3_3=new FileInputStream("res/Rooms/Room3_3.png");
            BI_Room3_3=ImageIO.read(IS_Room3_3);

            IS_Room3_4=new FileInputStream("res/Rooms/Room3_4.png");
            BI_Room3_4=ImageIO.read(IS_Room3_4);

            IS_Room3_5=new FileInputStream("res/Rooms/Room3_5.png");
            BI_Room3_5=ImageIO.read(IS_Room3_5);

            IS_Room3_6=new FileInputStream("res/Rooms/Room3_6.png");
            BI_Room3_6=ImageIO.read(IS_Room3_6);

            IS_Room3_7=new FileInputStream("res/Rooms/Room3_7.png");
            BI_Room3_7=ImageIO.read(IS_Room3_7);

            IS_Room3_8=new FileInputStream("res/Rooms/Room3_8.png");
            BI_Room3_8=ImageIO.read(IS_Room3_8);

            IS_Room3_9=new FileInputStream("res/Rooms/Room3_9.png");
            BI_Room3_9=ImageIO.read(IS_Room3_9);

            IS_Room4_1=new FileInputStream("res/Rooms/Room4_1.png");
            BI_Room4_1=ImageIO.read(IS_Room4_1);

            IS_Room4_2=new FileInputStream("res/Rooms/Room4_2.png");
            BI_Room4_2=ImageIO.read(IS_Room4_2);

            IS_Room4_3=new FileInputStream("res/Rooms/Room4_3.png");
            BI_Room4_3=ImageIO.read(IS_Room4_3);

            IS_Room4_4=new FileInputStream("res/Rooms/Room4_4R.png");
            BI_Room4_4=ImageIO.read(IS_Room4_4);

            IS_Room4_5=new FileInputStream("res/Rooms/Room4_5.png");
            BI_Room4_5=ImageIO.read(IS_Room4_5);

            IS_Room4_6=new FileInputStream("res/Rooms/Room4_6.png");
            BI_Room4_6=ImageIO.read(IS_Room4_6);

            IS_Room4_7=new FileInputStream("res/Rooms/Room4_7.png");
            BI_Room4_7=ImageIO.read(IS_Room4_7);

            IS_Room5_1=new FileInputStream("res/Rooms/Room5_1.png");
            BI_Room5_1=ImageIO.read(IS_Room5_1);

            IS_Room5_2=new FileInputStream("res/Rooms/Room5_2.png");
            BI_Room5_2=ImageIO.read(IS_Room5_2);

            IS_Room5_3=new FileInputStream("res/Rooms/Room5_3R.png");
            BI_Room5_3=ImageIO.read(IS_Room5_3);

            IS_Room5_4=new FileInputStream("res/Rooms/Room5_4.png");
            BI_Room5_4=ImageIO.read(IS_Room5_4);

            IS_Room5_5=new FileInputStream("res/Rooms/Room5_5.png");
            BI_Room5_5=ImageIO.read(IS_Room5_5);

            IS_Room5_6=new FileInputStream("res/Rooms/Room5_6.png");
            BI_Room5_6=ImageIO.read(IS_Room5_6);

            IS_Room0_1=new FileInputStream("res/Rooms/Room0_1.png");
            BI_Room0_1=ImageIO.read(IS_Room0_1);

            IS_Room1_2=new FileInputStream("res/Rooms/Room1_2.png");
            BI_Room1_2=ImageIO.read(IS_Room1_2);

            IS_Room1_3=new FileInputStream("res/Rooms/Room1_3.png");
            BI_Room1_3=ImageIO.read(IS_Room1_3);

            IS_Room1_4=new FileInputStream("res/Rooms/Room1_4.png");
            BI_Room1_4=ImageIO.read(IS_Room1_4);

            IS_Dead=new FileInputStream("res/DeadTiles.png");
            BI_Dead=ImageIO.read(IS_Dead);

            IS_Piano=new FileInputStream("res/Piano.png");
            BI_Piano=ImageIO.read(IS_Piano);

            IS_Josh=new FileInputStream("res/Josh.png");
            BI_Josh=ImageIO.read(IS_Josh);

            IS_Shaggy=new FileInputStream("res/SS_Shaggy.png");
            BI_Shaggy=ImageIO.read(IS_Shaggy);

            IS_TitleScreen=new FileInputStream("res/TitleScreen.png");
            BI_TitleScreen=ImageIO.read(IS_TitleScreen);
            IS_ControlScreen=new FileInputStream("res/ControlScreen.png");
            BI_ControlScreen=ImageIO.read(IS_ControlScreen);



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
        SS_Room2_4=new SpriteSheet(BI_Room2_4);
        SS_Room3_1=new SpriteSheet(BI_Room3_1);
        SS_Room3_2=new SpriteSheet(BI_Room3_2);
        SS_Room3_3=new SpriteSheet(BI_Room3_3);
        SS_Room3_4=new SpriteSheet(BI_Room3_4);
        SS_Room3_5=new SpriteSheet(BI_Room3_5);
        SS_Room3_6=new SpriteSheet(BI_Room3_6);
        SS_Room3_7=new SpriteSheet(BI_Room3_7);
        SS_Room3_8=new SpriteSheet(BI_Room3_8);
        SS_Room3_9=new SpriteSheet(BI_Room3_9);
        SS_Room4_1=new SpriteSheet(BI_Room4_1);
        SS_Room4_2=new SpriteSheet(BI_Room4_2);
        SS_Room4_3=new SpriteSheet(BI_Room4_3);
        SS_Room4_4=new SpriteSheet(BI_Room4_4);
        SS_Room4_5=new SpriteSheet(BI_Room4_5);
        SS_Room4_6=new SpriteSheet(BI_Room4_6);
        SS_Room4_7=new SpriteSheet(BI_Room4_7);
        SS_Room5_1=new SpriteSheet(BI_Room5_1);
        SS_Room5_2=new SpriteSheet(BI_Room5_2);
        SS_Room5_3=new SpriteSheet(BI_Room5_3);
        SS_Room5_4=new SpriteSheet(BI_Room5_4);
        SS_Room5_5=new SpriteSheet(BI_Room5_5);
        SS_Room5_6=new SpriteSheet(BI_Room5_6);
        SS_Room0_1=new SpriteSheet(BI_Room0_1);
        SS_Room1_2=new SpriteSheet(BI_Room1_2);
        SS_Room1_3=new SpriteSheet(BI_Room1_3);
        SS_Room1_4=new SpriteSheet(BI_Room1_4);

        SS_Tiles=new SpriteSheet(BI_Tiles);

        SS_JoshuaGiraffe=new SpriteSheet(BI_JoshuaGiraffe);
        SS_FatYoshi=new SpriteSheet(BI_FatYoshi);
        SS_MiscJFAttacks=new SpriteSheet(BI_MiscJFAttacks);

        SS_DatBoiKermit=new SpriteSheet(BI_DatBoiKermit);
        SS_BongoCatCrab=new SpriteSheet(BI_BongoCatCrab);

        SS_AntiHero=new SpriteSheet(BI_AntiHero);
        SS_AntiHeroBackwards=new SpriteSheet(BI_AntiHeroBackwards);
        SS_AntiHeroAttacks=new SpriteSheet(BI_AntiHeroAttacks);
        SS_ZuckHarambe=new SpriteSheet(BI_ZuckHarambe);

        SS_YodelKazoo=new SpriteSheet(BI_YodelKazoo);
        SS_WaluigiBob=new SpriteSheet(BI_WaluigiBob);

        SS_ArthurSpaget=new SpriteSheet(BI_ArthurSpaget);
        SS_Area51= new SpriteSheet(BI_Area51);
        SS_Dead=new SpriteSheet(BI_Dead);
        SS_Piano=new SpriteSheet(BI_Piano);


        SS_Josh=new SpriteSheet(BI_Josh);
        SS_Shaggy=new SpriteSheet(BI_Shaggy);

        SS_TitleScreen=new SpriteSheet(BI_TitleScreen);
        SS_ControlScreen=new SpriteSheet(BI_ControlScreen);


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
        Room2_4O=SS_Room2_4.getSubImage(0,0,96,16);
        Room2_4=SS_Room2_4.getSubImage(0,16,96,16);
        Room3_1=SS_Room3_1.getSubImage(0,0,64,64);
        Room3_1O=SS_Room3_1.getSubImage(0,64,64,64);


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

        Mushroom = SS_Tiles.getSubImage(323,0,96,113);
        RedGround = SS_Tiles.getSubImage(0,107,96,96);
        Ice=SS_Tiles.getSubImage(647,110,96,96);


        FatYoshiBF=SS_FatYoshi.getSubImage(0,0,1080,1062);
        JoshuaGiraffeBF=SS_JoshuaGiraffe.getSubImage(0,0,280,409);

        FatYoshi_A1[0]=SS_MiscJFAttacks.getSubImage(0,0,92,105);
        JoshuaGiraffe_A1[0]=SS_MiscJFAttacks.getSubImage(0,113,142,235);
        JoshuaGiraffe_A2[0]=SS_MiscJFAttacks.getSubImage(115,348,124,43);


        DatBoiBF=SS_DatBoiKermit.getSubImage(16,359,48,108);
        KermitBF=SS_DatBoiKermit.getSubImage(148,15,181,163);
        Kermit_A1[0]=SS_DatBoiKermit.getSubImage(0,238,52,58);
        DatBoi_A1[0]=SS_DatBoiKermit.getSubImage(0,0,115,213);
        DatBoi_A2[0]=SS_DatBoiKermit.getSubImage(0,313,48,39);

        BongoCatBF=SS_BongoCatCrab.getSubImage(10,18,154,144);
        CrabBF=SS_BongoCatCrab.getSubImage(0,180,619,384);
        Crab_A1[0]=SS_BongoCatCrab.getSubImage(15,589,297,273);
        Crab_A2[0]=SS_BongoCatCrab.getSubImage(638,387,400,207);
        BongoCat_A1[0]=SS_BongoCatCrab.getSubImage(204,20,207,159);
        BongoCat_A2[0]=SS_BongoCatCrab.getSubImage(658,0,212,304);



        AntiHero_WalkLeft[0] = SS_AntiHero.grabImage(3, 1, 19, 74);
        AntiHero_WalkLeft[1] = SS_AntiHero.grabImage(5, 1, 19, 74);

        AntiHero_WalkRight[0] = SS_AntiHero.grabImage(4, 1, 19, 74);
        AntiHero_WalkRight[1] = SS_AntiHero.grabImage(2, 1, 19, 74);

        AntiHero_WalkUp[0] = SS_AntiHero.grabImage(1, 2, 19, 74);
        AntiHero_WalkUp[1] = SS_AntiHero.grabImage(2, 2, 19, 74);
        AntiHero_WalkUp[2] = SS_AntiHero.grabImage(3, 2, 19, 74);


        AntiHero_WalkDown[0] = SS_AntiHero.grabImage(4, 2, 19, 74);
        AntiHero_WalkDown[1] = SS_AntiHero.grabImage(1, 1, 19, 74);
        AntiHero_WalkDown[2] = SS_AntiHero.grabImage(6, 1, 19, 74);
        AntiHero_Face[0] = SS_AntiHero.getSubImage(0, 148, 128, 128);
        AntiHeroBF=SS_AntiHero.getSubImage(0, 0, 19, 74);




        AntiHero_WalkLeftB[0] = SS_AntiHeroBackwards.grabImage(3, 1, 19, 74);
        AntiHero_WalkLeftB[1] = SS_AntiHeroBackwards.grabImage(5, 1, 19, 74);

        AntiHero_WalkRightB[0] = SS_AntiHeroBackwards.grabImage(4, 1, 19, 74);
        AntiHero_WalkRightB[1] = SS_AntiHeroBackwards.grabImage(2, 1, 19, 74);

        AntiHero_WalkUpB[0] = SS_AntiHeroBackwards.grabImage(1, 2, 19, 74);
        AntiHero_WalkUpB[1] = SS_AntiHeroBackwards.grabImage(2, 2, 19, 74);
        AntiHero_WalkUpB[2] = SS_AntiHeroBackwards.grabImage(3, 2, 19, 74);


        AntiHero_WalkDownB[0] = SS_AntiHeroBackwards.grabImage(4, 2, 19, 74);
        AntiHero_WalkDownB[1] = SS_AntiHeroBackwards.grabImage(1, 1, 19, 74);
        AntiHero_WalkDownB[2] = SS_AntiHeroBackwards.grabImage(6, 1, 19, 74);
        AntiHero_FaceB[0] = SS_AntiHeroBackwards.getSubImage(0, 148, 128, 128);
        AntiHeroBFB=SS_AntiHeroBackwards.getSubImage(65, 85, 19, 74);


        AntiHero_A1[0]=SS_AntiHeroAttacks.getSubImage(286,39,84,79);
        AntiHero_A2[0]=SS_AntiHeroAttacks.getSubImage(55,145,104,104);
        AntiHero_A3[0]=SS_AntiHeroAttacks.getSubImage(38,263,97,97);
        AntiHero_A4[0]=SS_AntiHeroAttacks.getSubImage(244,370,126,106);

        Zuck_A1[0]=SS_ZuckHarambe.getSubImage(461,0,141,226);
        Zuck_A2[0]=SS_ZuckHarambe.getSubImage(444,262,182,182);

        Harambe_A1[0]=SS_ZuckHarambe.getSubImage(16,594,153,132);
        Harambe_A2[0]=SS_ZuckHarambe.getSubImage(214,603,454,115);
        ZuckBF=SS_ZuckHarambe.getSubImage(0,236,422,313);
        HarambeBF=SS_ZuckHarambe.getSubImage(64,54,171,160);

        KazooKidBF=SS_YodelKazoo.getSubImage(10,0,318,286);
        KazooKid_A1[0]=SS_YodelKazoo.getSubImage(257,339,124,44);
        KazooKid_A2[0]=SS_YodelKazoo.getSubImage(270,482,110,75);


        YodelBoyBF=SS_YodelKazoo.getSubImage(0,305,100,397);
        YodelBoy_A1[0]=SS_YodelKazoo.getSubImage(121,316,87,125);
        YodelBoy_A2[0]=SS_YodelKazoo.getSubImage(129,456,111,145);


        WaluigiBF=SS_WaluigiBob.getSubImage(404,0,184,254);
        Waluigi_A1[0]=SS_WaluigiBob.getSubImage(636,37,132,186);
        Waluigi_A2[0]=SS_WaluigiBob.getSubImage(408,312,142,338);

        SpongebobBF=SS_WaluigiBob.getSubImage(0,0,341,324);
        Spongebob_A1[0]=SS_WaluigiBob.getSubImage(0,371,98,88);
        Spongebob_A2[0]=SS_WaluigiBob.getSubImage(175,361,220,256);


        ArthurBF=SS_ArthurSpaget.getSubImage(15,0,70,237);
        Arthur_A1[0]=SS_ArthurSpaget.getSubImage(0,245,75,79);
        Arthur_A2[0]=SS_ArthurSpaget.getSubImage(94,242,81,81);

        SpagetBF=SS_ArthurSpaget.getSubImage(133,37,117,172);
        Spaget_A1[0]=SS_ArthurSpaget.getSubImage(253,12,109,76);
        Spaget_A2[0]=SS_ArthurSpaget.getSubImage(259,115,134,80);

        aliens[0]=SS_Area51.getSubImage(10,61,43,58);
        aliens[1]=SS_Area51.getSubImage(65,2,29,59);
        aliens[2]=SS_Area51.getSubImage(70,67,22,61);
        aliens[3]=SS_Area51.getSubImage(103,69,57,76);

        guards[0]=SS_Area51.getSubImage(0,154,68,85);
        guards[1]=SS_Area51.getSubImage(105,2,44,66);
        guards[2]=SS_Area51.getSubImage(162,72,44,75);
        guards[3]=SS_Area51.getSubImage(93,160,50,95);

        area51Tiles[0]=SS_Area51.getSubImage(9,0,12,18);
        area51Tiles[1]=SS_Area51.getSubImage(10,21,33,33);



        alienFaces[0]=SS_Area51.getSubImage(65,2,30,30);
        alienFaces[1]=SS_Area51.getSubImage(10,61,42,39);
        alienFaces[2]=SS_Area51.getSubImage(70,67,18,29);
        alienFaces[3]=SS_Area51.getSubImage(111,70,29,36);

        guardFaces[0]=SS_Area51.getSubImage(22,154,20,23);
        guardFaces[1]=SS_Area51.getSubImage(122,2,18,22);
        guardFaces[2]=SS_Area51.getSubImage(176,72,21,23);
        guardFaces[3]=SS_Area51.getSubImage(93,160,37,42);

        computer=SS_Tiles.getSubImage(549,239,115,159);

        Room3_2=SS_Room3_2.getSubImage(0,0,32,32);
        Room3_2O=SS_Room3_2.getSubImage(0,32,32,32);
        Room3_3=SS_Room3_3.getSubImage(0,0,96,32);
        Room3_3O=SS_Room3_3.getSubImage(0,32,96,32);
        Room3_4=SS_Room3_4.getSubImage(0,0,64,64);
        Room3_4O=SS_Room3_4.getSubImage(0,64,64,64);
        Room3_5O=SS_Room3_5.getSubImage(0,32,32,32);
        Room3_5=SS_Room3_5.getSubImage(0,0,32,32);
        Room3_6O=SS_Room3_6.getSubImage(0,64,128,64);
        Room3_6=SS_Room3_6.getSubImage(0,0,128,64);

        Room3_7O=SS_Room3_7.getSubImage(0,32,32,32);
        Room3_7=SS_Room3_7.getSubImage(0,0,32,32);

        Room3_8O=SS_Room3_8.getSubImage(0,32,32,32);
        Room3_8=SS_Room3_8.getSubImage(0,0,32,32);

        Room3_9O=SS_Room3_9.getSubImage(0,64,24,64);
        Room3_9=SS_Room3_9.getSubImage(0,0,24,64);

        Room4_1O=SS_Room4_1.getSubImage(0,64,64,64);
        Room4_1=SS_Room4_1.getSubImage(0,0,64,64);

        Room4_2O=SS_Room4_2.getSubImage(0,64,64,64);
        Room4_2=SS_Room4_2.getSubImage(0,0,64,64);

        Room4_3O=SS_Room4_3.getSubImage(0,64,64,64);
        Room4_3=SS_Room4_3.getSubImage(0,0,64,64);

        Room4_4O=SS_Room4_4.getSubImage(0,128,64,128);
        Room4_4=SS_Room4_4.getSubImage(0,0,64,128);

        Room4_5O=SS_Room4_5.getSubImage(0,20,20,20);
        Room4_5=SS_Room4_5.getSubImage(0,0,20,20);

        Room4_6O=SS_Room4_6.getSubImage(0,64,64,64);
        Room4_6=SS_Room4_6.getSubImage(0,0,64,64);

        Room4_7O=SS_Room4_7.getSubImage(0,32,32,32);
        Room4_7=SS_Room4_7.getSubImage(0,0,32,32);

        Room5_1O=SS_Room5_1.getSubImage(0,64,64,64);
        Room5_1=SS_Room5_1.getSubImage(0,0,64,64);

        Room5_2O=SS_Room5_2.getSubImage(0,32,128,32);
        Room5_2=SS_Room5_2.getSubImage(0,0,128,32);

        Room5_3O=SS_Room5_3.getSubImage(0,32,256,32);
        Room5_3=SS_Room5_3.getSubImage(0,0,256,32);

        Room5_4O=SS_Room5_4.getSubImage(0,64,64,64);
        Room5_4=SS_Room5_4.getSubImage(0,0,64,64);

        Room5_5O=SS_Room5_5.getSubImage(0,64,64,64);
        Room5_5=SS_Room5_5.getSubImage(0,0,64,64);

        Room5_6O=SS_Room5_6.getSubImage(0,32,32,32);
        Room5_6=SS_Room5_6.getSubImage(0,0,32,32);

        Room1_2=SS_Room1_2.getSubImage(0,0,32,32);
        Room1_3=SS_Room1_3.getSubImage(0,0,32,32);
        Room1_4=SS_Room1_4.getSubImage(0,0,32,32);

        Room0_1=SS_Room0_1.getSubImage(0,0,32,128);



        redSwitch[0]=SS_Area51.getSubImage(142,150,47,43);
        redSwitch[1]=SS_Area51.getSubImage(195,153,47,40);
        greenSwitch[0]=SS_Area51.getSubImage(150,207,47,43);
        greenSwitch[1]=SS_Area51.getSubImage(202,209,47,43);

        WHO=SS_Tiles.getSubImage(679,260,128,128);

        Tree2=SS_Dead.getSubImage(32,37,96,85);
        grave=SS_Dead.getSubImage(16,145,116,141);
        dirt=SS_Dead.getSubImage(246,154,28,28);


        whiteKey=SS_Piano.getSubImage(0,12,61,312);
        blackKey=SS_Piano.getSubImage(279,13,33,169);
        whiteKeyPressed=SS_Piano.getSubImage(59,12,61,312);
        blackKeyPressed=SS_Piano.getSubImage(219,13,33,169);
        pianoBack=SS_Piano.getSubImage(0,0,591,12);
        josh=SS_Josh.getSubImage(0,0,231,404);

        ShaggyBF=SS_Shaggy.getSubImage(179,7,351,309);
        Shaggy_WalkRight[0]=SS_Shaggy.getSubImage(44,190,20,70);
        ShaggyFace=SS_Shaggy.getSubImage(194,6,171,180);

        TitleScreen=SS_TitleScreen.getSubImage(0,0,1280,960);
        ControlScreen=SS_ControlScreen.getSubImage(0,0,1280,960);



    }
    //private void assignTex(Game game){
   //     this.game.knuckles;
   // }






}
