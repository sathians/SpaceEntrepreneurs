package edu.chs.entrep.service;

import javafx.scene.image.Image;

/**
 * Created by josefinesvegborn on 2017-05-09.
 */
public class Images {

    Image image;

    public Images() {
    }

    public Image getBackgroundImage(int level) {
        switch (level) {

            case 1:
                image = new Image("img/background.png", 512, 527, false, true);
                break;
            case 2:
                image = new Image("img/background2.png", 512, 527, false, true);
                break;
            case 3:
                image = new Image("img/background.png", 512, 527, false, true);
                break;
            default:
                image = new Image("img/background.png", 512, 527, false, true);
        }
        return image;
    }

    public Image getCoverImage(int level) {
        switch (level) {

            case 1:
                image = new Image("img/firewall.png", 80, 35, false, true);
                break;
            case 2:
                image = new Image("img/firewall2.png", 80, 35, false, true);
                break;
            case 3:
                image = new Image("img/firewall.png", 80, 35, false, true);
                break;
            default:
                image = new Image("img/firewall.png", 80, 35, false, true);
        }
        return image;
    }

    public Image getMonsterImage(int level) {
        switch (level) {

            case 1:
                image = new Image("img/monster.png", 40, 40, false, true);
                break;
            case 2:
                image = new Image("img/monster2.png", 40, 40, false, true);
                break;
            case 3:
                image = new Image("img/monster.png", 40, 40, false, true);
                break;
            default:
                image = new Image("img/monster.png", 40, 40, false, true);
        }
        return image;
    }

    public Image getMissileImage() {

        image = new Image("img/missile.png", 20, 20, false, true);

        return image;
    }

    public Image getSpaceshipImage() {

        image = new Image("img/spaceship.png", 50, 50, false, true);

        return image;
    }

    public Image getGameOverImage() {

        image = new Image("img/gameOver.png", 512, 527, false, true);

        return image;
    }

    public Image getClearedLevelImage() {

        image = new Image("img/levelCleared.png", 512, 527, false, true);

        return image;
    }

    public Image getLifeImage() {

        image = new Image("img/life.png", 20, 20, false, true);

        return image;
    }

}

