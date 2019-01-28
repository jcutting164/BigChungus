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



    public Battle(Player player, Enemy enemy, Handler handler, Game game){
        this.player = player;
        this.enemy = enemy;
        this.handler = handler;
        this.playerTurn=true;
        this.game = game;
        this.battleKeyInput = new BattleKeyInput(handler, player, this);
        game.addKeyListener(this.battleKeyInput);
        this.selectedOption = new boolean[4];
        this.selectedOption[0] = true;
        this.selectedOption[1] = false;
        this.selectedOption[2] = false;
        this.selectedOption[3] = false;


        if(enemy.getId()==ID.Knuckles){
            enemy = (Knuckles) enemy;

             //= new Knuckles(enemy.getX(), enemy.getY(), enemy.getHeight(), enemy.getWidth(), handler, game, ID.Knuckles, 2, tbHandler, "", player, true);
        }

    }


    public void tick(){
        if(playerTurn){

        }else if(!playerTurn){

        }

    }

    public void render(Graphics g){
        // if player turn, render certain things including handler, at end of turn, clear everything non-relavent
        if(playerTurn){
            g.setColor(Color.white);

            g.drawRect(160, 100, 960, 320);
            g.drawImage(enemy.BattleForm, 445, 105,390, 305, null);

            g.drawRect(512, 528, 256, 256);
            g.drawImage(tex.Player_WalkUp[0], 600, 600, 38, 148, null);

            for(int i = 0; i<4; i++){
                if(i==0)
                    currentOption = "FIGHT";
                else if(i==1)
                    currentOption = "MAGIC";
                else if(i==2)
                    currentOption = "ITEMS";
                else if(i==3)
                    currentOption = "SPECIAL";
                if(selectedOption[i]==true){
                    g.setColor(Color.blue);
                    g.fillRect((i*170)+100, 860, 192, 64);
                    g.setColor(Color.orange);
                    g.drawRect((i*170)+100, 860, 192, 64);
                    g.drawRect((i*170)+99, 859, 194, 66);
                    Font fnt = new Font("Serif", 0, 29);
                    g.setFont(fnt);
                    g.setColor(Color.orange);
                    g.drawString(currentOption, 154, 900);
                }else{
                    g.setColor(Color.black);
                    g.fillRect((i*170)+100, 860, 192, 64);
                    g.setColor(Color.orange);
                    g.drawRect((i*170)+100, 860, 192, 64);
                    g.drawRect((i*170)+99, 859, 194, 66);
                    Font fnt = new Font("Serif", 0, 29);
                    g.setFont(fnt);
                    g.setColor(Color.orange);
                    g.drawString(currentOption, 154, 900);
                }
            }


        }else if(!playerTurn){
            g.setColor(Color.white);

            g.drawRect(160, 100, 960, 320);
            g.drawImage(enemy.BattleForm, 445, 105,390, 305, null);
        }

    }

    public void startPlayerTurn(){
        handler.clear();
        this.selectedOption = new boolean[4];
        this.selectedOption[0] = true;
        this.selectedOption[1] = false;
        this.selectedOption[2] = false;
        this.selectedOption[3] = false;
        // possible sound here for effect



        // add 4 options to the handler with keylistener that supports this
    }

    public void startEnemyTurn(){
        handler.clear();

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
        t
        this.selectedOption = selectedOption;
    }



}
