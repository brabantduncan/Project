package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Bonus.BonusHandler;
import com.mygdx.game.Bullet.Bullet;
import com.mygdx.game.Bullet.BulletManager;
import com.mygdx.game.LevelHandler;
import com.mygdx.game.RenderHandler;
import com.mygdx.game.TiledObjectUtil;
import com.mygdx.game.body.BodyBuilder;
import com.mygdx.game.colision.CollisionDetector;
import com.mygdx.game.controller.ControllerHandler;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.follower.FollowerManager;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerFactory;
import com.mygdx.game.player.hud.HudManager;
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
    private static OrthogonalTiledMapRenderer tmr;
    private static TiledMap map;


    private static World world;
    private ArrayList<Player> players;

    private ControllerHandler controllerHandler;

    ArrayList<Body> objects;

    private Music gameMusic;
    private static Array<Body> mapObjects;

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

    private HudManager hudManager;

    public BonusHandler getBonusHandler() {
        return bonusHandler;
    }

    public String[] usernames;
    public String difficulty;


    public PlayState(GameStateManager gms, String[] usernames, String[] pets, String difficulty) {

        super(gms);
        this.usernames = usernames;
        background = new Texture("../assets/background.jpg");
        map = new TmxMapLoader().load("../assets/Maps/naamloos.tmx");

        tmr = new OrthogonalTiledMapRenderer(map);

        System.out.println(map.getLayers().get("collison-layer").getObjects().getClass());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / Constants.SCALE, h / Constants.SCALE);
        world = new World(new Vector2(0, 0), false); // zwaartekracht is hier positief?
        b2dr = new Box2DDebugRenderer();

        batch = new SpriteBatch();
        bm = new BulletManager(camera);
        enemyManager = new EnemyManager();
        controllerHandler = new ControllerHandler(camera, bm, enemyManager);


        BodyBuilder.getInstance().setWorld(world);
        PlayerFactory playerFactory = new PlayerFactory();
        players = new ArrayList<Player>();

        for (int i = 0; i < usernames.length; i++) {
            players.add(playerFactory.createPlayer(usernames[i], pets[i]));
        }

        PlayerFactory playerFactoy = new PlayerFactory();
        //players = playerFactoy.getPlayers(2);

        controllerHandler.giveControles(players);


        //Nog hun hud tekenen
        hudManager = new HudManager();
        hudManager.setHud(players, batch);
        //player.spawnFollower();

        this.world.setContactListener(new CollisionDetector(this));


        levelHandler = new LevelHandler(enemyManager, gms);

        bonusHandler = new BonusHandler();
        renderHandler = new RenderHandler();
        objects = new ArrayList<Body>();


        followerManager = new FollowerManager();


        mapObjects = TiledObjectUtil.parseTiledObjectLayer(map, world);


        createBorders();
        System.out.println("pet:" + pets[0]);

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
        controllerHandler.handleInput(players);
        enemyManager.updateEnemyMovement(players);
    }


    @Override
    public void update(float dt) {
        world.step(1 / 60f, 6, 2);

        if (checkAllPlayersDeath()) {
            endGame();
        } else {

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
            batch.setProjectionMatrix(camera.combined);
            followerManager.moveFollower(players.get(0));
            followerManager.doAction(players.get(0), bm);
            hudManager.updateHandler(players, levelHandler.getLevel());
            enemyManager.doAction(players,bm,followerManager);


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



        tmr.render();

        /**
         batch.setProjectionMatrix(player.getHud().stage.getCamera().combined);
         player.getHud().stage.draw();
         **/

        b2dr.render(world, camera.combined);

        batch.begin();
        // batch.draw(background, 0, 0);

        renderHandler.renderPlayer(batch, players);
        renderHandler.renderEnemies(batch, enemyManager.getEnemies());
        renderHandler.renderBonus(batch, bonusHandler.getBonusToSpawn());
        renderHandler.renderBullets(batch, bm.getBullets());


        batch.end();

        hudManager.drawHud(players);
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


    public void endGame() {
        /**
         for(Players p: players){

         try {
         projectDB.getInstance().addScore(p.getPlayerName(), p.getCurrentScore());
         } catch (SQLException e) {
         e.printStackTrace();
         }

         }
         **/
        controllerHandler.destroyCrosshairBodys(players);
        gameMusic.stop();
        followerManager.destroyMultipleFollowers(players);
        BodyBuilder.getInstance().clearLists();
        gms.set(new MenuState(gms));

    }

    public boolean checkAllPlayersDeath() {
        boolean allDead = true;
        for (Player p : players) {
            if (!p.isDead()) {
                allDead = false;
            }
        }
        return allDead;
    }
    public static void changeMap(int level) {
        int mapSelect = (level % 10) +1;

        Gdx.app.postRunnable(() -> {
            map = new TmxMapLoader().load("../assets/Maps/level" + mapSelect +".tmx"); //load the new map
            tmr.getMap().dispose(); //dispose the old map
            tmr.setMap(map); //set the map in your renderer


            for(Body b : mapObjects){
                world.destroyBody(b);
            }
            mapObjects = TiledObjectUtil.parseTiledObjectLayer(map, world);

        });
    }
}
