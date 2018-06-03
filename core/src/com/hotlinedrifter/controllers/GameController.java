package com.hotlinedrifter.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.hotlinedrifter.ai.AIController;
import com.hotlinedrifter.body.entities.DrifterBody;
import com.hotlinedrifter.body.entities.JudgementBody;
import com.hotlinedrifter.body.entities.SpiderBody;
import com.hotlinedrifter.body.objects.WallBody;
import com.hotlinedrifter.body.objects.WaterBody;
import com.hotlinedrifter.model.BaseModel;
import com.hotlinedrifter.model.GameModel;
import com.hotlinedrifter.model.attacks.AttackModel;
import com.hotlinedrifter.model.bullets.BulletModel;
import com.hotlinedrifter.model.entities.*;
import com.hotlinedrifter.model.objects.TileObjectModel;
import com.hotlinedrifter.model.objects.WallModel;
import com.hotlinedrifter.model.objects.WaterModel;

import java.util.List;

/**
 * Controls physics and updates model.
 */
public class GameController implements ContactListener {

    /**
     * Velocity iterations for the world step method.
     */
    private static final int        VELOCITY_ITERATIONS = 6;

    /**
     * Position iterations for the world step method.
     */
    private static final int        POSITION_ITERATIONS = 2;

    /**
     * Time between spider spawns.
     */
    private static final float      TIME_NEXT_SPIDER     = 2f;

    /**
     * Time between judgement spawns.
     */
    private static final float      TIME_NEXT_JUDGEMENT  = 30f;

    /**
     * Singleton instance of this class.
     */
    private static GameController   _instance;

    /**
     * Physics' world.
     */
    private final World             _world;

    /**
     * World step buffer.
     */
    private float                   _accumulator;

    /**
     * Time until next spider spawn.
     */
    private float                   _timeNextSpider;

    /**
     * Time until next judgement spawn.
     */
    private float                   _timeNextJudgement;

    /**
     * Number of spiders to add.
     */
    private int                     _spidersToAdd;

    /**
     * Number of judgements to add.
     */
    private int                     _judgementsToAdd;

    /**
     * Creates an object of GameController.
     */
    private GameController() {

        _world = new World(new Vector2(0f, 0f), true);

        for(TileObjectModel tile : GameModel.getInstance().getTileObjectModels()) {
            if(tile instanceof WallModel)
                new WallBody(_world, (WallModel) tile);
            else if(tile instanceof WaterModel)
                new WaterBody(_world, (WaterModel) tile);
        }

        new DrifterBody(_world, GameModel.getInstance().getDrifter());

        for(SpiderModel spider : GameModel.getInstance().getSpiders())
            new SpiderBody(_world, spider);

        new JudgementBody(_world, GameModel.getInstance().getJudgement());

        _world.setContactListener(this);
    }

    /**
     * Returns the singleton instance of this class.
     * @return Singleton instance.
     */
    public static GameController getController() {

        if(_instance == null)
            _instance = new GameController();

        return _instance;
    }

    /**
     * Returns the physics' world.
     * @return Physic' world.
     */
    public World getWorld() {
        return _world;
    }

    /**
     * Updates the model and makes the next step in the physics' world.
     * @param delta Time in seconds since the last frame.
     */
    public void update(float delta) {

        DrifterModel drifter = GameModel.getInstance().getDrifter();
        drifter.setAction(InputController.getController().getNextAction());
        drifter.update(delta);

        JudgementModel judgement = GameModel.getInstance().getJudgement();
        if(judgement != null) {
            judgement.setAction(AIController.getController().getNextAction(judgement));
            judgement.update(delta);
        }

        List<SpiderModel> spiders = GameModel.getInstance().getSpiders();
        for (SpiderModel spider : spiders) {
            spider.setAction(AIController.getController().getNextAction(spider));
            spider.update(delta);
        }

        List<BulletModel> bullets = GameModel.getInstance().getBullets();
        for(BulletModel bullet : bullets)
            bullet.update(delta);

        _timeNextSpider -= delta;
        _timeNextJudgement -= delta;

        float frameTime = Math.min(delta, 0.25f);
        _accumulator += frameTime;
        while (_accumulator >= 1/60f) {
            _world.step(1/60f, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            _accumulator -= 1/60f;
        }

        Array<Body> bodies = new Array<Body>();
        _world.getBodies(bodies);

        for(Body body : bodies) {
            ((BaseModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((BaseModel) body.getUserData()).setRotation(body.getAngle());
            body.setLinearVelocity(((BaseModel) body.getUserData()).getVelocity());
        }
    }

    /**
     * Tells what to do when objects contact with each other.
     */
    @Override
    public void beginContact(Contact contact) {

        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        //Bullet collision

        if (bodyA.getUserData() instanceof BulletModel)
            bulletCollision(bodyA);

        if (bodyB.getUserData() instanceof BulletModel)
            bulletCollision(bodyB);

        //Entity collision

        if(bodyA.getUserData() instanceof DrifterModel && bodyB.getUserData() instanceof SpiderModel)
            entityCollision(bodyA, bodyB);
        if(bodyA.getUserData() instanceof SpiderModel && bodyB.getUserData() instanceof DrifterModel)
            entityCollision(bodyB, bodyA);

        if(bodyA.getUserData() instanceof DrifterModel && bodyB.getUserData() instanceof JudgementModel)
            entityCollision(bodyA, bodyB);
        if(bodyA.getUserData() instanceof JudgementModel && bodyB.getUserData() instanceof DrifterModel)
            entityCollision(bodyB, bodyA);

        //Bullet-entity collision

        if(bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof EntityModel)
            bulletEntityCollision(bodyA, bodyB);
        if(bodyA.getUserData() instanceof EntityModel && bodyB.getUserData() instanceof BulletModel)
            bulletEntityCollision(bodyB, bodyA);

        //Attack-entity collision

        if(bodyA.getUserData() instanceof AttackModel && bodyB.getUserData() instanceof EntityModel)
            attackEntityCollision(bodyA, bodyB);
        if(bodyA.getUserData() instanceof EntityModel && bodyB.getUserData() instanceof AttackModel)
            attackEntityCollision(bodyB, bodyA);

    }

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

    /**
     * A bullet collided with something. Lets remove it.
     * @param bulletBody the bullet that collided.
     */
    private void bulletCollision(Body bulletBody) {
        ((BulletModel) bulletBody.getUserData()).setFlagged(true);
    }

    /**
     * The Drifter collided with an enemy, it should take damage.
     * @param drifterBody Drifter's Body.
     * @param enemyBody Enemy's Body.
     */
    private void entityCollision(Body drifterBody, Body enemyBody) {
        if(((EntityModel) drifterBody.getUserData()).hit(((EntityModel) enemyBody.getUserData()).getEntityDamage()))
            ((EntityModel) drifterBody.getUserData()).setFlagged(true);
    }

    /**
     * A Bullet hit someone, so that entity will take damage and the bullet with be flagged for removal.
     * @param bulletBody Bullet's Body.
     * @param entityBody Entity's Body which has just been hit.
     */
    private void bulletEntityCollision(Body bulletBody, Body entityBody) {

        EntityModel entityModel = ((EntityModel) entityBody.getUserData());

        if(((EntityModel) entityBody.getUserData()).hit(((BulletModel) bulletBody.getUserData()).getBulletDamage())) {
            entityModel.setFlagged(true);

            if(entityModel instanceof SpiderModel) {
                ++_spidersToAdd;
                _timeNextSpider = TIME_NEXT_SPIDER;
            }
            else if(entityModel instanceof JudgementModel) {
                ++_judgementsToAdd;
                _timeNextJudgement = TIME_NEXT_JUDGEMENT;
            }
        }
    }

    /**
     * An attack hit someone, so that entity will take damage.
     * @param attackBody Attack's Body.
     * @param entityBody Entity's Body which has just been hit.
     */
    private void attackEntityCollision(Body attackBody, Body entityBody) {

        EntityModel entityModel = ((EntityModel) entityBody.getUserData());

        if(((EntityModel) entityBody.getUserData()).hit(((AttackModel) attackBody.getUserData()).getAttackDamage())) {
            entityModel.setFlagged(true);

            if(entityModel instanceof SpiderModel) {
                ++_spidersToAdd;
                _timeNextSpider = TIME_NEXT_SPIDER;
            }
            else if(entityModel instanceof JudgementModel) {
                ++_judgementsToAdd;
                _timeNextJudgement = TIME_NEXT_JUDGEMENT;
            }
        }
    }

    /**
     * Spawns new enemies in the map.
     */
    public void spawnEnemies() {

        if(_timeNextSpider < 0 && _spidersToAdd > 0) {
            SpiderModel newSpider = GameModel.getInstance().spawnSpider();
            new SpiderBody(_world, newSpider);
            --_spidersToAdd;
        }

        if(_timeNextJudgement < 0 && _judgementsToAdd > 0) {
            JudgementModel newJudgement = GameModel.getInstance().spawnJudgement();
            new JudgementBody(_world, newJudgement);
            --_judgementsToAdd;
        }
    }

    /**
     * Removes objects that have been flagged for removal on the previous step.
     */
    public void removeFlagged() {

        Array<Body> bodies = new Array<Body>();
        _world.getBodies(bodies);

        for (Body body : bodies) {
            if (((BaseModel)body.getUserData()).isFlagged()) {
                GameModel.getInstance().remove(body);
                _world.destroyBody(body);
            }
        }
    }
}