package com.mygdx.game.follower;

import com.badlogic.gdx.Version;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Shan on 12/18/2016.
 */
public interface FollowerInterface {

    public void move(Vector2 playerPosition, Vector2 MouseCoord);
    public void spawn(Body body);
    public void action(Vector2 mouseCoord);
}
