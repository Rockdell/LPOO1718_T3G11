package com.hotlinedrifter.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.model.BaseModel;
import com.hotlinedrifter.model.entities.SpiderModel;
import com.hotlinedrifter.model.entities.actions.BaseAction;
import com.hotlinedrifter.view.BaseView;

/**
 * View of a Spider.
 */
public class SpiderView extends BaseView {

    /**
     * Texture for the Spider's standing still animation.
     */
    private TextureRegion               _still;

    /**
     * Texture for the Spider's walk animation.
     */
    private Animation<TextureRegion>    _walk;

    /**
     * Creates an object of SpiderView.
     * @param game Game the view belongs to.
     */
    public SpiderView(HotlineDrifter game) {
        super(game);
    }

    @Override
    protected Sprite createView(HotlineDrifter game) {
        loadWalkAnimation(game);
        loadStillSprite(game);

        return new Sprite(_still);
    }

    /**
     * Loads the animations of the spider.
     * @param game Game the view belongs to.
     */
    private void loadWalkAnimation(HotlineDrifter game) {

        Texture walkSheet = game.getAssetManager().get("images/spider_walk.png");
        TextureRegion[][] walkRegion = TextureRegion.split(walkSheet, walkSheet.getWidth() / 8, walkSheet.getHeight());

        _walk = new Animation<TextureRegion>(0.08333f, walkRegion[0]);
    }

    /**
     * Loads the sprites of the spider.
     * @param game Game the view belongs to.
     */
    private void loadStillSprite(HotlineDrifter game) {

        Texture stillSheet = game.getAssetManager().get("images/spider_still.png");
        TextureRegion[][] stillRegion = TextureRegion.split(stillSheet, stillSheet.getWidth(), stillSheet.getHeight());

        _still = stillRegion[0][0];
    }

    @Override
    public void update(BaseModel model) {

        super.update(model);

        BaseAction action = ((SpiderModel) model).getAction();

        switch(action.getActionType()) {
            case STILL:
                _currentFrame.setRegion(_still);
                break;
            case WALK:
                _currentFrame.setRegion(_walk.getKeyFrame(model.getStateTime(), true));

                if(action.getDirection().x < 0)
                    _currentFrame.flip(true, false);
                break;
        }

        _currentFrame.setScale(0.5f);
    }
}
