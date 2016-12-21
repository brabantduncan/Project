package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Duncan on 21/12/2016.
 */

public class FasterEnemy implements EnemyInterface {

    private float x;
    private float y;

    private Body body;

    private final int MOVEMENT_SPEED = 10;

    private Texture texture = new Texture("../assets/Monsters/");

    @Override
    public Body getBody() {
        return null;
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
