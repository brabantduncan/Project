package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.enemy.EnemyInterface;

/**
 * Created by Shan on 12/19/2016.
 */
public class AiControllerHandler {

    public void moveToPlayer(Vector2 playerPosition, Body body, int movementspeed) {

        int horizontalForce = 0;
        int verticalForce = 0;

        float playerX = playerPosition.x + 20;
        float playerY = playerPosition.y + 20;
        float folowerX = body.getPosition().x;
        float folowerY = body.getPosition().y;

        if (folowerX < playerX) {

            horizontalForce += movementspeed;
        }
        if (folowerX > playerX) {
            horizontalForce -= movementspeed;
        }
        if (folowerY < playerY) {
            verticalForce += movementspeed;
        }
        if (folowerY > playerY) {
            verticalForce -= movementspeed;
        }

        body.setLinearVelocity(horizontalForce * 5, verticalForce * 5);

    }

    public void aiShoot(BulletManager bulletManager, Vector2 coordToShoot, Body body, int BulletSpeed) {
        if (Gdx.input.isTouched()) {
            bulletManager.addBullet(coordToShoot, BodyBuilder.getInstance().createBulletBody(body.getPosition()), BulletSpeed);

        }
    }

    public void EnemyShoot(BulletManager bulletManager, Vector2 coordToShoot, Body body, int BulletSpeed) {

        bulletManager.addBullet(coordToShoot, BodyBuilder.getInstance().createEnemyBulletBody(body.getPosition()), BulletSpeed);


    }
}
