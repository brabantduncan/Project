package com.mygdx.game.characterClass;

import com.mygdx.game.player.Player;

/**
 * Created by Shan on 11/9/2016.
 */
public class HunterHandler implements CharacterHandler  {

    private final float ATTACKSPEED_BONUS = 1.5f;

    //Tijdelijk casten naar int

    @Override
    public void addBonus(Player p1) {

        p1.setCharacterClass("Hunter");

        int bonus = (int) ((int) p1.getAttackSpeed()*ATTACKSPEED_BONUS);

        p1.setAttackSpeed(bonus);

    }
}
