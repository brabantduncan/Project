package com.mygdx.game.Bullet;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.player.Player;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import constants.Constants;


import java.util.ArrayList;

/**
 * Created by Shan on 12/1/2016.
 */
public class BulletManager {

    ArrayList<Bullet> bullets;


    ArrayList<Bullet> disposeBullets;
    Player p;
    Camera camera;

    public BulletManager(Player p, Camera camera) {

        bullets = new ArrayList<Bullet>();
        disposeBullets = new ArrayList<Bullet>();
        this.p = p;
        this.camera = camera;
    }

    public void addBullet(Vector2 mouseLocationAim, Body b) {
        Bullet bullet = new Bullet(b);
        bullets.add(bullet);
        handleBulletMovement(bullet, mouseLocationAim);

    }


    public void handleBulletMovement(Bullet b, Vector2 mouseCoords) {

        int bulletSpeed = Constants.BULLET_SPEED;
        Vector3 sp3 = camera.unproject(new Vector3(mouseCoords.x, mouseCoords.y, 0));
        Vector2 sp2 = new Vector2(sp3.x, sp3.y);

        Vector2 a = b.getB().getPosition();
        Vector2 d = sp2.sub(a);

        b.getB().applyLinearImpulse(d.x * bulletSpeed, d.y * bulletSpeed, b.getB().getPosition().x, b.getB().getPosition().y, true);
    }

    public void removeBullet(Body b) {

        for (Bullet bullet : bullets) {

            if (bullet.getB().equals(b)) {
                disposeBullets.add(bullet);
            }
        }
        bullets.removeAll(disposeBullets);
    }


    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void destroyBullets() {


        if (disposeBullets.size() > 0) {

            for (Bullet b : disposeBullets) {
                BodyBuilder.getInstance().addToDestroy(b.getB());
            }
            disposeBullets.clear();
        }
    }

}
