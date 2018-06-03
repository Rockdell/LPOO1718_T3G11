package com.hotlinedrifter.model.bullets;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.model.BaseModel;

/**
 * Bullet's Model
 */
public abstract class BulletModel extends BaseModel {

    /**
     * Creates a BulledModel object.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    BulletModel(float x, float y) {
        super(x, y);
    }

    /**
     * Sets up Bullet's position and velocity.
     * @param position Bullet's position.
     * @param direction Bullet's direction.
     */
    public void setupBullet(Vector2 position, Vector2 direction) {

        setFlagged(false);

        setPosition(position.x + direction.x * 0.3f, position.y + direction.y * 0.3f + 0.3f);

        setRotation(new Vector2(1f, 0f).angleRad(direction));

        setVelocity((float) (getBulletVelocity() * Math.cos(getAngle())), (float) (getBulletVelocity() * Math.sin(getAngle())));
    }

    /**
     * Returns Bullet Damage.
     * @return float value.
     */
    public abstract float getBulletDamage();

    /**
     * Returns Bullet Velocity.
     * @return float value.
     */
    public abstract float getBulletVelocity();
}
