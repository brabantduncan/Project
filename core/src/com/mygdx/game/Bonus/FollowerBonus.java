package com.mygdx.game.Bonus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.player.Player;
import constants.Constants;

/**
 * Created by Shan on 12/19/2016.
 */
public class FollowerBonus implements BonusInterface {

    private final int score = Constants.GEM;
    private Body b;
    private Texture texture = new Texture("../assets/MinecraftIngots/chicken.png");


    public FollowerBonus(Body body) {

        this.b = body;
        setUserData();
    }


    @Override
    public void addBonus(Player p) {
        if (p.getFollower() == null) {
            p.spawnFollower();
            
        }

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
