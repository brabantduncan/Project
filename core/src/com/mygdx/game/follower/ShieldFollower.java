package com.mygdx.game.follower;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.controller.AiControllerHandler;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.enemy.EnemyManager;

import java.util.ArrayList;

/**
 * Created by Shan on 12/19/2016.
 */
public class ShieldFollower implements FollowerInterface {

    private Body body;
    private Texture tex = new Texture("../assets/Monsters/alteroit.png");
    private AiControllerHandler aiControllerHandler;
    private ArrayList<Enemy> enemiesKilled;


    public ShieldFollower(Body body){
        this.body = body;
        setData();
        System.out.print("\nFollower is born");
        aiControllerHandler = new AiControllerHandler();
    }



    @Override
    public void action(Vector2 mouseCoord, BulletManager bulletManager) {


    }

    @Override
    public Texture getTexture() {
        return tex;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void update(Vector2 position) {
        aiControllerHandler.moveToPlayer(position,body);

    }

    @Override
    public void setData() {
        body.setUserData(this);
    }

    @Override
    public void spawnExtra(Body playerBody) {
        BodyBuilder.getInstance().creatJoint(playerBody,body);

    }

    // public void moveAroundPlayer(Vector2 coords)

    public void addEnemie(Enemy enemy){
        enemiesKilled.add(enemy);
    }

}
