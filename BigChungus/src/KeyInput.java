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
    private Textures tex = Game.getInstance();

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
                        //hud.setScore(hud.getScore() + 100);

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

                    if(game.getCurrentRoom().equals("Room5_4")){

                        if(key==KeyEvent.VK_A){
                            AudioPlayer.getSound("C3").play();
                            game.getC3().setImg(tex.whiteKeyPressed);
                            pianoReset();


                        }else if(key==KeyEvent.VK_W){
                            AudioPlayer.getSound("C#3").play();
                            game.getC3S().setImg(tex.blackKeyPressed);
                            pianoReset();



                        }else if(key==KeyEvent.VK_S){
                            AudioPlayer.getSound("D3").play();
                            game.getD3().setImg(tex.whiteKeyPressed);

                            if(!(game.getPianoKey()[0]))
                                game.getPianoKey()[0]=true;
                            else if(!(game.getPianoKey()[1])){
                                for(int j =0; j<1;j++){
                                    if(!(game.getPianoKey()[j])){
                                        // reset back
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[1]=true;
                                }
                            }else if(!(game.getPianoKey()[7])){
                                for(int j=0; j<7; j++){
                                    if(!(game.getPianoKey()[j])){
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[7]=true;
                                }
                            }




                        }else if(key==KeyEvent.VK_E){
                            AudioPlayer.getSound("D#3").play();
                            game.getD3S().setImg(tex.blackKeyPressed);

                            pianoReset();



                        }else if(key==KeyEvent.VK_D){
                            AudioPlayer.getSound("E3").play();
                            game.getE3().setImg(tex.whiteKeyPressed);
                            pianoReset();


                        }else if(key==KeyEvent.VK_F){
                            AudioPlayer.getSound("F3").play();
                            game.getF3().setImg(tex.whiteKeyPressed);

                            if(!(game.getPianoKey()[6])){
                                for(int j = 0;j<6; j++){
                                    if(!(game.getPianoKey()[j])){
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[6]=true;
                                }
                            }else if(!(game.getPianoKey()[8])){
                                for(int j = 0;j<8; j++){
                                    if(!(game.getPianoKey()[j])){
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[8]=true;
                                }
                            }



                        }else if(key==KeyEvent.VK_T){
                            AudioPlayer.getSound("F#3").play();
                            game.getF3S().setImg(tex.blackKeyPressed);
                            pianoReset();

                        }else if(key==KeyEvent.VK_G){
                            AudioPlayer.getSound("G3").play();
                            game.getG3().setImg(tex.whiteKeyPressed);

                            if(!(game.getPianoKey()[5])){
                                for(int j = 0;j<5; j++){
                                    if(!(game.getPianoKey()[j])){
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[5]=true;
                                }
                            }else if(!(game.getPianoKey()[9])){
                                for(int j = 0;j<9; j++){
                                    if(!(game.getPianoKey()[j])){
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[9]=true;
                                }
                            }

                            game.setPianoPuzzle(game.allContains(game.getPianoKey()));

                            if(game.isPianoPuzzle()){
                                game.getHandler().removeObject(game.getPianoBlock());

                                key=0;
                            }



                        }else if(key==KeyEvent.VK_Y){
                            AudioPlayer.getSound("G#3").play();
                            game.getG3S().setImg(tex.blackKeyPressed);

                            if(!(game.getPianoKey()[4])){
                                for(int j = 0;j<4; j++){
                                    if(!(game.getPianoKey()[j])){
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[4]=true;
                                }
                            }


                        }else if(key==KeyEvent.VK_H){
                            AudioPlayer.getSound("A4").play();
                            game.getA4().setImg(tex.whiteKeyPressed);

                            if(!(game.getPianoKey()[3])){
                                for(int j = 0;j<3; j++){
                                    if(!(game.getPianoKey()[j])){
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[3]=true;
                                }
                            }

                        }else if(key==KeyEvent.VK_U){
                            AudioPlayer.getSound("A#4").play();
                            game.getA4S().setImg(tex.blackKeyPressed);
                            pianoReset();

                        }else if(key==KeyEvent.VK_J){
                            AudioPlayer.getSound("B4").play();
                            game.getB4().setImg(tex.whiteKeyPressed);
                            pianoReset();


                        }else if(key==KeyEvent.VK_K){
                            AudioPlayer.getSound("C4").play();
                            game.getC4().setImg(tex.whiteKeyPressed);
                            pianoReset();


                        }else if(key==KeyEvent.VK_O){
                            AudioPlayer.getSound("C#4").play();
                            game.getC4S().setImg(tex.blackKeyPressed);
                            pianoReset();

                        }else if(key==KeyEvent.VK_L){
                            AudioPlayer.getSound("D4").play();
                            game.getD4().setImg(tex.whiteKeyPressed);


                            if(!(game.getPianoKey())[2]){
                                for(int j=0; j<2; j++){
                                    if(!(game.getPianoKey()[j])){
                                        pianoReset();
                                        break;
                                    }else
                                        game.getPianoKey()[2]=true;
                                }
                            }


                        }else if(key==KeyEvent.VK_P){
                            AudioPlayer.getSound("D#4").play();
                            game.getD4S().setImg(tex.blackKeyPressed);
                            pianoReset();

                        }else if(key==KeyEvent.VK_SEMICOLON){
                            AudioPlayer.getSound("E4").play();
                            game.getE4().setImg(tex.whiteKeyPressed);
                            pianoReset();


                        }
                        // GGGGGBGGBYGGBYRGGBYRRGGBYRRYGGBYRRYGGGBYRRYGYGGBYRRYGYR
                        // KEY: plus positions in key
                        // Q - Green -
                        // W - RED
                        // A - BLUE
                        // S - YELLOW
                    }else if(game.getCurrentRoom().equals("Room5_5")){
                        if(key==KeyEvent.VK_Q){
                            game.getGreenSimon().setsID(ID.green);
                            game.setGreenPressed(System.currentTimeMillis());
                            AudioPlayer.getSound("SimonGreen").play();

                            // go in chronological order to find the first false then check if all before are true
                            int temp=-1;
                            if(!game.getSimonSays()[0])
                                temp=0;
                            else if(!game.getSimonSays()[1])
                                temp=1;
                            else if(!game.getSimonSays()[2])
                                temp=2;
                            else if(!game.getSimonSays()[3])
                                temp=3;
                            else if(!game.getSimonSays()[4])
                                temp=4;
                            else if(!game.getSimonSays()[6])
                                temp=6;
                            else if(!game.getSimonSays()[7])
                                temp=7;
                            else if(!game.getSimonSays()[10])
                                temp=10;
                            else if(!game.getSimonSays()[11])
                                temp=11;
                            else if(!game.getSimonSays()[15])
                                temp=15;
                            else if(!game.getSimonSays()[16])
                                temp=16;
                            else if(!game.getSimonSays()[21])
                                temp=21;
                            else if(!game.getSimonSays()[22])
                                temp=22;
                            else if(!game.getSimonSays()[28])
                                temp=28;
                            else if(!game.getSimonSays()[29])
                                temp=29;
                            else if(!game.getSimonSays()[35])
                                temp=35;
                            else if(!game.getSimonSays()[36])
                                temp=36;
                            else if(!game.getSimonSays()[37])
                                temp=37;
                            else if(!game.getSimonSays()[43])
                                temp=43;
                            else if(!game.getSimonSays()[45])
                                temp=45;
                            else if(!game.getSimonSays()[46])
                                temp=46;
                            else if(!game.getSimonSays()[52])
                                temp=52;

                            if(temp!=0){
                                System.out.println("The loops runs");
                                for(int j = 0; j<temp; j++){
                                    if(!(game.getSimonSays()[j])){
                                        game.resetSimon();
                                        AudioPlayer.getSound("SimonFail");
                                        break;
                                    }else
                                        game.getSimonSays()[temp]=true;
                                }
                            }else{
                                game.getSimonSays()[0]=true;
                            }





                        }else if(key==KeyEvent.VK_W){
                            game.getRedSimon().setsID(ID.red);
                            game.setRedPressed(System.currentTimeMillis());
                            AudioPlayer.getSound("SimonRed").play();

                            int temp=-1;
                            if(!game.getSimonSays()[14])
                                temp=14;
                            else if(!game.getSimonSays()[19])
                                temp=19;
                            else if(!game.getSimonSays()[20])
                                temp=20;
                            else if(!game.getSimonSays()[25])
                                temp=25;
                            else if(!game.getSimonSays()[26])
                                temp=26;
                            else if(!game.getSimonSays()[32])
                                temp=32;
                            else if(!game.getSimonSays()[33])
                                temp=33;
                            else if(!game.getSimonSays()[40])
                                temp=40;
                            else if(!game.getSimonSays()[41])
                                temp=41;
                            else if(!game.getSimonSays()[49])
                                temp=49;
                            else if(!game.getSimonSays()[50])
                                temp=50;
                            else if(!game.getSimonSays()[54])
                                temp=54;



                            if(temp!=0){
                                System.out.println("The loops runs");
                                for(int j = 0; j<temp; j++){
                                    if(!(game.getSimonSays()[j])){
                                        game.resetSimon();
                                        AudioPlayer.getSound("SimonFail").play();
                                        break;
                                    }else
                                        game.getSimonSays()[temp]=true;
                                }
                            }else{
                                game.getSimonSays()[0]=true;
                            }


                        }else if(key==KeyEvent.VK_A){
                            int temp=-1;
                            if(!game.getSimonSays()[5])
                                temp=5;
                            else if(!game.getSimonSays()[8])
                                temp=8;
                            else if(!game.getSimonSays()[12])
                                temp=12;
                            else if(!game.getSimonSays()[17])
                                temp=17;
                            else if(!game.getSimonSays()[23])
                                temp=23;
                            else if(!game.getSimonSays()[30])
                                temp=30;
                            else if(!game.getSimonSays()[38])
                                temp=38;
                            else if(!game.getSimonSays()[47])
                                temp=47;




                            if(temp!=0){
                                System.out.println("The loops runs");
                                for(int j = 0; j<temp; j++){
                                    if(!(game.getSimonSays()[j])){
                                        game.resetSimon();
                                        AudioPlayer.getSound("SimonFail").play();
                                        break;
                                    }else
                                        game.getSimonSays()[temp]=true;
                                }
                            }else{
                                game.getSimonSays()[0]=true;
                            }



                            game.getBlueSimon().setsID(ID.blue);
                            game.setBluePressed(System.currentTimeMillis());
                            AudioPlayer.getSound("SimonBlue").play();

                        }else if(key==KeyEvent.VK_S){
                            int temp=-1;
                            if(!game.getSimonSays()[9])
                                temp=9;
                            else if(!game.getSimonSays()[13])
                                temp=13;
                            else if(!game.getSimonSays()[18])
                                temp=18;
                            else if(!game.getSimonSays()[24])
                                temp=24;
                            else if(!game.getSimonSays()[27])
                                temp=27;
                            else if(!game.getSimonSays()[31])
                                temp=31;
                            else if(!game.getSimonSays()[34])
                                temp=34;
                            else if(!game.getSimonSays()[39])
                                temp=39;
                            else if(!game.getSimonSays()[42])
                                temp=42;
                            else if(!game.getSimonSays()[44])
                                temp=44;
                            else if(!game.getSimonSays()[48])
                                temp=48;
                            else if(!game.getSimonSays()[51])
                                temp=51;
                            else if(!game.getSimonSays()[53])
                                temp=53;





                            if(temp!=0){
                                System.out.println("The loops runs");
                                for(int j = 0; j<temp; j++){
                                    if(!(game.getSimonSays()[j])){
                                        game.resetSimon();
                                        AudioPlayer.getSound("SimonFail").play();
                                        break;
                                    }else
                                        game.getSimonSays()[temp]=true;
                                }
                            }else{
                                game.getSimonSays()[0]=true;
                            }


                            game.getYellowSimon().setsID(ID.yellow);
                            game.setYellowPressed(System.currentTimeMillis());
                            AudioPlayer.getSound("SimonYellow").play();


                        }
                    }

                }



            }
        }else if(player.getLimited() && handler.object.contains(player)){


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

                        if(!inv.getOptions()[inv.inv.size()-1]){
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


        if(game.getCurrentRoom().equals("Room5_4")) {

            if (key == KeyEvent.VK_A) {
                game.getC3().setImg(tex.whiteKey);

            } else if (key == KeyEvent.VK_W) {
                game.getC3S().setImg(tex.blackKey);


            } else if (key == KeyEvent.VK_S) {
                game.getD3().setImg(tex.whiteKey);

            } else if (key == KeyEvent.VK_E) {
                game.getD3S().setImg(tex.blackKey);

            } else if (key == KeyEvent.VK_D) {
                game.getE3().setImg(tex.whiteKey);

            } else if (key == KeyEvent.VK_F) {
                game.getF3().setImg(tex.whiteKey);

            } else if (key == KeyEvent.VK_T) {
                game.getF3S().setImg(tex.blackKey);
            } else if (key == KeyEvent.VK_G) {
                game.getG3().setImg(tex.whiteKey);


            } else if (key == KeyEvent.VK_Y) {
                game.getG3S().setImg(tex.blackKey);
            } else if (key == KeyEvent.VK_H) {
                game.getA4().setImg(tex.whiteKey);

            } else if (key == KeyEvent.VK_U) {
                game.getA4S().setImg(tex.blackKey);
            } else if (key == KeyEvent.VK_J) {
                game.getB4().setImg(tex.whiteKey);

            } else if (key == KeyEvent.VK_K) {
                game.getC4().setImg(tex.whiteKey);

            } else if (key == KeyEvent.VK_O) {
                game.getC4S().setImg(tex.blackKey);
            } else if (key == KeyEvent.VK_L) {
                game.getD4().setImg(tex.whiteKey);

            } else if (key == KeyEvent.VK_P) {
                game.getD4S().setImg(tex.blackKey);
            } else if (key == KeyEvent.VK_SEMICOLON) {
                game.getE4().setImg(tex.whiteKey);

            }
        }else if(game.getCurrentRoom().equals("Room5_5")){
            /*
            if(key==KeyEvent.VK_Q){
                game.getGreenSimon().setsID(ID.lightGreen);
            }else if(key==KeyEvent.VK_W){
                game.getRedSimon().setsID(ID.lightRed);

            }else if(key==KeyEvent.VK_A){
                game.getBlueSimon().setsID(ID.lightBlue);

            }else if(key==KeyEvent.VK_S){
                game.getYellowSimon().setsID(ID.lightYellow);

            }*/
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

    public void pianoReset(){
        for(int i = 0; i<10; i++){
            game.getPianoKey()[i]=false;
        }
    }


}