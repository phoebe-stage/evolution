package com.evolution.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.evolution.game.chunks.Chunk;
import com.evolution.game.sensors.*;

import java.util.ArrayList;
import java.util.Random;

public class Guy extends Mover{

    private int type;
    private int angle;

    private Vector2 standardDirection = new Vector2(1,0);

    private Color color;

    private int sensingRadius;

    private Thread threadOne = new Thread(constants.THREAD_REGISTRY,this);
    private Thread threadTwo = new Thread(constants.THREAD_REGISTRY,this);
    private ArrayList<Thread> threads = new ArrayList<>();
    private String threadString = "";
    private int threadSum;

    private AngularSensor obstacleSensor = new ObstacleSensor(this);
    private AngularSensor guySensor = new GuySensor(this);
    private AngularSensor stuckSensor = new StuckSensor(this);
    private AngularSensor closeSensor = new CloseGuySensor(this);
    private AngularSensor directionSensor = new DirectionSensor(this);

    private ArrayList<Entity> sensedEntities = new ArrayList<>();

    private Vector2 vectorSum = new Vector2(0,0);
    private Random random = new Random();
    private boolean successful = false;

    public Guy(Vector2 position, int radius) {
        super(position, radius, constants.DEFAULT_GUY_SPEED);
        threadOne.init(1,180,100);
        threadTwo.init(2,180,70);
        this.setColor();
        this.angle = random.nextInt(360);
        sensingRadius = constants.SENSING_RADIUS;
        this.direction.set(VectorBoss.randomVector());
        this.type = random.nextInt(3);
        for(int i = 0; i<constants.NUM_THREADS; i++) {
            threads.add(new Thread(constants.THREAD_REGISTRY,this));
        }
        for (Thread thread:threads) {
            thread.initRand();
            threadString+=thread.getThreadNum();
            threadString+="  ";
            threadSum += Integer.parseInt(thread.getThreadNum());
            constants.THREAD_REGISTRY.registerThread(thread);
        }
//        threads.add(threadOne);
        threadString = threadString.substring(0,threadString.length()-2);
        constants.THREAD_REGISTRY.registerThreadSum(threadSum);
    }

    public Guy(Vector2 position, int radius, ArrayList<Thread> threads) {
        super(position, radius, constants.DEFAULT_GUY_SPEED);
        this.setColor();
        this.angle = random.nextInt(360);
        sensingRadius = constants.SENSING_RADIUS;
        this.direction.set(VectorBoss.randomVector());
        for (Thread thread:threads) {
            this.threads.add(thread.duplicate(this));
            if (random.nextDouble()<constants.Mutation_Chance) {
                thread.mutate();
            }
            threadString+=thread.getThreadNum();
            threadString+="  ";
            threadSum += Integer.parseInt(thread.getThreadNum());
            constants.THREAD_REGISTRY.registerThread(thread);
        }
        threadString = threadString.substring(0,threadString.length()-2);
        constants.THREAD_REGISTRY.registerThreadSum(threadSum);
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
        this.color = ColourMaster.getColour(4);
        float divisionWidth = (float) constants.SCREENWIDTH /ColourMaster.getDivisions();
        this.color.set(Color.BLACK);
        Vector3 colorVector = new Vector3(0,0,0);
        for(Thread thread : threads) {
            colorVector.add(ColourMaster.getColourVector(thread.getSensorNum()).setLength2(thread.getWeighting()));
        }
        colorVector.setLength(1);
        this.color.set(colorVector.x,colorVector.y,colorVector.z,1);
//        for (int i = 0; i<ColourMaster.getDivisions(); i++) {
//            if (this.position.x<=divisionWidth*(i+1) & this.position.x>divisionWidth*i) {
//                this.color = ColourMaster.getColour(i);
//            }
//        }
//        this.color = ColourMaster.getColour(this.position.x,0,constants.SCREENWIDTH);
//        this.color = ColourMaster.getColour(this.threadSum,constants.threadRegistry.getMinThreadSum(),constants.threadRegistry.getMaxThreadSum());
//        float red = (float) (1-(0.2*(chunkNum*5 % 5))+0.4);
//        float blue = (float) (1-(0.3*(chunkNum*5 % 6))+0.5);
//        float green = (float) (1-(0.4*(chunkNum*5 % 7))+0.4);
//        this.color = new Color(red,green,blue,1);
//        if (chunks.size()>1) {
//            this.color.set(1,1,1,1);
//        }
//        this.color = new Color((float) (1-(0.1*(chunkNum+1))), (float) (0.02*(chunkNum+1)), (float) (0.01*(chunkNum+1)),1);
    }


    @Override
    public void think() {
        this.direction.add(VectorBoss.randomVector().setLength(20));
//        this.direction.add(standardDirection.setLength(15));
        sensedEntities.clear();
        for (Chunk chunk : chunks) {
            for (Entity entity : chunk.getEntities()) {
                if (this.position.dst(entity.getPosition())<this.sensingRadius && !sensedEntities.contains(entity)) {
                    sensedEntities.add(entity);
                }
            }
        }
        for (Thread thread : threads) {
            thread.load(sensedEntities);
            this.direction.add(thread.sense());
            if (thread.getSensor() instanceof CloseGuySensor) {
                if (thread.sense()!=Vector2.Zero) {
                    this.reAdjusting();
                } else {
                    this.unAdjust();
                }
            }
        }


//        threadOne.load(sensedEntities);
//        this.direction.add(threadOne.sense());
//        threadOne.printThreadDesc();
//        threadTwo.load(sensedEntities);
//        this.direction.add(threadTwo.sense());
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

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }
}
