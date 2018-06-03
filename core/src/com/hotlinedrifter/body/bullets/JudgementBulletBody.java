package com.hotlinedrifter.body.bullets;

import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.model.bullets.JudgementBulletModel;

/**
 * Judgement BulletBody.
 */
public class JudgementBulletBody extends BulletBody {

    /**
     * Creates an object JudgementBulletBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    public JudgementBulletBody(World world, JudgementBulletModel model) {
        super(world, model);

        createFixture(new float[] { 0,0, 0,7, 21,7, 21,0 }, 21, 7, JBULLET_BODY, (short) (DRIFTER_BODY | DBULLET_BODY | WALL_BODY));
    }

}
