package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Bonus.BonusHandler;
import com.mygdx.game.Bonus.Gem;
import com.mygdx.game.Bullet.Bullet;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.colision.CollisionDetector;
import com.mygdx.game.controller.ControllerHandler;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.player.Hud;
import com.mygdx.game.player.Player;
import constants.Constants;

/**
 * Created by Shan on 11/28/2016.
 */
public class PlayState extends State {

    public SpriteBatch batch;


    private boolean DEBUG = false;
    private final float SCALE = 2.0f;

    private Box2DDebugRenderer b2dr;
    private OrthographicCamera camera;

    public World getWorld() {
        return world;
    }

    private World world;


    private Player player;
    private Body object;

    //Nog appart maken
    private Body wall;
    private Body wall2;
    private Body wall3;
    private Body wall4;


    private BodyBuilder bodyBuilder;
    //private ArrayList<Bullet> bullets = new ArrayList<Bullet>();


    private EnemyManager enemyManager;

    private BulletManager bm;

    private Hud hud;
    private int level;

    public void setDestroy(boolean destroy) {
        this.gameOver = destroy;
    }


    private BonusHandler bonusHandler;

    private boolean gameOver = false;
    public BonusHandler getBonusHandler() {
        return bonusHandler;
    }


    public PlayState(GameStateManager gms) {

        super(gms);


        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();


        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / SCALE, h / SCALE);

        world = new World(new Vector2(0, 0), false); // zwaartekracht is hier positief?
        b2dr = new Box2DDebugRenderer();

        batch = new SpriteBatch();

        bodyBuilder = new BodyBuilder();


        player = new Player(bodyBuilder.createPlayer(world, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4, 36, 56, false, Constants.PLAYER), "Duncan");

        //object = bodyBuilder.createWall(world,100, 100, 32, 32, true);


        wall = bodyBuilder.createWall(world, 0, 0, 1, Gdx.graphics.getHeight() / 2, true, Constants.Enemy, Constants.Wall);
        wall2 = bodyBuilder.createWall(world, 0, 0, Gdx.graphics.getWidth() / 2, 1, true, Constants.Enemy, Constants.Wall);
        wall3 = bodyBuilder.createWall(world, Gdx.graphics.getWidth() / 2, 0, 1, Gdx.graphics.getHeight() / 2, true, Constants.Enemy, Constants.Wall);
        wall4 = bodyBuilder.createWall(world, 0, Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth() / 2, 1, true, Constants.Enemy, Constants.Wall);

        this.world.setContactListener(new CollisionDetector(this));

        bm = new BulletManager(player);

        enemyManager = new EnemyManager(bodyBuilder, world);
        enemyManager.createEnemies(100);

        hud = new Hud(batch, player);

        level = 1;

        bonusHandler = new BonusHandler(world,bodyBuilder);

    }

    @Override
    public void handleInput() {

    }


    @Override
    public void update(float dt) {
        world.step(1 / 60f, 6, 2);

        if (gameOver) {
            dispose();
            Gdx.app.exit();
        } else {
            handleLevel();

            //bonusHandler.addGem();
            //bonusHandler.destroyGems();


            hud.update(player);
            destroyBullets();
            destroyEnemies();

            //controllerHandler.handleInput(player,dt);
            inputUpdate(dt);
            //cameraUpdate(dt);


            batch.setProjectionMatrix(camera.combined);


            updateEnemyMovement();


            updatePlayerRotation(getMouseCoords());
        }


    }

    @Override
    public void render() {

        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world, camera.combined);

        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        Sprite sprite = player.getSprite();
        batch.begin();

        sprite.draw(batch);
        sprite.setPosition(player.getPlayerBody().getPosition().x, player.getPlayerBody().getPosition().y);
        batch.end();
//        System.out.println("x " + sprite.getX()+ " player x "+ player.getPlayerBody().getPosition().x);
//        System.out.println("y " + sprite.getY()+ " player y "+ player.getPlayerBody().getPosition().y);
        //batch.begin();

    }


    public void inputUpdate(Float dt) {


        int horizontalForce = 0;
        int verticalForce = 0;


        float playerX = player.getPlayerBody().getPosition().x;
        float playerY = player.getPlayerBody().getPosition().y;


        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 50;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 50;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            verticalForce -= 50;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            verticalForce += 50;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            player.getPlayerBody().setTransform(0, 0, 0);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();


        if (Gdx.input.isTouched()) {

            createBullet(getMouseCoords(), dt);

        }


        player.getPlayerBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);


    }


    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = camera.position.x + (player.getPlayerBody().getPosition().x - camera.position.x) * .1f;
        position.y = camera.position.y + (player.getPlayerBody().getPosition().y - camera.position.y) * .1f;
        camera.position.set(position);

        camera.update();
    }


    public void createEnemies(int enemyCount) {

        enemyManager.createEnemies(enemyCount);

    }


    public void updateEnemyMovement() {
        for (Enemy e : enemyManager.getEnemies()) {
            e.updatePosition(player.getPlayerBody().getPosition());
        }

    }


    public Vector2 getMouseCoords() {
        int xmouse = Gdx.input.getX();
        int ymouse = Gdx.input.getY();

        return new Vector2(xmouse, ymouse);
    }


    public void updatePlayerRotation(Vector2 mouseCoords) {

        Vector3 sp3 = camera.unproject(new Vector3(mouseCoords.x, mouseCoords.y, 0));
        Vector2 sp2 = new Vector2(sp3.x, sp3.y);

        Vector2 a = player.getPlayerBody().getPosition();
        Vector2 d = sp2.sub(a);

        player.getPlayerBody().setTransform(player.getPlayerBody().getPosition(), d.angleRad());
        //System.out.println(player.getPlayerBody().getPosition());
    }


    public void createBorders() {


    }

    public Player getPlayer() {
        return player;
    }

    public void createBullet(Vector2 mouseLocation, Float delta) {

        float prev = 0;
        float delay = 5;

        if (prev == 0) {
            prev = delta - delay;
        }
        if (prev < (delta + delay)) {

            Bullet b = new Bullet(bodyBuilder.createBulletBody(world, player.getPlayerBody().getPosition()));

            bm.addBullet(camera, b, mouseLocation);

        }

        prev = delta;
    }

    public void dispose() {

        world.destroyBody(player.getPlayerBody());
        setDestroy(false);
    }


    public void removeBullet(Body b1, Body b2) {
        if (b1.getUserData() instanceof Bullet) {
            bm.removeBullet(b1);
        } else {
            bm.removeBullet(b2);
        }
    }

    public void destroyBullets() {


        if (bm.getDisposeBullets().size() > 0) {

            for (Bullet b : bm.getDisposeBullets()) {
                world.destroyBody(b.getB());
            }
            bm.clearDispose();
        }
    }

    public void removeEnemies(Body b1, Body b2) {
        if (b1.getUserData() instanceof Enemy) {
            enemyManager.removeEnemy(b1);
        } else {
            enemyManager.removeEnemy(b2);
        }
    }

    public void destroyEnemies() {
        if (enemyManager.getDisposeEnemies().size() > 0) {

            for (Enemy e : enemyManager.getDisposeEnemies()) {
                world.destroyBody(e.getBody());
            }
            enemyManager.clearDispose();
        }
    }

    public void handleLevel() {

        if (enemyManager.getEnemies().isEmpty()) {
            hud.updateLevel();
            createEnemies(100);
        }
    }

    public void spawnGem(Vector2 spawn){
        bonusHandler.spawnGem(spawn);
    }

}
