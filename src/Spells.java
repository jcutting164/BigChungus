import java.io.Serializable;

public class Spells implements Serializable {

    private String name;
    private String desc;
    private boolean usable;
    private Magic magic;
    public Spells(String name, String desc, Magic magic){
        this.name = name;
        this.desc = desc;
        this.magic = magic;
    }


    public void use(){
        if(name.equals("Basic Heal Spell")){
            System.out.println("YAY");
        }
    }


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public boolean getUsable(){
        return usable;
    }
    public void setUsable(boolean usable){
        this.usable = usable;
    }

    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }


}
