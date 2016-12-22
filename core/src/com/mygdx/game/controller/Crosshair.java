package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.body.BodyBuilder;

/**
 * Created by Shan on 12/21/2016.
 */
public class Crosshair {


    private float x;
    private float y;

    public Body getBody() {
        return body;
    }

    private Body body;

    public Texture getTex() {
        return tex;
    }



    private Texture tex;


    public Crosshair(Body playerBody){

        setXY(playerBody.getPosition());
        this.body = BodyBuilder.getInstance().createCrossHairBody(new Vector2(x,y));

    }

    public void setTex(String location){
        tex = new Texture(location);
    }

    private void setXY(Vector2 position){
        x = position.x;
        y = position.y;
    }


    public Vector2 getPosition(){
        return new Vector2(x,y);
    }


    public void setPosition(Vector2 position) {
        this.x = position.x;
        this.x = position.x;
    }

    public void update(float horizontalForce, float verticalForce){
        body.setLinearVelocity(verticalForce* 5, horizontalForce* 5);
        setPosition(body.getPosition());
    }

    public void destroyBody(){
        BodyBuilder.getInstance().addToDestroy(body);
    }




}
