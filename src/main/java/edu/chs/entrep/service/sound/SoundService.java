package edu.chs.entrep.service.sound;

import edu.chs.entrep.Main;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.*;

/**
 * Created by Sathian on 2017-05-04.
 */
class SoundService implements ISoundService {

    Map<String, File> nameSound = new HashMap<>();

    public SoundService() {
        nameSound.put("background", new File("src/main/resources/sounds/space.wav"));
        nameSound.put("shoot", new File("src/main/resources/sounds/shoot.wav"));
        nameSound.put("monsterKill", new File("src/main/resources/sounds/hit.wav"));
        nameSound.put("nextLevel", new File("src/main/resources/sounds/level.wav"));
        nameSound.put("spaceShipHit", new File("src/main/resources/sounds/gothit.wav"));
        nameSound.put("spaceShipHit", new File("src/main/resources/sounds/gothit.wav"));
        nameSound.put("gameOver", new File("src/main/resources/sounds/gameOver.wav"));
    }

    @Override
    public void playSound(String name) {

        try {
            File file = nameSound.get(name);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void repeatSound(String name, boolean start) {
        try {
            File file = nameSound.get(name);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));

            if(start) {
                clip.loop(100);
            }

            else if(!start) {
                clip.stop();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}







