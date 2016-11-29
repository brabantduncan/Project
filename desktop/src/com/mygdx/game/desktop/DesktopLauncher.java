package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
//import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testGDX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title =" The Eldershots";
		config.width = 720;
		config.height = 480;
		config.backgroundFPS = 30;
		config.foregroundFPS = 60;

		new LwjglApplication(new testGDX(), config);
		//new LwjglApplication(new MyGdxGame(), config);
	}
}
