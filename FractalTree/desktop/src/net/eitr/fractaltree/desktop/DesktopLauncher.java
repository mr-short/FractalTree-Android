package net.eitr.fractaltree.desktop;

import net.eitr.fractaltree.FractalTree;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.fullscreen = true;
		config.foregroundFPS = 4;
		config.backgroundFPS = -1;
		config.width = 1500;
		config.height = 700;
		new LwjglApplication(new FractalTree(), config);
	}
}
