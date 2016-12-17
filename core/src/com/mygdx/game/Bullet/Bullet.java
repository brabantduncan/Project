package com.mygdx.game.Bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Shan on 12/1/2016.
 */
public class Bullet {



    private Body b;
    private Texture texture = new Texture("../assets/MinecraftIngots/Laser_Shuriken.png");


    public Bullet(Body b){
        this.b = b;
        editBody();

    }

    public void editBody(){
        b.setUserData(this);
        b.isBullet();
    }

    public Body getB() {
        return b;
    }


    public Texture getTexture() {
        return texture;
    }
}
