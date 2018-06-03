package com.hotlinedrifter.body.attacks;

import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.model.attacks.DrifterAttackModel;

/**
 * Drifter's AttackBody.
 */
public class DrifterAttackBody extends AttackBody {

    /**
     * Creates an object DrifterAttackBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    public DrifterAttackBody(World world, DrifterAttackModel model) {

        super(world, model);

        createFixture(new float[] { 0,26, 0,52, 26,78, 52,78, 78,52, 78,26, 52,0, 26,0 }, 78, 78, DATTACK_BODY, ENEMY_BODY);
    }
}
