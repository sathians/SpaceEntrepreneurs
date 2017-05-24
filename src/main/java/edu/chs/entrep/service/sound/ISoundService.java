package edu.chs.entrep.service.sound;

/**
 * Created by josefinesvegborn on 2017-05-24.
 */
public interface ISoundService {
    public void playSound(String name);
    public void repeatSound(String name, boolean start);
}
