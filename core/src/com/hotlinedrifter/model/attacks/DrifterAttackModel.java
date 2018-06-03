package com.hotlinedrifter.model.attacks;

/**
 * Drifter's Attack body.
 */
public class DrifterAttackModel extends AttackModel {

    /**
     * Creates a DrifterAttackModel object.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public DrifterAttackModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.DATTACK;
    }

    @Override
    public float getAttackDamage() {
        return 40f;
    }
}
