package com.hotlinedrifter.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/** Catches and handles keyboard/mouse events. */
public class InputController extends InputAdapter {

    /** Possible states for the drifter. */
    public enum State { STILL, WALKING, ATTACKING, DASHING }

    /** Singleton instance of this class */
    private static InputController  _instance;

    /** Flag for the W key. */
    public static char W = 0x01;

    /** Flag for the S key. */
    public static char S = 0x01 << 1;

    /** Flag for the A key. */
    public static char A = 0x01 << 2;

    /** Flag for the D key. */
    public static char D = 0x01 << 3;

    /** Drifter's current state. */
    private State                   _currentState;

    /** Drifter's current direction. */
    private char                    _currentDirection;

    /** Creates an object of InputController. */
    private InputController() {

        Gdx.input.setInputProcessor(this);

        _currentState = State.STILL;
        _currentDirection = S;
    }

    /** Returns the singleton instance of this class.
     * @return Singleton instance. */
    public static InputController getController() {

        if(_instance == null)
            _instance = new InputController();

        return _instance;
    }

    /** Handles the user's inputs. */
    public void handleInputs() {

        char direction = 0x00;

        boolean up, down, left, right;

        if(up = Gdx.input.isKeyPressed(Input.Keys.W))
            direction |= W;

        if(down = Gdx.input.isKeyPressed(Input.Keys.S))
            direction |= S;

        if(left = Gdx.input.isKeyPressed(Input.Keys.A))
            direction |= A;

        if(right = Gdx.input.isKeyPressed(Input.Keys.D))
            direction |= D;

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            _currentState = State.ATTACKING;
            return;
        }

        // No keys were pressed
        if(!(up || down || left || right)) {
            if (_currentState == State.WALKING)
                _currentState = State.STILL;
        }
        // Opposite directions
        else if(up && down || left && right) {
            _currentState = State.STILL;
            _currentDirection = S;
        }
        else {
            _currentState = State.WALKING;
            _currentDirection = direction;
        }
    }

    /** Returns the drifter's current state.
     * @return Drifter's current state. */
    public State getCurrentState() {
        return _currentState;
    }

    /** Returns the drifter's current direction.
     * @return Drifter's current direction. */
    public char getCurrentDirection() {
        return _currentDirection;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(button == Input.Buttons.LEFT)
            _currentState = State.ATTACKING;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
