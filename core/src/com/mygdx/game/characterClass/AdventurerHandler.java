package com.mygdx.game.characterClass;

import com.mygdx.game.Player;

/**
 * Created by Shan on 11/8/2016.
 */
public class AdventurerHandler implements CharacterHandler {

    private final int DEFAULT_HEALTH= 100;
    private final int DEFAULT_ATTACKSPEED= 10;;
    private final int DEFAULT_MOVEMENTSPEED= 10;




    @Override
    public void addBonus(Player p1) {



        p1.setCharacterClass("Adventurer");


        p1.setHealth(DEFAULT_HEALTH);

        p1.setAttackSpeed(DEFAULT_ATTACKSPEED);
        p1.setMovenentSpeed(DEFAULT_MOVEMENTSPEED);

    }
}
