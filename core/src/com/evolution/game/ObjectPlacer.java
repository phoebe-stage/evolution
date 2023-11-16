package com.evolution.game;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class ObjectPlacer {

    double maxX;
    double maxY;
    int numObjects;
    float objectRadius;

    public ObjectPlacer(int maxX, int maxY, int numObjects, float objectRadius) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.numObjects = numObjects;
        this.objectRadius = objectRadius;
    }

    public abstract Vector2 getNextCoords();

}
