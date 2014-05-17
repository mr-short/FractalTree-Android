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
		config.width = 1600;
		config.height = 900;
		new LwjglApplication(new FractalTree(), config);
	}
}
