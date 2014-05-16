package net.eitr.fractaltree.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
	
	
	ShapeRenderer sr;
	boolean drawTree;
	int killCount, killCountMax;
	boolean drawCircles;
	double xList[],yList[],nxList[],nyList[],thickList[],colorList[];
	
	@Override
	public void render(float delta)
	{

		for (int i = 0; i < slider.length; i++)
		{	
			labelCur[i].setText((int)slider[i].getValue()+"");
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (drawTree)
		{
			sr.begin(ShapeType.Filled);
//			draw();
			for (int i = 0; i < killCountMax; i ++)
			{
//				sr.setColor((int)(Math.random()*50)+200, (int)(Math.random()*50)+200, (int)(Math.random()*50)+200, 1);
//				sr.setColor((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
				sr.setColor((float)colorList[i],(float)colorList[i],(float)colorList[i],1);
				sr.rectLine((float)xList[i], (float)yList[i], (float)nxList[i], (float)nyList[i], (float)thickList[i]);
			}
			sr.end();
//			drawTree = false;
		}
		
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
		sr = new ShapeRenderer();
		drawTree = false;
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

		slider[0].setValue(900);
		slider[1].setValue(10);
		slider[2].setValue(8);
		slider[3].setValue(16);
		slider[4].setValue(75);
		slider[5].setValue(85);
		slider[6].setValue(10);
		slider[7].setValue(20);
		slider[8].setValue(2);
		slider[9].setValue(3);
		slider[10].setValue(160);
		slider[11].setValue(16);

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

//			slider[i].addCaptureListener(new ChangeListener() {
//				@Override
//				public void changed(ChangeEvent event, Actor actor)
//				{
////					Gdx.graphics.requestRendering();
//				}
//			});
		}

		
		TextButton b = new TextButton("Create", skin);
		b.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				create();
			}
		});
		table.add(b).colspan(3).center();
		
		table.left().padLeft(20);
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

	public void create ()
	{
		drawTree = false;
		draw();
		drawTree = true;
	}
	
	private void draw ()
	{
		killCount = -1;
		killCountMax = 100000;
		drawCircles = false;

		xList = new double[killCountMax];
		yList = new double[killCountMax];
		nxList = new double[killCountMax];
		nyList = new double[killCountMax];
		thickList = new double[killCountMax];
		colorList = new double[killCountMax];
		
		drawRecursive(slider[10].getValue(), slider[0].getValue(), slider[1].getValue(), Math.PI/2, slider[11].getValue());
	}
	
	
	private void drawRecursive (double length, double x, double y, double theta, float thickness)
	{
		float maxLength = slider[10].getValue();
		float sizeMin = slider[2].getValue();
		float sizeMax = slider[3].getValue();
		float scaleMin = slider[4].getValue();
		float scaleMax = slider[5].getValue();
		float thetaMin = slider[6].getValue();
		float thetaMax = slider[7].getValue();
		float branchesMin = slider[8].getValue();
		float branchesMax = slider[9].getValue();
		
		killCount++;
		if ( killCount >= killCountMax )
			return;
		if ( length <= 1)
		{
//			if(drawCircles)
//				sr.ellipse((int)x,(int)y, (int)(Math.random()*6), (int)(Math.random()*6));
			return;
		}
		if (  maxLength * Math.pow(Math.random()*(scaleMax/100.-scaleMin/100.)+scaleMin/100., Math.random()*(sizeMax-sizeMin+1)+sizeMin) > length )
		{
//			if(drawCircles)
//				g.drawOval((int)x,(int)y, (int)(Math.random()*6), (int)(Math.random()*6));
			return;
		}
		
		double nx = Math.cos(theta)*length+x;
		double ny = Math.sin(theta)*length+y;
		
//		if ( randomColor )
//			g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
//		else if (shadeColor)
//		{
//			int shade = (int)(Math.random()*3);
//			
//			if ( shade == 2 )
//				g.setColor(color.darker());
//			else if ( shade == 1 )
//				g.setColor(color.brighter());
//			else
//				g.setColor(color);
//		}
//		else
//			sr.setColor(Color.WHITE);
		
		
//		sr.rectLine((int)x,(int)y,(int)nx,(int)ny,thickness);
		xList[killCount] = x;
		yList[killCount] = y;
		nxList[killCount] = nx;
		nyList[killCount] = ny;
		thickList[killCount] = thickness;
		colorList[killCount] = Math.random()*.5+.5;
		
		double scale = (Math.random()*(scaleMax/100.-scaleMin/100.)+scaleMin/100.);
		
		double nlength = length * scale;
		float thicknessn = Math.max((float)(thickness * scale * scale),1);
		
		int branches = (int)(Math.random()*(branchesMax-branchesMin+1)+branchesMin);
		
		int flip = 1;
		
		for ( int i = 0; i < branches; i++)
		{
			drawRecursive(nlength, nx, ny, theta + (Math.PI*(Math.random()*(thetaMax/100.-thetaMin/100.)+thetaMin/100.))*flip, thicknessn);
			flip*=-1;
		}
	}
	
}
