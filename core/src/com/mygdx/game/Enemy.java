package com.mygdx.game;

import java.util.Random;

/**
 * Created by laurens on 8/11/2016.
 */
public class Enemy {


    private String name;

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    private String type;
    private int health;
    private int attackSpeed;
    private int movementSpeed;
    private int spawnTime;
    private float x;
    private float y;



    public Enemy(String name, String type, int health, int movementspeed, int spawnTime) {

        //uitlezen uit database

        this.name = name;
        this.type = type;
        this.health = health;
        this.movementSpeed = movementspeed;
        this.spawnTime = spawnTime;


        x = pointGenerator(250,500); // should be game.WITH / 2;
        y = pointGenerator(200, 400);
    }


    public float pointGenerator(int min, int max){
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public float getX(){ return x;}
    public float getY(){ return y;}






}


