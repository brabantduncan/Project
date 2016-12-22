package com.mygdx.game.Bonus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Shan on 12/5/2016.
 */

//Manier vinden om random drops te maken en zorgen dat deze kan uitgebreid worden


public class BonusHandler {

    //alle items in interface doen
    ArrayList<Vector2> bonusSpawnCoord;
    HashMap<Body, BonusInterface> bonusToRemove;
    ArrayList<BonusInterface> destroyBodies;
    ArrayList<BonusInterface> bonusToSpawn;

    private BonusFactory bonusFactory;


    public BonusHandler() {
        bonusSpawnCoord = new ArrayList<Vector2>();
        bonusToSpawn = new ArrayList<BonusInterface>();
        bonusToRemove = new HashMap<Body, BonusInterface>();
        bonusFactory = new BonusFactory();
        destroyBodies = new ArrayList<BonusInterface>();
    }

    public BonusInterface spawnBonus(Vector2 spawn) {

        return bonusFactory.generateBonus(BodyBuilder.getInstance().createGemBody(spawn));

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

    }

    public void setRemoveList(Body playerbody, Body bonusBody) {
        ArrayList<BonusInterface> pickedUpBonus = new ArrayList<BonusInterface>();

        for (BonusInterface g : bonusToSpawn) {
            if (g.getBody().equals(bonusBody)) {
                pickedUpBonus.add(g);
                bonusToRemove.put(playerbody, g);

            }
        }
        bonusToSpawn.removeAll(pickedUpBonus);
    }

    public void destroyGems(ArrayList<Player> players) {

        if (!(bonusToRemove.size() == 0)) {

            for (Body b : bonusToRemove.keySet()) {
                for (Player p : players) {
                    if (p.getPlayerBody().equals(b)) {
                        System.out.print("Bonus added to player");
                        bonusToRemove.get(b).addBonus(p);
                        addDestroyBonus(bonusToRemove.get(b));
                    }
                }
            }

            bonusToRemove.clear();
            destroyBodies();
        }

    }


    public void addDestroyBonus(BonusInterface bonusInterface){
        destroyBodies.add(bonusInterface);
    }

    public void destroyBodies(){
        for (BonusInterface b: destroyBodies){
            BodyBuilder.getInstance().addToDestroy(b.getBody());

        }

        destroyBodies.clear();
    }

    public ArrayList<BonusInterface> getBonusToSpawn() {
        return bonusToSpawn;
    }

    public void removeFromField(Body body){
        ArrayList<BonusInterface> copy =cloneList(bonusToSpawn);
        for (BonusInterface b: copy){
            if (b.getBody().equals(body)){
                bonusToSpawn.remove(b);
                addDestroyBonus(b);
            }
        }
        copy.clear();
    }

    public ArrayList<BonusInterface> cloneList(ArrayList<BonusInterface> host){
        ArrayList<BonusInterface> copyList = new ArrayList<BonusInterface>();
        for (BonusInterface b:host){
            copyList.add(b);
        }
        return copyList;
        

    }
}

