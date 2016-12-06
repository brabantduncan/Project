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


    Controller c;
    Boolean hasController;
    PlayState playState;


    public  ControllerHandler(PlayState p){

        this.c= c;
        this.playState = p;
        hasController = false;

    }

    public void handleInput(Player p, float delta){
/**
        if (hasController = false){
            int horizontalForce = 0;
            int verticalForce = 0;


            float playerX = p.getPlayerBody().getPosition().x;
            float playerY = p.getPlayerBody().getPosition().y;


            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                horizontalForce -= 50;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                horizontalForce += 50;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                verticalForce -= 50;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                verticalForce += 50;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                p.getPlayerBody().setTransform(0, 0, 0);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();


            if (Gdx.input.isTouched()) {

                playState.createBullet(playState.getMouseCoords(), delta);

            }


            p.getPlayerBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);


        }
    */
    }


}


