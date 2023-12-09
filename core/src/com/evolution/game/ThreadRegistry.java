package com.evolution.game;

import com.evolution.game.sensors.AngularSensor;
import com.evolution.game.sensors.GuySensor;
import com.evolution.game.sensors.sensorFactories.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ThreadRegistry {

    private ThreadRegistry instance;

    private HashMap<Integer, SensorFactory> sensorRegistry = new HashMap<>();

    private int maxThread = 0;
    private int minThread = 0;

    private int maxThreadSum = 0;
    private int minThreadSum = 0;

    public ThreadRegistry() {
        sensorRegistry.put(0, new GuySensorFactory());
        sensorRegistry.put(1, new ObstacleSensorFactory());
        sensorRegistry.put(2, new CloseGuySensorFactory());
        sensorRegistry.put(3, new DirectionSensorFactory());
        sensorRegistry.put(4, new StuckSensorFactory());
        sensorRegistry.put(5,new AlwaysSensorFactory());
        sensorRegistry.put(6,new WallSensorFactory());
        ColourMaster.setDivisions(sensorRegistrySize());
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

    public void registerThread(Thread thread) {
        int threadNumInt = Integer.parseInt(thread.getThreadNum());
        if (minThread == 0 ) {
            minThread = threadNumInt;
        }
        if (maxThread < threadNumInt) {
            maxThread = threadNumInt;
        }
        if (minThread > threadNumInt) {
            minThread = threadNumInt;
        }
    }

    public int getMaxThread() {
        return this.maxThread;
    }

    public int getMinThread() {
        return this.minThread;
    }

    public void registerThreadSum(int sum) {
        if (minThreadSum == 0 ) {
            minThreadSum = sum;
        }
        if (maxThreadSum < sum) {
            maxThreadSum = sum;
        }
        if (minThreadSum > sum) {
            minThreadSum = sum;
        }
    }

    public int getMaxThreadSum() {
        return maxThreadSum;
    }

    public int getMinThreadSum() {
        return minThreadSum;
    }
}
