import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;


public class KeyInput extends KeyAdapter implements Serializable {


    private Handler handler;
    private TBHandler tbHandler;

    private long timeNow, time, timeOfLastShot;



    private boolean[] keyDown = new boolean[6];
    private Player player;
    private Inventory inv;

    public KeyInput(Handler handler, Player player, TBHandler tbHandler, Inventory inv) {
        this.handler = handler;
        this.timeOfLastShot = 0;
        this.player = player;
        this.tbHandler = tbHandler;
        this.inv = inv;

        for(int i = 0; i<6; i++) {
            keyDown[i] = false;
        }
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(!player.getLimited()){
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
        }else if(player.getLimited()){

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
                    if(key==KeyEvent.VK_DOWN){

                        if(!inv.getOptions()[inv.inv.size()]){
                            for(int j = 0; j<inv.inv.size()-1; j++){
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
                            for(int j = 0; j<inv.inv.size(); j++){
                                if(inv.getOptions()[j]){
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
                    System.out.println("YEET");
                    tempObject.setVelX(0);
                }


            }


        }else{

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