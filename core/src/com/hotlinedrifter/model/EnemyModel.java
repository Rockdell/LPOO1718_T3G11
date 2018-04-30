package com.hotlinedrifter.model;

import com.badlogic.gdx.physics.box2d.BodyDef;

/** Base model of an enemy. */
public class EnemyModel extends EntityModel{

    /** Creates an object of EnemyModel.
     * @param x X-position of the model.
     * @param y Y-position of the model. */
    public EnemyModel(float x, float y) {
        super(x, y, BodyDef.BodyType.DynamicBody);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.ENEMY;
    }
}
