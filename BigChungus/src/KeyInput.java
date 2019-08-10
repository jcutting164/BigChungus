import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;


public class KeyInput extends KeyAdapter implements Serializable {


    private Handler handler;
    private TBHandler tbHandler;

    private long timeNow, time, timeOfLastShot;
    private GamePlayer gp;



    private boolean[] keyDown = new boolean[6];
    private boolean[] keyDownS=new boolean[5];
    private Player player;
    private Inventory inv;
    private Game game;

    public KeyInput(Handler handler, Player player, TBHandler tbHandler, Inventory inv, GamePlayer gp,Game game) {
        this.handler = handler;
        this.timeOfLastShot = 0;
        this.player = player;
        this.tbHandler = tbHandler;
        this.inv = inv;
        this.gp = gp;
        this.game=game;

        for(int i = 0; i<6; i++) {
            keyDown[i] = false;
        }
        for(int i = 0; i<5; i++){
            keyDown[i]=false;
        }
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println(key);

        if(!player.getLimited() && handler.object.contains(player)){
            System.out.println(1);
            for(int i = 0; i< handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player) {
                    // key events for NPC.Player 1

                    if(key == KeyEvent.VK_UP ) {
                        {tempObject.setVelY(-20); keyDown[0]=true; }
                        player.setLastKeyHit(0);
                    }else if((key == KeyEvent.VK_DOWN)) {
                        {tempObject.setVelY(20); keyDown[1]=true; }
                        player.setLastKeyHit(1);
                    }else if(key == KeyEvent.VK_LEFT) {
                        {tempObject.setVelX(-20); keyDown[2]=true; }
                        player.setLastKeyHit(2);
                    }else if(key == KeyEvent.VK_RIGHT) {
                        {tempObject.setVelX(20); keyDown[3]=true; }
                        player.setLastKeyHit(3);
                    }else if(key == KeyEvent.VK_X) {
                        player.setLastKeyHit(4);
                    }else if(key == KeyEvent.VK_F) {
                        tbHandler.addObject(new TextBox(player, "THIS IS MY TEXT WOOOWOWOWOOOOOOO",0,0,0,0,ID.TextBox,tbHandler));

                        //hud.setScore(hud.getScore() + 100);
                    }else if(key == KeyEvent.VK_G) {
                        //hud.setScore(hud.getScore() + 10000);
                        tbHandler.removeObject(0);
                    }else if(key==KeyEvent.VK_C){
                        player.setVelX(0);
                        player.setVelY(0);
                        player.setLimited(true);
                        inv.setOpen(true);
                        inv.setOptions(true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
                        inv.setPage(1);
                        inv.setCurrentOption(0);
                    }else if(key==KeyEvent.VK_V){
                        player.setVelX(0);
                        player.setVelY(0);
                        player.setLimited(true);
                        player.getMagic().setOpen(true);
                        player.getMagic().setOptions(true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
                        player.getMagic().setPage(1);
                        player.getMagic().setCurrentOption(0);

                    }else if(key==KeyEvent.VK_SHIFT){
                            player.allowed=!player.allowed;

                    }
                }



            }
        }else if(player.getLimited() && handler.object.contains(player)){
            System.out.println(2);

            for(int i = 0; i< handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player) {
                    // key events for NPC.Player 1
                    if(!tbHandler.object.isEmpty()){
                        if(key == KeyEvent.VK_Z && tbHandler.object.get(0).getDone()) {
                            player.setLimited(false);
                            tbHandler.removeObject(0);
                        }
                    }


                if(inv.getOpen()){
                    System.out.println("Options: "+inv.getCurrentOption());
                    System.out.println("size"+inv.inv.size());

                    if(key==KeyEvent.VK_DOWN){

                        if(!inv.getOptions()[inv.inv.size()-1]){
                            for(int j = 0; j<inv.inv.size()-1; j++){
                                System.out.println("size"+inv.inv.size());
                                System.out.println(j+": "+inv.getOptions()[j]);
                                if(inv.getOptions()[j]){
                                    inv.setSpecOption(false, j);
                                    inv.setSpecOption(true, inv.getCurrentOption()+1);
                                    inv.setCurrentOption(inv.getCurrentOption()+1);
                                    break;
                                }
                            }
                        }

                    }else if(key==KeyEvent.VK_UP){
                        if(!inv.getOptions()[0]){
                            System.out.println("size2"+inv.inv.size());

                            for(int j = 0; j<inv.inv.size(); j++){
                                System.out.println("size"+inv.inv.size());
                                System.out.println(j+": "+inv.getOptions()[j]);
                                if(inv.getOptions()[j]){
                                    System.out.println("jaja");
                                    inv.setSpecOption(false, j);
                                    inv.setSpecOption(true, inv.getCurrentOption()-1);
                                    inv.setCurrentOption(inv.getCurrentOption()-1);
                                    break;
                                }
                            }
                        }

                    }else if(key==KeyEvent.VK_Z){
                        player.setLimited(false);
                        inv.setOpen(false);
                    }else if(key==KeyEvent.VK_X){

                        inv.inv.get(inv.getCurrentOption()).use(player,null);



                    }else if(key==KeyEvent.VK_V){
                        player.getMagic().setOpen(true);
                        player.getInv().setOpen(false);
                    }
                }else if(player.getMagic().getOpen()){
                    if(key==KeyEvent.VK_DOWN){

                        if(!player.getMagic().getOptions()[player.getMagic().magic.size()]){
                            for(int j = 0; j<player.getMagic().magic.size()-1; j++){
                                if(player.getMagic().getOptions()[j]){

                                    player.getMagic().setSpecOption(false, j);
                                    player.getMagic().setSpecOption(true, player.getMagic().getCurrentOption()+1);
                                    player.getMagic().setCurrentOption(player.getMagic().getCurrentOption()+1);
                                    break;


                                }
                            }
                        }

                    }else if(key==KeyEvent.VK_UP){
                        if(!player.getMagic().getOptions()[0]){
                            for(int j = 0; j<player.getMagic().magic.size(); j++){
                                if(player.getMagic().getOptions()[j]){
                                    player.getMagic().setSpecOption(false, j);
                                    player.getMagic().setSpecOption(true, player.getMagic().getCurrentOption()-1);
                                    player.getMagic().setCurrentOption(player.getMagic().getCurrentOption()-1);
                                    break;
                                }
                            }
                        }

                    }else if(key==KeyEvent.VK_Z){
                        player.setLimited(false);
                        player.getMagic().setOpen(false);
                    }else if(key==KeyEvent.VK_X){
                        if(player.getMagic().magic.get(player.getMagic().getCurrentOption()).getManaREQ()<=player.getMana()){
                            player.getMagic().magic.get(player.getMagic().getCurrentOption()).use(player, null);
                        }

                    }else if(key==KeyEvent.VK_C){
                        player.getMagic().setOpen(false);
                        player.getInv().setOpen(true);
                    }

                }


                }


            }
        }else{
            for(int i =0; i<handler.object.size(); i++){
                GameObject tempObject=handler.object.get(i);
                if(tempObject.getId()==ID.GamePlayer){
                    if(key==37){
                        keyDownS[2]=true;
                        tempObject.setVelX(-5);
                    }else if(key==39){
                        tempObject.setVelX(5);
                        keyDownS[3]=true;
                    }else if(key==38){
                        keyDownS[0]=true;
                        tempObject.setVelY(-5);
                    }else if(key==40){
                        keyDownS[1]=true;
                        tempObject.setVelY(5);
                    }else if(key==KeyEvent.VK_SPACE){
                        keyDownS[4] = true;
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;
                        if(time > 200) {
                            handler.addObject(new Laser(tempObject.getX() + (tempObject.getWidth() / 2), tempObject.getY() - (tempObject.getHeight() / 2), 16, 4, ID.Laser, handler.object.get(i), handler, Color.red,game));
                            AudioPlayer.getSound("LASER_SOUND").play();
                            timeOfLastShot = timeNow;
                        }else {

                        }

                    }else if(key==KeyEvent.VK_F){
                        game.setScore(game.getScore()+100);
                    }
                }
            }



        }



    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(player.getLimited() == false){

            for(int i = 0; i< handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player) {
                    // key events for NPC.Player 1

                    if(key == KeyEvent.VK_UP) {
                        keyDown[0] = false;
                        player.setLastKeyReleased(0);
                    }else if(key == KeyEvent.VK_DOWN) {
                        keyDown[1] = false;
                        player.setLastKeyReleased(1);
                    }else if(key == KeyEvent.VK_LEFT) {
                        keyDown[2] = false;
                        player.setLastKeyReleased(2);
                    }else if(key == KeyEvent.VK_RIGHT) {
                        keyDown[3] = false;
                        player.setLastKeyReleased(3);
                    }else if(key == KeyEvent.VK_SPACE) {
                        keyDown[4] = false;
                    }else if(key == KeyEvent.VK_X){
                        keyDown[5] = false;
                        player.setLastKeyReleased(4);
                    }
                }

                // vertical movement

                if((!keyDown[0] && !keyDown[1]) && tempObject.getId() == ID.Player){
                    tempObject.setVelY(0);
                }

                // horizontal
                if((!keyDown[2] && !keyDown[3]) && tempObject.getId() == ID.Player){
                    tempObject.setVelX(0);
                }

                for(int g = 0; g<4; g++){
                    System.out.println(keyDownS[g]);
                }




            }


        }else{
            for(int i = 0; i<handler.object.size(); i++){
                GameObject tempObject = handler.object.get(i);
                if(tempObject.getId()==ID.GamePlayer){
                    if(key == 38) {
                        keyDownS[0] = false;
                    }else if(key == 40) {
                        keyDownS[1] = false;
                    }else if(key == 37) {
                        keyDownS[2] = false;
                    }else if(key == 39) {
                        keyDownS[3] = false;
                    }else if(key == KeyEvent.VK_SPACE) {
                        keyDownS[4] = false;
                    }
                }
                if((!keyDownS[0] && !keyDownS[1]) && tempObject.getId() == ID.GamePlayer){
                    tempObject.setVelY(0);
                }

                // horizontal
                if((!keyDownS[2] && !keyDownS[3]) && tempObject.getId() == ID.GamePlayer){
                    tempObject.setVelX(0);
                }
            }


        }
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }





    }

    public int getStoppedSprite(KeyEvent e){
        int key = e.getKeyCode();
        int temp = 0;
        for(int i = 0; i< handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            // horizontal 2=L 3=R

            if((!keyDown[2]) && tempObject.getId() == ID.Player){
                temp = 2;
            }else if(!keyDown[3] && tempObject.getId() == ID.Player){
                temp = 3;
            }else if(!keyDown[0] && tempObject.getId() == ID.Player){
                temp=0;
            }else if(!keyDown[1] && tempObject.getId() == ID.Player){
                temp=1;
            }

        }
        return temp;

    }

    public boolean[] getKeyDown(){
        return keyDown;
    }


}