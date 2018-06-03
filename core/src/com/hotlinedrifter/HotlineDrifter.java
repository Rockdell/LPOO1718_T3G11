package com.hotlinedrifter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hotlinedrifter.screen.LoadingView;

/**
 * Hotline Drifter Game
 */
public class HotlineDrifter extends Game {

	/**
	 * SpriteBatch
	 */
	private SpriteBatch     _batch;

	/**
	 * Asset Manager
	 */
	private AssetManager    _assetManager;

	/**
	 * Creates and Start the game.
	 */
	@Override
	public void create () {
	    _batch = new SpriteBatch();
	    _assetManager = new AssetManager();

	    startGame();
	}

	/**
	 * Renders the screen.
	 */
	@Override
	public void render () {
        super.render();
	}

	/**
	 * Disposes of the game.
	 */
	@Override
	public void dispose () {
	    _batch.dispose();
	    _assetManager.dispose();
	}

	/**
	 * Starts the game.
	 */
	private void startGame() {
	    setScreen(new LoadingView(this));
    }

    /**
	 * Returns game's sprite batch.
	 * @return Game's sprite batch.
	 */
    public SpriteBatch getBatch() {
	    return _batch;
    }

    /**
	 * Returns game's asset manager.
	 * @return Game's asset manager.
	 */
    public AssetManager getAssetManager() {
	    return _assetManager;
    }
}
