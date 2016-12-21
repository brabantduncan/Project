package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Duncan on 21/12/2016.
 */

public class FasterEnemy implements EnemyInterface {

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


    private final int MOVEMENT_SPEED = 5;

    private Texture texture = new Texture("../assets/Monsters/weedle.gif");

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public float pointGenerator(int min, int max) {
        return 0;
    }

    @Override
    public void spawn() {

    }

    @Override
    public void updatePosition(Vector2 playerPosition) {

    }

    @Override
    public int randomMovement(int origineel) {
        return 0;
    }

    @Override
    public void setUserData() {
        body.setUserData(this);
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public float getX() {
        return 0;
    }
}
