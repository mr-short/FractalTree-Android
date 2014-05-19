package net.eitr.fractaltree.client;

import net.eitr.fractaltree.FractalTree;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () 
        {
//            return new GwtApplicationConfiguration(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            GwtApplicationConfiguration config = new GwtApplicationConfiguration(1500, 700);
            config.fps = 4;
            return config;
        }

        @Override
        public ApplicationListener getApplicationListener () 
        {
                return new FractalTree();
        }
}