

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
