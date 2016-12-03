package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Shan on 11/9/2016.
 */
public class BodyBuilder {

    public Body createPlayer(World world, int x, int y, int width, int height, boolean isStatic, short isA) {

        Body pBody;
        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }
        def.position.set(x, y);
        def.fixedRotation = true; //hier kijken voor rotatie

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10,5);
        FixtureDef fdef = new FixtureDef();
        fdef.shape =shape;
        fdef.density =1000f;
        fdef.filter.categoryBits =  isA; //is a
        //fdef.filter.maskBits = collidesWith; // I will colide with


        return world.createBody(def).createFixture(fdef).getBody();
    }

    public Body createWall(World world, int x, int y, int width, int height, boolean isStatic, short isA, short collWith) {


        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }
        def.position.set(x, y);
        def.fixedRotation = true; //hier kijken voor rotatie

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width,height);
        FixtureDef fdef = new FixtureDef();
        fdef.shape =shape;
        fdef.density =1000f;
        fdef.filter.categoryBits =  isA;
        fdef.filter.maskBits = collWith;


        return world.createBody(def).createFixture(fdef).getBody();
    }

    public Body createEnemy(World world, Vector2 spawn, boolean isStatic, short isA, short collidesWith){

        Body pBody;
        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }
        def.position.set(spawn.x, spawn.y);
        def.fixedRotation = true; //hier kijken voor rotatie

        CircleShape shape = new CircleShape();
        shape.setRadius(5f);
        FixtureDef fdef = new FixtureDef();
        fdef.shape =shape;
        fdef.density =1000f;
        fdef.filter.categoryBits =  isA; //is a
        fdef.filter.maskBits = collidesWith; // I will colide with


        return world.createBody(def).createFixture(fdef).getBody();



    }

    public Body createBulletBody(World world, Vector2 spawn){

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawn.x, spawn.y);

        CircleShape shape = new CircleShape();
        shape.setRadius(1);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1.0f;
        fdef.friction=0;


        return world.createBody(bodyDef).createFixture(fdef).getBody();

    }



    //public createBorders

}
