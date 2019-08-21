import com.sun.tools.javap.TryBlockWriter;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.Music;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;


public class Game extends Canvas implements Runnable, Serializable{


    /**
     *
     */
    private static final long serialVersionUID  = -1300776496297261616L;


    // height is 960
    public static final float WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;

    //temp

    private transient Thread thread;
    private boolean running = false;
    private Handler handler;
    private Player player;
    private NPC npc;
    transient    static Textures tex;
    private Camera camera;
    private transient BufferedImage currentLevel;
    TextBox tb;
    private TBHandler tbHandler;
    private KeyInput keyInput;
    private STATE currentState;
    private boolean Switch;
    private Battle currentBattle;
    private Font fnt;
    private Magic magic=new Magic();
    private AudioPlayer ap;
    private Inventory inv=new Inventory(this);
    private SaveObject so;
    private Enemy knuckles1;
    private Enemy pikachu;
    private Enemy bigChungus;
    private Enemy antihero;
    ////// careful
    transient private HashMap<String, BufferedImage> rooms = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> ranges=new HashMap<>();
    private String currentRoom;
    private String currentArea="";
    private boolean aliensFreed;
    private boolean A51_Switch1;
    private boolean A51_Switch2;
    private boolean A51_Switch3;
    private boolean A51_Switch4;
    private NPC A51_RSwitch1;
    private NPC A51_RSwitch2;
    private NPC A51_RSwitch3;
    private NPC A51_RSwitch4;

    private boolean A51_Switch5;
    private boolean A51_Switch6;
    private boolean A51_Switch7;
    private boolean A51_Switch8;
    private NPC A51_RSwitch5;
    private NPC A51_RSwitch6;
    private NPC A51_RSwitch7;
    private NPC A51_RSwitch8;
    private NPC A51_GSwitch1;
    private NPC who1;
    private NPC who2;
    private TextBox whoT1;
    private TextBox whoT2;
    private Block barrier;
    private boolean specialGameWin;


    private boolean endangered=false;

    // obtainable items and spells creation
    private Spells Room2_2_Basic_Heal;
    private Items Room2_3_Tidepod;
    private Items Room2_3_EmptyWaterBottle;
    private Items Room2_3_LiptonIcedTea;
    private Items Room3_2_WizardWand;
    private Items Room3_5_WizardCloak;
    private Items Room3_9_LightSaber;
    private Items Room3_9_AliensHat;
    private Items Room3_6_BigBopper;
    private Items Room3_6_SpaceFood;
    private Items Room3_8_InstaProtein;
    private Items Room3_7_ChickenNugget;



    private Spells Room2_3_YeetusDeletus;
    private Spells Room2_3_EnnieMeenieMinieMo;
    private Spells Room2_3_BoogieWoogie;
    private Spells Room3_2_FocusBeam;
    private Spells Room3_5_Earthquake;
    // in battle only
    private Spells Room3_1_ManaThrow;

    private Items Room4_4_C_I_T_Shirt;
    private Items Room4_4_BlueEyes;
    private Items Room4_2_Nokia;
    private Items Room4_1_UnoReverse;
    private Items Room4_3_BunnyEars;
    private Spells Room4_3_Laser;
    private Spells Room4_4_Screech;
    private Spells Room4_3_TakeItBackNowYall;
    private Spells Room4_4_Skert;
    private Spells Room4_4_FullHeal;
    private Items Room5_5_Bleach; //mana heal
    private Items Room5_4_ChickenTender; // health upgrade
    private Spells autokill;
    private Spells Room5_3_ManaRestore;
    private Spells Room5_3_TheGodPiercer;




    private GamePlayer gp;
    private int score=0;
    private int level=1;
    private Spawn spawn;
    private int gameHealth;


    private Block C3;
    private Block C3S;
    private Block D3;
    private Block D3S;
    private Block E3;
    private Block F3;
    private Block F3S;
    private Block G3;
    private Block G3S;
    private Block A4;
    private Block A4S;
    private Block B4;
    private Block C4;
    private Block C4S;
    private Block D4;
    private Block D4S;
    private Block E4;
    private boolean[] pianoKey=new boolean[10];
    private boolean pianoPuzzle;
    transient  private Block pianoBlock;

    private Block greenSimon;
    private Block blueSimon;
    private Block yellowSimon;
    private Block redSimon;

    private long greenPressed=0, yellowPressed=0, bluePressed=0, redPressed=0;
    private long time2, timeNow2, timeOfLastShot2;

    private boolean simonFirst;
    private int spot;
    private int spot2;
    private int spotHolder;
    private int highest=0;
    private String currentPath="";
    private boolean simonPuzzle;
    private Block simonBlock;
    private Items Room5_6_OrbR;
    private Items Room5_6_OrbD;

    private TextBox bc1;
    private TextBox bc2;
    private TextBox bc3;
    private TextBox bc4;
    private TextBox bc5;

    private boolean endgame;

    private String currentMusic;

    // pattern for simon says:
    // GGBYRRYGYR
    // G 0
    // GG 1 2
    // GGB 3 4 5
    // GGBY 6 7 8 9
    // GGBYR 10 11 12 13 14
    // GGBYRR 15 16 17 18 19 20
    // GGBYRRY 21 22 23 24 25 26 27
    // GGBYRRYG 28 29 30 31 32 33 34 35
    // GGBYRRYGY 36 37 38 39 40 41 42 43 44
    // GGBYRRYGYR 45 46 47 48 49 50 51 52 53 54

    // total path
    // GGGGGBGGBYGGBYRGGBYRRGGBYRRYGGBYRRYGGGBYRRYGYGGBYRRYGYR
    // 55 total clicks

    private boolean[] simonSays=new boolean[55];
    private String simonKey="GGGGGBGGBYGGBYRGGBYRRGGBYRRYGGBYRRYGGGBYRRYGYGGBYRRYGYR";
    private ArrayList<String> simonKeysList=new ArrayList<>();


    private long timeOfLastShot, time, timeNow;
    private boolean hero;
    private int lastX, lastY;
    private boolean playingGame;


    public enum STATE {
        TitleScreen,
        ControlScreen,
        FirstArea,
        Battle,
        GameOver,
        Normal,
        SpecialGame
    }


    private Window window;

    public Game() {


        this.playingGame=false;

        this.ap = new AudioPlayer();
        this.ap.load();
        tex = new Textures();
        rooms.put("Testing Room", tex.SS_FirstArea.grabImage(1, 1, 16, 16));
        rooms.put("Room1_1", tex.Room1_1);
        rooms.put("Room2_1",tex.Room2_1);
        rooms.put("Room2_2",tex.Room2_2);
        rooms.put("Room2_3",tex.Room2_3);
        rooms.put("Room2_4",tex.Room2_4);
        rooms.put("Room3_1",tex.Room3_1);
        rooms.put("Room3_2",tex.Room3_2);
        rooms.put("Room3_3",tex.Room3_3);
        rooms.put("Room3_4",tex.Room3_4);
        rooms.put("Room3_5",tex.Room3_5);
        rooms.put("Room3_6",tex.Room3_6);
        rooms.put("Room3_7",tex.Room3_7);
        rooms.put("Room3_8",tex.Room3_8);
        rooms.put("Room3_9",tex.Room3_9);
        rooms.put("Room4_1",tex.Room4_1);
        rooms.put("Room4_2",tex.Room4_2);
        rooms.put("Room4_3",tex.Room4_3);
        rooms.put("Room4_4",tex.Room4_4);
        rooms.put("Room4_5",tex.Room4_5);
        rooms.put("Room4_6",tex.Room4_6);
        rooms.put("Room4_7",tex.Room4_7);
        rooms.put("Room5_1",tex.Room5_1);
        rooms.put("Room5_2",tex.Room5_2);
        rooms.put("Room5_3",tex.Room5_3);
        rooms.put("Room5_4",tex.Room5_4);
        rooms.put("Room5_5",tex.Room5_5);
        rooms.put("Room5_6",tex.Room5_6);
        rooms.put("Room0_1",tex.Room0_1);
        rooms.put("Room1_2",tex.Room1_2);
        rooms.put("Room1_3",tex.Room1_3);
        rooms.put("Room1_4",tex.Room1_4);






        ranges.put("Room1_1",new ArrayList<>());
        ranges.get("Room1_1").add(4000);
        ranges.get("Room1_1").add(2000);

        ranges.put("Room2_1",new ArrayList<>());
        ranges.get("Room2_1").add(700);
        ranges.get("Room2_1").add(1000);

        ranges.put("Room2_2",new ArrayList<>());
        ranges.get("Room2_2").add((-64));
        ranges.get("Room2_2").add(-64);

        ranges.put("Room2_3",new ArrayList<>());
        ranges.get("Room2_3").add((4810));
        ranges.get("Room2_3").add(5140);

        ranges.put("Room2_4",new ArrayList<>());
        ranges.get("Room2_4").add((4790));
        ranges.get("Room2_4").add(10);


        ranges.put("Room3_1",new ArrayList<>());
        ranges.get("Room3_1").add((2720));
        ranges.get("Room3_1").add(3050);

        ranges.put("Room3_2",new ArrayList<>());
        ranges.get("Room3_2").add((690));
        ranges.get("Room3_2").add(1040);

        ranges.put("Room3_3",new ArrayList<>());
        ranges.get("Room3_3").add((4790));
        ranges.get("Room3_3").add(1040);

        ranges.put("Room3_4",new ArrayList<>());
        ranges.get("Room3_4").add((700));
        ranges.get("Room3_4").add(3050);

        ranges.put("Room3_5",new ArrayList<>());
        ranges.get("Room3_5").add((660));
        ranges.get("Room3_5").add(3050);

        ranges.put("Room3_6",new ArrayList<>());
        ranges.get("Room3_6").add((6840));
        ranges.get("Room3_6").add(3050);

        ranges.put("Room3_7",new ArrayList<>());
        ranges.get("Room3_7").add((700));
        ranges.get("Room3_7").add(1040);

        ranges.put("Room3_8",new ArrayList<>());
        ranges.get("Room3_8").add((680));
        ranges.get("Room3_8").add(1035);

        ranges.put("Room3_9",new ArrayList<>());
        ranges.get("Room3_9").add((200));
        ranges.get("Room3_9").add(3080);

        ranges.put("Room4_1",new ArrayList<>());
        ranges.get("Room4_1").add((10000));
        ranges.get("Room4_1").add(3080);

        ranges.put("Room4_2",new ArrayList<>());
        ranges.get("Room4_2").add((3380));
        ranges.get("Room4_2").add(3080);

        ranges.put("Room4_3",new ArrayList<>());
        ranges.get("Room4_3").add((10000));
        ranges.get("Room4_3").add(3080);

        ranges.put("Room4_4",new ArrayList<>());
        ranges.get("Room4_4").add((2700));
        ranges.get("Room4_4").add(7180);

        ranges.put("Room4_5",new ArrayList<>());
        ranges.get("Room4_5").add((-64));
        ranges.get("Room4_5").add(280);

        ranges.put("Room4_6",new ArrayList<>());
        ranges.get("Room4_6").add((10000));
        ranges.get("Room4_6").add(3100);

        ranges.put("Room4_7",new ArrayList<>());
        ranges.get("Room4_7").add((685));
        ranges.get("Room4_7").add(1040);

        ranges.put("Room5_1",new ArrayList<>());
        ranges.get("Room5_1").add((2750));
        ranges.get("Room5_1").add(3095);

        ranges.put("Room5_2",new ArrayList<>());
        ranges.get("Room5_2").add((6840));
        ranges.get("Room5_2").add(1040);

        ranges.put("Room5_3",new ArrayList<>());
        ranges.get("Room5_3").add((15030));
        ranges.get("Room5_3").add(1050);

        ranges.put("Room5_4",new ArrayList<>());
        ranges.get("Room5_4").add((2730));
        ranges.get("Room5_4").add(3080);

        ranges.put("Room5_5",new ArrayList<>());
        ranges.get("Room5_5").add((2730));
        ranges.get("Room5_5").add(3080);

        ranges.put("Room5_6",new ArrayList<>());
        ranges.get("Room5_6").add((2730));
        ranges.get("Room5_6").add(3080);

        ranges.put("Room1_2",new ArrayList<>());
        ranges.get("Room1_2").add((2730));
        ranges.get("Room1_2").add(3080);

        ranges.put("Room1_3",new ArrayList<>());
        ranges.get("Room1_3").add((2730));
        ranges.get("Room1_3").add(3080);

        ranges.put("Room1_4",new ArrayList<>());
        ranges.get("Room1_4").add((2730));
        ranges.get("Room1_4").add(3080);

        ranges.put("Room0_1",new ArrayList<>());
        ranges.get("Room0_1").add((2730));
        ranges.get("Room0_1").add(3080);

     //   currentRoom="Room0_1";


        window = new Window(WIDTH, HEIGHT, "The Reign of Big Chungus", this);

        //loadSave();
        tbHandler = new TBHandler();
        currentState=null;

        setHandler( new Handler(this));


        //loadSave();


        gp=new GamePlayer(0,0,0,0,ID.GamePlayer,getHandler(),this);
        spawn=new Spawn(getHandler(), this);



        timeOfLastShot=0;
        timeOfLastShot2=0;
        simonFirst=true;
        spot=0;
        spot2=0;
        spotHolder=0;




        //loadSave();
        setCurrentState(STATE.TitleScreen);
        AudioPlayer.getMusic("TitleScreen").loop();

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }



    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void run() {

        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(running) {
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running) {
                render();
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
            }
        }
        stop();
    }


    private void tick() {
        try{

            if(currentState==null){
                setLastX(900);
                setLastY(200);
                setInv(new Inventory(this));
                setMagic(new Magic());
                setCurrentState(STATE.FirstArea);
                setCurrentArea("wae");
                camera = new Camera(0,0,this);
                setCurrentRoom("Room0_1");
                camera.updateRange(getCurrentRoom());
                setPlayer(new Player(900, 200, 92, 23, getHandler(), this, ID.Player, 2,getInv(), getMagic()));
                getPlayer().setLimited(false);
                getPlayer().setInv(getInv());
                getPlayer().setMagic(getMagic());


                //autokill =new Spells("Auto kill", "Yeet and Delete", player.getMagic(),0,0,0,0,ID.Spell,this,0,false,false,0,0);
              //  player.getMagic().addItem(autokill);

                // items and spells addition
                setRoom2_2_Basic_Heal(  new Spells("Basic Heal", "Heals 5 HP uses 5 Mana","", getPlayer().getMagic(),800,150,32,32,ID.Spell,this,5,true,false,5,0));
                setRoom2_3_BoogieWoogie(new Spells("Boogie Woogie", "Increase attack by 5. Uses 10 mana","", getPlayer().getMagic(),660,2780,32,32,ID.Spell,this,10,false,false,0,0));
                setRoom2_3_YeetusDeletus(new Spells("Yeetus Deletus", "Will do 15 damage. 15 Mana","", getPlayer().getMagic(),5760,5860,32,32,ID.Spell,this,15,false,true,0,15));
                setRoom2_3_EnnieMeenieMinieMo(new Spells("Enie Meni Minie Mo", "May hurt you, may hurt the enemy. 20 Mana","", getPlayer().getMagic(),5915,115,32,32,ID.Spell,this,20,false,false,0,0));
                setRoom2_3_EmptyWaterBottle(new Items("Empty Water Bottle","Yeet it for 5-10 damage",900,1700,32,32,ID.Item,this,0,0,false,false,0,0));
                setRoom2_3_LiptonIcedTea( new Items("Lipton Iced Tea","Heals 14",5900,2020,32,32,ID.Item,this,0,0,true,false,14,0));
                setRoom2_3_Tidepod(new Items("Tide Pod","Heals 5",80,5920,32,32,ID.Item,this,0,0,true,false,5,0));

                setRoom3_9_AliensHat(new Items("Alien Hat", "+20 defense","A hat that says I love aliens.",450,3760,16,16,ID.Item,this,0,0,false,false,20,0));
                setRoom3_9_LightSaber(new Items("Light Saber", "+20 attack","An actual hecking light saber.",450,3760,16,16,ID.Item,this,0,0,false,false,0,20));
                setRoom3_1_ManaThrow(new Spells("Mana Throw", "Uses all mana. 1 mana = 1 damage point","", getPlayer().getMagic(),2055,1780,32,32,ID.Spell,this,0,false,true,0,0));
                setRoom3_2_WizardWand(new Items("Wizard Wand","+5 attack","A basic wizard's wand",1770,1750,32,32,ID.Item,this,0,0,false,false,0,5));
                setRoom3_5_Earthquake(new Spells("Earthquake", "5 damage 20 Mana, ","(50 damage against Electric types).",getPlayer().getMagic(),980,760,32,32,ID.Spell,this,20,false,true,0,0));
                setRoom3_5_WizardCloak(new Items("Wizard Cloak", "+5 defense","A typical wizard's cloak.",700,960,32,32,ID.Item,this,0,0,false,false,5,0));
                setRoom3_7_ChickenNugget(new Items("Chicken Nugget", "Adds 30% of your health back","A tasty nugget",1780,275,32,32,ID.Item,this,0,0,true,false,(int)(getPlayer().getMaxHealth()*.3),0));
                setRoom3_8_InstaProtein(new Items("Insta Protein", "+15 attack for this battle","Will taste gross but is worth it",1760,275,32,32,ID.Item,this,0,0,false,false,0,0));
                setRoom3_2_FocusBeam(new Spells("Focus Beam", "Double Damage with 15 mana","Focus your energy!", getPlayer().getMagic(),1150,650,32,32,ID.Spell,this,15,false,true,0,0));
                setRoom3_6_BigBopper(new Items("Big Bopper","30 HP heal","Overpriced ice cream truck food",2100,3820,32,32,ID.Item,this,0,0,true,false,30,0));
                setRoom3_6_SpaceFood(new Items("Space Food", "Not quite sure...", "Is this even space food?",2620,120,32,32,ID.Item,this,0,0,true,false,getPlayer().getMaxHealth(),0));

                setRoom4_1_UnoReverse(new Items("Uno Reverse", "Reverses an attack for double the damage","Enemy attack times 2 against the enemy",2820,1380,16,16,ID.Item,this,0,0,false,true,0,0));
                setRoom4_2_Nokia(new Items("Nokia", "+25 attack","An unbreakable phone that can break bones.",480,3740,16,16,ID.Item,this,0,0,false,false,0,25));
                setRoom4_3_BunnyEars(new Items("Bunny Ears", "+30 defense","Suspiciously odd looking ears",2515,3480,16,16,ID.Item,this,0,0,false,false,30,0));
                setRoom4_3_Laser(new Spells("Laser", "20-40 damage 30 mana","Pew pew pew pew",getPlayer().getMagic(),1355,2052,16,16,ID.Spell,this,30,false,true,0, ThreadLocalRandom.current().nextInt(20,41)));
                setRoom4_3_TakeItBackNowYall(new Spells("Take it back now", "Your max health - your health = damage","y'alllll for 29 mana",getPlayer().getMagic(),266,592,16,16,ID.Spell,this,29,false,true,0,0));
                setRoom4_4_BlueEyes(new Items("YuGiOh Card", "50 Damage","A Blue Eyes White Dragon",3760,160,16,16,ID.Item,this,0,0,false,true,0,50));
                setRoom4_4_C_I_T_Shirt(new Items("CIT Shirt", "+0 defense","Counselor In Training Shirt",3620,7760,16,16,ID.Item,this,0,0,false,false,20,0));
                setRoom4_4_Screech(new Spells("Screech", "1-200 damage 75 mana","Releases a loud sound to hurt ears.",getPlayer().getMagic(),400,4260,16,16,ID.Spell,this,75,false,true,0,ThreadLocalRandom.current().nextInt(1,201)));
                setRoom4_4_Skert(new Spells("Skert", "1-20 damage 9 mana","Might barely do anything idk lol.",getPlayer().getMagic(),2280,4080,16,16,ID.Spell,this,9,false,true,0,ThreadLocalRandom.current().nextInt(1,21)));
                setRoom5_6_OrbR(new Items("Orb Of Resurrection", "Restore all of the dead memes","Would go against the wizards",700,600,32,32,ID.Item,this,0,0,false,false,0,0));
                setRoom5_6_OrbD(new Items("Orb Of Destruction", "Kills off every meme in existence","Would go with the wizards",1100,600,32,32,ID.Item,this,0,0,false,false,0,0));
                setRoom5_5_Bleach(new Items("Bleach", "Fully restores mana", "Increases max mana by 25",200,880,32,32,ID.Item,this,0,0,false,false,0,0));
                setRoom5_4_ChickenTender(new Items("Chicken Tender", "Fully restores HP", "Increases max HP by 25",3640,3730,32,32,ID.Item,this,0,0,false,false,0,0));
                setRoom5_3_ManaRestore(new Spells("Mana Restore", "Restores mana by 15% only in battle","No mana cost",getPlayer().getMagic(),4610,860,32,32,ID.Spell,this,0,false,false,0,0));
                setRoom5_3_TheGodPiercer(new Spells("The God Piercer", "Lowers an enemy's attack by 10","50 mana",getPlayer().getMagic(),15230,220,32,32,ID.Spell,this,50,false,false,0,0));
                getRoom5_6_OrbR().isVisible=true;
                getRoom5_6_OrbD().isVisible=true;
/*
                player.getInv().addItem(Room3_9_AliensHat);
                player.getInv().addItem(Room3_9_LightSaber);
                player.getMagic().addItem(Room3_1_ManaThrow);
                player.getInv().addItem(Room3_2_WizardWand);
                player.getMagic().addItem(Room3_2_FocusBeam);
                player.getMagic().addItem(Room3_5_Earthquake);
                player.getInv().addItem(Room3_5_WizardCloak);
                player.getInv().addItem(Room3_7_ChickenNugget);
                player.getInv().addItem(Room3_8_InstaProtein);
                player.getInv().addItem(Room3_6_BigBopper);
                player.getInv().addItem(Room3_6_SpaceFood);
                player.getInv().addItem(Room4_1_UnoReverse);
                player.getInv().addItem(Room4_2_Nokia);
                player.getInv().addItem(Room4_3_BunnyEars);
                player.getInv().addItem(Room4_4_BlueEyes);
                player.getInv().addItem(Room4_4_C_I_T_Shirt);
                player.getMagic().addItem(Room4_3_Laser);
                player.getMagic().addItem(Room4_4_Screech);
                player.getMagic().addItem(Room4_4_Skert);
                player.getMagic().addItem(Room4_3_TakeItBackNowYall);

                player.getInv().addItem(Room5_5_Bleach);
                player.getInv().addItem(Room5_4_ChickenTender);
                player.getMagic().addItem(Room5_3_ManaRestore);
                player.getMagic().addItem(Room5_3_TheGodPiercer);


                player.getInv().addItem(Room2_3_LiptonIcedTea);
                player.getInv().addItem(Room2_3_Tidepod);
                player.getMagic().addItem(Room2_2_Basic_Heal);
                player.getMagic().addItem(Room2_3_YeetusDeletus); */
                aliensFreed=false;
                A51_Switch1=false;
                A51_Switch2=false;
                A51_Switch3=false;
                A51_Switch4=false;

                A51_RSwitch1=new NPC(750,500,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch1);
                A51_RSwitch2=new NPC(3840,320,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch2);
                A51_RSwitch3=new NPC(245,1400,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch3);
                A51_RSwitch4=new NPC(80,2200,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch4);

                A51_Switch5=false;
                A51_Switch6=false;
                A51_Switch7=false;
                A51_Switch8=false;

                // 3-6
                A51_RSwitch5=new NPC(4200,2740,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch5);
                // 3-7
                A51_RSwitch6=new NPC(500,1760,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch6);
                // 3-8
                A51_RSwitch7=new NPC(280,1755,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch7);
                //3-6
                A51_RSwitch8=new NPC(6300,3600,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch8);

                A51_GSwitch1=new NPC(1800,80,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.greenSwitch);

                who1=new NPC(1730,3780,128,128,getHandler(),this,ID.NPC,1,tbHandler,"Do we know eachother? It feels like we do. But from where? I can't put my finger on it. Although I don't have any fingers. I'm kinda a blob of stuff.",getPlayer(),ID.WHO1);;
                who2=new NPC(1540,100,128,128,getHandler(),this,ID.NPC,1,tbHandler,"There are some weird things going on around here... For some reason I feel like I shouldn't be helping you but uh.. you seem nice!",getPlayer(),ID.WHO2);
                whoT1=new TextBox(who1,"Woah woah buddy put your wand down! I'm not one of them bud. Actually I don't know what I am. I can't really remember too much of anything. My name or why I feel like I should know who you are.",1730,3780,1,1500,ID.TextBox,tbHandler);
                whoT2=new TextBox(who2,"Hey bud, so uh. The one thing I know is that this place is wack. Everything is a bit off here so... I have an idea. I'll give you a walkie talkie so I can help you out! We could even... be friends!",1540,100,1,1500,ID.TextBox,tbHandler);
                barrier=new Block(7280,64, 64, 675, getHandler(), this, ID.sideRail);
                specialGameWin=false;

                C3=new Block(1310,204,314,61,getHandler(),this,ID.whiteKey,ID.C3);
                C3S=new Block(1355,204,169,33,getHandler(),this,ID.blackKey,ID.C3S);
                D3=new Block(1371,204,314,61,getHandler(),this,ID.whiteKey,ID.D3);
                D3S=new Block(1420,204,169,33,getHandler(),this,ID.blackKey,ID.D3S);
                E3=new Block(1432,204,314,61,getHandler(),this,ID.whiteKey,ID.E3);
                F3=new Block(1493,204,314,61,getHandler(),this,ID.whiteKey,ID.F3);
                F3S=new Block(1540,204,169,33,getHandler(),this,ID.blackKey,ID.F3S);
                G3=new Block(1554,204,314,61,getHandler(),this,ID.whiteKey,ID.G3);
                G3S=new Block(1600,204,169,33,getHandler(),this,ID.blackKey,ID.G3S);
                A4=new Block(1615,204,314,61,getHandler(),this,ID.whiteKey,ID.A4);
                A4S=new Block(1660,204,169,33,getHandler(),this,ID.blackKey,ID.A4S);
                B4=new Block(1676,204,314,61,getHandler(),this,ID.whiteKey,ID.B4);
                C4=new Block(1737,204,314,61,getHandler(),this,ID.whiteKey,ID.C4);
                C4S=new Block(1785,204,169,33,getHandler(),this,ID.blackKey,ID.C4S);
                D4=new Block(1798,204,314,61,getHandler(),this,ID.whiteKey,ID.D4);
                D4S=new Block(1845,204,169,33,getHandler(),this,ID.blackKey,ID.D4S);
                E4=new Block(1857,204,314,61,getHandler(),this,ID.whiteKey,ID.E4);

                greenSimon=new Block(1400,400,250,250,getHandler(),this,ID.Simon,ID.lightGreen);
                blueSimon=new Block(1400,1000,250,250,getHandler(),this,ID.Simon,ID.lightBlue);
                yellowSimon=new Block(2000,1000,250,250,getHandler(),this,ID.Simon,ID.lightYellow);
                redSimon=new Block(2000,400,250,250,getHandler(),this,ID.Simon,ID.lightRed);

                knuckles1 = new Enemy(5760, 385, 100, 52, getHandler(), this, ID.Knuckles, 2, tbHandler, "Ugandan Knuckles", getPlayer(), ID.Knuckles,20,20,4,tex.Knuckles_BattleForm,Color.red,100);
                bigChungus = new Enemy(780, 130, 433, 225, getHandler(), this, ID.BigChungus, 7, tbHandler, "Big Chungus", getPlayer(), ID.BigChungus,100,100,4,tex.BigChungusBF[0],Color.gray,1000);
                pikachu=new Enemy(675,40,125,77,getHandler(),this,ID.Pikachu,1,tbHandler,"Surprised Pikachu",getPlayer(),ID.Pikachu,50,50, 4,tex.PikachuBF[0],Color.yellow,250);
                antihero=new Enemy(675,40,getPlayer().getHeight(),getPlayer().getWidth(),getHandler(),this,ID.AntiHero,1,getTbHandler(),"The Antihero",getPlayer(),ID.AntiHero, (int)(getPlayer().getAttack()*1.5), getPlayer().getDefense()*2, 4, tex.AntiHeroBF, Color.white,getPlayer().getHealth()*2);

                knuckles1.setRunnable(false);
                bigChungus.setRunnable(false);
                pikachu.setRunnable(false);
                antihero.setRunnable(false);
                pianoBlock= new Block(2035,110,5,225,getHandler(),this,ID.Tree2);
                simonBlock= new Block(1700,125,5,225,getHandler(),this,ID.Tree2);


                simonKeysList.add("G");
                simonKeysList.add("GG");
                simonKeysList.add("GGB");
                simonKeysList.add("GGBY");
                simonKeysList.add("GGBYR");
                simonKeysList.add("GGBYRR");
                simonKeysList.add("GGBYRRY");
                simonKeysList.add("GGBYRRYG");
                simonKeysList.add("GGBYRRYGY");
                simonKeysList.add("GGBYRRYGYR");

                bc1=new TextBox(bigChungus,"My people are gone. Do you realize what you have done?",0,1800,1,10000,ID.TextBox,tbHandler);
                bc2=new TextBox(bigChungus,"Your people have killed us. I'm done talking to you.",0,1600,1,10000,ID.TextBox,tbHandler);
                bc3=new TextBox(bigChungus,"I want to talk to the person controlling your actions. YOU. Why did you do this? All of my friends are dead. I tried to experiment with making new life but it failed as well.",0,1400,1,10000,ID.TextBox,tbHandler);
                bc4=new TextBox(bigChungus,"I am now a king without people to lead. When I realized the wizards are using the dark magic to avoid death, I knew our demise would be inevitable. ",0,1300,1,10000,ID.TextBox,tbHandler);
                bc5=new TextBox(bigChungus,"My friends are gone. I'm afraid this is where your journey ends. I will defeat you over and over until you give up.",0,1000,1,10000,ID.TextBox,tbHandler);

                for(int i = 0; i<10; i++){
                    pianoKey[i]=false;
                }
                pianoPuzzle=false;

                for(int i =0; i<55; i++){
                    simonSays[i]=false;
                }
                simonPuzzle=false;


            }if(currentState==STATE.FirstArea && !Switch){
               // handler.clear();


                if(getCurrentBattle()==null)
                    setPlayer(new Player(getLastX(), getLastY(), 92, 23, getHandler(), this, ID.Player, 2,getInv(), getMagic()));
                else{
                    getPlayer().setLimited(true);
                    setCurrentBattle(null);
                }
                //player.setLimited(false);

                // only for rooms that you can get into a battle in
                // NOT passive rooms
                try{
                    if(currentRoom.equals("Room1_1")) {
                        currentLevel = tex.SS_FirstArea.grabImage(1, 1, 64, 64);
                        //currentRoom = "Room1_1";

                        loadLevel(getCurrentRoom());
                        //TPoser tposer = new TPoser(900,900, 184, 184, handler, this, ID.TPoser, 7, tbHandler, "DOY DOY DOY", player, true);
                        //handler.addObject(tposer);

                        //npc = new NPC(400, 200, 19, 74, handler, this, ID.NPC, 2, tbHandler, "asdfasdfasdfasdfja;sldkjfaslkdjfasldf;a", player, ID.TheLastEntity);
                        //System.out.println("here");

                        //handler.addObject(knuckles1);
                        //Pikachu pikachu = new Pikachu(800, 500, 96, 48, handler, this, ID.Pikachu, 2, tbHandler, "Pikaaachuuu", player, true);
                        tex = getInstance();
                        bigChungus = new Enemy(780, 130, 433, 225, getHandler(), this, ID.BigChungus, 7, tbHandler, "Big Chungus", getPlayer(), ID.BigChungus,100,100,4,tex.BigChungusBF[0],Color.gray,1000);
                        bigChungus.setRunnable(false);
                        // handler.addObject(pikachu);
                        //handler.addObject(npc);
                        //handler.addObject(bigChungus);
                       // handler.addObject(player);
                        //handler.addObject(new Transition(200, 1000, 64, 64, ID.Transition, "First"));
                        so = new SaveObject(725, 25, 64, 64);
                        getHandler().addObject(so);

                    }
                    getHandler().clear();
                    loadLevel(getCurrentRoom());
                    //getHandler().addObject(getPlayer());
                    setKeyInput(new KeyInput(getHandler(), getPlayer(), getTbHandler(), getPlayer().getInv(),getGp(),this));


                    this.addKeyListener(getKeyInput());
                }catch (Exception e){
                    e.printStackTrace();
                }











                Switch=true;
            }else if(currentState==STATE.FirstArea && Switch){
                getPlayer().setHealth((int)clamp(getPlayer().getHealth(),0,getPlayer().getMaxHealth()));
                getPlayer().setMana((int)clamp(getPlayer().getMana(),0,getPlayer().getMaxMana()));

                for(int i = 0; i < getHandler().object.size(); i++){
                    try{
                       // System.out.println(i);
                       // System.out.println("sepc obj: "+getHandler().object.get(i));
                        if(getHandler().object.get(i).getId() == ID.Player){
                            getCamera().tick(getHandler().object.get(i));
                        }
                    }catch (Exception e){

                        e.printStackTrace();
                    }

                }




                getHandler().tick();
                tbHandler.tick();
                try{
                    //System.out.println(magic);
                    //System.out.println(inv);
                    if(getPlayer().getInv().getOpen()){
                        getPlayer().getInv().tick();

                    }else if(getPlayer().getMagic().getOpen())
                        getPlayer().getMagic().tick();
                }catch (Exception e){
                    getPlayer().setInv(inv);
                    getPlayer().setMagic(magic);
                }


               // for(int i = 0; i<55; i++){
               //     System.out.println("Here:           "+simonSays[i]);
              //  }

                pianoPuzzle=allContains(pianoKey);




            }else if(currentState==STATE.Battle && !Switch){
                getHandler().clear();
                this.removeKeyListener(keyInput);

                Switch = true;
                //create new battle object or get the battle object from player (defined by collision)

            }else if(currentState==STATE.Battle && Switch ){
                getPlayer().setHealth((int)clamp(getPlayer().getHealth(),0,getPlayer().getMaxHealth()));
                getPlayer().setMana((int)clamp(getPlayer().getMana(),0,getPlayer().getMaxMana()));
                getHandler().tick();
                currentBattle.tick();
            }else if(currentState==STATE.GameOver && !Switch){

            }else if(currentState==STATE.GameOver && Switch){

            }else if(currentState==STATE.Normal && !Switch){
               // System.out.println(currentLevel);
               // handler.clear();
              //  loadLevel(getCurrentRoom());


                //setCurrentBattle(null);



               // player = new Player(player.getX(), player.getY(), 92, 25, handler, this, ID.Player, 2,inv, magic);
               // handler.addObject(player);
               // camera = new Camera(0,0,this);
              //  camera.updateRange(currentRoom);
              //  this.addKeyListener(new KeyInput(handler, player, tbHandler, player.getInv(),getGp(),this));
              //  Switch=true;

            }else if(currentState==STATE.Normal && Switch){
            /*   for(int i = 0; i < handler.object.size(); i++){
                    if(handler.object.get(i).getId() == ID.Player){
                        camera.tick(handler.object.get(i));
                    }
                }
                handler.tick();
                tbHandler.tick();

                if(player.getInv().getOpen()){
                    player.getInv().tick();
                }else if(player.getMagic().getOpen())
                    player.getMagic().tick();*/

            }else if(currentState==STATE.SpecialGame){
                getHandler().tick();
                spawn.tick();
                if(getGameHealth()<=0){
                    setCurrentState(STATE.GameOver);
                    setSwitch(false);
                    setPlayingGame(false);
                }
                if(getScore()>=5000){
                    specialGameWin=true;
                    getHandler().clear();
                    AudioPlayer.getMusic("GAME").stop();
                    currentState=STATE.FirstArea;
                    loadLevel(getCurrentRoom());
                    getHandler().addObject(getPlayer());
                    setPlayingGame(false);
                }

                // tick hud
                setGameHealth((int)(Game.clamp(getGameHealth(), 0,10)));



                score = getScore();

            }
        }catch(Exception e){
            e.printStackTrace();
        }






    }

    private void render() {
        if(currentState==STATE.FirstArea && Switch){
            BufferStrategy bs = this.getBufferStrategy();

            if(bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();




            g.setColor(Color.black);
            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);


            Graphics2D g2d = (Graphics2D) g;

            g2d.translate(-camera.getX(), -camera.getY());




            getHandler().render(g);

            if(getCurrentRoom().equals("Room5_4")){
                fnt = new Font("Serif", Font.BOLD, 20);
                g.setFont(fnt);
                g.setColor(Color.orange);
                g.drawString("A",1335,440);
                g.drawString("S", 1400,440);
                g.drawString("D", 1455,440);
                g.drawString("F", 1515,440);
                g.drawString("G", 1580,440);
                g.drawString("H", 1635,440);
                g.drawString("J", 1700,440);
                g.drawString("K", 1760,440);
                g.drawString("L", 1820,440);
                g.drawString(";", 1880,440);
                g.setColor(Color.green.brighter().brighter().brighter().brighter());
                g.drawString("W", 1360,275);
                g.drawString("E", 1432,275);
                g.drawString("T", 1548,275);
                g.drawString("Y", 1608,275);
                g.drawString("U", 1670,275);
                g.drawString("O", 1795,275);
                g.drawString("P", 1855,275);



            }else if(getCurrentRoom().equals("Room5_5")){


                fnt = new Font("Serif", Font.BOLD, 54);

                g.setFont(fnt);
                g.setColor(Color.black);
                g.drawString("SIMON SAYS",1655,720);
                fnt = new Font("Serif", Font.BOLD, 25);
                g.setFont(fnt);
                String mytemp="";
                if(simonPuzzle){
                    g.setColor(Color.green);
                    mytemp="Completed";

                }else{
                    mytemp="Not Done";

                    g.setColor(Color.red);
                }

                g.drawString("Status: "+mytemp,1715,800);

                fnt = new Font("Serif", Font.BOLD, 54);
                g.setFont(fnt);
                g.setColor(Color.red);
                g.drawString("Q",1500,515);
                g.setColor(Color.green);

                g.drawString("W",2100,515);
                g.setColor(Color.orange);

                g.drawString("A",1500,1130);
                g.setColor(Color.MAGENTA);

                g.drawString("S",2120,1130);



                if(greenPressed+250<=System.currentTimeMillis()){
                   greenSimon.setsID(ID.lightGreen);

                }
                if(yellowPressed+250<=System.currentTimeMillis()){
                    yellowSimon.setsID(ID.lightYellow);


                }
                if(bluePressed+250<=System.currentTimeMillis())
                    blueSimon.setsID(ID.lightBlue);
                if(redPressed+250<=System.currentTimeMillis())
                    redSimon.setsID(ID.lightRed);


                //System.out.println("SPOT:               "+spot);

                highest=0;

                if(!simonPuzzle){
                    timeNow=System.currentTimeMillis();
                    time=timeNow-timeOfLastShot;
                    for(int j = 0; j<simonSays.length; j++){
                        if(!simonSays[j])
                            break;
                        else
                            highest++;

                    }
                    if(time>7500){
                        // init clause for first time

                        if(simonFirst){

                            spot=0;

                            for(int i = 0; i<simonSays.length; i++){
                                if(!simonSays[i]){
                                    spot2=i;
                                    break;
                                }
                            }
                            if(spot2==0)
                                spot2=0;
                            else if(spot2==1 || spot2==2)
                                spot2=1;
                            else if(spot2==3 || spot2==4 || spot2==5)
                                spot2=2;
                            else if(spot2==6 || spot2==7 || spot2==8|| spot2==9)
                                spot2=3;
                            else if(spot2==10 || spot2==11 || spot2==12|| spot2==13|| spot2==14)
                                spot2=4;
                            else if(spot2==15 || spot2==16 || spot2==17|| spot2==18|| spot2==19|| spot2==20)
                                spot2=5;
                            else if(spot2==21 || spot2==22 || spot2==23|| spot2==24|| spot2==25|| spot2==26|| spot2==27)
                                spot2=6;
                            else if(spot2==28 || spot2==29 || spot2==30|| spot2==31|| spot2==32|| spot2==33|| spot2==34|| spot2==35)
                                spot2=7;
                            else if(spot2==36 || spot2==37 || spot2==38|| spot2==39|| spot2==40|| spot2==41|| spot2==42|| spot2==43|| spot2==44)
                                spot2=8;
                            else if(spot2==45 || spot2==46 || spot2==47|| spot2==48|| spot2==49|| spot2==50|| spot2==51|| spot2==52|| spot2==53|| spot2==54)
                                spot2=9;

                            currentPath=simonKeysList.get(spot2);




                            // define temp int to keep track of which button is next
                            // turn to false on last loop
                            simonFirst=!simonFirst;
                        }




                        timeNow2=System.currentTimeMillis();
                        time2=timeNow2-timeOfLastShot2;
                        if(time2>300){
                            char temp = currentPath.charAt(spot);
                            switch (temp){
                                case 'G':
                                    greenSimon.setsID(ID.green);
                                    greenPressed=System.currentTimeMillis();
                                    AudioPlayer.getSound("SimonGreen").play();

                                    //greenSimon.setsID(ID.lightGreen);
                                    break;
                                case 'B':
                                    blueSimon.setsID(ID.blue);
                                    //blueSimon.setsID(ID.lightBlue);
                                    bluePressed=System.currentTimeMillis();
                                    AudioPlayer.getSound("SimonBlue").play();

                                    break;
                                case 'Y':
                                    yellowSimon.setsID(ID.yellow);
                                    //yellowSimon.setsID(ID.lightYellow);
                                    yellowPressed=System.currentTimeMillis();
                                    AudioPlayer.getSound("SimonYellow").play();

                                    break;
                                case 'R':
                                    redSimon.setsID(ID.red);
                                    //redSimon.setsID(ID.lightRed);
                                    redPressed=System.currentTimeMillis();
                                    AudioPlayer.getSound("SimonRed").play();

                                    break;
                            }

                            timeOfLastShot2=timeNow2;
                            spot++;
                            if(spot>=currentPath.length()){
                                simonFirst=true;
                                timeOfLastShot=timeNow;
                                if(simonSays[simonSays.length-1]){
                                    getHandler().removeObject(simonBlock);
                                    simonPuzzle=true;

                                }


                            }
                        }



                    }
                }












            }else if(currentRoom.equals("Room5_6")){
                if(endgame){
                    if(getTbHandler().object.isEmpty()){

                        if(bigChungus.isDefeated() && hero){
                            getHandler().clear();
                            currentRoom="Room1_3";
                            getHandler().addObject(getPlayer());
                            getPlayer().getInv().clear();
                            getPlayer().setX(900);
                            getPlayer().setY(20);
                            loadLevel(getCurrentRoom());
                            save();

                        }else{
                            getHandler().removeObject(getBigChungus());
                            getHandler().removeObject(getRoom5_6_OrbR());
                            save();
                        }
                    }
                }




            }else if(getCurrentRoom().equals("Room0_1")){
                System.out.println(getPlayer().getLimited());
                if(!(getHandler().object.contains(getPlayer()))){
                    System.out.println("jajaj");
                    getHandler().addObject(getPlayer());

                }

              //  removeKeyListener(getKeyInput());
              //  setKeyInput(new KeyInput(getHandler(),getPlayer(),getTbHandler(),getPlayer().getInv(),getGp(),this));
              //  addKeyListener(getKeyInput());
            }
            g.setColor(Color.black);



            g2d.translate(camera.getX(), camera.getY());
            tbHandler.render(g);






            try{
                if(getPlayer().getInv().getOpen()){
                    getPlayer().getInv().render(g);
                    getPlayer().drawHealthBar(8,540,g);
                    getPlayer().drawManaBar(8,600,g);

                    fnt = new Font("Serif", 0, 20);
                    g.setFont(fnt);
                    g.setColor(Color.white);
                    g.drawString("HP", 8,530);
                    g.setColor(Color.white);

                    fnt = new Font("Serif", 1, 16);
                    g.setFont(fnt);
                    g.drawString(getPlayer().getHealth() + " / " + getPlayer().getMaxHealth(), 215, 565);



                    fnt = new Font("Serif", 0, 20);
                    g.setFont(fnt);
                    g.setColor(Color.white);
                    g.drawString("Mana", 8,595);
                    g.setColor(Color.white);

                    fnt = new Font("Serif", 1, 16);
                    g.setFont(fnt);
                    g.drawString(getPlayer().getMana() + " / " + getPlayer().getMaxMana(), 215, 620);

                }else if(getPlayer().getMagic().getOpen()){
                    getPlayer().getMagic().render(g);
                    getPlayer().drawHealthBar(8,540,g);
                    getPlayer().drawManaBar(8,600,g);

                    fnt = new Font("Serif", 0, 20);
                    g.setFont(fnt);
                    g.setColor(Color.white);
                    g.drawString("Mana", 8,595);
                    g.setColor(Color.white);

                    fnt = new Font("Serif", 1, 16);
                    g.setFont(fnt);
                    g.drawString(getPlayer().getMana() + " / " + getPlayer().getMaxMana(), 215, 620);

                    fnt = new Font("Serif", 0, 20);
                    g.setFont(fnt);
                    g.setColor(Color.white);
                    g.drawString("HP", 8,530);
                    g.setColor(Color.white);

                    fnt = new Font("Serif", 1, 16);
                    g.setFont(fnt);
                    g.drawString(getPlayer().getHealth() + " / " + getPlayer().getMaxHealth(), 215, 565);
                }
            }catch (Exception e){
                e.printStackTrace();
                getPlayer().setInv(new Inventory(this));

                getPlayer().setMagic(new Magic());
                //player.getMagic().addItem(new Spells("Auto kill", "Yeet and Delete", magic,0,0,0,0,ID.Spell,this,0,false,false,0,0));

            }







            g.dispose();
            bs.show();
        }else if(currentState==STATE.Battle && Switch){
            BufferStrategy bs = this.getBufferStrategy();

            if(bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();
            if(getPlayer().getBackwards()){
                g.setColor(Color.white);
            }else{
                g.setColor(Color.black);
            }

            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
            getHandler().render(g);
            currentBattle.render(g);

            g.dispose();
            bs.show();
        }else if(currentState==STATE.GameOver && Switch){
        	Switch=false;
        }else if(currentState == STATE.GameOver && !Switch){
        	 BufferStrategy bs = this.getBufferStrategy();

             if(bs == null) {
                 this.createBufferStrategy(3);
                 return;
             }
             Graphics g = bs.getDrawGraphics();
        	g.setColor(Color.black);
        	g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
        	g.setColor(Color.white);
        	fnt = new Font("Serif", 1, 125);
        	g.setFont(fnt);
        	g.drawString("Game Over" , 300, 250);
            fnt = new Font("Serif", 1, 100);

        	g.drawString("Press X to continue!",200,500);




        	g.dispose();
            bs.show();
        }else if(currentState==STATE.Normal && !Switch){
            BufferStrategy bs = this.getBufferStrategy();

            if(bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();




            g.setColor(Color.black);
            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);

        }else if(currentState==STATE.Normal && Switch){
            BufferStrategy bs = this.getBufferStrategy();

            if(bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();



            g.setColor(Color.black);
            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);


            Graphics2D g2d = (Graphics2D) g;

            g2d.translate(-camera.getX(), -camera.getY());

           // System.out.println("rendering");


            getHandler().render(g);
            g2d.translate(camera.getX(), camera.getY());
            tbHandler.render(g);

            if(getPlayer().getInv().getOpen()){
                getPlayer().getInv().render(g);
            }else if(getPlayer().getMagic().getOpen()){
                getPlayer().getMagic().render(g);
            }

        }else if(currentState==STATE.SpecialGame){
            BufferStrategy bs = this.getBufferStrategy();

            if(bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();




            g.setColor(Color.black);
            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
            getHandler().render(g);

            // hud rendering

            g.setColor(Color.gray);
            g.fillRect(15, 850, 200, 32);

            if(getGameHealth() > 5) {
                g.setColor(Color.green);

            }else if(getGameHealth() <= 5) {
                g.setColor(Color.yellow);

            }else {
                g.setColor(Color.red);
            }



            g.fillRect(15, 850,  getGameHealth() * 20, 32);

            g.setColor(Color.white);
            g.drawRect(15, 850, 200, 32);


            g.drawString("Score: "+  getScore(), 15, 900);
            g.drawString("Level: "+  getLevel(), 15, 915);




            g.dispose();
            bs.show();
        }else if(getCurrentState()==STATE.TitleScreen){
            BufferStrategy bs = this.getBufferStrategy();

            if(bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();

            removeKeyListener(getKeyInput());
            setKeyInput(new KeyInput(getHandler(),getPlayer(),getTbHandler(),getInv(),getGp(),this));
            addKeyListener(getKeyInput());


            g.setColor(Color.black);
            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);

            g.drawImage(tex.TitleScreen,0,0,1280,960,null);

            g.drawString("",0,0);


            g.dispose();
            bs.show();
        }else if(getCurrentState()==STATE.ControlScreen){
            BufferStrategy bs = this.getBufferStrategy();

            if(bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();




            g.setColor(Color.black);
            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);

            g.drawImage(tex.ControlScreen,0,0,1280,960,null);




            g.dispose();
            bs.show();
        }




    }

    public static float clamp(float var, float min, float max) {
        if(var >= max)
            return var = max;
        else if(var<= min)
            return var = min;
        else
            return var;
    }


    public static float clampJ(float var, float min, float max) {
        if(var >= max)
            return max;
        else if(var <= min)
            return min;
        else
            return var;
    }

    public static Textures getInstance(){
        return tex;
    }


    public static void main(String[] args) {
        new Game();

    }

    public void loadLevel(String room) {


        // Graphics g = bs.getDrawGraphics();
        BufferedImage image = rooms.get(room);
        //System.out.println(rooms.get(room));
        //System.out.println(tex.Room2_1);
        // System.out.println(image);
        int w = image.getWidth();
        int h = image.getHeight();


        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == 255 && green == 0 && blue == 0) {
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.GrayGround));
                } else if (green == 255 && red == 0 && blue == 0) {
                    handler.addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, handler, this, ID.BlackGround));
                } else if (blue == 255 && red == 0 && green == 0) {
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Pavement));
                } else if (red == 57 && green == 255 && blue == 50) {
                    // g.drawImage();
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Grass));
                    // grass
                } else if (red == 191 && green == 0 && blue == 149) {
                    //tree
                    getHandler().addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, getHandler(), this, ID.Tree));
                } else if (red == 2 && green == 2 && blue == 40) {
                    // invisible block to stop during ice puzzles???
                    getHandler().addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, getHandler(), this, ID.invisWall));


                } else if (red == 255 && green == 230 && blue == 0) {
                    getHandler().addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, getHandler(), this, ID.Mushroom));

                } else if (red == 255 && green == 111 && blue == 0) {
                    //getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.RedGround));

                } else if (red == 0 && green == 255 && blue == 100) {
                    getHandler().addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, getHandler(), this, ID.sideRail));
                } else if (red == 100 && green == 100 && blue == 0) {
                    getHandler().addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, getHandler(), this, ID.bottomRail));
                } else if (red == 0 && green == 100 && blue == 255) {
                    getHandler().addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, getHandler(), this, ID.Ice));
                } else if (red == 100 && green == 50 && blue == 50) {
                    getHandler().addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, getHandler(), this, ID.Tree2));

                } else if (red == 100 && green == 100 && blue == 100) {
                    getHandler().addObject(new Block((xx * 64) - 64, (yy * 64) - 64, 64, 64, getHandler(), this, ID.grave));

                }
            }
        }
        if (currentRoom.equals("Room1_1")) {
            getHandler().addObject(new NPC(1050, 500, 64, 64, getHandler(), this, ID.NPC, 3, tbHandler, "You... are the last of us. I am unable to continue to fight him. My last gift to you... the prophecy.", getPlayer(), ID.TheLastEntity));
            getHandler().addObject(new NPC(750, 500, 48, 32, getHandler(), this, ID.NPC, 3, tbHandler, "The Chosen One must travel through The Wae, The Labyrinth, sdrawkcaB ehT, and the Land of Dead Memes in order to..... (The rest is cut out) What will you choose?", getPlayer(), ID.Book));
            // transition object to room 2_2
            getHandler().addObject(new Transition(835, 1975, 32, 100, ID.Transition, "Room2_1", 950, 100, getPlayer(), true, "wae"));
            camera.updateRange("Room1_1");
            getHandler().addObject(new SaveObject(740,300,64,64));

         //   handler.addObject(new Enemy(700, 500, 92, 25, handler, this, ID.Spaget, 2, tbHandler, "Spaget", getPlayer(), ID.Spaget, 20, 20, 2, tex.SpagetBF, Color.green, 50));
       //     handler.addObject(new Enemy(800, 500, 96, 48, handler, this, ID.Arthur, 1, tbHandler, "Arthur", getPlayer(), ID.Arthur, 20, 20, 2, tex.ArthurBF, new Color(255, 224, 189), 40));


        } else if (currentRoom.equals("Room2_1")) {
            AudioPlayer.getMusic("WaeForest").loop();
            currentMusic="WaeForest";

            setBackground(Color.black);
            loadLevel(tex.Room2_1O);
            getHandler().addObject(new Transition(835, 0, 32, 300, ID.Transition, "Room1_1", 900, 1800, getPlayer(), false, "none"));
            getHandler().addObject(new Transition(0, 130, 70, 32, ID.Transition, "Room2_2", 1100, 130, getPlayer(), false, "wae"));
            getHandler().addObject(new Transition(900, 1925, 32, 300, ID.Transition, "Room2_3", 3000, 200, getPlayer(), true, "wae"));
            camera.updateRange("Room2_1");
        } else if (currentRoom.equals("Room2_2")) {

            try{
                setBackground(Color.black);
                loadLevel(tex.Room2_2O);
                // add transistion back lol
                getHandler().addObject(new Transition(1175, 105, 70, 32, ID.Transition, "Room2_1", 60, 155, getPlayer(), true, "wae"));

                camera.updateRange("Room2_2");
                getHandler().addObject(getRoom2_2_Basic_Heal());
            }catch(Exception e){
                setRoom2_2_Basic_Heal(new Spells("Basic Heal", "Heals 5 HP uses 5 Mana","", getPlayer().getMagic(),800,150,32,32,ID.Spell,this,5,true,false,5,0));
                getHandler().clear();
                loadLevel(getCurrentRoom());
            }



        } else if (currentRoom.equals("Room2_3")) {
            setBackground(Color.black);
            loadLevel(tex.Room2_3O);
            camera.updateRange("Room2_3");
            getHandler().addObject(new SaveObject(2760,340,64,64));

            getHandler().addObject(new Transition(2600, 100, 32, 700, ID.Transition, "Room2_1", 975, 1800, getPlayer(), true, "wae"));
            getHandler().addObject(new Transition(2050, 6025, 32, 400, ID.Transition, "Room2_4", 480, 100, getPlayer(), false, "wae"));
            // coords for room2_4 transistion
            // 2050,6025

            // item and spell additions

            getHandler().addObject(getRoom2_3_BoogieWoogie());
            getHandler().addObject(getRoom2_3_EmptyWaterBottle());
            getHandler().addObject(getRoom2_3_EnnieMeenieMinieMo());
            getHandler().addObject(getRoom2_3_LiptonIcedTea());
            getHandler().addObject(getRoom2_3_Tidepod());
            getHandler().addObject(getRoom2_3_YeetusDeletus());


        } else if (currentRoom.equals("Room2_4")) {
            AudioPlayer.getMusic("Area51").stop();
            AudioPlayer.getMusic("WaeForest").loop();
            currentMusic="WaeForest";


            getHandler().addObject(new Transition(0, 30, 32, 1000, ID.Transition, "Room2_3", 2250, 5900, getPlayer(), true, "wae"));

            if (!knuckles1.isDefeated()) {
                getHandler().addObject(knuckles1);
                getHandler().addObject(new TextBox(null, "CLUCK CLUCK CLUCK (I know who you are.)", 1120, 0, 1000, 16, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(null, "CLUCK CLUCK CLUCK (I know what you are going to try to do.)", 1740, 0, 1000, 16, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(null, "CLUCK CLUCK CLUCK (I will give you a choice to turn back now before it is too late.)", 2360, 0, 1000, 16, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(knuckles1, "CLUCK CLUCK CLUCK (I'm sorry, but I must protect him with my life)", 5180, 0, 1000, 16, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(knuckles1, "CLUCK CLUCK (I, Ugandan Knuckles will stop you. Approach me and seek destruction.)", 5560, 0, 1000, 16, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(getPlayer(), "The Wae Forest has been cleared! Knuckles has been set free of the hex. Let's keep moving into Area 51.", 5850, 0, 1000, 16, ID.TextBox, tbHandler));

            }

            getHandler().addObject(new SaveObject(5280, 200, 64, 64));


            getHandler().addObject(new Transition(6050, 0, 1000, 16, ID.Transition, "Room3_1", 50, 3540, getPlayer(), true, "51"));


            setBackground(Color.black);
            loadLevel(tex.Room2_4O);
            camera.updateRange("Room2_4");
            //handler.addObject(knuckles1);
        } else if (currentRoom.equals("Room3_1")) {
            AudioPlayer.getMusic("WaeForest").stop();
            AudioPlayer.getMusic("Area51").loop();
            currentMusic="Area51";


            //endangered=true;
            setBackground(Color.black);
            loadLevel(tex.Room3_1O);
            camera.updateRange("Room3_1");
            if (!aliensFreed) {
                getHandler().addObject(new NPC(3250, 550, 129, 159, getHandler(), this, ID.NPC, 1, tbHandler, "Help!", getPlayer(), ID.Alien1));
                getHandler().addObject(new NPC(3250, 1480, 180, 180, getHandler(), this, ID.NPC, 1, tbHandler, "Hey, you're the hero aren't you? How bout you help us out? Find the GREEN switch that takes off our electric collars and we are out of here! We promise it will be worth it if you help us out.", getPlayer(), ID.Alien2));
                getHandler().addObject(new NPC(3250, 2680, 150, 123, getHandler(), this, ID.NPC, 1, tbHandler, "I just wish I didn't have to stay here, could you help us out? My boss above me here can't hear or see well. But if you manage to get close to him, he can tell you how to get us out of here!", getPlayer(), ID.Alien3));
            }

            getHandler().addObject(new Transition(0, 3280, 1000, 16, ID.Transition, "Room2_4", 6000, 400, getPlayer(), false, "wae"));
            getHandler().addObject(new Transition(0, 0, 16, 1000, ID.Transition, "Room3_3", 90, 1800, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(3960, 320, 400, 20, ID.Transition, "Room3_4", 50, 675, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(3960, 1220, 400, 20, ID.Transition, "Room3_4", 50, 1305, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(3960, 2560, 400, 20, ID.Transition, "Room3_4", 50, 2045, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(3480, 4000, 32, 100, ID.Transition, "Room3_5", 980, 20, getPlayer(), true, "51"));
            getHandler().addObject(new NPC(200, 3160, 78, 85, getHandler(), this, ID.NPC, 1, tbHandler, "Hey what's up? Welcome to Area 51! I don't mind if you have a look around here. Just ya know. There are 4 red switches which can you hit to get to another sector if you want. Do NOT hit the green switch.", getPlayer(), ID.Guard1));
            getHandler().addObject(getRoom3_1_ManaThrow());
            getHandler().addObject(getA51_RSwitch2());


        } else if (currentRoom.equals("Room3_2")) {

            getHandler().addObject(new Transition(1940, 0, 256, 16, ID.Transition, "Room3_3", 50, 350, getPlayer(), true, "51"));
            setBackground(Color.black);
            loadLevel(tex.Room3_2O);
            camera.updateRange("Room3_2");
            getHandler().addObject(getRoom3_2_WizardWand());
            getHandler().addObject(getRoom3_2_FocusBeam());
            getHandler().addObject(getA51_RSwitch3());
            getHandler().addObject(new SaveObject(5730,500,64,64));


        } else if (currentRoom.equals("Room3_3")) {
            getHandler().addObject(new Transition(10, 260, 1000, 16, ID.Transition, "Room3_2", 1850, 90, getPlayer(), true, "51"));

            getHandler().addObject(new Transition(0, 1900, 16, 1000, ID.Transition, "Room3_1", 420, 25, getPlayer(), true, "51"));

            getHandler().addObject(new Transition(6050, 1280, 500, 16, ID.Transition, "Room3_4", 200, 200, getPlayer(), true, "51"));
            getHandler().addObject(new NPC(5770, 175, 77, 55, getHandler(), this, ID.NPC, 1, tbHandler, "Hey wassup? If you can find the four red switches then you can open this door... but I forgot where they are. Just don't hit any green switches if you find any.", getPlayer(), ID.Guard2));
            if (!(A51_Switch1 && A51_Switch2 && A51_Switch3 && A51_Switch4)) {
                getHandler().addObject(new Block(5700, 80, 64, 350, getHandler(), this, ID.sideRail));
            } else
                getHandler().addObject(new Transition(5700, 20, 32, 500, ID.Transition, "Room3_6", 7500, 3800, getPlayer(), true, "51"));
            setBackground(Color.black);
            loadLevel(tex.Room3_3O);
            camera.updateRange("Room3_3");


        } else if (currentRoom.equals("Room3_4")) {
            getHandler().addObject(new Transition(0, 200, 300, 1, ID.Transition, "Room3_3", 6000, 1325, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(0, 600, 300, 16, ID.Transition, "Room3_1", 3900, 500, getPlayer(), false, "51"));
            getHandler().addObject(new Transition(0, 1280, 300, 16, ID.Transition, "Room3_1", 3900, 1485, getPlayer(), false, "51"));
            getHandler().addObject(new Transition(0, 1960, 300, 16, ID.Transition, "Room3_1", 3900, 2665, getPlayer(), false, "51"));

            setBackground(Color.black);
            loadLevel(tex.Room3_4O);
            camera.updateRange("Room3_4");

            getHandler().addObject(getA51_RSwitch4());

        } else if (currentRoom.equals("Room3_5")) {
            getHandler().addObject(new Transition(800, 0, 8, 500, ID.Transition, "Room3_1", 3475, 3900, getPlayer(), true, "51"));
            setBackground(Color.black);
            loadLevel(tex.Room3_5O);
            camera.updateRange("Room3_5");
            getHandler().addObject(getRoom3_5_Earthquake());
            getHandler().addObject(getRoom3_5_WizardCloak());
            getHandler().addObject(getA51_RSwitch1());

        } else if (currentRoom.equals("Room3_6")) {
            getHandler().addObject(new Transition(0, 192, 400, 2, ID.Transition, "Room3_8", 1940, 95, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(120, 4000, 2, 300, ID.Transition, "Room3_7", 160, 60, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(7200, 3980, 8, 500, ID.Transition, "Room3_3", 5855, 80, getPlayer(), true, "51"));

            if (!(A51_Switch5 && A51_Switch6 && A51_Switch7 && A51_Switch8)) {
                getHandler().addObject(barrier);
                getHandler().addObject(new NPC(7300, 150, 92, 84, getHandler(), this, ID.NPC, 1, tbHandler, "Hiya hero. Sorry I can't remember the password to open the door... sorry bout that. If you can find 4 new red switches then it'll open up.", getPlayer(), ID.Guard3));
            } else {
                getHandler().addObject(new Transition(7300, 0, 8, 1000, ID.Transition, "Room3_9", 675, 3840, getPlayer(), true, "51"));
                getHandler().addObject(new NPC(7300, 150, 92, 84, getHandler(), this, ID.NPC, 1, tbHandler, "Oh nice you opened the door! I don't mind if you go through", getPlayer(), ID.Guard3));

            }
            getHandler().addObject(getRoom3_6_BigBopper());
            getHandler().addObject(getRoom3_6_SpaceFood());
            getHandler().addObject(getA51_RSwitch5());
            getHandler().addObject(getA51_RSwitch8());

            setBackground(Color.black);
            loadLevel(tex.Room3_6O);
            getHandler().addObject(new SaveObject(7860,180,64,64));

            camera.updateRange("Room3_6");

            // 3 new transitions

        } else if (currentRoom.equals("Room3_8")) {
            getHandler().addObject(new Transition(2000, 0, 300, 500, ID.Transition, "Room3_6", 40, 262, getPlayer(), true, "51"));

            setBackground(Color.black);
            loadLevel(tex.Room3_8O);
            camera.updateRange("Room3_8");
            getHandler().addObject(getRoom3_8_InstaProtein());
            getHandler().addObject(getA51_RSwitch7());


            // 3 new transitions

        } else if (currentRoom.equals("Room3_7")) {


            setBackground(Color.black);
            loadLevel(tex.Room3_7O);
            getHandler().addObject(getRoom3_7_ChickenNugget());
            camera.updateRange("Room3_7");
            getHandler().addObject(new Transition(0, 0, 1, 300, ID.Transition, "Room3_6", 240, 3860, getPlayer(), true, "51"));

            getHandler().addObject(getA51_RSwitch6());

            getHandler().addObject(getA51_GSwitch1());


            // 3 new transitions

        } else if (currentRoom.equals("Room3_9")) {
            AudioPlayer.getMusic("Area51").loop();
            AudioPlayer.getMusic("Backwards").stop();
            currentMusic="Area51";

            getHandler().addObject(new Transition(640, 4020, 1, 400, ID.Transition, "Room3_6", 7600, 100, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(640, 0, 1, 400, ID.Transition, "Room4_1", 2038, 3896, getPlayer(), true, "back"));

            setBackground(Color.black);
            loadLevel(tex.Room3_9O);
            camera.updateRange("Room3_9");

            if (!(pikachu.isDefeated())) {
                getHandler().addObject(pikachu);
                getHandler().addObject(new SaveObject(325, 200, 64, 64));
                getHandler().addObject(new TextBox(null, "Pika Pika (I see that you beat my friend, Knuckles)", 0, 3580, 1, 1500, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(null, "Pika Pika (He was a good friend of mine)", 0, 3100, 1, 1500, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(null, "Pika Pika (I really only have a single question for you, before you go through with this.)", 0, 2900, 1, 1500, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(null, "Pika Pika (Do you fully understand what you are doing? Or is what you have been seeing biased?)", 0, 2500, 1, 1500, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(null, "Pika Pika (You won't stop until we're all gone will you? At least stop feeding them lies.)", 0, 2000, 1, 1500, ID.TextBox, tbHandler));
                getHandler().addObject(new TextBox(pikachu, "Pika Pika (It doesn't seem like I'll stop you with my words. Although, I'm actually not surprised. I'm sorry I have to do this.)", 0, 1200, 1, 1500, ID.TextBox, tbHandler));
            }


            // 3 new transitions

            if (aliensFreed) {
                getHandler().addObject(new NPC(325, 3750, 92, 84, getHandler(), this, ID.NPC, 1, tbHandler, "Hey! Thanks for freeing us!!! As a token of our appreciation take this item right here! I hope it helps.", getPlayer(), ID.Alien2));

                getHandler().addObject(getRoom3_9_AliensHat());
            } else {
                getHandler().addObject(new NPC(325, 3750, 92, 84, getHandler(), this, ID.NPC, 1, tbHandler, "Hey! Thanks for not letting any aliens out. As a token of our appreciation take this item right here!", getPlayer(), ID.Guard1));

                getHandler().addObject(getRoom3_9_LightSaber());
            }


        } else if (currentRoom.equals("Room4_1")) {
            AudioPlayer.getMusic("Area51").stop();
            AudioPlayer.getMusic("Backwards").loop();
            currentMusic="Backwards";

            getHandler().addObject(new Transition(1732, 4016, 1, 400, ID.Transition, "Room3_9", 680, 40, getPlayer(), true, "51"));
            getHandler().addObject(new Transition(1520, 0, 1, 1000, ID.Transition, "Room4_2", 2040, 3800, getPlayer(), false, "back"));
            getHandler().addObject(getRoom4_1_UnoReverse());
            setBackground(Color.black);
            loadLevel(tex.Room4_1O);
            camera.updateRange("Room4_1");



            getHandler().addObject(who1);
            getHandler().addObject(who2);

            getHandler().addObject(new TextBox(who1,"Woah woah buddy put your wand down! I'm not one of them bud. Actually I don't know what I am. I can't really remember too much of anything. My name or why I feel like I should know who you are.",1730,3780,1,1500,ID.TextBox,tbHandler));
            getHandler().addObject(new TextBox(who2,"Hey bud, so uh. The one thing I know is that this place is wack. Everything is a bit off here so... I have an idea. I'll give you a walkie talkie so I can help you out! We could even... be friends!",1540,100,1,1500,ID.TextBox,tbHandler));
            getHandler().addObject(new SaveObject(1618,3636,64,64));

        } else if (currentRoom.equals("Room4_2")) {
            getHandler().addObject(new TextBox(who1, "BSSSH Huh that's weird. Looks like there's some ice round these parts. Try slipping on it to see what happens! BSSSH over.", 0, 3770, 1, 5000, ID.TextBox, tbHandler));
            getHandler().addObject(new Transition(1800, 3980, 1, 1000, ID.Transition, "Room4_1", 1840, 100, getPlayer(), true, "back"));
            getHandler().addObject(new Transition(0, 1, 1, 5000, ID.Transition, "Room4_3", 1906, 3812, getPlayer(), false, "back"));
            setBackground(Color.black);
            loadLevel(tex.Room4_2O);
            camera.updateRange("Room4_2");
            getHandler().addObject(getRoom4_2_Nokia());


        } else if (currentRoom.equals("Room4_3")) {
            getHandler().addObject(new Transition(0, 4000, 1, 5000, ID.Transition, "Room4_2", 1942, 38, getPlayer(), false, "back"));
            getHandler().addObject(new Transition(0, 0, 1, 10000, ID.Transition, "Room4_4", 2120, 7900, getPlayer(), true, "back"));

            getHandler().addObject(new TextBox(who1, "Hmmm... oh no! An ice puzzle!!!! That's not good!", 0, 3584, 1, 5000, ID.TextBox, tbHandler));
            setBackground(Color.black);
            loadLevel(tex.Room4_3O);
            camera.updateRange("Room4_3");
            getHandler().addObject(getRoom4_3_BunnyEars());
            getHandler().addObject(getRoom4_3_Laser());
            getHandler().addObject(getRoom4_3_TakeItBackNowYall());


        } else if (currentRoom.equals("Room4_4")) {
            setBackground(Color.black);
            loadLevel(tex.Room4_4O);
            camera.updateRange("Room4_4");
            getHandler().addObject(new TextBox(who1, "BSSSH heyo nice job on that ice puzzle! I have a feeling for some reason that someone is trying to stop you. I feel like I should know this. Hm I'm not sure. Sadly... there seems to be an invisible maze now.. oof", 0, 7800, 1, 10000, ID.TextBox, tbHandler));
            getHandler().addObject(new Transition(0, 8020, 1, 5000, ID.Transition, "Room4_3", 1875, 100, getPlayer(), false, "back"));
            getHandler().addObject(new Transition(0, 0, 2, 10000, ID.Transition, "Room4_5", 565, 1040, getPlayer(), false, "back"));
            getHandler().addObject(new Transition(945, 3080, 2, 64, ID.Transition, "Room4_7", 945, 1820, getPlayer(), false, "back"));
            getHandler().addObject(getRoom4_4_BlueEyes());
            getHandler().addObject(getRoom4_4_C_I_T_Shirt());
            getHandler().addObject(getRoom4_4_FullHeal());
            getHandler().addObject(getRoom4_4_Screech());
            getHandler().addObject(getRoom4_4_Skert());
            getHandler().addObject(new SaveObject(1920,7860,64,64));


        } else if (currentRoom.equals("Room4_5")) {
            getHandler().addObject(new Transition(0, 1210, 1, 10000, ID.Transition, "Room4_4", 2040, 100, getPlayer(), true, "back"));
            if (!specialGameWin) {
                getHandler().addObject(new Block(20, 20, 1, 1000, getHandler(), this, ID.Mushroom));
                getHandler().addObject(new TextBox(who1, "Wow you're really killing these puzzles! Looks like there's a computer there with an old game. I think the controls are arrows to move and space to shoot. Get 5000 points to open the next door!", 0, 1000, 1, 10000, ID.TextBox, getTbHandler()));
            } else {
                getHandler().addObject(new Transition(0, 0, 1, 10000, ID.Transition, "Room4_6", 1880, 3860, getPlayer(), false, "back"));
            }
            // this is the GAME ROOM WOOOO
            getHandler().addObject(new NPC(600, 600, 159, 115, getHandler(), this, ID.NPC, 3, getTbHandler(), "Beeep", getPlayer(), ID.Computer));


            setBackground(Color.black);
            loadLevel(tex.Room4_5O);
            camera.updateRange("Room4_5");
            getHandler().addObject(new SaveObject(265,220,64,64));


        } else if (currentRoom.equals("Room4_6")) {
            AudioPlayer.getMusic("Backwards").loop();
            AudioPlayer.getMusic("Dead").stop();
            currentMusic="Backwards";


            // ANTIHERO BOSS ROOM
            getHandler().addObject(new Transition(0, 4000, 1, 10000, ID.Transition, "Room4_5", 500, 100, getPlayer(), true, "back"));
            if (getAntihero() == null) {
                antihero = new Enemy(1880, 40, getPlayer().getHeight(), getPlayer().getWidth(), getHandler(), this, ID.AntiHero, 1, getTbHandler(), "The Antihero", getPlayer(), ID.AntiHero, (int) (getPlayer().getAttack() * 1.5), getPlayer().getDefense() * 2, 4, tex.AntiHeroBF, Color.white, getPlayer().getHealth() * 2);
                tex=Game.getInstance();
                antihero.Face=tex.AntiHero_Face[0];
            }
            tex=Game.getInstance();
            antihero.Face=tex.AntiHero_Face[0];

            if (!getAntihero().isDefeated()) {
                getHandler().addObject(new TextBox(null, "I'm not talking to you, sorcerer. The one controlling your actions. The one who can stop all of this. ", 0, 1000, 1, 10000, ID.TextBox, getTbHandler()));
                getHandler().addObject(new TextBox(null, "I don't think you quite understand what you are doing. Stop now and leave this place. ", 0, 800, 1, 10000, ID.TextBox, getTbHandler()));
                getHandler().addObject(new TextBox(null, "Fine. He figured words wouldn't stop you. So I suppose that's why I'm here. He decided that your worst enemy would be yourself. ", 0, 600, 1, 10000, ID.TextBox, getTbHandler()));
                getHandler().addObject(new TextBox(antihero, "I was created after a lot of failed experimentation. To serve one purpose. To stop you. Or at least stall you until He can figure out what to do. ", 0, 500, 1, 10000, ID.TextBox, getTbHandler()));
                getHandler().addObject(new TextBox(antihero, "I live in a paradox constantly. I either win just to fight you again. Or I lose and perish. No illusions here. You have to kill me to continue.", 0, 400, 1, 10000, ID.TextBox, getTbHandler()));
                getHandler().addObject(new TextBox(antihero, "After all, I am you. I can copy your moves, weapons, attack power, defeense. Everything. And even make it better! Plus ya know. Some fun to spice it up. You can figure it out.", 0, 300, 1, 1000, ID.TextBox, getTbHandler()));
                getHandler().addObject(new TextBox(antihero, "This is how things are now. Just me and you. Go ahead and save. You're going to need it. ", 0, 200, 1, 10000, ID.TextBox, getTbHandler()));
                getHandler().addObject(new TextBox(null, "Turn back NOW.", 0, 1500, 1, 10000, ID.TextBox, getTbHandler()));
                getHandler().addObject(new TextBox(getPlayer(), "You can't stop me! I know what I am doing is right! ", 0, 1400, 1, 10000, ID.TextBox, getTbHandler()));
                antihero = new Enemy(1880, 40, getPlayer().getHeight(), getPlayer().getWidth(), getHandler(), this, ID.AntiHero, 1, getTbHandler(), "The Antihero", getPlayer(), ID.AntiHero, (int) (getPlayer().getAttack() * 1.5), getPlayer().getDefense() * 2, 4, tex.AntiHeroBF, Color.white, getPlayer().getHealth() * 2);

                getHandler().addObject(antihero);
            } else {
                getHandler().addObject(new TextBox(who1, "BSSSSH Come in. Sir, come in! Hello? What's going on? What happened?", 0, 20, 1, 10000, ID.TextBox, tbHandler));

                getHandler().addObject(new TextBox(getPlayer(), "*Drops walkie talkie* Good. Let us enter into the last area. We are almost finished here.", 0, 10, 1, 10000, ID.TextBox, tbHandler));
                getHandler().addObject(new Transition(0, 0, 1, 10000, ID.Transition, "Room5_1", 1960, 3860, getPlayer(), true, "dead"));
            }


            setBackground(Color.black);
            loadLevel(tex.Room4_6O);
            camera.updateRange("Room4_6");
            getHandler().addObject(new SaveObject(1600,140,64,64));


        } else if (currentRoom.equals("Room4_7")) {
            // secret lore room yay
            getHandler().addObject(new Transition(900, 1980, 2, 128, ID.Transition, "Room4_4", 975, 3120, getPlayer(), false, "back"));
            getHandler().addObject(new NPC(965, 340, 39, 28, getHandler(), this, ID.NPC, 3, getTbHandler(), "Entry: The meme destroyers are coming. We can only stop their chosen one for so long. We're running out of options and they are lowering our population to such small levels. -BC", getPlayer(), ID.Book));
            getHandler().addObject(new NPC(700, 400, 39, 28, getHandler(), this, ID.NPC, 3, getTbHandler(), "Entry: My people believe in me, but the lengths I've had to go to ensure our safety is troubling. Unlike them, I don't lie to my people to get my way. -BC", getPlayer(), ID.Book));
            getHandler().addObject(new NPC(800, 500, 39, 28, getHandler(), this, ID.NPC, 3, getTbHandler(), "Entry: They are using black magic. Nothing is working against them. I've decided that the only way to stop him is to not let him achieve his goal until he quits. He is unkillable. He is immortal. -BC", getPlayer(), ID.Book));
            getHandler().addObject(new NPC(1100, 340, 39, 28, getHandler(), this, ID.NPC, 3, getTbHandler(), "Entry: The wizards think we are an abomination. They plan to wipe us out completely. I've sent my royal guards to stop him and they have only been slaughtered so far. I miss my friends. -BC", getPlayer(), ID.Book));

            setBackground(Color.black);
            loadLevel(tex.Room4_7O);
            camera.updateRange("Room4_7");
        } else if (currentRoom.equals("Room5_1")) {
            AudioPlayer.getMusic("Dead").loop();
            AudioPlayer.getMusic("Area51").stop();
            currentMusic="Dead";



            getHandler().addObject(new Transition(4000, 3000, 10000, 1, ID.Transition, "Room5_2", 95, 1500, getPlayer(), true, "dead"));

            setBackground(Color.black);
            loadLevel(tex.Room5_1O);
            camera.updateRange("Room5_1");
        } else if (currentRoom.equals("Room5_2")) {

            getHandler().addObject(new Transition(0, 1280, 10000, 1, ID.Transition, "Room5_1", 3930, 3155, getPlayer(), true, "dead"));
            getHandler().addObject(new NPC(150, 700, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Ugandan Knuckles", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(300, 500, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Joshua Giraffe", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(2000, 700, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Malario", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(2500, 1200, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Tposer", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(3000, 1500, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Fat Yoshi", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(4000, 900, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Dat Boi", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(1200, 900, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Kermit", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(2000, 500, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Bongo Cat", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(400, 500, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Yodel Boy", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(1000, 900, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Kazoo Kid... hint: my song", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(1200, 700, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Arthur", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(975, 1500, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Spaget", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(855, 600, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Zuckerburg", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(3500, 229, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Surprised Pikachu", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(963, 635, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Waluigi", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(5000, 800, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Spongebob", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(1700, 500, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Rave Crab", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(2500, 800, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Zuckerburg", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(3600, 345, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "RIP: Here lies Harambe", getPlayer(), ID.grave));

            getHandler().addObject(new NPC(2700, 480, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "1: D D High D", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(3400, 1340, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "2: A G# G", getPlayer(), ID.grave));
            getHandler().addObject(new NPC(4660, 900, 64, 96, getHandler(), this, ID.NPC, 2, getTbHandler(), "3: F D F G", getPlayer(), ID.grave));

            // 8100, 780;
            getHandler().addObject(new Transition(8100, 780, 10000, 1, ID.Transition, "Room5_3", 110, 770, getPlayer(), true, "dead"));


            setBackground(Color.black);
            loadLevel(tex.Room5_2O);
            camera.updateRange("Room5_2");
            getHandler().addObject(new SaveObject(255,220,64,64));

        } else if (currentRoom.equals("Room5_3")) {

            // top: 130, 440
            // mid: 630, 550
            // bottom: 1210,650
            getHandler().addObject(getRoom5_3_ManaRestore());
            getHandler().addObject(getRoom5_3_TheGodPiercer());

            getHandler().addObject(new Transition(0, 0, 10000, 1, ID.Transition, "Room5_2", 8020, 980, getPlayer(), true, "dead"));

            getHandler().addObject(new Transition(2210, 130, 440, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));
            getHandler().addObject(new Transition(2210, 1210, 650, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));

            getHandler().addObject(new Transition(3690, 1210, 650, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));
            getHandler().addObject(new Transition(3690, 630, 550, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));

            getHandler().addObject(new Transition(5030, 130, 440, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));
            getHandler().addObject(new Transition(5030, 630, 550, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));

            getHandler().addObject(new Transition(6370, 130, 440, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));
            getHandler().addObject(new Transition(6370, 1210, 650, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));

            getHandler().addObject(new Transition(7890, 630, 550, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));
            getHandler().addObject(new Transition(7890, 1210, 650, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));

            getHandler().addObject(new Transition(9530, 1210, 650, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));
            getHandler().addObject(new Transition(9530, 630, 550, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));

            getHandler().addObject(new Transition(12870, 130, 440, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));
            getHandler().addObject(new Transition(12870, 1210, 650, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));

            getHandler().addObject(new Transition(14530, 130, 440, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));
            getHandler().addObject(new Transition(14530, 1210, 650, 1, ID.Transition, "Room5_3", 110, 980, getPlayer(), true, "dead"));


            getHandler().addObject(new Transition(16300, 0, 10000, 1, ID.Transition, "Room5_4", 110, 3680, getPlayer(), true, "dead"));

            setBackground(Color.black);
            loadLevel(tex.Room5_3O);
            camera.updateRange("Room5_3");
        } else if (currentRoom.equals("Room5_4")) {
            AudioPlayer.getMusic("Dead").stop();
            currentMusic="none";

            getHandler().addObject(getRoom5_4_ChickenTender());
            getHandler().addObject(new Transition(0, 0, 1, 10000, ID.Transition, "Room5_5", 1880, 3869, getPlayer(), false, "dead"));

            getHandler().addObject(new Transition(0, 0, 10000, 1, ID.Transition, "Room5_3", 16260, 1029, getPlayer(), false, "dead"));
            if (!pianoPuzzle) {

                for (int i = 0; i < 10; i++) {
                    pianoKey[i] = false;
                }

                getHandler().addObject(pianoBlock);
            }

            getHandler().addObject(new Block(1310, 180, 24, 608, getHandler(), this, ID.backPiano));

            getHandler().addObject(C3);
            getHandler().addObject(D3);
            getHandler().addObject(E3);
            getHandler().addObject(F3);
            getHandler().addObject(G3);
            getHandler().addObject(A4);
            getHandler().addObject(B4);
            getHandler().addObject(C4);
            getHandler().addObject(D4);
            getHandler().addObject(E4);

            getHandler().addObject(C3S);
            getHandler().addObject(D3S);
            getHandler().addObject(F3S);
            getHandler().addObject(G3S);
            getHandler().addObject(A4S);
            getHandler().addObject(C4S);
            getHandler().addObject(D4S);


            getHandler().addObject(new NPC(1090,320,96,64,getHandler(),this,ID.NPC,2,getTbHandler(),"The door will only open when you play the correct tune of 10 keys. If you mess up, it will reset itself. The tune is a bit familiar and... deadly (That's a hint)",getPlayer(),ID.Book));

            setBackground(Color.black);
            loadLevel(tex.Room5_4O);
            camera.updateRange("Room5_4");


        }else if(currentRoom.equals("RoomGame")){

        }else if(currentRoom.equals("Room5_5")){
            AudioPlayer.getMusic("Dead").stop();
            currentMusic="none";


            getHandler().addObject(getRoom5_5_Bleach());
            getHandler().addObject(new Transition(0, 0, 1, 10000, ID.Transition, "Room5_6", 900, 1810, getPlayer(), false, "dead"));

            getHandler().addObject(new Transition(0, 3989, 1, 10000, ID.Transition, "Room5_4", 2140, 70, getPlayer(), true, "dead"));
            getHandler().addObject(greenSimon);
            getHandler().addObject(yellowSimon);
            getHandler().addObject(blueSimon);
            getHandler().addObject(redSimon);


            if(!simonPuzzle)
                getHandler().addObject(simonBlock);

            setBackground(Color.black);
            loadLevel(tex.Room5_5O);
            camera.updateRange("Room5_5");
            getHandler().addObject(new SaveObject(1540,190,64,64));

        }else if(currentRoom.equals("Room5_6")){

            AudioPlayer.getMusic("Dead").loop();
            currentMusic="Dead";



            setBackground(Color.black);

            if(bigChungus.isDefeated()){
                getHandler().addObject(new Block(640,1950,1,600,getHandler(),this,ID.BlackGround));
                // end game scenario :)
                // orb of ressurection or orb of destruction
                getHandler().addObject(getRoom5_6_OrbR());
                getHandler().addObject(getRoom5_6_OrbD());

                getPlayer().setX(900);
                getPlayer().setY(600);
            }else{
                getHandler().addObject(new Transition(640, 1950, 1, 10000, ID.Transition, "Room5_5", 1800, 70, getPlayer(), false, "dead"));
                getHandler().addObject(bc1);
                getHandler().addObject(bc2);
                getHandler().addObject(bc3);
                getHandler().addObject(bc4);
                getHandler().addObject(bc5);
                getHandler().addObject(new SaveObject(560,650,64,64));

            }

            getHandler().addObject(bigChungus);



            camera.updateRange("Room5_6");


        }else if(currentRoom.equals("Room0_1")){
            getPlayer().setX(980);
            getPlayer().setY(150);
            Enemy josh=new Enemy(0,0,0,0,getHandler(),this,ID.Josh,3,getTbHandler(),"jaja",getPlayer(),ID.Josh,100000,100000,10000,null,Color.black,10);

            getHandler().addObject(new TextBox(josh,"Oh hi! My name is Josh, the creator of this game! I'm bout to give you a tutorial of the controls. Press Z to escape this textbox and arrows to move the wizard here.",0,300,1,10000,ID.TextBox,getTbHandler()));
            getHandler().addObject(new TextBox(josh,"Press C to open your inventory and Z to close that inventory. Then try hitting V to view your spells.",0,500,1,10000,ID.TextBox,getTbHandler()));
            getHandler().addObject(new TextBox(josh,"Along your journey you will find green orbs that are spells or items in which you can use in battle. (some can be used outside of battle)",0,700,1,10000,ID.TextBox,getTbHandler()));
            getHandler().addObject(new TextBox(josh,"While exploring, you may find yourself in a randomly encountered battle! Just keep in mind that usually to select something you use X then Z to deselect. ",0,800,1,10000,ID.TextBox,getTbHandler()));
            getHandler().addObject(new TextBox(josh,"In battles, you will have 4 options. Fight, Items, Spells, or Run. Use the left and right arrow to vary which option you want to use. ",0,900,1,10000,ID.TextBox,getTbHandler()));
            getHandler().addObject(new TextBox(josh,"That's pretty much it. If you need help with controls. Go to the main menu and click Controls with your mouse! Also... the escape key will close the game so be careful! Have fun!! Walk further down to start the game.",0,1000,1,10000,ID.TextBox,getTbHandler()));
            getHandler().addObject(new Transition(0,4000,1,10000,ID.Transition,"Room1_1",920,40,getPlayer(),false,"wae"));

            setBackground(Color.black);
            camera.updateRange("Room0_1");

        }else if(currentRoom.equals("Room1_2")){

            setBackground(Color.black);
            camera.updateRange("Room1_2");

        }else if(currentRoom.equals("Room1_3")){
            //shaggy room
            Enemy shaggy =new Enemy(20,1475,140,40,getHandler(),this,ID.Shaggy,2,getTbHandler(),"Ultimate Shaggy",getPlayer(),ID.Shaggy,999999,999999,1,tex.ShaggyBF,Color.green,999999);
            getHandler().addObject(shaggy);
            getHandler().addObject(new TextBox(shaggy,"You won't let them live, you won't let them die. I think it's time you understand what it's like to be a mere mortal",620,0,10000,1,ID.TextBox,getTbHandler() ));
            getHandler().addObject(new TextBox(shaggy,"Zoinks, this is where uh, your story ends.",420,0,10000,1,ID.TextBox,getTbHandler() ));



            //0,1540

            setBackground(Color.black);
            camera.updateRange("Room1_3");

        }else if(currentRoom.equals("Room1_4")){

            setBackground(Color.black);
            camera.updateRange("Room1_4");

        }

        camera.setX(((getPlayer().getX())  ));
        camera.setY(((getPlayer().getY())  ));





        }






    public void loadLevel(BufferedImage image){


        int w = image.getWidth();
        int h = image.getHeight();


        for(int xx = 0; xx < w; xx++){
            for(int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == 255&&green==0&&blue==0)
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.GrayGround));
                else if (green == 255&&red==0&&blue==0) {
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.BlackGround));
                }else if(blue==255&&red==0&&green==0){
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.Pavement));
                }else if(red==57&&green==255&&blue==50){
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.Grass));
                    // grass
                }else if(red==191&&green==0&&blue==149){
                    //tree
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.Tree));
                }else if(red==2&&green==2&&blue==40){
                    // invisible block to stop during ice puzzles???
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.invisWall));


                }else if(red==255&&green==230&&blue==0){
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.Mushroom));

                }else if(red==255&&green==111&&blue==0){
                    //getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.RedGround));

                }else if(red==0 && green==255 && blue==100){
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.sideRail));
                }else if(red==100 && green==100 && blue==0){
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.bottomRail));
                }else if(red==0 && green==100 && blue==255){
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.Ice));
                }else if(red==100 && green==50 && blue==50){
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.Tree2));

                }else if(red==100 && green==100 && blue==100){
                    getHandler().addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, getHandler(), this, ID.grave));

                }
            }
        }
        if(currentRoom.equals("Room1_1")){
            getHandler().addObject(new NPC(1050,500,64,64,getHandler(),this,ID.NPC,3,tbHandler,"You... are the last of us. I am unable to continue to fight him. My last gift to you... the prophecy.",getPlayer(),ID.TheLastEntity));
            getHandler().addObject(new NPC(750,500,48,32,getHandler(),this,ID.NPC,3,tbHandler,"The Chosen One must travel through The Wae Forest, Area 51, Eht Sdrawkcab and the Land of Dead Memes to make the ultimate decision. The choice is up to fate. The decision that he will hate.",getPlayer(), ID.Book));
            // transition object to room 2_2
            getHandler().addObject(new Transition(835,1975,32,100,ID.Transition,"Room2_1",850,100,getPlayer(),true,"wae"));
            getHandler().addObject(new Items("Apple","Good Snack boi",900,1700,64,64,ID.Item,this,0,0,false,false,0,0));



        }else if(currentRoom.equals("Room2_1")){

            setBackground(Color.black);

        }
    }


    public KeyInput getKeyInput() {
        return keyInput;
    }

    public boolean getSwitch(){
        return this.Switch;
    }
    public void setSwitch(boolean Switch){
        this.Switch = Switch;
    }
    public STATE getCurrentState(){
        return currentState;
    }
    public void setCurrentState(STATE currentState){
        this.currentState = currentState;
    }
    public Battle getCurrentBattle(){
        return this.currentBattle;
    }
    public void setCurrentBattle(Battle currentBattle){
        this.currentBattle = currentBattle;
    }

    public AudioPlayer getAp(){
        return this.ap;
    }

    public void save(){
        try{
            setInv(getPlayer().getInv());
            setMagic(getPlayer().getMagic());
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("game.bin"));
            os.writeObject(this);
            os.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void loadSave(){
        System.out.println("jeejee");
        try{

            inv = new Inventory(this);
            magic = new Magic();
            currentState = STATE.FirstArea;
            currentArea="dead";
            camera = new Camera(0,0,this);


            currentRoom="Room0_1";
            camera.updateRange(currentRoom);
            setPlayer(new Player(900, 200, 92, 23, getHandler(), this, ID.Player, 2,inv, magic));
            getPlayer().setLimited(false);



            //player.getMagic().addItem(new Spells("Auto kill", "Yeet and Delete", player.getMagic(),0,0,0,0,ID.Spell,this,0,false,false,0,0));

            // items and spells addition

/*
                player.getInv().addItem(Room3_9_AliensHat);
                player.getInv().addItem(Room3_9_LightSaber);
                player.getMagic().addItem(Room3_1_ManaThrow);
                player.getInv().addItem(Room3_2_WizardWand);
                player.getMagic().addItem(Room3_2_FocusBeam);
                player.getMagic().addItem(Room3_5_Earthquake);
                player.getInv().addItem(Room3_5_WizardCloak);
                player.getInv().addItem(Room3_7_ChickenNugget);
                player.getInv().addItem(Room3_8_InstaProtein);
                player.getInv().addItem(Room3_6_BigBopper);
                player.getInv().addItem(Room3_6_SpaceFood);
                player.getInv().addItem(Room4_1_UnoReverse);
                player.getInv().addItem(Room4_2_Nokia);
                player.getInv().addItem(Room4_3_BunnyEars);
                player.getInv().addItem(Room4_4_BlueEyes);
                player.getInv().addItem(Room4_4_C_I_T_Shirt);
                player.getMagic().addItem(Room4_3_Laser);
                player.getMagic().addItem(Room4_4_Screech);
                player.getMagic().addItem(Room4_4_Skert);
                player.getMagic().addItem(Room4_3_TakeItBackNowYall);

                player.getInv().addItem(Room5_5_Bleach);
                player.getInv().addItem(Room5_4_ChickenTender);
                player.getMagic().addItem(Room5_3_ManaRestore);
                player.getMagic().addItem(Room5_3_TheGodPiercer);


                player.getInv().addItem(Room2_3_LiptonIcedTea);
                player.getInv().addItem(Room2_3_Tidepod);
                player.getMagic().addItem(Room2_2_Basic_Heal);
                player.getMagic().addItem(Room2_3_YeetusDeletus); */


            A51_RSwitch1=new NPC(750,500,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch1);
            A51_RSwitch2=new NPC(3840,320,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch2);
            A51_RSwitch3=new NPC(245,1400,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch3);
            A51_RSwitch4=new NPC(80,2200,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch4);



            // 3-6
            A51_RSwitch5=new NPC(4200,2740,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch5);
            // 3-7
            A51_RSwitch6=new NPC(500,1760,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch6);
            // 3-8
            A51_RSwitch7=new NPC(280,1755,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch7);
            //3-6
            A51_RSwitch8=new NPC(6300,3600,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.redSwitch8);

            A51_GSwitch1=new NPC(1800,80,47,43,getHandler(),this,ID.NPC,1,tbHandler,"Click!",getPlayer(),ID.greenSwitch);

            who1=new NPC(1730,3780,128,128,getHandler(),this,ID.NPC,1,tbHandler,"Do we know eachother? It feels like we do. But from where? I can't put my finger on it. Although I don't have any fingers. I'm kinda a blob of stuff.",getPlayer(),ID.WHO1);;
            who2=new NPC(1540,100,128,128,getHandler(),this,ID.NPC,1,tbHandler,"There are some weird things going on around here... For some reason I feel like I shouldn't be helping you but uh.. you seem nice!",getPlayer(),ID.WHO2);
            whoT1=new TextBox(who1,"Woah woah buddy put your wand down! I'm not one of them bud. Actually I don't know what I am. I can't really remember too much of anything. My name or why I feel like I should know who you are.",1730,3780,1,1500,ID.TextBox,tbHandler);
            whoT2=new TextBox(who2,"Hey bud, so uh. The one thing I know is that this place is wack. Everything is a bit off here so... I have an idea. I'll give you a walkie talkie so I can help you out! We could even... be friends!",1540,100,1,1500,ID.TextBox,tbHandler);
            barrier=new Block(7280,64, 64, 675, getHandler(), this, ID.sideRail);
            specialGameWin=false;

            C3=new Block(1310,204,314,61,getHandler(),this,ID.whiteKey,ID.C3);
            C3S=new Block(1355,204,169,33,getHandler(),this,ID.blackKey,ID.C3S);
            D3=new Block(1371,204,314,61,getHandler(),this,ID.whiteKey,ID.D3);
            D3S=new Block(1420,204,169,33,getHandler(),this,ID.blackKey,ID.D3S);
            E3=new Block(1432,204,314,61,getHandler(),this,ID.whiteKey,ID.E3);
            F3=new Block(1493,204,314,61,getHandler(),this,ID.whiteKey,ID.F3);
            F3S=new Block(1540,204,169,33,getHandler(),this,ID.blackKey,ID.F3S);
            G3=new Block(1554,204,314,61,getHandler(),this,ID.whiteKey,ID.G3);
            G3S=new Block(1600,204,169,33,getHandler(),this,ID.blackKey,ID.G3S);
            A4=new Block(1615,204,314,61,getHandler(),this,ID.whiteKey,ID.A4);
            A4S=new Block(1660,204,169,33,getHandler(),this,ID.blackKey,ID.A4S);
            B4=new Block(1676,204,314,61,getHandler(),this,ID.whiteKey,ID.B4);
            C4=new Block(1737,204,314,61,getHandler(),this,ID.whiteKey,ID.C4);
            C4S=new Block(1785,204,169,33,getHandler(),this,ID.blackKey,ID.C4S);
            D4=new Block(1798,204,314,61,getHandler(),this,ID.whiteKey,ID.D4);
            D4S=new Block(1845,204,169,33,getHandler(),this,ID.blackKey,ID.D4S);
            E4=new Block(1857,204,314,61,getHandler(),this,ID.whiteKey,ID.E4);

            greenSimon=new Block(1400,400,250,250,getHandler(),this,ID.Simon,ID.lightGreen);
            blueSimon=new Block(1400,1000,250,250,getHandler(),this,ID.Simon,ID.lightBlue);
            yellowSimon=new Block(2000,1000,250,250,getHandler(),this,ID.Simon,ID.lightYellow);
            redSimon=new Block(2000,400,250,250,getHandler(),this,ID.Simon,ID.lightRed);

            knuckles1 = new Enemy(5760, 385, 100, 52, getHandler(), this, ID.Knuckles, 2, tbHandler, "Ugandan Knuckles", getPlayer(), ID.Knuckles,20,20,4,tex.Knuckles_BattleForm,Color.red,100);
            bigChungus = new Enemy(780, 130, 433, 225, getHandler(), this, ID.BigChungus, 7, tbHandler, "Big Chungus", getPlayer(), ID.BigChungus,100,100,4,tex.BigChungusBF[0],Color.gray,1000);
            pikachu=new Enemy(675,40,125,77,getHandler(),this,ID.Pikachu,1,tbHandler,"Surprised Pikachu",getPlayer(),ID.Pikachu,50,50, 4,tex.PikachuBF[0],Color.yellow,250);
            antihero=new Enemy(675,40,getPlayer().getHeight(),getPlayer().getWidth(),getHandler(),this,ID.AntiHero,1,getTbHandler(),"The Antihero",getPlayer(),ID.AntiHero, (int)(getPlayer().getAttack()*1.5), getPlayer().getDefense()*2, 4, tex.AntiHeroBF, Color.white,getPlayer().getHealth()*2);

            knuckles1.setRunnable(false);
            bigChungus.setRunnable(false);
            pikachu.setRunnable(false);
            antihero.setRunnable(false);
            pianoBlock= new Block(2035,110,5,225,getHandler(),this,ID.Tree2);
            simonBlock= new Block(1700,125,5,225,getHandler(),this,ID.Tree2);


            simonKeysList.add("G");
            simonKeysList.add("GG");
            simonKeysList.add("GGB");
            simonKeysList.add("GGBY");
            simonKeysList.add("GGBYR");
            simonKeysList.add("GGBYRR");
            simonKeysList.add("GGBYRRY");
            simonKeysList.add("GGBYRRYG");
            simonKeysList.add("GGBYRRYGY");
            simonKeysList.add("GGBYRRYGYR");

            bc1=new TextBox(bigChungus,"My people are gone. Do you realize what you have done?",0,1800,1,10000,ID.TextBox,tbHandler);
            bc2=new TextBox(bigChungus,"Your people have killed us. I'm done talking to you.",0,1600,1,10000,ID.TextBox,tbHandler);
            bc3=new TextBox(bigChungus,"I want to talk to the person controlling your actions. YOU. Why did you do this? All of my friends are dead. I tried to experiment with making new life but it failed as well.",0,1400,1,10000,ID.TextBox,tbHandler);
            bc4=new TextBox(bigChungus,"I am now a king without people to lead. When I realized the wizards are using the dark magic to avoid death, I knew our demise would be inevitable. ",0,1300,1,10000,ID.TextBox,tbHandler);
            bc5=new TextBox(bigChungus,"My friends are gone. I'm afraid this is where your journey ends. I will defeat you over and over until you give up.",0,1000,1,10000,ID.TextBox,tbHandler);

            for(int i = 0; i<10; i++){
                pianoKey[i]=false;
            }

            for(int i =0; i<55; i++){
                simonSays[i]=false;
            }
            simonPuzzle=false;
            // notes for what to add to saving

            // all of the rooms
            // add antihero boss to handler check

            setCurrentState(STATE.FirstArea);

            ObjectInputStream os = new ObjectInputStream(new FileInputStream("game.bin"));
            Game temp = (Game) os.readObject();

            this.currentRoom=temp.currentRoom;
            this.ap=temp.getAp();
            this.ap.load();
            setRooms(new HashMap<>());
            getRooms().put("Testing Room", tex.SS_FirstArea.grabImage(1, 1, 16, 16));
            getRooms().put("Room1_1", tex.Room1_1);
            getRooms().put("Room2_1",tex.Room2_1);
            getRooms().put("Room2_2",tex.Room2_2);
            getRooms().put("Room2_3",tex.Room2_3);
            getRooms().put("Room2_4",tex.Room2_4);
            getRooms().put("Room3_1",tex.Room3_1);
            getRooms().put("Room3_2",tex.Room3_2);
            getRooms().put("Room3_3",tex.Room3_3);
            getRooms().put("Room3_4",tex.Room3_4);
            getRooms().put("Room3_5",tex.Room3_5);
            getRooms().put("Room3_6",tex.Room3_6);
            getRooms().put("Room3_7",tex.Room3_7);
            getRooms().put("Room3_8",tex.Room3_8);
            getRooms().put("Room3_9",tex.Room3_9);
            getRooms().put("Room4_1",tex.Room4_1);
            getRooms().put("Room4_2",tex.Room4_2);
            getRooms().put("Room4_3",tex.Room4_3);
            getRooms().put("Room4_4",tex.Room4_4);
            getRooms().put("Room4_5",tex.Room4_5);
            getRooms().put("Room4_6",tex.Room4_6);
            getRooms().put("Room4_7",tex.Room4_7);
            getRooms().put("Room5_1",tex.Room5_1);
            getRooms().put("Room5_2",tex.Room5_2);
            getRooms().put("Room5_3",tex.Room5_3);
            getRooms().put("Room5_4",tex.Room5_4);
            getRooms().put("Room5_5",tex.Room5_5);
            getRooms().put("Room5_6",tex.Room5_6);
            getRooms().put("Room0_1",tex.Room0_1);
            getRooms().put("Room1_2",tex.Room1_2);
            getRooms().put("Room1_3",tex.Room1_3);
            getRooms().put("Room1_4",tex.Room1_4);
            pianoPuzzle=temp.isPianoPuzzle();
            aliensFreed=temp.isAliensFreed();
            A51_Switch1=temp.isA51_Switch1();
            A51_Switch2=temp.isA51_Switch2();
            A51_Switch3=temp.isA51_Switch3();
            A51_Switch4=temp.isA51_Switch4();

            A51_Switch5=temp.isA51_Switch5();
            A51_Switch6=temp.isA51_Switch6();
            A51_Switch7=temp.isA51_Switch7();
            A51_Switch8=temp.isA51_Switch8();
            currentArea=temp.getCurrentArea();

            Room2_2_Basic_Heal =temp.getRoom2_2_Basic_Heal();
            Room2_3_BoogieWoogie=temp.getRoom2_3_BoogieWoogie();
            Room2_3_YeetusDeletus=temp.getRoom2_3_YeetusDeletus();
            Room2_3_EnnieMeenieMinieMo=temp.getRoom2_3_EnnieMeenieMinieMo();
            Room2_3_EmptyWaterBottle=temp.getRoom2_3_EmptyWaterBottle();
            Room2_3_LiptonIcedTea = temp.getRoom2_3_LiptonIcedTea();
            Room2_3_Tidepod=temp.getRoom2_3_Tidepod();

            Room3_9_AliensHat=temp.getRoom3_9_AliensHat();
            Room3_9_LightSaber=temp.getRoom3_9_LightSaber();
            Room3_1_ManaThrow=temp.getRoom3_1_ManaThrow();
            Room3_2_WizardWand=temp.getRoom3_2_WizardWand();
            Room3_5_Earthquake=temp.getRoom3_5_Earthquake();
            Room3_5_WizardCloak=temp.getRoom3_5_WizardCloak();
            Room3_7_ChickenNugget=temp.getRoom3_7_ChickenNugget();
            Room3_8_InstaProtein=temp.getRoom3_8_InstaProtein();
            Room3_2_FocusBeam=temp.getRoom3_2_FocusBeam();
            Room3_6_BigBopper=temp.getRoom3_6_BigBopper();
            Room3_6_SpaceFood=temp.getRoom3_6_SpaceFood();

            Room4_1_UnoReverse=temp.getRoom4_1_UnoReverse();
            Room4_2_Nokia=temp.getRoom4_2_Nokia();
            Room4_3_BunnyEars=temp.getRoom4_3_BunnyEars();
            Room4_3_Laser=temp.getRoom4_3_Laser();
            Room4_3_TakeItBackNowYall=temp.getRoom4_3_TakeItBackNowYall();
            Room4_4_BlueEyes=temp.getRoom4_4_BlueEyes();
            Room4_4_C_I_T_Shirt=temp.getRoom4_4_C_I_T_Shirt();
            Room4_4_FullHeal=temp.getRoom4_4_FullHeal();
            Room4_4_Screech=temp.getRoom4_4_Screech();
            Room4_4_Skert=temp.getRoom4_4_Skert();
            Room5_6_OrbR=temp.getRoom5_6_OrbR();
            Room5_6_OrbD=temp.getRoom5_6_OrbD();
            Room5_5_Bleach=temp.getRoom5_5_Bleach();
            Room5_4_ChickenTender=temp.getRoom5_4_ChickenTender();
            Room5_3_TheGodPiercer=temp.getRoom5_3_TheGodPiercer();
            try{
                setCurrentMusic(temp.getCurrentMusic());
                AudioPlayer.getMusic(getCurrentMusic()).loop();
            }catch(Exception e){
                e.printStackTrace();
            }
// inventory and magic reset??
            // bc the items are not being replaced in inv and having problems with game assignments



            //Room5_6_OrbR.isVisible=true;
           // Room5_6_OrbD.isVisible=true;



            A51_RSwitch1=temp.getA51_RSwitch1();
            A51_RSwitch2=temp.getA51_RSwitch2();
            A51_RSwitch3=temp.getA51_RSwitch3();
            A51_RSwitch4=temp.getA51_RSwitch4();



            // 3-6
            A51_RSwitch5=temp.getA51_RSwitch5();
            // 3-7
            A51_RSwitch6=temp.getA51_RSwitch6();
            // 3-8
            A51_RSwitch7=temp.getA51_RSwitch7();
            //3-6
            A51_RSwitch8=temp.getA51_RSwitch8();

            A51_GSwitch1=temp.getA51_GSwitch1();



            A51_RSwitch1.setGame(this);
            A51_RSwitch2.setGame(this);
            A51_RSwitch3.setGame(this);
            A51_RSwitch4.setGame(this);



            // 3-6
            A51_RSwitch5.setGame(this);
            // 3-7
            A51_RSwitch6.setGame(this);
            // 3-8
            A51_RSwitch7.setGame(this);
            //3-6
            A51_RSwitch8.setGame(this);

            A51_GSwitch1.setGame(this);





            //window=new Window(WIDTH, HEIGHT, "The Reign of Big Chungus", this);
            setHandler(new Handler(this));
            getPlayer().setHandler(getHandler());
            currentLevel = tex.Room1_1;
            camera = new Camera(0,0,this);
            camera.updateRange(currentRoom);
            //loadLevel(currentLevel);
            tbHandler = temp.tbHandler;
            //currentState=null;
            //Switch=false;
            //player=temp.player;
            //player.setGame(temp);
            //   knuckles1=temp.knuckles1;
            //System.out.println(knuckles1);
            //   knuckles1.setGame(temp);
            //   pikachu=temp.pikachu;
            //   pikachu.setGame(temp);
            //   bigChungus=temp.bigChungus;
            //  bigChungus.setGame(temp);
            // npc=temp.npc;
            //npc.setGame(temp);
      /*      for(int i = 0; i<handler.object.size(); i++){
                if(handler.object.get(i).getId()==ID.Player){
                    player=(Player)handler.object.get(i);
                    handler.object.set(i, player);
                }else if(handler.object.get(i).getId()==ID.BigChungus){
                    bigChungus=(Enemy) handler.object.get(i);
                    handler.object.set(i, bigChungus);
                }else if(handler.object.get(i).getId()==ID.Knuckles){
                    knuckles1=(Enemy) handler.object.get(i);
                    handler.object.set(i, knuckles1);
                }else if(handler.object.get(i).getId()==ID.Pikachu){
                    pikachu=(Enemy) handler.object.get(i);
                    handler.object.set(i, pikachu);
                }else if(handler.object.get(i).getId()==ID.NPC){
                    npc=(NPC) handler.object.get(i);
                    handler.object.set(i, npc);
                }else if(handler.object.get(i).getId()==ID.AntiHero){
                    antihero=(Enemy) handler.object.get(i);
                    handler.object.set(i,antihero);
                }

            }*/
            setBigChungus(temp.getBigChungus());
            setKnuckles1(temp.getKnuckles1());
            setAntihero(temp.getAntihero());
            setPikachu(temp.getPikachu());
            setPlayer(temp.getPlayer());

            getPlayer().setGame(this);
            getBigChungus().setGame(this);
            getKnuckles1().setGame(this);
            getPikachu().setGame(this);
            getAntihero().setGame(this);
            getPlayer().setInv(temp.getInv());
            setEndangered(temp.getEndangered());
            getPlayer().setMagic(temp.getMagic());
            setKeyInput(new KeyInput(getHandler(), getPlayer(), getTbHandler(), getPlayer().getInv(),getGp(),this));
            this.addKeyListener(getKeyInput());
            setSwitch(false);
            getHandler().clear();
            loadLevel(getCurrentRoom());
            setSwitch(true);
            getPlayer().setLimited(false);
            getPlayer().setX(temp.getPlayer().getX());
            getPlayer().setY(temp.getPlayer().getY());
            //getHandler().addObject(getPlayer());
            getPlayer().setLastKeyHit(1000);

            getRoom2_2_Basic_Heal().setGame(this);
            getRoom2_3_BoogieWoogie().setGame(this);
            getRoom2_3_YeetusDeletus().setGame(this);
            getRoom2_3_EnnieMeenieMinieMo().setGame(this);
            getRoom2_3_EmptyWaterBottle().setGame(this);
            getRoom2_3_LiptonIcedTea().setGame(this);
            getRoom2_3_Tidepod().setGame(this);

            getRoom3_9_AliensHat().setGame(this);
            getRoom3_9_LightSaber().setGame(this);
            getRoom3_1_ManaThrow().setGame(this);
            getRoom3_2_WizardWand().setGame(this);
            getRoom3_5_Earthquake().setGame(this);
            getRoom3_5_WizardCloak().setGame(this);
            getRoom3_7_ChickenNugget().setGame(this);
            getRoom3_8_InstaProtein().setGame(this);
            getRoom3_2_FocusBeam().setGame(this);
            getRoom3_6_BigBopper().setGame(this);
            getRoom3_6_SpaceFood().setGame(this);

            getRoom4_1_UnoReverse().setGame(this);
            getRoom4_2_Nokia().setGame(this);
            getRoom4_3_BunnyEars().setGame(this);
            getRoom4_3_Laser().setGame(this);
            getRoom4_3_TakeItBackNowYall().setGame(this);
            getRoom4_4_BlueEyes().setGame(this);
            getRoom4_4_C_I_T_Shirt().setGame(this);
            getRoom4_4_FullHeal().setGame(this);
            getRoom4_4_Screech().setGame(this);
            getRoom4_4_Skert().setGame(this);
            getRoom5_6_OrbR().setGame(this);
            getRoom5_6_OrbD().setGame(this);
            getRoom5_5_Bleach().setGame(this);
            getRoom5_4_ChickenTender().setGame(this);
            getRoom5_3_TheGodPiercer().setGame(this);
//            autokill.setGame(this);





            for(int i=0; i<getPlayer().getMagic().magic.size(); i++){
                if(getPlayer().getMagic().magic.get(i).getName().equals("Auto kill")){
                    getPlayer().getMagic().magic.set(i, autokill);
                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Basic Heal")){
                    getPlayer().getMagic().magic.set(i, Room2_2_Basic_Heal);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Boogie Woogie")){
                    getPlayer().getMagic().magic.set(i, Room2_3_BoogieWoogie);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Yeetus Deletus")){
                    getPlayer().getMagic().magic.set(i, Room2_3_YeetusDeletus);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Enie Meni Minie Mo")){
                    getPlayer().getMagic().magic.set(i, Room2_3_EnnieMeenieMinieMo);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Mana Throw")){
                    getPlayer().getMagic().magic.set(i, Room3_1_ManaThrow);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Earthquake")){
                    getPlayer().getMagic().magic.set(i, Room3_5_Earthquake);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Focus Beam")){
                    getPlayer().getMagic().magic.set(i, Room3_2_FocusBeam);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Laser")){
                    getPlayer().getMagic().magic.set(i, Room4_3_Laser);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Take it back now")){
                    getPlayer().getMagic().magic.set(i, Room4_3_TakeItBackNowYall);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Full Heal")){
                    getPlayer().getMagic().magic.set(i, Room4_4_FullHeal);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Screech")){
                    getPlayer().getMagic().magic.set(i, Room4_4_Screech);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Skert")){
                    getPlayer().getMagic().magic.set(i, Room4_4_Skert);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("Mana Restore")){
                    getPlayer().getMagic().magic.set(i, Room5_3_ManaRestore);

                }else if(getPlayer().getMagic().magic.get(i).getName().equals("The God Piercer")){
                    getPlayer().getMagic().magic.set(i, Room5_3_TheGodPiercer);

                }
            }


            for(int i = 0; i<getPlayer().getInv().inv.size(); i++) {
                if (getPlayer().getInv().inv.get(i).getName().equals("Empty Water Bottle")) {
                    getPlayer().getInv().inv.set(i, Room2_3_EmptyWaterBottle);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Lipton Iced Tea")) {
                    getPlayer().getInv().inv.set(i, Room2_3_LiptonIcedTea);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Tide Pod")) {
                    getPlayer().getInv().inv.set(i, Room2_3_Tidepod);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Alien Hat")) {
                    getPlayer().getInv().inv.set(i, Room3_9_AliensHat);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Light Saber")) {
                    getPlayer().getInv().inv.set(i, Room3_9_LightSaber);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Wizard Wand")) {
                    getPlayer().getInv().inv.set(i, Room3_2_WizardWand);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Wizard Cloak")) {
                    getPlayer().getInv().inv.set(i, Room3_5_WizardCloak);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Chicken Nugget")) {
                    getPlayer().getInv().inv.set(i, Room3_7_ChickenNugget);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Insta Protein")) {
                    getPlayer().getInv().inv.set(i, Room3_8_InstaProtein);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Big Bopper")) {
                    getPlayer().getInv().inv.set(i, Room3_6_BigBopper);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Space Food")) {
                    getPlayer().getInv().inv.set(i, Room3_6_SpaceFood);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Uno Reverse")) {
                    getPlayer().getInv().inv.set(i, Room4_1_UnoReverse);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Nokia")) {
                    getPlayer().getInv().inv.set(i, Room4_2_Nokia);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Bunny Ears")) {
                    getPlayer().getInv().inv.set(i, Room4_3_BunnyEars);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("YuGiOh Card")) {
                    getPlayer().getInv().inv.set(i, Room4_4_BlueEyes);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("CIT Shirt")) {
                    getPlayer().getInv().inv.set(i, Room4_4_C_I_T_Shirt);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Orb Of Resurrection")) {
                    getPlayer().getInv().inv.set(i, Room5_6_OrbR);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Orb Of Destruction")) {
                    getPlayer().getInv().inv.set(i, Room5_6_OrbD);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Bleach")) {
                    getPlayer().getInv().inv.set(i, Room5_5_Bleach);

                }else if(getPlayer().getInv().inv.get(i).getName().equals("Chicken Tender")) {
                    getPlayer().getInv().inv.set(i, Room5_4_ChickenTender);

                }
            }

            removeKeyListener(getKeyInput());
            removeKeyListener(getKeyInput());
            setKeyInput(new KeyInput(getHandler(), getPlayer(),getTbHandler(),getPlayer().getInv(),getGp(),this));
            addKeyListener(getKeyInput());




            // need to reinit all images as they are not saved
            //player.setCurrentImages();



            // reinit all stuffs

            //this.handler = this.so.getHandler();

            // possible problem: THe handler that is being saved contains different objects that it wants to tick and render than the new ones






        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public Handler getHandler(){
        return this.handler;
    }

    public Player getPlayer() {
        return player;
    }

    public TBHandler getTbHandler() {
        return tbHandler;
    }

    public void setCurrentLevel(BufferedImage currentLevel){
        this.currentLevel=currentLevel;
    }
    public BufferedImage getCurrentLevel(){
        return this.currentLevel;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean getEndangered() {
        return endangered;
    }

    public void setEndangered(boolean endangered) {
        this.endangered = endangered;
    }

    public HashMap<String, BufferedImage> getRooms() {
        return this.rooms;
    }

    public void setRooms(HashMap<String, BufferedImage> rooms) {
        this.rooms = rooms;
    }

    public HashMap<String, ArrayList<Integer>> getRanges() {
        return ranges;
    }

    public void setRanges(HashMap<String, ArrayList<Integer>> ranges) {
        this.ranges = ranges;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public String getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(String currentArea) {
        this.currentArea = currentArea;
    }


    public boolean isA51_Switch1() {
        return A51_Switch1;
    }

    public void setA51_Switch1(boolean a51_Switch1) {
        A51_Switch1 = a51_Switch1;
    }

    public boolean isA51_Switch2() {
        return A51_Switch2;
    }

    public void setA51_Switch2(boolean a51_Switch2) {
        A51_Switch2 = a51_Switch2;
    }

    public boolean isA51_Switch3() {
        return A51_Switch3;
    }

    public void setA51_Switch3(boolean a51_Switch3) {
        A51_Switch3 = a51_Switch3;
    }

    public boolean isA51_Switch4() {
        return A51_Switch4;
    }

    public void setA51_Switch4(boolean a51_Switch4) {
        A51_Switch4 = a51_Switch4;
    }



    public NPC getA51_RSwitch1() {
        return A51_RSwitch1;
    }

    public void setA51_RSwitch1(NPC a51_RSwitch1) {
        A51_RSwitch1 = a51_RSwitch1;
    }

    public NPC getA51_RSwitch2() {
        return A51_RSwitch2;
    }

    public void setA51_RSwitch2(NPC a51_RSwitch2) {
        A51_RSwitch2 = a51_RSwitch2;
    }

    public NPC getA51_RSwitch3() {
        return A51_RSwitch3;
    }

    public void setA51_RSwitch3(NPC a51_RSwitch3) {
        A51_RSwitch3 = a51_RSwitch3;
    }

    public NPC getA51_RSwitch4() {
        return A51_RSwitch4;
    }

    public void setA51_RSwitch4(NPC a51_RSwitch4) {
        A51_RSwitch4 = a51_RSwitch4;
    }

    public boolean isA51_Switch5() {
        return A51_Switch5;
    }

    public void setA51_Switch5(boolean a51_Switch5) {
        A51_Switch5 = a51_Switch5;
    }

    public boolean isA51_Switch6() {
        return A51_Switch6;
    }

    public void setA51_Switch6(boolean a51_Switch6) {
        A51_Switch6 = a51_Switch6;
    }

    public boolean isA51_Switch7() {
        return A51_Switch7;
    }

    public void setA51_Switch7(boolean a51_Switch7) {
        A51_Switch7 = a51_Switch7;
    }

    public boolean isA51_Switch8() {
        return A51_Switch8;
    }

    public void setA51_Switch8(boolean a51_Switch8) {
        A51_Switch8 = a51_Switch8;
    }

    public NPC getA51_RSwitch5() {
        return A51_RSwitch5;
    }

    public void setA51_RSwitch5(NPC a51_RSwitch5) {
        A51_RSwitch5 = a51_RSwitch5;
    }

    public NPC getA51_RSwitch6() {
        return A51_RSwitch6;
    }

    public void setA51_RSwitch6(NPC a51_RSwitch6) {
        A51_RSwitch6 = a51_RSwitch6;
    }

    public NPC getA51_RSwitch7() {
        return A51_RSwitch7;
    }

    public void setA51_RSwitch7(NPC a51_RSwitch7) {
        A51_RSwitch7 = a51_RSwitch7;
    }

    public NPC getA51_RSwitch8() {
        return A51_RSwitch8;
    }

    public void setA51_RSwitch8(NPC a51_RSwitch8) {
        A51_RSwitch8 = a51_RSwitch8;
    }

    public boolean isAliensFreed() {
        return aliensFreed;
    }

    public void setAliensFreed(boolean aliensFreed) {
        this.aliensFreed = aliensFreed;
    }

    public TextBox getWhoT1() {
        return whoT1;
    }

    public void setWhoT1(TextBox whoT1) {
        this.whoT1 = whoT1;
    }

    public TextBox getWhoT2() {
        return whoT2;
    }

    public void setWhoT2(TextBox whoT2) {
        this.whoT2 = whoT2;
    }

    public Block getBarrier() {
        return barrier;
    }

    public void setBarrier(Block barrier) {
        this.barrier = barrier;
    }

    public GamePlayer getGp() {
        return gp;
    }

    public void setGp(GamePlayer gp) {
        this.gp = gp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGameHealth() {
        return gameHealth;
    }

    public void setGameHealth(int gameHealth) {
        this.gameHealth = gameHealth;
    }

    public boolean isSpecialGameWin() {
        return specialGameWin;
    }

    public void setSpecialGameWin(boolean specialGameWin) {
        this.specialGameWin = specialGameWin;
    }

    public Spawn getSpawn() {
        return spawn;
    }

    public void setSpawn(Spawn spawn) {
        this.spawn = spawn;
    }

    public Enemy getAntihero() {
        return antihero;
    }

    public void setAntihero(Enemy antihero) {
        this.antihero = antihero;
    }

    public Block getC3() {
        return C3;
    }

    public void setC3(Block c3) {
        C3 = c3;
    }

    public Block getC3S() {
        return C3S;
    }

    public void setC3S(Block c3S) {
        C3S = c3S;
    }

    public Block getD3() {
        return D3;
    }

    public void setD3(Block d3) {
        D3 = d3;
    }

    public Block getD3S() {
        return D3S;
    }

    public void setD3S(Block d3S) {
        D3S = d3S;
    }

    public Block getE3() {
        return E3;
    }

    public void setE3(Block e3) {
        E3 = e3;
    }

    public Block getF3() {
        return F3;
    }

    public void setF3(Block f3) {
        F3 = f3;
    }

    public Block getF3S() {
        return F3S;
    }

    public void setF3S(Block f3S) {
        F3S = f3S;
    }

    public Block getG3() {
        return G3;
    }

    public void setG3(Block g3) {
        G3 = g3;
    }

    public Block getG3S() {
        return G3S;
    }

    public void setG3S(Block g3S) {
        G3S = g3S;
    }

    public Block getA4() {
        return A4;
    }

    public void setA4(Block a4) {
        A4 = a4;
    }

    public Block getA4S() {
        return A4S;
    }

    public void setA4S(Block a4S) {
        A4S = a4S;
    }

    public Block getB4() {
        return B4;
    }

    public void setB4(Block b4) {
        B4 = b4;
    }

    public Block getC4() {
        return C4;
    }

    public void setC4(Block c4) {
        C4 = c4;
    }

    public Block getC4S() {
        return C4S;
    }

    public void setC4S(Block c4S) {
        C4S = c4S;
    }

    public Block getD4() {
        return D4;
    }

    public void setD4(Block d4) {
        D4 = d4;
    }

    public Block getD4S() {
        return D4S;
    }

    public void setD4S(Block d4S) {
        D4S = d4S;
    }

    public Block getE4() {
        return E4;
    }

    public void setE4(Block e4) {
        E4 = e4;
    }

    public boolean[] getPianoKey() {
        return pianoKey;
    }

    public void setPianoKey(boolean[] pianoKey) {
        this.pianoKey = pianoKey;
    }

    public boolean isPianoPuzzle() {
        return pianoPuzzle;
    }

    public void setPianoPuzzle(boolean pianoPuzzle) {
        this.pianoPuzzle = pianoPuzzle;
    }

    public Block getPianoBlock() {
        return pianoBlock;
    }

    public void setPianoBlock(Block pianoBlock) {
        this.pianoBlock = pianoBlock;
    }


    public Block getGreenSimon() {
        return greenSimon;
    }

    public void setGreenSimon(Block greenSimon) {
        this.greenSimon = greenSimon;
    }

    public Block getBlueSimon() {
        return blueSimon;
    }

    public void setBlueSimon(Block blueSimon) {
        this.blueSimon = blueSimon;
    }

    public Block getYellowSimon() {
        return yellowSimon;
    }

    public void setYellowSimon(Block yellowSimon) {
        this.yellowSimon = yellowSimon;
    }

    public Block getRedSimon() {
        return redSimon;
    }

    public void setRedSimon(Block redSimon) {
        this.redSimon = redSimon;
    }

    public boolean allContains(boolean[] temp){
        boolean check=true;

        for(int i = 0; i<temp.length; i++){
            if(!temp[i])
                check=false;

        }
        return check;

    }

    public void resetSimon(){
        for(int i = 0; i<55; i++){
            simonSays[i]=false;
        }
        spot=0;

    }

    public long getGreenPressed() {
        return greenPressed;
    }

    public void setGreenPressed(long greenPressed) {
        this.greenPressed = greenPressed;
    }

    public long getYellowPressed() {
        return yellowPressed;
    }

    public void setYellowPressed(long yellowPressed) {
        this.yellowPressed = yellowPressed;
    }

    public long getBluePressed() {
        return bluePressed;
    }

    public void setBluePressed(long bluePressed) {
        this.bluePressed = bluePressed;
    }

    public long getRedPressed() {
        return redPressed;
    }

    public void setRedPressed(long redPressed) {
        this.redPressed = redPressed;
    }

    public boolean[] getSimonSays() {
        return simonSays;
    }

    public void setSimonSays(boolean[] simonSays) {
        this.simonSays = simonSays;
    }

    public String getSimonKey() {
        return simonKey;
    }

    public void setSimonKey(String simonKey) {
        this.simonKey = simonKey;
    }

    public void setSpot2(int spot2){
        this.spot2=spot2;

    }

    public boolean isSimonPuzzle() {
        return simonPuzzle;
    }

    public void setSimonPuzzle(boolean simonPuzzle) {
        this.simonPuzzle = simonPuzzle;
    }

    public Enemy getBigChungus() {
        return bigChungus;
    }

    public void setBigChungus(Enemy bigChungus) {
        this.bigChungus = bigChungus;
    }

    public boolean isEndgame() {
        return endgame;
    }

    public void setEndgame(boolean endgame) {
        this.endgame = endgame;
    }

    public boolean isHero() {
        return hero;
    }

    public void setHero(boolean hero) {
        this.hero = hero;
    }

    public String getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(String currentMusic) {
        this.currentMusic = currentMusic;
    }

    public Enemy getKnuckles1() {
        return knuckles1;
    }

    public void setKnuckles1(Enemy knuckles1) {
        this.knuckles1 = knuckles1;
    }

    public Enemy getPikachu() {
        return pikachu;
    }

    public void setPikachu(Enemy pikachu) {
        this.pikachu = pikachu;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static float getWIDTH() {
        return WIDTH;
    }

    public static float getHEIGHT() {
        return HEIGHT;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public static Textures getTex() {
        return tex;
    }

    public static void setTex(Textures tex) {
        Game.tex = tex;
    }

    public TextBox getTb() {
        return tb;
    }

    public void setTb(TextBox tb) {
        this.tb = tb;
    }

    public void setTbHandler(TBHandler tbHandler) {
        this.tbHandler = tbHandler;
    }

    public void setKeyInput(KeyInput keyInput) {
        this.keyInput = keyInput;
    }

    public boolean isSwitch() {
        return Switch;
    }

    public Font getFnt() {
        return fnt;
    }

    public void setFnt(Font fnt) {
        this.fnt = fnt;
    }

    public Magic getMagic() {
        return magic;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }

    public void setAp(AudioPlayer ap) {
        this.ap = ap;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public SaveObject getSo() {
        return so;
    }

    public void setSo(SaveObject so) {
        this.so = so;
    }

    public NPC getA51_GSwitch1() {
        return A51_GSwitch1;
    }

    public void setA51_GSwitch1(NPC a51_GSwitch1) {
        A51_GSwitch1 = a51_GSwitch1;
    }

    public NPC getWho1() {
        return who1;
    }

    public void setWho1(NPC who1) {
        this.who1 = who1;
    }

    public NPC getWho2() {
        return who2;
    }

    public void setWho2(NPC who2) {
        this.who2 = who2;
    }

    public boolean isEndangered() {
        return endangered;
    }

    public Spells getRoom2_2_Basic_Heal() {
        return Room2_2_Basic_Heal;
    }

    public void setRoom2_2_Basic_Heal(Spells room2_2_Basic_Heal) {
        Room2_2_Basic_Heal = room2_2_Basic_Heal;
    }

    public Items getRoom2_3_Tidepod() {
        return Room2_3_Tidepod;
    }

    public void setRoom2_3_Tidepod(Items room2_3_Tidepod) {
        Room2_3_Tidepod = room2_3_Tidepod;
    }

    public Items getRoom2_3_EmptyWaterBottle() {
        return Room2_3_EmptyWaterBottle;
    }

    public void setRoom2_3_EmptyWaterBottle(Items room2_3_EmptyWaterBottle) {
        Room2_3_EmptyWaterBottle = room2_3_EmptyWaterBottle;
    }

    public Items getRoom2_3_LiptonIcedTea() {
        return Room2_3_LiptonIcedTea;
    }

    public void setRoom2_3_LiptonIcedTea(Items room2_3_LiptonIcedTea) {
        Room2_3_LiptonIcedTea = room2_3_LiptonIcedTea;
    }

    public Items getRoom3_2_WizardWand() {
        return Room3_2_WizardWand;
    }

    public void setRoom3_2_WizardWand(Items room3_2_WizardWand) {
        Room3_2_WizardWand = room3_2_WizardWand;
    }

    public Items getRoom3_5_WizardCloak() {
        return Room3_5_WizardCloak;
    }

    public void setRoom3_5_WizardCloak(Items room3_5_WizardCloak) {
        Room3_5_WizardCloak = room3_5_WizardCloak;
    }

    public Items getRoom3_9_LightSaber() {
        return Room3_9_LightSaber;
    }

    public void setRoom3_9_LightSaber(Items room3_9_LightSaber) {
        Room3_9_LightSaber = room3_9_LightSaber;
    }

    public Items getRoom3_9_AliensHat() {
        return Room3_9_AliensHat;
    }

    public void setRoom3_9_AliensHat(Items room3_9_AliensHat) {
        Room3_9_AliensHat = room3_9_AliensHat;
    }

    public Items getRoom3_6_BigBopper() {
        return Room3_6_BigBopper;
    }

    public void setRoom3_6_BigBopper(Items room3_6_BigBopper) {
        Room3_6_BigBopper = room3_6_BigBopper;
    }

    public Items getRoom3_6_SpaceFood() {
        return Room3_6_SpaceFood;
    }

    public void setRoom3_6_SpaceFood(Items room3_6_SpaceFood) {
        Room3_6_SpaceFood = room3_6_SpaceFood;
    }

    public Items getRoom3_8_InstaProtein() {
        return Room3_8_InstaProtein;
    }

    public void setRoom3_8_InstaProtein(Items room3_8_InstaProtein) {
        Room3_8_InstaProtein = room3_8_InstaProtein;
    }

    public Items getRoom3_7_ChickenNugget() {
        return Room3_7_ChickenNugget;
    }

    public void setRoom3_7_ChickenNugget(Items room3_7_ChickenNugget) {
        Room3_7_ChickenNugget = room3_7_ChickenNugget;
    }

    public Spells getRoom2_3_YeetusDeletus() {
        return Room2_3_YeetusDeletus;
    }

    public void setRoom2_3_YeetusDeletus(Spells room2_3_YeetusDeletus) {
        Room2_3_YeetusDeletus = room2_3_YeetusDeletus;
    }

    public Spells getRoom2_3_EnnieMeenieMinieMo() {
        return Room2_3_EnnieMeenieMinieMo;
    }

    public void setRoom2_3_EnnieMeenieMinieMo(Spells room2_3_EnnieMeenieMinieMo) {
        Room2_3_EnnieMeenieMinieMo = room2_3_EnnieMeenieMinieMo;
    }

    public Spells getRoom2_3_BoogieWoogie() {
        return Room2_3_BoogieWoogie;
    }

    public void setRoom2_3_BoogieWoogie(Spells room2_3_BoogieWoogie) {
        Room2_3_BoogieWoogie = room2_3_BoogieWoogie;
    }

    public Spells getRoom3_2_FocusBeam() {
        return Room3_2_FocusBeam;
    }

    public void setRoom3_2_FocusBeam(Spells room3_2_FocusBeam) {
        Room3_2_FocusBeam = room3_2_FocusBeam;
    }

    public Spells getRoom3_5_Earthquake() {
        return Room3_5_Earthquake;
    }

    public void setRoom3_5_Earthquake(Spells room3_5_Earthquake) {
        Room3_5_Earthquake = room3_5_Earthquake;
    }

    public Spells getRoom3_1_ManaThrow() {
        return Room3_1_ManaThrow;
    }

    public void setRoom3_1_ManaThrow(Spells room3_1_ManaThrow) {
        Room3_1_ManaThrow = room3_1_ManaThrow;
    }

    public Items getRoom4_4_C_I_T_Shirt() {
        return Room4_4_C_I_T_Shirt;
    }

    public void setRoom4_4_C_I_T_Shirt(Items room4_4_C_I_T_Shirt) {
        Room4_4_C_I_T_Shirt = room4_4_C_I_T_Shirt;
    }

    public Items getRoom4_4_BlueEyes() {
        return Room4_4_BlueEyes;
    }

    public void setRoom4_4_BlueEyes(Items room4_4_BlueEyes) {
        Room4_4_BlueEyes = room4_4_BlueEyes;
    }

    public Items getRoom4_2_Nokia() {
        return Room4_2_Nokia;
    }

    public void setRoom4_2_Nokia(Items room4_2_Nokia) {
        Room4_2_Nokia = room4_2_Nokia;
    }

    public Items getRoom4_1_UnoReverse() {
        return Room4_1_UnoReverse;
    }

    public void setRoom4_1_UnoReverse(Items room4_1_UnoReverse) {
        Room4_1_UnoReverse = room4_1_UnoReverse;
    }

    public Items getRoom4_3_BunnyEars() {
        return Room4_3_BunnyEars;
    }

    public void setRoom4_3_BunnyEars(Items room4_3_BunnyEars) {
        Room4_3_BunnyEars = room4_3_BunnyEars;
    }

    public Spells getRoom4_3_Laser() {
        return Room4_3_Laser;
    }

    public void setRoom4_3_Laser(Spells room4_3_Laser) {
        Room4_3_Laser = room4_3_Laser;
    }

    public Spells getRoom4_4_Screech() {
        return Room4_4_Screech;
    }

    public void setRoom4_4_Screech(Spells room4_4_Screech) {
        Room4_4_Screech = room4_4_Screech;
    }

    public Spells getRoom4_3_TakeItBackNowYall() {
        return Room4_3_TakeItBackNowYall;
    }

    public void setRoom4_3_TakeItBackNowYall(Spells room4_3_TakeItBackNowYall) {
        Room4_3_TakeItBackNowYall = room4_3_TakeItBackNowYall;
    }

    public Spells getRoom4_4_Skert() {
        return Room4_4_Skert;
    }

    public void setRoom4_4_Skert(Spells room4_4_Skert) {
        Room4_4_Skert = room4_4_Skert;
    }

    public Spells getRoom4_4_FullHeal() {
        return Room4_4_FullHeal;
    }

    public void setRoom4_4_FullHeal(Spells room4_4_FullHeal) {
        Room4_4_FullHeal = room4_4_FullHeal;
    }

    public Items getRoom5_5_Bleach() {
        return Room5_5_Bleach;
    }

    public void setRoom5_5_Bleach(Items room5_5_Bleach) {
        Room5_5_Bleach = room5_5_Bleach;
    }

    public Items getRoom5_4_ChickenTender() {
        return Room5_4_ChickenTender;
    }

    public void setRoom5_4_ChickenTender(Items room5_4_ChickenTender) {
        Room5_4_ChickenTender = room5_4_ChickenTender;
    }

    public Spells getRoom5_3_ManaRestore() {
        return Room5_3_ManaRestore;
    }

    public void setRoom5_3_ManaRestore(Spells room5_3_ManaRestore) {
        Room5_3_ManaRestore = room5_3_ManaRestore;
    }

    public Spells getRoom5_3_TheGodPiercer() {
        return Room5_3_TheGodPiercer;
    }

    public void setRoom5_3_TheGodPiercer(Spells room5_3_TheGodPiercer) {
        Room5_3_TheGodPiercer = room5_3_TheGodPiercer;
    }

    public long getTime2() {
        return time2;
    }

    public void setTime2(long time2) {
        this.time2 = time2;
    }

    public long getTimeNow2() {
        return timeNow2;
    }

    public void setTimeNow2(long timeNow2) {
        this.timeNow2 = timeNow2;
    }

    public long getTimeOfLastShot2() {
        return timeOfLastShot2;
    }

    public void setTimeOfLastShot2(long timeOfLastShot2) {
        this.timeOfLastShot2 = timeOfLastShot2;
    }

    public boolean isSimonFirst() {
        return simonFirst;
    }

    public void setSimonFirst(boolean simonFirst) {
        this.simonFirst = simonFirst;
    }

    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public int getSpot2() {
        return spot2;
    }

    public int getSpotHolder() {
        return spotHolder;
    }

    public void setSpotHolder(int spotHolder) {
        this.spotHolder = spotHolder;
    }

    public int getHighest() {
        return highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    public Block getSimonBlock() {
        return simonBlock;
    }

    public void setSimonBlock(Block simonBlock) {
        this.simonBlock = simonBlock;
    }

    public Items getRoom5_6_OrbR() {
        return Room5_6_OrbR;
    }

    public void setRoom5_6_OrbR(Items room5_6_OrbR) {
        Room5_6_OrbR = room5_6_OrbR;
    }

    public Items getRoom5_6_OrbD() {
        return Room5_6_OrbD;
    }

    public void setRoom5_6_OrbD(Items room5_6_OrbD) {
        Room5_6_OrbD = room5_6_OrbD;
    }

    public TextBox getBc1() {
        return bc1;
    }

    public void setBc1(TextBox bc1) {
        this.bc1 = bc1;
    }

    public TextBox getBc2() {
        return bc2;
    }

    public void setBc2(TextBox bc2) {
        this.bc2 = bc2;
    }

    public TextBox getBc3() {
        return bc3;
    }

    public void setBc3(TextBox bc3) {
        this.bc3 = bc3;
    }

    public TextBox getBc4() {
        return bc4;
    }

    public void setBc4(TextBox bc4) {
        this.bc4 = bc4;
    }

    public TextBox getBc5() {
        return bc5;
    }

    public void setBc5(TextBox bc5) {
        this.bc5 = bc5;
    }

    public ArrayList<String> getSimonKeysList() {
        return simonKeysList;
    }

    public void setSimonKeysList(ArrayList<String> simonKeysList) {
        this.simonKeysList = simonKeysList;
    }

    public long getTimeOfLastShot() {
        return timeOfLastShot;
    }

    public void setTimeOfLastShot(long timeOfLastShot) {
        this.timeOfLastShot = timeOfLastShot;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTimeNow() {
        return timeNow;
    }

    public void setTimeNow(long timeNow) {
        this.timeNow = timeNow;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public boolean isPlayingGame() {
        return playingGame;
    }

    public void setPlayingGame(boolean playingGame) {
        this.playingGame = playingGame;
    }
}
