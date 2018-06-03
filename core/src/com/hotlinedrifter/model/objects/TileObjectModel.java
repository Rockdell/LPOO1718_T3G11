package com.hotlinedrifter.model.objects;

import com.badlogic.gdx.math.Rectangle;
import com.hotlinedrifter.model.BaseModel;

/**
 * Model of a Tile Object.
 */
public abstract class TileObjectModel extends BaseModel {

    /**
     * Shape of the tile.
     */
    private Rectangle _bounds;

    /**
     *  Creates an object of TileObjectModel.
     * @param bounds TileObject Box2D position.
     */
    TileObjectModel(Rectangle bounds) {
        super(bounds.getX(), bounds.getY());

        _bounds = bounds;
    }

    /**
     * Returns the bounds of the tile's shape.
     * @return Tile shape.
     */
    public Rectangle getBounds() {
        return _bounds;
    }
}

