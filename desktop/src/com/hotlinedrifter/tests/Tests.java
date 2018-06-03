package com.hotlinedrifter.tests;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.hotlinedrifter.controllers.GameController;
import com.hotlinedrifter.model.GameModel;
import com.hotlinedrifter.model.bullets.BulletModel;
import com.hotlinedrifter.model.entities.DrifterModel;
import com.hotlinedrifter.model.entities.EntityModel;
import com.hotlinedrifter.model.entities.JudgementModel;
import com.hotlinedrifter.model.entities.SpiderModel;
import com.hotlinedrifter.model.entities.actions.Attack;
import com.hotlinedrifter.model.entities.actions.BaseAction;
import com.hotlinedrifter.model.entities.actions.Shoot;

import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    /**
     * Tests if the entity models area created correctly.
     */
    @Test
    public void testEntityModels() {

        DrifterModel drifter = new DrifterModel(0, 0);

        assertEquals(drifter.getPosition(), new Vector2(0, 0));
        assertEquals((long)drifter.getHp(), (long)100);
        assertEquals((long)drifter.getEs(), (long)100);

        SpiderModel spider = new SpiderModel(0, 0);

        assertEquals(spider.getPosition(), new Vector2(0, 0));
        assertEquals((long)spider.getHp(), (long)50);
        assertEquals((long)spider.getEs(), (long)0);

        JudgementModel judgementModel = new JudgementModel(0, 0);

        assertEquals(judgementModel.getPosition(), new Vector2(0, 0));
        assertEquals((long)judgementModel.getHp(), (long)500);
        assertEquals((long)judgementModel.getEs(), (long)0);
    }

    /**
     * Tests if the drifter attacks correctly.
     */
    @Test
    public void testDrifterAttack() {

        DrifterModel drifter = new DrifterModel(0, 0);
        drifter.setAction(new Attack(drifter, new Vector2(1, 0)));

        assertEquals(drifter.getAction().getActionType(), BaseAction.ActionType.ATTACK);
    }

    /**
     * Tests if the entities shoot correctly.
     */
    @Test
    public void testEntityShots() {

        GameModel.getInstance().spawnJudgement();
        GameController.getController();

        GameModel.getInstance().getDrifter().setAction(new Shoot(GameModel.getInstance().getDrifter(), new Vector2(1, 0)));
        GameModel.getInstance().getJudgement().setAction(new Shoot(GameModel.getInstance().getJudgement(), new Vector2(1, 0)));

        assertEquals(GameModel.getInstance().getDrifter().getAction().getActionType(), BaseAction.ActionType.SHOOT);
        assertEquals(GameModel.getInstance().getJudgement().getAction().getActionType(), BaseAction.ActionType.SHOOT);

        GameModel.getInstance().getDrifter().update(0);
        GameModel.getInstance().getJudgement().update(0);

        Array<Body> bodies = new Array<Body>();
        GameController.getController().getWorld().getBodies(bodies);

        int bulletsCount = 0;
        for(Body body: bodies) {
            if (body.getUserData() instanceof BulletModel)
                bulletsCount++;
        }

        assertEquals(bulletsCount, 2);
    }

    /**
     * Tests if the game spawn entities correctly.
     */
    @Test
    public void testEntitiesSpawn() {

        GameModel.getInstance().spawnJudgement();

        Array<Body> bodies = new Array<Body>();
        GameController.getController().getWorld().getBodies(bodies);

        int drifterCount = 0, judgementCount = 0;

        if(GameModel.getInstance().getDrifter() != null)
            ++drifterCount;

        if(GameModel.getInstance().getJudgement() != null)
            ++judgementCount;

        assertEquals(judgementCount, 1);
        assertEquals(drifterCount, 1);
    }

    /**
     * Tests if the entities are created with the default action (still, face down)
     */
    @Test
    public void testEntityDefaultAction() {

        SpiderModel spider = new SpiderModel(0, 0);
        JudgementModel judgement = new JudgementModel(0, 0);
        DrifterModel drifter = new DrifterModel(0, 0);

        assertEquals(BaseAction.ActionType.STILL, spider.getAction().getActionType());
        assertEquals(BaseAction.ActionType.STILL, judgement.getAction().getActionType());
        assertEquals(BaseAction.ActionType.STILL, drifter.getAction().getActionType());

        assertEquals((long)0, (long)spider.getAction().getDirection().x);
        assertEquals((long)-1, (long)spider.getAction().getDirection().y);

        assertEquals((long)0, (long)judgement.getAction().getDirection().x);
        assertEquals((long)-1, (long)judgement.getAction().getDirection().y);

        assertEquals((long)0, (long)drifter.getAction().getDirection().x);
        assertEquals((long)-1, (long)drifter.getAction().getDirection().y);
    }

    /**
     * Tests if the flagged entities are removed correctly.
     */
    @Test
    public void testRemoveFlagged() {

        GameModel.getInstance().spawnJudgement();

        Array<Body> bodies = new Array<Body>();
        GameController.getController().getWorld().getBodies(bodies);

        int oldEnemies = 0;
        for(Body body: bodies) {
            if (body.getUserData() instanceof SpiderModel || body.getUserData() instanceof JudgementModel) {
                ++oldEnemies;
                ((EntityModel) body.getUserData()).setFlagged(true);
            }
        }

        GameController.getController().removeFlagged();

        int newEnemies = 0;
        for(Body body: bodies) {
            if(body.getUserData() instanceof SpiderModel || body.getUserData() instanceof JudgementModel)
                ++newEnemies;
        }

        assertNotEquals(newEnemies, oldEnemies);
    }
}
