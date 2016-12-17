package com.mygdx.game.Bonus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.player.Player;

/**
 * Created by Shan on 12/12/2016.
 */
public class Overshield implements BonusInterface  {

    private Body body;
    private Texture texture = new Texture("../assets/MinecraftIngots/Shield.png");


    public Overshield(Body body){
        this.body = body;
        setUserData();
    }


    @Override
    public void addBonus(Player p) {
        p.setInvincible(true);
    }

    @Override
    public void setUserData() {
        body.setUserData(this);
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}
