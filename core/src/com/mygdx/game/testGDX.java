package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.Login;
import com.mygdx.game.states.OptionState;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.PlayState;


/**
 * Created by Shan on 11/27/2016.
 */
public class testGDX implements ApplicationListener {

    public  static final  int WIDTH = 400;
    public  static final  int HEIGHT= 800;

    private GameStateManager gsm;
    private SpriteBatch batch;
    private Texture img;


    @Override
    public void create() {

        batch = new SpriteBatch();
        gsm = new GameStateManager();

        Gdx.gl.glClearColor(1,0,0,1);
        //gsm.push(new MenuState(gsm)); // start in menu
        //gsm.push(new PlayState(gsm));

        Gdx.gl.glClearColor(0,0,0,1);

        //gsm.push(new MenuState(gsm)); // start in menu
<<<<<<< HEAD
        gsm.push(new Login(gsm));

=======
<<<<<<< HEAD

        //gsm.push(new PlayState(gsm));
=======
>>>>>>> 70356379903cd58064c7147f37a3895e3e547ab8
        gsm.push(new OptionState(gsm));
>>>>>>> ae7536baff1fdf390a78689c641fb972c4d83111

<<<<<<< HEAD

        gsm.push(new PlayState(gsm));

        //gsm.push(new MenuState(gsm)); // start in menu
        gsm.push(new PlayState(gsm));

=======
>>>>>>> 70356379903cd58064c7147f37a3895e3e547ab8

    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {

    }
}
