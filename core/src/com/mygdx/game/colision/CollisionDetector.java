package com.mygdx.game.colision;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.utils.random.GaussianDoubleDistribution;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Bonus.BonusInterface;
import com.mygdx.game.Bullet.Bullet;
import com.mygdx.game.enemy.DragonEnemy;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.enemy.EnemyInterface;
import com.mygdx.game.follower.FrontWatcherFollower;
import com.mygdx.game.follower.ShieldFollower;
import com.mygdx.game.player.Player;
import com.mygdx.game.states.GameInterface;

/**
 * Created by Shan on 11/30/2016.
 */
public class CollisionDetector implements com.badlogic.gdx.physics.box2d.ContactListener {

    GameInterface p;

    public CollisionDetector(GameInterface p) {
        this.p = p;
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
        for (int i = 0; i < manifold.getNumberOfContactPoints(); i++) {

            if (contact.getFixtureA().getUserData() instanceof Bullet) {
                contact.setEnabled(false);
            }
            if (contact.getFixtureB().getUserData() instanceof Bullet) {
                contact.setEnabled(false);
            }
        }

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        checkClause(fa.getBody(), fb.getBody());

    }

    public void checkClause(Body b1, Body b2) {


        handlePlayerShot(b1, b2);
        handlePlayerHitEnenmy(b1, b2);
        bulletHit(b1, b2);
        bonusHit(b1, b2);
        checkFollowerEnenmy(b1, b2);


    }

    private void bonusHit(Body b1, Body b2) {
        if (b1.getUserData() instanceof BonusInterface || b2.getUserData() instanceof BonusInterface) {
            handleBonusCollision(b1, b2);
        }
    }

    private void bulletHit(Body b1, Body b2) {
        if ((b1.getUserData() instanceof Bullet && !(b2.getUserData() instanceof Player))
                || (!(b1.getUserData() instanceof Player) && b2.getUserData() instanceof Bullet)) {

            p.removeBullet(b1, b2);
            checkBulletEnemy(b1, b2);
        }

    }

    public void checkFollowerEnenmy(Body b1, Body b2) {

        if (b1.getUserData() instanceof EnemyInterface || b2.getUserData() instanceof EnemyInterface) {


            if (b1.getUserData() instanceof FrontWatcherFollower || b2.getUserData() instanceof FrontWatcherFollower) {
                // p.getPlayer().destroyFollower();
            }
            if (b1.getUserData() instanceof ShieldFollower || b2.getUserData() instanceof ShieldFollower) {
                sendBody(b1, b2);
            }


        }


    }

    public void handlePlayerHitEnenmy(Body b1, Body b2) {
        if ((b1.getUserData() instanceof Player && b2.getUserData() instanceof EnemyInterface) ||
                (b1.getUserData() instanceof EnemyInterface&& b2.getUserData() instanceof Player)) {

            System.out.println("Taking damage from enenemy");
            p.getPlayer().stream().filter(player -> player.getPlayerBody().equals(b1)
                    || player.getPlayerBody().equals(b2)).forEach(player -> player.damage(5));

        }

    }

    public void sendBody(Body b1, Body b2) {
        if (b1.getUserData() instanceof EnemyInterface) {
            p.getEnemyManager().removeEnemy(b1);
        } else {
            p.getEnemyManager().removeEnemy(b2);
        }
    }

    public void checkBulletEnemy(Body b1, Body b2) {

        if (b1.getUserData() instanceof EnemyInterface || b2.getUserData() instanceof EnemyInterface) {


            p.removeEnemies(b1, b2);
            if (b1.getUserData() instanceof EnemyInterface) {

                p.addCoordToBonusHandler(b1.getPosition());

            }
            if (b1.getUserData() instanceof EnemyInterface) {
                p.addCoordToBonusHandler(b1.getPosition());


            }
        }

    }

    public void handleBonusCollision(Body b1, Body b2) {
        if (b1.getUserData() instanceof BonusInterface && b2.getUserData() instanceof Player) {

            p.getBonusHandler().setRemoveList(b2, b1);

        }
        if (b1.getUserData() instanceof Player && b2.getUserData() instanceof BonusInterface) {

            p.getBonusHandler().setRemoveList(b1, b2);
        }


    }

    public void handlePlayerShot(Body b1, Body b2) {

        if ((b1.getUserData() instanceof Player && b2.getUserData() instanceof Bullet) ||
                (b1.getUserData() instanceof Bullet && b2.getUserData() instanceof Player)){
            p.getPlayer().stream().filter(player -> player.getPlayerBody().equals(b1)
                    || player.getPlayerBody().equals(b2)).forEach(player -> player.damage(5));
        }
        p.removeBullet(b1, b2);


    }

}





