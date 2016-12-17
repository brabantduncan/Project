package com.mygdx.game.Bonus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.player.Player;

/**
 * Created by Shan on 12/7/2016.
 */
public interface BonusInterface {

    public void addBonus(Player p);
    public void setUserData();
    public Body getBody();
    public Texture getTexture();
}
