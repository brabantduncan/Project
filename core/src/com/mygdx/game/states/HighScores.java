package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import database.projectDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Laurens Druwel on 20/12/2016.
 */
public class HighScores extends State {
    public SpriteBatch batch;
    private Stage stage;
    private Texture background;
    private Skin skin;
    private ArrayList<HashMap<String, String>> highscores;
    private GameStateManager gsm;

    public HighScores(final GameStateManager gms){
        super(gms);
        gsm = gms;
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        skin = new Skin(Gdx.files.internal("../assets/data/uiskin.json"), new TextureAtlas(Gdx.files.internal("../assets/data/uiskin.atlas")));
        Gdx.input.setInputProcessor(stage);

        final Table table = getTable(skin);

        Label highscoresLabel = new Label("Highscores", skin);
        highscoresLabel.setPosition(stage.getWidth() /2 - (highscoresLabel.getWidth() / 2 ), 800);
        stage.addActor(highscoresLabel);

        TextButton backButton = new TextButton("Back", skin);
        backButton.setPosition(1200, 100);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.push(new MenuState(gsm));
            }
        });
        stage.addActor(backButton);

        stage.addActor(table);
        getHighScores();



    }


        final Label usernames = new Label("Usernames", skin);


    public ArrayList<HashMap<String, String>> getHighScores(){

        try {
            highscores = new ArrayList<HashMap<String, String>>(projectDB.getInstance().getHighScores());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error loading highscores");

        }
        return highscores;
    }

    public Table getTable(Skin skin){
        highscores = getHighScores();
        final Label username = new Label("Usernames", skin);
        username.setAlignment(Align.center);
        username.setWrap(false);


        final Label score = new Label("Score", skin);
        username.setAlignment(Align.center);
        username.setWrap(false);
        Table table = new Table(skin);

        table.add(username).width(100).pad(10);
        table.add(score).width(100).pad(10);

        for(HashMap<String, String> highscore : highscores){
            table.row();
            table.add(highscore.get("username"));
            table.add(highscore.get("score"));

        }


        table.setPosition(stage.getWidth() / 2 - (table.getWidth() / 2), stage.getHeight() / 2);
        table.setSize(table.getWidth(), table.getHeight());


        return table;


    }
    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
    batch.begin();
    batch.draw(background, 0, 0);
    batch.end();
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
    }


}
