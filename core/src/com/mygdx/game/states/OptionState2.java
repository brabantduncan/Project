package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
public class OptionState2 extends State {
    public SpriteBatch batch;
    private Stage stage;
    private Texture background;
    private Skin skin;
    public Texture petSprite;
    private TextArea description;
    private GameStateManager gsm;
    private String username1;
    private String username2;
    private String userpet1;
    private String userpet2;
    private String difficulty;

    public OptionState2(final GameStateManager gsm, final String username1, final String username2, final String userpet1, String difficulty){
        super(gsm);
        this.gsm = gsm;
        this.username1 = username1;
        this.username2 = username2;
        this.userpet1 = userpet1;
        this.difficulty = difficulty;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        skin = new Skin(Gdx.files.internal("../assets/data/uiskin.json"), new TextureAtlas(Gdx.files.internal("../assets/data/uiskin.atlas")));
        Gdx.input.setInputProcessor(stage);

        //loadDifficulty(stage);
        loadPet(stage);


        TextButton backButton = new TextButton("Back", skin);
        backButton.setPosition(1100, 100);
        final GameStateManager finalGsm = gsm;
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.push(new MenuState(gsm));
            }
        });
        stage.addActor(backButton);

        TextButton playGameButton = new TextButton("Play game", skin);
        playGameButton.setPosition(1200, 100);
        playGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String[] users = {username1, username2};
                String[] pets = {userpet1, userpet2};
                System.out.println(users.length);
                gsm.set(new PlayState(gsm, users, pets, difficulty));
            }
        });

        stage.addActor(playGameButton);

    }



    public void loadDifficulty(Stage stage){
        Label playerLabel = new Label("Player: " +username2, skin);
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
        String[] pets = {"Frontwatcher", "Shielder", "Minedo"};

        final SelectBox<String> pet = new SelectBox<String>(skin);
        pet.setItems(pets);
        pet.setSelected("FrontWatcher");
        userpet2 = pet.getSelected();
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
                    userpet2 = pet.getSelected();
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

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(petSprite, stage.getWidth() / 2 - 50, 300, 64, 64);
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
