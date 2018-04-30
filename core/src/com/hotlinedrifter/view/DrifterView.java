package com.hotlinedrifter.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.controllers.InputController;

/** View of a drifter. */
public class DrifterView extends EntityView {

    /** Textures for while the drifter is standing still (for all 4 sides). */
    private TextureRegion               _still_up, _still_down, _still_left, _still_right;

    /** Textures for while the drifter is running (for all 4 sides). */
    private Animation<TextureRegion>    _walk_up, _walk_down, _walk_left, _walk_right;

    /** Textures for while the drifter is using a charge attack. */
    private Animation<TextureRegion>    _charged_attack;

    /** Creates an object of DrifterView.
     * @param game Game the view belongs to. */
    public DrifterView(HotlineDrifter game) {
        super(game);
    }

    @Override
    protected Sprite createView(HotlineDrifter game) {
        _loadAnimations(game);
        _loadSprites(game);

        return new Sprite(_still_down);
    }

    /** Loads the animations of the drifter.
     * @param game Game the view belongs to. */
    private void _loadAnimations(HotlineDrifter game) {

        Texture walkSheet = game.getAssetManager().get("core/assets/hero_walk.png");
        TextureRegion[][] walkRegion = TextureRegion.split(walkSheet, walkSheet.getWidth() / 12, walkSheet.getHeight() / 4);

        TextureRegion[] walkUpFrames = new TextureRegion[12];
        TextureRegion[] walkDownFrames = new TextureRegion[12];
        TextureRegion[] walkLeftFrames = new TextureRegion[12];
        TextureRegion[] walkRightFrames = new TextureRegion[12];

        System.arraycopy(walkRegion[0], 0, walkUpFrames, 0, 12);
        System.arraycopy(walkRegion[1], 0, walkDownFrames, 0, 12);
        System.arraycopy(walkRegion[2], 0, walkLeftFrames, 0, 12);
        System.arraycopy(walkRegion[3], 0, walkRightFrames, 0, 12);

        _walk_up = new Animation<TextureRegion>(0.08333f, walkUpFrames);
        _walk_down = new Animation<TextureRegion>(0.08333f, walkDownFrames);
        _walk_left = new Animation<TextureRegion>(0.08333f, walkLeftFrames);
        _walk_right = new Animation<TextureRegion>(0.08333f, walkRightFrames);

        Texture attackSheet = game.getAssetManager().get("core/assets/hero_charged_atk_v3.png");
        TextureRegion[][] attackRegion = TextureRegion.split(attackSheet, attackSheet.getWidth() / 20, attackSheet.getHeight());

        TextureRegion[] attackFrames = new TextureRegion[20];

        System.arraycopy(attackRegion[0], 0, attackFrames, 0, 20);

        _charged_attack = new Animation<TextureRegion>(0.1f, attackFrames);
    }

    /** Loads the sprites of the drifter.
     * @param game Game the view belongs to. */
    private void _loadSprites(HotlineDrifter game) {

        Texture stillSheet = game.getAssetManager().get("core/assets/hero_still.png");
        TextureRegion[][] stillRegion = TextureRegion.split(stillSheet, stillSheet.getWidth() / 4, stillSheet.getHeight());

        _still_up = stillRegion[0][0];
        _still_down = stillRegion[0][1];
        _still_left = stillRegion[0][2];
        _still_right = stillRegion[0][3];
    }

    @Override
    protected void setNextFrame() {

        InputController.State state = InputController.getController().getCurrentState();
        char direction = InputController.getController().getCurrentDirection();

        switch(state) {
            case STILL:
                if((direction & InputController.A) == InputController.A)
                    _currentFrame.setRegion(_still_left);
                else if((direction & InputController.D) == InputController.D)
                    _currentFrame.setRegion(_still_right);
                else if((direction & InputController.W) == InputController.W)
                    _currentFrame.setRegion(_still_up);
                else if((direction & InputController.S) == InputController.S)
                    _currentFrame.setRegion(_still_down);
                break;
            case WALKING:
                if((direction & InputController.A) == InputController.A)
                    _currentFrame.setRegion(_walk_left.getKeyFrame(_stateTime, true));
                else if((direction & InputController.D) == InputController.D)
                    _currentFrame.setRegion(_walk_right.getKeyFrame(_stateTime, true));
                else if((direction & InputController.W) == InputController.W)
                    _currentFrame.setRegion(_walk_up.getKeyFrame(_stateTime, true));
                else if((direction & InputController.S) == InputController.S)
                    _currentFrame.setRegion(_walk_down.getKeyFrame(_stateTime, true));
                break;
            case ATTACKING:
                _currentFrame.setRegion(_charged_attack.getKeyFrame(_stateTime, false));
                break;
        }
    }
}