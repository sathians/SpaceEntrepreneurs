package edu.chs.entrep.model;

import javafx.scene.image.Image;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Cover {

    private final String coverImg = "img/Firewall_a0.png";
    private Image cover1;
    public Cover (){

        this.cover1 = new Image(coverImg,70, 50, true, true );

        // Image cover2 = new Image(coverImg,70, 50, true, true );
        //Image cover3 = new Image(coverImg,70, 50, true, true );
    }

    public Image getCover(){
        return cover1;
    }

}
