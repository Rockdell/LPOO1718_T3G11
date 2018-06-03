package com.hotlinedrifter.body.objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.body.BaseBody;
import com.hotlinedrifter.model.objects.TileObjectModel;

/**
 * Tile Object Body
 */
public abstract class TileObjectBody extends BaseBody {

    /**
     * Creates an object TileObjectBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     * @param category Category of the body.
     */
    TileObjectBody(World world, TileObjectModel model, short category) {

        super(world, model, BodyDef.BodyType.StaticBody);

        createFixture(model.getBounds(), category);
    }
}
