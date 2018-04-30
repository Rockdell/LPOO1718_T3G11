package com.hotlinedrifter.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hotlinedrifter.HotlineDrifter;

/** Base view of an enemy. */
public class EnemyView extends EntityView {

    /** Texture for while the drifter is standing still. */
    private TextureRegion _still_down;

    /** Creates an object of EnemyView.
     * @param game Game the view belongs to. */
    public EnemyView(HotlineDrifter game) {
        super(game);
    }

    @Override
    protected Sprite createView(HotlineDrifter game) {
        //TODO later
        return null;
    }

    @Override
    protected void setNextFrame() {
        //TODO later
    }
}
