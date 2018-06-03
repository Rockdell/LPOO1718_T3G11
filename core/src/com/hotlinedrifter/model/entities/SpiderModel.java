package com.hotlinedrifter.model.entities;

import com.badlogic.gdx.math.Vector2;

import static com.hotlinedrifter.screen.GameView.PIXEL_TO_METER;

/**
 * Spider Model
 */
public class SpiderModel extends EntityModel {

    /**
     * Creates a Spider Model
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public SpiderModel(float x, float y) {
        super(x, y);

        setHealth(50f, 0f);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.SPIDER;
    }

    @Override
    public float getDelay() {
        return 0f;
    }

    @Override
    public Vector2 getTilePosition() {
        return new Vector2(getPosition().x, getPosition().y - 7 * PIXEL_TO_METER);
    }

    @Override
    public float getEntityDamage() {
        return 10f;
    }

    @Override
    public float getEntityVelocity() {
        return 2f;
    }
}
