package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Created by laurens on 10/11/2016.
 */
public class Bullet {
    Rectangle hitbox;
    float a, time;
    int speed;
    Texture text;

    public Bullet(int x, int y, float angle){
        time = 2;
        speed = 50;
        hitbox = new Rectangle(x, y, 10,10);
        text = new Texture("badlogic.jpg");

    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public void update(float delta){
        hitbox.x +=  speed *(int)Math.sin(a) * (int) delta;
        hitbox.y += speed * (int)Math.cos(a) * (int) delta;
        time -= delta;
    }
    public boolean isDead(){
        if(time < 0 ) return false;
        return false;

    }
    public void draw(SpriteBatch batch){
        batch.draw(text, hitbox.x, hitbox.y, 10, 10);
    }

}
