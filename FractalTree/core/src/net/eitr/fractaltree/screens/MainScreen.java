package net.eitr.fractaltree.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainScreen implements Screen
{
	Stage stage;
	Table table;
	BitmapFont font;
    Skin skin;
    SpriteBatch batch;
    Slider slider [];
    Label labelMin [];
    Label labelMax [];
    Label labelCur [];
    Label labelSlider [];
	int menuX, menuY;
	
	@Override
	public void render(float delta)
	{

		for (int i = 0; i < slider.length; i++)
		{	
			labelCur[i].setText((int)slider[i].getValue()+"");
		}
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		batch.begin();
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show()
	{
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		table.defaults();
//		table.columnDefaults(0).width(100f);
		table.columnDefaults(1).width(100f);
//		table.columnDefaults(2).width(100f);
		
		menuX = 10; menuY = 10;
		
		slider = new Slider[12];
		labelMin = new Label[12];
		labelMax = new Label[12];
		labelSlider = new Label[12];
		labelCur = new Label[12];

		slider[0] = new Slider(10,Gdx.graphics.getWidth(),10,false,skin);
		slider[1] = new Slider(10,Gdx.graphics.getHeight(),10,false,skin);
		slider[2] = new Slider(1,40,1,false,skin);
		slider[3] = new Slider(1,40,1,false,skin);
		slider[4] = new Slider(50,100,1,false,skin);
		slider[5] = new Slider(50,100,1,false,skin);
		slider[6] = new Slider(0,50,1,false,skin);
		slider[7] = new Slider(0,50,1,false,skin);
		slider[8] = new Slider(0,6,1,false,skin);
		slider[9] = new Slider(0,6,1,false,skin);
		slider[10] = new Slider(1,300,1,false,skin);
		slider[11] = new Slider(1,64,1,false,skin);

		labelSlider[0] = new Label("Location - X",skin);
		labelSlider[1] = new Label("Location - Y",skin);
		labelSlider[2] = new Label("Size - Min",skin);
		labelSlider[3] = new Label("Size - Max",skin);
		labelSlider[4] = new Label("Scale (%) - Min",skin);
		labelSlider[5] = new Label("Scale (%) - Max",skin);
		labelSlider[6] = new Label("Angle (%) - Min",skin);
		labelSlider[7] = new Label("Angle (%) - Max",skin);
		labelSlider[8] = new Label("Branches - Min",skin);
		labelSlider[9] = new Label("Branches - Max",skin);
		labelSlider[10] = new Label("Start Length",skin);
		labelSlider[11] = new Label("Start Width",skin);

		
		for (int i = 0; i < slider.length; i++)
		{
			labelMin[i] = new Label((int)slider[i].getMinValue()+"",skin);
			labelMax[i] = new Label((int)slider[i].getMaxValue()+"",skin);
			labelCur[i] = new Label(" ",skin);

			labelMin[i].setAlignment(Align.left);
			labelMax[i].setAlignment(Align.right);
			labelCur[i].setAlignment(Align.center);
			
			slider[i].setWidth(300);
			
			table.add(labelSlider[i]).colspan(3).center().row();
			table.add(slider[i]).colspan(3).fillX().row();
			table.add(labelMin[i]).left();
			table.add(labelCur[i]).center();
			table.add(labelMax[i]).right();
			table.row();
		}

		
		TextButton b = new TextButton("Fuck me", skin);
		table.add(b);
		
		stage.addActor(table);
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
		stage.dispose();
		skin.dispose();
	}

}
