package com.hotlinedrifter.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Base of all Models.
 */
public abstract class BaseModel {

    /**
     * Existing model types.
     */
    public enum ModelType { DRIFTER, SPIDER, JUDGEMENT, DBULLET, JBULLET, DATTACK, WALL, WATER }

    /**
     * X-position of the model.
     */
    private float   _x;

    /**
     * Y-position of the model.
     */
    private float   _y;

    /**
     * Angle of the model.
     */
    private float   _angle;

    /**
     * X-velocity of the model.
     */
    private float   _vx;

    /**
     * Y-velocity of the model.
     */
    private float   _vy;

    /**
     *  Animation time.
     */
    private float   _stateTime;

    /**
     * Removal flag.
     */
    private boolean _flagged;

    /**
     * Creates an object of Base Model.
     * @param x Body's x coordinate.
     * @param y Body's y coordinate.
     */
    public BaseModel(float x, float y) {
        _x = x;
        _y = y;
        _angle = 0f;
        _vx = 0f;
        _vy = 0f;

        _stateTime = 0f;
        _flagged = false;
    }

    /**
     * Returns Position of the model.
     * @return float value with the Y-position.
     */
    public Vector2 getPosition() {
        return new Vector2(_x, _y);
    }

    /**
     * Returns the angle of the model.
     * @return float value with the angle.
     */
    public float getAngle() {
        return _angle;
    }

    /**
     * Returns the velocity of the model.
     * @return float value with the velocity.
     */
    public Vector2 getVelocity() {
        return new Vector2(_vx, _vy);
    }
    /**
     * Returns State Time.
     * @return float value with stateTime.
     */
    public float getStateTime() {
        return _stateTime;
    }

    /**
     * Sets body position.
     * @param x body's x coordinate.
     * @param y body's y coordinate.
     */
    public void setPosition(float x, float y) {
        _x = x;
        _y = y;
    }

    /**
     * Sets body rotation.
     * @param angle Angle to rotate.
     */
    public void setRotation(float angle) {
        _angle = angle;
    }

    /**
     * Sets body position.
     * @param vx body's x-velocity.
     * @param vy body's y-velocity.
     */
    public void setVelocity(float vx, float vy) {
        _vx = vx;
        _vy = vy;
    }

    /**
     * Resets body's stateTime.
     */
    protected void resetStateTime() {
        _stateTime = 0f;
    }

    /**
     * Sets the flag value.
     * @param flagged flag value.
     */
    public void setFlagged(boolean flagged) {
        _flagged = flagged;
    }

    /**
     * Returns flag's value.
     * @return boolean flag's value.
     */
    public boolean isFlagged() {
        return _flagged;
    }

    /**
     * Update's stateTime.
     * @param delta time passed since last render.
     */
    public void update(float delta) {
        _stateTime += delta;
    }

    /**
     * Returns ModelType
     * @return ModelType value.
     */
    public abstract ModelType getModelType();
}
