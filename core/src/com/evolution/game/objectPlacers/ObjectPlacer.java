package com.evolution.game.objectPlacers;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.obstacles.RectObstacle;

import java.util.ArrayList;

public abstract class ObjectPlacer {

    double maxX;
    double maxY;
    int numObjects;
    int objectRadius;
    ArrayList<RectObstacle> rects;

    public ObjectPlacer(int maxX, int maxY, int numObjects, int objectRadius, ArrayList<RectObstacle> obstacles) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.numObjects = numObjects;
        this.objectRadius = objectRadius;
        rects=obstacles;
    }

    public abstract Vector2 getNextCoords();

}
