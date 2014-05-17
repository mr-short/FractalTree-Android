package net.eitr.fractaltree.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class InfoScreen implements Screen
{
	Stage stage;
	Label label [];
	Skin skin;
	Table table;
	TextButton exitButton;
	
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void show()
	{
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(stage);
		
		label = new Label[13];

		label[0] = new Label("Location - X: Set horizontal position of tree on the screen.",skin);
		label[1] = new Label("Size: Approximate number of branches from root to tip.",skin);
		label[2] = new Label("Scale (%): Size factor of new branch based on previous one.",skin);
		label[3] = new Label("Angle (%): Amount of turn for next branch. 0 = parallel, 50% = perpendicular.",skin);
		label[4] = new Label("Branches: Amount of branches to create off the previous one.",skin);
		label[5] = new Label("Start Length: Distance of root branch. Affects height of tree.",skin);
		label[6] = new Label("Start Width: Size of the trunk. New branches are proportionately smaller.",skin);
		label[7] = new Label("Memory Usage: Total number of branches allowed to be created. (in thousands)",skin);
		label[8] = new Label("",skin);
		label[9] = new Label("If the tree is curved and only one sided, it is creating too many branches,",skin);
		label[10] = new Label("thus runs out of memory to create the rest. This saves your computer from crashing.",skin);
		label[11] = new Label("Try reducing the max size of the tree or how many branches it is trying to create.",skin);
		label[11] = new Label("",skin);
		label[12] = new Label("If it takes too long to load, try reducing memory usage. It's only needed for bushier trees.",skin);
		

		int x = 100;
		int y = Gdx.graphics.getHeight() - 100;
		
		for (int i = 0; i < label.length; i++)
		{
			label[i].setPosition(x, y);
			y -= 50;
			table.add(label[i]).row();
		}
		
		exitButton = new TextButton("Back", skin);
		exitButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen());
            }
        });
		table.add(exitButton);
		
		stage.addActor(table);
	}

	@Override
	public void hide()
	{
		dispose();
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
		stage.dispose();
		skin.dispose();
	}

}
