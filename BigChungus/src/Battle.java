
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Battle implements Serializable {
    private Player player;
    private Enemy enemy;
    private Game game;
    private Handler handler;
    private String currentOption;
    private Enemy realEnemy;
    private boolean playerTurn;
    private BattleKeyInput battleKeyInput;
    private transient Textures tex = Game.getInstance();
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
    private AudioPlayer ap;
    private int baseAttack, baseDefense;
    private int baseLevel;
    private boolean firstTurn=true;
    private boolean lowHealth=false;
    private boolean enemyItemUse=false;
    private String currentEnemyItem="";
    private String lastSpell="";


    public Battle(Player player, Enemy enemy, Handler handler, Game game, AudioPlayer ap){


        this.player = player;
        this.enemy = enemy;
        this.handler = handler;
        this.playerTurn=true;
        this.playerTurnStart=true;
        this.game = game;
        this.game.setLastX((int)(this.player.getX()));
        this.game.setLastY((int)(this.player.getY()));
        this.enemyVisable=true;

        this.selectedOption = new boolean[4];
        this.selectedOption[0] = true;
        this.selectedOption[1] = false;
        this.selectedOption[2] = false;
        this.selectedOption[3] = false;
        this.firstTimeInTurn=false;
        this.ap = ap;
        this.bPlayer = new BattlePlayer(640, 650, 16, 16, ID.BattlePlayer, player, game, ap);
        this.battleKeyInput = new BattleKeyInput(handler, game.getPlayer(), this, bPlayer);
        game.addKeyListener(this.battleKeyInput);
        this.baseAttack=game.getPlayer().getAttack();
        this.baseDefense=game.getPlayer().getDefense();
        this.baseLevel=game.getPlayer().getLevel();

        ap.load();
        if(enemy.getId()==ID.Knuckles){
            ap.getMusic("Knuckles").loop();
        }else if(enemy.getId()==ID.Pikachu){
            ap.getMusic("Pikachu").loop();
        }else if(enemy.getId()==ID.BigChungus){
            ap.getMusic("BigChungus").loop();
        }else if(enemy.getId()==ID.Malario){
            ap.getMusic("Malario").loop();
        }else if(enemy.getId()==ID.TPoser){
            ap.getMusic("TPoser").loop();
        }else if(enemy.getId()==ID.FatYoshi){
            ap.getMusic("FatYoshi").loop();
        }else if(enemy.getId()==ID.JoshuaGiraffe){
            ap.getMusic("JoshuaGiraffe").loop();
        }else if(enemy.getId()==ID.Kermit){
            ap.getMusic("Kermit").loop();
        }else if(enemy.getId()==ID.DatBoi){
            ap.getMusic("DatBoi").loop();
        }else if(enemy.getId()==ID.Crab)
            ap.getMusic("Crab").loop();
        else if(enemy.getId()==ID.BongoCat)
            ap.getMusic("BongoCat").loop();
        else if(enemy.getId()==ID.AntiHero)
            ap.getMusic("AntiHero").loop();
        else if(enemy.getId()==ID.Zuck)
            ap.getMusic("Zuck").loop();
        else if(enemy.getId()==ID.Harambe)
            ap.getMusic("Harambe").loop();
        else if(enemy.getId()==ID.KazooKid)
            ap.getMusic("KazooKid").loop();
        else if(enemy.getId()==ID.YodelBoy)
            ap.getMusic("YodelBoy").loop();
        else if(enemy.getId()==ID.Waluigi)
            ap.getMusic("Waluigi").loop();
        else if(enemy.getId()==ID.Spongebob)
            ap.getMusic("Spongebob").loop();
        else if(enemy.getId()==ID.Arthur)
            ap.getMusic("Arthur").loop();
        else if(enemy.getId()==ID.Spaget)
            ap.getMusic("Spaget").loop();
        else if(enemy.getId()==ID.Shaggy)
            ap.getMusic("Shaggy").loop();

        try{
            Thread.sleep(750);
        }catch(Exception e){

        }






    }

                                                                  
    public void tick(){

        if(player.getBackwards()&&enemy.getId()==ID.AntiHero&&enemy.getNameColor()==Color.white){
            enemy.setNameColor(Color.black);
            //ap.getMusic("AntiHero").stop();
            ap.getMusic("AntiHeroB").loop();
        }else if(!player.getBackwards()&&enemy.getId()==ID.AntiHero&&enemy.getNameColor()==Color.black){
            enemy.setNameColor(Color.white);

            //ap.getMusic("AntiHeroB").stop();
            ap.getMusic("AntiHero").loop();
        }


        player.setHealth((int)(Game.clamp(player.getHealth(),0,player.getMaxHealth())));
        enemy.setHealth((int)(Game.clamp(enemy.getHealth(),0,enemy.getMaxHealth())));

        player.setMana((int)(Game.clamp(player.getMana(),0,player.getMaxMana())));
        enemy.setMana((int)(Game.clamp(enemy.getMana(),0,enemy.getMaxMana())));


        if(player.getHealth() <= 0){
            		game.setCurrentState(Game.STATE.GameOver);
            		game.setSwitch(true);
                     game.removeKeyListener(battleKeyInput);
                     game.addKeyListener(game.getKeyInput());

            //ap.load();
                    ap.getMusic("Pikachu").stop();
                    ap.getMusic("Knuckles").stop();
                    ap.getMusic("BigChungus").stop();
                    ap.getMusic("Malario").stop();
                    ap.getMusic("TPoser").stop();
                    ap.getMusic("JoshuaGiraffe").stop();
                    ap.getMusic("FatYoshi").stop();
                    ap.getMusic("DatBoi").stop();
                    ap.getMusic("Kermit").stop();
                    ap.getMusic("BongoCat").stop();
                    ap.getMusic("Crab").stop();
                    ap.getMusic("AntiHero").stop();
                    ap.getMusic("AntiHeroB").stop();
                    ap.getMusic("Zuck").stop();
                    ap.getMusic("Harambe").stop();
                    ap.getMusic("YodelBoy").stop();
                    ap.getMusic("KazooKid").stop();
                    ap.getMusic("Spongebob").stop();
                    ap.getMusic("Waluigi").stop();
                    ap.getMusic("Spaget").stop();
                    ap.getMusic("Arthur").stop();
                    ap.getMusic("Shaggy").stop();


            ap.getSound("GameOver").play();
                    game.getPlayer().setAttack(this.baseAttack);
                    game.getPlayer().setDefense(this.baseDefense);


            	}

        if(enemy.getHealth()<=0) {
            endBattle();
        }
        if(playerTurn){

        }else if(!playerTurn){





        }

        if(player.getInv().getOpen()){
            player.getInv().tick();
        }
        if(player.getMagic().getOpen()){
            player.getMagic().tick();
        }

    }

    public void render(Graphics g){

        if(player.getInv().getOpen()){
            player.getInv().render(g);

            fnt = new Font("Serif", 0, 20);
            g.setFont(fnt);
            if(player.getBackwards())
                g.setColor(Color.black);
            else
                g.setColor(Color.white);


            g.drawString("HP", 500,825);
            player.drawHealthBar(800, g);
            if(player.getBackwards())
                g.setColor(Color.black);
            else
                g.setColor(Color.white);

            fnt = new Font("Serif", 1, 16);
            g.setFont(fnt);
            g.drawString(player.getHealth() + " / " + player.getMaxHealth(), 750, 824);

        }else if(player.getMagic().getOpen()){
            fnt = new Font("Serif", 0, 20);
            g.setFont(fnt);
            if(player.getBackwards())
                g.setColor(Color.black);
            else
                g.setColor(Color.white);
            g.drawString("Mana", 480,825);
            player.getMagic().render(g);
            player.drawManaBar(800, g);
            if(player.getBackwards())
                g.setColor(Color.black);
            else
                g.setColor(Color.white);

            fnt = new Font("Serif", 1, 16);
            g.setFont(fnt);
            g.drawString(player.getMana() + " / " + player.getMaxMana(), 750, 824);
        }else{
            if(player.getBackwards())
                g.setColor(Color.black);
            else
                g.setColor(Color.white);
            g.drawRect(512, 528, 256, 256);
            g.drawRect(511, 527, 258, 258);

            fnt = new Font("Serif", 0, 20);
            g.setFont(fnt);
            if(player.getBackwards())
                g.setColor(Color.black);
            else
                g.setColor(Color.white);
            g.drawString("HP", 500,825);
            player.drawHealthBar(800, g);
            if(player.getBackwards())
                g.setColor(Color.black);
            else
                g.setColor(Color.white);

            fnt = new Font("Serif", 1, 16);
            g.setFont(fnt);
            g.drawString(player.getHealth() + " / " + player.getMaxHealth(), 750, 824);


        }
        enemy.drawHealthBar( 25, g);

        fnt = new Font("Serif", 0, 29);
        g.setFont(fnt);
        g.setColor(enemy.nameColor);
        g.drawString(enemy.getName(), 25, 35);








        // if player turn, render certain things including handler, at end of turn, clear everything non-relavent
        if(playerTurn){
            if(playerTurnStart){
                startPlayerTurn();
            }else{
                if(player.getBackwards())
                    g.setColor(Color.black);
                else
                    g.setColor(Color.white);
                fnt = new Font("Serif", 1, 16);
                g.setFont(fnt);


                if(player.getBackwards())
                    g.setColor(Color.black);
                else
                    g.setColor(Color.white);

                g.drawRect(160, 100, 960, 320);
                if(enemyVisable){
                    if(enemy.getId()==ID.BigChungus){
                        g.drawImage(enemy.getBattleForm(), 445, 105,290, 305, null);
                    }else if(enemy.getId()==ID.AntiHero&&!player.getBackwards()){
                        g.drawImage(enemy.getBattleFormB(),615, 125,68, 266, null);

                    }else if(enemy.getId()==ID.AntiHero&&player.getBackwards()){
                        g.drawImage(enemy.getBattleForm(),615, 125,68, 266, null);

                    }else if(enemy.getId()==ID.YodelBoy){
                        g.drawImage(enemy.getBattleForm(), 545,105,190,305,null);
                    }else if(enemy.getId()==ID.Arthur){
                        g.drawImage(enemy.getBattleForm(),545,105,190,305,null);
                    }
                    else{
                        g.drawImage(enemy.getBattleForm(), 445, 105,390, 305, null);
                    }

                }else{
                    if(player.getBackwards())
                        g.setColor(Color.white);
                    else
                        g.setColor(Color.black);
                    g.fillRect(445, 105, 390, 305);
                }

                if(!player.getInv().getOpen()&&!player.getMagic().getOpen()){
                    g.drawImage(tex.Player_WalkUp[0], 612, 600, 38, 148, null);
                }

                if(enemyItemUse){
                    if(enemy.getId()==ID.AntiHero){
                        if(player.getBackwards()){
                            g.setColor(Color.black);
                        }else
                            g.setColor(Color.white);

                        g.drawRect(200,200,178,128);
                        g.drawRect(199,199,180,130);
                        g.drawString("Just used: ",210,220);
                        g.drawString(currentEnemyItem,210,240);



                    }
                }


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
                            currentOption = "RUN";
                            text_x = 210;
                        }


                        if(selectedOption[i]==true){
                            //update and do math for this
                            if(!player.getBackwards())
                                g.setColor(Color.orange.darker());
                            else
                                g.setColor(Color.blue.darker());
                            g.fillRect((i*267)+144, 860, 192, 64);
                            if(player.getBackwards())
                                g.setColor(Color.black);
                            else
                                g.setColor(Color.white);
                            g.drawRect((i*267)+144, 860, 192, 64);
                            g.drawRect((i*267)+143, 859, 194, 66);
                            fnt = new Font("Serif", 0, 29);
                            g.setFont(fnt);
                            if(player.getBackwards())
                                g.setColor(Color.white);
                            else
                                g.setColor(Color.black);
                            g.drawString(currentOption, (i*267)+text_x, 900);
                        }else{
                            if(player.getBackwards())
                                g.setColor(Color.white);
                            else
                                g.setColor(Color.black);
                            g.fillRect((i*267)+144, 860, 192, 64);
                            if(!player.getBackwards())
                                g.setColor(Color.orange.darker());
                            else
                                g.setColor(Color.blue.darker());
                            g.drawRect((i*267)+144, 860, 192, 64);
                            g.drawRect((i*267)+143, 859, 194, 66);
                            fnt = new Font("Serif", 0, 29);
                            g.setFont(fnt);
                            if(!player.getBackwards())
                                g.setColor(Color.orange.darker());
                            else
                                g.setColor(Color.blue.darker());
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
                            if(player.getBackwards())
                                g.setColor(Color.black);
                            else
                                g.setColor(Color.white);
                            g.drawRect(154, 800, 313, 96);
                            g.drawRect(153, 799, 315, 98);
                            if(enemy.getId()==ID.AntiHero&&!player.getBackwards()){
                                g.setColor(Color.white);
                            }else if(enemy.getId()==ID.AntiHero&&player.getBackwards()){
                                g.setColor(Color.black);
                            }else{
                                g.setColor(enemy.getNameColor());

                            }
                            fnt = new Font("Serif", 0, 29);
                            g.setFont(fnt);
                            g.drawString(enemy.getName(), 179, 830);
                            if(player.getBackwards())
                                g.setColor(Color.black);
                            else
                                g.setColor(Color.white);
                            g.fillRect(163, 817, 8, 8);
                        }


                    }else if(selectedOption[1]){
                        if(selection2){
                            // magic / item function to be created soon with init and deletion system for turn basing
                        }else{

                        }


                    }else if(selectedOption[2]){

                        if(selection2){
                            // items / item function to be created soon with init and deletion system for turn basing
                        }else{
                            // open player.getInv()
                        }


                    }else if(selectedOption[3]){
                        // run odds and end of battle
                        if(enemy.isRunnable()){
                            if(ThreadLocalRandom.current().nextInt(0,20)==0){
                                if(true){
                                    endBattle();
                                }
                            }else if(enemy.getHealth()<=enemy.getMaxHealth()/2){
                                if(ThreadLocalRandom.current().nextInt(0,10)==0){
                                    endBattle();
                                }
                            }else if(enemy.getHealth()<=enemy.getMaxHealth()/4){
                                if(ThreadLocalRandom.current().nextInt(0,5)==0){
                                    endBattle();
                                }
                            }
                        }
                        playerTurn=false;
                        enemyTurnStart=true;



                    }
                }else if(selection2){
                    if(selectedOption[0]){
                        if(player.getBackwards())
                            g.setColor(Color.black);
                        else
                            g.setColor(Color.white);
                        g.drawRect(154, 800, 512, 96);
                        g.drawRect(153, 799, 514, 98);
                        g.setColor(enemy.getNameColor());
                        fnt = new Font("Serif", 0, 29);
                        g.setFont(fnt);
                        g.drawString(enemy.getName(), 179, 830);
                        if(player.getBackwards())
                            g.setColor(Color.black);
                        else
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
                if(player.getBackwards())
                    g.setColor(Color.black);
                else
                    g.setColor(Color.white);

                g.drawRect(160, 100, 960, 320);
                if(enemy.getId()==ID.BigChungus){
                    g.drawImage(enemy.getBattleForm(), 445, 105,290, 305, null);
                }else if(enemy.getId()==ID.AntiHero&&!player.getBackwards()){
                    g.drawImage(enemy.getBattleFormB(),615, 125,68, 266, null);

                }else if(enemy.getId()==ID.AntiHero&&player.getBackwards()){
                    g.drawImage(enemy.getBattleForm(),615, 125,68, 266, null);

                }else if(enemy.getId()==ID.YodelBoy){
                    g.drawImage(enemy.getBattleForm(), 545,105,190,305,null);
                }else if(enemy.getId()==ID.Arthur){
                    g.drawImage(enemy.getBattleForm(),545,105,190,305,null);
                }
                else{
                    g.drawImage(enemy.getBattleForm(), 445, 105,390, 305, null);
                }
                // effect part of template



                if(enemy.getId()==ID.Knuckles){
                    if(enemy.getCurrentMove()==0){
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
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Knuckles_A1, 3, 18, 18, bPlayer, player, handler,this);
                                temp.boxBounce();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
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
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 31, 30, ID.EnemyAttackItem, tex.Knuckles_A1, 3, 18, 18, bPlayer, player, handler,this);
                                temp.randomProtShot();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==2){
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
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 34, 105, ID.EnemyAttackItem, tex.Knuckles_A2, 3, 105, 34, bPlayer, player, handler,this);
                                temp.DYKDW();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==3){
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
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 290, 270, ID.EnemyAttackItem, tex.Knuckles_A1, 6, 300, 310, bPlayer, player, handler,this);
                                temp.LS();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Pikachu) {
                    if (enemy.getCurrentMove() == 0) {

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
                                    EnemyAttackItem temp1 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler,this);
                                    EnemyAttackItem temp2 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler,this);
                                    EnemyAttackItem temp3 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler,this);
                                    EnemyAttackItem temp4 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler,this);
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
                                    EnemyAttackItem temp1 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler,this);
                                    EnemyAttackItem temp2 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler,this);
                                    EnemyAttackItem temp3 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler,this);
                                    EnemyAttackItem temp4 = new EnemyAttackItem(600, 600, 31, 30, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 18, 18, bPlayer, player, handler,this);
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
                    } else if (enemy.getCurrentMove() == 1) {
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

                                    EnemyAttackItem temp1 = new EnemyAttackItem(600, 600, 90, 55, ID.EnemyAttackItem, tex.Pikachu_A1, 2, 55, 90, bPlayer, player, handler,this);
                                    temp1.LargeLightningShot();
                                    handler.addObject(temp1);
                                    track++;
                                    firstLoop=false;
                                    timeOfLastShot2 = timeNow2;
                                }else{
                                    ArrayList<EnemyAttackItem> templist = handler.getELIST();
                                    // temp getting rid of 1
                                    templist.get(templist.size()-1).setVelY(20);


                                    EnemyAttackItem temp1 = new EnemyAttackItem(600, 600, 90, 55, ID.EnemyAttackItem, tex.Pikachu_A1, 3, 55, 90, bPlayer, player, handler,this);
                                    temp1.LargeLightningShot();
                                    handler.addObject(temp1);
                                    timeOfLastShot2 = timeNow2;
                                    track++;

                                }

                            }
                        }



                    }else if(enemy.getCurrentMove()==2){
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
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 97, 96, ID.EnemyAttackItem, tex.Pikachu_A2, 6, 96, 97, bPlayer, player, handler,this);
                                temp.Pokeball();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }


                        }

                    }else if(enemy.getCurrentMove()==3){
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
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 97, 96, ID.EnemyAttackItem, tex.Pikachu_A3, 6, 96, 97, bPlayer, player, handler,this);
                                temp.PikachuShot();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }


                        }

                    }
                }else if(enemy.getId()==ID.BigChungus){
                    if (enemy.getCurrentMove() == 0) {

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
                                EnemyAttackItem temp = new EnemyAttackItem(0,0,36,117,ID.EnemyAttackItem,tex.BigChungus_A1,1,117,36,bPlayer,player,handler,this);
                                temp.CarrotShot();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;

                            }
                        }
                    } else if (enemy.getCurrentMove() == 1) {
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
                                EnemyAttackItem temp = new EnemyAttackItem(0,0,128,128,ID.EnemyAttackItem,tex.BigChungus_A3,4,128,128,bPlayer,player,handler,this);
                                choice = temp.CoinFlip();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;

                            }
                        }



                    }else if(enemy.getCurrentMove()==2){
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
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 97, 96, ID.EnemyAttackItem, tex.BigChungus_A2, 4, 96, 97, bPlayer, player, handler,this);
                                temp.CardAttack();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }


                        }

                    }else if(enemy.getCurrentMove()==3){
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
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 488, 272, ID.EnemyAttackItem, tex.BigChungus_A4, 6, 272, 488, bPlayer, player, handler,this);
                                temp.Gottem();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }


                        }

                    }
                }else if(enemy.getId()==ID.Malario) {
                    if(enemy.getCurrentMove()==0){
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
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 66, 104, ID.EnemyAttackItem, tex.Malario_A1, 3, 104, 66, bPlayer, player, handler,this);
                                temp.Mosquito();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
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
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 18, 18, ID.EnemyAttackItem, tex.Malario_A2, 3, 18, 18, bPlayer, player, handler,this);
                                temp.Fireball();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getsID()==ID.TPoser){
                    if(enemy.getCurrentMove()==0){
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
                            if(time2>50 && track < 200){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 48, 48, ID.EnemyAttackItem, tex.Tposer_A1, 3, 48, 48, bPlayer, player, handler,this);
                                temp.letterT();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>25 && track < 200){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 50, 66, ID.EnemyAttackItem, tex.Tposer_A2, 3, 66, 50, bPlayer, player, handler,this);
                                temp.teaCup();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.FatYoshi){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 8050) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>800 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 52, 46, ID.EnemyAttackItem, tex.FatYoshi_A1, 3, 46, 52, bPlayer, player, handler,this);
                                temp.eggAttack();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 4050) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>400 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 105, 92, ID.EnemyAttackItem, tex.FatYoshi_A1, 3, 92, 105, bPlayer, player, handler,this);
                                temp.eggAttack2();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.JoshuaGiraffe){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10500) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1500 && track < 8){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 117, 71, ID.EnemyAttackItem, tex.JoshuaGiraffe_A1, 3, 71, 117, bPlayer, player, handler,this);
                                temp.giraffe();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 6050) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>600 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 43, 124, ID.EnemyAttackItem, tex.JoshuaGiraffe_A2, 3, 124, 43, bPlayer, player, handler,this);
                                temp.guitar();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.DatBoi){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10500) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1500 && track < 8){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 107, 57, ID.EnemyAttackItem, tex.DatBoi_A1, 3, 57, 107, bPlayer, player, handler,this);
                                temp.unicycle();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 6050) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>600 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 39, 48, ID.EnemyAttackItem, tex.DatBoi_A2, 3, 48, 39, bPlayer, player, handler,this);
                                temp.frog();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Kermit){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>650 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 58, 52, ID.EnemyAttackItem, tex.Kermit_A1, 3, 52, 58, bPlayer, player, handler,this);
                                temp.lipton();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 6050) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>600 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 58, 52, ID.EnemyAttackItem, tex.Kermit_A1, 3, 52, 58, bPlayer, player, handler,this);
                                temp.lipton2();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.BongoCat){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>650 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 40, 51, ID.EnemyAttackItem, tex.BongoCat_A1, 3, 51, 40, bPlayer, player, handler,this);
                                temp.bongo();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 6050) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>600 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 152, 106, ID.EnemyAttackItem, tex.BongoCat_A2, 3, 106, 152, bPlayer, player, handler,this);
                                temp.note();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Crab){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 34, 37, ID.EnemyAttackItem, tex.Crab_A1, 3, 37, 34, bPlayer, player, handler,this);
                                temp.crab();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 15000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>2000 && track < 5){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 51, 100, ID.EnemyAttackItem, tex.Crab_A2, 3, 100, 51, bPlayer, player, handler,this);
                                temp.crabArm();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.AntiHero){

                    if(enemy.getMaxHealth()/4>enemy.getHealth() && player.getBackwards()){
                        player.setBackwards(false);
                        setPlayerTurn(true);
                        setPlayerTurnStart(true);

                    }

                    // ////////// HEALING OPTIONS /////////////



                    if(enemy.getHealth()<=enemy.getMaxHealth()/2){
                        if(!(ThreadLocalRandom.current().nextInt(4)==0)){
                            // look for healing options

                            // check inventory
                            int bestHealI=0;
                            int locationI=-1;
                            for(int i = 0; i<enemy.getInv().inv.size(); i++){
                                int tempHealth=enemy.getHealth();
                                Enemy tempEnemy=new Enemy(0,0,0,0,handler,game,ID.AntiHero,3,game.getTbHandler(),"...",player,ID.AntiHero,enemy.getAttack(),enemy.getDefense(),enemy.getMoves(),enemy.getBattleForm(),enemy.getNameColor(),enemy.getHealth());
                                tempEnemy.inv=new Inventory(game);
                                for(int j = 0; j<enemy.getInv().inv.size(); j++){
                                    Items temp = enemy.getInv().inv.get(i);
                                    tempEnemy.inv.addItem(new Items(temp.getName(),temp.getDesc(),temp.getX(),temp.getY(),temp.getHeight(),temp.getWidth(),temp.getId(),game,temp.getAttack(),temp.getDefense(),temp.isHealing(),temp.isDamaging(),temp.getHealFactor(),temp.getDamageFactor()));
                                }
                                tempEnemy.magic=new Magic();
                                try{
                                    for(int j = 0; j<enemy.getMagic().magic.size(); j++){
                                        Spells temp = enemy.getMagic().magic.get(i);
                                        tempEnemy.magic.addItem(new Spells(temp.getName(),temp.getDesc(),tempEnemy.magic,temp.getX(),temp.getY(),temp.getHeight(),temp.getWidth(),temp.getId(),game,temp.getManaREQ(),temp.getHealing(),temp.getDamaging(),temp.getHealFactor(),temp.getDamageFactor()));
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    System.out.println("WEIRD ERROR");
                                }



                                Player tempPlayer=new Player(0,0,0,0,handler,game,ID.Player,3,player.getInv(),player.getMagic());
                                tempPlayer.health=player.getHealth();
                                System.out.println(tempEnemy.getHealth());
                                tempEnemy.getInv().inv.get(i).use(tempEnemy, tempPlayer);
                                System.out.println(tempEnemy.getHealth());
                                if(tempEnemy.getHealth()>enemy.getHealth()&&tempEnemy.getHealth()-enemy.getHealth()>bestHealI){
                                    System.out.println(true);
                                    bestHealI=tempEnemy.getHealth()-enemy.getHealth();
                                    locationI=i;
                                }
                            }


                            int bestHealM=0;
                            int locationM=-1;

                            for(int i = 0; i<enemy.getMagic().magic.size(); i++){
                                int tempHealth=enemy.getHealth();
                                Enemy tempEnemy=new Enemy(0,0,0,0,handler,game,ID.AntiHero,3,game.getTbHandler(),"...",player,ID.AntiHero,enemy.getAttack(),enemy.getDefense(),enemy.getMoves(),enemy.getBattleForm(),enemy.getNameColor(),enemy.getHealth());

                                tempEnemy.magic=new Magic();
                                for(int j = 0; j<enemy.getMagic().magic.size(); j++){
                                    Spells temp = enemy.getMagic().magic.get(i);
                                    tempEnemy.magic.addItem(new Spells(temp.getName(),temp.getDesc(),tempEnemy.magic,temp.getX(),temp.getY(),temp.getHeight(),temp.getWidth(),temp.getId(),game,temp.getManaREQ(),temp.getHealing(),temp.getDamaging(),temp.getHealFactor(),temp.getDamageFactor()));
                                }


                                Player tempPlayer=new Player(0,0,0,0,handler,game,ID.Player,3,player.getInv(),player.getMagic());
                                tempPlayer.health=player.getHealth();
                                try{
                                    if(tempEnemy.getInv().inv.size()!=1)
                                        tempEnemy.getInv().inv.get(i).use(tempEnemy, tempPlayer);

                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                                tempEnemy.getMagic().magic.get(i).use(tempEnemy, tempPlayer);
                                if(tempEnemy.getHealth()>enemy.getHealth()&&tempEnemy.getHealth()-enemy.getHealth()>bestHealM&&enemy.getMana()>=tempEnemy.getMagic().magic.get(i).getManaREQ()&&!(lastSpell.equals(tempEnemy.getMagic().magic.get(i).getName()))){
                                    bestHealM=tempEnemy.getHealth()-enemy.getHealth();
                                    locationM=i;
                                }
                            }


                            if(bestHealI>0 || bestHealM>0){
                                if(bestHealI>=bestHealM){
                                    currentEnemyItem=enemy.getInv().inv.get(locationI).getName();

                                    enemy.getInv().inv.get(locationI).use(enemy,player);
                                }else{
                                    System.out.println(enemy.getMagic().magic.get(locationM).getName());
                                    currentEnemyItem=enemy.getMagic().magic.get(locationM).getName();
                                    lastSpell=enemy.getMagic().magic.get(locationM).getName();

                                    enemy.getMagic().magic.get(locationM).use(enemy,player);


                                }
                                enemyItemUse=true;

                                setPlayerTurn(true);
                                setPlayerTurnStart(true);
                            }


                        }
                        }


                    if(!player.getBackwards()){
                        // random spell usage instead of a move
                        if(ThreadLocalRandom.current().nextInt(0,5)==0){

                            if(!(ThreadLocalRandom.current().nextInt(0,1)==0)){
                                // use a damaging random non healing spell or item

                                int mostDamage=0;
                                int location=-1;

                                for(int i=0; i<enemy.getInv().inv.size(); i++){
                                    if(enemy.getInv().inv.get(i).isDamaging()){
                                        if(mostDamage<enemy.getInv().inv.get(i).getDamageFactor()){
                                            mostDamage=enemy.getInv().inv.get(i).getDamageFactor();
                                            location=i;

                                        }
                                    }
                                }

                                int mostDamageM=0;
                                int locationM=-1;

                                for(int i = 0; i<enemy.getMagic().magic.size(); i++){
                                    if(enemy.getMagic().magic.get(i).getDamaging()){
                                        if(mostDamageM<enemy.getMagic().magic.get(i).getDamageFactor() && enemy.getMana()>=enemy.getMagic().magic.get(i).getManaREQ()){
                                            mostDamageM=enemy.getMagic().magic.get(i).getDamageFactor();
                                            locationM=i;
                                        }
                                    }
                                }
                                if(mostDamage>=mostDamageM && mostDamage!=0){
                                    currentEnemyItem=enemy.getInv().inv.get(location).getName();

                                    enemy.getInv().inv.get(location).use(enemy,player);
                                    enemyItemUse=true;
                                    setPlayerTurn(true);
                                    setPlayerTurnStart(true);

                                }else if(mostDamage<=mostDamageM && mostDamageM!=0){
                                    currentEnemyItem=enemy.getMagic().magic.get(locationM).getName();

                                    enemy.getMagic().magic.get(locationM).use(enemy,player);
                                    enemyItemUse=true;
                                    setPlayerTurn(true);
                                    setPlayerTurnStart(true);
                                }
                            }else{
                                Magic temp = new Magic();
                                for(int i=0; i<enemy.getMagic().magic.size(); i++){
                                    if(!(enemy.getMagic().magic.get(i).getDamaging()) && !(enemy.getMagic().magic.get(i).getHealing())){
                                        Spells tempSpell = enemy.getMagic().magic.get(i);
                                        temp.addItem(new Spells(tempSpell.getName(),tempSpell.getDesc(),temp,tempSpell.getX(),tempSpell.getY(),tempSpell.getWidth(),tempSpell.getHeight(),ID.Spell,game,tempSpell.getManaREQ(),tempSpell.getHealing(),tempSpell.getDamaging(),tempSpell.getHealFactor(),tempSpell.getDamageFactor()));
                                    }
                                }
                                if(temp.magic.size()!=0){
                                    int spot=ThreadLocalRandom.current().nextInt(0,temp.magic.size());
                                    currentEnemyItem=temp.magic.get(spot).getName();
                                    temp.magic.get(spot).use(enemy,player);
                                    enemyItemUse=true;

                                    setPlayerTurn(true);
                                    setPlayerTurnStart(true);

                                }


                            }




                        }


                    }







                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                            lastSpell="";
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>500 && track < 24){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 53, 67, ID.AntiHero, tex.AntiHero_A1, 3, 67, 53, bPlayer, player, handler,this);
                                temp.magicBlast1();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 15000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                            lastSpell="";

                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>500 && track < 28){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 53, 57, ID.EnemyAttackItem, tex.AntiHero_A2, 3, 57, 53, bPlayer, player, handler,this);
                                temp.magicBlast2();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }if(enemy.getCurrentMove()==2){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                            lastSpell="";

                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 45, 45, ID.EnemyAttackItem, tex.AntiHero_A3, 3, 45, 45, bPlayer, player, handler,this);
                                temp.magicBlast3();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==3){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 15000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                            lastSpell="";

                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1000 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 42, 59, ID.EnemyAttackItem, tex.AntiHero_A4, 3, 59, 42, bPlayer, player, handler,this);
                                int move = ThreadLocalRandom.current().nextInt(38);
                                switch (move){
                                    case 0:
                                        temp.boxBounce();
                                        break;
                                    case 1:
                                        temp.randomProtShot();
                                        break;
                                    case 2:
                                        temp.DYKDW();
                                        break;
                                    case 3:
                                        temp.LS();
                                        break;
                                    case 4:
                                        temp.LightningShot(ThreadLocalRandom.current().nextInt(4));
                                        break;
                                    case 5:
                                        temp.LargeLightningShot();
                                        break;
                                    case 6:
                                        temp.PikachuShot();
                                        break;
                                    case 7:
                                        temp.Fireball();
                                        break;
                                    case 8:
                                        temp.Mosquito();
                                        break;
                                    case 9:
                                        temp.letterT();
                                        break;
                                    case 10:
                                        temp.teaCup();
                                        break;
                                    case 11:
                                        temp.eggAttack();
                                        break;
                                    case 12:
                                        temp.eggAttack2();
                                        break;
                                    case 13:
                                        temp.giraffe();
                                        break;
                                    case 14:
                                        temp.guitar();
                                        break;
                                    case 15:
                                        temp.frog();
                                        break;
                                    case 16:
                                        temp.unicycle();
                                        break;
                                    case 17:
                                        temp.lipton();
                                        break;
                                    case 18:
                                        temp.lipton2();
                                        break;
                                    case 19:
                                        temp.bongo();
                                        break;
                                    case 20:
                                        temp.note();
                                        break;
                                    case 21:
                                        temp.crab();
                                        break;
                                    case 22:
                                        temp.crabArm();
                                        break;
                                    case 23:
                                        temp.banana();
                                        break;
                                    case 24:
                                        temp.rifle();
                                        break;
                                    case 25:
                                        temp.lizard();
                                        break;
                                    case 26:
                                        temp.facebook();
                                        break;
                                    case 27:
                                        temp.kazoo1();
                                        break;
                                    case 28:
                                        temp.kazoo2();
                                        break;
                                    case 29:
                                        temp.boot();
                                        break;
                                    case 30:
                                        temp.yodelnote();
                                        break;
                                    case 31:
                                        temp.krabbypatty();
                                        break;
                                    case 32:
                                        temp.net();
                                        break;
                                    case 33:
                                        temp.L();
                                        break;
                                    case 34:
                                        temp.racket();
                                        break;
                                    case 35:
                                        temp.gottemFist();
                                        break;
                                    case 36:
                                        temp.spagetFilled();
                                        break;
                                    case 37:
                                        temp.spaget();
                                        break;



                                }
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Harambe){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 68, 79, ID.EnemyAttackItem, tex.Harambe_A1, 3, 79, 68, bPlayer, player, handler,this);
                                temp.banana();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 15000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1000 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 28, 113, ID.EnemyAttackItem, tex.Harambe_A2, 3, 113, 28, bPlayer, player, handler,this);
                                temp.rifle();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Zuck){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 78, 80, ID.EnemyAttackItem, tex.Zuck_A1, 3, 80, 78, bPlayer, player, handler,this);
                                temp.lizard();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>100 && track < 1){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 80, 85, ID.EnemyAttackItem, tex.Zuck_A2, 3, 85, 80, bPlayer, player, handler,this);
                                temp.facebook();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.YodelBoy){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>750 && track < 20){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 250, 174, ID.EnemyAttackItem, tex.YodelBoy_A1, 3, 174, 250, bPlayer, player, handler,this);
                                temp.boot();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1000 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 145, 111, ID.EnemyAttackItem, tex.YodelBoy_A2, 3, 111, 145, bPlayer, player, handler,this);
                                temp.yodelnote();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Spongebob){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 176, 196, ID.EnemyAttackItem, tex.Spongebob_A1, 3, 196, 176, bPlayer, player, handler,this);
                                temp.krabbypatty();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 190, 150, ID.EnemyAttackItem, tex.Spongebob_A2, 3, 150, 190, bPlayer, player, handler,this);
                                temp.net();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Waluigi){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 186, 130, ID.EnemyAttackItem, tex.Waluigi_A1, 3, 130, 186, bPlayer, player, handler,this);

                                temp.L();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 114, 47, ID.EnemyAttackItem, tex.Waluigi_A2, 3, 47, 114, bPlayer, player, handler,this);

                                temp.racket();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.KazooKid){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 44, 124, ID.EnemyAttackItem, tex.KazooKid_A1, 3, 124, 44, bPlayer, player, handler,this);
                                temp.kazoo1();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 75, 110, ID.EnemyAttackItem, tex.KazooKid_A2, 3, 110, 75, bPlayer, player, handler,this);
                                temp.kazoo2();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Arthur){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 5000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1000 && track < 1){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 79, 75, ID.EnemyAttackItem, tex.Arthur_A1, 3, 75, 79, bPlayer, player, handler,this);
                                temp.gottemFist();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 20, 20, ID.EnemyAttackItem, tex.Arthur_A2, 3, 20, 20, bPlayer, player, handler,this);
                                temp.fist();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Spaget){
                    if(enemy.getCurrentMove()==0){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 7000) {
                            timeOfLastShot=0;
                            // end func
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(600, 600, 72, 109, ID.EnemyAttackItem, tex.Spaget_A1, 3, 109, 72, bPlayer, player, handler,this);
                                temp.spagetFilled();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }else if(enemy.getCurrentMove()==1){
                        timeNow = System.currentTimeMillis();
                        time = timeNow - timeOfLastShot;

                        if(time > 10000) {
                            timeOfLastShot=0;
                            // will update damage calculation based on attack / defense, for now is one value
                            setPlayerTurn(true);
                            // will do same for player when enemy uses this function for end
                            setPlayerTurnStart(true);
                        }else{
                            timeNow2 = System.currentTimeMillis();
                            time2 = timeNow2 - timeOfLastShot2;
                            if(time2>1250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(-100, 0, 80, 134, ID.EnemyAttackItem, tex.Spaget_A2, 3, 134, 80, bPlayer, player, handler,this);
                                temp.spaget();
                                handler.addObject(temp);
                                timeOfLastShot2=timeNow2;
                                track++;
                            }
                        }
                    }
                }else if(enemy.getId()==ID.Shaggy){

                    if(enemy.getCurrentMove()==0){
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
                            if(time2>250 && track < 10){
                                EnemyAttackItem temp = new EnemyAttackItem(0, 0, 25, 4000, ID.EnemyAttackItem, tex.AntiHero_A1, 3, 4000, 25, bPlayer, player, handler,this);
                                temp.yeet();
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
        this.player.setMana(this.player.getMana()+ThreadLocalRandom.current().nextInt(5,10));
        if(!firstTurn){
            if(enemy.getId()==ID.AntiHero){
                if(ThreadLocalRandom.current().nextInt(0,4)==0){
                    player.setBackwards(!player.getBackwards());

                }
            }
        }




        // possible sound here for effect



    }

    public void startEnemyTurn(){
        if(enemyItemUse)
            enemyItemUse=false;
        if(enemy.getId()==ID.AntiHero)
            enemy.setMana(enemy.getMana()+ThreadLocalRandom.current().nextInt(5,11));
        if(firstTurn){
            firstTurn=false;
        }
        enemy.setMana((int)(Game.clamp(enemy.getMana(),0,enemy.getMaxMana())));


        // redefine all enemy attack objects previously made in init



        handler.clear();

        if(enemy.getHealth()<=enemy.getMaxHealth()/2){
            lowHealth=true;
        }

        bPlayer.setX(630);
        bPlayer.setY(650);
        bPlayer.setVelX(0);
        bPlayer.setVelY(0);
        handler.addObject(bPlayer);
        enemyTurnStart=false;
        enemy.chooseMove();
        // use setup
        track = 0;
        timeOfLastShot=System.currentTimeMillis();
        timeOfLastShot2=System.currentTimeMillis();
        firstLoop=true;


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

    public void endBattle(){
        player.setBackwards(false);
        game.getCurrentBattle().getEnemy().setDefeated(true);


        ap.getMusic("Pikachu").stop();
        ap.getMusic("Knuckles").stop();
        ap.getMusic("BigChungus").stop();
        ap.getMusic("Malario").stop();
        ap.getMusic("TPoser").stop();
        ap.getMusic("JoshuaGiraffe").stop();
        ap.getMusic("FatYoshi").stop();
        ap.getMusic("DatBoi").stop();
        ap.getMusic("Kermit").stop();
        ap.getMusic("BongoCat").stop();
        ap.getMusic("Crab").stop();
        ap.getMusic("AntiHero").stop();
        ap.getMusic("AntiHeroB").stop();
        ap.getMusic("Zuck").stop();
        ap.getMusic("Harambe").stop();
        ap.getMusic("YodelBoy").stop();
        ap.getMusic("KazooKid").stop();
        ap.getMusic("Spongebob").stop();
        ap.getMusic("Waluigi").stop();
        ap.getMusic("Spaget").stop();
        ap.getMusic("Arthur").stop();
        ap.getMusic("Shaggy").stop();

        if(!game.getCurrentMusic().equals("none")){
            AudioPlayer.getMusic(game.getCurrentMusic()).loop();
        }


        game.setCurrentState(Game.STATE.FirstArea);
        game.setSwitch(false);
        game.removeKeyListener(battleKeyInput);
        //game.setCurrentBattle(null);

        int amount=0;

        if(enemy.getName().equals("Surprised Pikachu")){
            amount =ThreadLocalRandom.current().nextInt(1000,2000);
        }else if(enemy.getName().equals("Ugandan Knuckles")){
            amount =ThreadLocalRandom.current().nextInt(500,1000);
            //player.setLimited(true);
            //game.getTbHandler().addObject(new TextBox(enemy,"Thank you, you have freed me from his curse. Continue with your journey. Good luck!",0,0,0,0,ID.TextBox,game.getTbHandler()));
        }else if(enemy.getName().equals("Big Chungus")){
            amount =ThreadLocalRandom.current().nextInt(10000,20000);
        }else if(enemy.getName().equals("Malario")){
            amount =ThreadLocalRandom.current().nextInt(20,30);
        }else if(enemy.getName().equals("TPoser")){
            amount = ThreadLocalRandom.current().nextInt(18,25);
        }else if(enemy.getName().equals("Bongo Cat") || enemy.getName().equals("Zuckerberg") || enemy.getName().equals("Dat Boi") || enemy.getName().equals("SpOnGeBoB")){
            amount = ThreadLocalRandom.current().nextInt(30,75);


        }else if(enemy.getName().equals("Crab Rave") || enemy.getName().equals("Kermit") || enemy.getName().equals("The Kazoo Kid") || enemy.getName().equals("Walmart Yodel Boy")){
            amount = ThreadLocalRandom.current().nextInt(100,200);

        }else if(enemy.getId()==ID.FatYoshi){
            amount =ThreadLocalRandom.current().nextInt(25,32);

        }else if(enemy.getId()==ID.JoshuaGiraffe){
            amount =ThreadLocalRandom.current().nextInt(22,45);

        }else if(enemy.getName().equals("Harambe") || enemy.getName().equals("Waluigi") || enemy.getName().equals("Arthur") || enemy.getName().equals("Spaget")){
            amount = ThreadLocalRandom.current().nextInt(200,300);

        }else if(enemy.getName().equals("The Antihero")){
            amount =ThreadLocalRandom.current().nextInt(2000,4000);

        }
        game.getPlayer().setXp(game.getPlayer().getXp()+amount);



        while(player.getXp()>=player.getLevel()*100){
            player.LevelUp();

        }
        game.getPlayer().setLimited(true);

        if(enemy.getId()!=ID.BigChungus){
            if(player.getLevel()>this.baseLevel){
                game.getPlayer().setLimited(true);
                game.getTbHandler().addObject(new TextBox(player,"You leveled up to Level "+game.getPlayer().getLevel()+" and gained "+amount+" xp.",0,0,0,0,ID.TextBox,game.getTbHandler()));
            }else{
                game.getPlayer().setLimited(true);
                game.getTbHandler().addObject(new TextBox(player, "You got "+amount+" xp.",0,0,0,0,ID.TextBox,game.getTbHandler()));
                //  game.getPlayer().setLimited(true);




            }
        }else{
            game.getPlayer().setLimited(true);
            game.getTbHandler().addObject(new TextBox(game.getBigChungus(),"*Sigh* You have defeated me. But hero.... You have the choice... There are 2 orbs. Please make the right choice.",0,0,0,0,ID.TextBox,game.getTbHandler()));
        }





        game.getPlayer().setVelX(0);
        game.getPlayer().setVelY(0);

        if(enemy.getHealth()<=0){
            player.addOneKill();
        }






    }

    public Enemy getEnemy(){
        return this.enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public String getCurrentOption() {
        return currentOption;
    }

    public void setCurrentOption(String currentOption) {
        this.currentOption = currentOption;
    }

    public Enemy getRealEnemy() {
        return realEnemy;
    }

    public void setRealEnemy(Enemy realEnemy) {
        this.realEnemy = realEnemy;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public BattleKeyInput getBattleKeyInput() {
        return battleKeyInput;
    }

    public void setBattleKeyInput(BattleKeyInput battleKeyInput) {
        this.battleKeyInput = battleKeyInput;
    }

    public Textures getTex() {
        return tex;
    }

    public void setTex(Textures tex) {
        this.tex = tex;
    }

    public void setSelectedOption(boolean[] selectedOption) {
        this.selectedOption = selectedOption;
    }

    public int getText_x() {
        return text_x;
    }

    public void setText_x(int text_x) {
        this.text_x = text_x;
    }

    public boolean isSelection1() {
        return selection1;
    }

    public Font getFnt() {
        return fnt;
    }

    public void setFnt(Font fnt) {
        this.fnt = fnt;
    }

    public boolean isSelection2() {
        return selection2;
    }

    public boolean isEnemyVisable() {
        return enemyVisable;
    }

    public boolean isEffectTime() {
        return effectTime;
    }

    public boolean isFirstTimeInTurn() {
        return firstTimeInTurn;
    }

    public boolean isPauseEnd() {
        return pauseEnd;
    }

    public S_BasicAttack getAttack() {
        return attack;
    }

    public void setAttack(S_BasicAttack attack) {
        this.attack = attack;
    }

    public boolean isPlayerTurnStart() {
        return playerTurnStart;
    }

    public boolean isEnemyTurnStart() {
        return enemyTurnStart;
    }

    public BattlePlayer getbPlayer() {
        return bPlayer;
    }

    public void setbPlayer(BattlePlayer bPlayer) {
        this.bPlayer = bPlayer;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public long getTimeOfLastShot() {
        return timeOfLastShot;
    }

    public void setTimeOfLastShot(long timeOfLastShot) {
        this.timeOfLastShot = timeOfLastShot;
    }

    public long getTimeOfLastShot2() {
        return timeOfLastShot2;
    }

    public void setTimeOfLastShot2(long timeOfLastShot2) {
        this.timeOfLastShot2 = timeOfLastShot2;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTimeNow() {
        return timeNow;
    }

    public void setTimeNow(long timeNow) {
        this.timeNow = timeNow;
    }

    public long getTime2() {
        return time2;
    }

    public void setTime2(long time2) {
        this.time2 = time2;
    }

    public long getTimeNow2() {
        return timeNow2;
    }

    public void setTimeNow2(long timeNow2) {
        this.timeNow2 = timeNow2;
    }

    public boolean isFirstLoop() {
        return firstLoop;
    }

    public void setFirstLoop(boolean firstLoop) {
        this.firstLoop = firstLoop;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public AudioPlayer getAp() {
        return ap;
    }

    public void setAp(AudioPlayer ap) {
        this.ap = ap;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public boolean getLowHealth() {
        return lowHealth;
    }

    public void setLowHealth(boolean lowHealth) {
        this.lowHealth = lowHealth;
    }




}
