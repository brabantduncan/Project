package com.mygdx.game.Bonus;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bonus.bonusses.*;

import java.util.Random;

/**
 * Created by Shan on 12/14/2016.
 */
public class BonusFactory {

    //private BonusMaker[] bonusPrototypes;


    public BonusInterface generateBonus(Body b) {
        Random r = new Random();//
        return getRandomPowerUp(b, r.nextInt(50));
    }


    private BonusInterface getRandomPowerUp(Body b, int i) {

        BonusInterface bonus;

        if (30 < i && i < 31) {
            bonus = new Overshield(b);
        } else {
            if (40 < i && i < 45) {
                bonus = new Multiplier(b);
            } else {

                 //bonus = new Gem(b);
                //bonus = new Bomb(b);
                //bonus = new FollowerBonus(b);

                 //bonus = new Gem(b);
                //bonus = new Bomb(b);
                bonus = new FollowerBonus(b);


            }
        }
        return bonus;
    }


}


/**
 * functional interface
 * static interface BonusMaker {
 * <p>
 * public BonusInterface create(Body b);
 * }
 * private static final BonusMaker[] bonusPrototypes = new BonusMaker[]{
 * <p>
 * <p>
 * new BonusMaker() { public BonusInterface create(Body b){return new Gem(b);} },
 * new BonusMaker() { public BonusInterface create(Body b){return new Multiplier(b);} },
 * new BonusMaker() { public BonusInterface create(Body b){return new Overshield(b);} }
 * <p>
 * };
 * <p>
 * private static final BonusMaker[] bonusPrototypes = new BonusMaker[]{
 * <p>
 * <p>
 * new BonusMaker() { public BonusInterface create(Body b){return new Gem(b);} },
 * new BonusMaker() { public BonusInterface create(Body b){return new Multiplier(b);} },
 * new BonusMaker() { public BonusInterface create(Body b){return new Overshield(b);} }
 * <p>
 * };
 * <p>
 * private static final BonusMaker[] bonusPrototypes = new BonusMaker[]{
 * <p>
 * <p>
 * new BonusMaker() { public BonusInterface create(Body b){return new Gem(b);} },
 * new BonusMaker() { public BonusInterface create(Body b){return new Multiplier(b);} },
 * new BonusMaker() { public BonusInterface create(Body b){return new Overshield(b);} }
 * <p>
 * };
 **/
//random int  en daarvoor switch


//lambda expression
/**
 * private static final BonusMaker[] bonusPrototypes = new BonusMaker[]{
 * <p>
 * <p>
 * new BonusMaker() { public BonusInterface create(Body b){return new Gem(b);} },
 * new BonusMaker() { public BonusInterface create(Body b){return new Multiplier(b);} },
 * new BonusMaker() { public BonusInterface create(Body b){return new Overshield(b);} }
 * <p>
 * };
 **/