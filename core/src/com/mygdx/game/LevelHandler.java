package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.states.GameStateManager;

/**
 * Created by Shan on 12/17/2016.
 */
public class LevelHandler {

    int level;
    EnemyManager enemyManager;
    GameStateManager gameStateManager;


    public LevelHandler(EnemyManager enemyManager, GameStateManager gameStateManager) {
        level = 1;

        this.enemyManager = enemyManager;
        this.gameStateManager = gameStateManager;
        enemyManager.createEnemies(1);

    }

    public void updateLevel() {
        enemyManager.destroyEnemies();
        handleLevel();
    }

    public void NextLevel() {
        level += 1;
        enemyManager.createEnemies(spawnCount());
    }

    public int getLevel() {
        return level;
    }

    public void handleLevel() {
        if (enemyManager.getEnemies().isEmpty()) {
            NextLevel();

        }
    }

    public int spawnCount() {
        return 100; //random waarden

    }


}
