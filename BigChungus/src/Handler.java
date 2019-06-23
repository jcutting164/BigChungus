import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler implements Serializable {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public Handler(){

    }

    public void tick() {
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();


        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if(tempObject.isVisible){
                tempObject.render(g);
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



}
