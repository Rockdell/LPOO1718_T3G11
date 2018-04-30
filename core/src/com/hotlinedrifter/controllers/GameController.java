package com.hotlinedrifter.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.hotlinedrifter.layout.GameLevel;
import com.hotlinedrifter.model.DrifterModel;
import com.hotlinedrifter.model.EnemyModel;

import java.util.List;

/** Controls physics and updates models. */
public class GameController implements ContactListener {

    /** Singleton instance of this class */
    private static GameController _instance;

    /** Step of a entity */
    private static final float STEP = 100f;

    /** Physics' world */
    private final World         _world;

    /** Creates an object GameController. */
    private GameController() {
        _world = new World(new Vector2(0f, 0f), true);
        //_world.setContactListener(this);
    }

     /** Returns the singleton instance of this class.
      * @return Singleton instance. */
    public static GameController getController() {

        if(_instance == null)
            _instance = new GameController();

        return _instance;
    }

    /** Returns the physics' world.
     * @return Physic' world. */
    public World getWorld() {
        return _world;
    }

    /** Updates the models and makes the next step in the physics' world.
     * @param delta Time in seconds since the last frame. */
    public void update(float delta) {

        _updateDrifter(delta);
        _updateEnemies(delta);

        _world.step(delta, 6, 2);
    }

    /** Updates the drifter's position.
     * @param delta Time in seconds since the last frame */
    private void _updateDrifter(float delta) {

        if(InputController.getController().getCurrentState() != InputController.State.WALKING)
            return;

        DrifterModel drifterModel = GameLevel.getInstance().getDrifter();
        char direction = InputController.getController().getCurrentDirection();

        if((direction & InputController.W) == InputController.W)
            drifterModel.moveTo(drifterModel.getX(), drifterModel.getY() + STEP * delta);

        if((direction & InputController.S) == InputController.S)
            drifterModel.moveTo(drifterModel.getX(), drifterModel.getY() - STEP * delta);

        if((direction & InputController.A) == InputController.A)
            drifterModel.moveTo(drifterModel.getX() - STEP * delta, drifterModel.getY());

        if((direction & InputController.D) == InputController.D)
            drifterModel.moveTo(drifterModel.getX() + STEP * delta, drifterModel.getY());
    }

    /** Updates the enemies's position.
     * @param delta Time in seconds since the last frame */
    private void _updateEnemies(float delta) {

        //TODO

        List<EnemyModel> enemies = GameLevel.getInstance().getEnemies();

        for(EnemyModel enemy : enemies)
            enemy.moveTo(enemy.getX(), enemy.getY());
    }

    @Override
    public void beginContact(Contact contact) {
        //TODO
    }

    @Override
    public void endContact(Contact contact) {
        //TODO
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}