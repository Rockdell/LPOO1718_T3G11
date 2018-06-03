package com.hotlinedrifter.view.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.model.BaseModel;
import com.hotlinedrifter.view.BaseView;

/**
 * Judgement Bullet's View.
 */
public class JudgementBulletView extends BaseView {

    /**
     * Bullet's Animation.
     */
    private Animation<TextureRegion> _bullet;

    /**
     * Creates a Judgement Bullet's View object.
     * @param game Game to which the view belongs to.
     */
    public JudgementBulletView(HotlineDrifter game) {
        super(game);
    }

    @Override
    protected Sprite createView(HotlineDrifter game) {

        loadBulletAnimation(game);

        return new Sprite(_bullet.getKeyFrames()[0]);
    }

    /**
     * Loads Bullet animation.
     * @param game Game to which the animation belong to.
     */
    private void loadBulletAnimation(HotlineDrifter game) {

        Texture bulletSheet = game.getAssetManager().get("images/judgement_bullet.png");
        TextureRegion[][] bulletRegion = TextureRegion.split(bulletSheet, bulletSheet.getWidth() / 4, bulletSheet.getHeight());

        _bullet = new Animation<TextureRegion>(0.1f, bulletRegion[0]);
    }

    @Override
    public void update(BaseModel model) {
        super.update(model);

        _currentFrame.setRegion(_bullet.getKeyFrame(model.getStateTime(), true));
        _currentFrame.setRotation((float) Math.toDegrees(model.getAngle()));
        _currentFrame.setScale(0.5f);
    }
}
