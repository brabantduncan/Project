package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.DataBuffer;
import com.mygdx.game.body.BodyBuilder;

import java.util.ArrayList;

/**
 * Created by Shan on 12/20/2016.
 */
public class PlayerFactory {

    World world;

    public PlayerFactory(){

    }


    public Player createPlayer(){
        return new Player(BodyBuilder.getInstance().createPlayer(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4, 45 / 4, 48 / 4, false), "John");
    }

    public ArrayList<Player> getPlayers(int amount){
        ArrayList<Player> players = new ArrayList<Player>();
        System.out.println("You asked for "+amount+ "players");
        for (int i =0; i<amount ;i++){
            players.add(createPlayer());
            System.out.println("Player created");
        }
        return players;
    }

}
