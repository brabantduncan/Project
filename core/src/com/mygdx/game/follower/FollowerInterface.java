package com.mygdx.game.follower;

import com.badlogic.gdx.Version;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.states.PlayState;

import java.util.ArrayList;

/**
 * Created by Shan on 12/18/2016.
 */
public interface FollowerInterface {

    void action(Vector2 mouseCoord, BulletManager bulletManager);

    Texture getTexture();

    ArrayList<Body> getBody();

    void update(Vector2 position);

    void setData();

    void spawnExtra(Body playerPostion);

    int getTimeExist();

    int getBorn();

    void increaseBorn();

    void updateDrone();

    boolean isUpgraded();

    void upgrade(Boolean upgrade);

}




