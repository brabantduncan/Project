package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
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
import com.mygdx.game.screen.PlayScreen;
import com.mygdx.game.screen.StartScreen;


public class MyGdxGame extends Game {

	public SpriteBatch batch;


	private boolean DEBUG = false;
	private final float SCALE = 2.0f;

	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;

	private World world;

	private Player player;

	private Body object;

	private BodyBuilder bodyBuilder;

	private Bullet bullet;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();



		camera = new OrthographicCamera();
		camera.setToOrtho(false, w / SCALE, h / SCALE);

		world = new World(new Vector2(0, -5), false); // zwaartekracht is hier positief?
		b2dr = new Box2DDebugRenderer();

		batch = new SpriteBatch();

		bodyBuilder = new BodyBuilder();



		player = new Player(bodyBuilder.createBox(world,0, 0, 36, 56, false),"Duncan");

		object = bodyBuilder.createBox(world,100, 100, 32, 32, true);

		setScreen(new StartScreen(this));

		//createBox(0, 0, 5, 5, true);





	}

	@Override
	public void render() {

		super.render();
/**
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		b2dr.render(world, camera.combined);
**/
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width / SCALE, height / SCALE);
	}

	@Override
	public void dispose() {
		world.dispose();
		b2dr.dispose();
		batch.dispose();
	}

	private void update(float delta) {
		world.step(1 / 60f, 6, 2);

		inputUpdate(delta);
		cameraUpdate(delta);

		batch.setProjectionMatrix(camera.combined);
	}


	public void inputUpdate(float delta) {
		int horizontalForce = 0;
		int verticalForce = 0;

		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.input.getY();

		float playerX = player.getPlayerBody().getPosition().x;
		float playerY = player.getPlayerBody().getPosition().y;


		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			horizontalForce -= 50;
			transform(mouseX, mouseY, playerX, playerY);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			horizontalForce += 50;
			transform(mouseX, mouseY, playerX, playerY);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			verticalForce -= 50;
			transform(mouseX, mouseY, playerX, playerY);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			verticalForce += 50;
			transform(mouseX, mouseY, playerX, playerY);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			player.getPlayerBody().setTransform(0,0,0);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			Bullet bullet = new Bullet();
			bullet.createBox(world, (int) playerX, (int) playerY, 20, true);

		}







	player.getPlayerBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);




	}

	public void transform(float mouseX, float mouseY, float playerX, float playerY){
		float distanceX = calcDistance(mouseX, playerX);
		float distanceY = calcDistance(mouseY, playerY);
		float transAngle = (float) calcAngle((double) distanceY, (double) distanceX);
		player.getPlayerBody().setTransform(playerX, playerY,transAngle);
		System.out.println("c: ("+ mouseX +","+ mouseY+")");
		System.out.println("p: ("+ playerX +","+ playerY+")");
		System.out.println("angle: "+ transAngle);
	}





	public void cameraUpdate(float delta) {
		Vector3 position = camera.position;
		position.x = camera.position.x + (player.getPlayerBody().getPosition().x - camera.position.x) * .1f;
		position.y = camera.position.y + (player.getPlayerBody().getPosition().y - camera.position.y) * .1f;
		camera.position.set(position);

		camera.update();
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

