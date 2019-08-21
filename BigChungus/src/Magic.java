import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;


public class Magic implements Serializable {

    private boolean isOpen;
    private Font fnt;
    private boolean[] options;
    int page;
    int currentOption;


    ArrayList<Spells> magic = new ArrayList<>();

    public Magic(){
        this.options = new boolean[20];
        for(int i = 0; i<20; i++){
            options[i] = false;
        }
        options[0] = true;
        page=1;
        currentOption=0;
    }



    public void addItem(Spells temp){
        magic.add(temp);
    }

    public void removeItem(Items temp){
        magic.remove(temp);
    }
    public void removeItem(int i){
        magic.remove(i);
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
        g.drawString("Spells:", 18, 680);
        int j=0;
        if(page==1){
            for(int i = 0; i<4; i++){
                try{
                    g.drawString(magic.get(i).getName(), 35, 680+(45*(j+1)));
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

                    g.drawString(magic.get(i).getName(), 35, 680+(45*(j+1)));
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

                    g.drawString(magic.get(i).getName(), 35, 680+(45*(j+1)));
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

                    g.drawString(magic.get(i).getName(), 35, 680+(45*(j+1)));
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
                    g.drawString(magic.get(i).getName(), 35, 680+(37*(j+1)));
                    if(currentOption==i){
                        g.setColor(Color.white);
                        g.fillRect(30,668+(45*(j+1)), 8, 8);
                    }
                }catch (Exception e){

                }

                j++;
            }
        }

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
        if(!magic.isEmpty()){
            g.drawString(magic.get(currentOption).getDesc(), 328, 720);
            if(!(magic.get(currentOption).getDesc2().equals(""))){
                g.drawString(magic.get(currentOption).getDesc2(), 328, 750);

            }

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




}
