package com.evolution.game.sensors.sensorFactories;

import com.evolution.game.Guy;
import com.evolution.game.sensors.AngularSensor;
import com.evolution.game.sensors.WallSensor;

public class WallSensorFactory extends SensorFactory{
    @Override
    public AngularSensor createNewInstance(Guy guy) {
        return new WallSensor(guy);
    }
}
