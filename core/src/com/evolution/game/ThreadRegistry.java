package com.evolution.game;

import com.evolution.game.sensors.AngularSensor;
import com.evolution.game.sensors.GuySensor;
import com.evolution.game.sensors.sensorFactories.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ThreadRegistry {

    private ThreadRegistry instance;

    private HashMap<Integer, SensorFactory> sensorRegistry = new HashMap<>();

    public ThreadRegistry() {
        sensorRegistry.put(0, new GuySensorFactory());
        sensorRegistry.put(1, new ObstacleSensorFactory());
        sensorRegistry.put(2, new CloseGuySensorFactory());
        sensorRegistry.put(3, new DirectionSensorFactory());
        sensorRegistry.put(4, new StuckSensorFactory());
        sensorRegistry.put(5,new AlwaysSensorFactory());
        sensorRegistry.put(6,new WallSensorFactory());
    }

    public ThreadRegistry getInstance() {
        if (instance == null) {
            this.instance = new ThreadRegistry();
        }
        return instance;
    }

    public int sensorRegistrySize() {
        return sensorRegistry.size();
    }

    public AngularSensor getSensor(int sensorNum, Guy guy) {
        return sensorRegistry.get(sensorNum).createNewInstance(guy);
    }

}
