package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;



/**
 * Created by Shan on 11/10/2016.
 */
public class MenuScreen implements Screen {

    Stage stage;
    TextButton button;
    Skin skin;
    SpriteBatch batch;
    Texture background;
    String next;

    //private MyGdxGame game;


    public MenuScreen() {
        batch = new SpriteBatch();
        stage = new Stage();
        background = new Texture(Gdx.files.internal("background.jpg"));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(200, 55, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));


        BitmapFont bfont=new BitmapFont();
        bfont.getData().scale(1);
        skin.add("default",bfont);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        final TextButton textButton = new TextButton("SINGLEPLAYER",textButtonStyle);
        textButton.setPosition(290*2, 300*2);
        stage.addActor(textButton);


        final TextButton textButton2 = new TextButton("MULTIPLAYER", textButtonStyle);
        textButton2.setPosition(290*2, 230*2);
        stage.addActor(textButton2);

        final TextButton textButton3 = new TextButton("HIGHSCORES", textButtonStyle);
        textButton3.setPosition(290*2, 160*2);
        stage.addActor(textButton3);



        textButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                textButton.setText("Starting new game");
                next = "Game";
            }
        });

        textButton2.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                textButton.setText("Starting new game");
                next = "mult";
            }
        });

        textButton3.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {

                textButton.setText("Starting new game");
                next = "high";


            }
        });




    }


    @Override
    public void render (float delta) {


        batch.begin();
        batch.draw(background,0,0);
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();


    }

    @Override
    public void resize (int width, int height) {

    }

    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
        batch.dispose();
        
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }




    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }


}
