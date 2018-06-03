package com.hotlinedrifter.model.bullets;

/**
 * Drifter Bullet's Model
 */
public class DrifterBulletModel extends BulletModel {

    /**
     * Creates a DrifterBulletBody object.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public DrifterBulletModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.DBULLET;
    }

    @Override
    public float getBulletDamage() {
        return 20f;
    }

    @Override
    public float getBulletVelocity() {
        return 20f;
    }
}