package edu.chs.entrep.service.image;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by josefinesvegborn on 2017-05-09.
 * This class manages the the image files and keeps them mapped with relevant keyword.
 */
class ImageService implements IImageService {

    private Map<String, Image> nameImage = new HashMap<>();

    public ImageService() {

        //Add gamebackgound images
        nameImage.put("background1", new Image("img/background.png", 512, 527, false, true));
        nameImage.put("background2", new Image("img/background2.png", 512, 527, false, true));
        nameImage.put("background3", new Image("img/background3.png", 512, 527, false, true));

        //Add monster images
        nameImage.put("monster1", new Image("img/monster1.png", 40, 40, false, true));
        nameImage.put("monster2", new Image("img/monster2.png", 40, 40, false, true));
        nameImage.put("monster3", new Image("img/monster3.png", 40, 40, false, true));

        //Add cover images
        nameImage.put("cover1", new Image("img/firewall.png", 80, 35, false, true));
        nameImage.put("cover2", new Image("img/firewall2.png", 80, 35, false, true));
        nameImage.put("cover3", new Image("img/firewall.png", 80, 35, false, true));

        //Add missile image
        nameImage.put("missile", new Image("img/missile.png", 20, 20, false, true));

        //Add spaceship image
        nameImage.put("spaceship", new Image("img/spaceship.png", 50, 50, false, true));
        nameImage.put("spaceshipHit", new Image("img/hitSpaceship.png", 50, 50, false, true));

        //Add Game Over image
        nameImage.put("gameOver", new Image("img/GameOver1.png", 512, 527, false, true));

        //Add Level Cleared image
        nameImage.put("levelCleared", new Image("img/levelCleared1.png", 512, 527, false, true));

        //Add life image
        nameImage.put("life", new Image("img/life.png", 20, 20, false, true));
    }

    @Override
    public Image getImage(String name) {
        return nameImage.get(name);
    }

    @Override
    public Image getImage(String name, int level) {
        return nameImage.get(name+level);
    }
}

