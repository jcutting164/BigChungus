import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;


public class KeyInput extends KeyAdapter{


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
                        {tempObject.setVelY(-5); keyDown[0]=true; }
                        player.setLastKeyHit(0);
                    }else if((key == KeyEvent.VK_DOWN)) {
                        {tempObject.setVelY(5); keyDown[1]=true; }
                        player.setLastKeyHit(1);
                    }else if(key == KeyEvent.VK_LEFT) {
                        {tempObject.setVelX(-5); keyDown[2]=true; }
                        player.setLastKeyHit(2);
                    }else if(key == KeyEvent.VK_RIGHT) {
                        {tempObject.setVelX(5); keyDown[3]=true; }
                        player.setLastKeyHit(3);
                    }else if(key == KeyEvent.VK_X) {
                        player.setLastKeyHit(4);
                    }else if(key == KeyEvent.VK_F) {
                        tbHandler.addObject(new TextBox(player, "THIS IS MY TEXT WOOOWOWOWOOOOOOO"));

                        //hud.setScore(hud.getScore() + 100);
                    }else if(key == KeyEvent.VK_G) {
                        //hud.setScore(hud.getScore() + 10000);
                        tbHandler.removeObject(0);
                    }else if(key==KeyEvent.VK_C){
                        player.setLimited(true);
                        inv.setOpen(true);
                        inv.setOptions(true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
                        inv.setPage(1);
                        inv.setCurrentOption(0);
                    }
                }

            }
        }else if(player.getLimited() == true){

            for(int i = 0; i< handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player) {
                    // key events for NPC.Player 1

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
                            inv.inv.get(inv.getCurrentOption()).use();
                        }
                    }else{
                        if(key == KeyEvent.VK_X && tbHandler.object.get(0).getDone()) {
                            player.setLimited(false);
                            tbHandler.removeObject(0);
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
