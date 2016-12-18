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
        System.out.print("handle input summoned+\n");

        int horizontalForce = 0;
        int verticalForce = 0;


        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            horizontalForce -= 50;
            System.out.print("A pressed+\n");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            horizontalForce += 50;
            System.out.print("D pressed+\n");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            verticalForce -= 50;
            System.out.print("S pressed+\n");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            verticalForce += 50;
            System.out.print("W pressed+\n");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            playState.getPlayer().getPlayerBody().setTransform(0, 0, 0);
            System.out.print("Enter pressed+\n");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();


        if (Gdx.input.isTouched()) {

            playState.createBullet(playState.getMouseCoords());

        }


        playState.getPlayer().getPlayerBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);


    }


}


