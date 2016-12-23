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
        return getRandomPowerUp(b, r.nextInt(100));
    }


    private BonusInterface getRandomPowerUp(Body b, int i) {

        BonusInterface bonus;

        if (0 < i && i <25 ) {
            return  new Gem(b);
        }

        if (25 < i && i < 45) {
            return new Multiplier(b);
        }
        if (45 < i && i < 55) {return new FollowerBonus(b);}
        if (55 < i && i < 65) {return new Bomb(b);}
        if (65< i && i < 75) {return  new Overshield(b);}

        return new Gem(b);
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