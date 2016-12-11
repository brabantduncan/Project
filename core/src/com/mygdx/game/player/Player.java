package com.mygdx.game.player;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.characterClass.AdventurerHandler;
import com.mygdx.game.characterClass.CharacterHandler;

/**
 * Created by Shan on 11/7/2016.
 */
public class Player {

    private Body playerBody;

    private String playerName;

    //worden beinvloed door de charachterclass
    private String characterClass;
    private CharacterHandler handler;

    // vieze manier:
    // int soort;   en steek er 1 2 of 3 in

    // beter;

    private int health;
    private int attackSpeed;
    private int movenentSpeed;


    private int currentLevel;
    private int currentEXP;



    private int currentScore;
    private int highScore;

    private float x;
    private float y;
    private Texture tex = new Texture("../assets/Monsters/alteroit.png");
    private Sprite sprite = new Sprite(tex, 0, 0, 48, 45);


    public boolean isDead() {
        return dead;
    }

    private boolean dead;


    public Player(Body playerBody, String playerName, int currentLevel, int currentEXP, String characterClass, int currentScore, int highScore) {

        //Voor uit database halen

        this.playerBody = playerBody;
        this.playerName = playerName;
        this.currentLevel = currentLevel;
        this.currentEXP = currentEXP;
        this.characterClass = characterClass;
        this.currentScore = currentScore;
        this.highScore = highScore;
        setUserData();
        dead = false;
    }

    public Player(Body playerBody, String playerName) {

        //Default Player

        this.playerBody = playerBody;
        this.playerName = playerName;
        currentLevel = 1;
        currentEXP = 0;

        characterClass = "Adventurer";

        handler= new AdventurerHandler();
        addBonus();

        currentScore = 0;
        highScore = 0;
        setUserData();



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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getMovenentSpeed() {
        return movenentSpeed;
    }

    public void setMovenentSpeed(int movenentSpeed) {
        this.movenentSpeed = movenentSpeed;
    }


    public void addBonus(){

        handler.addBonus(this);

    }

    public void changeClass(CharacterHandler newHandler){
        handler = newHandler;
        addBonus();
    }

    public void update(float dt) {

    }

    public void alterPosX(float x){
        this.x += x;
    }
    public void alterPosY(float y){
        this.y += y;
    }

    public float getY(){
        return y;
    }

    public float getX(){
        return x;
    }

    public void setUserData(){
        playerBody.setUserData(this);
    }

    public void damage(int enemyDamage){
        health -= enemyDamage;
        if (health == 0){
            dead = true;
            kill();
        }
    }

    public void kill(){
        System.out.print("Player died");
    }
    public Sprite getSprite(){
        return sprite;
    }
}

