package com.hotlinedrifter.model.objects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Model of a wall.
 */
public class WallModel extends TileObjectModel {

    /**
     * Creates a WallModel.
     * @param bounds TileObject Box2D position.
     */
    public WallModel(Rectangle bounds) {
        super(bounds);
    }

    @Override
    public TileObjectModel.ModelType getModelType() {
        return ModelType.WALL;
    }
}