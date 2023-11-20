package com.evolution.game.objectPlacers;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.obstacles.RectObstacle;

import java.util.ArrayList;
import java.util.Random;

public class RandomGridObjectPlacer extends ObjectPlacer {

    private ArrayList<Vector2> grids = new ArrayList<>();
    private Random random;
    private int numGrids;
    private float horizontalGridSize;
    private float verticalGridSize;

    public RandomGridObjectPlacer(int maxX, int maxY, int numObjects, int objectRadius, ArrayList<RectObstacle> obstacles) {
        super(maxX,maxY,numObjects,objectRadius,obstacles);
        this.random = new Random();
        numGrids = 3 * (int) (Math.ceil(Math.sqrt(numObjects)));
        horizontalGridSize = (float) maxX /numGrids;
        verticalGridSize = (float) maxY /numGrids;
        if (objectRadius*2>horizontalGridSize || objectRadius*2>verticalGridSize) {
            throw new RuntimeException("Objects are too large for the grids");
        }
        constructGridArray();
    }

    public void constructGridArray() {
        for (int i= 0; i < numGrids; i++) {
            for (int j= 0; j < numGrids; j++) {
                grids.add(new Vector2(horizontalGridSize*i, verticalGridSize*j));
            }
        }
    }

    @Override
    public Vector2 getNextCoords() {
        Vector2 returnCoord = grids.remove(random.nextInt(grids.size()));
        returnCoord.x += objectRadius;
        returnCoord.y += objectRadius;
        returnCoord.x+=random.nextFloat(horizontalGridSize-objectRadius*2);
        returnCoord.y+=random.nextFloat(verticalGridSize-objectRadius*2);
        return (returnCoord);
    }

}
