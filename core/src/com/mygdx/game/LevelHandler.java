package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.PlayState;
import database.projectDB;

import java.sql.SQLException;

/**
 * Created by Shan on 12/17/2016.
 */
public class LevelHandler {

    int level;
    EnemyManager enemyManager;
    GameStateManager gameStateManager;
    public String difficulty;



    public LevelHandler(EnemyManager enemyManager, GameStateManager gameStateManager, String difficulty) {
        level = 1;

        this.enemyManager = enemyManager;
        this.gameStateManager = gameStateManager;
        this.difficulty = difficulty;

        //enemyManager.createEnemies(20); // begin monsters

        enemyManager.createEnemies(1);

    }

    public void updateLevel() {
        enemyManager.destroyEnemies();
        handleLevel();
    }

    public void NextLevel() {
        level += 1;

        //PlayState.changeMap(level);
        System.out.println("new level");
        try {
            enemyManager.createEnemies(spawnCount(difficulty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevel() {
        return level;
    }

    public void handleLevel() {
        if (enemyManager.getEnemies().isEmpty()) {
            NextLevel();
        }
    }

    public int spawnCount(String difficulty) throws SQLException {
        return level*15*projectDB.getInstance().getDifficultyParameter(difficulty); //monsters per level
    }


}
