package com.mygdx.game.Bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Duncan on 23/12/2016.
 */
public class EnemyBullet implements BulletInterface{


    private Body b;
    private Texture texture = new Texture("../assets/MinecraftIngots/chicken.png");


    public EnemyBullet(Body b){
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
