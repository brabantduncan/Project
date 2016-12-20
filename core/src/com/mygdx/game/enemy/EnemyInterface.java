package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Duncan 20/12/2016
 */
public interface EnemyInterface {

    Body getBody();

    float pointGenerator(int min, int max);

    void spawn();

    void updatePosition(Vector2 playerPosition);

    int randomMovement(int origineel);

    void setUserData();

    Texture getTexture();

    float getY();

    float getX();
}
