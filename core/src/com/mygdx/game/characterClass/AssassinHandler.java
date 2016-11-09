package com.mygdx.game.characterClass;

import com.mygdx.game.Player;

/**
 * Created by Shan on 11/9/2016.
 */
public class AssassinHandler implements CharacterHandler  {


    private final int MOVEMENTSPEED_BONUS = 2;


    @Override
    public void addBonus(Player p1) {
        p1.setCharacterClass("Assassin");
        p1.setMovenentSpeed(p1.getMovenentSpeed()*2);

    }
}
