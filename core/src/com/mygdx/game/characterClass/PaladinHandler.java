package com.mygdx.game.characterClass;

import com.mygdx.game.player.Player;

/**
 * Created by Shan on 11/9/2016.
 */
public class PaladinHandler implements CharacterHandler  {

    private final int HEALTH_BONUS = 50;

    @Override
    public void addBonus(Player p1) {

        p1.setCharacterClass("Paladin");
        p1.setHealth(p1.getHealth()+50);



    }
}
