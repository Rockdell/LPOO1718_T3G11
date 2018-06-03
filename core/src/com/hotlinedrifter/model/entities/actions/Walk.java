package com.hotlinedrifter.model.entities.actions;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Walk Action
 */
public class Walk extends BaseAction {

    /**
     * Creates a Walk action object.
     * @param actor Actor of the action.
     * @param target Target of the action.
     */
    public Walk(EntityModel actor, Vector2 target) {
        super(actor, target);
    }

    @Override
    public void doAction() {

        if(isInterruptable())
            _actor.setVelocity(getDirection().x * _actor.getEntityVelocity(), getDirection().y * _actor.getEntityVelocity());
    }

    @Override
    public ActionType getActionType() {
        return ActionType.WALK;
    }

}
