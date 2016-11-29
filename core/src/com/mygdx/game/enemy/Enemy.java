package com.mygdx.game.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Player;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

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


    private Body body;



    public Enemy(Body body) {

        //uitlezen uit database
        this.body = body;
        /**
        this.name = name;
        this.type = type;
        this.health = health;
        this.movementSpeed = movementspeed;
        this.spawnTime = spawnTime;
        **/

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


    public void spawn(){

    }

    public void updatePosition(Vector2 playerPosition){

        int horizontalForce = 0;
        int verticalForce = 0;


        float playerX = playerPosition.x;
        float playerY = playerPosition.y;

        float enemyX = body.getPosition().x;
        float enemyY = body.getPosition().y;

        if( enemyX < playerX){

            horizontalForce += 10;
        }
        if(enemyX > playerX) {
            horizontalForce -= 10;
        }
        if(enemyY < playerY){
            verticalForce +=10;
        }
        if(enemyY > playerY){
            verticalForce -=10;
        }

        body.setLinearVelocity(horizontalForce * 5, verticalForce * 5);
        System.out.print("Player is nu op " + playerX+" en "+ playerY+"\n");
        System.out.print("Enemy is nu op " + x+" en "+ y +"\n");
    }

    public float getX(){ return x;}
    public float getY(){ return y;}






}


