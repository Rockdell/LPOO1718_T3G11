package com.hotlinedrifter.body.objects;

import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.model.objects.WaterModel;

/**
 * Water Body.
 */
public class WaterBody extends TileObjectBody {

    /**
     * Creates an object WaterBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    public WaterBody(World world, WaterModel model) {
        super(world, model, WATER_BODY);
    }
}
