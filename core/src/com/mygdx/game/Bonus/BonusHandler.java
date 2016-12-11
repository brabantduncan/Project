package com.mygdx.game.Bonus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Shan on 12/5/2016.
 */


public class BonusHandler {

    //HashMap<Vector2,Item> spawnlocation;

    //alle items in interface doen
    ArrayList<Vector2> gemSpawnCoord;
    ArrayList<Gem> gemRemove;
    ArrayList<Gem> gemSpawn;

    private World world;
    private BodyBuilder bodyBuilder;



    public BonusHandler(World w, BodyBuilder b) {
        //spawnlocation = new HashMap<Vector2, Item>();
        gemSpawnCoord = new ArrayList<Vector2>();
        gemSpawn = new ArrayList<Gem>();
        gemRemove = new ArrayList<Gem>();
        this.world = w;
        this.bodyBuilder = b;
    }

    public Gem spawnGem(Vector2 spawn) {

        return new Gem(bodyBuilder.createGemBody(world, spawn));

    }

    /**
     * public void addBonus(Vector2 spawn){
     * //System.out.print("Spawn item at "+spawn+" \n");
     * };
     */


    public void addGemCoord(Vector2 spawn) {

        gemSpawnCoord.add(spawn);


    }

    public void addGem() {

        if (!(gemSpawnCoord.size() == 0)) {
            for (Vector2 v : gemSpawnCoord) {
                gemSpawn.add(spawnGem(v));


            }
            gemSpawnCoord.clear();
        }
        //System.out.print("Niks\n");

    }

    public void setRemoveList(Body gemBody) {

        for (Gem g : gemSpawn) {
            if (g.getB().equals(gemBody)) {
                gemRemove.add(g);
            }
        }
        gemSpawn.removeAll(gemRemove);
    }

    public void destroyGems(Player p ) {
        if (!(gemRemove.size() == 0)) {
            for (Gem g : gemRemove) {
                g.addBonus(p);
                world.destroyBody(g.getB());
            }
            gemRemove.clear();
        }

    }

    //public void excuteBonus(){}

}

