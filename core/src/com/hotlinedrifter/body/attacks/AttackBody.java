package com.hotlinedrifter.body.attacks;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.body.BaseBody;
import com.hotlinedrifter.model.attacks.AttackModel;

/**
 * Attack Body.
 */
abstract class AttackBody extends BaseBody {

    /**
     * Creates an object AttackBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    AttackBody(World world, AttackModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);
    }
}
