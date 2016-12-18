package com.mygdx.game.Bonus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

/**
 * Created by Shan on 12/5/2016.
 */

//Manier vinden om random drops te maken en zorgen dat deze kan uitgebreid worden


public class BonusHandler {

    //alle items in interface doen
    ArrayList<Vector2> bonusSpawnCoord;
    ArrayList<BonusInterface> bonusToRemove;
    ArrayList<BonusInterface> bonusToSpawn;

    private World world;
    private BodyBuilder bodyBuilder;

    private BonusFactory bonusFactory;



    public BonusHandler(World w, BodyBuilder b) {
        bonusSpawnCoord = new ArrayList<Vector2>();
        bonusToSpawn = new ArrayList<BonusInterface>();
        bonusToRemove = new ArrayList<BonusInterface>();
        bonusFactory = new BonusFactory();
        this.world = w;
        this.bodyBuilder = b;
    }

    public BonusInterface spawnBonus(Vector2 spawn) {

        return bonusFactory.generateBonus(bodyBuilder.createGemBody(world,spawn));

    }

    public void addSpawnCoord(Vector2 spawn) {

        bonusSpawnCoord.add(spawn);


    }

    public void addBonus() {

        if (!(bonusSpawnCoord.size() == 0)) {
            for (Vector2 v : bonusSpawnCoord) {
                 bonusToSpawn.add(spawnBonus(v));


            }
            bonusSpawnCoord.clear();
        }
        //System.out.print("Niks\n");

    }

    public void setRemoveList(Body bonusBody) {

        for (BonusInterface g :  bonusToSpawn) {
            if (g.getBody().equals(bonusBody)) {
                bonusToRemove.add(g);
            }
        }
         bonusToSpawn.removeAll(bonusToRemove);
    }

    public void destroyGems(Player p ) {
        if (!(bonusToRemove.size() == 0)) {
            for (BonusInterface g : bonusToRemove) {
                g.addBonus(p);
                world.destroyBody(g.getBody());
            }
            bonusToRemove.clear();
        }

    }

    public ArrayList<BonusInterface> getBonusToSpawn(){
        return bonusToSpawn;
    }

}
