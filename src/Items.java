import java.io.Serializable;

public class Items implements Serializable {

    private String name;
    private String desc;
    private boolean usable;
    private Inventory inv;
    public Items(String name, String desc, Inventory inv){
        this.name = name;
        this.desc = desc;
        this.inv = inv;
    }


    public void use(){
        if(name.equals("Basic HP")){
            System.out.println("YAY");
            inv.removeItem(this);
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
