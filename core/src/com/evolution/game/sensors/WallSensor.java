package com.evolution.game.sensors;

import com.evolution.game.Guy;
import com.evolution.game.constants;

public class WallSensor extends AngularSensor{
    public WallSensor(Guy guy) {
        super(guy);
    }

    @Override
    public void filterAndClean() {
    }

    @Override
    public void calculate() {
        vectorSum.set(0,0);
        if (guy.getX()-guy.getSensingRadius() <= 0) {
            vectorSum.add(-1,0);
        }
        if (guy.getX()+guy.getSensingRadius() >= constants.SCREENWIDTH) {
            vectorSum.add(1,0);
        }
        if (guy.getY()-guy.getSensingRadius()<=0) {
            vectorSum.add(0,-1);
        }
        if (guy.getY() + guy.getSensingRadius() >= constants.SCREENHEIGHT) {
            vectorSum.add(0,1);
        }

    }
}
