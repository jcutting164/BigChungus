import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class BattleKeyInput extends KeyAdapter{


    private Handler handler;
    private TBHandler tbHandler;

    private long timeNow, time, timeOfLastShot;



    private boolean[] keyDown = new boolean[6];
    private Player player;
    private Battle battle;
    private BattlePlayer bPlayer;

    public BattleKeyInput(Handler handler, Player player, Battle battle, BattlePlayer bPlayer) {
        this.handler = handler;
        this.timeOfLastShot = 0;
        this.player = player;
        this.battle = battle;
        this.bPlayer=bPlayer;

        for(int i = 0; i<6; i++) {
            keyDown[i] = false;
        }
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(battle.getPlayerTurn()){
            if(!player.getLimited()){
                if(key == KeyEvent.VK_UP ) {
                    keyDown[0]=true;
                    player.setLastKeyHit(0);
                }else if((key == KeyEvent.VK_DOWN)) {
                    keyDown[1]=true;
                    player.setLastKeyHit(1);
                }else if(key == KeyEvent.VK_LEFT) {
                    keyDown[2]=true;
                    player.setLastKeyHit(2);
                    if(!battle.getSelection1()){
                        if(battle.getSelectedOption()[1]){
                            battle.setSelectedOption(true, false, false, false);
                        }else if(battle.getSelectedOption()[2]){
                            battle.setSelectedOption(false, true, false, false);
                        }else if(battle.getSelectedOption()[3]){
                            battle.setSelectedOption(false, false, true, false);
                        }
                    }




                }else if(key == KeyEvent.VK_RIGHT) {
                    keyDown[3]=true;
                    player.setLastKeyHit(3);
                    if(!battle.getSelection1()){
                        if(battle.getSelectedOption()[0]){
                            battle.setSelectedOption(false, true, false, false);
                        }else if(battle.getSelectedOption()[1]){
                            battle.setSelectedOption(false, false, true, false);
                        }else if(battle.getSelectedOption()[2]){
                            battle.setSelectedOption(false, false, false, true);
                        }
                    }

                }else if(key == KeyEvent.VK_X) {
                    player.setLastKeyHit(4);
                    if(!battle.getSelection1()){

                        battle.setSelection1(true);
                        if(battle.getSelectedOption()[0]){
                            battle.setSelectedOption2(true);

                        }else if(battle.getSelectedOption()[1]){
                            player.setLimited(true);

                            player.getMagic().setOpen(true);
                            player.getMagic().setOptions(true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
                            player.getMagic().setPage(1);
                            player.getMagic().setCurrentOption(0);

                        }else if(battle.getSelectedOption()[2]){
                            player.setLimited(true);

                            player.getInv().setOpen(true);
                            player.getInv().setOptions(true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
                            player.getInv().setPage(1);
                            player.getInv().setCurrentOption(0);

                        }else if(battle.getSelectedOption()[3]){

                        }
                    }else if(battle.getSelection1() && !battle.getSelection2()){
                        battle.setSelection2(true);
                        battle.setFirstTimeInTurn(true);
                    }


                }else if(key== KeyEvent.VK_F){
                    battle.setPlayerTurn(true);
                }else if(key==KeyEvent.VK_Z){
                    if(battle.getSelection1() && !battle.getSelection2()){
                        battle.setSelection1(false);
                    }
                }else if(key==KeyEvent.VK_ESCAPE){
                    System.exit(1);
                }
            }else if(player.getLimited()){
                if(key==KeyEvent.VK_DOWN){

                    if(battle.getSelectedOption()[2]){
                        if(!player.getInv().getOptions()[player.getInv().inv.size()]){
                            for(int j = 0; j<player.getInv().inv.size()-1; j++){
                                if(player.getInv().getOptions()[j]){
                                    player.getInv().setSpecOption(false, j);
                                    player.getInv().setSpecOption(true, player.getInv().getCurrentOption()+1);
                                    player.getInv().setCurrentOption(player.getInv().getCurrentOption()+1);
                                    break;
                                }
                            }
                        }
                    }else if(battle.getSelectedOption()[1]){
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
                    }



                }else if(key==KeyEvent.VK_UP){
                    if(battle.getSelectedOption()[2]){
                        if(!player.getInv().getOptions()[0]){
                            for(int j = 0; j<player.getInv().inv.size(); j++){
                                if(player.getInv().getOptions()[j]){
                                    player.getInv().setSpecOption(false, j);
                                    player.getInv().setSpecOption(true, player.getInv().getCurrentOption()-1);
                                    player.getInv().setCurrentOption(player.getInv().getCurrentOption()-1);
                                    break;
                                }
                            }
                        }
                    }else if(battle.getSelectedOption()[1]){
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
                    }


                }else if(key==KeyEvent.VK_Z&&battle.getSelectedOption()[2]){
                    player.setLimited(false);
                    player.getInv().setOpen(false);
                    battle.setSelection1(false);
                }else if(key==KeyEvent.VK_X&&battle.getSelectedOption()[2]){
                    player.getInv().inv.get(player.getInv().getCurrentOption()).use();
                    player.setLimited(false);
                    player.getInv().setOpen(false);
                    battle.setPlayerTurn(false);
                    battle.setEnemyTurnStart(true);
                    battle.setSelectedOption2(true);


                }else if(key==KeyEvent.VK_Z&&battle.getSelectedOption()[1]){
                    player.setLimited(false);
                    player.getMagic().setOpen(false);
                    battle.setSelection1(false);
                }else if(key==KeyEvent.VK_X&&battle.getSelectedOption()[1]){
                    player.getMagic().magic.get(player.getMagic().getCurrentOption()).use();
                    player.setLimited(false);
                    player.getMagic().setOpen(false);
                    battle.setPlayerTurn(false);
                    battle.setEnemyTurnStart(true);
                    battle.setSelectedOption2(true);
                }else if(key==KeyEvent.VK_ESCAPE){
                    System.exit(1);
                }
            }




            for(int i = 0; i< handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player) {
                    // key events for NPC.Player 1

                    if(key == KeyEvent.VK_X && tbHandler.object.get(0).getDone()) {
                        player.setLimited(false);
                        tbHandler.removeObject(0);
                    }
                }

            }




        }else if(!battle.getPlayerTurn()){
            if(key == KeyEvent.VK_UP ) {
                keyDown[0]=true;
                player.setLastKeyHit(0);
                bPlayer.setVelY(-4);
            }else if((key == KeyEvent.VK_DOWN)) {
                keyDown[1]=true;
                player.setLastKeyHit(1);
                bPlayer.setVelY(4);
            }else if(key == KeyEvent.VK_LEFT) {
                keyDown[2]=true;
                player.setLastKeyHit(2);
                bPlayer.setVelX(-4);





            }else if(key == KeyEvent.VK_RIGHT) {
                keyDown[3]=true;
                player.setLastKeyHit(3);
                bPlayer.setVelX(4);


            }else if(key == KeyEvent.VK_X) {
                player.setLastKeyHit(4);



            }else if(key== KeyEvent.VK_F){
            }else if(key==KeyEvent.VK_Z){

            }else if(key==KeyEvent.VK_ESCAPE){
                System.exit(1);
            }


            for(int i = 0; i< handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player) {
                    // key events for NPC.Player 1

                    if(key == KeyEvent.VK_X && tbHandler.object.get(0).getDone()) {
                        player.setLimited(false);
                        tbHandler.removeObject(0);
                    }
                }

            }
        }
    }

                // key events for NPC.Player 1


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(battle.getPlayerTurn()){
            for(int i = 0; i< handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.Player) {
                    // key events for NPC.Player 1

                    if (key == KeyEvent.VK_UP) {
                        keyDown[0] = false;
                        player.setLastKeyReleased(0);
                    } else if (key == KeyEvent.VK_DOWN) {
                        keyDown[1] = false;
                        player.setLastKeyReleased(1);
                    } else if (key == KeyEvent.VK_LEFT) {
                        keyDown[2] = false;
                        player.setLastKeyReleased(2);
                    } else if (key == KeyEvent.VK_RIGHT) {
                        keyDown[3] = false;
                        player.setLastKeyReleased(3);
                    } else if (key == KeyEvent.VK_SPACE) {
                        keyDown[4] = false;
                    } else if (key == KeyEvent.VK_X) {
                        keyDown[5] = false;
                        player.setLastKeyReleased(4);
                    }
                }

                // vertical movement

                if ((!keyDown[0] && !keyDown[1]) && tempObject.getId() == ID.Player) {
                    tempObject.setVelY(0);
                }

                // horizontal
                if ((!keyDown[2] && !keyDown[3]) && tempObject.getId() == ID.Player) {
                    tempObject.setVelX(0);
                }


                if (key == KeyEvent.VK_ESCAPE) {
                    System.exit(1);
                }


            }
        }else if(!battle.getPlayerTurn()){
            for(int i = 0; i< handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.BattlePlayer) {
                    // key events for NPC.Player 1

                    if (key == KeyEvent.VK_UP) {
                        keyDown[0] = false;
                        player.setLastKeyReleased(0);
                    } else if (key == KeyEvent.VK_DOWN) {
                        keyDown[1] = false;
                        player.setLastKeyReleased(1);
                    } else if (key == KeyEvent.VK_LEFT) {
                        keyDown[2] = false;
                        player.setLastKeyReleased(2);
                    } else if (key == KeyEvent.VK_RIGHT) {
                        keyDown[3] = false;
                        player.setLastKeyReleased(3);
                    } else if (key == KeyEvent.VK_SPACE) {
                        keyDown[4] = false;
                    } else if (key == KeyEvent.VK_X) {
                        keyDown[5] = false;
                        player.setLastKeyReleased(4);
                    }
                }

                // vertical movement

                if ((!keyDown[0] && !keyDown[1]) && tempObject.getId() == ID.BattlePlayer) {
                    tempObject.setVelY(0);
                }

                // horizontal
                if ((!keyDown[2] && !keyDown[3]) && tempObject.getId() == ID.BattlePlayer) {
                    tempObject.setVelX(0);
                }


                if (key == KeyEvent.VK_ESCAPE) {
                    System.exit(1);
                }


            }
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
