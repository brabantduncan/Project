package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screen.MenuScreen;

/**
 * Created by Shan on 11/27/2016.
 */
public class MenuState extends State {

    private Screen display;

    public MenuState(GameStateManager gms) {
        super(gms);
        display = new MenuScreen();

    }

    @Override
    public void handleInput() {
       if(Gdx.input.isTouched()){
            gms.set(new PlayState(gms));
        }


    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render() {

        display.render(Gdx.graphics.getDeltaTime());

    }

    @Override
    public void dispose() {

    }
}
