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
import java.util.concurrent.ThreadLocalRandom;


public class Game extends Canvas implements Runnable, Serializable {


    /**
     *
     */
    private static final long serialVersionUID  = -1300776496297261616L;


    // height is 960
    public static final float WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;

    //temp

    private transient Thread thread;
    private boolean running = false;
    private transient BufferedImage spriteSheet = null;
    private Handler handler;
    private Player player;
    private NPC npc;
    static Textures tex;
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
    transient private HashMap<String, BufferedImage> rooms = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> ranges=new HashMap<>();
    private String currentRoom="Room4_4";
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


    private GamePlayer gp;
    private int score=0;
    private int level=1;
    private Spawn spawn;
    private int gameHealth;

    public enum STATE {
        Menu,
        FirstArea,
        Battle,
        GameOver,
        Normal,
        SpecialGame;
    };


    private Window window;

    public Game() {




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



        window = new Window(WIDTH, HEIGHT, "The Reign of Big Chungus", this);
        handler = new Handler(this);
        //loadSave();
        tbHandler = new TBHandler();
        currentState=null;


        inv = new Inventory(this);

        magic = new Magic();

        //loadSave();
        knuckles1 = new Enemy(5760, 385, 100, 52, handler, this, ID.Knuckles, 2, tbHandler, "Ugandan Knuckles", player, ID.Knuckles,20,20,4,tex.Knuckles_BattleForm,Color.red,100);
        bigChungus = new Enemy(5760, 375, 433, 225, handler, this, ID.BigChungus, 7, tbHandler, "Big Chungus", player, ID.BigChungus,100,100,4,tex.BigChungusBF[0],Color.gray,1000);
        pikachu=new Enemy(675,40,125,77,handler,this,ID.Pikachu,1,tbHandler,"Surprised Pikachu",player,ID.Pikachu,50,50, 4,tex.PikachuBF[0],Color.yellow,250);
        antihero=new Enemy(675,40,getPlayer().getHeight(),getPlayer().getWidth(),getHandler(),this,ID.AntiHero,1,getTbHandler(),"The Antihero",getPlayer(),ID.AntiHero, (int)(getPlayer().getAttack()*1.5), getPlayer().getDefense()*2, 4, tex.AntiHeroBF, Color.white,getPlayer().getHealth()*2);
        gp=new GamePlayer(0,0,0,0,ID.GamePlayer,getHandler(),this);
        spawn=new Spawn(getHandler(), this);
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
                currentState = STATE.FirstArea;
                currentArea="wae";
                camera = new Camera(0,0,this);
                camera.updateRange("Room1_1");
                player = new Player(900, 100, 92, 23, handler, this, ID.Player, 2,inv, magic);
                player.setInv(inv);
                player.setMagic(magic);
                System.out.println(player);
                System.out.println(player.getMagic());

                player.getMagic().addItem(new Spells("Auto kill", "Yeet and Delete", player.getMagic(),0,0,0,0,ID.Spell,this,0,false,false,0,0));

                // items and spells addition
                Room2_2_Basic_Heal = new Spells("Basic Heal", "Heals 5 HP uses 5 Mana","", player.getMagic(),800,150,32,32,ID.Spell,this,5,true,false,5,0);
                Room2_3_BoogieWoogie=new Spells("Boogie Woogie", "Increase attack by 5. Uses 10 mana","", player.getMagic(),660,2780,32,32,ID.Spell,this,10,false,false,0,0);
                Room2_3_YeetusDeletus=new Spells("Yeetus Deletus", "Will do 15 damage. 15 Mana","", player.getMagic(),5760,5860,32,32,ID.Spell,this,15,false,true,0,15);
                Room2_3_EnnieMeenieMinieMo=new Spells("Enie Meni Minie Mo", "May hurt you, may hurt the enemy. 20 Mana","", player.getMagic(),5915,115,32,32,ID.Spell,this,20,false,false,0,0);
                Room2_3_EmptyWaterBottle=new Items("Empty Water Bottle","Yeet it for 5-10 damage",900,1700,32,32,ID.Item,this,0,0,false,false,0,0);
                Room2_3_LiptonIcedTea = new Items("Lipton Iced Tea","Heals 14",5900,2020,32,32,ID.Item,this,0,0,true,false,14,0);
                Room2_3_Tidepod=new Items("Tide Pod","Heals 5",80,5920,32,32,ID.Item,this,0,0,true,false,5,0);

                Room3_9_AliensHat=new Items("Alien Hat", "+20 defense","A hat that says I love aliens.",450,3760,16,16,ID.Item,this,0,0,false,false,20,0);
                Room3_9_LightSaber=new Items("Light Saber", "+20 attack","An actual hecking light saber.",450,3760,16,16,ID.Item,this,0,0,false,false,0,20);
                Room3_1_ManaThrow=new Spells("Mana Throw", "Uses all mana. 1 mana = 1 damage point","", player.getMagic(),2055,1780,32,32,ID.Spell,this,0,false,true,0,0);
                Room3_2_WizardWand=new Items("Wizard Wand","+5 attack","A basic wizard's wand",1770,1750,32,32,ID.Item,this,0,0,false,false,0,5);
                Room3_5_Earthquake=new Spells("Earthquake", "5 damage 20 Mana, ","(50 damage against Electric types).",player.getMagic(),980,760,32,32,ID.Spell,this,20,false,true,0,0);
                Room3_5_WizardCloak=new Items("Wizard Cloak", "+5 defense","A typical wizard's cloak.",700,960,32,32,ID.Item,this,0,0,false,false,5,0);
                Room3_7_ChickenNugget=new Items("Chicken Nugget", "Adds 30% of your health back","A tasty nugget",1780,275,32,32,ID.Item,this,0,0,true,false,(int)(player.getMaxHealth()*.3),0);
                Room3_8_InstaProtein=new Items("Insta Protein", "+15 attack for this battle","Will taste gross but is worth it",1760,275,32,32,ID.Item,this,0,0,false,false,0,0);
                Room3_2_FocusBeam=new Spells("Focus Beam", "Double Damage with 15 mana","Focus your energy!", player.getMagic(),1150,650,32,32,ID.Spell,this,15,false,true,0,0);
                Room3_6_BigBopper=new Items("Big Bopper","30 HP heal","Overpriced ice cream truck food",2100,3820,32,32,ID.Item,this,0,0,true,false,30,0);
                Room3_6_SpaceFood=new Items("Space Food", "Not quite sure...", "Is this even space food?",2620,120,32,32,ID.Item,this,0,0,true,false,player.getMaxHealth(),0);

                Room4_1_UnoReverse=new Items("Uno Reverse", "Reverses an attack for double the damage","Enemy attack times 2 against the enemy",2820,1380,16,16,ID.Item,this,0,0,false,true,0,0);;
                Room4_2_Nokia=new Items("Nokia", "+25 attack","An unbreakable phone that can break bones.",480,3740,16,16,ID.Item,this,0,0,false,false,0,25);
                Room4_3_BunnyEars=new Items("Bunny Ears", "+30 defense","Suspiciously odd looking ears",2515,3480,16,16,ID.Item,this,0,0,false,false,30,0);;
                Room4_3_Laser=new Spells("Laser", "20-40 damage 30 mana","Pew pew pew pew",player.getMagic(),1355,2052,16,16,ID.Spell,this,30,false,true,0, ThreadLocalRandom.current().nextInt(20,41));
                Room4_3_TakeItBackNowYall=new Spells("Take it back now", "Your max health - your health = damage","y'alllll for 29 mana",player.getMagic(),266,592,16,16,ID.Spell,this,29,false,true,0,0);
                Room4_4_BlueEyes=new Items("YuGiOh Card", "50 Damage","A Blue Eyes White Dragon",3760,160,16,16,ID.Item,this,0,0,false,true,0,50);
                Room4_4_C_I_T_Shirt=new Items("CIT Shirt", "+0 defense","Counselor In Training Shirt",3620,7760,16,16,ID.Item,this,0,0,false,false,20,0);
                Room4_4_FullHeal=new Spells("Full Heal", "Fully heals ","200 mana",player.getMagic(),100,7840,16,16,ID.Spell,this,200,true,false,0,0);
                Room4_4_Screech=new Spells("Screech", "1-200 damage 75 mana","Releases a loud sound to hurt ears.",player.getMagic(),400,4260,16,16,ID.Spell,this,75,false,true,0,ThreadLocalRandom.current().nextInt(1,201));
                Room4_4_Skert=new Spells("Skert", "1-20 damage 9 mana","Might barely do anything idk lol.",player.getMagic(),2280,4080,16,16,ID.Spell,this,9,false,true,0,ThreadLocalRandom.current().nextInt(1,21));

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




                player.getInv().addItem(Room2_3_LiptonIcedTea);
                player.getInv().addItem(Room2_3_Tidepod);
                player.getMagic().addItem(Room2_2_Basic_Heal);
                player.getMagic().addItem(Room2_3_YeetusDeletus);
                aliensFreed=false;
                A51_Switch1=false;
                A51_Switch2=false;
                A51_Switch3=false;
                A51_Switch4=false;

                A51_RSwitch1=new NPC(750,500,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.redSwitch1);
                A51_RSwitch2=new NPC(3840,320,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.redSwitch2);
                A51_RSwitch3=new NPC(245,1400,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.redSwitch3);
                A51_RSwitch4=new NPC(80,2200,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.redSwitch4);

                A51_Switch5=false;
                A51_Switch6=false;
                A51_Switch7=false;
                A51_Switch8=false;

                // 3-6
                A51_RSwitch5=new NPC(4200,2740,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.redSwitch5);
                // 3-7
                A51_RSwitch6=new NPC(500,1760,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.redSwitch6);
                // 3-8
                A51_RSwitch7=new NPC(280,1755,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.redSwitch7);
                //3-6
                A51_RSwitch8=new NPC(6300,3600,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.redSwitch8);

                A51_GSwitch1=new NPC(1800,80,47,43,handler,this,ID.NPC,1,tbHandler,"Click!",player,ID.greenSwitch);

                who1=new NPC(1730,3780,128,128,handler,this,ID.NPC,1,tbHandler,"Do we know eachother? It feels like we do. But from where? I can't put my finger on it. Although I don't have any fingers. I'm kinda a blob of stuff.",player,ID.WHO1);;
                who2=new NPC(1540,100,128,128,handler,this,ID.NPC,1,tbHandler,"There are some weird things going on around here... For some reason I feel like I shouldn't be helping you but uh.. you seem nice!",player,ID.WHO2);
                whoT1=new TextBox(who1,"Woah woah buddy put your wand down! I'm not one of them bud. Actually I don't know what I am. I can't really remember too much of anything. My name or why I feel like I should know who you are.",1730,3780,1,1500,ID.TextBox,tbHandler);
                whoT2=new TextBox(who2,"Hey bud, so uh. The one thing I know is that this place is wack. Everything is a bit off here so... I have an idea. I'll give you a walkie talkie so I can help you out! We could even... be friends!",1540,100,1,1500,ID.TextBox,tbHandler);
                barrier=new Block(7280,64, 64, 675, handler, this, ID.sideRail);
                specialGameWin=false;

            }if(currentState==STATE.FirstArea && !Switch){
               // handler.clear();





                // only for rooms that you can get into a battle in
                // NOT passive rooms
                if(currentRoom.equals("Room1_1")) {
                    currentLevel = tex.SS_FirstArea.grabImage(1, 1, 64, 64);
                    //currentRoom = "Room1_1";

                    loadLevel(currentRoom);
                    //TPoser tposer = new TPoser(900,900, 184, 184, handler, this, ID.TPoser, 7, tbHandler, "DOY DOY DOY", player, true);
                    //handler.addObject(tposer);

                    //npc = new NPC(400, 200, 19, 74, handler, this, ID.NPC, 2, tbHandler, "asdfasdfasdfasdfja;sldkjfaslkdjfasldf;a", player, ID.TheLastEntity);
                    //System.out.println("here");

                    //handler.addObject(knuckles1);
                    //Pikachu pikachu = new Pikachu(800, 500, 96, 48, handler, this, ID.Pikachu, 2, tbHandler, "Pikaaachuuu", player, true);
                    tex = getInstance();
                    bigChungus = new Enemy(5760, 375, 433, 225, handler, this, ID.BigChungus, 7, tbHandler, "Big Chungus", player, ID.BigChungus, 100, 100, 4, tex.BigChungusBF[0], Color.gray, 1000);
                    // handler.addObject(pikachu);
                    //handler.addObject(npc);
                    //handler.addObject(bigChungus);
                    handler.addObject(player);
                    //handler.addObject(new Transition(200, 1000, 64, 64, ID.Transition, "First"));
                    so = new SaveObject(725, 25, 64, 64);
                    handler.addObject(so);

                }


                handler.clear();
                loadLevel(currentRoom);
                handler.addObject(player);





                this.keyInput = new KeyInput(handler, player, tbHandler, player.getInv(),getGp(),this);

                this.addKeyListener(keyInput);

                Switch=true;
            }else if(currentState==STATE.FirstArea && Switch==true){
                player.setHealth((int)clamp(player.getHealth(),0,player.getMaxHealth()));
                player.setMana((int)clamp(player.getMana(),0,player.getMaxMana()));
                System.out.println("X: "+player.getX()+" Y: "+player.getY());

                for(int i = 0; i < handler.object.size(); i++){
                    if(handler.object.get(i).getId() == ID.Player){
                        camera.tick(handler.object.get(i));
                    }
                }



                handler.tick();
                tbHandler.tick();
                try{
                    //System.out.println(magic);
                    //System.out.println(inv);
                    if(player.getInv().getOpen()){
                        player.getInv().tick();

                    }else if(player.getMagic().getOpen())
                        player.getMagic().tick();
                }catch (Exception e){
                    player.setInv(inv);
                    player.setMagic(magic);
                }


            }else if(currentState==STATE.Battle && !Switch){
                handler.clear();
                this.removeKeyListener(keyInput);

                Switch = true;
                //create new battle object or get the battle object from player (defined by collision)

            }else if(currentState==STATE.Battle && Switch ){
                player.setHealth((int)clamp(player.getHealth(),0,player.getMaxHealth()));
                player.setMana((int)clamp(player.getMana(),0,player.getMaxMana()));
                handler.tick();
                currentBattle.tick();
            }else if(currentState==STATE.GameOver && !Switch){

            }else if(currentState==STATE.GameOver && Switch){

            }else if(currentState==STATE.Normal && !Switch){
               // System.out.println(currentLevel);
                handler.clear();
                loadLevel(currentRoom);


                //setCurrentBattle(null);



                player = new Player(player.getX(), player.getY(), 92, 25, handler, this, ID.Player, 2,inv, magic);
                handler.addObject(player);
                camera = new Camera(0,0,this);
                camera.updateRange(currentRoom);
                this.addKeyListener(new KeyInput(handler, player, tbHandler, player.getInv(),getGp(),this));
                Switch=true;

            }else if(currentState==STATE.Normal && Switch){
                for(int i = 0; i < handler.object.size(); i++){
                    if(handler.object.get(i).getId() == ID.Player){
                        camera.tick(handler.object.get(i));
                    }
                }
                handler.tick();
                tbHandler.tick();

                if(player.getInv().getOpen()){
                    player.getInv().tick();
                }else if(player.getMagic().getOpen())
                    player.getMagic().tick();

            }else if(currentState==STATE.SpecialGame){
                handler.tick();
                spawn.tick();
                if(getGameHealth()<=0){
                    setCurrentState(STATE.GameOver);
                    setSwitch(false);
                }
                if(getScore()>=5000){
                    specialGameWin=true;
                    handler.clear();
                    AudioPlayer.getMusic("GAME").stop();
                    currentState=STATE.FirstArea;
                    loadLevel(getCurrentRoom());
                    handler.addObject(getPlayer());
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




            handler.render(g);

            g2d.translate(camera.getX(), camera.getY());
            tbHandler.render(g);

            try{
                if(player.getInv().getOpen()){
                    player.getInv().render(g);
                    player.drawHealthBar(8,540,g);
                    player.drawManaBar(8,600,g);

                    fnt = new Font("Serif", 0, 20);
                    g.setFont(fnt);
                    g.setColor(Color.white);
                    g.drawString("HP", 8,530);
                    g.setColor(Color.white);

                    fnt = new Font("Serif", 1, 16);
                    g.setFont(fnt);
                    g.drawString(player.getHealth() + " / " + player.getMaxHealth(), 215, 565);



                    fnt = new Font("Serif", 0, 20);
                    g.setFont(fnt);
                    g.setColor(Color.white);
                    g.drawString("Mana", 8,595);
                    g.setColor(Color.white);

                    fnt = new Font("Serif", 1, 16);
                    g.setFont(fnt);
                    g.drawString(player.getMana() + " / " + player.getMaxMana(), 215, 620);

                }else if(player.getMagic().getOpen()){
                    player.getMagic().render(g);
                    player.drawHealthBar(8,540,g);
                    player.drawManaBar(8,600,g);

                    fnt = new Font("Serif", 0, 20);
                    g.setFont(fnt);
                    g.setColor(Color.white);
                    g.drawString("Mana", 8,595);
                    g.setColor(Color.white);

                    fnt = new Font("Serif", 1, 16);
                    g.setFont(fnt);
                    g.drawString(player.getMana() + " / " + player.getMaxMana(), 215, 620);

                    fnt = new Font("Serif", 0, 20);
                    g.setFont(fnt);
                    g.setColor(Color.white);
                    g.drawString("HP", 8,530);
                    g.setColor(Color.white);

                    fnt = new Font("Serif", 1, 16);
                    g.setFont(fnt);
                    g.drawString(player.getHealth() + " / " + player.getMaxHealth(), 215, 565);
                }
            }catch (Exception e){
                e.printStackTrace();
                player.setInv(new Inventory(this));

                player.setMagic(new Magic());
                player.getMagic().addItem(new Spells("Auto kill", "Yeet and Delete", magic,0,0,0,0,ID.Spell,this,0,false,false,0,0));

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
            if(player.getBackwards()){
                g.setColor(Color.white);
            }else{
                g.setColor(Color.black);
            }

            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
            handler.render(g);
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


            handler.render(g);
            g2d.translate(camera.getX(), camera.getY());
            tbHandler.render(g);

            if(player.getInv().getOpen()){
                player.getInv().render(g);
            }else if(player.getMagic().getOpen()){
                player.getMagic().render(g);
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
            handler.render(g);

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

    public void loadLevel(String room){


       // Graphics g = bs.getDrawGraphics();
        BufferedImage image = rooms.get(room);
        //System.out.println(rooms.get(room));
        //System.out.println(tex.Room2_1);
       // System.out.println(image);
        int w = image.getWidth();
        int h = image.getHeight();


        for(int xx = 0; xx < w; xx++){
            for(int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == 255&&green==0&&blue==0){
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.GrayGround));
                }
                else if (green == 255&&red==0&&blue==0) {
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.BlackGround));
                }else if(blue==255&&red==0&&green==0){
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Pavement));
                }else if(red==57&&green==255&&blue==50){
                   // g.drawImage();
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Grass));
                    // grass
                }else if(red==191&&green==0&&blue==149){
                    //tree
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Tree));
                }else if(red==2&&green==2&&blue==40){
                    // invisible block to stop during ice puzzles???
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.invisWall));


                }else if(red==255&&green==230&&blue==0){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Mushroom));

                }else if(red==255&&green==111&&blue==0){
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.RedGround));

                }else if(red==0 && green==255 && blue==100){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.sideRail));
                }else if(red==100 && green==100 && blue==0){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.bottomRail));
                }else if(red==0 && green==100 && blue==255){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Ice));
                }
            }
        }
        if(currentRoom.equals("Room1_1")){
            handler.addObject(new NPC(1050,500,64,64,handler,this,ID.NPC,3,tbHandler,"You... are the last of us. I am unable to continue to fight him. My last gift to you... the prophecy.",player,ID.TheLastEntity));
            handler.addObject(new NPC(750,500,48,32,handler,this,ID.NPC,3,tbHandler,"The Chosen One must travel through The Wae, The Labyrinth, sdrawkcaB ehT, and the Land of Dead Memes in order to..... (The rest is cut out) What will you choose?",player, ID.Book));
            // transition object to room 2_2
            handler.addObject(new Transition(835,1975,32,100,ID.Transition,"Room2_1",950,100,player,true,"wae"));
            camera.updateRange("Room1_1");


            handler.addObject(new Enemy(700, 500, 92, 25, handler, this, ID.Spaget, 2, tbHandler, "Spaget", player, ID.Spaget,20,20,2,tex.SpagetBF,Color.green,50));
            handler.addObject(new Enemy(800, 500, 96, 48, handler, this, ID.Arthur, 1, tbHandler, "Arthur", player, ID.Arthur,20,20,2,tex.ArthurBF,new Color(255,224,189),40));


        }else if(currentRoom.equals("Room2_1")){

            setBackground(Color.black);
            loadLevel(tex.Room2_1O);
            handler.addObject(new Transition(835,0,32,300,ID.Transition, "Room1_1",900,1800,player,false,"wae"));
            handler.addObject(new Transition(0,130,70,32,ID.Transition, "Room2_2",1100,130,player,false,"wae"));
            handler.addObject(new Transition(900,1925,32,200,ID.Transition,"Room2_3",3000,200,player,true,"wae"));
            camera.updateRange("Room2_1");
        }else if(currentRoom.equals("Room2_2")){
            setBackground(Color.black);
            loadLevel(tex.Room2_2O);
            // add transistion back lol
            handler.addObject(new Transition(1175,105,70,32,ID.Transition, "Room2_1",60,155,player,true,"wae"));

            camera.updateRange("Room2_2");
            handler.addObject(Room2_2_Basic_Heal);

        }else if(currentRoom.equals("Room2_3")){
            setBackground(Color.black);
            loadLevel(tex.Room2_3O);
            camera.updateRange("Room2_3");
            handler.addObject(new Transition(2600,100,32,700,ID.Transition, "Room2_1",975,1800,player,true,"wae"));
            handler.addObject(new Transition(2050,6025,32,400,ID.Transition,"Room2_4",480,60,player,false,"wae"));
            // coords for room2_4 transistion
            // 2050,6025

            // item and spell additions

            handler.addObject(Room2_3_BoogieWoogie);
            handler.addObject(Room2_3_EmptyWaterBottle);
            handler.addObject(Room2_3_EnnieMeenieMinieMo);
            handler.addObject(Room2_3_LiptonIcedTea);
            handler.addObject(Room2_3_Tidepod);
            handler.addObject(Room2_3_YeetusDeletus);


        }else if(currentRoom.equals("Room2_4")){
            handler.addObject(new Transition(0,30,32,1000,ID.Transition,"Room2_3",2250,5900,player,true,"wae"));

            if(!knuckles1.isDefeated()){
                handler.addObject(knuckles1);
                handler.addObject(new TextBox(null,"CLUCK CLUCK CLUCK (I know who you are.)",1120,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(null,"CLUCK CLUCK CLUCK (I know what you are going to try to do.)",1740,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(null,"CLUCK CLUCK CLUCK (I will give you a choice to turn back now before it is too late.)",2360,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(knuckles1,"CLUCK CLUCK CLUCK (I'm sorry, but I must protect him with my life)",5180,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(knuckles1,"CLUCK CLUCK (I, Ugandan Knuckles will stop you. Approach me and seek destruction.)",5560,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(player, "The Wae Forest has been cleared! Knuckles has been set free of the hex. Let's keep moving into Area 51.",5850,0,1000,16,ID.TextBox,tbHandler));

            }

            handler.addObject(new SaveObject(5280,200,64,64));


            handler.addObject(new Transition(6050,0,1000,16,ID.Transition,"Room3_1",50,3540,player,true,"51"));




            setBackground(Color.black);
            loadLevel(tex.Room2_4O);
            camera.updateRange("Room2_4");
            //handler.addObject(knuckles1);
        }else if(currentRoom.equals("Room3_1")){
            //endangered=true;
            setBackground(Color.black);
            loadLevel(tex.Room3_1O);
            camera.updateRange("Room3_1");
            if(!aliensFreed){
                handler.addObject(new NPC(3250,550,129,159,handler,this,ID.NPC,1,tbHandler,"Help!",player,ID.Alien1));
                handler.addObject(new NPC(3250,1480,180,180,handler,this,ID.NPC,1,tbHandler,"Hey, you're the hero aren't you? How bout you help us out? Find the GREEN switch that takes off our electric collars and we are out of here! We promise it will be worth it if you help us out.",player,ID.Alien2));
                handler.addObject(new NPC(3250,2680,150,123,handler,this,ID.NPC,1,tbHandler,"I just wish I didn't have to stay here, could you help us out? My boss above me here can't hear or see well. But if you manage to get close to him, he can tell you how to get us out of here!",player,ID.Alien3));
            }

            handler.addObject(new Transition(0,3280,1000,16,ID.Transition,"Room2_4",6000,400,player,false,"wae"));
            handler.addObject(new Transition(0,0,16,1000,ID.Transition,"Room3_3",90,1800,player,true,"51"));
            handler.addObject(new Transition(3960,320,400,20,ID.Transition,"Room3_4",50,675,player,true,"51"));
            handler.addObject(new Transition(3960,1220,400,20,ID.Transition,"Room3_4",50,1305,player,true,"51"));
            handler.addObject(new Transition(3960,2560,400,20,ID.Transition,"Room3_4",50,2045,player,true,"51"));
            handler.addObject(new Transition(3480,4000,32,100,ID.Transition,"Room3_5",980,20,player,true,"51"));
            handler.addObject(new NPC(200,3160,78,85,handler,this, ID.NPC, 1,tbHandler,"Hey what's up? Welcome to Area 51! I don't mind if you have a look around here. Just ya know. There are 4 red switches which can you hit to get to another sector if you want. Do NOT hit the green switch.",player,ID.Guard1));
            handler.addObject(Room3_1_ManaThrow);
            handler.addObject(A51_RSwitch2);


        }else if(currentRoom.equals("Room3_2")){

            handler.addObject(new Transition(1940,0,256,16,ID.Transition,"Room3_3",50,350,player,true,"51"));
            setBackground(Color.black);
            loadLevel(tex.Room3_2O);
            camera.updateRange("Room3_2");
            handler.addObject(Room3_2_WizardWand);
            handler.addObject(Room3_2_FocusBeam);
            handler.addObject(A51_RSwitch3);

        }else if(currentRoom.equals("Room3_3")){
            handler.addObject(new Transition(10,260,1000,16,ID.Transition,"Room3_2",1850,90,player,true,"51"));

            handler.addObject(new Transition(0,1900,16,1000,ID.Transition,"Room3_1",420,25,player,true,"51"));

            handler.addObject(new Transition(6050,1280,500,16,ID.Transition,"Room3_4",200,200,player,true,"51"));
            handler.addObject(new NPC(5770,175,77,55,handler,this,ID.NPC,1,tbHandler,"Hey wassup? If you can find the four red switches then you can open this door... but I forgot where they are. Just don't hit any green switches if you find any.",player, ID.Guard2));
            if(!(A51_Switch1 && A51_Switch2 && A51_Switch3 && A51_Switch4)){
                handler.addObject(new Block(5700,80, 64, 350, handler, this, ID.sideRail));
            }else
                handler.addObject(new Transition(5700,20,32,500,ID.Transition,"Room3_6",7500,3800,player,true,"51"));
            setBackground(Color.black);
            loadLevel(tex.Room3_3O);
            camera.updateRange("Room3_3");


        }else if(currentRoom.equals("Room3_4")){
            handler.addObject(new Transition(0,200,300,1,ID.Transition,"Room3_3",6000,1325,player,true,"51"));
            handler.addObject(new Transition(0,600,300,16,ID.Transition,"Room3_1",3900,500,player,false,"51"));
            handler.addObject(new Transition(0,1280,300,16,ID.Transition,"Room3_1",3900,1485,player,false,"51"));
            handler.addObject(new Transition(0,1960,300,16,ID.Transition,"Room3_1",3900,2665,player,false,"51"));

            setBackground(Color.black);
            loadLevel(tex.Room3_4O);
            camera.updateRange("Room3_4");

            handler.addObject(A51_RSwitch4);

        }else if(currentRoom.equals("Room3_5")){
            handler.addObject(new Transition(800,0,8,500,ID.Transition,"Room3_1",3475,3900,player,true,"51"));
            setBackground(Color.black);
            loadLevel(tex.Room3_5O);
            camera.updateRange("Room3_5");
            handler.addObject(Room3_5_Earthquake);
            handler.addObject(Room3_5_WizardCloak);
            handler.addObject(A51_RSwitch1);

        }else if(currentRoom.equals("Room3_6")){
            handler.addObject(new Transition(0,192,400,2,ID.Transition,"Room3_8",1940,95,player,true,"51"));
            handler.addObject(new Transition(120,4000,2,300,ID.Transition,"Room3_7",160,60,player,true,"51"));
            handler.addObject(new Transition(7200,3980,8,500,ID.Transition,"Room3_3",5855,80,player,true,"51"));

            if(!(A51_Switch5 && A51_Switch6 && A51_Switch7 && A51_Switch8)){
                handler.addObject(barrier);
                handler.addObject(new NPC(7300,150,92,84,handler,this,ID.NPC,1,tbHandler,"Hiya hero. Sorry I can't remember the password to open the door... sorry bout that. If you can find 4 new red switches then it'll open up.",player,ID.Guard3));
            }else{
                handler.addObject(new Transition(7300,0,8,1000,ID.Transition,"Room3_9",675,3840,player,true,"51"));
                handler.addObject(new NPC(7300,150,92,84,handler,this,ID.NPC,1,tbHandler,"Oh nice you opened the door! I don't mind if you go through",player,ID.Guard3));

            }
            handler.addObject(Room3_6_BigBopper);
            handler.addObject(Room3_6_SpaceFood);
            handler.addObject(A51_RSwitch5);
            handler.addObject(A51_RSwitch8);

            setBackground(Color.black);
            loadLevel(tex.Room3_6O);
            camera.updateRange("Room3_6");

            // 3 new transitions

        }else if(currentRoom.equals("Room3_8")){
            handler.addObject(new Transition(2000,0,300,500,ID.Transition,"Room3_6",40,262,player,true,"51"));

            setBackground(Color.black);
            loadLevel(tex.Room3_8O);
            camera.updateRange("Room3_8");
            handler.addObject(Room3_8_InstaProtein);
            handler.addObject(A51_RSwitch7);


            // 3 new transitions

        }else if(currentRoom.equals("Room3_7")){


            setBackground(Color.black);
            loadLevel(tex.Room3_7O);
            handler.addObject(Room3_7_ChickenNugget);
            camera.updateRange("Room3_7");
            handler.addObject(new Transition(0,0,1,300,ID.Transition,"Room3_6",240,3860,player,true,"51"));

            handler.addObject(A51_RSwitch6);

            handler.addObject(A51_GSwitch1);


            // 3 new transitions

        }else if(currentRoom.equals("Room3_9")){
            handler.addObject(new Transition(640,4020,1,400,ID.Transition,"Room3_6",7600,100,player,true,"51"));
            handler.addObject(new Transition(640,0,1,400,ID.Transition,"Room4_1",2038,3896,player,true,"back"));

            setBackground(Color.black);
            loadLevel(tex.Room3_9O);
            camera.updateRange("Room3_9");

            if(!(pikachu.isDefeated())){
                handler.addObject(pikachu);
                handler.addObject(new SaveObject(325,200,64,64));
                handler.addObject(new TextBox(null,"Pika Pika (I see that you beat my friend, Knuckles)",0,3580,1,1500,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(null,"Pika Pika (He was a good friend of mine)",0,3100,1,1500,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(null,"Pika Pika (I really only have a single question for you, before you go through with this.)",0,2900,1,1500,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(null,"Pika Pika (Do you fully understand what you are doing? Or is what you have been seeing biased?)",0,2500,1,1500,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(null,"Pika Pika (You won't stop until we're all gone will you? At least stop feeding them lies.)",0,2000,1,1500,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(pikachu,"Pika Pika (It doesn't seem like I'll stop you with my words. Although, I'm actually not surprised. I'm sorry I have to do this.)",0,1200,1,1500,ID.TextBox,tbHandler));
            }


            // 3 new transitions

            if(aliensFreed){
                handler.addObject(new NPC(325,3750,92,84,handler,this,ID.NPC,1,tbHandler,"Hey! Thanks for freeing us!!! As a token of our appreciation take this item right here! I hope it helps.",player,ID.Alien2));

                handler.addObject(Room3_9_AliensHat);
            }else{
                handler.addObject(new NPC(325,3750,92,84,handler,this,ID.NPC,1,tbHandler,"Hey! Thanks for not letting any aliens out. As a token of our appreciation take this item right here!",player,ID.Guard1));

                handler.addObject(Room3_9_LightSaber);
            }




        }else if(currentRoom.equals("Room4_1")){
            handler.addObject(new Transition(1732,4016,1,400,ID.Transition,"Room3_9",680,40,player,true,"51"));
            handler.addObject(new Transition(1520,0,1,1000,ID.Transition,"Room4_2",2040,3800,player,false,"back"));
            handler.addObject(Room4_1_UnoReverse);
            setBackground(Color.black);
            loadLevel(tex.Room4_1O);
            camera.updateRange("Room4_1");



            handler.addObject(who1);
            handler.addObject(who2);

            handler.addObject(whoT1);
            handler.addObject(whoT2);
        }else if(currentRoom.equals("Room4_2")){
            handler.addObject(new TextBox(who1, "BSSSH Huh that's weird. Looks like there's some ice round these parts. Try slipping on it to see what happens! BSSSH over.", 0,3770,1,5000,ID.TextBox,tbHandler));
            handler.addObject(new Transition(1800,3980,1,1000,ID.Transition,"Room4_1",1840,240,player,true,"back"));
            handler.addObject(new Transition(0,1,1,5000,ID.Transition,"Room4_3",1906,3812,player,false,"back"));
            setBackground(Color.black);
            loadLevel(tex.Room4_2O);
            camera.updateRange("Room4_2");
            handler.addObject(Room4_2_Nokia);


        }else if(currentRoom.equals("Room4_3")){
            handler.addObject(new Transition(0,4000,1,5000,ID.Transition,"Room4_2",1942,38,player,false,"back"));
            handler.addObject(new Transition(0,0,1,10000,ID.Transition,"Room4_4",2120,7900,player,true,"back"));

            handler.addObject(new TextBox(who1,"Hmmm... oh no! An ice puzzle!!!! That's not good!",0,3584,1,5000,ID.TextBox,tbHandler));
            setBackground(Color.black);
            loadLevel(tex.Room4_3O);
            camera.updateRange("Room4_3");
            handler.addObject(Room4_3_BunnyEars);
            handler.addObject(Room4_3_Laser);
            handler.addObject(Room4_3_TakeItBackNowYall);



        }else if(currentRoom.equals("Room4_4")){
            setBackground(Color.black);
            loadLevel(tex.Room4_4O);
            camera.updateRange("Room4_4");
            handler.addObject(new TextBox(who1, "BSSSH heyo nice job on that ice puzzle! I have a feeling for some reason that someone is trying to stop you. I feel like I should know this. Hm I'm not sure. Sadly... there seems to be an invisible maze now.. oof",0,7800,1,10000,ID.TextBox,tbHandler));
            handler.addObject(new Transition(0,8020,1,5000,ID.Transition,"Room4_3",1875,100,player,false,"back"));
            handler.addObject(new Transition(0,0,2,10000,ID.Transition,"Room4_5",565,1040,getPlayer(),false,"back"));
            handler.addObject(new Transition(945,3080,2,64,ID.Transition,"Room4_7",945,1820,getPlayer(),false,"back"));
            handler.addObject(Room4_4_BlueEyes);
            handler.addObject(Room4_4_C_I_T_Shirt);
            handler.addObject(Room4_4_FullHeal);
            handler.addObject(Room4_4_Screech);
            handler.addObject(Room4_4_Skert);

        }else if(currentRoom.equals("Room4_5")){
            handler.addObject(new Transition(0,1210,1,10000,ID.Transition,"Room4_4",2040,100,getPlayer(),true,"back"));
            if(!specialGameWin){
                handler.addObject(new Block(20,20,1,1000,getHandler(),this,ID.Mushroom));
                handler.addObject(new TextBox(who1, "Wow you're really killing these puzzles! Looks like there's a computer there with an old game. I think the controls are arrows to move and space to shoot. Get 5000 points to open the next door!",0,1000,1,10000,ID.TextBox,getTbHandler()));
            }
            // this is the GAME ROOM WOOOO
            handler.addObject(new NPC(600,600,159,115,getHandler(),this,ID.NPC,3,getTbHandler(),"Beeep",getPlayer(),ID.Computer));
            handler.addObject(new Transition(0,0,1,10000,ID.Transition,"Room4_6",1880,3860,getPlayer(),false,"back"));

            setBackground(Color.black);
            loadLevel(tex.Room4_5O);
            camera.updateRange("Room4_5");

        }else if(currentRoom.equals("Room4_6")){
            // ANTIHERO BOSS ROOM
            handler.addObject(new Transition(0,4000,1,10000,ID.Transition,"Room4_5",500,100,getPlayer(),true,"back"));



            if(!antihero.isDefeated()){
                handler.addObject(new TextBox(null,"I'm not talking to you, sorcerer. The one controlling your actions. The one who can stop all of this. ",0,1000,1,10000,ID.TextBox,getTbHandler()));
                handler.addObject(new TextBox(null,"I don't think you quite understand what you are doing. Stop now and leave this place. ",0,800,1,10000,ID.TextBox,getTbHandler()));
                handler.addObject(new TextBox(null,"Fine. He figured words wouldn't stop you. So I suppose that's why I'm here. He decided that your worst enemy would be yourself. ",0,600,1,10000,ID.TextBox,getTbHandler()));
                handler.addObject(new TextBox(antihero,"I was created after a lot of failed experimentation. To serve one purpose. To stop you. Or at least stall you until He can figure out what to do. ",0,500,1,10000,ID.TextBox,getTbHandler()));
                handler.addObject(new TextBox(antihero,"I live in a paradox constantly. I either win just to fight you again. Or I lose and perish. No illusions here. You have to kill me to continue.",0,400,1,10000,ID.TextBox,getTbHandler()));
                handler.addObject(new TextBox(antihero,"After all, I am you. I can copy your moves, weapons, attack power, defeense. Everything. And even make it better! Plus ya know. Some fun to spice it up. You can figure it out.",0,300,1,1000,ID.TextBox,getTbHandler()));
                handler.addObject(new TextBox(antihero,"This is how things are now. Just me and you. Go ahead and save. You're going to need it. ",0,200,1,10000,ID.TextBox,getTbHandler()));
                handler.addObject(new TextBox(null,"Turn back NOW.",0,1500,1,10000,ID.TextBox,getTbHandler()));
                handler.addObject(new TextBox(player,"You can't stop me! I know what I am doing is right! ",0,1400,1,10000,ID.TextBox,getTbHandler()));
                antihero=new Enemy(1880,40,getPlayer().getHeight(),getPlayer().getWidth(),getHandler(),this,ID.AntiHero,1,getTbHandler(),"The Antihero",getPlayer(),ID.AntiHero, (int)(getPlayer().getAttack()*1.5), getPlayer().getDefense()*2, 4, tex.AntiHeroBF, Color.white,getPlayer().getHealth()*2);

                handler.addObject(antihero);
            }else{
                handler.addObject(new TextBox(who1,"BSSSSH Come in. Sir, come in! Hello? What's going on? What happened?",0,20,1,10000,ID.TextBox,tbHandler));


                handler.addObject(new TextBox(getPlayer(),"*Drops walkie talkie* Good. Let us enter into the last area. We are almost finished here.",0,0,1,10000,ID.TextBox,tbHandler));
            }


            setBackground(Color.black);
            loadLevel(tex.Room4_6O);
            camera.updateRange("Room4_6");

        }else if(currentRoom.equals("Room4_7")){
            // secret lore room yay
            handler.addObject(new Transition(900,1980,2,128,ID.Transition,"Room4_4",975,3120,getPlayer(),false,"back"));
            handler.addObject(new NPC(965,340,39,28,getHandler(),this,ID.NPC,3,getTbHandler(),"Entry: The meme destroyers are coming. We can only stop their chosen one for so long. We're running out of options and they are lowering our population to such small levels. -BC",getPlayer(),ID.Book));
            handler.addObject(new NPC(700,400,39,28,getHandler(),this,ID.NPC,3,getTbHandler(),"Entry: My people believe in me, but the lengths I've had to go to ensure our safety is troubling. Unlike them, I don't lie to my people to get my way. -BC",getPlayer(),ID.Book));
            handler.addObject(new NPC(800,500,39,28,getHandler(),this,ID.NPC,3,getTbHandler(),"Entry: They are using black magic. Nothing is working against them. I've decided that the only way to stop him is to not let him achieve his goal until he quits. He is unkillable. He is immortal. -BC",getPlayer(),ID.Book));
            handler.addObject(new NPC(1100,340,39,28,getHandler(),this,ID.NPC,3,getTbHandler(),"Entry: The wizards think we are an abomination. They plan to wipe us out completely. I've sent my royal guards to stop him and they have only been slaughtered so far. I miss my friends. -BC",getPlayer(),ID.Book));

            setBackground(Color.black);
            loadLevel(tex.Room4_7O);
            camera.updateRange("Room4_7");
        }

        else if(currentRoom.equals("RoomGame")){

        }

        camera.setX(((player.getX())  ));
        camera.setY(((player.getY())  ));
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
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.GrayGround));
                else if (green == 255&&red==0&&blue==0) {
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.BlackGround));
                }else if(blue==255&&red==0&&green==0){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Pavement));
                }else if(red==57&&green==255&&blue==50){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Grass));
                    // grass
                }else if(red==191&&green==0&&blue==149){
                    //tree
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Tree));
                }else if(red==2&&green==2&&blue==40){
                    // invisible block to stop during ice puzzles???
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.invisWall));


                }else if(red==255&&green==230&&blue==0){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Mushroom));

                }else if(red==255&&green==111&&blue==0){
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.RedGround));

                }else if(red==0 && green==255 && blue==100){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.sideRail));
                }else if(red==100 && green==100 && blue==0){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.bottomRail));
                }else if(red==0 && green==100 && blue==255){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Ice));
                }
            }
        }
        if(currentRoom.equals("Room1_1")){
            handler.addObject(new NPC(1050,500,64,64,handler,this,ID.NPC,3,tbHandler,"You... are the last of us. I am unable to continue to fight him. My last gift to you... the prophecy.",player,ID.TheLastEntity));
            handler.addObject(new NPC(750,500,48,32,handler,this,ID.NPC,3,tbHandler,"The Chosen One must travel through The Wae Forest, Area 51, Eht Sdrawkcab and the Land of Dead Memes to make the ultimate decision. The choice is up to fate. The decision that he will hate.",player, ID.Book));
            // transition object to room 2_2
            handler.addObject(new Transition(835,1975,32,100,ID.Transition,"Room2_1",850,100,player,true,"wae"));
            handler.addObject(new Items("Apple","Good Snack boi",900,1700,64,64,ID.Item,this,0,0,false,false,0,0));



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
        try{



            ObjectInputStream os = new ObjectInputStream(new FileInputStream("game.bin"));
            Game temp = (Game) os.readObject();
            this.currentRoom=temp.currentRoom;
            this.ap=temp.getAp();
            this.ap.load();
            tex = new Textures();
            rooms.put("Testing Room", tex.SS_FirstArea.grabImage(1, 1, 64, 64));
            rooms.put("Room1_1", tex.Room1_1);
            rooms.put("Room2_1",tex.Room2_1);
            rooms.put("Room2_4",tex.Room2_4);
            rooms.put("Room2_3",tex.Room2_3);
            window=new Window(WIDTH, HEIGHT, "The Reign of Big Chungus", this);
            handler = temp.handler;
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
            for(int i = 0; i<handler.object.size(); i++){
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
                }

            }
            player.setGame(this);
            bigChungus.setGame(this);
            knuckles1.setGame(this);
            pikachu.setGame(this);
            npc.setGame(this);
            this.keyInput = new KeyInput(handler, player, tbHandler, player.getInv(),getGp(),this);
            this.addKeyListener(keyInput);
            this.Switch=false;






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
        return rooms;
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
}
