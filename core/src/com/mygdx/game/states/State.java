package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Bonus.BonusHandler;
import com.mygdx.game.player.Player;

/**
 * Created by Shan on 11/27/2016.
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gms;


    public State(GameStateManager gms){

        this.gms = gms;
        camera = new OrthographicCamera();
        mouse = new Vector3();


    }



    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render();

    public abstract void dispose();




}
