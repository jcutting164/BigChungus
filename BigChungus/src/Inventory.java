import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;


public class Inventory implements Serializable {

    private boolean isOpen;
    private Font fnt;
    private boolean[] options;
    int page;
    int currentOption;
    private Game game;

    ArrayList<Items> inv = new ArrayList<>();

    public Inventory(Game game){
        this.options = new boolean[20];
        for(int i = 0; i<20; i++){
            options[i] = false;
        }
        options[0] = true;
        page=1;
        currentOption=0;
        this.game=game;
    }



    public void addItem(Items temp){
        inv.add(temp);
    }

    public void removeItem(Items temp){
        inv.remove(temp);
    }
    public void removeItem(int i){
        inv.remove(i);
    }


    public void tick(){
        if(currentOption>=0 && currentOption<4){
            page=1;
        }else if(currentOption>=4 && currentOption<8){
            page=2;
        }else if(currentOption>=8 && currentOption<12){
            page=3;
        }else if(currentOption>=12 && currentOption<16){
            page=4;
        }else if(currentOption>=16 && currentOption<20){
            page=5;
        }

    }
    public void render(Graphics g){


        g.setColor(Color.white);
        g.drawRect(10, 650, 256, 256);
        g.drawRect(9, 649, 258, 258);
        g.setColor(Color.black);
        g.fillRect(11, 651, 255, 255);

        fnt = new Font("Serif", 1, 25);
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Inventory:", 18, 680);
        int j=0;
        if(page==1){
            for(int i = 0; i<4; i++){
                try{
                    g.drawString(inv.get(i).getName(), 35, 680+(45*(j+1)));
                    if(currentOption==i){
                        g.setColor(Color.white);
                        g.fillRect(20,668+(45*(j+1)), 8, 8);
                    }
                }catch(Exception e){
                }



                j++;
            }

        }else if(page==2){
            for(int i = 4; i<8; i++){
                try{

                    g.drawString(inv.get(i).getName(), 35, 680+(45*(j+1)));
                    if(currentOption==i){
                        g.setColor(Color.white);
                        g.fillRect(20, 668+(45*(j+1)), 8, 8);
                    }
                }catch(Exception e){

                }

                j++;
            }

        }else if(page==3){
            for(int i = 8; i<12; i++){
                try{

                    g.drawString(inv.get(i).getName(), 35, 680+(45*(j+1)));
                    if(currentOption==i){
                        g.setColor(Color.white);
                        g.fillRect(20,668+(45*(j+1)), 8, 8);
                    }
                }catch (Exception e){

                }

                j++;
            }

        }else if(page==4){
            for(int i = 12; i<16; i++){
                try{

                    g.drawString(inv.get(i).getName(), 35, 680+(45*(j+1)));
                    if(currentOption==i){
                        g.setColor(Color.white);
                        g.fillRect(20,668+(45*(j+1)), 8, 8);
                    }
                }catch(Exception e){

                }

                j++;
            }



        }else if(page==5){
            for(int i = 16; i<20; i++){
                try{
                    g.drawString(inv.get(i).getName(), 35, 680+(45*(j+1)));
                    if(currentOption==i){
                        g.setColor(Color.white);
                        g.fillRect(20,668+(45*(j+1)), 8, 8);
                    }
                }catch (Exception e){

                }

                j++;
            }
        }
        try{
            if(game.getCurrentBattle()==null){
                g.setColor(Color.white);
                g.drawRect(300, 775, 512, 128);
                g.drawRect(299, 774, 514, 130);
                g.setColor(Color.black);
                g.fillRect(301, 776, 511, 127);
                g.setColor(Color.white);
                g.drawString("Current Weapon:",325,815);
                g.drawString("Current Armor:",325,865);
                if(!(game.getPlayer().getWeapon()==null))
                    g.drawString(game.getPlayer().getWeapon().getName(), 550,815);
                if(!(game.getPlayer().getArmor()==null))
                    g.drawString(game.getPlayer().getArmor().getName(), 550,865);
            }
        }catch (Exception e){

        }
        try{

            fnt = new Font("Serif", 1, 12);
            g.setFont(fnt);
            g.drawString(page+" / 5", 230, 900);

            g.drawRect(300, 650, 512, 128);
            g.drawRect(299, 649, 514, 130);
            g.setColor(Color.black);
            g.fillRect(301, 651, 511, 127);
            g.setColor(Color.white);
            fnt = new Font("Serif", 1, 25);
            g.setFont(fnt);
            g.drawString("Desc:", 318, 680);
            if(currentOption>=0 && currentOption<inv.size()){
                g.drawString(inv.get(currentOption).getDesc(), 328, 720);
            }else{
                currentOption--;
                resetOptions();
                //setSpecOption(true, currentOption);
            }
            if(!(inv.get(currentOption).getDesc2().equals(""))){
                g.drawString(inv.get(currentOption).getDesc2(), 328, 750);

            }






        }catch (Exception e){

        }




    }

    public boolean getOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean[] getOptions(){
        return options;
    }

    public void setOptions(boolean... args){
        this.options = args;
    }
    public void resetOptions(){
        boolean[] temp = new boolean[inv.size()];
        for(int i = 0; i<inv.size()-1; i++){
            temp[i]=false;
        }
        temp[inv.size()-1]=true;
        this.options=temp;
    }

    public void setSpecOption(boolean option, int index){
        this.options[index] = option;
    }

    public void setPage(int page){
        this.page = page;
    }
    public int getPage(){
        return page;
    }

    public void setCurrentOption(int currentOption){
        this.currentOption = currentOption;
    }
    public int getCurrentOption(){
        return currentOption;
    }


    public void clear(){
        int size = inv.size();
        for(int i = 0; i<size; i++){
            inv.remove(inv.get(0));
        }
    }













}
