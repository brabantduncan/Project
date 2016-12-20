package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Bonus.BonusInterface;
import com.mygdx.game.Bullet.Bullet;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.follower.FollowerInterface;
import com.mygdx.game.player.Player;
import constants.Constants;

import java.util.ArrayList;

/**
 * Created by Shan on 12/17/2016.
 */
public class RenderHandler {


    public RenderHandler() {

    }

    //<<<<<<< HEAD
    public void renderPlayer(SpriteBatch batch, Texture texture, Player player) {


         //=======


         batch.draw(texture,player.getPlayerBody().getPosition().x -(texture.getWidth()/2),player.getPlayerBody().getPosition().y-(texture.getHeight()/2));
         //>>>>>>> b4f5f51513cbb818a2e03ec3104eee990a0495ba

        // batch.draw(texture,player.getPlayerBody().getPosition().x* Constants.SCALE -(texture.getWidth()/2),player.getPlayerBody().getPosition().y*Constants.SCALE-(texture.getHeight()/2));
         if (player.getFollower() !=null){
         renderFollower(batch,player.getFollower());
         }
         }

        public void renderEnemies (SpriteBatch batch, ArrayList < Enemy > enemies){

            for (Enemy e : enemies) {
                Texture t = e.getTexture();
                batch.draw(t, e.getBody().getPosition().x - (t.getWidth() / 2), e.getBody().getPosition().y - (t.getHeight() / 2));
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
        batch.draw(follower.getTexture(), follower.getBody().getPosition().x - (follower.getTexture().getWidth() / 2), follower.getBody().getPosition().y - (follower.getTexture().getHeight() / 2));
       // batch.draw(texture,player.getPlayerBody().getPosition().x -(texture.getWidth()/2),player.getPlayerBody().getPosition().y-(texture.getHeight()/2));

    }
}


