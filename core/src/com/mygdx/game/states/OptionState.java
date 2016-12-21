package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import database.projectDB;

import java.sql.SQLException;

/**
 * Created by Laurens Druwel on 21/12/2016.
 */
public class OptionState extends State {
    public SpriteBatch batch;
    private Stage stage;
    private Texture background;
    private Skin skin;
    public Texture petSprite;
    private TextArea description;

    public OptionState(GameStateManager gsm){
        super(gsm);
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        skin = new Skin(Gdx.files.internal("../assets/data/uiskin.json"), new TextureAtlas(Gdx.files.internal("../assets/data/uiskin.atlas")));
        Gdx.input.setInputProcessor(stage);

        loadDifficulty(stage);
        loadPet(stage);

    }



    public void loadDifficulty(Stage stage){
        Label playerLabel = new Label("<PlayerName>", skin);
        playerLabel.setPosition(stage.getWidth()/2 - (playerLabel.getWidth() /2),800);
        stage.addActor(playerLabel);

        Label difficultyLabel = new Label("Difficulty", skin);
        String[] difficulties = {"EASY", "NORMAL", "HARD"};

        final SelectBox<String> difficulty = new SelectBox<String>(skin);
        difficulty.setItems(difficulties);


        Table table = new Table();
        table.add(difficultyLabel).padRight(50);
        table.add(difficulty);

        table.setPosition(stage.getWidth()/2 - (table.getWidth() /2),stage.getHeight()/2 + 100);

        stage.addActor(table);
    }

    public void loadPet(final Stage stage) {
        petSprite = new Texture("../assets/Monsters/frontwatcher.png");
        try {
            description = new TextArea(projectDB.getInstance().getDescription("Frontwatcher"),skin);
            description.setPosition(stage.getWidth() / 2 + 50, 310);
            description.setWidth(400);
            description.setDisabled(true);
            stage.addActor(description);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Label petLabel = new Label("Select pet", skin);
        String[] pets = {"Frontwatcher", "Shielder"};

        final SelectBox<String> pet = new SelectBox<String>(skin);
        pet.setItems(pets);
        pet.setSelected("FrontWatcher");
        pet.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(pet.getSelected().toLowerCase());
                petSprite = new Texture("../assets/Monsters/"+ pet.getSelected().toLowerCase()+".png");
                try {
                    description.remove();
                    description = new TextArea(projectDB.getInstance().getDescription(pet.getSelected()),skin);
                    description.setPosition(stage.getWidth() / 2 + 50, 310);
                    description.setWidth(400);
                    description.setDisabled(true);

                    stage.addActor(description);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Table table = new Table();
        table.add(petLabel).padRight(50);
        table.add(pet);
        table.setPosition(stage.getWidth()/2 - (table.getWidth() /2),stage.getHeight()/2);


        stage.addActor(table);


    }


















    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.begin();
        batch.draw(petSprite, stage.getWidth() / 2 - 50, 300, 64, 64);
        batch.end();
        stage.act();
    }

    @Override
    public void dispose() {

    }
}
