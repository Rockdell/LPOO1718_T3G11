package com.hotlinedrifter.hud;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Contains and manages the elements of the HUD.
 */
public class EntityHud extends Table {

    /**
     * Entity Table for HP.
     */
    private EntityTable _hp;

    /**
     * Entity Table for Energy Shield.
     */
    private EntityTable _es;

    /**
     * Creates and instance of EntityHud.
     * @param model Model associated with the HUD.
     */
    EntityHud(EntityModel model) {

        super();

        setFillParent(true);
        top().right();
        defaults().padTop(10).padRight(10);

        _hp = new EntityTable(EntityTable.TableType.HP, model);

        if(model.getEs() > 0)
            _es = new EntityTable(EntityTable.TableType.ES, model);

        add(_hp.getLabel());
        add(_hp).prefWidth(500).prefHeight(50).colspan(2);

        row();
        add();
        row();
        add();

        if(model.getEs() > 0) {
            add(_es.getLabel());
            add(_es).prefWidth(300).prefHeight(30).left();
        }
    }

    /**
     * Updates all actors according to the model
     * @param delta time passed since the last render.
     */
    @Override
    public void act(float delta) {
        for(Actor actor : getChildren())
            actor.act(delta);
    }
}
