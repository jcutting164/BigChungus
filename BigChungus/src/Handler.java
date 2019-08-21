import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Handler implements Serializable {

    transient LinkedList<GameObject> object = new LinkedList<GameObject>();
    Game game;
    public Handler(Game game){
        this.game=game;
    }

    public void tick() {

        // tiles




        for(int i = 0; i < object.size(); i++) {

            GameObject tempObject = object.get(i);
            tempObject.tick();


        }
    }

    public void render(Graphics g) {


        if(game.getCurrentState()!=Game.STATE.SpecialGame){

            BufferedImage image=null;
            if(!(game.getRooms()==null))
                image = game.getRooms().get(game.getCurrentRoom());
            else{
                Textures tex = Game.getInstance();
                game.setRooms(new HashMap<>());
                game.getRooms().put("Testing Room", tex.SS_FirstArea.grabImage(1, 1, 16, 16));
                game.getRooms().put("Room1_1", tex.Room1_1);
                game.getRooms().put("Room2_1",tex.Room2_1);
                game.getRooms().put("Room2_2",tex.Room2_2);
                game.getRooms().put("Room2_3",tex.Room2_3);
                game.getRooms().put("Room2_4",tex.Room2_4);
                game.getRooms().put("Room3_1",tex.Room3_1);
                game.getRooms().put("Room3_2",tex.Room3_2);
                game.getRooms().put("Room3_3",tex.Room3_3);
                game.getRooms().put("Room3_4",tex.Room3_4);
                game.getRooms().put("Room3_5",tex.Room3_5);
                game.getRooms().put("Room3_6",tex.Room3_6);
                game.getRooms().put("Room3_7",tex.Room3_7);
                game.getRooms().put("Room3_8",tex.Room3_8);
                game.getRooms().put("Room3_9",tex.Room3_9);
                game.getRooms().put("Room4_1",tex.Room4_1);
                game.getRooms().put("Room4_2",tex.Room4_2);
                game.getRooms().put("Room4_3",tex.Room4_3);
                game.getRooms().put("Room4_4",tex.Room4_4);
                game.getRooms().put("Room4_5",tex.Room4_5);
                game.getRooms().put("Room4_6",tex.Room4_6);
                game.getRooms().put("Room4_7",tex.Room4_7);
                game.getRooms().put("Room5_1",tex.Room5_1);
                game.getRooms().put("Room5_2",tex.Room5_2);
                game.getRooms().put("Room5_3",tex.Room5_3);
                game.getRooms().put("Room5_4",tex.Room5_4);
                game.getRooms().put("Room5_5",tex.Room5_5);
                game.getRooms().put("Room5_6",tex.Room5_6);
                game.getRooms().put("Room0_1",tex.Room0_1);
                game.getRooms().put("Room1_2",tex.Room1_2);
                game.getRooms().put("Room1_3",tex.Room1_3);
                game.getRooms().put("Room1_4",tex.Room1_4);
                image = game.getRooms().get(game.getCurrentRoom());
            }

            int w = image.getWidth();
            int h = image.getHeight();

            //int xStart = (int) Math.max(0,game.getCamera().getX() / 64);
            //int xEnd = (int) Math.min(w*64, (game.getCamera().getX() + game.WIDTH / 65));
            //int yStart = (int) Math.max(0, game.getCamera().getY() / 64);
            //int yEnd = (int) Math.min(h*64, (game.getCamera().getY()+game.HEIGHT / 65));
            int xStart=0;
            int xEnd=w;
            int yStart=0;
            int yEnd=h;
            Textures tex=Game.getInstance();
            if(game.getCurrentBattle()==null){
                for(int xx = xStart; xx < xEnd; xx++){
                    for(int yy = yStart; yy < yEnd; yy++) {
                        int pixel = image.getRGB(xx, yy);
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = (pixel) & 0xff;
                        if (red == 255&&green==0&&blue==0){
                            g.setColor(Color.gray);
                            g.fillRect((xx * 64)-64, (yy * 64)-64, 64, 64);
                            //addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, this, game, ID.GrayGround));
                        }
                        else if (green == 255&&red==0&&blue==0) {
                            //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.BlackGround));
                        }else if(blue==255&&red==0&&green==0){
                            g.drawImage(tex.Pavement[0],(xx * 64)-64, (yy * 64)-64, 64, 64,null);
                            //addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, this, game, ID.Pavement));
                        }else if(red==57&&green==255&&blue==50){
                            // g.drawImage();
                            g.drawImage(tex.Grass,(xx * 64)-64, (yy * 64)-64, 64, 64,null);
                            // addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, this, game, ID.Grass));
                            // grass
                        }else if(red==255&&green==230&&blue==0){

                        }else if(red==255&&green==111&&blue==0){
                            //handler.addObject(new Block((xx * 64)-64, (yy * 64)-64, 64, 64, handler, this, ID.RedGround));
                            g.drawImage(tex.RedGround, (xx * 64)-64, (yy * 64)-64, 64, 64,null);
                        }else if(red==0 && green==68 && blue==69)
                            g.drawImage(tex.dirt, (xx * 64)-64, (yy * 64)-64, 64, 64,null);

                    }
                }
            }




        }






        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            try{
                if(tempObject.isVisible){
                    tempObject.render(g);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }


    public void addObject(GameObject object) {
        this.object.add(object);
    }


    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void removeObject(int i) {
        this.object.remove(i);
    }




    public void clear() {
        int size = this.object.size();
        for(int i = 0; i < size; i++) {
            this.object.remove(0);
        }

    }

    public void roomClear(){
        for(int i = 0; i<this.object.size(); i++){
            if(!(this.object.get(i).getId()==ID.Player)){
                this.object.remove(i);
                roomClear();
            }
        }
    }

    public boolean isIn(){
        boolean tempBool=false;
        for(int i = 0; i<this.object.size(); i++){
            if(this.object.get(i).getId()==ID.EnemyAttackItem){
                tempBool=true;
            }
        }
        return tempBool;
    }

    public ArrayList<EnemyAttackItem> getELIST(){
        ArrayList<EnemyAttackItem> templist = new ArrayList<>();
        for(int i =0; i<this.object.size(); i++){
            if(this.object.get(i).getId()==ID.EnemyAttackItem){
                templist.add((EnemyAttackItem) this.object.get(i));
            }


        }
        return templist;

    }

    public void removeEAI(){
        for(int i=0; i<this.object.size(); i++){
            if(this.object.get(i).getId()==ID.EnemyAttackItem){
                this.object.remove(i);
                removeEAI();
            }
        }
    }

    public void clearEnemys() {
        for(int i = 0; i < this.object.size(); i++) {
            GameObject temp = this.object.get(i);
            if(temp.getId() == ID.BasicEnemy || temp.getId() == ID.EnemyBoss || temp.getId() == ID.SmartEnemy || temp.getId() == ID.EndEnemy) {
                this.object.remove(i);
                clearEnemys();
            }
        }
    }


    public boolean detectEnemies() {
        for(int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            if(temp.getId() == ID.BasicEnemy || temp.getId() == ID.EndEnemy || temp.getId() == ID.EnemyBoss || temp.getId() == ID.SmartEnemy) {
                return true;
            }
        }
        return false;
    }

    public int getEnemyAMT() {
        int amt = 0;
        for(int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            if(temp.getId() == ID.BasicEnemy || temp.getId() == ID.EndEnemy || temp.getId() == ID.EnemyBoss || temp.getId() == ID.SmartEnemy) {
                amt++;
            }
        }
        return amt;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
