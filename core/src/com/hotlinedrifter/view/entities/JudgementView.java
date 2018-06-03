package com.hotlinedrifter.view.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.model.BaseModel;
import com.hotlinedrifter.model.entities.JudgementModel;
import com.hotlinedrifter.model.entities.actions.BaseAction;
import com.hotlinedrifter.view.BaseView;

/**
 * View of a Judgement.
 */
public class JudgementView extends BaseView {

    /**
     * Texture for the Judgement's walk animation.
     */
    private Animation<TextureRegion> _walk;

    /**
     * Texture for the Judgement's shooting animation.
     */
    private Animation<TextureRegion> _shoot;

    /**
     * Shooting sound.
     */
    private Sound                    _shootingSound;

    /**
     * Creates an object of JudgementView.
     * @param game Game the view belongs to.
     */
    public JudgementView(HotlineDrifter game) {
        super(game);

        _shootingSound = game.getAssetManager().get("audio/judgementBullet.wav", Sound .class);
    }

    @Override
    protected Sprite createView(HotlineDrifter game) {

        loadWalkAnimation(game);
        loadShootAnimation(game);

        return new Sprite(_walk.getKeyFrames()[0]);
    }

    /**
     * Loads the animations of the judgement.
     * @param game Game the view belongs to.
     */
    private void loadWalkAnimation(HotlineDrifter game) {

        Texture walkSheet = game.getAssetManager().get("images/judgement_walk.png");
        TextureRegion[][] bulletRegion = TextureRegion.split(walkSheet, walkSheet.getWidth() / 4, walkSheet.getHeight());

        _walk = new Animation<TextureRegion>(0.25f, bulletRegion[0]);
    }

    /**
     * Loads the Judgement's shooting animation.
     * @param game Game the view belongs to.
     */
    private void loadShootAnimation(HotlineDrifter game) {

        Texture shootSheet = game.getAssetManager().get("images/judgement_shoot.png");
        TextureRegion[][] shootRegion = TextureRegion.split(shootSheet, shootSheet.getWidth() / 12, shootSheet.getHeight());

        _shoot = new Animation<TextureRegion>(0.03f, shootRegion[0]);
    }

    @Override
    public void update(BaseModel model) {

        super.update(model);

        BaseAction action = ((JudgementModel) model).getAction();

        switch(action.getActionType()) {
            case STILL:
            case WALK:
                _currentFrame.setRegion(_walk.getKeyFrame(model.getStateTime(), true));

                if(action.getDirection().x < 0)
                    _currentFrame.flip(true, false);

                break;
            case SHOOT:

                if(action.isInterruptable())
                    action.setInterruptable(false);

                if(_shoot.getKeyFrame(0) == _shoot.getKeyFrame(model.getStateTime()))
                    _shootingSound.play();

                if(!_shoot.isAnimationFinished(model.getStateTime())) {
                    _currentFrame.setRegion(_shoot.getKeyFrame(model.getStateTime(), false));

                    if (action.getDirection().x < 0)
                        _currentFrame.flip(true, false);
                }
                else
                    action.setInterruptable(true);
        }

    }
}
