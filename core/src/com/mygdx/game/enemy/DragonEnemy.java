package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.controller.AiControllerHandler;
import com.mygdx.game.follower.FollowerManager;
import com.mygdx.game.player.Player;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import constants.Constants;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Duncan on 22/12/2016.
 */
public class DragonEnemy implements EnemyInterface {

    private float x;
    private float y;

    private Body body;



    private Texture texture = new Texture("../assets/Monsters/darkdragon.png");

    private AiControllerHandler aiControllerHandler;

    public DragonEnemy(Body body){
        this.body = body;

        x = pointGenerator(250,500); // should be game.WIDTH / 2;
        y = pointGenerator(200, 400);
        aiControllerHandler = new AiControllerHandler();
        setUserData();
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public float pointGenerator(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public void updatePosition(Vector2 playerPosition) {

        aiControllerHandler.moveToPlayer(playerPosition,body, Constants.MOVEMENT_SPEED_DRAGON);


    }

    @Override
    public void setUserData() {
        body.setUserData(this);
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public float getY() {
        return x;
    }

    @Override
    public float getX() {
        return y;
    }

    @Override
    public void action(ArrayList<Player> players, BulletManager bulletManager, FollowerManager followerManager) {

        Random rand = new Random();
        int random = rand.nextInt(10)+1;
        enablePlayer(random,players);
        shoot(random,getRandomPlayer(players).getPlayerBody().getWorldCenter(),bulletManager);
        destroy(random,players, followerManager);
        disablePlayer(random,players);

    }


    public void shoot(int random, Vector2 playerCoord, BulletManager bulletManager){
        if (random == 1){
            aiControllerHandler.EnemyShoot(bulletManager,playerCoord,body,5);
        }
    }

    public void destroy(int random, ArrayList<Player> players, FollowerManager followerManager){
        if ( (2 <=random) && (random <=4) ){
            followerManager.destroyMultipleFollowers(players);
        }
    }

    public void disablePlayer(int random, ArrayList<Player> players){

        if (random ==5){
            Player player =  getRandomPlayer(players);
            player.setDisabled(true);
            player.setInvincible(false);
        }

    }

    public void enablePlayer(int random, ArrayList<Player> players){

        if (random> 5){
            getRandomPlayer(players).setDisabled(false);
        }

    }

//    public void removepoints()


    public Player getRandomPlayer(ArrayList<Player> players){
        Random rand = new Random();
        int randomPlayer = rand.nextInt(players.size());
        return  players.get(randomPlayer);
    }



}
