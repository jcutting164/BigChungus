import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;


public class GameKeyInput extends KeyAdapter implements Serializable {


    private Handler handler;
    private TBHandler tbHandler;

    private long timeNow, time, timeOfLastShot;
    private GamePlayer gp;



    private boolean[] keyDown = new boolean[6];
    private Player player;
    private Inventory inv;

    public GameKeyInput(Handler handler, Player player, TBHandler tbHandler, Inventory inv, GamePlayer gp) {
        this.handler = handler;
        this.timeOfLastShot = 0;
        this.player = player;
        this.tbHandler = tbHandler;
        this.inv = inv;
        this.gp = gp;

        for(int i = 0; i<6; i++) {
            keyDown[i] = false;
        }
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_UP){

        }else if(key==KeyEvent.VK_DOWN){

        }else if(key==KeyEvent.VK_LEFT){

        }else if(key==KeyEvent.VK_RIGHT){

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
                }else if(tempObject.getId()==ID.GamePlayer){
                    if(key == KeyEvent.VK_UP) {
                        keyDown[0] = false;
                    }else if(key == KeyEvent.VK_DOWN) {
                        keyDown[1] = false;
                    }else if(key == KeyEvent.VK_LEFT) {
                        keyDown[2] = false;
                    }else if(key == KeyEvent.VK_RIGHT) {
                        keyDown[3] = false;
                    }else if(key == KeyEvent.VK_SPACE) {
                        keyDown[4] = false;
                    }
                }

                // vertical movement




                if((!keyDown[0] && !keyDown[1]) && tempObject.getId() == ID.GamePlayer){
                    tempObject.setVelY(0);
                }

                // horizontal
                if((!keyDown[2] && !keyDown[3]) && tempObject.getId() == ID.GamePlayer){
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