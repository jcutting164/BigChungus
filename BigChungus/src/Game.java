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
    private Magic magic;
    private AudioPlayer ap;
    private Inventory inv;
    private SaveObject so;
    private Enemy knuckles1;
    private Enemy pikachu;
    private Enemy bigChungus;
    transient private HashMap<String, BufferedImage> rooms = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> ranges=new HashMap<>();
    private String currentRoom="Room1_1";


    private boolean endangered=false;

    // obtainable items and spells creation
    Items Room1_1_Apple;
    Spells Room2_2_Basic_Heal;
    Items Room2_3_Tidepod;
    Items Room2_3_EmptyWaterBottle;
    Items Room2_3_LiptonIcedTea;
    Items Room2_3_WizardWand;
    Items Room2_3_WizardCloak;
    Spells Room2_3_YeetusDeletus;
    Spells Room2_3_EnnieMeenieMinieMo;
    Spells Room2_3_BoogieWoogie;



    public enum STATE {
        Menu,
        FirstArea,
        Battle,
        GameOver,
        Normal;
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
        ranges.get("Room3_1").add((3380));
        ranges.get("Room3_1").add(3100);

        window = new Window(WIDTH, HEIGHT, "The Reign of Big Chungus", this);
        handler = new Handler(this);
        //loadSave();
        tbHandler = new TBHandler();
        currentState=null;


        inv = new Inventory();

        magic = new Magic();
        magic.addItem(new Spells("Auto kill", "Yeet and Delete", magic,0,0,0,0,ID.Spell,this,0,false,false,0,0));

        //loadSave();
        knuckles1 = new Enemy(5760, 385, 100, 52, handler, this, ID.Knuckles, 2, tbHandler, "Ugandan Knuckles", player, ID.Knuckles,20,20,4,tex.Knuckles_BattleForm,Color.red,100);
        bigChungus = new Enemy(5760, 375, 433, 225, handler, this, ID.BigChungus, 7, tbHandler, "Big Chungus", player, ID.BigChungus,100,100,4,tex.BigChungusBF[0],Color.gray,1000);



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
                camera = new Camera(0,0,this);
                camera.updateRange("Room1_1");
                player = new Player(900, 100, 92, 25, handler, this, ID.Player, 2,inv, magic);
                // items and spells addition
                Room1_1_Apple = new Items("Apple","Good Snack boi",900,1700,32,32,ID.Item,this,0,0,false,false,0,0);
                Room2_2_Basic_Heal = new Spells("Basic Heal", "Heals 5 HP uses 5 Mana", player.getMagic(),800,150,32,32,ID.Spell,this,5,true,false,5,0);
                Room2_3_BoogieWoogie=new Spells("Boogie Woogie", "Increase attack by 5. Uses 10 mana", player.getMagic(),660,2780,32,32,ID.Spell,this,10,false,false,0,0);
                Room2_3_YeetusDeletus=new Spells("Yeetus Deletus", "Will do 15 damage. 15 Mana", player.getMagic(),5760,5860,32,32,ID.Spell,this,15,false,true,0,15);
                Room2_3_EnnieMeenieMinieMo=new Spells("Enie Meni Minie Mo", "May hurt you, may hurt the enemy. 20 Mana", player.getMagic(),5915,115,32,32,ID.Spell,this,20,false,false,0,0);
                Room2_3_EmptyWaterBottle=new Items("Empty Water Bottle","Yeet it for 5-10 damage",900,1700,32,32,ID.Item,this,0,0,false,false,0,0);
                Room2_3_LiptonIcedTea = new Items("Lipton Iced Tea","Heals 14",5900,2020,32,32,ID.Item,this,0,0,true,false,14,0);
                Room2_3_Tidepod=new Items("Tide Pod","Heals 5",80,5920,32,32,ID.Item,this,0,0,true,false,5,0);


                inv.addItem(Room2_3_LiptonIcedTea);
                inv.addItem(Room2_3_Tidepod);
                magic.addItem(Room2_2_Basic_Heal);
                magic.addItem(Room2_3_YeetusDeletus);

            }if(currentState==STATE.FirstArea && Switch == false){
               // handler.clear();





                // only for rooms that you can get into a battle in
                // NOT passive rooms
                if(currentRoom.equals("Room1_1")){
                    currentLevel = tex.SS_FirstArea.grabImage(1, 1, 64, 64);
                    //currentRoom = "Room1_1";

                    loadLevel(currentRoom);
                    //TPoser tposer = new TPoser(900,900, 184, 184, handler, this, ID.TPoser, 7, tbHandler, "DOY DOY DOY", player, true);
                    //handler.addObject(tposer);

                    //npc = new NPC(400, 200, 19, 74, handler, this, ID.NPC, 2, tbHandler, "asdfasdfasdfasdfja;sldkjfaslkdjfasldf;a", player, ID.TheLastEntity);
                    System.out.println("here");

                    //handler.addObject(knuckles1);
                    //Pikachu pikachu = new Pikachu(800, 500, 96, 48, handler, this, ID.Pikachu, 2, tbHandler, "Pikaaachuuu", player, true);
                    tex=getInstance();
                    bigChungus = new Enemy(5760, 375, 433, 225, handler, this, ID.BigChungus, 7, tbHandler, "Big Chungus", player, ID.BigChungus,100,100,4,tex.BigChungusBF[0],Color.gray,1000);
                    // handler.addObject(pikachu);
                    //handler.addObject(npc);
                    //handler.addObject(bigChungus);
                    handler.addObject(player);
                    //handler.addObject(new Transition(200, 1000, 64, 64, ID.Transition, "First"));
                    so = new SaveObject(725, 25, 64, 64);
                    handler.addObject(so);




                }else if(currentRoom.equals("Room2_1")){
                    handler.clear();
                    loadLevel(currentRoom);
                    handler.addObject(player);
                }else if(currentRoom.equals("Room2_3")){
                    handler.clear();
                    loadLevel(currentRoom);
                    handler.addObject(player);
                }else if(currentRoom.equals("Room2_4")){
                    System.out.println("here");
                    handler.clear();
                    loadLevel(currentRoom);
                    handler.addObject(player);
                }else if(currentRoom.equals("Room3_1")){
                    handler.clear();
                    loadLevel(currentRoom);
                    handler.addObject(player);
                }




                this.keyInput = new KeyInput(handler, player, tbHandler, player.getInv());

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

                if(player.getInv().getOpen()){
                    player.getInv().tick();

                }else if(player.getMagic().getOpen())
                    player.getMagic().tick();

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
                System.out.println(currentLevel);
                handler.clear();
                loadLevel(currentRoom);


                //setCurrentBattle(null);



                player = new Player(player.getX(), player.getY(), 92, 25, handler, this, ID.Player, 2,inv, magic);
                handler.addObject(player);
                camera = new Camera(0,0,this);
                camera.updateRange(currentRoom);
                this.addKeyListener(new KeyInput(handler, player, tbHandler, player.getInv()));
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
                player.setInv(new Inventory());

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

            System.out.println("rendering");


            handler.render(g);
            g2d.translate(camera.getX(), camera.getY());
            tbHandler.render(g);

            if(player.getInv().getOpen()){
                player.getInv().render(g);
            }else if(player.getMagic().getOpen()){
                player.getMagic().render(g);
            }







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
        System.out.println(rooms.get(room));
        System.out.println(tex.Room2_1);
        System.out.println(image);
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
                }else if(red==0&&green==0&&blue==0){

                }else if(red==255&&green==230&&blue==0){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Mushroom));

                }else if(red==255&&green==111&&blue==0){
                    //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.RedGround));

                }
            }
        }
        if(currentRoom.equals("Room1_1")){
            handler.addObject(new NPC(1050,500,64,64,handler,this,ID.NPC,3,tbHandler,"You... are the last of us. I am unable to continue to fight him. My last gift to you... the prophecy.",player,ID.TheLastEntity));
            handler.addObject(new NPC(750,500,48,32,handler,this,ID.NPC,3,tbHandler,"The Chosen One must travel through The Wae, The Labyrinth, sdrawkcaB ehT, and the Land of Dead Memes in order to..... (The rest is cut out) What will you choose?",player, ID.Book));
            // transition object to room 2_2
            handler.addObject(new Transition(835,1975,32,100,ID.Transition,"Room2_1",950,100,player,true));
            handler.addObject(Room1_1_Apple);
            camera.updateRange("Room1_1");


            handler.addObject(new Enemy(700, 500, 92, 25, handler, this, ID.Spaget, 2, tbHandler, "Spaget", player, ID.Spaget,20,20,2,tex.SpagetBF,Color.green,50));
            handler.addObject(new Enemy(800, 500, 96, 48, handler, this, ID.Arthur, 1, tbHandler, "Arthur", player, ID.Arthur,20,20,2,tex.ArthurBF,new Color(255,224,189),40));


        }else if(currentRoom.equals("Room2_1")){

            setBackground(Color.black);
            loadLevel(tex.Room2_1O);
            handler.addObject(new Transition(835,0,32,300,ID.Transition, "Room1_1",900,1800,player,false));
            handler.addObject(new Transition(0,130,70,32,ID.Transition, "Room2_2",1100,130,player,false));
            handler.addObject(new Transition(900,1925,32,200,ID.Transition,"Room2_3",3000,200,player,true));
            camera.updateRange("Room2_1");
        }else if(currentRoom.equals("Room2_2")){
            setBackground(Color.black);
            loadLevel(tex.Room2_2O);
            // add transistion back lol
            handler.addObject(new Transition(1175,105,70,32,ID.Transition, "Room2_1",60,155,player,true));

            camera.updateRange("Room2_2");
            handler.addObject(Room2_2_Basic_Heal);

        }else if(currentRoom.equals("Room2_3")){
            setBackground(Color.black);
            loadLevel(tex.Room2_3O);
            camera.updateRange("Room2_3");
            handler.addObject(new Transition(2600,100,32,700,ID.Transition, "Room2_1",975,1800,player,true));
            handler.addObject(new Transition(2050,6025,32,400,ID.Transition,"Room2_4",480,60,player,false));
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
            handler.addObject(new Transition(0,30,32,1000,ID.Transition,"Room2_3",2250,5900,player,true));

            if(!knuckles1.isDefeated()){
                handler.addObject(knuckles1);
                handler.addObject(new TextBox(null,"CLUCK CLUCK CLUCK (I know who you are.)",1120,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(null,"CLUCK CLUCK CLUCK (I know what you are going to try to do.)",1740,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(null,"CLUCK CLUCK CLUCK (I will give you a choice to turn back now before it is too late.)",2360,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(knuckles1,"CLUCK CLUCK CLUCK (I'm sorry, but I must protect him with my life)",5180,0,1000,16,ID.TextBox,tbHandler));
                handler.addObject(new TextBox(knuckles1,"CLUCK CLUCK (I, Ugandan Knuckles will stop you. Approach me and seek destruction.)",5560,0,1000,16,ID.TextBox,tbHandler));

                handler.addObject(new SaveObject(5280,200,64,64));
            }

            handler.addObject(new TextBox(player, "The Wae Forest has been cleared! Knuckles has been set free of the hex. Let's keep moving into The Labyrinth.",5850,0,1000,16,ID.TextBox,tbHandler));


            handler.addObject(new Transition(5900,0,1000,16,ID.Transition,"Room3_1",400,400,player,true));




            setBackground(Color.black);
            loadLevel(tex.Room2_4O);
            camera.updateRange("Room2_4");
            //handler.addObject(knuckles1);
        }else if(currentRoom.equals("Room3_1")){
            //endangered=true;
            setBackground(Color.black);
            loadLevel(tex.Room3_1O);
            camera.updateRange("Room3_1");
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
                }else if(red==0&&green==0&&blue==0){
                }else if(red==255&&green==230&&blue==0){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.Mushroom));

                }else if(red==255&&green==111&&blue==0){
                    handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.RedGround));

                }
            }
        }
        if(currentRoom.equals("Room1_1")){
            handler.addObject(new NPC(1050,500,64,64,handler,this,ID.NPC,3,tbHandler,"You... are the last of us. I am unable to continue to fight him. My last gift to you... the prophecy.",player,ID.TheLastEntity));
            handler.addObject(new NPC(750,500,48,32,handler,this,ID.NPC,3,tbHandler,"The Chosen One must travel through The Wae, The Labyrinth, Eht Sdrawkcab and the Land of Dead Memes and make the ultimate decision. The choice is up to fate. The decision that he will hate.",player, ID.Book));
            // transition object to room 2_2
            handler.addObject(new Transition(835,1975,32,100,ID.Transition,"Room2_1",850,100,player,true));
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
            this.keyInput = new KeyInput(handler, player, tbHandler, player.getInv());
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



}
