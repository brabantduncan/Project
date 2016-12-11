package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


/**
 * Created by Duncan on 11/10/2016.
 */
public class PlayScreen implements Screen{
// shan je stinkt een beetje
    Stage stage;
    TextButton button;
    Skin skin;
    SpriteBatch batch;


   // private  MyGdxGame game;
    Texture text = new Texture("badlogic.jpg");


    public PlayScreen() {
    batch = new SpriteBatch();

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(text,0,0);
        batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}
