package com.evolution.game.sensors.successStrats;

import com.evolution.game.Guy;
import com.evolution.game.constants;

public class Corners extends SuccessCriteria{
    @Override
    public boolean isSuccessful(Guy guy) {
        if (guy.getPosition().dst(0,0) < 50) {
            return true;
        } else if (guy.getPosition().dst(constants.SCREENWIDTH,0) < 50) {
            return true;
        } else if (guy.getPosition().dst(constants.SCREENWIDTH,constants.SCREENHEIGHT) < 50) {
            return true;
        } else if (guy.getPosition().dst(0,constants.SCREENHEIGHT) < 50) {
            return true;
        }
        return false;
    }
}
