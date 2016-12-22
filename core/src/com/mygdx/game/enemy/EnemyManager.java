package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.player.Player;
import constants.Constants;

import java.util.ArrayList;
import java.util.Random;

/**
 * Edited by Duncan on 21/12/2016.
 */
public class EnemyManager {

    private ArrayList<EnemyInterface> enemies;
    private ArrayList<EnemyInterface> disposeEnemies;

    public int makeSpawnPointsX() {
        Random x = new Random();
        int Low = -300;
        int High = Gdx.graphics.getWidth();
        int ResultX = x.nextInt(High - Low) + Low;
        while(ResultX>0 && ResultX<720){
            Random nx = new Random();
            int nLow = -300;
            int nHigh = Gdx.graphics.getWidth();
            int nResultX = nx.nextInt(nHigh - nLow) + nLow;
            ResultX = nResultX;
        }
        return ResultX;
    };

    public int makeSpawnPointsY(){
        Random y = new Random();
        int Low = -500;
        int High = 980;
        int ResultY = y.nextInt(High - Low) + Low;
        while(ResultY>0 && ResultY<480){
            Random ny = new Random();
            int nLow = -500;
            int nHigh = Gdx.graphics.getHeight();
            int nResultY = ny.nextInt(nHigh - nLow) + nLow;
            ResultY = nResultY;
        }
        return ResultY;
    }

    public Vector2 makeRandomVector() {
        int x = makeSpawnPointsX();
        int y = makeSpawnPointsY();

        if (!(x < 0 || x > Gdx.graphics.getWidth())) {
            if (!(y < 0 || y > Gdx.graphics.getHeight())) {
                Random r = new Random();
                int randomInt = r.nextInt(100);

                if((randomInt%2)==0){
                    return new Vector2(x, -randomInt);
                }else{
                    return new Vector2(x,Gdx.graphics.getHeight()+randomInt);
                }

            } else {
                return new Vector2(x, y);
            }
        } else {

            return new Vector2(x, y);

        }


    }

    public EnemyManager(){
        enemies = new ArrayList<EnemyInterface>();
        disposeEnemies = new ArrayList<EnemyInterface>();

    }

    public EnemyInterface spawnEnemy(){
        return new EnemyFactory().generateEnemy(BodyBuilder.getInstance().createEnemy(makeRandomVector(), false));
    }


    public void createEnemies(int amount){
        for (int i = 0; i < amount; i++) {
            enemies.add(spawnEnemy());
        }
    }

    public ArrayList<EnemyInterface> getEnemies() {
        return enemies;
    }

    public  void removeEnemy(Body b) {

        for (EnemyInterface e : enemies) {

            if (e.getBody().equals(b)) {
                disposeEnemies.add(e);
                System.out.print("Dispose set");
            }
        }
        enemies.removeAll(disposeEnemies);
    }


    public void removeEnemies(Body b1, Body b2) {
        if (b1.getUserData() instanceof EnemyInterface) {
            removeEnemy(b1);
        } else {
            removeEnemy(b2);
        }

    }


    public void updateEnemyMovement(ArrayList<Player> players) {
        for (EnemyInterface e : enemies) {
            Body closest = calcClosest(players, e.getBody().getPosition());
            e.updatePosition(closest.getPosition());
        }

    }

    private Body calcClosest(ArrayList<Player> players, Vector2 enemyPosition ) {
        Body closest = players.get(0).getPlayerBody();
        for(Player p: players){
            if (p.getPlayerBody().getPosition().dst(enemyPosition) <closest.getPosition().dst(enemyPosition)){
                closest = p.getPlayerBody();
            }
        }
        return closest;
    }

    public void destroyEnemies() {
        if (disposeEnemies.size() > 0) {

            for (EnemyInterface e : disposeEnemies) {
                BodyBuilder.getInstance().addToDestroy(e.getBody());
            }
            disposeEnemies.clear();
        }
    }

    public void destroyAllPeasants(){
<<<<<<< HEAD
        System.out.println("destroying all");
        ArrayList<Enemy> enemyCopy = clone(enemies);
        for (Enemy e: enemyCopy){
=======
        ArrayList<EnemyInterface> enemyCopy = clone(enemies);
        for (EnemyInterface e: enemyCopy){
>>>>>>> d1f4feda5cb597c65b301fbf7721f987e24565a9
            removeEnemy(e.getBody());
        }
    }

    public ArrayList clone(ArrayList<EnemyInterface> host){
        ArrayList clone = new ArrayList<Enemy>();
        for (EnemyInterface e:host){
            clone.add(e);
        }
        return clone;
    }

}
