package com.mygdx.game;
/**
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Created by laurens on 10/11/2016.
 */
/**
public class Bullet {

    private int SPEED;
    private Texture text;
    private float x, y;
    public boolean remove = false;
    private Body body;


    public Bullet(float x, float y,Body b){
        this.x = x;
        this.y = y;
        this.SPEED = 80;
        body = b;
        body.isBullet();


        if (text == null) text = new Texture("badlogic.jpg");


    }


    public void update(float deltaTime){
        y += SPEED * deltaTime;
        //System.out.println("height; " + Gdx.graphics.getHeight());
        if(y > Gdx.graphics.getHeight()) remove = true; // beter zou zijn om te verwijderen wnr hij zover van de speler is??
    }


    public void render(SpriteBatch batch){
        //System.out.println(x +"  "+y);
        batch.draw(text, x, y, 10, 10);

    }




}
**/