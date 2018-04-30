package com.hotlinedrifter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hotlinedrifter.screen.GameScreen;

public class HotlineDrifter extends Game {

	private SpriteBatch     _batch;
	private AssetManager    _assetManager;

	@Override
	public void create () {
	    _batch = new SpriteBatch();
	    _assetManager = new AssetManager();

	    startGame();
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
	    _batch.dispose();
	    _assetManager.dispose();
	}

	private void startGame() {
	    setScreen(new GameScreen(this));
    }

    public SpriteBatch getBatch() {
	    return _batch;
    }

    public AssetManager getAssetManager() {
	    return _assetManager;
    }
}
