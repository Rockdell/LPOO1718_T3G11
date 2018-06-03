package com.hotlinedrifter.model.objects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Model of water.
 */
public class WaterModel extends TileObjectModel {

    /**
     * Creates a WaterModel.
     * @param bounds TileObject Box2D position.
     */
    public WaterModel(Rectangle bounds) {
        super(bounds);
    }

    @Override
    public TileObjectModel.ModelType getModelType() {
        return ModelType.WATER;
    }
}