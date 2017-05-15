package edu.chs.entrep.model;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Player {
    //This class should hold name, score, etc.
    private final String name;
    private int score;
    private int life;

    public Player (String name){

        this.name=name;
        this.score = 0;
        this.life=3;
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
