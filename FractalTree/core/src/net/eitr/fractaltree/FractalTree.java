package net.eitr.fractaltree;

import net.eitr.fractaltree.screens.MainScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class FractalTree extends Game {

	@Override
	public void create () 
	{
		// TODO: re-enable splash screen
//		setScreen(new SplashScreen());	
		setScreen(new MainScreen());
	}
}
