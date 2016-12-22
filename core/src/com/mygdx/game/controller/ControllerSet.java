package com.mygdx.game.controller;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Shan on 12/21/2016.
 */
public class ControllerSet {

    private Controller controller;
    private Crosshair crosshair;

    public Controller getController() {
        return controller;
    }

    public Crosshair getCrosshair() {
        return crosshair;
    }


    public ControllerSet(Body playerBody, Controller controller){
        this.controller = controller;
        this.crosshair = new Crosshair(playerBody);
    }

    public void setCrosshairCoords(Vector2 newPosition) {
        crosshair.setPosition(newPosition);
    }




}
