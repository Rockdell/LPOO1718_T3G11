package com.hotlinedrifter.model;

import com.badlogic.gdx.physics.box2d.BodyDef;

/** Model of a drifter. */
public class DrifterModel extends EntityModel {

    /** Creates an object of DrifterModel.
     * @param x X-position of the model.
     * @param y Y-position of the model. */
    public DrifterModel(float x, float y) {
        super(x, y, BodyDef.BodyType.DynamicBody);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.DRIFTER;
    }
}
