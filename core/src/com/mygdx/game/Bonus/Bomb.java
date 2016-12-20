package com.mygdx.game.Bonus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.player.Player;
import constants.Constants;

/**
 * Created by Shan on 12/19/2016.
 */
public class Bomb implements BonusInterface {


    private Body b;
    private Texture texture = new Texture("../assets/MinecraftIngots/Diamond_(Gem).png");



    public Bomb(Body body){
        this.b =body;
        setUserData();
    }

    @Override
    public void addBonus(Player p) {
        p.setHasBomb(true);
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
