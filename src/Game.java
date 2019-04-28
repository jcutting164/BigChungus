import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


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
    transient BufferedImage currentLevel;
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
    private Knuckles knuckles1;
    private Pikachu pikachu;
    private BigChungus bigChungus;




    public enum STATE {
        Menu,
        FirstArea,
        Battle,
        GameOver;
    };


    private Window window;

    public Game() {

        this.ap = new AudioPlayer();
        this.ap.load();
        tex = new Textures();
        window = new Window(WIDTH, HEIGHT, "The Reign of Big Chungus", this);
        handler = new Handler();
        //loadSave();
        tbHandler = new TBHandler();
        currentState=null;


        inv = new Inventory();
        inv.addItem(new Items("Basic HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("Mediocre Potion", "A healing potion that will give you 40 HP", inv));
        inv.addItem(new Items("Max Potion", "A healing potion that will give you MAX HP", inv));

        inv.addItem(new Items("Sword", "A sharp sword", inv));
        inv.addItem(new Items("Magic Ball", "Zoinks idk what this does lol", inv));
        inv.addItem(new Items(" asdf HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("rete HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("asdf HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("fdsa HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("asdf HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("fda HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("fdfdfd HP", "A healing potion that will give you 20 HP", inv));

        magic = new Magic();
        magic.addItem(new Spells("Basic Heal Spell", "Heals 10 hp and uses 10 mana", magic));

        //loadSave();


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
            }if(currentState==STATE.FirstArea && Switch == false){
               // handler.clear();



                currentLevel = tex.SS_FirstArea.grabImage(1, 1, 64, 64);
                camera = new Camera(0,0);
                loadLevel(currentLevel);
                loadLevel(currentLevel);

                player = new Player(200, 200, 19, 74, handler, this, ID.Player, 2,inv, magic);
                npc = new NPC(400, 200, 19, 74, handler, this, ID.NPC, 2, tbHandler, "asdfasdfasdfasdfja;sldkjfaslkdjfasldf;a", player);
                Knuckles knuckles1 = new Knuckles(600, 200, 96, 48, handler, this, ID.Knuckles, 2, tbHandler, "CLICK CLICK CLICK", player, true);
                handler.addObject(knuckles1);
                Pikachu pikachu = new Pikachu(800, 500, 96, 48, handler, this, ID.Pikachu, 2, tbHandler, "Pikaaachuuu", player, true);
                BigChungus bigChungus = new BigChungus(500, 500, 433, 225, handler, this, ID.BigChungus, 7, tbHandler, "CHUNGA", player, true);
                handler.addObject(pikachu);
                handler.addObject(npc);
                handler.addObject(bigChungus);
                handler.addObject(player);
                so = new SaveObject(300, 300, 64, 64);
                handler.addObject(so);


                this.keyInput = new KeyInput(handler, player, tbHandler, player.getInv());

                this.addKeyListener(keyInput);

                Switch=true;
            }else if(currentState==STATE.FirstArea && Switch==true){
                for(int i = 0; i < handler.object.size(); i++){
                    if(handler.object.get(i).getId() == ID.Player){
                        camera.tick(handler.object.get(i));
                    }
                }
                handler.tick();
                tbHandler.tick();

                if(player.getInv().getOpen()){
                    player.getInv().tick();
                }
            }else if(currentState==STATE.Battle && Switch == false){
                handler.clear();
                removeKeyListener(keyInput);

                Switch = true;
                //create new battle object or get the battle object from player (defined by collision)

            }else if(currentState==STATE.Battle && Switch == true){
                handler.tick();
                currentBattle.tick();
            }else if(currentState==STATE.GameOver && Switch==false){

            }else if(currentState==STATE.GameOver && Switch==true){

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




            g.setColor(Color.gray);
            g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);


            Graphics2D g2d = (Graphics2D) g;

            g2d.translate(-camera.getX(), -camera.getY());




            handler.render(g);

            g2d.translate(camera.getX(), camera.getY());
            tbHandler.render(g);

            if(player.getInv().getOpen()){
                player.getInv().render(g);
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

            g.setColor(Color.black);
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

    private void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();


        for(int xx = 0; xx < w; xx++){
            for(int yy = 0; yy < h; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if(red == 255)
                    handler.addObject(new Block(xx*32, yy*32, 64, 64, handler, this, ID.GrayGround));
                else if(green == 255){
                    handler.addObject(new Block(xx*32, yy*32, 64, 64, handler, this, ID.BlackGround));
                }
            }


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
            System.out.println("temp+ "+temp);
            this.ap=temp.getAp();
            this.ap.load();
            tex = new Textures();
            //window = new Window(WIDTH, HEIGHT, "The Reign of Big Chungus", this);
            window=new Window(WIDTH, HEIGHT, "The Reign of Big Chungus", this);
            handler = temp.handler;
            currentLevel = tex.SS_FirstArea.grabImage(1, 1, 64, 64);
            camera = new Camera(0,0);
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
                    bigChungus=(BigChungus) handler.object.get(i);
                    handler.object.set(i, bigChungus);
                }else if(handler.object.get(i).getId()==ID.Knuckles){
                    knuckles1=(Knuckles) handler.object.get(i);
                    handler.object.set(i, knuckles1);
                }else if(handler.object.get(i).getId()==ID.Pikachu){
                    pikachu=(Pikachu) handler.object.get(i);
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
}
