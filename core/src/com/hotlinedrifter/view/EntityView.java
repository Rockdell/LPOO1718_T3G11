package com.hotlinedrifter.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.model.EntityModel;

/** View of a game's entity. */
public abstract class EntityView {

    /** Current frame to be drawn. */
    protected Sprite    _currentFrame;
    protected float     _stateTime;

    /** Creates an object EntityView.
     * @param game Game the view belongs to. */
    EntityView(HotlineDrifter game) {
        _currentFrame = createView(game);
    }

    /** Updates the view, based on an entity model.
     * @param model Entity model we want the view to represent. */
    public void update(EntityModel model) {
        float newStateTime = model.getStateTime() + Gdx.graphics.getDeltaTime();
        model.setStateTime(newStateTime);

        _stateTime = newStateTime;
        _currentFrame.setCenter(model.getX(), model.getY());
    }

    /** Draws the view.
     * @param batch SpriteBatch to be used to draw. */
    public void draw(SpriteBatch batch) {
        setNextFrame();
        _currentFrame.draw(batch);
    }

    /** Creates the view.
     * @param game Game the view belongs to.
     * @return Sprite to be used as the first frame. */
    protected abstract Sprite createView(HotlineDrifter game);

    /** Chooses the next frame to be drawn, based on the state of the model. */
    protected abstract void setNextFrame();
}
