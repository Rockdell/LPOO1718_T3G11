package com.hotlinedrifter.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.hotlinedrifter.model.GameModel;
import com.hotlinedrifter.model.entities.actions.*;
import com.hotlinedrifter.screen.GameView;

import static com.hotlinedrifter.screen.GameView.PIXEL_TO_METER;

/**
 * Catches and handles keyboard/mouse events.
 */
public class InputController extends InputAdapter {

    /**
     * Flag for the W key.
     */
    private static Vector2 W = new Vector2(0f, 1f);

    /**
     * Flag for the S key.
     */
    private static Vector2 S = new Vector2(0f, -1f);

    /**
     * Flag for the A key.
     */
    private static Vector2 A = new Vector2(-1f, 0f);

    /**
     * Flag for the D key.
     */
    private static Vector2 D = new Vector2(1f, 0f);

    /**
     * Singleton instance of this class.
     */
    private static InputController  _instance;

    /**
     * Drifter's next action.
     */
    private BaseAction              _nextAction;

    /**
     * Creates an object of InputController.
     */
    private InputController() {
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Returns the singleton instance of this class.
     * @return Singleton instance.
     */
    public static InputController getController() {

        if(_instance == null)
            _instance = new InputController();

        return _instance;
    }

    /**
     * Returns Drifter's next action.
     * @return BaseAction object.
     */
    BaseAction getNextAction() {
        BaseAction tmp = _nextAction;
        _nextAction = null;
        return tmp;
    }

    /**
     * Handles the user's inputs.
     */
    public void handleInputs() {

        if(_nextAction != null)
            return;

        Vector2 direction = new Vector2(0f, 0f);

        boolean up, down, left, right;

        if(up = Gdx.input.isKeyPressed(Input.Keys.W))
            direction.add(W);

        if(down = Gdx.input.isKeyPressed(Input.Keys.S))
            direction.add(S);

        if(left = Gdx.input.isKeyPressed(Input.Keys.A))
            direction.add(A);

        if(right = Gdx.input.isKeyPressed(Input.Keys.D))
            direction.add(D);

        if(!(up || down || left || right))
            _nextAction = new Still(GameModel.getInstance().getDrifter(), GameModel.getInstance().getDrifter().getAction().getFacingDirection());
        else if(up && down || left && right)
            _nextAction = new Still(GameModel.getInstance().getDrifter(), S);
        else
            _nextAction = new Walk(GameModel.getInstance().getDrifter(), direction);
    }

    /**
     * Returns the cursor's position.
     * @return Vector2 with the cursor's x and y variables.
     */
    private Vector2 getCursorPosition() {
        Vector3 cursorPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        GameView.getViewport().getCamera().unproject(cursorPos);

        return new Vector2(cursorPos.x * PIXEL_TO_METER, cursorPos.y * PIXEL_TO_METER);
    }

    /**
     * Returns True if space key is pressed and creates a Dash object from BaseAction, false otherwise.
     * @return boolean True if space key is pressed, false otherwise.
     */
    @Override
    public boolean keyDown(int keycode) {

        if(_nextAction != null)
            return false;

        if(GameModel.getInstance().getDrifter().getActionDelay() < 0) {
            if (keycode == Input.Keys.SPACE) {
                if(GameModel.getInstance().getDrifter().getAction().getFacingDirection().x == -1 || GameModel.getInstance().getDrifter().getAction().getFacingDirection().x == 1)
                    _nextAction = new Dash(GameModel.getInstance().getDrifter(), GameModel.getInstance().getDrifter().getAction().getFacingDirection());
            }
        }

        return false;
    }

    /**
     * Returns True if a mouse button is pressed and creates a new Action according the clicked button, false otherwise.
     * @return boolean True if a mouse button is pressed, false otherwise.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(_nextAction != null)
            return false;

        if(GameModel.getInstance().getDrifter().getActionDelay() < 0) {
            if (button == Input.Buttons.LEFT)
                _nextAction = new Shoot(GameModel.getInstance().getDrifter(), getCursorPosition());
            else if (button == Input.Buttons.RIGHT)
                _nextAction = new Attack(GameModel.getInstance().getDrifter(), getCursorPosition());
        }

        return true;
    }
}
