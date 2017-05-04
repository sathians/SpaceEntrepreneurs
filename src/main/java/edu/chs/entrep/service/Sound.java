package edu.chs.entrep.service;

import javafx.scene.media.AudioClip;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by Sathian on 2017-05-04.
 */
public class Sound {

    public void shootSound() {

        try {
            File file = new File("C:\\Users\\Sathian\\IdeaProjects\\SpaceEntrepreneurs\\src\\main\\resources\\sounds\\shoot.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            // Thread.sleep(clip.getMicrosecondLength());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
