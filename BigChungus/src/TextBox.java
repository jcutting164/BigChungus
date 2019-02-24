import java.awt.*;
import java.awt.image.BufferedImage;

// 97 characters can fit per line of text


public class TextBox{

    private Character character;
    private String string;
    private BufferedImage face;
    private String text;
    private Graphics g;
    private String[] segments;
    private int lines;
    private int characters;
    private String currentString;
    private int currentLine;
    private Font fnt;
    private int textLength;
    private String flag;
    private boolean done;


    public TextBox(Character character, String text) {

        this.character=character;
        this.text = text;
        this.textLength = text.length();
        this.g = g;
        this.characters = text.length();
        if(characters % 85 > 0){
            lines = (characters / 85) + 1;
        }else{
            lines = characters / 85;
        }

        System.out.println(lines);
        segments = new String[lines];
        System.out.println(segments);

        for(int i = 1; i < lines+1; i++){
            if(textLength >= 85){
                segments[i-1] = text.substring((i*85)-85, i*85);
                System.out.println(segments[i-1]);
                textLength -= 85;
            }else{
                segments[i-1] = text.substring((i*85)-85, (i*85)-85+textLength);
            }

        }

        fnt = new Font("Serif", 0, 25);

        this.currentString = "";
        this.flag = "INIT CLAUSE";



    }






    public void tick(){

        if(!flag.equals("END CLAUSE")){
            if(flag.equals("INIT CLAUSE")){
                currentLine = 0;
            }
            if(currentString.length() == textLength){

                currentLine++;
                currentString = "";
                if(currentLine == lines){
                    flag = "END CLAUSE";
                    done = true;
                }
            }else{
                currentString = currentString + (segments[currentLine].charAt(currentString.length()));
            }
        }




    }
    public void render(Graphics g){
        g.setFont(fnt);
        g.setColor(Color.black);
        g.fillRect(128, 800, 1024, 128);
        g.setColor(Color.white);
        g.drawRect(128, 800, 1024, 128);
        g.drawRect(128, 800, 128, 128);

        g.drawRect(127, 799, 1026, 130);
        g.drawRect(127, 799, 130, 130);
        if(this.character.getId()==ID.Knuckles){
            g.drawImage(this.character.Face, 127, 799, null);
        }else if(this.character.getId()==ID.Pikachu){
            g.drawImage(this.character.Face, 127, 799,127, 122, null);
        }else if(this.character.getId()==ID.BigChungus){
            g.drawImage(this.character.Face, 130, 800,135, 140, null);
        }




        g.setColor(Color.white);
        if(currentLine==0){
            g.drawString(currentString, 275, 835);
        }else if(currentLine == 1){
            g.drawString(currentString, 275, 865);
            g.drawString(this.segments[currentLine-1], 275, 835);
        }else if(currentLine == 2){
            g.drawString(currentString, 275, 895);
            g.drawString(this.segments[currentLine-1], 275, 865);
            g.drawString(this.segments[currentLine-2], 275, 835);
        }else if(currentLine == 3){
            g.drawString(this.segments[currentLine-1], 275, 895);
            g.drawString(this.segments[currentLine-2], 275, 865);
            g.drawString(this.segments[currentLine-3], 275, 835);
        }



    }
    public Rectangle getBounds(){
        return new Rectangle(0, 0, 0, 0);
    }



    public boolean getDone(){
        return done;
    }





}
