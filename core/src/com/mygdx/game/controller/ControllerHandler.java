package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.mygdx.game.player.Player;
import com.mygdx.game.states.PlayState;

/**
 * Created by Shan on 12/4/2016.
 */
public class ControllerHandler {


    //Controller c;
    Boolean hasController;
    PlayState playState;


    public ControllerHandler() {

        //this.c= c;
        // this.playState = p;
        hasController = false;

    }

    public void handleInput(PlayState playState) {


        int horizontalForce = 0;
        int verticalForce = 0;


        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 50;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 50;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            verticalForce -= 50;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            verticalForce += 50;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            playState.getPlayer().getPlayerBody().setTransform(0, 0, 0);

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
            if(playState.getPlayer().isHasBomb()){
                playState.destroyAllPeasants();
                playState.getPlayer().setHasBomb(false);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();


        if (Gdx.input.isTouched()) {

            playState.createBullet(playState.getMouseCoords());

        }


        playState.getPlayer().getPlayerBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);


    }


}


