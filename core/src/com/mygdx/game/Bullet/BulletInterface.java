package com.mygdx.game.Bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Duncan on 23/12/2016.
 */

public interface BulletInterface {
    void editBody();

    Body getB();

    Texture getTexture();

}
