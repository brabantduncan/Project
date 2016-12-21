package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.body.BodyBuilder;
import constants.Constants;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Shan on 11/28/2016.
 */
public class EnemyManager {

    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> disposeEnemies;

    public int makeSpawnPointsX() {
        Random x = new Random();
        int Low = 0;
        int High = Gdx.graphics.getWidth();
        int ResultX = x.nextInt(High - Low) + Low;
        return ResultX;
    };

    public int makeSpawnPointsY(){
        Random y = new Random();
        int Low = 0;
        int High = Gdx.graphics.getHeight();
        int ResultY = y.nextInt(High - Low) + Low;
        return ResultY;
    }

    public Vector2 makeRandomVector(){
        return new Vector2(makeSpawnPointsX(),makeSpawnPointsY());
    }

   /* private void setSpawnPoints(){

        spawnPoints.add(makeRandomVector());
        spawnPoints.add(makeRandomVector());
        spawnPoints.add(makeRandomVector());
        spawnPoints.add(makeRandomVector());
        spawnPoints.add(makeRandomVector());
        spawnPoints.add(makeRandomVector());
        spawnPoints.add(makeRandomVector());
        spawnPoints.add(makeRandomVector());
        spawnPoints.add(makeRandomVector());

    };*/


    public EnemyManager(){
        //spawnPoints = new ArrayList<Vector2>();
        enemies = new ArrayList<Enemy>();
        disposeEnemies = new ArrayList<Enemy>();
        //setSpawnPoints();



    }

    public Enemy spawnEnemy(){
        return new Enemy(BodyBuilder.getInstance().createEnemy(makeRandomVector() ,false));

    }

    /*public Vector2 choseSpawnPoint(){

        Random r = new Random();

        int result = r.nextInt(spawnPoints.size()-0) + 0;
        return spawnPoints.get(result);
    }*/

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


    public void destroyEnemies() {
        if (disposeEnemies.size() > 0) {

            for (Enemy e : disposeEnemies) {
                BodyBuilder.getInstance().addToDestroy(e.getBody());
            }
            disposeEnemies.clear();
        }
    }

    public void destroyAllPeasants(){
        ArrayList<Enemy> enemyCopy = clone(enemies);
        for (Enemy e: enemyCopy){
            removeEnemy(e.getBody());
        }
    }

    public ArrayList clone(ArrayList<Enemy> host){
        ArrayList clone = new ArrayList<Enemy>();
        for (Enemy e:host){
            clone.add(e);
        }
        return clone;
    }
}
