package com.hotlinedrifter.view.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.model.BaseModel;
import com.hotlinedrifter.model.entities.DrifterModel;
import com.hotlinedrifter.model.entities.actions.Attack;
import com.hotlinedrifter.model.entities.actions.BaseAction;
import com.hotlinedrifter.view.BaseView;

/**
 * View of a drifter.
 */
public class DrifterView extends BaseView {

    /**
     * Textures for while the drifter is standing still (for all 4 sides).
     */
    private TextureRegion               _still_up, _still_down, _still_left, _still_right;

    /**
     * Textures for while the drifter is running (for all 4 sides).
     */
    private Animation<TextureRegion>    _walk_up, _walk_down, _walk_left, _walk_right;

    /**
     * Texture for the Drifter's Dash.
     */
    private Animation<TextureRegion>    _dash;

    /**
     * Texture for the Drifter's shooting animation.
     */
    private Animation<TextureRegion>    _shoot_up, _shoot_down, _shoot_left, _shoot_right;

    /**
     */
    private Animation<TextureRegion>    _attack;

    /**
     * Shooting sound.
     */
    private Sound                       _bulletSound;

    /**
     * Attack sound.
     */
    private Sound                       _attackSound;

    /**
     * Creates an object of DrifterView.
     * @param game Game the view belongs to.
     */
    public DrifterView(HotlineDrifter game) {
        super(game);

        _bulletSound = game.getAssetManager().get("audio/bulletSound.wav",Sound .class);
        _attackSound = game.getAssetManager().get("audio/attackSound.wav", Sound.class);
    }

    @Override
    protected Sprite createView(HotlineDrifter game) {
        loadWalkAnimation(game);
        loadDashAnimation(game);
        loadShootAnimation(game);
        loadAttackAnimation(game);
        loadStillSprites(game);

        return new Sprite(_still_down);
    }

    /**
     * Loads the animations of the drifter.
     * @param game Game the view belongs to.
     */
    private void loadWalkAnimation(HotlineDrifter game) {

        Texture walkSheet = game.getAssetManager().get("images/drifter_walk.png");
        TextureRegion[][] walkRegion = TextureRegion.split(walkSheet, walkSheet.getWidth() / 12, walkSheet.getHeight() / 4);

        _walk_up = new Animation<TextureRegion>(0.08333f, walkRegion[0]);
        _walk_down = new Animation<TextureRegion>(0.08333f, walkRegion[1]);
        _walk_left = new Animation<TextureRegion>(0.08333f, walkRegion[2]);
        _walk_right = new Animation<TextureRegion>(0.08333f, walkRegion[3]);
    }

    /**
     * Loads the Drifter's Dash animation.
     * @param game Game the view belongs to.
     */
    private void loadDashAnimation(HotlineDrifter game) {

        Texture dashSheet = game.getAssetManager().get("images/drifter_dash.png");
        TextureRegion[][] dashRegion = TextureRegion.split(dashSheet, dashSheet.getWidth() / 16, dashSheet.getHeight());

        _dash = new Animation<TextureRegion>(0.03f, dashRegion[0]);
    }

    /**
     * Loads the Drifter's shooting animation.
     * @param game Game the view belongs to.
     */
    private void loadShootAnimation(HotlineDrifter game) {

        Texture shootSheet = game.getAssetManager().get("images/drifter_shoot.png");
        TextureRegion[][] shootRegion = TextureRegion.split(shootSheet, shootSheet.getWidth() / 10, shootSheet.getHeight() / 4);

        _shoot_up = new Animation<TextureRegion>(0.02f, shootRegion[0]);
        _shoot_down = new Animation<TextureRegion>(0.02f, shootRegion[1]);
        _shoot_left = new Animation<TextureRegion>(0.02f, shootRegion[2]);
        _shoot_right = new Animation<TextureRegion>(0.02f, shootRegion[3]);
    }

    /**
     * Loads the Drifter's attack animation.
     * @param game Game the view belongs to.
     */
    private void loadAttackAnimation(HotlineDrifter game) {

        Texture attackSheet = game.getAssetManager().get("images/drifter_attack.png");
        TextureRegion[][] attackRegion = TextureRegion.split(attackSheet, attackSheet.getWidth() / 20, attackSheet.getHeight());

        _attack = new Animation<TextureRegion>(0.05f, attackRegion[0]);
    }

    /**
     * Loads the sprites of the drifter.
     * @param game Game the view belongs to.
     */
    private void loadStillSprites(HotlineDrifter game) {

        Texture stillSheet = game.getAssetManager().get("images/drifter_still.png");
        TextureRegion[][] stillRegion = TextureRegion.split(stillSheet, stillSheet.getWidth() / 4, stillSheet.getHeight());

        _still_up = stillRegion[0][0];
        _still_down = stillRegion[0][1];
        _still_left = stillRegion[0][2];
        _still_right = stillRegion[0][3];
    }

    @Override
    public void update(BaseModel model) {

        super.update(model);

        BaseAction action = ((DrifterModel) model).getAction();

        switch(action.getActionType()) {
            case STILL:
                if(action.getFacingDirection().x == -1)
                    _currentFrame.setRegion(_still_left);
                else if(action.getFacingDirection().x == 1)
                    _currentFrame.setRegion(_still_right);
                else if(action.getFacingDirection().y == 1)
                    _currentFrame.setRegion(_still_up);
                else
                    _currentFrame.setRegion(_still_down);
                break;
            case WALK:
                if(action.getFacingDirection().x == -1)
                    _currentFrame.setRegion(_walk_left.getKeyFrame(model.getStateTime(), true));
                else if(action.getFacingDirection().x == 1)
                    _currentFrame.setRegion(_walk_right.getKeyFrame(model.getStateTime(), true));
                else if(action.getFacingDirection().y == 1)
                    _currentFrame.setRegion(_walk_up.getKeyFrame(model.getStateTime(), true));
                else
                    _currentFrame.setRegion(_walk_down.getKeyFrame(model.getStateTime(), true));
                break;
            case DASH:
                if(action.isInterruptable())
                    action.setInterruptable(false);

                if(!_dash.isAnimationFinished(model.getStateTime())) {
                    _currentFrame.setRegion(_dash.getKeyFrame(model.getStateTime(), false));

                    if(action.getFacingDirection().x == -1)
                        _currentFrame.flip(true, false);
                }
                else
                    action.setInterruptable(true);
                break;
            case SHOOT:

                if(action.isInterruptable())
                    action.setInterruptable(false);

                Animation<TextureRegion> nextAnimation;

                if(action.getFacingDirection().x == -1)
                    nextAnimation = _shoot_left;
                else if(action.getFacingDirection().x == 1)
                    nextAnimation = _shoot_right;
                else if(action.getFacingDirection().y == 1)
                    nextAnimation = _shoot_up;
                else
                    nextAnimation = _shoot_down;

                 if(nextAnimation.getKeyFrame(0) == nextAnimation.getKeyFrame(model.getStateTime()))
                     _bulletSound.play(0.2f);

                if(!nextAnimation.isAnimationFinished(model.getStateTime()))
                    _currentFrame.setRegion(nextAnimation.getKeyFrame(model.getStateTime(), false));
                else
                    action.setInterruptable(true);
                break;
            case ATTACK:
                if(action.isInterruptable())
                    action.setInterruptable(false);

                if(_attack.getKeyFrame(0) == _attack.getKeyFrame(model.getStateTime()))
                    _attackSound.play();

                if(!_attack.isAnimationFinished(model.getStateTime())) {
                    _currentFrame.setRegion(_attack.getKeyFrame(model.getStateTime(), false));

                    if(action.getFacingDirection().x == -1 || action.getFacingDirection().y == -1)
                        _currentFrame.flip(true, false);
                }
                else {
                    action.setInterruptable(true);
                    ((Attack) action).getAttackModel().setFlagged(true);
                }
                break;
        }
    }
}