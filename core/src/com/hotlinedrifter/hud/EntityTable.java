package com.hotlinedrifter.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.hotlinedrifter.model.entities.EntityModel;

/**
 * Table containing visual objects for the HUD.
 */
public class EntityTable extends Table {

    /**
     * Table Enumeration to determine which type of table it is.
     */
    public enum TableType { HP, ES }

    /**
     * TableType.
     */
    private TableType _type;

    /**
     * EntityModel associated with the Table.
     */
    private EntityModel _model;

    /** Label displaying HP or ES information. */
    private Label _label;

    /**
     * Image displaying HP or ES information.
     */
    private Image _bar;

    /**
     * Created and EntityTable object.
     * @param type Table type.
     * @param model EntityModel associated with the Table.
     */
    EntityTable(TableType type, EntityModel model) {

        super();

        _type = type;
        _model = model;

        _bar = new Image(new Texture(Gdx.files.internal("images/blank.png")));

        if(type == TableType.HP) {
            _label = new Label(String.format("%02d", (int) model.getHp()), new Label.LabelStyle(new BitmapFont(Gdx.files.internal("images/PixRiddim.fnt")), Color.WHITE));
            _bar.setColor(Color.FIREBRICK);
            _bar.setSize(500 * (_model.getHp() / 100), 50);
        }
        else {
            _label = new Label(String.format("%02d", (int) model.getEs()), new Label.LabelStyle(new BitmapFont(Gdx.files.internal("images/PixRiddim.fnt")), Color.WHITE));
            _bar.setColor(new Color((float)75/254, (float)254/254, (float)250/254, 1f));
            _bar.setSize(300 * (_model.getEs() / 100), 50);
        }

        addActor(_bar);
    }

    /**
     * Returns the Label.
     * @return Label of the table.
     */
    Label getLabel() {
        return _label;
    }

    /**
     * Updates the Label and the Image according to the associated model.
     * @param delta time passed since the last render.
     */
    @Override
    public void act(float delta) {

        if(_model.isDead())
            return;

        if(_type == TableType.HP) {
            _label.setText(String.format("%02d", (int) _model.getHp()));
            _bar.setSize(500 * (_model.getHp() / 100), 50);
        }
        else {
            _label.setText(String.format("%02d", (int) _model.getEs()));
            _bar.setSize(300 * (_model.getEs() / 100), 50);
        }
    }
}
