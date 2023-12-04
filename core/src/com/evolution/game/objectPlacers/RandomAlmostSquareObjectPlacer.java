package com.evolution.game.objectPlacers;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.obstacles.RectObstacle;

import java.util.ArrayList;
import java.util.Random;

public class RandomAlmostSquareObjectPlacer extends ObjectPlacer{

    private ArrayList<Vector2> grids = new ArrayList<>();
    private Random random;
    private int totalNumberGrids;
    private double horizontalGridSize;
    private double verticalGridSize;
    private int numHorizontal;
    private int numVertical;
    public RandomAlmostSquareObjectPlacer(int maxX, int maxY, int numObjects, int objectRadius, ArrayList<RectObstacle> obstacles) {
        super(maxX, maxY, numObjects, objectRadius,obstacles);
        int sumAreas = 0;
        for (RectObstacle obstacle : obstacles) {
            sumAreas += obstacle.getArea();
        }
        this.random = new Random();
        int multiplier = 3;
        while (true) {
            totalNumberGrids = multiplier *(int)Math.pow(((int)Math.ceil(Math.sqrt(numObjects))),2);
            double squareSize = Math.sqrt((double) (maxX * maxY) /totalNumberGrids);
            numHorizontal = (int) Math.ceil(maxX/squareSize);
            numVertical = (int) Math.ceil(maxY/squareSize);
            horizontalGridSize = (double) maxX /(numHorizontal);
            verticalGridSize = (double) maxY /numVertical;
            if (sumAreas/(horizontalGridSize*verticalGridSize)>(numHorizontal*numVertical-numObjects)) {
                multiplier+=1;
            } else {
                break;
            }
        }
        if (objectRadius*2>horizontalGridSize || objectRadius*2>verticalGridSize) {
            throw new RuntimeException("Objects are too large for the grids");
        }
        constructGridArray();
    }

    public void constructGridArray() {
        for (int i= 0; i < numHorizontal; i++) {
            for (int j= 0; j < numVertical; j++) {
                grids.add(new Vector2((float) (horizontalGridSize*i), (float) (verticalGridSize*j)));
            }
        }
    }

    @Override
    public Vector2 getNextCoords() {
        Vector2 returnCoord;
        boolean contained = false;
        while (true) {
            contained = false;
            returnCoord = grids.remove(random.nextInt(grids.size()));
            returnCoord.x += objectRadius;
            returnCoord.y += objectRadius;
            returnCoord.x+=random.nextFloat((float) (horizontalGridSize-objectRadius*2));
            returnCoord.y+=random.nextFloat((float) (verticalGridSize-objectRadius*2));
            for (RectObstacle obstacle : rects) {
                if (obstacle.contains(returnCoord,objectRadius)) {
                    contained = true;
                }
            }
            if (!contained) {
                return returnCoord;
            }

        }
    }
}
