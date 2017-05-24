package edu.chs.entrep.service.sound;

/**
 * Created by josefinesvegborn on 2017-05-24.
 */
public class SoundFactory {
    public static ISoundService getSoundService() {
        return new SoundService();
    }
}
