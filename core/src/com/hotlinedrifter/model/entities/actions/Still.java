package com.hotlinedrifter.model.entities.actions;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Still Action.
 */
public class Still extends BaseAction {

    /**
     * Creates a Still action object.
     * @param actor Actor of the action.
     * @param target Target of the action.
     */
    public Still(EntityModel actor, Vector2 target) {
        super(actor, target);
    }

    @Override
    public void doAction() {
        if(isInterruptable())
            _actor.setVelocity(0f, 0f);
    }

    @Override
    public ActionType getActionType() {
        return ActionType.STILL;
    }

}
