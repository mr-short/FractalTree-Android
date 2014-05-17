package net.eitr.fractaltree.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import net.eitr.fractaltree.FractalTree;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
//            return new GwtApplicationConfiguration(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            return new GwtApplicationConfiguration(1600, 900);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new FractalTree();
        }
}