package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Bonus.BonusHandler;
import com.mygdx.game.Bullet.Bullet;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.LevelHandler;
import com.mygdx.game.RenderHandler;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.colision.CollisionDetector;
import com.mygdx.game.controller.ControllerHandler;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.follower.Follower;
import com.mygdx.game.player.Player;
import constants.Constants;
import java.util.ArrayList;

/**
 * Created by Shan on 11/28/2016.
 */
public class PlayState extends State implements GameInterface {

    public SpriteBatch batch;

    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();



    private Box2DDebugRenderer b2dr;  // wegdoen om alleen maar sprites te tonen
    private OrthographicCamera camera;


    private World world;
    private Player player;

    private ControllerHandler controllerHandler;

    ArrayList<Body> objects;


    private BodyBuilder bodyBuilder;
    private EnemyManager enemyManager;
    private BulletManager bm;
    private BonusHandler bonusHandler;
    private LevelHandler levelHandler;
    private RenderHandler renderHandler;


    public BonusHandler getBonusHandler() {
        return bonusHandler;
    }

    public Follower follower;


    public PlayState(GameStateManager gms) {

        super(gms);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / Constants.SCALE, h / Constants.SCALE);

        world = new World(new Vector2(0, 0), false); // zwaartekracht is hier positief?
        b2dr = new Box2DDebugRenderer();

        batch = new SpriteBatch();

        bodyBuilder = new BodyBuilder();

        player = new Player(bodyBuilder.createPlayer(world, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4, 45 / 4, 48 / 4, false, Constants.PLAYER), "Duncan");
        player.createHud(batch);

        this.world.setContactListener(new CollisionDetector(this));
        bm = new BulletManager(player,world,camera);

        enemyManager = new EnemyManager(bodyBuilder, world);


        levelHandler = new LevelHandler(player,enemyManager,gms);

        bonusHandler = new BonusHandler(world, bodyBuilder);
        renderHandler = new RenderHandler();
        objects = new ArrayList<Body>();
        createBorders();
        controllerHandler = new ControllerHandler();

    }

    @Override
    public void handleInput() {
        controllerHandler.handleInput(this);
        enemyManager.updateEnemyMovement(player.getPlayerBody().getPosition());
    }


    @Override
    public void update(float dt) {
        world.step(1 / 60f, 6, 2);

        if (player.isDead()) {
            dispose();
            gms.set(new MenuState(gms));

        } else {

            bm.destroyBullets();
            levelHandler.updateLevel(world);
            player.getHud().updateLevel(levelHandler.getLevel());
            player.updateHud();
            bonusHandler.addBonus();
            bonusHandler.destroyGems(player);

            handleInput();
            //cameraUpdate(dt);
            //batch.setProjectionMatrix(camera.combined);

            updatePlayerRotation(getMouseCoords());
        }
    }

    @Override
    public void render() {

        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // b2dr.render(world, camera.combined);

        batch.setProjectionMatrix(player.getHud().stage.getCamera().combined);
        player.getHud().stage.draw();

        batch.begin();
        renderHandler.renderPlayer(batch, player.getTexture(), player.getPlayerBody());
        renderHandler.renderEnemies(batch, enemyManager.getEnemies());
        renderHandler.renderBonus(batch, bonusHandler.getBonusToSpawn());
        renderHandler.renderBullets(batch, bm.getBullets());

        batch.end();
    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = camera.position.x + (player.getPlayerBody().getPosition().x - camera.position.x) * .1f;
        position.y = camera.position.y + (player.getPlayerBody().getPosition().y - camera.position.y) * .1f;
        camera.position.set(position);

        camera.update();
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
        objects.add(bodyBuilder.createWall(world, 0, 0, 1, (int) (Gdx.graphics.getHeight() / Constants.SCALE), true, Constants.Enemy, Constants.Wall));
        objects.add(bodyBuilder.createWall(world, 0, 0, (int) (Gdx.graphics.getWidth() / Constants.SCALE), 1, true, Constants.Enemy, Constants.Wall));
        objects.add(bodyBuilder.createWall(world, Gdx.graphics.getWidth() / 2, 0, 1, (int) (Gdx.graphics.getHeight() / Constants.SCALE), true, Constants.Enemy, Constants.Wall));
        objects.add(bodyBuilder.createWall(world, 0, (int) (Gdx.graphics.getHeight() / Constants.SCALE), Gdx.graphics.getWidth() / 2, 1, true, Constants.Enemy, Constants.Wall));


    }

    @Override
    public Player getPlayer() {
        return player;
    }


    @Override
    public void removeBullet(Body b1, Body b2) {
        if (b1.getUserData() instanceof Bullet) {
            bm.removeBullet(b1);
        } else {
            bm.removeBullet(b2);
        }
    }

    public void createBullet(Vector2 mouse){
        bm.addBullet(getMouseCoords(),bodyBuilder.createBulletBody(world,player.getPlayerBody().getPosition()));
    }

    @Override
    public void removeEnemies(Body b1, Body b2) {
        enemyManager.removeEnemies(b1, b2);
    }


    @Override
    public void addCoordToBonusHandler(Vector2 coord) {
        bonusHandler.addSpawnCoord(coord);
    }


    @Override
    public void dispose() {

        world.destroyBody(player.getPlayerBody());
//        batch.dispose();
        //b2dr.dispose();
        //world.dispose();
    }



}
