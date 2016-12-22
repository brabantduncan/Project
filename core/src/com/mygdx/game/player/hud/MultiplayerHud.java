package com.mygdx.game.player.hud;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.player.Player;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Shan on 12/21/2016.
 */
public class MultiplayerHud implements HudInterFace {

    public Stage stage;
    FillViewport port;

    HashMap<Label, Label> scorePerPlayer;
    //ArrayList<Label>
    Label bombLabel;
    Label bombLabel2;
    Label levelLabel;
    Label levelLabel2;

    public MultiplayerHud(ArrayList<Player> players, Batch batch){

        scorePerPlayer= new HashMap<Label, Label>(players.size());
        setScorePerPlayer(players);
        setHud(players,batch);

    }





    @Override
    public void update(ArrayList<Player> players, int level) {

        int i = 0;
        for (Label label: scorePerPlayer.keySet()){
            scorePerPlayer.get(label).setText(Integer.toString(players.get(i).getCurrentScore()));
            i+=1;
        }
        updateBomb(players);
        updateLevel(level);
    }

    @Override
    public void draw() {
        stage.draw();
    }

    public void setHud(ArrayList<Player> players, Batch batch){

        port = new FillViewport(720,480,new OrthographicCamera());
        stage = new Stage(port,batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        for(int i =0; i< players.size(); i++){
            Label label2 = new Label(Integer.toString(players.get(i).getCurrentScore()),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            String name = "Score player "+Integer.toString(i+1);
            Label label = new Label(name,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            scorePerPlayer.put(label,label2);
        }

        setTable(table);
        table.row();
        setOther(table);

        stage.addActor(table);
    }

    private void setTable(Table table) {
        table.top();
        for (Label label: scorePerPlayer.keySet()){
            table.add(label).expandX().pad(5);

        }
        table.row();
        for (Label label: scorePerPlayer.keySet()){
            table.add(scorePerPlayer.get(label)).expandX().pad(5);
        }
    }

    public void setScorePerPlayer(ArrayList<Player> players){
        for (int i = 0; i< players.size();i++ ){

        }
    }

    private void setOther(Table table){
        bombLabel=new Label("No bomb available",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bombLabel2=new Label("Player with bomb",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel=new Label(Integer.toString(0),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel2=new Label("Current level",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(bombLabel2).expandX().pad(0);
        table.add(levelLabel2).expandX().pad(0);
        table.row();
        table.add(bombLabel).expandX().pad(0);
        table.add(levelLabel).expandX().pad(0);
    }

    private void updateBomb(ArrayList<Player> players){
       bombLabel.setText(translateBomb(players));
    }

    public void updateLevel(int level){
        levelLabel.setText(Integer.toString(level));
    }

    public String translateBomb(ArrayList<Player> players){
        String string = "No bomb available";
        for (int i =0; i<players.size();i++){
            if (players.get(i).isHasBomb()){
                return "Player "+(i+1);
            }
        }
        return string;
    }
}
