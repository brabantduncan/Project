package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.Random;

/**
 * Created by laurens on 8/11/2016.
 */
public class Enemy implements EnemyInterface{

    private float x;
    private float y;


    public Body getBody() {
        return body;
    }

    private Body body;


    private final int MOVEMENT_SPEED = 4;

    private Texture texture = new Texture("../assets/Monsters/weedle.gif");



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

        x = pointGenerator(250,500); // should be game.WIDTH / 2;
        y = pointGenerator(200, 400);

        setUserData();
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

            horizontalForce += randomMovement(MOVEMENT_SPEED);
        }
        if(enemyX > playerX) {
            horizontalForce -= randomMovement(MOVEMENT_SPEED);
        }
        if(enemyY < playerY){
            verticalForce +=randomMovement(MOVEMENT_SPEED);
        }
        if(enemyY > playerY){
            verticalForce -=randomMovement(MOVEMENT_SPEED);
        }

        body.setLinearVelocity(horizontalForce * 5, verticalForce * 5);

    }

    public float getX(){ return x;}
    public float getY(){ return y;}


    public int randomMovement(int origineel){
        Random rand = new Random();
        return rand.nextInt(origineel+10);
    }


    public void setUserData(){
        body.setUserData(this);
    }

    public Texture getTexture(){
        return texture;
    }

}


