package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


/**
 * Created by Laurens Druwel on 20/12/2016.
 */
public class Login extends State {
    public SpriteBatch batch;
    private Stage stage;
    private TextButton button_SinglePlayer, button_MuliPlayer, button_HighScores;
    private Texture background;
    private Skin skin;



    public Login(GameStateManager gsm){
        super(gsm);
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



        TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();
        tfs.fontColor = Color.RED;
        tfs.font = new BitmapFont();

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
        tbs.fontColor = Color.RED;
        tbs.font = new BitmapFont();

        final Label.LabelStyle ls = new Label.LabelStyle();
        ls.fontColor = Color.RED;
        ls.font = new BitmapFont();


        Label login = new Label("Login", ls);
        login.setPosition(stage.getWidth() /2, 600);
        stage.addActor(login);

        Label usernameLabel = new Label("Username", ls);

        final TextField usernameInput = new TextField("", tfs);
        usernameInput.setPosition(stage.getWidth() / 2, 550);

        usernameInput.setMessageText("<Username>");

        usernameInput.setColor(Color.WHITE);

        Label passwordLabel = new Label("Password", ls);

        final TextField passwordInput = new TextField("", tfs);
        passwordInput.setMessageText("<Password>");
        passwordInput.setColor(Color.WHITE);

        TextButton loginButton = new TextButton("login", tbs);
        loginButton.setPosition(stage.getWidth() / 2 - (loginButton.getWidth() / 2), 420);
        loginButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                if( false /* user klopt in combinatie met passwoord*/){
                    gms.set(new PlayState(gms)); // get login
                }
                else{

                    skin = new Skin(Gdx.files.internal("../assets/data/uiskin.json"), new TextureAtlas(Gdx.files.internal("../assets/data/uiskin.atlas")));
                    final Dialog loginErrorDlg = new Dialog("Error", skin);
                    loginErrorDlg.setSize(400, 80);
                    loginErrorDlg.setPosition(stage.getWidth() / 2 -(loginErrorDlg.getWidth() / 2), stage.getHeight() / 2);
                    TextButton okButton = new TextButton("Ok", skin);
                    okButton.addListener(new ChangeListener() {
                        public void changed (ChangeEvent event, Actor actor) {
                        loginErrorDlg.remove();}});
                    Label errorLabel = new Label("Unable to retrieve username/password \n please check if typed correctly", skin);
                    loginErrorDlg.add(errorLabel);
                    loginErrorDlg.add(okButton);
                    stage.addActor(loginErrorDlg);
                }

            }});
        stage.addActor(loginButton);


        TextButton createAccountButton = new TextButton("create new account", tbs);
        createAccountButton.setPosition(stage.getWidth() / 2 - (createAccountButton.getWidth() / 2), 400);
        stage.addActor(createAccountButton);

       // stage.setKeyboardFocus(usernameInput);

        Table table = new Table();
        table.add(usernameLabel);
        table.add(usernameInput).width(100);
        table.defaults().spaceRight(30f);
        table.row();
        table.add(passwordLabel);
        table.add(passwordInput).width(100);
        table.setPosition(stage.getWidth()/2,stage.getHeight()/2);
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



        batch.begin();
        //batch.draw(background, 0, 0);

        batch.end();

        stage.draw();

    }

    @Override
    public void dispose() {

    }





}



