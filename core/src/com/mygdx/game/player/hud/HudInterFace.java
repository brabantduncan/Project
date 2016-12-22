package com.mygdx.game.player.hud;

import com.mygdx.game.player.Player;

import java.util.ArrayList;

/**
 * Created by Shan on 12/21/2016.
 */
public interface HudInterFace {

    void update(ArrayList<Player> players, int level);
    void  draw();
}
