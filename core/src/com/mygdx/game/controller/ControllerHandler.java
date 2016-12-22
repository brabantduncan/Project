package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.player.Player;

import constants.XboxPad;

import java.util.ArrayList;

/**
 * Created by Shan on 12/4/2016.
 */
public class ControllerHandler {


    private Camera camera;
    //Controller c;
    Boolean hasController;

    BulletManager bulletManager;
    EnemyManager enemyManager;


    public ControllerHandler(Camera camera, BulletManager bulletManager,EnemyManager enemyManager) {

        //this.c= c;
        // this.playState = p;
        hasController = false;
        this.camera = camera;
        this.bulletManager = bulletManager;
        this.enemyManager = enemyManager;

    }

    public void handleInputKeybord(Player player) {


        int horizontalForce = 0;
        int verticalForce = 0;

        updatePlayerRotation(getMouseCoords(), player);

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
            if(player.isHasBomb()){
                System.out.println("destroying all");
                enemyManager.destroyAllPeasants();
                player.setHasBomb(false);
            }
        }

        if (Gdx.input.isTouched()) {

            bulletManager.addBullet(getMouseCoords(), BodyBuilder.getInstance().createBulletBody(player.getPlayerBody().getPosition()));

        }


        player.getPlayerBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);


    }


    public void moveWithController(Controller controller, Player player) {

        updateCrosshairOrientation(player.getController().getCrosshair().getPosition(), player);

        int horizontalForcePlayer = getHorizontalForce(controller);
        int verticalForcePlayer = getVerticalForce(controller);
        player.getPlayerBody().setLinearVelocity(verticalForcePlayer* 5, horizontalForcePlayer* 5);

        int horizontalForceCrosshair = getHorizontalForceCrosshair(controller);
        int verticalForceCrosshair = getVerticalForceCrosshair(controller);
        player.getController().getCrosshair().update(horizontalForceCrosshair,verticalForceCrosshair);
        System.out.println("De player is nu op punt: "+player.getPlayerBody().getPosition());
        System.out.println("De crosshair is nu op punt: "+player.getController().getCrosshair().getPosition());

        if (controller.getAxis(XboxPad.BUTTON_B) ==1){
            if(player.isHasBomb()){
                System.out.println("destroying all");
                enemyManager.destroyAllPeasants();
                player.setHasBomb(false);
            }
        }

        if (controller.getAxis(XboxPad.AXIS_RIGHT_TRIGGER) < 0) {
            System.out.println(controller.getAxis(XboxPad.AXIS_RIGHT_TRIGGER) );
            Vector2 direction = player.getController().getCrosshair().getBody().getWorldCenter();
            System.out.println(direction);

           // bulletManager.addBulletController(player.getPlayerBody().getWorldCenter(),
            //        direction,BodyBuilder.getInstance().createBulletBody(player.getPlayerBody().getPosition()));
            //bulletManager.addBullet(direction,BodyBuilder.getInstance().createBulletBody(player.getPlayerBody().getPosition()));

            bulletManager.addBulletController(player.getPlayerBody().getPosition(),
                            direction,BodyBuilder.getInstance().createBulletBody(player.getPlayerBody().getPosition()));

        }




    }


    public void giveControles(ArrayList<Player> players) {
        System.out.print("\nThere are " + players.size());
        for (int i = 0; i < players.size(); i++) {
            if (i != 0) {
                players.get(i).setController(new ControllerSet(players.get(i).getPlayerBody(),Controllers.getControllers().get(i-1)));
            }

        }
    }

    public void handleInput(ArrayList<Player> players) {
        for (Player p : players) {
            if (p.getController() != null) {
                moveWithController(Controllers.getControllers().get(0), p);
            } else {
                handleInputKeybord(p);
            }

        }
    }

    public Vector2 getMouseCoords() {
        int xmouse = Gdx.input.getX();
        int ymouse = Gdx.input.getY();

        return new Vector2(xmouse, ymouse);
    }


    public void updatePlayerRotation(Vector2 pointer, Player player) {

        Vector3 sp3 = camera.unproject(new Vector3(pointer.x, pointer.y, 0));
        Vector2 sp2 = new Vector2(sp3.x, sp3.y);

        Vector2 a = player.getPlayerBody().getPosition();
        Vector2 d = sp2.sub(a);

        player.getPlayerBody().setTransform(player.getPlayerBody().getPosition(), d.angleRad());
        //System.out.println(player.getPlayerBody().getPosition());
    }

    public void updateCrosshairOrientation(Vector2 pointer, Player player){






       // player.getPlayerBody().setTransform(player.getPlayerBody().getPosition(), (float) angleToTurn);

    }





    public int getHorizontalForce(Controller controller){

        int horizontalForce = 0;


        if (controller.getAxis(XboxPad.AXIS_LEFT_Y) > 0.2) {
            horizontalForce = -50;
        }
        if (controller.getAxis(XboxPad.AXIS_LEFT_Y) < -0.2) {
            horizontalForce = 50;
        }

        return  horizontalForce;



    }


    public int getVerticalForce(Controller controller){


        int verticalForce = 0;

        if (controller.getAxis(XboxPad.AXIS_LEFT_X) > 0.2) {
            verticalForce = +50;
        }
        if (controller.getAxis(XboxPad.AXIS_LEFT_X) < -0.2) {
            verticalForce = -50;
        }
        return  verticalForce;



    }

    public int getHorizontalForceCrosshair(Controller controller){

        int horizontalForce = 0;


        if (controller.getAxis(XboxPad.AXIS_RIGHT_Y) > 0.2) {
            horizontalForce = -50;
        }
        if (controller.getAxis(XboxPad.AXIS_RIGHT_Y) < -0.2) {
            horizontalForce = 50;
        }

        return  horizontalForce;



    }


    public int getVerticalForceCrosshair(Controller controller){


        int verticalForce = 0;

        if (controller.getAxis(XboxPad.AXIS_RIGHT_X) > 0.2) {
            verticalForce = +50;
        }
        if (controller.getAxis(XboxPad.AXIS_RIGHT_X) < -0.2) {
            verticalForce = -50;
        }
        return  verticalForce;



    }



    /**
     *
     Vector2 angleVector = new Vector2(0, 0);//angleV;

     if (controller.getAxis(XboxPad.AXIS_RIGHT_X) > 0.1 || controller.getAxis(XboxPad.AXIS_RIGHT_X) < -0.1) {
     angleVector = new Vector2(controller.getAxis(XboxPad.AXIS_RIGHT_X), angleVector.y);
     }
     if (controller.getAxis(XboxPad.AXIS_RIGHT_Y) < -0.1 || controller.getAxis(XboxPad.AXIS_RIGHT_Y) > 0.1) {
     angleVector = new Vector2(angleVector.x, -controller.getAxis(XboxPad.AXIS_RIGHT_Y));
     }
     */


}


