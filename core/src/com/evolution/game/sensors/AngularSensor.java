package com.evolution.game.sensors;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Entity;
import com.evolution.game.Guy;
import com.evolution.game.VectorBoss;
import com.evolution.game.constants;


import java.util.ArrayList;

public abstract class AngularSensor {
    protected Guy guy;
    private double angle;
    private int strength;
    private Vector2 position;
    protected ArrayList<Entity> sensedEntities = new ArrayList<>();
    protected Vector2 vectorSum = new Vector2(0,0);

    public AngularSensor(Guy guy) {
        this.position= guy.getPosition();
        this.angle = 0;
        this.strength = 0;
        this.guy = guy;
        filterAndClean();
    }

    public int getAngle() {
        return (int) angle;
    }

    public int getStrength() {
        return strength;
    }

    public void takeInEntities(ArrayList<Entity> entities) {
        sensedEntities.clear();
        sensedEntities.addAll(entities);
        filterAndClean();
    }

    public abstract void filterAndClean();

    public void calculate() {
        this.vectorSum.set(0,0);
        for (Entity entity : sensedEntities) {
            Vector2 entityPosition = new Vector2(entity.getPosition());
            vectorSum.add(VectorBoss.vectorBetween(position,entityPosition).setLength(constants.SENSOR_CONFIG_NUM /position.dst2(entityPosition)));
        }
    }

    public Vector2 getVectorSum() {
        return vectorSum;
    }
}
