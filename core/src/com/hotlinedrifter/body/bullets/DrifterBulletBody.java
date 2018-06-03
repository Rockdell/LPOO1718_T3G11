package com.hotlinedrifter.body.bullets;

import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.model.bullets.DrifterBulletModel;

/**
 * Drifter BulletBody.
 */
public class DrifterBulletBody extends BulletBody {

    /**
     * Creates an object DrifterBulletBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    public DrifterBulletBody(World world, DrifterBulletModel model) {
        super(world, model);

        createFixture(new float[] { 0,0, 0,7, 21,7, 21,0 }, 21, 7, DBULLET_BODY, (short) (ENEMY_BODY | JBULLET_BODY | WALL_BODY));
    }
}
