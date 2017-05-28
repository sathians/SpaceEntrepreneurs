package edu.chs.entrep.model;

import edu.chs.entrep.service.sound.SoundFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by niklasohlsson on 2017-04-27.
 * This class is the initiator and head of the game-logic.
 */
public class SpaceEntrepreneurs {

    private ArrayList<Monster> monsterList;
    private ArrayList<Cover> coverList;
    public Player player;
    public Highscore highscore;

    final private int MAX_LEVEL = 3;
    private int level;
    private int count = 0;

    private Character spaceship;
    private Missile missile;
    private Missile monsterMissile;

    private boolean gameOver;
    private boolean win;

    public SpaceEntrepreneurs(Player player, int level, Highscore highscore) {

        this.player = player;
        this.level = level;
        this.highscore = highscore;

        coverList = new ArrayList<Cover>();
        monsterList = new ArrayList<Monster>();

        spaceship = new Character();
        spaceship.setVelocity(0, 0);

        missile = new Missile();
        missile.setHeight(20);
        missile.setWidth(20);

        monsterMissile = new Missile();
        monsterMissile.setHeight(20);
        monsterMissile.setWidth(20);

        gameOver = false;

        SoundFactory.getSoundService().repeatSound("background", true);

        initLevel(level);
    }

    public void initLevel(int level) {

        switch (level) {
            case 1:
                initMonsterList(3);

                coverList.add(new Cover());
                coverList.add(new Cover());
                coverList.add(new Cover());

                coverList.get(0).setPosition(75, 400);
                coverList.get(1).setPosition(225, 400);
                coverList.get(2).setPosition(375, 400);

                break;

            case 2:
                initMonsterList(4);

                coverList.add(new Cover());
                coverList.add(new Cover());

                coverList.get(0).setPosition(75, 400);
                coverList.get(1).setPosition(375, 400);

                break;

            case 3:
                initMonsterList(5);

                coverList.add(new Cover());

                coverList.get(0).setPosition(225, 400);

                break;
        }
    }

    public void initMonsterList(int rows) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 6; j++) {
                Monster monster = new Monster();

                monster.setPosition(30 + j * 82, 30 + i * 50);
                monsterList.add(monster);
            }
        }
    }

    public void left() {
        if (spaceship.getPositionX() > 0)
            spaceship.addVelocity(-100, 0);
    }

    public void right() {

        if (spaceship.getPositionX() < 462)
            spaceship.addVelocity(100, 0);
    }

    public void stopSpaceShip() {
        spaceship.setVelocity(0, 0);
    }


    public void shoot() {
        if (!missile.isOnScreen()) {
            missile.setOnScreen(true);
            missile.setPosition(spaceship.getPositionX() + (spaceship.getWidht() / 2) - (missile.getWidht() / 2), spaceship.getPositionY());

            SoundFactory.getSoundService().playSound("shoot");
            missile.setVelocity(0, -400);
        }
    }

    public void monsterShoot() {
        if (!monsterMissile.isOnScreen()) {
            monsterMissile.setOnScreen(true);
            Random random = new Random();
            int index = random.nextInt(monsterList.size());
            monsterMissile.setPosition(monsterList.get(index).getPositionX() + (monsterList.get(index).getWidht() / 2), monsterList.get(index).getPositionY());
            monsterMissile.setVelocity(0, 200);
        }


    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }


    public void moveMonster() {

        double posR = 0;
        double posL = 512;
        //

        for (Monster monster : monsterList) {

            if (posR < monster.getPositionX())
                posR = monster.getPositionX();

            if (posL > monster.getPositionX())
                posL = monster.getPositionX();
        }

        for (Monster monster : monsterList) {

            if (posR < (512 - 40) && monster.getVelocityX() >= 0) {
                monster.setVelocity(25, 0);
                count = 2;
            } else if (posL > 0) {
                monster.setVelocity(-25, 0);
                count = 0;
            } else {
                monster.setVelocity(0, 0);
            }

        }
        if (count == 2 || count == 0) {
            for (Monster monster : monsterList) {
                monster.setPosition(monster.getPositionX(), monster.getPositionY() + 0.05);
            }
        }
    }


    public boolean gameOverCheck() {

        if (player.getLife() == 0 || getMonsterList().get(monsterList.size() - 1).getPositionY() > 360) {
            gameOver = true;

            SoundFactory.getSoundService().playSound("gameOver");
        }
        return gameOver;
    }

    public boolean monsterCheck() {
        if (monsterList.isEmpty()) {
            SoundFactory.getSoundService().playSound("nextLevel");
            win = true;
        }
        return win;
    }


    public boolean collisionCheck() {

        boolean spaceshipHit = false;

        Iterator<Monster> monsterIter = monsterList.iterator();
        while (monsterIter.hasNext()) {
            Monster monster = monsterIter.next();
            if (missile.isOnScreen() && missile.intersects(monster)) {
                monsterIter.remove();
                SoundFactory.getSoundService().playSound("monsterKill");
                missile.setOnScreen(false);
                player.updateScore(100);
            }
        }

        if (monsterMissile.isOnScreen() && monsterMissile.intersects(spaceship)) {
            monsterMissile.setOnScreen(false);
            player.decLife();
            SoundFactory.getSoundService().playSound("spaceShipHit");
            spaceshipHit = true;
        }

        //missile out of bound or intersect with wall
        if (missile.getPositionY() < 0)
            missile.setOnScreen(false);

        if (monsterMissile.getPositionY() > 500)
            monsterMissile.setOnScreen(false);


        //missile intersects with cover

        for (Cover cover : coverList) {
            if (missile.intersects(cover)) {
                missile.setOnScreen(false);
            }
            if (monsterMissile.intersects(cover)) {
                monsterMissile.setOnScreen(false);
            }
        }
        return spaceshipHit;
    }


    //Checks if the score makes it to highscorelist by reading old list and writing new score to it if applicable.
    //returns newHighscore true if the score made it to the list.

    public boolean checkHighscore() {
        String[][] oldHighscores = highscore.getHighscoreList();
        int oldScore;
        int position;
        boolean newHighscore = false;

        //Decides position in the list by comparing to the old scores
        for (int i = 0; i < oldHighscores.length; i++) {
            oldScore = Integer.parseInt(oldHighscores[i][1]);
            if (player.getScore() > oldScore) {
                position = i;
                highscore.writeHighscore(player.getName(), player.getScore(), position);
                newHighscore = true;
                break;
            }
        }
        return newHighscore;
    }

    public boolean finishedGameCheck() {
        boolean finishedGame = false;

        if (monsterList.isEmpty() && level == MAX_LEVEL) {
            finishedGame = true;
        }

        return finishedGame;
    }

    public Character getSpaceship() {
        return spaceship;
    }

    public Missile getMissile() {
        return missile;
    }

    public Missile getMonsterMissile() {
        return monsterMissile;
    }

    public ArrayList<Cover> getCoverList() {
        return coverList;
    }
}

