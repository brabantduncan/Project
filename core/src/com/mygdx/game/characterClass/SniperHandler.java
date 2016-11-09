package com.mygdx.game.characterClass;

import com.mygdx.game.Player;

/**
 * Created by Shan on 11/9/2016.
 */
public class SniperHandler implements CharacterHandler  {

    private final int ATTACKSPEED_BONUS = 2;

    @Override
    public void addBonus(Player p1) {

        p1.setCharacterClass("Sniper");
        p1.setAttackSpeed( p1.getAttackSpeed()*ATTACKSPEED_BONUS);

    }
}
