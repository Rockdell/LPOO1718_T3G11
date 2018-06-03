package com.hotlinedrifter.body.bullets;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.body.BaseBody;
import com.hotlinedrifter.model.bullets.BulletModel;

/**
 * Bullet Body.
 */
abstract class BulletBody extends BaseBody {

    /**
     * Creates an object BulletBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    BulletBody(World world, BulletModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);
    }
}
