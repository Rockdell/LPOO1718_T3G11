package com.hotlinedrifter.model.attacks;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.model.BaseModel;

/**
 * Attack body.
 */
public abstract class AttackModel extends BaseModel {

    /**
     * Creates an AttackModel object.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    AttackModel(float x, float y) {
        super(x, y);
    }

    /**
     * Sets up the attack.
     * @param position Attack's position.
     */
    public void setupAttack(Vector2 position) {

        setFlagged(false);

        setPosition(position.x, position.y);
    }

    /**
     * Returns attack's damage
     * @return float value with the attack's damage.
     */
    public abstract float getAttackDamage();
}
