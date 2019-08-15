import java.awt.*;
import java.io.*;

public class SaveObject extends GameObject implements Serializable {



    // player's attribs
    private String name;
    private Inventory inv;
    private Magic magic;
    private int mana, maxMana;
    private int health, maxHealth;
    private Handler handler;
    private transient Animation spin;

    transient Textures tex = Game.getInstance();



    public SaveObject(float x, float y, float height, float width){
        super(x, y, height, width, ID.SaveIcon);
        this.spin = new Animation(5, tex.SaveIcon[0],tex.SaveIcon[1],tex.SaveIcon[2],tex.SaveIcon[3],tex.SaveIcon[4],tex.SaveIcon[5],tex.SaveIcon[6],tex.SaveIcon[7],tex.SaveIcon[8],tex.SaveIcon[9],tex.SaveIcon[10],tex.SaveIcon[11]);
    }

    public void tick() {
        spin.runAnimation();
    }

    public void render(Graphics g){
        spin.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }


    public void interaction(Game game){
        game.getPlayer().setLimited(true);
        game.save();
        game.getTbHandler().addObject(new TextBox(game.getPlayer(), "The game has been saved.",0,0,0,0,ID.TextBox,game.getTbHandler()));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public Magic getMagic() {
        return magic;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public void setHandler(Handler handler){
        this.handler = handler;
    }
    public Handler getHandler(){
        return this.handler;
    }
}
