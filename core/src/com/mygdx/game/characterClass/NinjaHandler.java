package com.mygdx.game.characterClass;

import com.mygdx.game.player.Player;

/**
 * Created by Shan on 11/9/2016.
 */
public class NinjaHandler implements CharacterHandler  {



    private final float ATTACKSPEED_BONUS = 2.5f;
    private final int MOVEMENTSPEED_BONUS = 2;

    @Override
    public void addBonus(Player p1) {



        int attackBonus = (int) (p1.getAttackSpeed() * ATTACKSPEED_BONUS);
        p1.setAttackSpeed(attackBonus);

        p1.setMovenentSpeed( p1.getMovenentSpeed() * MOVEMENTSPEED_BONUS);

    }
}
