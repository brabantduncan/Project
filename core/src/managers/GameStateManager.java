package managers;

import gamestates.GameState;
import gamestates.PlayState;

/**
 * Created by laurens on 8/11/2016.
 */
public class GameStateManager {

    public GameState gameState;

    public static final int MENU = 0;
    public static final int PLAY = 1;

    public GameStateManager(){
        setState(PLAY);
    }
    public void setState(int state){
        if(gameState != null) gameState.dispose();
        if(state == MENU){
            //TODO gameState = new MenuState
        }
        if(state == PLAY){
            gameState = new PlayState(this);
        }
    }
}
