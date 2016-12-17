package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.enemy.EnemyManager;

/**
 * Created by Shan on 12/17/2016.
 */
public class LevelHandler {

    int level;
    EnemyManager enemyManager;

    public LevelHandler(EnemyManager enemyManager){
        level = 1;

        this.enemyManager = enemyManager;
        enemyManager.createEnemies(100);
    }

    public void updateLevel(World world){
        destroyEnemies(world);
        handleLevel();
    }

    public void NextLevel(){
        level+=1;
        enemyManager.createEnemies(spawnCount());
    }

    public int getLevel(){
        return level;
    }

    public void destroyEnemies(World world) {
        if (enemyManager.getDisposeEnemies().size() > 0) {

            for (Enemy e : enemyManager.getDisposeEnemies()) {
                world.destroyBody(e.getBody());
            }
            enemyManager.clearDispose();
        }
    }

    public void handleLevel() {

        if (enemyManager.getEnemies().isEmpty()) {
            NextLevel();

        }
    }

    public int spawnCount(){
        return 100; //random waarden

    }

}
