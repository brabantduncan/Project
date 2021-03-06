package com.mygdx.game.follower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.LevelHandler;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.controller.AiControllerHandler;
import com.mygdx.game.states.PlayState;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import constants.Constants;

import java.util.ArrayList;

import static sun.audio.AudioPlayer.player;

/**
 * Created by Shan on 12/12/2016.
 */

public class FrontWatcherFollower implements FollowerInterface {



    private Body body;
    private Texture tex = new Texture("../assets/Monsters/acrophis.png");
    private AiControllerHandler aiControllerHandler;
    private LevelHandler levelhandler;

    private int timeCanExist;
    private int born;



    private boolean isUpgraded;

    public FrontWatcherFollower(Body body){
        this.body = body;
        setData();
        System.out.print("Follower is born");
        aiControllerHandler = new AiControllerHandler();
        timeCanExist = 2000;
        born = 0;
        isUpgraded = false;
    }

    @Override
    public void setData(){
        body.setUserData(this);
        System.out.print((this instanceof FollowerInterface)+"\n");
    }

    @Override
    public void spawnExtra(Body playerPostion) {

    }



    @Override
    public void update(Vector2 playerPosition){

        aiControllerHandler.moveToPlayer(playerPosition,body, Constants.MOVEMENT_FOLLOWER_SPEED);
    }


    @Override
    public void action(Vector2 mouseCoord, BulletManager bulletManager) {

        aiControllerHandler.aiShoot(bulletManager,mouseCoord,body,100);

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
    public int getTimeExist() {
        return timeCanExist;
    }

    @Override
    public int getBorn() {
        return born ;
    }

    @Override
    public void increaseBorn() {
        born +=1;
    }

    @Override
    public void updateDrone() {

        timeCanExist +=1000;

    }

    @Override
    public boolean isUpgraded() {
        return isUpgraded;
    }

    @Override
    public void upgrade(Boolean upgrade) {
        this.isUpgraded = upgrade;
    }



}
