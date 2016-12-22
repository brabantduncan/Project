package com.mygdx.game.follower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.LevelHandler;
import com.mygdx.game.controller.AiControllerHandler;

import java.util.ArrayList;

/**
 * Created by Stijn on 21/12/2016.
 */
public class nukeFollower implements FollowerInterface {


    private Body body;
    private Texture tex = new Texture("../assets/Monsters/antolyon.png");
    private AiControllerHandler aiControllerHandler;
    private LevelHandler levelhandler;

    private int timeCanExist;
    private int born;

    public nukeFollower(Body body) {
        this.body = body;
        setData();
        System.out.print("Follower is born");
        aiControllerHandler = new AiControllerHandler();
        timeCanExist = updateDrone();
        born = 0;
    }


<<<<<<< HEAD
=======
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

        aiControllerHandler.moveToPlayer(playerPosition,body);
    }


>>>>>>> 21043c9476c14d564bebe3c34fd935207a2bcca3
    @Override
    public void action(Vector2 mouseCoord, BulletManager bulletManager) {

        aiControllerHandler.aiShoot(bulletManager,mouseCoord,body);

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

<<<<<<< HEAD
    @Override
    public void update(Vector2 playerPosition) {
        aiControllerHandler.moveToPlayer(playerPosition, body);
=======
>>>>>>> 21043c9476c14d564bebe3c34fd935207a2bcca3


<<<<<<< HEAD
    @Override
    public void setData() {
        body.setUserData(this);
        System.out.print((this instanceof FollowerInterface) + "\n");
    }

    @Override
    public void spawnExtra(Body playerPostion) {

    }
=======
>>>>>>> 21043c9476c14d564bebe3c34fd935207a2bcca3

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
        born += 1;
    }

    @Override
    public int updateDrone() {
        if(levelhandler.getLevel()<6){
            return 1000;
        }
        else {
            return 2000;
        }
    }
}
