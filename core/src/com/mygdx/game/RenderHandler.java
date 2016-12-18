package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bonus.BonusInterface;
import com.mygdx.game.Bullet.Bullet;
import com.mygdx.game.enemy.Enemy;

import java.util.ArrayList;

/**
 * Created by Shan on 12/17/2016.
 */
public class RenderHandler {


    public RenderHandler(){

    }

    public void renderPlayer(Batch batch, Texture texture, Body playerBody){

        batch.draw(texture,playerBody.getPosition().x*2 -(texture.getWidth()/2),playerBody.getPosition().y*2-(texture.getHeight()/2));

    }

    public void renderEnemies(SpriteBatch batch, ArrayList<Enemy> enemies) {

        for (Enemy e: enemies){
            Texture t = e.getTexture();
            batch.draw(t,e.getBody().getPosition().x*2 - (t.getWidth()/2),e.getBody().getPosition().y*2-(t.getHeight()/2));
        }

    }

    public void renderBonus(SpriteBatch batch, ArrayList<BonusInterface> bonus){

        for (BonusInterface b: bonus){
            Texture t = b.getTexture();
            batch.draw(t,b.getBody().getPosition().x*2 - (t.getWidth()/2),b.getBody().getPosition().y*2-(t.getHeight()/2));
        }

    }

    public void renderBullets(SpriteBatch batch, ArrayList<Bullet> bullets){

        for (Bullet b: bullets){
            Texture t = b.getTexture();
            batch.draw(t,b.getB().getPosition().x*2 - (t.getWidth()/2),b.getB().getPosition().y*2-(t.getHeight()/2));
        }

    }
}