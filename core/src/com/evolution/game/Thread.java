package com.evolution.game;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.sensors.AngularSensor;

import java.util.ArrayList;
import java.util.Random;

public class Thread {
    private Random random = new Random();
    private ThreadRegistry threadRegistry;
    private Guy guy;
    private int sensorNum;
    private AngularSensor sensor;
    private int direction;
    private int weighting;

    private void normalise() {
        sensorNum = sensorNum%threadRegistry.sensorRegistrySize();
        direction = direction % 360;
        sensor = threadRegistry.getSensor(sensorNum,guy);
    }
    public Thread(ThreadRegistry threadRegistry, Guy guy) {
        threadRegistry = threadRegistry;
        guy = guy;
    }
    public void init(int sensorNum, int direction, int weighting) {
        sensorNum = sensorNum;
        direction = direction;
        weighting = weighting;
        normalise();
    }
    public void initRand() {
        init(random.nextInt(20),random.nextInt(360),random.nextInt(50));
    }

    public void load(ArrayList<Entity> entities) {
        sensor.takeInEntities(entities);
        sensor.calculate();
    }

    public Vector2 sense() {
        return sensor.getVectorSum().setLength(weighting).setAngleDeg(direction);
    }
}
