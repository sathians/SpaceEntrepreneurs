package edu.chs.entrep.service.image;

/**
 * Created by josefinesvegborn on 2017-05-18.
 */
public class ImageFactory {
    public static IImageService getImageService() {
        return new ImageService();
    }
}
