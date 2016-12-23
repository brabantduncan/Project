package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.follower.FollowerManager;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

/**
 * Created by Duncan 20/12/2016
 */
public interface EnemyInterface {
    Body getBody();
    float pointGenerator(int min, int max);
    void updatePosition(Vector2 playerPosition);
    void setUserData();
    Texture getTexture();
    void action(ArrayList<Player> players, BulletManager bulletManager, FollowerManager followerManager);
    float getY();
    float getX();
}
