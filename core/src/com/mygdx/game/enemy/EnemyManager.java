package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.body.BodyBuilder;
import constants.Constants;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Shan on 11/28/2016.
 */
public class EnemyManager {

    private BodyBuilder bodyBuilder;
    private ArrayList<Vector2> spawnPoints;




    private ArrayList<Enemy> enemies;

    public ArrayList<Enemy> getDisposeEnemies() {
        return disposeEnemies;
    }

    private ArrayList<Enemy> disposeEnemies;

    private World w;


    private void setSpawnPoints(){

        spawnPoints.add(new Vector2(0,0));
        spawnPoints.add(new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()));
        spawnPoints.add(new Vector2(Gdx.graphics.getWidth()/2,0));
        spawnPoints.add(new Vector2(0,Gdx.graphics.getHeight()/1));
    };


    public EnemyManager(BodyBuilder bodyBuilder, World w){
        this.bodyBuilder = bodyBuilder;
        spawnPoints = new ArrayList<Vector2>();
        enemies = new ArrayList<Enemy>();
        disposeEnemies = new ArrayList<Enemy>();
        this.w = w;
        setSpawnPoints();



    }

    public Enemy spawnEnemy(){
        return new Enemy(bodyBuilder.createEnemy(w, choseSpawnPoint() ,false, Constants.Enemy, Constants.PLAYER));

    }

    public Vector2 choseSpawnPoint(){

        Random r = new Random();

        int result = r.nextInt(spawnPoints.size()-0) + 0;
        return spawnPoints.get(result);
    }

    public void createEnemies(int amount){
        for (int i = 0; i < amount; i++) {


            enemies.add(spawnEnemy());
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public  void removeEnemy(Body b) {

        for (Enemy e : enemies) {

            if (e.getBody().equals(b)) {
                disposeEnemies.add(e);
                System.out.print("Dispose set");
            }
        }
        enemies.removeAll(disposeEnemies);
    }

    public void clearDispose(){
        disposeEnemies.clear();
    }


    public void removeEnemies(Body b1, Body b2) {
        if (b1.getUserData() instanceof Enemy) {
            removeEnemy(b1);

        } else {
            removeEnemy(b2);
        }
    }

    public void  updateEnemyMovement(Vector2 playerPosition) {
        for (Enemy e : enemies) {
            e.updatePosition(playerPosition);
        }

    }


}
