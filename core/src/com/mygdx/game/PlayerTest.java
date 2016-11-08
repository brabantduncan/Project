package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;

import static org.junit.Assert.*;

/**
 * Created by Shan on 11/7/2016.
 * Fixed by Duncan on 8/11/2016.
 */
public class PlayerTest {

    Player testPlayer;
    Body body = null;

    int newHighScore;


    @org.junit.Before
    public void setUp() throws Exception {

        testPlayer = new Player(body,"Duncan");
    }

    @org.junit.Test
    public void setHighScore() throws Exception {

        int origneleScore = testPlayer.getHighScore();
        newHighScore = testPlayer.getHighScore() +100;
        // currentscore  is 0

        testPlayer.increaseCurrentScore(newHighScore);


        assertEquals(origneleScore + newHighScore, testPlayer.getHighScore());

    }

    @org.junit.Test
    public void levelUpReset() throws Exception {

        int origineelLevel = testPlayer.getCurrentLevel();


        testPlayer.levelUp();

        assertEquals(origineelLevel +1, testPlayer.getCurrentLevel());

    }

    @org.junit.Test
    public void setEXPTest(){
        int expNeeded = testPlayer.neededExpCalc();

        int bonusScore = 5;
        int origineelLevel = testPlayer.getCurrentLevel();



        testPlayer.setCurrentEXP(expNeeded+bonusScore);

        assertEquals(origineelLevel+1, testPlayer.getCurrentLevel());
        assertEquals(bonusScore, testPlayer.getCurrentEXP());
    }

    @org.junit.Test
    public void testString(){

        System.out.println(testPlayer.toString());

        int expNeeded = testPlayer.neededExpCalc();
        int bonusScore = 5;

        newHighScore = testPlayer.getHighScore() +100;


        testPlayer.increaseCurrentScore(newHighScore);
        testPlayer.setCurrentEXP(expNeeded+bonusScore);



        System.out.println(testPlayer.toString());




    }

}