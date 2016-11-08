package com.mygdx.game;


import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Shan on 11/7/2016.
 */
public class Player {

    private Body playerBody;

    private String playerName;

    private int currentLevel;
    private int currentEXP;

    private String characterClass;

    private int currentScore;
    private int highScore;


    public Player(Body playerBody, String playerName, int currentLevel, int currentEXP, String characterClass, int currentScore, int highScore) {

        //Voor uit database halen

        this.playerBody = playerBody;
        this.playerName = playerName;
        this.currentLevel = currentLevel;
        this.currentEXP = currentEXP;
        this.characterClass = characterClass;
        this.currentScore = currentScore;
        this.highScore = highScore;
    }

    public Player(Body playerBody, String playerName) {

        //Default Player

        this.playerBody = playerBody;
        this.playerName = playerName;
        currentLevel = 1;
        currentEXP = 0;

        characterClass = "Adventurer";

        currentScore = 0;
        highScore = 0;

        


    }


    public Body getPlayerBody() {
        return playerBody;
    }

    public String getPlayerName() {
        return playerName;
    }



    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getCurrentEXP() {
        return currentEXP;
    }

    public void setCurrentEXP(int extraEXP) {

        int expBeforeLevelUp = neededExpCalc();
        if((expBeforeLevelUp <= currentEXP + extraEXP)){
            levelUp();

            int remainingExp = (currentEXP + extraEXP) - expBeforeLevelUp;

            setCurrentEXP(remainingExp);

        }
        else {

            currentEXP = 0;
            currentEXP = extraEXP;
        }



    }

    public void levelUp() {
        currentLevel = currentLevel +1;


    }

    public int neededExpCalc(){

        return currentLevel * 100 ;

    }



    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }



    public int getCurrentScore() {
        return currentScore;
    }

    public void increaseCurrentScore(int score) {
        currentScore += score;
        if(highScore < currentScore)
            setHighScore();
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore() {

            highScore = currentScore;
    }


    @Override
    public  String toString(){
        return "Player "+ playerName + " heeft de klasse "+ characterClass
                +". Zijn huidig level is "+ currentLevel+ " en heeft al "+ currentEXP+ " van de "
                + neededExpCalc()+ ", zijn highscore is " + highScore;
    }

}
