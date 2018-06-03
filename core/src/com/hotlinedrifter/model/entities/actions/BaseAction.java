package com.hotlinedrifter.model.entities.actions;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Base Class Action for all existing actions.
 */
public abstract class BaseAction {

    /**
     * Types of existing Actions.
     */
    public enum ActionType { STILL, WALK, DASH, SHOOT, ATTACK }

    /**
     * EntityModel of the action.
     */
    final EntityModel _actor;

    /**
     * Direction of the Action.
     */
    private final Vector2       _direction;

    /**
     * If the action is interruptable or not.
     */
    private boolean             _interruptable;

    /**
     * Creates a BaseAction object.
     * @param actor Entity Model associated with the Action.
     * @param target Action's Target.
     */
    public BaseAction(EntityModel actor, Vector2 target) {
        _actor = actor;
        _direction = target;
        _interruptable = true;
    }

    /**
     * Acts accordingly to the current action.
     */
    public abstract void doAction();

    /**
     * Returns direction of the action.
     * @return Vector2 containing action's direction.
     */
    public Vector2 getDirection() {
        return _direction;
    }

    /**
     * Returns Direction of the action.
     * @return Vector2 containing action's direction.
     */
    public Vector2 getFacingDirection() {

        if(_direction.y >= _direction.x && _direction.y <= -_direction.x)
            return new Vector2(-1f, 0f);
        else if(_direction.y <= _direction.x && _direction.y >= -_direction.x)
           return new Vector2(1f, 0f);
        else if(_direction.y > _direction.x && _direction.y > -_direction.x)
            return new Vector2(0f, 1f);
        else if(_direction.y < _direction.x && _direction.y < -_direction.x)
            return new Vector2(0f, -1f);
        else
            return null;
    }

    /**
     * Sets the action as interruptable or not.
     * @param interruptable True if interruptable, false otherwise.
     */
    public void setInterruptable(boolean interruptable) {
        _interruptable = interruptable;
    }

    /**
     * Returns the interruptable value.
     * @return boolean interruptable.
     */
    public boolean isInterruptable() {
        return _interruptable;
    }

    /**
     * Returns Action type unique for each subclass.
     * @return ActionType value.
     */
    public abstract ActionType getActionType();
}
