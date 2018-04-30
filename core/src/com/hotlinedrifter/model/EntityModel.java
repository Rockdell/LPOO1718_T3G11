package com.hotlinedrifter.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.hotlinedrifter.controllers.GameController;

/** Model of a game's entity. */
public abstract class EntityModel {

    /** Possible model's types. */
    public enum ModelType { DRIFTER, ENEMY }

    /** Physics' body of the model. */
    private final Body  _body;

    /** State time for the animations. */
    private float       _stateTime = 0f;

    /** Creates an object of EntityModel.
     * @param x X-position of the model.
     * @param y Y-position of the model.
     * @param type Type of physics' body. */
    EntityModel(float x, float y, BodyDef.BodyType type) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(x, y);

        _body = GameController.getController().getWorld().createBody(bodyDef);
        //_body.setUserData(this);

        PolygonShape polygon = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        polygon.setAsBox(7, 15);
        fixtureDef.shape = polygon;

        _body.createFixture(fixtureDef);
        polygon.dispose();

        //TODO
        //createFixture(...)
    }

    /** Returns the X-position of the model.
     * @return X-position of the model. */
    public float getX() {
        return _body.getPosition().x;
    }

    /** Returns the Y-position of the model.
     * @return Y-position of the model. */
    public float getY() {
        return _body.getPosition().y;
    }

    /** Returns the state time of the model.
     * @return State time of the model. */
    public float getStateTime() {
        return _stateTime;
    }

    /** Sets the state time of the model.
     * @param stateTime State time to be set. */
    public void setStateTime(float stateTime) {
        _stateTime = stateTime;
    }

    /** Moves the model to a certain position.
     * @param x New X-position of the model.
     * @param y New Y-position of the model. */
    public void moveTo(float x, float y) {
        _body.setTransform(x, y, 0);
    }

    /** Helper method to create a polygon fixture represented by a set of vertexes.
     * @param vertexes The vertexes defining the fixture in pixels so it is easier to get them from a bitmap image.
     * @param width The width of the bitmap the vertexes where extracted from.
     * @param height The height of the bitmap the vertexes where extracted from.
     * @param density The density of the fixture. How heavy it is in relation to its area.
     * @param friction The friction of the fixture. How slippery it is.
     * @param restitution The restitution of the fixture. How much it bounces.
     * @param category
     * @param mask */
    final void createFixture(float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        // Transform pixels into meters, center and invert the y-coordinate
        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;   // center the vertex x-coordinate
            if (i % 2 != 0) vertexes[i] -= height / 2;  // center the vertex y-coordinate
            if (i % 2 != 0) vertexes[i] *= -1;          // invert the y-coordinate

            //vertexes[i] *= PIXEL_TO_METER;              // scale from pixel to meter
        }

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;

        _body.createFixture(fixtureDef);

        polygon.dispose();
    }

    /** Return the model's type.
     * @return Model's type. */
    public abstract ModelType getModelType();
}
