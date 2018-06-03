package com.hotlinedrifter.model.entities.actions;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Dash Action.
 */
public class Dash extends BaseAction {

    /**
     * Creates a Dash action object.
     * @param actor Actor of the action.
     * @param target Target of the action.
     */
    public Dash(EntityModel actor, Vector2 target) {
        super(actor, target);
    }

    @Override
    public void doAction() {

        if(isInterruptable()) {

            _actor.setActionDelay(_actor.getDelay());

            _actor.setVelocity(getFacingDirection().x * _actor.getEntityVelocity() * 2f, getFacingDirection().y * _actor.getEntityVelocity() * 2f);
        }
    }

    @Override
    public ActionType getActionType() {
        return ActionType.DASH;
    }
}
