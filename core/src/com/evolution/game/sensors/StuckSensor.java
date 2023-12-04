package com.evolution.game.sensors;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Guy;

public class StuckSensor extends AngularSensor{


    public StuckSensor(Guy guy) {
        super(guy);
    }

    @Override
    public void filterAndClean() {

    }

    @Override
    public void calculate() {
        this.vectorSum.setLength(this.vectorSum.len()/2);
        if (!guy.isSuccessfulThink()) {
            vectorSum.add(guy.getDirection());
        }
    }
}
