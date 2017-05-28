package edu.chs.entrep.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
import java.util.ArrayList;

/**
 * Created by josefinesvegborn on 2017-05-04.
 * This class manages the highscore file by reading from and writing to a textfile
 */
public class Highscore {

    private File file;
    private final int LIST_LENGTH = 4;
    private String[][] highscoreList;


    public Highscore(File file) {
        this.file = file;
        highscoreList = new String[LIST_LENGTH][2];
        highscoreList = fileToList();
    }

    /**
     * Reads the highscore from the textfile using fileToList and returns string
     * @return A string with the Score
     */

    public String readHighscore() {
        highscoreList = fileToList();
        String highscoreString =  "";

        for(int i = 0; i < highscoreList.length; i++) {
            highscoreString = highscoreString + highscoreList[i][0] + " " + highscoreList[i][1] + "\n";
        }

        return highscoreString;
    }

    /**
     *
     * @return
     */

    public String[][] getHighscoreList() {
        return highscoreList;
    }

    /**
     * Writes to textfile for highscore
     * @param name
     * @param score
     * @param position - i higscorelistan
     */
    public void writeHighscore(String name, int score, int position) {

        String highscoreString = "";

        //Push the others down the list
        for(int i = (highscoreList.length-1); i > position; i--) {
            highscoreList[i][0] = highscoreList[i-1][0]; //push name downwards
            highscoreList[i][1] = highscoreList[i-1][1]; //push score downwards
        }

        //Adds new person
        highscoreList[position][0] = name;
        highscoreList[position][1] = String.valueOf(score);

        //Makes a String out of the higscorelist
        for(String[] item:highscoreList) {
            highscoreString = highscoreString + item[0] + " " + item[1] + "/";
        }

        //Writes the new highscore string to the textfile
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(highscoreString);
            writer.flush();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private support method for reading from the file and making a list.
     * @return a list containing names and string
     */

    private String[][] fileToList() {

        //Makes sure the matrix is filled with empty strings
        for(String[] item:highscoreList) {
            item[0] = "";
            item[1] = "";
        }

        try {
            FileReader reader = new FileReader(file);
            int character;
            ArrayList<java.lang.Character> charList = new ArrayList<>();

            while ((character = reader.read()) != -1) {
                charList.add((char) character);
            }
            reader.close();

            int i = 0;
            int j = 0;
            for(char c:charList){
                if(c == '/') {
                    i++; //If there's a slash, it moves to next list item.
                    j=0; //Goes back to name
                }
                else if(c == ' ') {
                    j++; //Goes to score instead of name
                }
                else {
                    highscoreList[i][j] = highscoreList[i][j] + c; //Builds up the name or score from the carachters
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return highscoreList;
    }
}
