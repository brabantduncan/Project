package com.mygdx.game.Bonus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.player.Player;

/**
 * Created by Shan on 12/12/2016.
 */
public class Multiplier implements BonusInterface {


    private float multiplier;
    private Body body;
    private Texture texture = new Texture("../assets/MinecraftIngots/Bonus.png");

    public Multiplier(Body body) {
        this.body = body;
        multiplier = 0;
        setUserData();
    }

    public Multiplier(Body body, int multiplier) {
        this.body = body;
        this.multiplier = multiplier;
    }

    @Override
    public void addBonus(Player p) {

        int score = p.getCurrentScore();
        p.resetScore();

        int procentBonus = (int)(Math.round(0.05*score));
        p.increaseCurrentScore(score + procentBonus);

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
