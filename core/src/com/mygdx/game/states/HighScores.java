package com.mygdx.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

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
    private String next;

    public HighScores(final GameStateManager gms){
        super(gms);
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        batch = new SpriteBatch();
        stage = new Stage();
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(200, 55, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));


        BitmapFont bfont=new BitmapFont();
        bfont.getData().scale(1);
        skin.add("default",bfont);

        LabelStyle ls = new LabelStyle();
        TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();



        ls.font = skin.getFont("default");
        tfs.font = skin.getFont("default");


        skin.add("default", ls);
        skin.add("default", tfs);





        Label nameLabel = new Label("Name:", skin);
        TextField nameText = new TextField("", skin);
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("jqlmdksjfm", skin);
        //nameText.setMessageText("testname");
        //addressText.setMessageText("testaddress");

//        Table table = new Table();
//        table.add(nameLabel);
//        table.add(nameText).width(100);
//        table.row();
//        table.add(addressLabel);
//        table.add(addressText).width(100);
//        table.setPosition(0,0);
//        Label label1 = new Label("test", skin);
//        //stage.addActor(label1);
//        table.setPosition(stage.getWidth()/2,stage.getHeight()/2);
//        //  stage.addActor(nameLabel);
//        stage.addActor(table);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.fontColor = Color.RED;
        style.font = new BitmapFont();
        TextField test = new TextField("",style);
        test.setMessageText("placeholder");
        test .setText("test");
        //textfield .setMessageText("test2"); //new line
        stage.addActor(test );


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
