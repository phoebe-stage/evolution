package com.evolution.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.awt.*;

public class World implements Screen,constants  {
    OrthographicCamera camera;

    WorldGame game;


    public World(WorldGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, SCREENWIDTH, SCREENHEIGHT);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        for (Entity guy : game.entities) {
            game.shapeDrawer.setColor(guy.getColor());
            game.shapeDrawer.filledCircle(guy.getX(),guy.getY(),guy.getRadius());
        }
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
