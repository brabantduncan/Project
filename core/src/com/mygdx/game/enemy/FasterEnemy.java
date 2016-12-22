package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.Random;

/**
 * Created by Duncan on 21/12/2016.
 */

public class FasterEnemy implements EnemyInterface {

    private float x;
    private float y;

    private Body body;

    private final int MOVEMENT_SPEED = 14;

    private Texture texture = new Texture("../assets/Monsters/kakuna.gif");

    public FasterEnemy(Body body) {
        //uitlezen uit database
        this.body = body;

        x = pointGenerator(250,500); // should be game.WIDTH / 2;
        y = pointGenerator(200, 400);

        setUserData();
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public float pointGenerator(int min, int max){
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }



    @Override
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

    @Override
    public float getX(){ return x;}

    @Override
    public float getY(){ return y;}

    @Override
    public int randomMovement(int origineel){
        Random rand = new Random();
        return rand.nextInt(origineel+10);
    }

    @Override
    public void setUserData(){
        body.setUserData(this);
    }

    @Override
    public Texture getTexture(){
        return texture;
    }

}