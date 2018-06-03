package com.hotlinedrifter.model.entities;

import com.badlogic.gdx.math.Vector2;

import static com.hotlinedrifter.screen.GameView.PIXEL_TO_METER;

/**
 * Judgement Model
 */
public class JudgementModel extends EntityModel {

    /**
     * Creates Judgement Model.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public JudgementModel(float x, float y) {
        super(x, y);

        setHealth(500f, 0f);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.JUDGEMENT;
    }

    @Override
    public float getDelay() {
        return 0.5f;
    }

    @Override
    public Vector2 getTilePosition() {
        return new Vector2(getPosition().x, getPosition().y - 50 * PIXEL_TO_METER);
    }

    @Override
    public float getEntityDamage() {
        return 100f;
    }

    @Override
    public float getEntityVelocity() {
        return 1f;
    }
}
