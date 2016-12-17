package com.mygdx.game.follower;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

import static sun.audio.AudioPlayer.player;

/**
 * Created by Shan on 12/12/2016.
 */
public class Follower {

    public Body getBody() {
        return body;
    }

    private Body body;
    private Camera cam;


    public Follower(Body body, Camera cam){
        this.body = body;
        this.cam = cam;
        setData();
    }

    public void setData(){
        body.setUserData(this);
    }


    public void update(Vector2 playerPosition){

        int horizontalForce = 0;
        int verticalForce = 0;


        float playerX = playerPosition.x  +20;
        float playerY = playerPosition.y  +20;

        float folowerX = body.getPosition().x;
        float folowerY = body.getPosition().y;

        if( folowerX < playerX){

            horizontalForce += 50;
        }
        if(folowerX > playerX) {
            horizontalForce -= 50;
        }
        if(folowerY < playerY){
            verticalForce +=50;
        }
        if(folowerY> playerY){
            verticalForce -=50;
        }



        body.setLinearVelocity(horizontalForce * 5, verticalForce * 5);

    }




    public void turn(Vector2 enemyPosition){

        Vector3 sp3 = cam.unproject(new Vector3(enemyPosition.x, enemyPosition.y, 0));
        Vector2 sp2 = new Vector2(sp3.x, sp3.y);

        Vector2 a = body.getPosition();
        Vector2 d = sp2.sub(a);

        body.setTransform(body.getPosition(), d.angleRad());
    }
}
