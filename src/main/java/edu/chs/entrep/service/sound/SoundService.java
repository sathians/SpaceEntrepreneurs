package edu.chs.entrep.service.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Sathian on 2017-05-04.
 * This class manages the the sounds and keeps the sound files mapped with relevant keyword.
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
        nameSound.put("click", new File("src/main/resources/sounds/click.wav"));
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
                clip.loop(clip.LOOP_CONTINUOUSLY);
            }

            else if(!start) {
                clip.stop();
            }

        }    catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}







