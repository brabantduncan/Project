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

    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> disposeEnemies;
    private ArrayList<FasterEnemy> fasterenemies;
    private ArrayList<FasterEnemy> disposeFasterenemies;

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

    public EnemyManager(){
        enemies = new ArrayList<Enemy>();
        fasterenemies = new ArrayList<FasterEnemy>();
        disposeEnemies = new ArrayList<Enemy>();
    }

    public Enemy spawnEnemy(){
        return new Enemy(BodyBuilder.getInstance().createEnemy(makeRandomVector() ,false));
    }

    public FasterEnemy spawnFasterEnemy(){
        return new FasterEnemy(BodyBuilder.getInstance().createEnemy(makeRandomVector(), false));
    }

    public void createEnemies(int amount){
        for (int i = 0; i < amount; i++) {
            enemies.add(spawnEnemy());
            fasterenemies.add(spawnFasterEnemy());
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<FasterEnemy> getFasterenemies(){
        return fasterenemies;
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

    public  void removeFasterEnemy(Body b) {

        for (FasterEnemy f : fasterenemies) {

            if (f.getBody().equals(b)) {
                disposeFasterenemies.add(f);
                System.out.print("Dispose faster set");
            }
        }
        fasterenemies.removeAll(disposeFasterenemies);
    }


    public void removeEnemies(Body b1, Body b2) {
        if (b1.getUserData() instanceof Enemy) {
            removeEnemy(b1);

        } else {
            removeEnemy(b2);
        }

        if (b1.getUserData() instanceof FasterEnemy) {
            removeFasterEnemy(b1);

        } else {
            removeFasterEnemy(b2);
        }
    }

    public void updateEnemyMovement(ArrayList<Player> players) {
        for (Enemy e : enemies) {
            Body closest = calcClosest(players, e.getBody().getPosition());
            e.updatePosition(closest.getPosition());
        }

        for (FasterEnemy e : fasterenemies) {
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

            for (Enemy e : disposeEnemies) {
                BodyBuilder.getInstance().addToDestroy(e.getBody());
            }
            disposeEnemies.clear();
        }
        if (disposeFasterenemies.size() > 0) {

            for (FasterEnemy e : disposeFasterenemies) {
                BodyBuilder.getInstance().addToDestroy(e.getBody());
            }
            disposeFasterenemies.clear();
        }
    }

    public void destroyAllPeasants(){
        ArrayList<Enemy> enemyCopy = clone(enemies);
        for (Enemy e: enemyCopy){
            removeEnemy(e.getBody());
        }
        ArrayList<FasterEnemy> fasterCopy = cloneF(fasterenemies);
        for (FasterEnemy f: fasterCopy){
            removeEnemy(f.getBody());
        }
    }

    public ArrayList clone(ArrayList<Enemy> host){
        ArrayList clone = new ArrayList<Enemy>();
        for (Enemy e:host){
            clone.add(e);
        }
        return clone;
    }

    public ArrayList cloneF(ArrayList<FasterEnemy> host){
        ArrayList cloneF = new ArrayList<FasterEnemy>();
        for (FasterEnemy f:host){
            cloneF.add(f);
        }
        return cloneF;
    }
}
