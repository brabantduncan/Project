package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Shan on 11/9/2016.
 */
public class BodyBuilder {

    public Body createBox(World world, int x, int y, int width, int height, boolean isStatic) {

        Body pBody;
        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }
        def.position.set(x, y);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(width / 2, height / 2 );


        pBody.createFixture(shape, 1.0f);

        shape.dispose();
        return pBody;
    }
}
