package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setForegroundFPS(60);
		cfg.setWindowedMode(500, 700);
		cfg.setResizable(false);
		cfg.setTitle("Final Project");
		new Lwjgl3Application(new MyGdxGame(), cfg);
	}
}