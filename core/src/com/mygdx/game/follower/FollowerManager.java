package com.mygdx.game.follower;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.player.Player;

/**
 * Created by Shan on 12/19/2016.
 */
public class FollowerManager {

    public void moveFollower(Player p){
        if (p.getFollower() != null){
            p.getFollower().update(p.getPlayerBody().getPosition());
        }

    }

    public void doAction(Player player,Vector2 mouseCoords, BulletManager bm) {
        if (player.getFollower() != null){
            player.getFollower().action(mouseCoords,bm);
        }

    }
}
