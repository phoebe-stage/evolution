package com.evolution.game.sensors.successStrats;

import com.evolution.game.Guy;
import com.evolution.game.constants;

public class ToTheRight extends SuccessCriteria{
    @Override
    public boolean isSuccessful(Guy guy) {
        return guy.getX()> (float) constants.SCREENWIDTH -100;
    }
}
