package com.evolution.game;

import com.badlogic.gdx.math.Vector2;

public class GridObjectPlacer extends ObjectPlacer {

    private float currX;
    private float currY;
    public GridObjectPlacer(int maxX, int maxY, int numberObjects, float objectRadius) {
        super(maxX, maxY, numberObjects, objectRadius);
        currX = 10;
        currY = 10;
    }

    @Override
    public Vector2 getNextCoords() {
        currX += 20;
        return new Vector2(currX,currY);
    }
}
