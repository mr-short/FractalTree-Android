package net.eitr.fractaltree.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
    Table tableLeft, tableRight;
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
    double xList[],yList[],nxList[],nyList[],thickList[],colorList[][];
    boolean leafNode[];
    CheckBox randomColor, shadeColor, drawLeaves;


    Dialog loadDialog, infoDialog;
    
    long lastTime, frameCount = 0;

    @Override
    public void render(float delta)
    {
        frameCount += 1;
        if (System.currentTimeMillis() - lastTime > 1000)
        {
            lastTime = System.currentTimeMillis();
            System.out.println(1.0 / Gdx.graphics.getDeltaTime() + "   " + frameCount);
            frameCount = 0;
        }
        
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
                sr.setColor((float)colorList[i][0],(float)colorList[i][1],(float)colorList[i][2],1);


                if (leafNode[i])
                {
                    sr.ellipse((float)xList[i], (float)yList[i],  (float)nxList[i], (float)nyList[i]);
                }
                else
                {
                    sr.rectLine((float)xList[i], (float)yList[i], (float)nxList[i], (float)nyList[i], (float)thickList[i]);
                }
            }
            sr.end();
            //			drawTree = false;
        }

        stage.act();
        stage.draw();

        //		batch.begin();
        //		batch.end();

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
        tableLeft = new Table(skin);
        tableRight = new Table(skin);
        tableLeft.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tableRight.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //		table.defaults();
        //		table.columnDefaults(0).width(100f);
        tableLeft.columnDefaults(1).width(100f);
        tableRight.columnDefaults(1).width(100f);
        //		table.columnDefaults(2).width(100f);

        menuX = 10; menuY = 10;

        int totalActors = 13;

        slider = new Slider[totalActors];
        labelMin = new Label[totalActors];
        labelMax = new Label[totalActors];
        labelSlider = new Label[totalActors];
        labelCur = new Label[totalActors];

        slider[0] = new Slider(10,Gdx.graphics.getWidth(),10,false,skin);
        slider[1] = new Slider(-Gdx.graphics.getHeight(),Gdx.graphics.getHeight(),10,false,skin);
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
        slider[12] = new Slider(1,200,1,false,skin);

        slider[0].setValue(1000);
        slider[1].setValue(10);
        slider[2].setValue(8);
        slider[3].setValue(12);
        slider[4].setValue(75);
        slider[5].setValue(85);
        slider[6].setValue(10);
        slider[7].setValue(20);
        slider[8].setValue(2);
        slider[9].setValue(3);
        slider[10].setValue(130);
        slider[11].setValue(16);
        slider[12].setValue(50);

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
        labelSlider[12] = new Label("Memory Usage",skin);


        for (int i = 0; i < 8; i++)
        {
            labelMin[i] = new Label((int)slider[i].getMinValue()+"",skin);
            labelMax[i] = new Label((int)slider[i].getMaxValue()+"",skin);
            labelCur[i] = new Label(" ",skin);

            labelMin[i].setAlignment(Align.left);
            labelMax[i].setAlignment(Align.right);
            labelCur[i].setAlignment(Align.center);

            slider[i].setWidth(300);
            slider[i].setName("Slider"+i);

            tableLeft.add(labelSlider[i]).colspan(3).center().row();
            tableLeft.add(slider[i]).colspan(3).fillX().row();
            tableLeft.add(labelMin[i]).left();
            tableLeft.add(labelCur[i]).center();
            tableLeft.add(labelMax[i]).right();
            tableLeft.row();

            //            if (i >= 2 && i <= 9)
            slider[i].addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {
                    checkMinMax(actor.getName());
                    createTree();
                }
            });
        }

        for (int i = 8; i < slider.length; i++)
        {
            labelMin[i] = new Label((int)slider[i].getMinValue()+"",skin);
            labelMax[i] = new Label((int)slider[i].getMaxValue()+"",skin);
            labelCur[i] = new Label(" ",skin);

            labelMin[i].setAlignment(Align.left);
            labelMax[i].setAlignment(Align.right);
            labelCur[i].setAlignment(Align.center);

            slider[i].setWidth(300);
            slider[i].setName("Slider"+i);

            tableRight.add(labelSlider[i]).colspan(3).center().row();
            tableRight.add(slider[i]).colspan(3).fillX().row();
            tableRight.add(labelMin[i]).left();
            tableRight.add(labelCur[i]).center();
            tableRight.add(labelMax[i]).right();
            tableRight.row();

            //            if (i >= 2 && i <= 9)
            slider[i].addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {
                    checkMinMax(actor.getName());
                    createTree();
                }
            });
        }


        randomColor = new CheckBox("Colors", skin);
        tableRight.add(randomColor).colspan(3).left().row();

        drawLeaves = new CheckBox("Leaves", skin);
        drawLeaves.toggle();
        tableRight.add(drawLeaves).colspan(3).left().row();

        shadeColor = new CheckBox("Shade Tree", skin);
        shadeColor.toggle();
        tableRight.add(shadeColor).colspan(3).left().row();

        TextButton startButton = new TextButton("Create", skin);
        startButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                createTree();
            }
        });
        tableRight.add(startButton).colspan(2).left();

        TextButton infoButton = new TextButton("Info", skin);
        infoButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                //                ((Game)Gdx.app.getApplicationListener()).setScreen(new InfoScreen());
                infoDialog.show(stage);
            }
        });
        tableRight.add(infoButton);

        TextButton exitButton = new TextButton("Quit", skin);
        exitButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Gdx.app.exit();
            }
        });
        //        tableRight.add(exitButton);


        loadDialog = new Dialog("Loading...", skin);
        infoDialog = new Dialog("Info",skin);
        Label infoLabel [] = new Label[13];

        infoLabel[0] = new Label("Location: Set horizontal position of tree on the screen.",skin);
        infoLabel[1] = new Label("Size: Approximate number of branches from root to tip.",skin);
        infoLabel[2] = new Label("Scale (%): Size factor of new branch based on previous one.",skin);
        infoLabel[3] = new Label("Angle (%): Amount of turn for next branch. 0 = parallel, 50% = perpendicular.",skin);
        infoLabel[4] = new Label("Branches: Amount of branches to create off the previous one.",skin);
        infoLabel[5] = new Label("Start Length: Distance of root branch. Affects height of tree.",skin);
        infoLabel[6] = new Label("Start Width: Size of the trunk. New branches are proportionately smaller.",skin);
        infoLabel[7] = new Label("Memory Usage: Total number of branches allowed to be created. (in thousands)",skin);
        infoLabel[8] = new Label("",skin);
        infoLabel[9] = new Label("If the tree is curved and only one sided, it is creating too many branches,",skin);
        infoLabel[10] = new Label("thus runs out of memory to create the rest. This saves your computer from crashing.",skin);
        infoLabel[11] = new Label("Try reducing the max size of the tree or how many branches it is trying to create.",skin);
        infoLabel[11] = new Label("",skin);
        infoLabel[12] = new Label("If it takes too long to load, try reducing memory usage. It's only needed for bushier trees.",skin);

        infoDialog.row();
        for (int i = 0; i < infoLabel.length; i++)
        {
            infoDialog.add(infoLabel[i]).center().row();
        }


        TextButton closeDialog = new TextButton("Close", skin);
        closeDialog.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                infoDialog.hide();
            }
        });
        infoDialog.add(closeDialog);
        Dialog.fadeDuration = 0f;		

        tableLeft.left().padLeft(20);
        tableRight.left().padLeft(200);
        tableLeft.setWidth(140);
        tableRight.setWidth(140);
        tableLeft.setY(0);
        tableRight.setY((tableLeft.getMinHeight()-tableRight.getMinHeight())/2);
        stage.addActor(tableLeft);
        stage.addActor(tableRight);
        createTree();
    }

    private void checkMinMax (String name)
    {
        int i = Integer.parseInt(name.substring(6));
        switch (i)
        {
            case 2: if (slider[2].getValue() > slider[3].getValue())
                slider[3].setValue(slider[2].getValue());
            break;
            case 3: if (slider[2].getValue() > slider[3].getValue())
                slider[2].setValue(slider[3].getValue());
            break;
            case 4: if (slider[4].getValue() > slider[5].getValue())
                slider[5].setValue(slider[4].getValue());
            break;
            case 5: if (slider[4].getValue() > slider[5].getValue())
                slider[4].setValue(slider[5].getValue());
            break;
            case 6: if (slider[6].getValue() > slider[7].getValue())
                slider[7].setValue(slider[6].getValue());
            break;
            case 7: if (slider[6].getValue() > slider[7].getValue())
                slider[6].setValue(slider[7].getValue());
            break;
            case 8: if (slider[8].getValue() > slider[9].getValue())
                slider[9].setValue(slider[8].getValue());
            break;
            case 9: if (slider[8].getValue() > slider[9].getValue())
                slider[8].setValue(slider[9].getValue());
            break;
            default: assert false; break;
        }
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
        batch.dispose();
        sr.dispose();
    }

    public void createTree ()
    {
        drawTree = false;
        loadDialog.show(stage);
        buildTree();
        loadDialog.hide();
        drawTree = true;
    }

    private void buildTree ()
    {
        killCount = -1;
        killCountMax = (int)slider[12].getValue()*1000;

        xList = new double[killCountMax];
        yList = new double[killCountMax];
        nxList = new double[killCountMax];
        nyList = new double[killCountMax];
        thickList = new double[killCountMax];
        colorList = new double[killCountMax][3];
        leafNode = new boolean[killCountMax];

        double r,g,b;
        r=g=b=.9;

        if (randomColor.isChecked())
        {
            r = Math.random()*1;
            g = Math.random()*1;
            b = Math.random()*1;
        }

        buildRecursive(slider[10].getValue(), slider[0].getValue(), slider[1].getValue(), Math.PI/2, slider[11].getValue(), r, g, b);
    }


    private void buildRecursive (double length, double x, double y, double theta, float thickness, double r, double g, double b)
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


        double nx = Math.cos(theta)*length+x;
        double ny = Math.sin(theta)*length+y;

        xList[killCount] = x;
        yList[killCount] = y;
        nxList[killCount] = nx;
        nyList[killCount] = ny;
        thickList[killCount] = thickness;
        colorList[killCount][0] = r;
        colorList[killCount][1] = g;
        colorList[killCount][2] = b;
        leafNode[killCount] = false;

        if(shadeColor.isChecked())
        {
            double color = Math.random()*.4-.3;
            colorList[killCount][0] += color;
            colorList[killCount][1] += color;
            colorList[killCount][2] += color;
        }

        if ( length <= 1 || (maxLength * Math.pow(Math.random()*(scaleMax/100.-scaleMin/100.)+scaleMin/100., Math.random()*(sizeMax-sizeMin+1)+sizeMin) > length) )
        {
            nxList[killCount] = 1;
            nyList[killCount] = 1;
            leafNode[killCount] = true;

            if(drawLeaves.isChecked())
            {
                nxList[killCount] = Math.random()*6+1;
                nyList[killCount] = Math.random()*6+1;
            }

            return;
        }

        double scale = (Math.random()*(scaleMax/100.-scaleMin/100.)+scaleMin/100.);

        double nlength = length * scale;
        float thicknessn = Math.max((float)(thickness * scale * scale),1);

        int branches = (int)(Math.random()*(branchesMax-branchesMin+1)+branchesMin);

        int flip = 1;

        for ( int i = 0; i < branches; i++)
        {
            buildRecursive(nlength, nx, ny, theta + (Math.PI*(Math.random()*(thetaMax/100.-thetaMin/100.)+thetaMin/100.))*flip, thicknessn, r, g, b);
            flip*=-1;
        }
    }

}
