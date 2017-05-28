package edu.chs.entrep.service.image;

import javafx.scene.image.Image;

/**
 * Created by josefinesvegborn on 2017-05-18.
 * This interface sets rules for how to access images for the game.
 */
public interface IImageService {
    public Image getImage(String name);
    public Image getImage(String name, int level);
}
