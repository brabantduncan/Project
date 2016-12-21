package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.player.Player;
import com.mygdx.game.states.PlayState;
import constants.XboxPad;

import java.util.ArrayList;

/**
 * Created by Shan on 12/4/2016.
 */
public class ControllerHandler {


    //Controller c;
    Boolean hasController;
    PlayState playState;
    ArrayList<Controller> controllers;


    public ControllerHandler() {

        //this.c= c;
        // this.playState = p;
        hasController = false;

    }

    public void handleInputKeybord(PlayState playState, Player player) {


        int horizontalForce = 0;
        int verticalForce = 0;


        if (Gdx.input.isKeyPressed(Input.Keys.Q) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 50;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 50;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            verticalForce -= 50;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            verticalForce += 50;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            player.getPlayerBody().setTransform(0, 0, 0);

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();


        if (Gdx.input.isTouched()) {

            playState.createBullet(playState.getMouseCoords(), player);

        }


        player.getPlayerBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);


    }


    public void moveWithController(Controller controller, Player player) {


        int horizontalForce = 0;
        int verticalForce = 0;

        if (controller.getAxis(XboxPad.AXIS_LEFT_Y) > 0.2) {
            horizontalForce = -50;
        }
        if (controller.getAxis(XboxPad.AXIS_LEFT_Y) < -0.2) {
            horizontalForce = 50;
        }
        if (controller.getAxis(XboxPad.AXIS_LEFT_X) > 0.2) {
            verticalForce = +50;
        }
        if (controller.getAxis(XboxPad.AXIS_LEFT_X) < -0.2) {
            verticalForce = -50;
        }

        player.getPlayerBody().setLinearVelocity(verticalForce * 5, horizontalForce * 5);


        Vector2 angleVector = new Vector2(0, 0);//angleV;

        if (controller.getAxis(XboxPad.AXIS_RIGHT_X) > 0.1 || controller.getAxis(XboxPad.AXIS_RIGHT_X) < -0.1) {
            angleVector = new Vector2(controller.getAxis(XboxPad.AXIS_RIGHT_X), angleVector.y);
        }
        if (controller.getAxis(XboxPad.AXIS_RIGHT_Y) < -0.1 || controller.getAxis(XboxPad.AXIS_RIGHT_Y) > 0.1) {
            angleVector = new Vector2(angleVector.x, -controller.getAxis(XboxPad.AXIS_RIGHT_Y));
        }

        player.getPlayerBody().setTransform(player.getPlayerBody().getPosition(), angleVector.nor().angleRad());

        /**
         if (controller.getAxis(XboxPad.AXIS_RIGHT_TRIGGER) != 0 ){

         playState.createBullet(playState.getMouseCoords());


         System.out.print("\nShooting");
         System.out.print(angleVector);
         playState.createBullet(player.getPlayerBody().getAngle());

         }
         **/


    }


    public void giveControles(ArrayList<Player> players) {
        System.out.print("\nThere are " + players.size());
        for (int i = 0; i < players.size(); i++) {
            if (i != 0) {
                players.get(i).setController(Controllers.getControllers().get((i - 1)));
            }

        }
    }

    public void handleInput(ArrayList<Player> players, PlayState playState) {
        for (Player p : players) {
            if (p.getController() != null) {
                moveWithController(Controllers.getControllers().get(0), p);
            } else {
                handleInputKeybord(playState, p);
            }
        }
    }
}


