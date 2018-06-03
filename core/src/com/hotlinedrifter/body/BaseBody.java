package com.hotlinedrifter.body;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.hotlinedrifter.body.objects.TileObjectBody;
import com.hotlinedrifter.model.BaseModel;
import com.hotlinedrifter.model.objects.TileObjectModel;

import static com.hotlinedrifter.screen.GameView.PIXEL_TO_METER;

/**
 * Base Body.
 */
public abstract class BaseBody {

    /**
     * Category bits for drifter.
     */
    protected final static short DRIFTER_BODY      = 0x01;

    /**
     * Category bits for enemy.
     */
    protected final static short ENEMY_BODY        = 0x01 << 1;

    /**
     * Category bits for drifter's bullet.
     */
    protected final static short DBULLET_BODY      = 0x01 << 2;

    /**
     * Category bits for judgement's bullet.
     */
    protected final static short JBULLET_BODY      = 0x01 << 3;

    /**
     * Category bits for drifter's attack.
     */
    protected final static short DATTACK_BODY      = 0x01 << 4;

    /**
     * Category bits for wall.
     */
    protected final static short WALL_BODY         = 0x01 << 5;

    /**
     * Category bits for water.
     */
    protected final static short WATER_BODY        = 0x01 << 6;

    /**
     * Body.
     */
    private final Body _body;

    /**
     * Creates an object of BaseBody.
     * @param world World the body belongs to.
     * @param model Model of the body
     * @param type Type of body.
     */
    public BaseBody(World world, BaseModel model, BodyDef.BodyType type) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;

        if(this instanceof TileObjectBody) {

            TileObjectModel tileModel = (TileObjectModel) model;

            bodyDef.position.set(
                    (tileModel.getBounds().getX() + tileModel.getBounds().getWidth() / 2f) * PIXEL_TO_METER,
                    (tileModel.getBounds().getY() + tileModel.getBounds().getHeight() / 2f) * PIXEL_TO_METER);
        }
        else
            bodyDef.position.set(model.getPosition().x, model.getPosition().y);

        bodyDef.angle = model.getAngle();

        _body = world.createBody(bodyDef);
        _body.setUserData(model);
    }

    /**
     * Returns X-position of the body.
     * @return float value with the X-position.
     */
    public float getX() {
        return _body.getPosition().x;
    }

    /**
     * Returns Y-position of the body.
     * @return float value with the Y-position.
     */
    public float getY() {
        return _body.getPosition().y;
    }

    /**
     * Creates entity fixture.
     * @param vertexes Fixture's dimensions.
     * @param width Fixture's width.
     * @param height Fixture's height.
     * @param category Fixture's category bits.
     * @param mask Fixture's mask bits
     */
    protected final void createFixture(float[] vertexes, int width, int height, short category, short mask) {

        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;   // center the vertex x-coordinate
            if (i % 2 != 0) vertexes[i] -= height / 2;  // center the vertex y-coordinate
            if (i % 2 != 0) vertexes[i] *= -1;          // invert the y-coordinate

            vertexes[i] *= PIXEL_TO_METER;
        }

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;

        _body.createFixture(fixtureDef);

        polygon.dispose();
    }

    /**
     * Creates TileObject fixture.
     * @param bounds Rectangle of the tile object.
     * @param category Fixture's category bits.
     */
    protected final void createFixture(Rectangle bounds, short category) {

        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox((bounds.getWidth() / 2) * PIXEL_TO_METER, (bounds.getHeight() / 2) * PIXEL_TO_METER);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.filter.categoryBits = category;

        _body.createFixture(fixtureDef);

        polygon.dispose();
    }
}
