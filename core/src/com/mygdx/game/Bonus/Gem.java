package com.mygdx.game.Bonus;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.player.Player;

/**
 * Created by Shan on 12/4/2016.
 */
public class Gem {

    private int score;



    private Body b;


    public Gem(Body body){

        this.b =body;
        setData();
    }
    public Body getB() {
        return b;
    }

    public void setData(){

      b.setUserData(this);
    };


}