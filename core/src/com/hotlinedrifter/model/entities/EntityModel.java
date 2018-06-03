package com.hotlinedrifter.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.model.BaseModel;
import com.hotlinedrifter.model.entities.actions.BaseAction;
import com.hotlinedrifter.model.entities.actions.Still;

/**
 * Entity's model.
 */
public abstract class EntityModel extends BaseModel {

    /**
     * Current health.
     */
    private float       _hp;

    /**
     * Current energy shield.
     */
    private float       _es;

    /**
     * Current action.
     */
    private BaseAction  _action;

    /**
     * Delay between actions.
     */
    private float       _actionDelay;

    /**
     * Creates a EntityModel's object.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    EntityModel(float x, float y) {
        super(x, y);

        _action = new Still(this, new Vector2(0f, -1f));
        _actionDelay = 0f;
    }

    /**
     * Returns current health.
     * @return Current health.
     */
    public float getHp() {
        return _hp;
    }

    /**
     * Returns current energy shield.
     * @return Current energy shield.
     */
    public float getEs() {
        return _es;
    }

    /**
     * Sets the health and energy shield of the entity.
     * @param hp New health.
     * @param es New energy shield
     */
    void setHealth(float hp, float es) {
        _hp = hp;
        _es = es;
    }

    /**
     * Checks if the entity is dead.
     * @return True if dead, false otherwise.
     */
    public boolean isDead() {
        return _hp < 0;
    }

    /**
     * Updates the health and the energy shield values.
     * @param damage Damage taken.
     * @return True if dead, false otherwise.
     */
    public boolean hit(float damage) {

        _es -= damage;

        if(_es < 0) {
            _hp += _es;
            _es = 0;
        }

        return isDead();
    }

    /**
     * Returns current action.
     * @return Current action.
     */
    public BaseAction getAction() {
        return _action;
    }

    /**
     * Sets current action.
     * @param action New action.
     */
    public void setAction(BaseAction action) {

        if(_action.isInterruptable()) {

            if (action.getActionType() != _action.getActionType() || action.getDirection().x != _action.getDirection().x || action.getDirection().y != _action.getDirection().y)
                resetStateTime();

            _action = action;
        }
    }

    /**
     * Returns delay between actions.
     * @return Delay between actions.
     */
    public float getActionDelay() {
        return _actionDelay;
    }

    /**
     * Sets delay between actions.
     * @param actionDelay Delay between actions.
     */
    public void setActionDelay(float actionDelay) {
        _actionDelay = actionDelay;
    }

    @Override
    public void update(float delta) {

        if(this instanceof DrifterModel) {
            _es += 2 * delta;

            if(_es > 100f)
                _es = 100f;
        }

        if(_action.isInterruptable())
            _actionDelay -= delta;

        _action.doAction();

        super.update(delta);
    }

    /**
     * Returns current delay.
     * @return Current delay.
     */
    public abstract float getDelay();

    /**
     * Returns the position of the entity in the tilemap.
     * @return Position of the entity in the tilemap.
     */
    public abstract Vector2 getTilePosition();

    /**
     * Returns Entity Damage.
     * @return float value.
     */
    public abstract float getEntityDamage();

    /**
     * Returns Entity Velocity.
     * @return float value.
     */
    public abstract float getEntityVelocity();

}