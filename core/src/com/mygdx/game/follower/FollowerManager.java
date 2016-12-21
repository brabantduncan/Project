package com.mygdx.game.follower;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

/**
 * Created by Shan on 12/19/2016.
 */
public class FollowerManager {

    public int timer;

    public FollowerManager() {
        timer = 1;
    }

    public void moveFollower(Player p) {

        if (p.getFollower() != null) {
            p.getFollower().update(p.getPlayerBody().getPosition());
            controleTimer(p);
        }

    }

    public void doAction(Player player, Vector2 mouseCoords, BulletManager bm) {
        if (player.getFollower() != null) {
            player.getFollower().action(mouseCoords, bm);
        }

    }

    public void controleTimer(Player p) {
        if (p.getFollower().getBorn() >= p.getFollower().getTimeExist()) {
            p.destroyFollower();

        } else {
            p.getFollower().increaseBorn();
        }

    }

    public void destroyFollower(Player player){
        if (player.getFollower() != null){
            player.destroyFollower();
        }
    }

    //odd eyes alle followers laten doden

    public void destroyMultipleFollowers(ArrayList<Player> players){
        for(Player p: players){
            destroyFollower(p);
        }
    }

}
