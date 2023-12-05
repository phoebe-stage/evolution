package com.evolution.game.sensors.sensorFactories;

import com.evolution.game.Guy;
import com.evolution.game.sensors.AlwaysSensor;
import com.evolution.game.sensors.AngularSensor;

public class AlwaysSensorFactory extends SensorFactory{
    @Override
    public AngularSensor createNewInstance(Guy guy) {
        return new AlwaysSensor(guy);
    }
}
