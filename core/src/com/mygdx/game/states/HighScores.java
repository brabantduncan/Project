package com.mygdx.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import javax.xml.soap.Text;

/**
 * Created by Laurens Druwel on 20/12/2016.
 */
public class HighScores extends State {
    public SpriteBatch batch;
    private Stage stage;
    private TextButton button_SinglePlayer, button_MuliPlayer, button_HighScores;
    private Texture background;
    private Skin skin;

    public HighScores(final GameStateManager gms){
        super(gms);
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        skin = new Skin(Gdx.files.internal("../assets/data/uiskin.json"), new TextureAtlas(Gdx.files.internal("../assets/data/uiskin.atlas")));
        Gdx.input.setInputProcessor(stage);


        final Label usernames = new Label("Usernames", skin);
        usernames.setAlignment(Align.center);
        usernames.setWrap(false);


        final Label text = new Label(" private static final String reallyLongString = \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\"\n" +
                "        + \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\"\n" +
                "        + \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\";\n", skin);
        text.setAlignment(Align.center);
        text.setWrap(false);
        final Label text2 = new Label(" private static final String reallyLongString = \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\"\n" +
                "        + \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\"\n" +
                "        + \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\";\n", skin);
        text2.setAlignment(Align.center);
        text2.setWrap(false);
        final Label text3 = new Label(" private static final String reallyLongString = \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\"\n" +
                "        + \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\"\n" +
                "        + \"This\\nIs\\nA\\nReally\\nLong\\nString\\nThat\\nHas\\nLots\\nOf\\nLines\\nAnd\\nRepeats.\\n\";\n", skin);
        text3.setAlignment(Align.center);
        text3.setWrap(false);

        final Table scrollTable = new Table();
        scrollTable.add(usernames);
        scrollTable.add(text);
        scrollTable.row();
        scrollTable.add(text2);
        scrollTable.row();
        scrollTable.add(text3);
        ScrollPane sp = new ScrollPane(scrollTable, skin);

        final ScrollPane scroller = new ScrollPane(scrollTable);

        final Table table = new Table();
        //table.setFillParent(true);
        table.add(scroller).fill().expand();

        scroller.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
        scroller.setSize(300,300);

        stage.addActor(scroller );



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

    }
}
