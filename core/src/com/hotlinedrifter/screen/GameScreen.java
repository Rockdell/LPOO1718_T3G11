package com.hotlinedrifter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.controllers.GameController;
import com.hotlinedrifter.controllers.InputController;
import com.hotlinedrifter.layout.GameLevel;
import com.hotlinedrifter.model.DrifterModel;
import com.hotlinedrifter.model.EnemyModel;
import com.hotlinedrifter.view.EntityView;
import com.hotlinedrifter.view.ViewFactory;

import java.util.List;

/** Screen where the game's entities will be displayed. */
public class GameScreen extends ScreenAdapter {

    /** Game the screen belongs to. */
    private final HotlineDrifter    _game;

    /** Screen's viewport. */
    private Viewport                _viewport;

    /** Debug renderer */
    private Box2DDebugRenderer _b2dr;

    /** Creates an object GameScreen.
     * @param game Game the screen belongs to. */
    public GameScreen(final HotlineDrifter game) {

        _game = game;
        _viewport = new FitViewport(480, 270, new OrthographicCamera());

        _b2dr = new Box2DDebugRenderer();

        GameLevel.getInstance().generateLevel();

        _loadAssets();
    }

    @Override
    public void render(float delta) {

        InputController.getController().handleInputs();
        GameController.getController().update(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _viewport.getCamera().update();
        _game.getBatch().setProjectionMatrix(_viewport.getCamera().combined);

        _b2dr.render(GameController.getController().getWorld(), _viewport.getCamera().combined);

        _game.getBatch().begin();
        _drawEntities();
        _game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        _viewport.update(width, height);
    }

    /** Loads the assets needed for the current level. */
    private void _loadAssets() {

        _game.getAssetManager().load("core/assets/hero_still.png", Texture.class);
        _game.getAssetManager().load("core/assets/hero_walk.png", Texture.class);
        _game.getAssetManager().load("core/assets/hero_charged_atk_v3.png", Texture.class);
        _game.getAssetManager().finishLoading();
    }

    /** Draws the entities of the current level. */
    private void _drawEntities() {

        DrifterModel drifter = GameLevel.getInstance().getDrifter();
        EntityView drifter_view = ViewFactory.makeView(_game, drifter);
        drifter_view.update(drifter);
        drifter_view.draw(_game.getBatch());

        //TODO add sprites for enemies
        List<EnemyModel> enemies = GameLevel.getInstance().getEnemies();
        for(EnemyModel enemy : enemies) {
            EntityView enemy_view = ViewFactory.makeView(_game, enemy);
            //enemy_view.update(enemy);
            //enemy_view.draw(_game.getBatch());
        }
    }
}
