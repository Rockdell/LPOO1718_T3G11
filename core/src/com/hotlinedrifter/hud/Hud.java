package com.hotlinedrifter.hud;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hotlinedrifter.model.GameModel;

/**
 * InGame HUD for Drifter.
 */
public class Hud extends Stage {

    /**
     *  Entity HUD.
     */
    private EntityHud _drifterHud;

    /**
     * Creates an instance of HUD.
     */
    public Hud() {
        _drifterHud = new EntityHud(GameModel.getInstance().getDrifter());
        addActor(_drifterHud);
    }
}