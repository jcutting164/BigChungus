import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;



public class TBHandler implements Serializable {
    private Player player;

    LinkedList<TextBox> object = new LinkedList<TextBox>();

    public TBHandler(){
        this.player = player;
    }

    public void tick() {
        for(int i = 0; i < object.size(); i++) {
            TextBox tempObject = object.get(i);

            tempObject.tick();

        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < object.size(); i++) {
            TextBox tempObject = object.get(i);

            tempObject.render(g);

        }
    }


    public void addObject(TextBox object) {
        this.object.add(object);
    }

    public void removeObject(TextBox object) {
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



}
