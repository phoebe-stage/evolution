package com.evolution.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(constants.SCREENWIDTH,constants.SCREENHEIGHT);
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("Evolution");
		WorldGame game = new WorldGame();
		new Lwjgl3Application(game, config);
	}
}
