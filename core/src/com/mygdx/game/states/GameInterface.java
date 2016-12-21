package com.mygdx.game.states;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bonus.BonusHandler;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

/**
 * Created by Shan on 12/18/2016.
 */
public interface GameInterface {

    public void removeBullet(Body b1, Body b2);

    public void removeEnemies(Body b1, Body b2);

    public void addCoordToBonusHandler(Vector2 coord);

    public BonusHandler getBonusHandler();

    public ArrayList<Player> getPlayer();

    public  void destroyAllPeasants();

    public EnemyManager getEnemyManager();

}
