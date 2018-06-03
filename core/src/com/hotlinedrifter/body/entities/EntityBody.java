package com.hotlinedrifter.body.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.body.BaseBody;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Entity Body.
 */
abstract class EntityBody extends BaseBody {

    /**
     * Creates an object EntityBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    EntityBody(World world, EntityModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);
    }
}
