package com.evolution.game.sensors.sensorFactories;

import com.evolution.game.Guy;
import com.evolution.game.sensors.AngularSensor;

public abstract class SensorFactory {
    public abstract AngularSensor createNewInstance(Guy guy);
}
