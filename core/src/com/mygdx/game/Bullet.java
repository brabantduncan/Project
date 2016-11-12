package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Created by laurens on 10/11/2016.
 */
public class Bullet {

    private int SPEED;
    private Texture text;
    private float x, y;
    public boolean remove = false;


    public Bullet(float x, float y){
        this.x = x;
        this.y = y;
        this.SPEED = 80;


        if (text == null) text = new Texture("badlogic.jpg");


    }


    public void update(float deltaTime){
        y += SPEED * deltaTime;
        System.out.println("height; " + Gdx.graphics.getHeight());
        if(y > Gdx.graphics.getHeight()) remove = true; // beter zou zijn om te verwijderen wnr hij zover van de speler is??
    }

    public void render(SpriteBatch batch){
        System.out.println(x +"  "+y);
        batch.draw(text, x, y, 10, 10);

    }

}
