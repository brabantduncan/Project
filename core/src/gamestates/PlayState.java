package gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Player;
import managers.GameStateManager;

/**
 * Created by laurens on 8/11/2016.
 */
public class PlayState extends GameState{

    private ShapeRenderer sr;
    private Player player;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }



    public void init() {
        sr = new ShapeRenderer();
        player = new Player(null, null, 2, 99, null, 2, 22); //(demo) TODO combine with playerclass shan made
    }


    public void update(float dt) {
        player.update(dt);
    }


    public void draw() {    }


    public void handleInput() {    }

    public void dispose() {    }
}
