package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bonus.BonusInterface;
import com.mygdx.game.Bullet.Bullet;
import com.mygdx.game.controller.ControllerSet;
import com.mygdx.game.enemy.EnemyInterface;
import com.mygdx.game.follower.FollowerInterface;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

/**
 * Created by Shan on 12/17/2016.
 */
public class RenderHandler {


    public RenderHandler() {

    }


    public void renderPlayer(SpriteBatch batch, ArrayList<Player> players) {

        for (Player player : players) {

            batch.draw(player.getTexture(), player.getPlayerBody().getPosition().x - (player.getTexture()
                    .getWidth() / 2), player.getPlayerBody().getPosition().y - (player.getTexture().getHeight() / 2));



            if (player.getFollower() != null) {
                renderFollower(batch, player.getFollower());
            }

            if (player.getController() != null) {
                renderController(batch, player.getController());
            }


        }
    }

    private void renderController(SpriteBatch batch, ControllerSet controller) {
        Texture t = controller.getCrosshair().getTex();
        Body crossBody = controller.getCrosshair().getBody();
        batch.draw(t, crossBody.getPosition().x - (t.getWidth() / 2),crossBody.getPosition().y - (t.getHeight() / 2));
    }

    public void renderEnemies(SpriteBatch batch, ArrayList<EnemyInterface> enemies) {

        for (EnemyInterface e : enemies) {
            Texture t = e.getTexture();
            batch.draw(t, e.getBody().getPosition().x - (t.getWidth() / 2), e.getBody().getPosition().y
                    - (t.getHeight() / 2));
        }
    }

    public void renderBonus(SpriteBatch batch, ArrayList<BonusInterface> bonus) {

        for (BonusInterface b : bonus) {
            Texture t = b.getTexture();
            batch.draw(t, b.getBody().getPosition().x - (t.getWidth() / 2), b.getBody().getPosition().y - (t.getHeight() / 2));
        }

    }

    public void renderBullets(SpriteBatch batch, ArrayList<Bullet> bullets) {

        for (Bullet b : bullets) {
            Texture t = b.getTexture();
            batch.draw(t, b.getB().getPosition().x - (t.getWidth() / 2), b.getB().getPosition().y - (t.getHeight() / 2));
        }

    }

    private void renderFollower(SpriteBatch batch, FollowerInterface follower) {
        batch.draw(follower.getTexture(), follower.getBody().get(0).getPosition().x - (follower.getTexture().getWidth() / 2), follower.getBody().get(0).getPosition().y - (follower.getTexture().getHeight() / 2));

        // batch.draw(texture,player.getPlayerBody().getPosition().x -(texture.getWidth()/2),player.getPlayerBody().getPosition().y-(texture.getHeight()/2));

    }
}


