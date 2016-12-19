package com.mygdx.game.body;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Shan on 11/9/2016.
 */
public class BodyBuilder {


    private static BodyBuilder instance;
    private World world;

    private BodyBuilder(){

    }

    public static BodyBuilder getInstance(){
        if(instance == null){
            instance = new BodyBuilder();

        }
        return instance;
    }

    public void setWorld(World world){
        this.world = world;
    }

    public Body setLevelWall(int x, int y, int width, int height, boolean isStatic, short isA, short collWith) {
        this.world = world;

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

    public Body createPlayer(int x, int y, int width, int height, boolean isStatic, short isA) {

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
        shape.setAsBox(width,height);
        FixtureDef fdef = new FixtureDef();
        fdef.shape =shape;
        fdef.density =1000f;
        fdef.filter.categoryBits =  isA; //is a
        //fdef.filter.maskBits = collidesWith; // I will colide with


        return world.createBody(def).createFixture(fdef).getBody();
    }

    public Body createEnemy(Vector2 spawn, boolean isStatic, short isA, short collidesWith){

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

    public Body createBulletBody(Vector2 spawn){

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

    public Body createGemBody(Vector2 spawn){

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawn.x, spawn.y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1.0f;
        fdef.friction=0;
        fdef.filter.categoryBits = 4;
        fdef.filter.maskBits = 1;


        return world.createBody(bodyDef).createFixture(fdef).getBody();



    }

    public void destroyBody(Body body){
        world.destroyBody(body);
    }
}
