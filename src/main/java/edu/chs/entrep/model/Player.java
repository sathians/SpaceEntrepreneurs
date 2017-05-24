package edu.chs.entrep.model;

/**
 * Created by josefinesvegborn on 2017-04-03.
 * This class represent the player (not the character) and holds name, life and score.
 */

public class Player {

    private final String name;
    private int score;
    private int life;

    private final int MAX_LIFE = 3;

    public Player (String name){

        this.name = name;
        this.score = 0;
        this.life = MAX_LIFE;
    }


    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
    public void updateScore (int x){
        score = score + x;
    }

    public void decLife(){
        life -= 1;
    }

    public int getLife(){
        return life;
    }
}
