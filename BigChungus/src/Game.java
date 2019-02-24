import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.*;


public class Game extends Canvas implements Runnable{



    /**
     *
     */
    private static final long serialVersionUID = -1300776496297261616L;

    // height is 960
    public static final float WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;

    //temp
    private BufferedImage player2;

    private Thread thread;
    private boolean running = false;
    private BufferedImage spriteSheet = null;
    private Handler handler;
    private Player player;
    private NPC npc;
    static Textures tex;
    private Camera camera;
    BufferedImage currentLevel;
    TextBox tb;
    private TBHandler tbHandler;
    private KeyInput keyInput;
    private STATE currentState;
    private boolean Switch;
    private Battle currentBattle;
    private Font fnt;
    private Inventory inv;


    public enum STATE {
        Menu,
        FirstArea,
        Battle,
        GameOver;
    };


    private Window window;

    public Game() {
        tex = new Textures();
        window = new Window(WIDTH, HEIGHT, "Temp", this);
        handler = new Handler();
        tbHandler = new TBHandler();
        currentState=null;
        Switch=false;
        inv = new Inventory(this);
        inv.addItem(new Items("Basic HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("asdf HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("fda HP", "A healing potion that will give you 20 HP", inv));

        inv.addItem(new Items("asdf HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("hgfds HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items(" asdf HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("rete HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("asdf HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("fdsa HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("asdf HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("fda HP", "A healing potion that will give you 20 HP", inv));
        inv.addItem(new Items("fdfdfd HP", "A healing potion that will give you 20 HP", inv));
/*
        currentLevel = tex.SS_FirstArea.grabImage(1, 1, 64, 64);
        camera = new Camera(0,0);
        loadLevel(currentLevel);

        player = new NPC.Player(200, 200, 19, 74, handler, this, ID.NPC.Player, 2);
        npc = new NPC(400, 200, 19, 74, handler, this, ID.NPC, 2, tbHandler, "i am an NPCC", player);

        handler.addObject(npc);
        handler.addObject(player);
        this.keyInput = new KeyInput(handler, player, tbHandler);

        this.addKeyListener(keyInput);
*/



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

        if(currentState==null){
            currentState = STATE.FirstArea;
        }if(currentState==STATE.FirstArea && Switch == false){
            handler.clear();
            currentLevel = tex.SS_FirstArea.grabImage(1, 1, 64, 64);
            camera = new Camera(0,0);
            loadLevel(currentLevel);

            player = new Player(200, 200, 19, 74, handler, this, ID.Player, 2);
            npc = new NPC(400, 200, 19, 74, handler, this, ID.NPC, 2, tbHandler, "i am an NPCC", player);
            Knuckles knuckles1 = new Knuckles(600, 200, 96, 48, handler, this, ID.Knuckles, 2, tbHandler, "CLICK CLICK CLICK", player, true);
            handler.addObject(knuckles1);
            Pikachu pikachu = new Pikachu(800, 500, 96, 48, handler, this, ID.Pikachu, 2, tbHandler, "Pikaaachuuu", player, true);
            BigChungus bigChungus = new BigChungus(500, 500, 433, 225, handler, this, ID.BigChungus, 7, tbHandler, "CHUNGA", player, true);
            handler.addObject(pikachu);
            handler.addObject(npc);
            handler.addObject(bigChungus);
            handler.addObject(player);


            this.keyInput = new KeyInput(handler, player, tbHandler, inv);

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

            if(inv.getOpen()){
                inv.tick();
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

            if(inv.getOpen()){
                inv.render(g);
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
        System.out.println(w);
        System.out.println(h);

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
    
    

}
