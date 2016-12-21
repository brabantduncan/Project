package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    private TextButton button_SinglePlayer, button_MuliPlayer, button_HighScores;
    private Texture background;
    private Skin skin;
    private String next;



    public MenuState(final GameStateManager gms) {
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

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        button_SinglePlayer = new TextButton("SINGLEPLAYER",textButtonStyle);
        button_SinglePlayer.setPosition(270 *2 + (button_SinglePlayer.getWidth() /2), 300 *2);
        stage.addActor(button_SinglePlayer);


        button_MuliPlayer = new TextButton("MULTIPLAYER", textButtonStyle);
        button_MuliPlayer.setPosition(270* 2+ (button_MuliPlayer.getWidth() /2), 230 * 2);
        stage.addActor(button_MuliPlayer);

        button_HighScores = new TextButton("HIGHSCORES", textButtonStyle);
        button_HighScores.setPosition(270 *2+ (button_HighScores.getWidth() /2), 160 *2);
        stage.addActor(button_HighScores);



        button_SinglePlayer.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                button_SinglePlayer.setText("Starting new game");
                next = "Game";
                gms.set(new Login(gms));
            }
        });

        button_MuliPlayer.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                button_SinglePlayer.setText("Starting new game");
                next = "mult";
                System.out.println(actor.getName());

            }
        });

        button_HighScores.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {

                button_SinglePlayer.setText("Starting new game");
                next = "high";
                gms.set(new HighScores(gms));


            }
        });
    }

    @Override
    public void handleInput() {
        //       if(Gdx.input.isTouched()){
        //
        //            gms.set(new PlayState(gms));
        //        }


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

