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


<<<<<<< HEAD
    public Player createPlayer(String username){
        return new Player(BodyBuilder.getInstance().createPlayer(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4, 45 / 4, 48 / 4, false), username);
=======
    public Player createPlayer(){
        return new Player(BodyBuilder.getInstance().createPlayer(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4, 45 / 4, 48 / 4, false), "John");

>>>>>>> 392449fa3718f0b54f0cef2fea6baff49662c7fa
    }

    public ArrayList<Player> getPlayers(int amount){
        ArrayList<Player> players = new ArrayList<Player>();
        System.out.println("You asked for "+amount+ "players");
        for (int i =0; i<amount ;i++){
            players.add(createPlayer("john" + i));
            System.out.println("Player created");
        }
        return players;
    }

}
