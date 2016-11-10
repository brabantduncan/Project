package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;



public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture tex;
	private boolean DEBUG = false;
	private final float SCALE = 2.0f;

	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;

	private World world;
	private Body player;
	private Body object;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();



		camera = new OrthographicCamera();
		camera.setToOrtho(false, w / SCALE, h / SCALE);

		world = new World(new Vector2(0, -5), false); // zwaartekracht is hier positief?
		b2dr = new Box2DDebugRenderer();

		player = createBox(0, 0, 16, 16, false);
		object = createBox(100, 100, 32, 32, true);


		//createBox(0, 0, 5, 5, true);





	}

	@Override
	public void render() {
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		b2dr.render(world, camera.combined);



	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width / SCALE, height / SCALE);
	}

	@Override
	public void dispose() {
		world.dispose();
		b2dr.dispose();
	}

	private void update(float delta) {
		world.step(1 / 60f, 6, 2);

		inputUpdate(delta);
		cameraUpdate(delta);
	}


	public void inputUpdate(float delta) {
		int horizontalForce = 0;
		int verticalForce = 0;

		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.input.getY();

		float playerX = player.getPosition().x;
		float playerY = player.getPosition().y;


		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			horizontalForce -= 50;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			horizontalForce += 50;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			verticalForce -= 50;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			verticalForce += 50;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			player.setTransform(0,0,0);
		}
		float distanceX = calcDistance(mouseX, playerX);
		float distanceY = calcDistance(mouseY, playerY);
		float transAngle = (float) calcAngle((double) distanceY, (double) distanceX);
		player.setTransform(playerX, playerY, transAngle);


		player.setLinearVelocity(horizontalForce * 5, verticalForce * 5);
		System.out.println("c: ("+ mouseX +","+ mouseY+")");
		System.out.println("p: ("+ playerX +","+ playerY+")");



	}







	public void cameraUpdate(float delta) {
		Vector3 position = camera.position;
		position.x = camera.position.x + (player.getPosition().x - camera.position.x) * .1f;
		position.y = camera.position.y + (player.getPosition().y - camera.position.y) * .1f;
		camera.position.set(position);

		camera.update();
	}

	public Body createBox(int x, int y, int width, int height, boolean isStatic) {
		Body pBody;
		BodyDef def = new BodyDef();

		if (isStatic) {
			def.type = BodyDef.BodyType.StaticBody;
		} else {
			def.type = BodyDef.BodyType.DynamicBody;
		}
		def.position.set(x, y);
		def.fixedRotation = true;
		pBody = world.createBody(def);

		PolygonShape shape = new PolygonShape();

		shape.setAsBox(width / 2, height / 2 );


		pBody.createFixture(shape, 1.0f);

		shape.dispose();
		return pBody;



	}
	public float calcDistance(float cursorPos, float playerPos) {
		float distance = 0;
		if (playerPos < 0) {
			distance = Math.abs(cursorPos - playerPos);
		}

		if (playerPos > 0) {
			distance = Math.abs(cursorPos - playerPos);
		}
		return distance;
	}



	public double calcAngle(double o, double a){
		double angle;
		angle = Math.toDegrees(Math.atan(o/a));
		return angle; //degrees
	}




}

