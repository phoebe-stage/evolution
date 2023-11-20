package com.evolution.game.objectPlacers;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.obstacles.RectObstacle;

import java.util.ArrayList;

public class GridObjectPlacer extends ObjectPlacer {

    private float currX;
    private float currY;
    public GridObjectPlacer(int maxX, int maxY, int numberObjects, int objectRadius, ArrayList<RectObstacle> obstacles) {
        super(maxX, maxY, numberObjects, objectRadius,obstacles);
        currX = 10;
        currY = 10;
    }

    @Override
    public Vector2 getNextCoords() {
        currX += 20;
        return new Vector2(currX,currY);
    }
}
