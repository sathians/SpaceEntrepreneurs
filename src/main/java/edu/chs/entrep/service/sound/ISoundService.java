package edu.chs.entrep.service.sound;

/**
 * Created by josefinesvegborn on 2017-05-24.
 * This interface sets rules for how to access sound for the game.
 */
public interface ISoundService {
    public void playSound(String name);
    public void repeatSound(String name, boolean start);
}
