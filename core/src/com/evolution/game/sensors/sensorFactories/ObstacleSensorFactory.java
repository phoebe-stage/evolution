package com.evolution.game.sensors.sensorFactories;

import com.evolution.game.Guy;
import com.evolution.game.sensors.AngularSensor;
import com.evolution.game.sensors.ObstacleSensor;

public class ObstacleSensorFactory extends SensorFactory{
    @Override
    public AngularSensor createNewInstance(Guy guy) {
        return new ObstacleSensor(guy);
    }
}
