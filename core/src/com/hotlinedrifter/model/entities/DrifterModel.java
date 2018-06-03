package com.hotlinedrifter.model.entities;

import com.badlogic.gdx.math.Vector2;

import static com.hotlinedrifter.screen.GameView.PIXEL_TO_METER;

/**
 * Drifter's Model.
 * */
public class DrifterModel extends EntityModel {

    /**
     * Creates a DrifterModel object.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public DrifterModel(float x, float y) {
        super(x, y);

        setHealth(100f, 100f);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.DRIFTER;
    }

    @Override
    public float getDelay() {
        return 0.1f;
    }

    @Override
    public Vector2 getTilePosition() {
        return new Vector2(getPosition().x, getPosition().y - 16 * PIXEL_TO_METER);
    }

    @Override
    public float getEntityDamage() {
        return 25f;
    }

    @Override
    public float getEntityVelocity() {
        return 5f;
    }
}
