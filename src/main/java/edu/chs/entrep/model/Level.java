package edu.chs.entrep.model;

/**
 * Created by Sathian on 2017-04-27.
 */
public class Level extends Monster{

    Character spaceship;

    public Level (int x) {


        int level = x;

        switch (level) {
            case 1:
                initMonsterList(15);
                spaceship = new Character();

                Cover cover1 = new Cover();
                Cover cover2 = new Cover();
                Cover cover3 = new Cover();

                cover1.setPosition(75, 400);
                cover2.setPosition(225, 400);
                cover3.setPosition(375, 400);

            case 2:
                initMonsterList(20);
                spaceship = new Character();

                Cover cover4 = new Cover();
                Cover cover5 = new Cover();

                cover4.setPosition(75,400);
                cover5.setPosition(375,400);

            case 3:
                initMonsterList(30);
                spaceship = new Character();

                Cover cover6 = new Cover();

                cover6.setPosition(225,400);

        }
    }
        public Character getSpaceship(){
            return spaceship;
        }
    }


