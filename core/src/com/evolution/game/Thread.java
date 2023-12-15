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
        if (sensorNum <0) {
            sensorNum = threadRegistry.sensorRegistrySize()-sensorNum;
        }
        sensorNum = sensorNum%threadRegistry.sensorRegistrySize();
//        System.out.println(String.format("%02d", sensorNum));
        if (direction <0) {
            direction = 360 - direction;
        }
        direction = direction % 360;
        if (weighting >= 100) {
            weighting = 100;
        } else if (weighting<=0) {
            weighting = 0;
        }
        sensor = threadRegistry.getSensor(sensorNum,guy);
    }
    public Thread(ThreadRegistry threadRegistry, Guy guy) {
        this.threadRegistry = threadRegistry;
        this.guy = guy;
    }
    public void init(int sensorNum, int direction, int weighting) {
        this.sensorNum = sensorNum;
        this.direction = direction;
        this.weighting = weighting;
        normalise();
    }
    public void initRand() {
        init(random.nextInt(20),random.nextInt(360),random.nextInt(50));
    }

    public void load(ArrayList<Entity> entities) {
        sensor.takeInEntities(entities);
        sensor.calculate();
    }
    public void mutate() {
        double sensorThreshold = 0.1;
        double weightingThreshold = 0.5;
        double randomDouble = random.nextDouble();
        if(randomDouble<sensorThreshold) {
            if(random.nextBoolean()) {
                sensorNum ++;
            } else{
                sensorNum--;
            }
        } else if (randomDouble < weightingThreshold) {
            if(random.nextBoolean()) {
                weighting += 10;
            } else{
                weighting-=10;
            }
        } else {
            if(random.nextBoolean()) {
                direction +=10;
            } else{
                direction-=10;
            }
        }
        normalise();
    }

    public Vector2 sense() {
        return sensor.getVectorSum().setLength(weighting).rotateDeg(direction);
    }

    public String toString() {
        return getThreadNum() + " - " + sensor + " angle:" + direction + " weighting:" + weighting;
    }
    public AngularSensor getSensor() {
        return this.sensor;
    }

    public String getThreadNum() {
        String string;
        string = String.format("%02d", sensorNum);
        string+=String.format("%03d", direction);
        string+=String.format("%03d", weighting);
        return string;
    }

    public int getSensorNum() {
        return sensorNum;
    }

    public int getWeighting() {
        return weighting;
    }

    public Thread duplicate(Guy guy) {
        Thread newThread = new Thread(threadRegistry,guy);
        newThread.init(this.sensorNum,this.direction,this.weighting);
        return newThread;
    }

}
