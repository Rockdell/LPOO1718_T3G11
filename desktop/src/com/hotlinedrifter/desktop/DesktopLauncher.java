package com.hotlinedrifter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hotlinedrifter.HotlineDrifter;

	public class DesktopLauncher {
		public static void main (String[] arg) {
			LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
			cfg.title = "Hotline Drifter";
			cfg.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
			cfg.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
			cfg.vSyncEnabled = true;
			cfg.fullscreen = true;
			cfg.samples = 16;
			new LwjglApplication(new HotlineDrifter(), cfg);
		}
}
