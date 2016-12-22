package com.mygdx.game.player.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

/**
 * Created by Shan on 12/21/2016.
 */
public class HudManager {

    public HudManager(){

    }

    public void updateHandler(ArrayList<Player> players, int level){

            players.get(0).getHud().update(players,level);

    }

    public void setHud(ArrayList<Player> players, Batch batch){
        if(players.size() >1){
            players.get(0).createHud(batch,new MultiplayerHud(players,batch));
        }
        else {
            players.get(0).createHud(batch, new Hud(batch));
        }
    }

    public void drawHud(ArrayList<Player> players){
        players.get(0).getHud().draw();
    }
}
