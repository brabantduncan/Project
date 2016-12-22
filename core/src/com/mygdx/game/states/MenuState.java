package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by Shan on 11/27/2016.
 */
public class MenuState extends State {
    // private Texture background;
    //private Texture button;
    public SpriteBatch batch;
    private Stage stage;
    private TextButton singleplayerButton, multiplayerButton, highscoreButton;
    private Texture background;
    private Skin skin;
    private GameStateManager gsm;



    public MenuState(final GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        skin = new Skin(Gdx.files.internal("../assets/data/uiskin.json"), new TextureAtlas(Gdx.files.internal("../assets/data/uiskin.atlas")));
        Gdx.input.setInputProcessor(stage);

        singleplayerButton = new TextButton("SINGLEPLAYER",skin);
        singleplayerButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                singleplayerButton.setText("Starting new game");
                gsm.set(new Login(gsm));
            }
        });


        multiplayerButton = new TextButton("MULTIPLAYER", skin);
        multiplayerButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                gsm.set(new LoginMultiPlayer1(gsm));
            }
        });

        highscoreButton = new TextButton("HIGHSCORES", skin);
        highscoreButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {

                singleplayerButton.setText("Starting new game");
                gsm.set(new HighScores(gsm));
            }
        });

        Label menuLabel = new Label("Menu", skin);
        menuLabel.setPosition(stage.getWidth() /2 - (menuLabel.getWidth() / 2), 800);
        stage.addActor(menuLabel);



        Table table = new Table(skin);
        table.add(singleplayerButton).padBottom(80);
        table.row();
        table.add(multiplayerButton).padBottom(80);
        table.row();
        table.add(highscoreButton).padBottom(80);
        table.setPosition(stage.getWidth() /2 - (table.getWidth() /2), 400);
        stage.addActor(table);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

        handleInput();

    }



    public void render() {
        batch.begin();
        batch.draw(background, 0, 0);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        batch.end();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
    }
}

