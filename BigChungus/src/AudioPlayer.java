

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




            soundMap.put("GameOver", new Sound("res/MiscMusic/GameOver.wav"));



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
