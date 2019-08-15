

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Sound;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class AudioPlayer implements Serializable {

    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void load() {

        try {
            /*
            musicMap.put("MENU_MUSIC", new Music("res/TITLE.wav"));
            musicMap.put("GAME_MUSIC", new Music("res/GAME.wav"));
            soundMap.put("LASER_SOUND", new Sound("res/LASER.wav"));
            soundMap.put("DEATH_SOUND", new Sound("res/DEATH.wav")); */

            musicMap.put("Knuckles", new Music("res/BattleMusic/Knuckles.wav"));
            musicMap.put("Pikachu", new Music("res/BattleMusic/Pikachu.wav"));
            musicMap.put("BigChungus", new Music("res/BattleMusic/BigChungus.wav"));
            musicMap.put("Malario", new Music("res/BattleMusic/Malario.wav"));
            musicMap.put("TPoser",new Music("res/BattleMusic/Tposer.wav"));
            musicMap.put("JoshuaGiraffe",new Music("res/BattleMusic/JoshuaGiraffe.wav"));
            musicMap.put("FatYoshi",new Music("res/BattleMusic/FatYoshi.wav"));
            musicMap.put("DatBoi",new Music("res/BattleMusic/DatBoi.wav"));
            musicMap.put("Kermit",new Music("res/BattleMusic/Kermit.wav"));
            musicMap.put("BongoCat",new Music("res/BattleMusic/BongoCat.wav"));
            musicMap.put("Crab",new Music("res/BattleMusic/Crab.wav"));
            musicMap.put("AntiHero",new Music("res/BattleMusic/AntiHero.wav"));
            musicMap.put("AntiHeroB",new Music("res/BattleMusic/AntiHeroBack.wav"));
            musicMap.put("Zuck",new Music("res/BattleMusic/Zuck.wav"));
            musicMap.put("Harambe",new Music("res/BattleMusic/Harambe.wav"));
            musicMap.put("YodelBoy",new Music("res/BattleMusic/YodelBoy.wav"));
            musicMap.put("KazooKid",new Music("res/BattleMusic/KazooKid.wav"));
            musicMap.put("Waluigi",new Music("res/BattleMusic/Waluigi.wav"));
            musicMap.put("Spongebob",new Music("res/BattleMusic/Spongebob.wav"));
            musicMap.put("Spaget",new Music("res/BattleMusic/Spaget.wav"));
            musicMap.put("Arthur",new Music("res/BattleMusic/Arthur.wav"));
            musicMap.put("TITLE", new Music("res/MiscMusic/TITLE.wav"));
            musicMap.put("GAME", new Music("res/MiscMusic/GAME.wav"));




            soundMap.put("GameOver", new Sound("res/MiscMusic/GameOver.wav"));
            soundMap.put("LASER_SOUND", new Sound("res/MiscMusic/LASER.wav"));
            soundMap.put("DEATH_SOUND", new Sound("res/MiscMusic/DEATH.wav"));

            soundMap.put("C3", new Sound("res/Piano/C3.wav"));
            soundMap.put("C#3", new Sound("res/Piano/C#3.wav"));
            soundMap.put("D3", new Sound("res/Piano/D3.wav"));
            soundMap.put("D#3", new Sound("res/Piano/D#3.wav"));
            soundMap.put("E3", new Sound("res/Piano/E3.wav"));
            soundMap.put("F3", new Sound("res/Piano/F3.wav"));
            soundMap.put("F#3", new Sound("res/Piano/F#3.wav"));
            soundMap.put("G3", new Sound("res/Piano/G3.wav"));
            soundMap.put("G#3", new Sound("res/Piano/G#3.wav"));
            soundMap.put("A4", new Sound("res/Piano/A4.wav"));

            //
            soundMap.put("A#4", new Sound("res/Piano/A#4.wav"));
            soundMap.put("B4", new Sound("res/Piano/B4.wav"));
            //
            soundMap.put("C4", new Sound("res/Piano/C4.wav"));
            soundMap.put("C#4", new Sound("res/Piano/C#4.wav"));
            soundMap.put("D4", new Sound("res/Piano/D4.wav"));
            soundMap.put("D#4", new Sound("res/Piano/D#4.wav"));
            soundMap.put("E4", new Sound("res/Piano/E4.wav"));





        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static Music getMusic(String key) {
        return musicMap.get(key);
    }

    public static Sound getSound(String key) {
        return soundMap.get(key);
    }


}
