package net.eitr.fractaltree.screens;

import net.eitr.fractaltree.tween.SpriteAccessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen
{
	SpriteBatch batch;
	Sprite splash;
	TweenManager tweenManager;
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		splash.draw(batch);
		batch.end();
		tweenManager.update(delta);
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void show()
	{
		batch = new SpriteBatch();
		splash = new Sprite(new Texture("ui/splash.png"));
//		splash.setPosition(Gdx.graphics.getWidth()/2-splash.getWidth()/2, Gdx.graphics.getHeight()/2-splash.getHeight()/2);
		splash.setAlpha(0);
		splash.setScale(.2f);
		splash.setPosition(-splash.getWidth()*.4f, -splash.getHeight()*.4f);
//		splash.setPosition(0, 0);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		Tween.to(splash, SpriteAccessor.ALPHA, 1f).target(1).repeatYoyo(1, .5f).ease(TweenEquations.easeInQuad).setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
			}
		}).start(tweenManager);

	}

	@Override
	public void hide()
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		splash.getTexture().dispose();
	}

}
