package edu.chs.entrep.model;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Player {
    //This class should hold name, score, etc.
    private final String name;
    private int score=0;

    public Player (String name){

        this.name=name;
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
}
