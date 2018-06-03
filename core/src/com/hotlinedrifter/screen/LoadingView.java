package com.hotlinedrifter.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.hotlinedrifter.HotlineDrifter;

/**
 * Loading view.
 */
public class LoadingView extends ScreenAdapter {

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
     * Camera.
     */
    private OrthographicCamera      _camera;

    /**
     * Loading Screen Background Image.
     */
    private Sprite                  _loadingScreen;

    /**
     * State.
     */
    private Stage                   _stage;

    /**
     * Label.
     */
    private Label                   _continue;

    /**
     * Progress Bar.
     */
    private ProgressBar             _progressBar;

    /**
     * Pressed Enter.
     */
    private boolean                 _pressedEnter = false;

    /**
     * Creates an Object of Loading View.
     * @param game Game this view belongs to.
     */
    public LoadingView(HotlineDrifter game) {
        _game = game;
        _camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        _camera.setToOrtho(false);
    }

    /**
     * Initiates assets loading and progress bar.
     */
    public void show() {
        _loadingScreen = new Sprite(new Texture(Gdx.files.internal("images/loadingScreen.png")));
        _loadingScreen.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        initiateProgressBar();

        loadAssets();
    }

    /**
     * Created progress bar.
     */
    private void initiateProgressBar() {

        _stage = new Stage();

        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.FIREBRICK);
        pixmap.fill();
        skin.add("firebrick", new Texture(pixmap));

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("firebrick", Color.DARK_GRAY), drawable);
        barStyle.knobBefore = barStyle.knob;
        _progressBar = new ProgressBar(0, 0.90f, 0.01f, false, barStyle);
        _progressBar.setSize(Gdx.graphics.getWidth() / 5f, 50);
        _progressBar.setPosition(Gdx.graphics.getWidth() / 2 - _progressBar.getWidth() / 2, Gdx.graphics .getHeight() / 3);
        _progressBar.setAnimateDuration(0.1f);
        _stage.addActor(_progressBar);

        _continue = new Label("Press ENTER to Continue", new Label.LabelStyle(new BitmapFont(Gdx.files.internal("images/PixRiddim.fnt")), Color.WHITE));
        _continue.setPosition(Gdx.graphics.getWidth() / 2 - _continue.getWidth() / 2, Gdx.graphics.getHeight() / 3);
        _continue.setVisible(false);
        _stage.addActor(_continue);

    }

    /**
     * Renders the screen.
     * @param delta time passed since last render.
     */
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _camera.update();
        _game.getBatch().setProjectionMatrix(_camera.combined);
        _game.getBatch().begin();
        _loadingScreen.draw(_game.getBatch());
        _game.getBatch().end();

        // Display Progress Bar.
        _progressBar.setValue(_game.getAssetManager().getProgress());
        _stage.draw();
        _stage.act();

        if (_game.getAssetManager().update()) {

            _continue.setVisible(true);

            Gdx.input.setInputProcessor(new InputAdapter () {
                @Override
                public boolean keyDown (int keycode) {
                    if(keycode == Input.Keys.ENTER)
                        _pressedEnter = true;

                    return false;
                }
            });

            if(_pressedEnter) {
                _game.setScreen(new GameView(_game));
                this.dispose();
            }
        }
    }

    /**
     * Loads the assets needed for the Game.
     */
    private void loadAssets() {

        _game.getAssetManager().load("images/drifter_still.png", Texture.class);
        _game.getAssetManager().load("images/drifter_walk.png", Texture.class);
        _game.getAssetManager().load("images/drifter_dash.png", Texture.class);
        _game.getAssetManager().load("images/drifter_shoot.png", Texture.class);
        _game.getAssetManager().load("images/drifter_attack.png", Texture.class);
        _game.getAssetManager().load("images/drifter_bullet.png", Texture.class);

        _game.getAssetManager().load("images/spider_still.png", Texture.class);
        _game.getAssetManager().load("images/spider_walk.png", Texture.class);

        _game.getAssetManager().load("images/judgement_walk.png", Texture.class);
        _game.getAssetManager().load("images/judgement_shoot.png", Texture.class);
        _game.getAssetManager().load("images/judgement_bullet.png", Texture.class);

        _game.getAssetManager().load("images/death_screen.jpg", Texture.class);

        _game.getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        _game.getAssetManager().load("images/WorldMap.tmx", TiledMap.class);

        _game.getAssetManager().load("images/blank.png", Texture.class);
        _game.getAssetManager().setLoader(BitmapFont.class, new BitmapFontLoader(new InternalFileHandleResolver()));
        _game.getAssetManager().load("images/PixRiddim.fnt", BitmapFont.class);

        _game.getAssetManager().load("audio/HotlineDrifterMusic.ogg", Music.class);
        _game.getAssetManager().load("audio/bulletSound.wav", Sound.class);
        _game.getAssetManager().load("audio/attackSound.wav", Sound.class);
        _game.getAssetManager().load("audio/judgementBullet.wav", Sound .class);

        _game.getAssetManager().finishLoading();
    }

    /**
     * Disposes the LoadingView.
     */
    @Override
    public void dispose() {
        super.dispose();
        _stage.dispose();
    }

}