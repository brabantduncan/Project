package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Bullet.Bullet;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.states.PlayState;

/**
 * Created by Shan on 11/30/2016.
 */
public class CollisionDetector implements com.badlogic.gdx.physics.box2d.ContactListener {

    PlayState p;

    public CollisionDetector(PlayState p){
        this.p= p;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        WorldManifold manifold = contact.getWorldManifold();
        for (int i =0; i< manifold.getNumberOfContactPoints();i++){

            if(contact.getFixtureA().getUserData() instanceof Bullet){
                contact.setEnabled(false);
            }
            if(contact.getFixtureB().getUserData() instanceof Bullet){
                contact.setEnabled(false);
            }
        }

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        checkClause(fa.getBody(),fb.getBody());

    }


    public void checkClause(Body b1, Body b2){

        if ((b1.getUserData() instanceof Player && b2.getUserData() instanceof Enemy) || (b1.getUserData() instanceof Enemy && b2.getUserData() instanceof Player)) {

            handlePlayerEnemy();

        }

        if ((b1.getUserData() instanceof Bullet && !(b2.getUserData() instanceof Player)) || (!(b1.getUserData() instanceof Player) && b2.getUserData() instanceof Bullet)) {
            p.removeBullet(b1,b2);
            if(b1.getUserData() instanceof Enemy || b2.getUserData() instanceof Enemy){
                p.removeEnemies(b1,b2);
                p.getPlayer().increaseCurrentScore(10);
                System.out.print(p.getPlayer().getCurrentScore()+"\n");
            }


        }




    }

    public void handlePlayerEnemy(){
        p.getPlayer().kill();
        p.setDestroy(true);

    }
}
