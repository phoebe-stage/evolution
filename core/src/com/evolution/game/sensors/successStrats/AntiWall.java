package com.evolution.game.sensors.successStrats;

import com.evolution.game.Guy;
import com.evolution.game.constants;

public class AntiWall extends SuccessCriteria{
    @Override
    public boolean isSuccessful(Guy guy) {
        if (guy.getY() < 100 | guy.getY() > constants.SCREENHEIGHT-100) {
            return false;
        }

        if (guy.getX() < 100 | guy.getX() > constants.SCREENWIDTH-100) {
            return false;
        }
        return true;
    }
}
