package com.mygdx.game.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import constants.Constants;

import java.util.ArrayList;

/**
 * Created by Shan on 11/9/2016.
 */
public class BodyBuilder {


    private static BodyBuilder instance;
    private World world;
    private ArrayList<Body> bodiesToDestroy;
    private ArrayList<Joint> jointsToDestroy;

    private BodyBuilder() {
        bodiesToDestroy = new ArrayList<Body>();
        jointsToDestroy = new ArrayList<Joint>();
    }

    public static BodyBuilder getInstance() {
        if (instance == null) {
            instance = new BodyBuilder();

        }
        return instance;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body setLevelWall(int x, int y, int width, int height, boolean isStatic) {


        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }
        def.position.set(x, y);
        def.fixedRotation = true; //hier kijken voor rotatie

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1000f;
        fdef.filter.categoryBits = Constants.Wall;
        fdef.filter.maskBits = Constants.PLAYER | Constants.GEM;


        return world.createBody(def).createFixture(fdef).getBody();
    }

    public Body createPlayer(int x, int y, int width, int height, boolean isStatic) {

        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }
        def.position.set(x, y);
        def.fixedRotation = true; //hier kijken voor rotatie

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 999999999999f;
        fdef.filter.categoryBits = Constants.PLAYER;//is a
        fdef.filter.maskBits = Constants.Enemy | Constants.Wall | Constants.GEM; // I will colide with


        return world.createBody(def).createFixture(fdef).getBody();
    }

    public Body createEnemy(Vector2 spawn, boolean isStatic) {

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
        fdef.shape = shape;
        fdef.density = 1000f;
        fdef.filter.categoryBits = Constants.Enemy; //is a
        fdef.filter.maskBits = Constants.PLAYER | Constants.FOLOWER; // I will colide with


        return world.createBody(def).createFixture(fdef).getBody();


    }

    public Body createBulletBody(Vector2 spawn) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawn.x, spawn.y);

        CircleShape shape = new CircleShape();
        shape.setRadius(1);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1.0f;
        fdef.friction = 0;


        return world.createBody(bodyDef).createFixture(fdef).getBody();

    }

    public Body createFollowerBody(Vector2 spawn, Boolean isCricle) {


        BodyDef nodeBodyDefinition = new BodyDef();
        nodeBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        //nodeBodyDefinition.position.set(50, 50);

        PolygonShape shape = new PolygonShape();
        float density = 1.0f;
        shape.setAsBox(10, 10);

        Body body = world.createBody(nodeBodyDefinition);
        //body.setUserData(this);
        body.setTransform(spawn.x+50, spawn.y+50, 0);
        final FixtureDef nodeFixtureDefinition = createFixtureDefinition(shape, density);
        nodeFixtureDefinition.isSensor = false;
        nodeFixtureDefinition.filter.categoryBits = Constants.PLAYER;
        nodeFixtureDefinition.filter.maskBits = Constants.Enemy;
        body.createFixture(nodeFixtureDefinition);
        shape.dispose();

        return body;


    }

    public Body createGemBody(Vector2 spawn) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawn.x, spawn.y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 1);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1.0f;
        fdef.friction = 0;
        fdef.filter.categoryBits = Constants.GEM;
        fdef.filter.maskBits = Constants.PLAYER| Constants.Wall;


        return world.createBody(bodyDef).createFixture(fdef).getBody();


    }

    public Body createCrossHairBody(Vector2 spawn){

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawn.x, spawn.y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1.0f;
        fdef.filter.categoryBits= Constants.CROSSHAIR;
        fdef.filter.maskBits= Constants.Wall;


        return world.createBody(bodyDef).createFixture(fdef).getBody();


    }


    public void destroyBody(Body body) {
        if (body != null){
            world.destroyBody(body);
        }
    }

    public void addToDestroy(Body b) {
        bodiesToDestroy.add(b);
    }

    public void destroyBodies() {
        if (bodiesToDestroy.size() > 0){
            for (Body body : bodiesToDestroy) {
                destroyBody(body);
            }

        }
        bodiesToDestroy.clear();
    }


    public void creatJoint(Body anchorPoint, Body turningBody, Body jointBox) {


        //Body rotator = createBox(40, 40, anchorPoint.getPosition().x, anchorPoint.getPosition().y); //wat rond object zit
        //rotator.setType(BodyDef.BodyType.DynamicBody);

        turningBody.setType(BodyDef.BodyType.DynamicBody);

        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.initialize(anchorPoint, jointBox, anchorPoint.getWorldCenter());
        revoluteJointDef.enableMotor = true;
        revoluteJointDef.motorSpeed = 2000000;
        revoluteJointDef.maxMotorTorque = 999999999;

        WeldJointDef weldJointDef = new WeldJointDef();
        weldJointDef.initialize(jointBox, turningBody, jointBox.getWorldCenter());

        Joint j1 = world.createJoint(revoluteJointDef);
        Joint j2 = world.createJoint(weldJointDef);

        jointsToDestroy.clear();
        jointsToDestroy.add(j1);
        jointsToDestroy.add(j2);
    }


    public Body createBox(float w, float h, float x, float y) {
        BodyDef nodeBodyDefinition = new BodyDef();
        nodeBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        //nodeBodyDefinition.position.set(50, 50);
        System.out.print("Box made\n");
        PolygonShape shape = new PolygonShape();
        float density = 1.0f;
        shape.setAsBox(w / 2.0f, h / 2.0f);

        Body body = world.createBody(nodeBodyDefinition);
        body.setUserData("box");
        body.setTransform(x, y, 0);
        FixtureDef nodeFixtureDefinition = createFixtureDefinition(shape, density);

        body.createFixture(nodeFixtureDefinition);
        shape.dispose();
        System.out.print("Box made\n");

        return body;
    }

    private static FixtureDef createFixtureDefinition(final Shape shape, final float density) {
        final FixtureDef nodeFixtureDefinition = new FixtureDef();
        nodeFixtureDefinition.shape = shape;
        nodeFixtureDefinition.friction = 1;
        nodeFixtureDefinition.density = density;
        nodeFixtureDefinition.restitution = 0.1f;
        nodeFixtureDefinition.isSensor = true;
        return nodeFixtureDefinition;
    }

    public  void destroyJoint(){
        System.out.print("\nDestroying body\n");
        if(0<jointsToDestroy.size()){
            System.out.print("\nThere are joints to destroy "+ bodiesToDestroy.size()+"\n");

            for (Joint j: jointsToDestroy){
                System.out.print("\nDestroying joint\n");
                world.destroyJoint(j);
            }
        }
        System.out.print("\nCLearing arraylist\n");
        jointsToDestroy.clear();
    }

    public void clearLists(){
        bodiesToDestroy.clear();
        jointsToDestroy.clear();
    }


}
