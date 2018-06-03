package com.hotlinedrifter.body.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.model.entities.SpiderModel;

/**
 * Spider Body.
 */
public class SpiderBody extends EntityBody {

    /**
     * Creates an object SpiderBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    public SpiderBody(World world, SpiderModel model) {
        super(world, model);

        createFixture(new float[] { 0,0, 0,14, 16,14, 16,0 }, 16, 14, ENEMY_BODY, (short) (DRIFTER_BODY | ENEMY_BODY | DBULLET_BODY | DATTACK_BODY | WALL_BODY | WATER_BODY));
    }
}
