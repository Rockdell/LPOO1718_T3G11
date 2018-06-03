package com.hotlinedrifter.body.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.hotlinedrifter.model.entities.JudgementModel;

/**
 * Judgement Body.
 */
public class JudgementBody extends EntityBody {

    /**
     * Creates an object JudgementBody.
     * @param world World it belongs to.
     * @param model Model of the body.
     */
    public JudgementBody(World world, JudgementModel model) {
        super(world, model);

        createFixture(new float[] { 0,0, 0,100, 50,100, 50,0 }, 50, 100, ENEMY_BODY, (short) (DRIFTER_BODY | ENEMY_BODY | DBULLET_BODY | DATTACK_BODY | WALL_BODY | WATER_BODY));
    }
}
