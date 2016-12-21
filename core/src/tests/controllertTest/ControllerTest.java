package tests.controllertTest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import constants.XboxPad;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ControllerTest extends ApplicationAdapter {

    Controller controller;
    SpriteBatch batch;
    Sprite sprite;
    BitmapFont font;
    boolean hasControllers = false;
    String message = "Please install a controller";
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer renderer;
    Body test;

    @Override
    public void create () {
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setPosition(Gdx.graphics.getWidth()/2 -sprite.getWidth()/2,
                Gdx.graphics.getHeight()/2-sprite.getHeight()/2);

        font = new BitmapFont();
        font.setColor(Color.WHITE);

        camera = new OrthographicCamera(100, 100);
        camera.position.set(0,0,0);

        world = new World(Vector2.Zero, true);

        renderer = new Box2DDebugRenderer();

        test = createBox(10,4,0,0); //wat rond object zit


        if(Controllers.getControllers().size > 0)
        {
            controller = Controllers.getControllers().get(0);
            hasControllers = true;
        }
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        buttonDown();
        calculateForce();
        world.step(Gdx.graphics.getDeltaTime(), 4, 5);
        camera.update();

        renderer.render(world, camera.combined);

    }

    public void buttonDown() {

        if(controller.getButton(XboxPad.BUTTON_Y))
            sprite.setY(sprite.getY() + 1);
        /*if(buttonCode == XboxPad.BUTTON_A)
            sprite.setY(sprite.getY()-1);
        if(buttonCode == XboxPad.BUTTON_X)
            sprite.setX(sprite.getX() - 1);
        if(buttonCode == XboxPad.BUTTON_B)
            sprite.setX(sprite.getX() + 1);

        if(buttonCode == XboxPad.BUTTON_LB)
            sprite.scale(-0.1f);
        if(buttonCode == XboxPad.BUTTON_RB)
            sprite.scale(0.1f);
        return false;*/
    }

    public void calculateForce()
    {
        int horizontalForce = 0;
        int verticalForce = 0;

        if (controller.getAxis(XboxPad.AXIS_LEFT_Y) > 0.2 ) {
            horizontalForce = -10;
        }
        if (controller.getAxis(XboxPad.AXIS_LEFT_Y) < -0.2 ){
            horizontalForce= 10;
        }
        if (controller.getAxis(XboxPad.AXIS_LEFT_X) > 0.2 ) {
            verticalForce = +10;
        }
        if (controller.getAxis(XboxPad.AXIS_LEFT_X) < - 0.2 ) {
            verticalForce = -10;
        }


        Vector2 angleVector = new Vector2();//angleV;

        if (controller.getAxis(XboxPad.AXIS_RIGHT_X) > 0.1 || controller.getAxis(XboxPad.AXIS_RIGHT_X) < -0.1) {
            angleVector = new Vector2(controller.getAxis(XboxPad.AXIS_RIGHT_X), angleVector.y);
        }
        if (controller.getAxis(XboxPad.AXIS_RIGHT_Y) < -0.1 || controller.getAxis(XboxPad.AXIS_RIGHT_Y) > 0.1) {
            angleVector = new Vector2(angleVector.x, -controller.getAxis(XboxPad.AXIS_RIGHT_Y));
        }


        test.setTransform(test.getPosition(), angleVector.nor().angleRad());
        //test.setAngularVelocity(angleVector.nor().angle());

        // System.out.println(angleVector);
/**
        if(axisCode == XboxPad.AXIS_LEFT_Y && value <0.1){
            verticalForce = -10;
        }
        if(axisCode == XboxPad.AXIS_LEFT_Y && value >0.1){
            verticalForce = 10;
        }
**/
/*
        System.out.print("\naxiscode: "+axisCode);
        System.out.print("\nValue: "+round(value,2));
        System.out.print("\nBody nu op : "+test.getPosition());
*/
        test.setLinearVelocity(verticalForce*5,horizontalForce*5);

    }

    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        // This is the dpad
        if(value == XboxPad.BUTTON_DPAD_LEFT)
            sprite.translateX(-10f);
        if(value == XboxPad.BUTTON_DPAD_RIGHT)
            sprite.translateX(10f);
        if(value == XboxPad.BUTTON_DPAD_UP)
            sprite.translateY(10f);
        if(value == XboxPad.BUTTON_DPAD_DOWN)
            sprite.translateY(-10f);
        return false;
    }

     private Body createBox(float w, float h, float x, float y) {
        BodyDef nodeBodyDefinition = new BodyDef();
        nodeBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        nodeBodyDefinition.position.set(50, 50);

        PolygonShape shape = new PolygonShape();
        float density = 1.0f;
        shape.setAsBox(w / 2.0f, h / 2.0f);

        Body body = world.createBody(nodeBodyDefinition);
        //body.setUserData(this);
        body.setTransform(x, y, 0);
        final FixtureDef nodeFixtureDefinition = createFixtureDefinition(shape, density);

        body.createFixture(nodeFixtureDefinition);
        shape.dispose();

        return body;
    }


    private static FixtureDef createFixtureDefinition(final Shape shape, final float density) {
        final FixtureDef nodeFixtureDefinition = new FixtureDef();
        nodeFixtureDefinition.shape = shape;
        nodeFixtureDefinition.friction = 1;
        nodeFixtureDefinition.density = density;
        nodeFixtureDefinition.restitution = 0.1f;
        return nodeFixtureDefinition;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}