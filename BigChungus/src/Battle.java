import java.awt.*;
import java.util.ArrayList;


public class Battle {
    private Player player;
    private Enemy enemy;
    private Game game;
    private Handler handler;
    private String currentOption;
    private Enemy realEnemy;
    private boolean playerTurn;
    private BattleKeyInput battleKeyInput;
    Textures tex = Game.getInstance();
    private boolean[] selectedOption;
    private int text_x;
    private boolean selection1;
    private Font fnt;
    private boolean selection2;
    private boolean[] selectedOption2;
    private boolean enemyVisable;
    private boolean effectTime;
    private boolean firstTimeInTurn;
    private boolean pauseEnd;
    private S_BasicAttack attack;
    private boolean playerTurnStart;
    private boolean enemyTurnStart;
    private BattlePlayer bPlayer;
    private int track;
    private long timeOfLastShot, timeOfLastShot2, time, timeNow, time2, timeNow2;
    private boolean firstLoop;
    private int choice=0;





    public Battle(Player player, Enemy enemy, Handler handler, Game game){
        this.player = player;
        this.enemy = enemy;
        this.handler = handler;
        this.playerTurn=true;
        this.playerTurnStart=true;
        this.game = game;

        this.enemyVisable=true;

        this.selectedOption = new boolean[4];
        this.selectedOption[0] = true;
        this.selectedOption[1] = false;
        this.selectedOption[2] = false;
        this.selectedOption[3] = false;
        this.firstTimeInTurn=false;
        this.bPlayer = new BattlePlayer(640, 650, 16, 16, ID.BattlePlayer, player, game);
        this.battleKeyInput = new BattleKeyInput(handler, player, this, bPlayer);
        game.addKeyListener(this.battleKeyInput);

        AudioPlayer ap = new AudioPlayer();
        ap.load();
        if(enemy.getId()==ID.Knuckles){
            ap.getMusic("Knuckles").loop();
        }else if(enemy.getId()==ID.Pikachu){
            ap.getMusic("Pikachu").loop();
        }else if(enemy.getId()==ID.BigChungus){
            ap.getMusic("BigChungus").loop();
        }





    }


    public void tick(){
        if(playerTurn){

        }else if(!playerTurn){

        }

    }

    public void render(Graphics g){
        enemy.drawHealthBar( 25, g);

        fnt = new Font("Serif", 0, 29);
        g.setFont(fnt);
        g.setColor(enemy.nameColor);
        g.drawString(enemy.getName(), 25, 35);


        g.setColor(Color.white);
        g.drawRect(512, 528, 256, 256);
        g.drawRect(511, 527, 258, 258);
        player.drawHealthBar(800, g);
        fnt = new Font("Serif", 1, 16);
        g.setFont(fnt);
        g.drawString(player.getHealth() + " / " + player.getMaxHealth(), 750, 824);





        // if player turn, render certain things including handler, at end of turn, clear everything non-relavent
        if(playerTurn){
            if(playerTurnStart){
                startPlayerTurn();
            }else{
                g.setColor(Color.white);
                fnt = new Font("Serif", 1, 16);
                g.setFont(fnt);


                g.setColor(Color.white);

                g.drawRect(160, 100, 960, 320);
                if(enemyVisable){
                    if(enemy.getId()==ID.BigChungus){
                        g.drawImage(enemy.BattleForm, 445, 105,290, 305, null);
                    }else{
                        g.drawImage(enemy.BattleForm, 445, 105,390, 305, null);
                    }

                }else{
                    g.setColor(Color.black);
                    g.fillRect(445, 105, 390, 305);
                }


                g.drawImage(tex.Player_WalkUp[0], 600, 600, 38, 148, null);
                if(!selection1){
                    for(int i = 0; i<4; i++){
                        if(i==0){
                            currentOption = "FIGHT";
                            text_x = 195;
                        }

                        else if(i==1){
                            currentOption = "MAGIC";
                            text_x = 195;
                        }

                        else if(i==2){
                            currentOption = "ITEMS";
                            text_x = 195;
                        }

                        else if(i==3){
                            currentOption = "SPECIAL";
                            text_x = 184;
                        }


                        if(selectedOption[i]==true){
                            //update and do math for this
                            g.setColor(Color.orange.darker());
                            g.fillRect((i*267)+144, 860, 192, 64);
                            g.setColor(Color.white);
                            g.drawRect((i*267)+144, 860, 192, 64);
                            g.drawRect((i*267)+143, 859, 194, 66);
                            fnt = new Font("Serif", 0, 29);
                            g.setFont(fnt);
                            g.setColor(Color.black);
                            g.drawString(currentOption, (i*267)+text_x, 900);
                        }else{
                            g.setColor(Color.black);
                            g.fillRect((i*267)+144, 860, 192, 64);
                            g.setColor(Color.orange);
                            g.drawRect((i*267)+144, 860, 192, 64);
                            g.drawRect((i*267)+143, 859, 194, 66);
                            fnt = new Font("Serif", 0, 29);
                            g.setFont(fnt);
                            g.setColor(Color.orange);
                            g.drawString(currentOption, (i*267)+text_x, 900);
                        }
                    }
                }else if(selection1){

                    // go through each option and provide options for each
                    if(selectedOption[0]){
                        if(selection2){

                            // ATTACK / item function to be created soon with init and deletion system for turn basing
                            if(firstTimeInTurn){
                                attack = new S_BasicAttack(500, 960, 19, 72, ID.Spell, handler, this, enemy);
                                attack.use();
                            }else{
                                if(effectTime){

                                    attack.effect();
                                }else{
                                    attack.end();

                                }
                            }



                        }else{
                            g.setColor(Color.white);
                            g.drawRect(154, 800, 313, 96);
                            g.drawRect(153, 799, 315, 98);
                            g.setColor(enemy.getNameColor());
                            fnt = new Font("Serif", 0, 29);
                            g.setFont(fnt);
                            g.drawString(enemy.getName(), 179, 830);
                            g.setColor(Color.white);
                            g.fillRect(163, 817, 8, 8);
                        }


                    }else if(selectedOption[1]){
                        if(selection2){
                            // magic / item function to be created soon with init and deletion system for turn basing
                        }else{
                            g.setColor(Color.red);
                            g.drawRect(154, 800, 512, 96);
                            g.drawRect(153, 799, 514, 98);
                        }


                    }else if(selectedOption[2]){
                        if(selection2){
                            // items / item function to be created soon with init and deletion system for turn basing
                        }else{
                            g.setColor(Color.green);
                            g.drawRect(154, 800, 512, 96);
                            g.drawRect(153, 799, 514, 98);
                        }


                    }else if(selectedOption[3]){
                        if(selection2){
                            // special / item function to be created soon with init and deletion system for turn basing
                        }else{
                            g.setColor(Color.blue);
                            g.drawRect(154, 800, 512, 96);
                            g.drawRect(153, 799, 514, 98);
                        }


                    }
                }else if(selection2){
                    if(selectedOption[0]){
                        g.setColor(Color.white);
                        g.drawRect(154, 800, 512, 96);
                        g.drawRect(153, 799, 514, 98);
                        g.setColor(enemy.getNameColor());
                        fnt = new Font("Serif", 0, 29);
                        g.setFont(fnt);
                        g.drawString(enemy.getName(), 179, 830);
                        g.setColor(Color.white);
                        g.fillRect(163, 817, 8, 8);

                    }else if(selectedOption[1]){
                        g.setColor(Color.red);
                        g.drawRect(154, 800, 512, 96);
                        g.drawRect(153, 799, 514, 98);

                    }else if(selectedOption[2]){
                        g.setColor(Color.green);
                        g.drawRect(154, 800, 512, 96);
                        g.drawRect(153, 799, 514, 98);

                    }else if(selectedOption[3]){
                        g.setColor(Color.blue);
                        g.drawRect(154, 800, 512, 96);
                        g.drawRect(153, 799, 514, 98);

                    }
                }



            }
        }else if(!playerTurn){
            if(enemyTurnStart){
                startEnemyTurn();
            }else{
                g.setColor(Color.white);

                g.drawRect(160, 100, 960, 320);
                if(enemy.getId()==ID.BigChungus){
                    g.drawImage(enemy.BattleForm, 445, 105,290, 305, null);
                }else{
                    g.drawImage(enemy.BattleForm, 445, 105,390, 305, null);
                }
                // effect part of template
                if(enemy.getId()==ID.Knuckles){
                    if(enemy.currentMove==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>2000 && track < 4){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Knuckles_A1, 3, 18, 18, bPlayer, player, handler);
                                temp.boxBounce();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.currentMove==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 14000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>500 && track < 20){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 31, 30, ID.EnemyAttackItem, tex.Knuckles_A1, 3, 18, 18, bPlayer, player, handler);
                                temp.randomProtShot();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.currentMove==2){
                        // effect code;
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;
                        if(time > 10000 || (!handler.isIn()&&track>0)) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{

                            if(track < 1){
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 34, 105, ID.EnemyAttackItem, tex.Knuckles_A2, 3, 105, 34, bPlayer, player, handler);
                                temp.DYKDW();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.currentMove==3){
                        // effect code;
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;
                        if(time > 15000 || (!handler.isIn()&&track>4)) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(track < 5 && time2>3000){
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 290, 270, ID.EnemyAttackItem, tex.Knuckles_A1, 6, 300, 310, bPlayer, player, handler);
                                temp.LS();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Pikachu) {
                    if (enemy.currentMove == 0) {

                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if (time > 10000) {
                            timeOfLastShot = 0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        } else {
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if (time2 > 500 && track < 15) {


                                if (firstLoop) {
                                    EnemyAttackItem temp1 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler);
                                    EnemyAttackItem temp2 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler);
                                    EnemyAttackItem temp3 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler);
                                    EnemyAttackItem temp4 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler);
                                    temp1.LightningShot(0);
                                    temp2.LightningShot(1);
                                    temp3.LightningShot(2);
                                    temp4.LightningShot(3);

                                    handler.addObject(temp1);
                                    handler.addObject(temp2);
                                    handler.addObject(temp3);
                                    handler.addObject(temp4);

                                    timeOfLastShot2 = timeNow2;
                                    track++;
                                    firstLoop = false;
                                } else {

                                    ArrayList<EnemyAttackItem> templist = handler.getELIST();

                                    templist.get((track - 1) * 3).setVelX(6);
                                    templist.get(((track - 1) * 3)).setVelY(6);
                                    templist.get(1 + ((track - 1)) * 3).setVelX(4);
                                    templist.get(1 + ((track - 1)) * 3).setVelY(6);
                                    templist.get(2 + ((track - 1)) * 3).setVelX(-4);
                                    templist.get(2 + ((track - 1)) * 3).setVelY(6);
                                    templist.get(3 + ((track - 1)) * 3).setVelX(-6);
                                    templist.get(3 + ((track - 1)) * 3).setVelY(6);
                                    templist.get(0 + ((track - 1)) * 3).setImages(tex.Pikachu_A1S);
                                    templist.get(1 + ((track - 1)) * 3).setImages(tex.Pikachu_A1S);
                                    templist.get(2 + ((track - 1)) * 3).setImages(tex.Pikachu_A1S);
                                    templist.get(3 + ((track - 1)) * 3).setImages(tex.Pikachu_A1S);
                                    EnemyAttackItem temp1 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler);
                                    EnemyAttackItem temp2 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler);
                                    EnemyAttackItem temp3 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler);
                                    EnemyAttackItem temp4 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler);
                                    temp1.LightningShot(0);
                                    temp2.LightningShot(1);
                                    temp3.LightningShot(2);
                                    temp4.LightningShot(3);

                                    handler.addObject(temp1);
                                    handler.addObject(temp2);
                                    handler.addObject(temp3);
                                    handler.addObject(temp4);

                                    timeOfLastShot2 = timeNow2;
                                    track++;
                                }

                            }
                        }
                    } else if (enemy.currentMove == 1) {
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if (time > 10000) {
                            timeOfLastShot = 0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if (time2 > 500 && track < 20) {
                                if(firstLoop){

                                    EnemyAttackItem temp1 = new EnemyAttackItem(600, 600, 90, 55, ID.EnemyAttackItem, tex.Pikachu_A1, 2, 55, 90, bPlayer, player, handler);
                                    temp1.LargeLightningShot();
                                    handler.addObject(temp1);
                                    track++;
                                    firstLoop=false;
                                    timeOfLastShot2 = timeNow2;
                                }else{
                                    ArrayList<EnemyAttackItem> templist = handler.getELIST();
                                    templist.get(templist.size()-1).setVelY(20);


                                    EnemyAttackItem temp1 = new EnemyAttackItem(600, 600, 90, 55, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 55, 90, bPlayer, player, handler);
                                    temp1.LargeLightningShot();
                                    handler.addObject(temp1);
                                    timeOfLastShot2 = timeNow2;
                                    track++;

                                }

                            }
                        }



                    }else if(enemy.currentMove==2){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if (time > 10000) {
                            timeOfLastShot = 0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(track < 10 && time2>1000){
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 97, 96, ID.EnemyAttackItem, tex.Pikachu_A2, 6, 96, 97, bPlayer, player, handler);
                                temp.Pokeball();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }


                        }

                    }else if(enemy.currentMove==3){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if (time > 10000) {
                            timeOfLastShot = 0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(track < 1 && time2>1000){
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 97, 96, ID.EnemyAttackItem, tex.Pikachu_A3, 6, 96, 97, bPlayer, player, handler);
                                temp.PikachuShot();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }


                        }

                    }
                }else if(enemy.getId()==ID.BigChungus){
                    if (enemy.currentMove == 0) {

                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if (time > 9000) {
                            timeOfLastShot = 0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        } else {
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if (time2 > 100 && track < 45) {
                                EnemyAttackItem temp = new EnemyAttackItem(0,0,36,117,ID.EnemyAttackItem,tex.BigChungus_A1,1,117,36,bPlayer,player,handler);
                                temp.CarrotShot();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;

                            }
                        }
                    } else if (enemy.currentMove == 1) {
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if (time > 3000) {
                            timeOfLastShot = 0;
                            if(choice==0)
                                player.setHealth(player.getHealth()/2);
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else if(time>2000){
                            if(choice==0)
                                g.drawImage(tex.BigChungus_A3[0],795,595,134,134,null);
                            else
                                g.drawImage(tex.BigChungus_A3[1],795,595,140,140,null);
                        }else {
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if (time2 > 250 && track < 1) {
                                EnemyAttackItem temp = new EnemyAttackItem(0,0,128,128,ID.EnemyAttackItem,tex.BigChungus_A3,4,128,128,bPlayer,player,handler);
                                choice = temp.CoinFlip();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;

                            }
                        }



                    }else if(enemy.currentMove==2){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if (time > 10000) {
                            timeOfLastShot = 0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(track < 10 && time2>1000){
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 97, 96, ID.EnemyAttackItem, tex.BigChungus_A2, 4, 96, 97, bPlayer, player, handler);
                                temp.CardAttack();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }


                        }

                    }else if(enemy.currentMove==3){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if (time > 7000) {
                            timeOfLastShot = 0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(track < 5 && time2>1000){
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 488, 272, ID.EnemyAttackItem, tex.BigChungus_A4, 6, 272, 488, bPlayer, player, handler);
                                temp.Gottem();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }


                        }

                    }
                }



            }











            // choice of move by enemy
            // execution of move based on handler removal?
            // check if move has been selected, if not, make move then execute move
            // also needs to render and allow access to dodge moves



        }

    }

    public void startPlayerTurn(){
        handler.clear();
        this.selectedOption = new boolean[4];
        this.selectedOption[0] = true;
        this.selectedOption[1] = false;
        this.selectedOption[2] = false;
        this.selectedOption[3] = false;
        this.firstTimeInTurn=false;
        this.enemyVisable=true;
        this.selection1=false;
        this.selection2=false;
        this.effectTime=false;
        this.firstTimeInTurn=false;
        this.playerTurnStart=false;




        // possible sound here for effect



    }

    public void startEnemyTurn(){

        // redefine all enemy attack objects previously made in init



        handler.clear();
        bPlayer.setX(630);
        bPlayer.setY(650);
        bPlayer.setVelX(0);
        bPlayer.setVelY(0);
        handler.addObject(bPlayer);
        enemyTurnStart=false;
        enemy.chooseMove();
        // use setup
        if(enemy.getId()==ID.Knuckles){
            if(enemy.currentMove==0){
                track = 0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==1){
                track = 0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==2){
                track=0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==3){
                track=0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }
        }else if(enemy.getId()==ID.Pikachu){
            if(enemy.currentMove==0){
                track = 0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==1){
                track = 0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==2){
                track=0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==3){
                track=0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }
            firstLoop=true;
        }else if(enemy.getId()==ID.BigChungus){
            if(enemy.currentMove==0){
                track = 0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==1){
                track = 0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==2){
                track=0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }else if(enemy.currentMove==3){
                track=0;
                timeOfLastShot=System.currentTimeMillis();
                timeOfLastShot2=System.currentTimeMillis();
            }
            firstLoop=true;
        }


    }

    public boolean getPlayerTurn(){
        return playerTurn;
    }
    public void setPlayerTurn(boolean playerTurn){
        this.playerTurn = playerTurn;
    }
    public boolean[] getSelectedOption(){
        return this.selectedOption;
    }
    public void setSelectedOption(boolean one, boolean two, boolean three, boolean four){
        boolean[] temp = new boolean[4];
        temp[0]=one;
        temp[1]=two;
        temp[2]=three;
        temp[3]=four;
        this.selectedOption = temp;
    }
    public boolean getSelection1(){
        return selection1;
    }
    public void setSelection1(boolean selection1){
        this.selection1 = selection1;
    }
    public boolean getSelection2(){
        return selection2;
    }
    public void setSelection2(boolean selection2){
        this.selection2 = selection2;
    }
    public boolean[] getSelectedOption2(){
        return this.selectedOption2;
    }
    public void setSelectedOption2(boolean... args){
        this.selectedOption2 = args;
    }
    public boolean getEnemyVisable(){
        return this.enemyVisable;
    }
    public void setEnemyVisable(boolean enemyVisable){
        this.enemyVisable = enemyVisable;
    }
    public boolean getEffectTime(){
        return this.effectTime;

    }
    public void setEffectTime(boolean effectTime){
        this.effectTime=effectTime;
    }
    public boolean getFirstTimeInTurn(){
        return this.firstTimeInTurn;
    }
    public void setFirstTimeInTurn(boolean firstTimeInTurn){
        this.firstTimeInTurn=firstTimeInTurn;
    }
    public boolean getPauseEnd(){
        return this.pauseEnd;
    }
    public void setPauseEnd(boolean pauseEnd){
        this.pauseEnd = pauseEnd;
    }
    public boolean getPlayerTurnStart(){
        return this.playerTurnStart;
    }
    public void setPlayerTurnStart(boolean playerTurnStart){
        this.playerTurnStart = playerTurnStart;
    }
    public boolean getEnemyTurnStart(){
        return this.enemyTurnStart;
    }
    public void setEnemyTurnStart(boolean enemyTurnStart){
        this.enemyTurnStart = enemyTurnStart;
    }



}
