package com.mygdx.game.characterClass;

import com.mygdx.game.Player;

/**
 * Created by Shan on 11/8/2016.
 */
public class WarriorHandler implements CharacterHandler {

    private final int EXTRA_HEALTH = 20;

    @Override
    public void addBonus(Player p1) {

        p1.setCharacterClass("Warrior");
        p1.setHealth(p1.getHealth()+ EXTRA_HEALTH);

    }
}
