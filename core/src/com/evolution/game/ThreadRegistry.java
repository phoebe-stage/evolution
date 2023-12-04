package com.evolution.game;

import com.evolution.game.sensors.AngularSensor;
import com.evolution.game.sensors.GuySensor;
import com.evolution.game.sensors.sensorFactories.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ThreadRegistry {

    private ThreadRegistry instance;

    private HashMap<Integer, SensorFactory> sensorRegistry = new HashMap<>();

    private ThreadRegistry() {
        sensorRegistry.put(1, new GuySensorFactory());
        sensorRegistry.put(2, new ObstacleSensorFactory());
        sensorRegistry.put(3, new CloseGuySensorFactory());
        sensorRegistry.put(4, new DirectionSensorFactory());
        sensorRegistry.put(5, new StuckSensorFactory());
    }

    public void getInstance() {
        this.instance = new ThreadRegistry();
    }



}
