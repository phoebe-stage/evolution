package com.evolution.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.evolution.game.chunks.Chunk;
import com.evolution.game.obstacles.ObstacleParticle;
import com.evolution.game.sensors.*;

import java.util.ArrayList;
import java.util.Random;

public class Guy extends Mover{

    private int type;
    private int angle;

    private Vector2 standardDirection = new Vector2(1,0);

    private Color color;

    private int sensingRadius;

    private Thread threadOne = new Thread(constants.threadRegistry,this);

    private AngularSensor obstacleSensor = new ObstacleSensor(this);
    private AngularSensor guySensor = new GuySensor(this);
    private AngularSensor stuckSensor = new StuckSensor(this);
    private AngularSensor closeSensor = new CloseGuySensor(this);
    private AngularSensor directionSensor = new DirectionSensor(this);

    private ArrayList<Entity> sensedEntities = new ArrayList<>();

    private Vector2 vectorSum = new Vector2(0,0);
    private Random random = new Random();

    public Guy(Vector2 position, int radius) {
        super(position, radius, constants.DEFAULT_GUY_SPEED);
        this.setColor();
        this.angle = random.nextInt(360);
        sensingRadius = constants.SENSING_RADIUS;
        this.direction.set(standardDirection);
        this.type = random.nextInt(3);
    }

    public Color getColor() {
        this.setColor();
        return this.color;
    }


    public void setColor() {
        int chunkNum = 0;

        for (Chunk chunk : chunks) {
            chunkNum += chunk.getNumber();
        }
        if (chunkNum != 0) {
            chunkNum = chunkNum/chunks.size();
        }

        chunkNum ++;


        float red = (float) ((0.1*(chunkNum*4 % 5))+0.1);
        float blue = (float) ((0.1*(chunkNum*4 % 6))+0.1);
        float green = (float) ((0.1*(chunkNum*4 % 7))+0.1);
        this.color = new Color(red,green,blue,1);
        if (chunks.size()>1) {
            this.color.set(1,1,1,1);
        }
//        this.color = new Color((float) (1-(0.1*(chunkNum+1))), (float) (0.02*(chunkNum+1)), (float) (0.01*(chunkNum+1)),1);
    }


    @Override
    public void think() {
//        this.direction.add(VectorBoss.randomVector().setLength(20));
//        this.direction.add(standardDirection.setLength(15));
        sensedEntities.clear();
        for (Chunk chunk : chunks) {
            for (Entity entity : chunk.getEntities()) {
                if (this.position.dst(entity.getPosition())<this.sensingRadius && !sensedEntities.contains(entity)) {
                    sensedEntities.add(entity);
                }
            }
        }
        threadOne.load(sensedEntities);
        this.direction.add(threadOne.sense());
//        obstacleSensor.takeInEntities(sensedEntities);
//        obstacleSensor.calculate();
//        this.direction.add(obstacleSensor.getVectorSum().setLength(10).rotateDeg(180));
//        stuckSensor.takeInEntities(sensedEntities);
//        stuckSensor.calculate();
//        closeSensor.takeInEntities(sensedEntities);
//        closeSensor.calculate();
//        if (closeSensor.getVectorSum()!=Vector2.Zero) {
//            this.reAdjusting();
//        } else {
//            this.unAdjust();
//        }
//        this.direction.add(closeSensor.getVectorSum().setLength(40).rotateDeg(180));
////        this.direction.add(standardDirection).setLength(70);
//////        this.direction.add(new Vector2(1,0).setLength(10));
//        if (random.nextInt(4)==2) {
//            this.direction.add(VectorBoss.randomVector().setLength(20));
//        }
////        this.direction.add(stuckSensor.getVectorSum().rotateDeg(90).setLength(10));
//
//
//
//        guySensor.takeInEntities(sensedEntities);
//        guySensor.calculate();
////        this.direction.add(guySensor.getVectorSum().setLength(25).rotateDeg(0));
//        sensedEntities.clear();
//        directionSensor.takeInEntities(sensedEntities);
//        directionSensor.calculate();
//        this.direction.add(guySensor.getVectorSum().setLength(10).rotateDeg(0));
//        if (type != 1) {
//            this.direction.add(standardDirection.setLength(30));
//        }
//        if (type == 1) {
//            this.direction.add(directionSensor.getVectorSum().setLength(40));
//        }

//        vectorSum.set(0,0);

        this.move();


    }

    @Override
    public boolean cantCollide() {
        return true;
    }

    public int getSensingRadius() {
        return sensingRadius;
    }
}
