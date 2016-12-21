package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Bonus.BonusHandler;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.LevelHandler;
import com.mygdx.game.RenderHandler;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.colision.CollisionDetector;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.player.Player;
import constants.Constants;

import java.util.ArrayList;

/**
 * Created by Shan on 12/13/2016.
 *//**
public class BossState extends State implements GameInterface {


    public SpriteBatch batch;

    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();



    private Box2DDebugRenderer b2dr;  // wegdoen om alleen maar sprites te tonen
    private OrthographicCamera camera;


    private World world;
    private Player player;


    ArrayList<Body> objects;


    private BodyBuilder bodyBuilder;
    private EnemyManager enemyManager;
    private BulletManager bm;
    private BonusHandler bonusHandler;
    private LevelHandler levelHandler;
    private RenderHandler renderHandler;

    public BossState(GameStateManager gms,Player player) {
        super(gms);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / Constants.SCALE, h / Constants.SCALE);
        world = new World(new Vector2(0, 0), false); // zwaartekracht is hier positief?
        b2dr = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        bodyBuilder = BodyBuilder.getInstance();
        this.player = player;
        player.updateHud();
        this.world.setContactListener(new CollisionDetector(this));
        bm = new BulletManager(player,camera);
        enemyManager = new EnemyManager();
        levelHandler = new LevelHandler(player,enemyManager,gms);
        bonusHandler = new BonusHandler();
        renderHandler = new RenderHandler();
    }

    @Override
    public void handleInput() {
        int horizontalForce = 0;
        int verticalForce = 0;

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
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();


        if (Gdx.input.isTouched()) {

            //createBullet(getMouseCoords());

        }


        player.getPlayerBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render() {

        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 2);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(player.getHud().stage.getCamera().combined);
        player.getHud().stage.draw();

        batch.begin();
        renderHandler.renderPlayer(batch, player.getTexture(), player);
        renderHandler.renderEnemies(batch, enemyManager.getEnemies());
        renderHandler.renderBonus(batch, bonusHandler.getBonusToSpawn());
        renderHandler.renderBullets(batch, bm.getBullets());

        batch.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void removeBullet(Body b1, Body b2) {

    }

    @Override
    public void removeEnemies(Body b1, Body b2) {

    }

    @Override
    public void addCoordToBonusHandler(Vector2 coord) {

    }

    @Override
    public BonusHandler getBonusHandler() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public void destroyAllPeasants() {

    }

    @Override
    public EnemyManager getEnemyManager() {
        return null;
    }


}
**/