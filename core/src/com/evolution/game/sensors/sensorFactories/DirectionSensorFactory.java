package com.evolution.game.sensors.sensorFactories;

import com.evolution.game.Guy;
import com.evolution.game.sensors.AngularSensor;
import com.evolution.game.sensors.DirectionSensor;

public class DirectionSensorFactory extends SensorFactory{
    @Override
    public AngularSensor createNewInstance(Guy guy) {
        return new DirectionSensor(guy);
    }
}
