import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    private Player player;

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public Handler(){
        this.player = player;
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

    public boolean isIn(){
        boolean tempBool=false;
        for(int i = 0; i<this.object.size(); i++){
            if(this.object.get(i).getId()==ID.EnemyAttackItem){
                tempBool=true;
            }
        }
        return tempBool;
    }



}
