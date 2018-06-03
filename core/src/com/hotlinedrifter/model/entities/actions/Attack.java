package com.hotlinedrifter.model.entities.actions;

import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.body.attacks.DrifterAttackBody;
import com.hotlinedrifter.controllers.GameController;
import com.hotlinedrifter.model.GameModel;
import com.hotlinedrifter.model.attacks.AttackModel;
import com.hotlinedrifter.model.attacks.DrifterAttackModel;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Attack Action.
 */
public class Attack extends BaseAction {

    /**
     * Model of the attack.
     */
    private AttackModel _attackModel;

    /**
     * Creates an Attack action object.
     * @param actor Actor of the action.
     * @param target Target of the action.
     */
    public Attack(EntityModel actor, Vector2 target) {
        super(actor, target.sub(actor.getPosition()));
    }

    @Override
    public void doAction() {

        if(isInterruptable()) {

            _actor.setActionDelay(_actor.getDelay());

            _actor.setVelocity(0f, 0f);

            _attackModel = GameModel.getInstance().createAttack(_actor.getModelType());

            _attackModel.setupAttack(_actor.getPosition());

            if(_attackModel instanceof DrifterAttackModel)
                new DrifterAttackBody(GameController.getController().getWorld(), (DrifterAttackModel) _attackModel);
        }
    }

    /**
     * Returns the Attack model of the attack.
     * @return AttackModel.
     */
    public AttackModel getAttackModel() {
        return _attackModel;
    }

    @Override
    public BaseAction.ActionType getActionType() {
        return ActionType.ATTACK;
    }
}
