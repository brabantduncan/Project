package com.mygdx.game.Bonus.bonusses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bonus.BonusInterface;
import com.mygdx.game.player.Player;
import constants.Constants;

/**
 * Created by Shan on 12/4/2016.
 */
public class Gem implements BonusInterface {

    private final int score = Constants.GEM;
    private Body b;
    private Texture texture = new Texture("../assets/MinecraftIngots/Diamond_(Gem).png");


    public Gem(Body body){

        this.b =body;
        setUserData();
    }




    @Override
    public void addBonus(Player player) {
     player.increaseCurrentScore(score);
    }

    @Override
    public void setUserData() {
        b.setUserData(this);
    }

    @Override
    public Body getBody() {
      return b;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }


}