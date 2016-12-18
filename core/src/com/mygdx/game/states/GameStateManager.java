package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Shan on 11/27/2016.
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){

        states = new Stack<State>();
    }
    public void push(State newState){

        states.push(newState);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.peek().dispose();
        states.pop();
        states.push(state);
    }

    public void update(float dt){

        states.peek().update(dt);
    }

    public  void render(SpriteBatch batch){

        states.peek().render();

    }


}
