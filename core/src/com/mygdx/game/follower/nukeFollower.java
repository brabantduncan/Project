package com.mygdx.game.follower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.LevelHandler;
import com.mygdx.game.controller.AiControllerHandler;
import constants.Constants;

import java.util.ArrayList;

/**
 * Created by Stijn on 21/12/2016.
 */
public class nukeFollower implements FollowerInterface {


    private Body body;
    private Texture tex = new Texture("../assets/Monsters/minedo.png");
    private AiControllerHandler aiControllerHandler;
    private LevelHandler levelhandler;

    private int timeCanExist;
    private int born;
    private boolean upgraded;

    public nukeFollower(Body body) {
        this.body = body;
        setData();
        System.out.print("Follower is born");
        aiControllerHandler = new AiControllerHandler();
        timeCanExist = 200;
        born = 0;
        upgraded = false;
    }


    @Override
    public void setData() {
        body.setUserData(this);
        System.out.print((this instanceof FollowerInterface) + "\n");
    }

    @Override
    public void spawnExtra(Body playerPostion) {

    }

    @Override
    public int getTimeExist() {
        return timeCanExist;
    }

    @Override
    public int getBorn() {
        return born;
    }


    @Override
    public void action(Vector2 mouseCoord, BulletManager bulletManager) {
        aiControllerHandler.aiShoot(bulletManager,mouseCoord,body,0);
    }

    @Override
    public Texture getTexture() {
        return tex;
    }

    @Override
    public ArrayList<Body> getBody() {
        ArrayList arrayList = new ArrayList<Body>();
        arrayList.add(body);
        return arrayList;
    }

    @Override
    public void update(Vector2 playerPosition) {

        aiControllerHandler.moveToPlayer(playerPosition,body, Constants.MOVEMENT_FOLLOWER_SPEED);

    }

    @Override
    public void increaseBorn() {
        born += 1;
    }

    @Override
    public void updateDrone() {
        this.timeCanExist *= 10;

    }

    @Override
    public boolean isUpgraded() {

        return upgraded;
    }

    @Override
    public void upgrade(Boolean upgrade) {

    }
}

