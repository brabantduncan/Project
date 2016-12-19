package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.player.Player;
import com.mygdx.game.states.BossState;
import com.mygdx.game.states.GameStateManager;

/**
 * Created by Shan on 12/17/2016.
 */
public class LevelHandler {

    int level;
    EnemyManager enemyManager;
    GameStateManager gameStateManager;
    Player player;

    public LevelHandler(Player player, EnemyManager enemyManager, GameStateManager gameStateManager) {
        level = 1;

        this.enemyManager = enemyManager;
        this.gameStateManager = gameStateManager;
        enemyManager.createEnemies(100);
        this.player = player;
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

        if (level == 20) {

            gameStateManager.set(new BossState(gameStateManager, player));
        }
        if (enemyManager.getEnemies().isEmpty()) {
            NextLevel();

        }
    }

    public int spawnCount() {
        return 1; //random waarden

    }

}
