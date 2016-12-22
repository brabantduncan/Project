package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import database.projectDB;

import java.sql.SQLException;

;


/**
 * Created by Laurens Druwel on 20/12/2016.
 */
public class LoginMultiPlayer2 extends State {
    public SpriteBatch batch;
    private Stage stage;
    private Texture background;
    private Skin skin;




    public LoginMultiPlayer2(final GameStateManager gsm, final String username1){
        super(gsm);
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("../assets/background.jpg"));
        skin = new Skin(Gdx.files.internal("../assets/data/uiskin.json"), new TextureAtlas(Gdx.files.internal("../assets/data/uiskin.atlas")));
        Gdx.input.setInputProcessor(stage);


        Label loginLabel = new Label("Login", skin);
        loginLabel.setPosition(stage.getWidth() /2 - (loginLabel.getWidth() /2 ), 800);
        stage.addActor(loginLabel);

        Label usernameLabel = new Label("Username", skin);

        final TextField usernameInput = new TextField("", skin);
        usernameInput.setPosition(stage.getWidth() / 2, 550);

        usernameInput.setMessageText("<Username>");

        usernameInput.setColor(Color.WHITE);

        Label passwordLabel = new Label("Password", skin);

        final TextField passwordInput = new TextField("", skin);
        passwordInput.setPasswordMode(true);
        passwordInput.setMessageText("<Password>");
        passwordInput.setColor(Color.WHITE);


        TextButton loginButton = new TextButton("login", skin);
        loginButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                try {
                    if(projectDB.getInstance().loginCheck(username, password)) gms.set(new OptionState2(gms, username1, username)); // get login
                } catch (SQLException e) {
                    stage.addActor(getDialog("Unable to retrieve username/password \n please check if typed correctly"));
                }
            }});
        stage.addActor(loginButton);


        TextButton createAccountButton = new TextButton("create new account", skin);
        createAccountButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final Dialog createAccountdlg = new Dialog("Create account", skin);
                createAccountdlg.setSize(505, 80);
                createAccountdlg.setPosition(stage.getWidth() / 2 -(createAccountdlg.getWidth() / 2), stage.getHeight() / 2);
                createAccountdlg.setMovable(false);

                Label usernameLabel = new Label("Username", skin);
                createAccountdlg.add(usernameLabel);

                final TextField usernameInput = new TextField("", skin);
                usernameInput.setMessageText("<Username>");
                usernameInput.setColor(Color.WHITE);
                createAccountdlg.add(usernameInput);

                Label passwordLabel = new Label("Password", skin);
                createAccountdlg.add(passwordLabel);

                final TextField passwordInput = new TextField("", skin);
                passwordInput.setMessageText("<Password>");
                passwordInput.setColor(Color.WHITE);
                createAccountdlg.add(passwordInput);




                TextButton okButton = new TextButton("Ok", skin);
                okButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        String username = usernameInput.getText();
                        String password = passwordInput.getText();
                        try {
                            projectDB.getInstance().addPlayer(username, password);
                        } catch (SQLException e) {
                            stage.addActor(getDialog("Unable to retrieve username/password \n please check if typed correctly"));
                        }
                        gms.set(new OptionState2(gms, username, username1));
                    }
                });
                createAccountdlg.add(okButton);
                stage.addActor(createAccountdlg);
            }
        });
        stage.addActor(createAccountButton);

        TextButton backButton = new TextButton("Back", skin);
        backButton.setPosition(1200, 100);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new MenuState(gsm));
            }
        });

        stage.addActor(backButton);




        Table table = new Table();
        table.add(usernameLabel).width(100).pad(20);
        table.add(usernameInput);
        table.row();
        table.add(passwordLabel).width(100).pad(20);
        table.add(passwordInput);
        table.row();
        table.add(loginButton);
       //table.row();
        table.add(createAccountButton);
        table.setPosition(stage.getWidth()/2 - (table.getWidth() /2 ),stage.getHeight()/2);
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
        batch.draw(background, 0, 0);

        batch.end();

        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
    }

    public Dialog getDialog(String msg){
        final Dialog dlg = new Dialog("Error", skin);
        dlg.setSize(400, 80);
        dlg.setPosition(stage.getWidth() / 2 -(dlg.getWidth() / 2), stage.getHeight() / 2);
        dlg.setMovable(false);
        TextButton okButton = new TextButton("Ok", skin);
        okButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                dlg.remove();}});
        Label errorLabel = new Label("Unable to retrieve username/password \n please check if typed correctly", skin);
        dlg.add(errorLabel);
        dlg.add(okButton);

        return dlg;
    }





}



