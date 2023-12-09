package com.evolution.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class ColourMaster {
    private static int divisions = 4;
    public static Color getColour(float place, float minimum, float maximum) {
        float red;
        float green;
        float blue;
        float percent = (place - minimum) /(maximum-minimum);
        red = (float) Math.cos(7*percent-1)+0.5f;
        green = (float) Math.cos(7*percent - 3)+0.5f;
        blue = (float) Math.cos(6*percent - 5)+0.3f;
//        System.out.println(red);
        return (new Color(red,green,blue,1));
    }

    public static int getDivisions() {
        return divisions;
    }

    public static void setDivisions(int num) {
        divisions = num;
    }

    public static Color getColour(int number) {
        return getColour((float) 1 /divisions*number,0,1);
    }

    public static Vector3 getColourVector(int number) {
        Color color = getColour(number);
        return new Vector3(color.r,color.g,color.b);
    }
}
