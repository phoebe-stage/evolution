package com.evolution.game;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class VectorBoss {

    private static Random random = new Random();
    public static double calculateAngleDeg(Vector2 position, Vector2 reference) {
        return Math.round(Math.toDegrees(calculateAngleRad(position,reference))%360);
    }

    public static float calculateAngleRad(Vector2 position, Vector2 reference) {
        Vector2 basis = new Vector2(0,-1);
        float angle = basis.angleRad(vectorBetween(reference,position));
        return (float) (2*Math.PI - angle);
    }

    public static Vector2 vectorBetween(Vector2 start, Vector2 end) {
        Vector2 workingVector = new Vector2(end);
        return workingVector.sub(start);
    }

    public static Vector2 randomVector() {
        boolean positiveX = random.nextBoolean();
        boolean positiveY = random.nextBoolean();
        int x = random.nextInt(5);
        int y = random.nextInt(5);
        if (!positiveX) {
            x = x*-1;
        }
        if (!positiveY) {
            y=y*-1;
        }
        return new Vector2(x,y);

    }


}
