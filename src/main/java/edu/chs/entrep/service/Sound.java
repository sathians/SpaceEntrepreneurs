package edu.chs.entrep.service;

import edu.chs.entrep.Main;
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
            File file = new File("src/main/resources/sounds/shoot.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();

            //Thread.sleep(clip.getMicrosecondLength()/1000);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void bgdSound() {
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = new File("src/main/resources/sounds/space.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(file));
                    clip.start();
                    Thread.sleep(clip.getMicrosecondLength());


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void nextLevelSound() {

        try {
            File file = new File("src/main/resources/sounds/nextLevel.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();

            //Thread.sleep(clip.getMicrosecondLength()/1000);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}







