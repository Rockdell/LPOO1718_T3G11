package com.hotlinedrifter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.ai.AIController;
import com.hotlinedrifter.controllers.GameController;
import com.hotlinedrifter.controllers.InputController;
import com.hotlinedrifter.hud.Hud;
import com.hotlinedrifter.model.GameModel;
import com.hotlinedrifter.model.bullets.BulletModel;
import com.hotlinedrifter.model.entities.DrifterModel;
import com.hotlinedrifter.model.entities.JudgementModel;
import com.hotlinedrifter.model.entities.SpiderModel;
import com.hotlinedrifter.view.BaseView;
import com.hotlinedrifter.view.ViewFactory;

import java.util.List;

/**
 * Screen where the game's entities will be displayed.
 */
public class GameView extends ScreenAdapter {

    /**
     * Pixels to meter constant.
     */
    public static final float       PIXEL_TO_METER = 0.05f;

    /**
     * Viewport_Width Constant.
     */
    private static final float      VIEWPORT_WIDTH = 20;

    /**
     * Viewport_Height Constant.
     */
    private static final float      VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    /**
     * Show Debug Physics
     */
    private static final boolean    DEBUG_PHYSICS = false;

    /**
     * Game the screen belongs to.
     */
    private final HotlineDrifter                _game;

    /**
     * Screen's viewport.
     */
    private static FitViewport                  _viewport;

    /**
     * Tiled Map Renderer
     */
    private static OrthogonalTiledMapRenderer   _mapRenderer;

    /**
     * Debug renderer
     */
    private static Box2DDebugRenderer           _debugRenderer;

    /**
     * Debug Camera
     */
    private static Matrix4                      _debugCamera;

    /**
     *  Game's HUD
     */
    private static Hud                          _hud;

    /**
     * Game's Music
     */
    private Music                               _music;

    /**
     * Creates an object GameView.
     * @param game Game the screen belongs to.
     */
    GameView(final HotlineDrifter game) {

        _game = game;

        GameModel.getInstance().generateLevel(game);

        //Initialize controller
        AIController.getController();

        _viewport = createViewport();

        _mapRenderer = new OrthogonalTiledMapRenderer(GameModel.getInstance().getMap());

        _hud = new Hud();

        _music = game.getAssetManager().get("audio/HotlineDrifterMusic.ogg", Music.class);
        _music.setLooping(true);
        _music.play();
    }

    /**
     * Created a viewport.
     * @return Created Viewport.
     */
    private FitViewport createViewport() {

        FitViewport viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_HEIGHT / PIXEL_TO_METER));

        viewport.getCamera().position.set(viewport.getCamera().viewportWidth / 2f, viewport.getCamera().viewportHeight / 2f, 0);
        viewport.getCamera().update();

        if(DEBUG_PHYSICS) {
            _debugRenderer = new Box2DDebugRenderer();
            _debugCamera = viewport.getCamera().combined.cpy();
            _debugCamera.scl(1 / PIXEL_TO_METER);
        }

        return viewport;
    }

    /**
     * Handles all the Controllers and renders the screen.
     * @param delta time passed since last render.
     */
    @Override
    public void render(float delta) {

        if (GameModel.getInstance().getDrifter().isDead()) {
            this.dispose();
            _game.setScreen(new DeathView(_game));
        }

        GameController.getController().removeFlagged();
        GameController.getController().spawnEnemies();

        InputController.getController().handleInputs();
        GameController.getController().update(delta);

        _hud.act();

        _viewport.getCamera().position.set(
                GameModel.getInstance().getDrifter().getPosition().x / PIXEL_TO_METER,
                GameModel.getInstance().getDrifter().getPosition().y / PIXEL_TO_METER, 0f);

        _viewport.getCamera().update();
        _game.getBatch().setProjectionMatrix(_viewport.getCamera().combined);

        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        renderBackground();

        _game.getBatch().begin();
        renderEntities();
        _game.getBatch().end();

        renderForeground();

        _hud.draw();

        if(DEBUG_PHYSICS) {
            _debugCamera = _viewport.getCamera().combined.cpy();
            _debugCamera.scl(1/PIXEL_TO_METER);
            _debugRenderer.render(GameController.getController().getWorld(), _debugCamera);
        }
    }

    /**
     * Draws the entities of the current level.
     */
    private void renderEntities() {

        DrifterModel drifter = GameModel.getInstance().getDrifter();
        if(!drifter.isDead()) {
            BaseView drifterView = ViewFactory.makeView(_game, drifter);
            drifterView.update(drifter);
            drifterView.draw(_game.getBatch());
        }

        JudgementModel judgement = GameModel.getInstance().getJudgement();
        if(judgement != null) {
            BaseView judgementView = ViewFactory.makeView(_game, judgement);
            judgementView.update(judgement);
            judgementView.draw(_game.getBatch());
        }

        List<SpiderModel> enemies = GameModel.getInstance().getSpiders();
        for(SpiderModel enemy : enemies) {
            BaseView enemyView = ViewFactory.makeView(_game, enemy);
            enemyView.update(enemy);
            enemyView.draw(_game.getBatch());
        }

        List<BulletModel> bullets = GameModel.getInstance().getBullets();
        for(BulletModel bullet : bullets) {
            BaseView bullet_view = ViewFactory.makeView(_game, bullet);
            bullet_view.update(bullet);
            bullet_view.draw(_game.getBatch());
        }
    }

    /**
     * Renders the Foreground of the map.
     */
    private void renderForeground() {
        _mapRenderer.setView((OrthographicCamera) _viewport.getCamera());
        int[] mapLayersIndex = { GameModel.getInstance().getMap().getLayers().getIndex("foreground") };
        _mapRenderer.render(mapLayersIndex);
    }

    /**
     * Renders the Background of the map.
     */
    private void renderBackground() {
        _mapRenderer.setView((OrthographicCamera) _viewport.getCamera());
        int[] mapLayersIndex = { GameModel.getInstance().getMap().getLayers().getIndex("background") };
        _mapRenderer.render(mapLayersIndex);
    }

    /**
     * Returns Viewport.
     * @return Viewport.
     */
    public static Viewport getViewport() {
        return _viewport;
    }
}
