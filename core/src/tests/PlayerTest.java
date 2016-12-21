package tests;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.player.Player;
import com.mygdx.game.characterClass.CharacterHandler;
import com.mygdx.game.characterClass.WarriorHandler;

import static org.junit.Assert.assertEquals;

/**
 * Created by Shan on 11/7/2016.
 */

/**
public class PlayerTest {

    Player testPlayer;
    Body body = null;

    Player testPlayer2;


    int newHighScore;

    CharacterHandler handler = new WarriorHandler();

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

    @org.junit.Test
    public void addBonus(){

        testPlayer2 = new Player(body,"Stijn");

        assertEquals(100,testPlayer2.getHealth());
        assertEquals(10,testPlayer2.getAttackSpeed());
        assertEquals(10,testPlayer2.getMovenentSpeed());
    }

    @org.junit.Test
    public  void changeClass(){

        testPlayer2 = new Player(body, "Stijn");

        int originalHealth = testPlayer2.getHealth();
        testPlayer2.changeClass(handler);

        assertEquals(originalHealth+20,testPlayer2.getHealth());

        System.out.println(testPlayer2.toString());
    }

}**/