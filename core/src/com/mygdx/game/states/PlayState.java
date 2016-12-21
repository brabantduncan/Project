package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.audio.Music;
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
import com.mygdx.game.follower.FollowerManager;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerFactory;
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
    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;


    private World world;
    private ArrayList<Player> players;

    private ControllerHandler controllerHandler;

    ArrayList<Body> objects;


    private Music gameMusic;

    @Override
    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    private EnemyManager enemyManager;
    private BulletManager bm;
    private BonusHandler bonusHandler;
    private LevelHandler levelHandler;
    private RenderHandler renderHandler;
    private Texture background;

    private FollowerManager followerManager;


    public BonusHandler getBonusHandler() {
        return bonusHandler;
    }



    public PlayState(GameStateManager gms) {

        super(gms);
        background = new Texture("../assets/background.jpg");
        map = new TmxMapLoader().load("../assets/Maps/naamloos.tmx");

        // tmr = new OrthogonalTiledMapRenderer(map);


       tmr = new OrthogonalTiledMapRenderer(map);
        tmr = new OrthogonalTiledMapRenderer(map);

        System.out.println(map.getLayers().get("collison-layer").getObjects().getClass());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / Constants.SCALE, h / Constants.SCALE);
        world = new World(new Vector2(0, 0), false); // zwaartekracht is hier positief?
        b2dr = new Box2DDebugRenderer();

        batch = new SpriteBatch();
        controllerHandler = new ControllerHandler();


        BodyBuilder.getInstance().setWorld(world);
        PlayerFactory playerFactoy = new PlayerFactory();
        players = playerFactoy.getPlayers(1);
        controllerHandler.giveControles(players);

        //Nog hun hud tekenen

        //player.spawnFollower();

        this.world.setContactListener(new CollisionDetector(this));

        bm = new BulletManager(camera);
        enemyManager = new EnemyManager();


        levelHandler = new LevelHandler(enemyManager, gms);

        bonusHandler = new BonusHandler();
        renderHandler = new RenderHandler();
        objects = new ArrayList<Body>();

        controllerHandler = new ControllerHandler();

        followerManager = new FollowerManager();


        // TiledObjectUtil.parseTiledObjectLayer(map, world);

        createBorders();


        //TiledObjectUtil.parseTiledObjectLayer(map, world);

        //TiledObjectUtil.parseTiledObjectLayer(map, world);

        createBorders();


        //music
        /**
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("../assets/sounds/gameMusic.mp3"));
        gameMusic.setVolume(.25f);
        gameMusic.setLooping(true);
        gameMusic.play();
        */
    }

    @Override
    public void handleInput() {
        controllerHandler.handleInput(players, this);
        enemyManager.updateEnemyMovement(players);
    }


    @Override
    public void update(float dt) {
        world.step(1 / 60f, 6, 2);

        if (checkAllPlayersDeath()) {
            endGame();
        }

        else {

            bm.destroyBullets();
            levelHandler.updateLevel();
            /**
             * //hud interface maken die een gezamelijk hud heeft en een aparte
             player.getHud().updateLevel(levelHandler.getLevel());
             player.updateHud();**/

            bonusHandler.addBonus(); // spawned alle bonussen;
            bonusHandler.destroyGems(players);
            handleInput();

            tmr.setView(camera);

            tmr.setView(camera);

            //cameraUpdate(dt);
            //batch.setProjectionMatrix(camera.combined);
           // followerManager.moveFollower(players);
            followerManager.doAction(players.get(0), getMouseCoords(), bm);

            updatePlayerRotation(getMouseCoords(), players.get(0));

            BodyBuilder.getInstance().destroyBodies();
        }
    }

    @Override
    public void render() {

        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        //batch.draw(background, 0, 0);
        batch.end();

        //tmr.render();

        /**
         batch.setProjectionMatrix(player.getHud().stage.getCamera().combined);
         player.getHud().stage.draw();
         **/

        b2dr.render(world, camera.combined);

        batch.begin();
        // batch.draw(background, 0, 0);
/**
 renderHandler.renderPlayer(batch, players.get(0).getTexture(), players.get(0));
 renderHandler.renderEnemies(batch, enemyManager.getEnemies());
 renderHandler.renderBonus(batch, bonusHandler.getBonusToSpawn());
 renderHandler.renderBullets(batch, bm.getBullets());

 **/
        batch.end();
        /**
         batch.setProjectionMatrix(player.getHud().stage.getCamera().combined);
         player.getHud().stage.draw();**/
    }

    /**
     * public void cameraUpdate(float delta) {
     * Vector3 position = camera.position;
     * position.x = camera.position.x + (player.getPlayerBody().getPosition().x - camera.position.x) * .1f;
     * position.y = camera.position.y + (player.getPlayerBody().getPosition().y - camera.position.y) * .1f;
     * camera.position.set(position);
     * camera.update();
     * }
     **/

    public Vector2 getMouseCoords() {
        int xmouse = Gdx.input.getX();
        int ymouse = Gdx.input.getY();

        return new Vector2(xmouse, ymouse);
    }

    public void updatePlayerRotation(Vector2 mouseCoords, Player player) {

        Vector3 sp3 = camera.unproject(new Vector3(mouseCoords.x, mouseCoords.y, 0));
        Vector2 sp2 = new Vector2(sp3.x, sp3.y);

        Vector2 a = player.getPlayerBody().getPosition();
        Vector2 d = sp2.sub(a);

        player.getPlayerBody().setTransform(player.getPlayerBody().getPosition(), d.angleRad());
        //System.out.println(player.getPlayerBody().getPosition());
    }

    public void createBorders() {
        objects.add(BodyBuilder.getInstance().setLevelWall(0, 0, (int) (Gdx.graphics.getWidth() / Constants.SCALE), 1, true));
        objects.add(BodyBuilder.getInstance().setLevelWall(0, 0, 1, (int) (Gdx.graphics.getHeight() / Constants.SCALE), true));
        objects.add(BodyBuilder.getInstance().setLevelWall(Gdx.graphics.getWidth() / 2, 0, 1, (int) (Gdx.graphics.getHeight() / Constants.SCALE), true));
        objects.add(BodyBuilder.getInstance().setLevelWall(0, (int) (Gdx.graphics.getHeight() / Constants.SCALE), Gdx.graphics.getWidth() / 2, 1, true));


    }

    @Override
    public ArrayList<Player> getPlayer() {
        return players;
    }


    @Override
    public void removeBullet(Body b1, Body b2) {
        if (b1.getUserData() instanceof Bullet) {
            bm.removeBullet(b1);
        } else {
            bm.removeBullet(b2);
        }
    }

    public void createBullet(Vector2 mouse, Player player) {
        bm.addBullet(getMouseCoords(), BodyBuilder.getInstance().createBulletBody(player.getPlayerBody().getPosition()));


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

        //BodyBuilder.getInstance().addToDestroy(player.getPlayerBody());
//        batch.dispose();
        //b2dr.dispose();
        //world.dispose();

    }

    @Override
    public void destroyAllPeasants() {
        enemyManager.destroyAllPeasants();
    }


    public void endGame(){
        /**
         for(Players p: players){

         try {
         projectDB.getInstance().addScore(p.getPlayerName(), p.getCurrentScore());
         } catch (SQLException e) {
         e.printStackTrace();
         }

         }
         **/
        gameMusic.stop();
        followerManager.destroyMultipleFollowers(players);
        BodyBuilder.getInstance().clearLists();
        gms.set(new MenuState(gms));

    }

    public boolean checkAllPlayersDeath(){
        boolean allDead = true;
        for (Player p:players){
            if(!p.isDead()){
                allDead = false;
            }
        }
        return allDead;
    }
}
