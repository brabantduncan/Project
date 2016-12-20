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
    private Body jointBox;
    private ArrayList<Body> bodies;

    private Texture texture = new Texture("../assets/MinecraftIngots/shield.png");;
    private AiControllerHandler aiControllerHandler;
    private ArrayList<Enemy> enemiesKilled;
    private int timeCanExist;



    private int born;

    public ShieldFollower(Body body){
        this.body = body;
        setData();
        System.out.print("\nFollower is born");
        aiControllerHandler = new AiControllerHandler();
        timeCanExist =20;
        born =0;
        bodies = new ArrayList<Body>();

    }



    @Override
    public void action(Vector2 mouseCoord, BulletManager bulletManager) {


    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public ArrayList<Body> getBody() {
        setBody();
        return bodies;
    }

    public void setBody(){
        bodies.clear();
        bodies.add(body);
        bodies.add(jointBox);
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
        jointBox = BodyBuilder.getInstance().createBox(40, 40, playerBody.getPosition().x, playerBody.getPosition().y);
        BodyBuilder.getInstance().creatJoint(playerBody,body,jointBox);

    }


    // public void moveAroundPlayer(Vector2 coords)

    public void addEnemie(Enemy enemy){
        enemiesKilled.add(enemy);
    }

    @Override
    public int getTimeExist() {
        return timeCanExist;
    }

    public int getBorn() {
        return born;
    }

    @Override
    public void increaseBorn() {
        born+=1;
    }


}
