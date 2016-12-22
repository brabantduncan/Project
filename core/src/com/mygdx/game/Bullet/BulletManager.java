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
    Camera camera;

    public BulletManager(Camera camera) {

        bullets = new ArrayList<Bullet>();
        disposeBullets = new ArrayList<Bullet>();
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

    public void addBulletController(Vector2 origin,Vector2 locationAim,Body body ){
        Bullet bullet = new Bullet(body);
        bullets.add(bullet);

        int bulletSpeed = Constants.BULLET_SPEED;

        /**

        double angle = Math.atan2(locationAim.y, locationAim.x) - Math.atan2(origin.y, origin.x);

        float magnitude = 800F;
        float xVel = (float) (Math.cos(angle)*magnitude);
        float yVel = (float) (Math.sin(angle)*magnitude);

        Vector2 vel = new Vector2(xVel,yVel);
        System.out.println(vel);
        //bullet.getB().applyLinearImpulse(vel,bullet.getB().getPosition(),false);
        bullet.getB().setLinearVelocity(vel);*/


        /***
        Vector2 sp2 = new Vector2((locationAim.x -origin.x) *360, (locationAim.y -origin.y)*360);

        Vector2 a = bullet.getB().getPosition();
        Vector2 d = sp2.sub(a);

        bullet.getB().applyLinearImpulse(d.x * bulletSpeed, d.y * bulletSpeed, bullet.getB().getPosition().x, bullet.getB().getPosition().y, true);
        */

        /**


        float angle = (float) (Math.atan2(locationAim.y,locationAim.x) - Math.atan2(origin.y, origin.x));
        Double dx = Math.cos(angle);
        Double dy = Math.sin(angle);

        float newX = (float) (body.getPosition().x+dx)*bulletSpeed;
        float newY = (float) (body.getPosition().y+dy)*bulletSpeed;

        **/

        /**
        System.out.println("Begin positie "+origin);
        System.out.println("Eind positie "+locationAim);


        float pointVectorY = (locationAim.y - origin.y)*bulletSpeed;
        float pointVectorX = (locationAim.x - origin.y) *bulletSpeed;

        float angle = (float) Math.atan2(pointVectorY, pointVectorX);


        System.out.println("De vector hiervan is "+new Vector2(pointVectorX,pointVectorY));


        body.applyForceToCenter(new Vector2(pointVectorX,pointVectorY),true);
        //body.applyLinearImpulse(new Vector2(pointVectorX,pointVectorY), body.getWorldCenter(),true);
        // body.setLinearVelocity(finalVelx,finalVely);
        **/

        float velx = locationAim.x - origin.x;
        float vely = locationAim.y - origin.y;
        float length = (float) Math.sqrt(velx * velx + vely * vely);
        if (length != 0) {
            velx = velx / length;
            vely = vely / length;
        }
        float finalVelx = velx * bulletSpeed;
        float finalVely = vely * bulletSpeed;

        body.setLinearVelocity(finalVelx,finalVely);
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
