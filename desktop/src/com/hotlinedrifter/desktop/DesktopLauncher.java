package com.hotlinedrifter.desktop;

import com.badlogic.gdx.Files;
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
			cfg.addIcon("images/icon16.png", Files.FileType.Internal);
			cfg.addIcon("images/icon32.png", Files.FileType.Internal);
			cfg.addIcon("images/icon128.png", Files.FileType.Internal);
			new LwjglApplication(new HotlineDrifter(), cfg);
		}
}
