package com.hotlinedrifter.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hotlinedrifter.HotlineDrifter;

/**
 * Death view.
 */
public class DeathView extends ScreenAdapter {

    /**
     * Viewport_Width Constant.
     */
    private static final float      VIEWPORT_WIDTH = 20;

    /**
     * Viewport_Height Constant.
     */
    private static final float      VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    /**
     * Game the screen belongs to.
     */
    private final HotlineDrifter    _game;

    /**
     * Camera
     */
    private OrthographicCamera      _camera;

    /**
     * Loading Screen Background Image.
     */
    private Sprite                  _deathScreen;

    /**
     * Creates an Object of Loading View.
     * @param game Game this view belongs to.
     */
    DeathView(HotlineDrifter game) {
        _game = game;
        _camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        _camera.setToOrtho(false);
    }

    /**
     * Initiates assets loading and progress bar.
     */
    public void show() {
        _deathScreen = new Sprite(_game.getAssetManager().get("images/death_screen.jpg", Texture.class));
        _deathScreen.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Renders the screen.
     * @param delta time passed since last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _camera.update();
        _game.getBatch().setProjectionMatrix(_camera.combined);
        _game.getBatch().begin();
        _deathScreen.draw(_game.getBatch());
        _game.getBatch().end();
    }

    /**
     * Disposes the DeathView.
     */
    @Override
    public void dispose() {
        _game.dispose();
        super.dispose();
    }
}