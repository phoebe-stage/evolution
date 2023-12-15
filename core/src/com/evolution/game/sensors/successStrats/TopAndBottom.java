package com.evolution.game.sensors.successStrats;

import com.evolution.game.Guy;
import com.evolution.game.constants;

public class TopAndBottom extends SuccessCriteria{
    @Override
    public boolean isSuccessful(Guy guy) {
        return guy.getY() < 20 | guy.getY() > constants.SCREENHEIGHT - 20;
    }
}
