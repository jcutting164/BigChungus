import java.awt.*;


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



    private M_Knuckles_1 m_knuckles_1;





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
        this.bPlayer = new BattlePlayer(640, 650, 16, 16, ID.BattlePlayer);
        this.battleKeyInput = new BattleKeyInput(handler, player, this, bPlayer);
        game.addKeyListener(this.battleKeyInput);

        if(enemy.getId()==ID.Knuckles){
            m_knuckles_1=new M_Knuckles_1(0,0,1,1280,ID.M_Knuckles1,this.handler,this,this.enemy, this.bPlayer, this.player);
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
                    g.drawImage(enemy.BattleForm, 445, 105,390, 305, null);
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
                g.drawImage(enemy.BattleForm, 445, 105,390, 305, null);

                if(enemy.moves==1){
                    m_knuckles_1.effect();
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

        m_knuckles_1.setX(0);
        m_knuckles_1.setY(0);

        handler.clear();
        bPlayer.setX(630);
        bPlayer.setY(650);
        bPlayer.setVelX(0);
        bPlayer.setVelY(0);
        handler.addObject(bPlayer);
        enemyTurnStart=false;
        enemy.chooseMove();
        System.out.println(enemy.moves);
        if(enemy.getId()==ID.Knuckles){
            if(enemy.moves==1){
                m_knuckles_1.use();
            }
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
