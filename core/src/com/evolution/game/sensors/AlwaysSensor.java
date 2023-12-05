package com.evolution.game.sensors;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Entity;
import com.evolution.game.Guy;
import com.evolution.game.VectorBoss;
import com.evolution.game.constants;

public class AlwaysSensor extends AngularSensor{
    public AlwaysSensor(Guy guy) {
        super(guy);
    }

    @Override
    public void filterAndClean() {

    }

    @Override
    public void calculate() {
        vectorSum.set(0,-1);
    }
}
