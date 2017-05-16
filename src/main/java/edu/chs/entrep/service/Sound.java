package edu.chs.entrep.service;

import edu.chs.entrep.Main;
import javafx.scene.media.AudioClip;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;
import java.io.File;

import static java.lang.Thread.*;

/**
 * Created by Sathian on 2017-05-04.
 */
public class Sound{


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

    public void monsterKillSound() {

        try {
            File file = new File("src/main/resources/sounds/hit.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();

            //Thread.sleep(clip.getMicrosecondLength()/1000);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void nextLevelSound() {

        try {
            File file = new File("src/main/resources/sounds/level.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
           // Thread.sleep(600);
            clip.start();

           // sleep(clip.getMicrosecondLength()/1000);



        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void hitSound() {

        try {
            File file = new File("src/main/resources/sounds/gothit.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            // Thread.sleep(600);
            clip.start();

            // sleep(clip.getMicrosecondLength()/1000);



        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void gameoverSound() {

        try {
            File file = new File("src/main/resources/sounds/gameOver.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            // Thread.sleep(600);
            clip.start();

            // sleep(clip.getMicrosecondLength()/1000);



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
                    sleep(clip.getMicrosecondLength());

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
        try {
            sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}







