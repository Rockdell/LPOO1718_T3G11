package com.hotlinedrifter.model.bullets;

/**
 * Judgement Bullet's Model
 */
public class JudgementBulletModel extends BulletModel {

    /**
     * Creates a JudgementBulletModel object.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public JudgementBulletModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.JBULLET;
    }

    @Override
    public float getBulletDamage() {
        return 50f;
    }

    @Override
    public float getBulletVelocity() {
        return 5f;
    }
}
