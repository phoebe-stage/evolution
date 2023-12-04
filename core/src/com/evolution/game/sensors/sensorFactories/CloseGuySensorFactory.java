package com.evolution.game.sensors.sensorFactories;

import com.evolution.game.Guy;
import com.evolution.game.sensors.AngularSensor;
import com.evolution.game.sensors.CloseGuySensor;

public class CloseGuySensorFactory extends SensorFactory{
    @Override
    public AngularSensor createNewInstance(Guy guy) {
        return new CloseGuySensor(guy);
    }
}
