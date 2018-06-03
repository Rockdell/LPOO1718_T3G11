package com.hotlinedrifter.body.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.model.entities.DrifterModel;

/**
 * Drifter Body.
 */
public class DrifterBody extends EntityBody {

    /**
     * Creates an object DrifterBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    public DrifterBody(World world, DrifterModel model) {
        super(world, model);

        createFixture(new float[] { 0,0, 0,32, 12,32, 12,0 }, 12, 32, DRIFTER_BODY, (short) (ENEMY_BODY | JBULLET_BODY | WALL_BODY | WATER_BODY));
    }
}
