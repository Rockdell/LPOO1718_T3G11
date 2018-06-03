package com.hotlinedrifter.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.model.BaseModel;
import static com.hotlinedrifter.screen.GameView.PIXEL_TO_METER;

/**
 * View of a game's entity.
 */
public abstract class BaseView {

    /**
     * Current frame to be drawn.
     */
    protected Sprite    _currentFrame;

    /**
     * Creates an object BaseView.
     * @param game Game the view belongs to.
     */
    public BaseView(HotlineDrifter game) {
        _currentFrame = createView(game);
    }

    /**
     * Updates the view, based on an entity model.
     * @param model Entity model we want the view to represent.
     */
    public void update(BaseModel model) {
        _currentFrame.setCenter(model.getPosition().x / PIXEL_TO_METER, model.getPosition().y / PIXEL_TO_METER);
    }

    /**
     * Draws the view.
     * @param batch SpriteBatch to be used to draw.
     */
    public void draw(SpriteBatch batch) {
        _currentFrame.draw(batch);
    }

    /**
     * Creates the view.
     * @param game Game the view belongs to.
     * @return Sprite to be used as the first frame.
     */
    protected abstract Sprite createView(HotlineDrifter game);
}
