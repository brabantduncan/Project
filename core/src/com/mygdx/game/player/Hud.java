package com.mygdx.game.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import com.sun.org.apache.xpath.internal.operations.String;


/**
 * Created by Shan on 12/3/2016.
 */
public class Hud {
    public Stage stage;


    private int score;
    private int health;
    private int exp;
    private int level;

    Label expLabel;
    Label expLabel2;

    Label scoreLabel;
    Label scoreLabel2;

    Label levelLabel;
    Label levelLabel2;

    Label healthLabel;
    Label healthLabel2;

    FillViewport port;

    public  Hud(SpriteBatch sb, Player p){

        score =0;
        health =100;
        exp =0;
        level =1;

        port = new FillViewport(720,480,new OrthographicCamera());
        stage = new Stage(port,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        expLabel = new Label(Integer.toString(exp),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        expLabel2 = new Label("EXP",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        scoreLabel=new Label(Integer.toString(score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel2=new Label("SCORE",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        levelLabel=new Label(Integer.toString(level),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel2=new Label("LEVEL",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        healthLabel=new Label(Integer.toString(health),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel2=new Label("HEALTH",new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        //Layout nog veranderen

        table.top();

        table.add(expLabel2).expandX().pad(10);
        table.add(scoreLabel2).expandX().pad(10);
        table.add(levelLabel2).expandX().pad(10);
        table.add(healthLabel2).expandX().pad(10);
        table.row();
        table.add(expLabel).expandX().pad(5);
        table.add(scoreLabel).expandX().pad(5);
        table.add(levelLabel).expandX().pad(5);
        table.add(healthLabel).expandX().pad(5);

        stage.addActor(table);

    }

    public void update(Player p){
        score = p.getCurrentScore();
        exp = p.getCurrentEXP();
        health= p.getHealth();


        expLabel.setText(Integer.toString(exp));
        scoreLabel.setText(Integer.toString(score));

        healthLabel.setText(Integer.toString(health));

    }
    public void updateLevel(){
        level+=1;
        levelLabel.setText(Integer.toString(level));

    }

    //public void setStage()

}
