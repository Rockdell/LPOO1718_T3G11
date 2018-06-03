package com.hotlinedrifter.body.objects;

import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.model.objects.WallModel;

/**
 * Wall Body.
 */
public class WallBody extends TileObjectBody {

    /**
     * Creates an object WallBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    public WallBody(World world, WallModel model) {
        super(world, model, WALL_BODY);
    }
}
