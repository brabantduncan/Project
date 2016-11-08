package gamestates;


import managers.GameStateManager;

/**
 * Created by laurens on 8/11/2016.
 */
public abstract class GameState {

    private GameStateManager gsm;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
