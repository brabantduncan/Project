package com.mygdx.game.enemy;


import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bonus.BonusInterface;
import com.mygdx.game.Bonus.bonusses.FollowerBonus;
import com.mygdx.game.Bonus.bonusses.Multiplier;
import com.mygdx.game.Bonus.bonusses.Overshield;

import java.util.Random;

/**
 * Created by Duncan on 21/12/2016.
 */
public class EnemyFactory {

    public EnemyInterface generateEnemy(Body b) {
        Random r = new Random();
        return getRandomEnemy(b, r.nextInt(60));
    }

    private EnemyInterface getRandomEnemy(Body b, int i) {

        EnemyInterface enemy;

        if ( i < 30) {
            enemy = new Enemy(b);
        } else {
            if (i>30 && i<50) {
                enemy = new FasterEnemy(b);
            }
            else{
                enemy = new DragonEnemy(b);
            }
        }
        return enemy;
    }
}