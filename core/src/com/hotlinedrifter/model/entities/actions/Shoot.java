package com.hotlinedrifter.model.entities.actions;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.body.bullets.DrifterBulletBody;
import com.hotlinedrifter.body.bullets.JudgementBulletBody;
import com.hotlinedrifter.controllers.GameController;
import com.hotlinedrifter.model.GameModel;
import com.hotlinedrifter.model.bullets.BulletModel;
import com.hotlinedrifter.model.bullets.DrifterBulletModel;
import com.hotlinedrifter.model.bullets.JudgementBulletModel;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Shoot Action.
 */
public class Shoot extends BaseAction{

    /**
     * Creates a Shoot action object.
     * @param actor Actor of the action.
     * @param target Target of the action.
     */
    public Shoot(EntityModel actor, Vector2 target) {
        super(actor, target.sub(actor.getPosition()));
    }

    @Override
    public void doAction() {

        if(isInterruptable()) {

            _actor.setActionDelay(_actor.getDelay());

            _actor.setVelocity(0f, 0f);

            BulletModel bullet = GameModel.getInstance().createBullet(_actor.getModelType());

            bullet.setupBullet(_actor.getPosition(), getDirection());

            if(bullet instanceof DrifterBulletModel)
                new DrifterBulletBody(GameController.getController().getWorld(), (DrifterBulletModel) bullet);
            else if(bullet instanceof JudgementBulletModel)
                new JudgementBulletBody(GameController.getController().getWorld(), (JudgementBulletModel) bullet);
        }
    }

    @Override
    public BaseAction.ActionType getActionType() {
        return ActionType.SHOOT;
    }
}
