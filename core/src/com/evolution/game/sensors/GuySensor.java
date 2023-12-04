package com.evolution.game.sensors;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Entity;
import com.evolution.game.Guy;
import com.evolution.game.obstacles.ObstacleParticle;

import java.util.ArrayList;

public class GuySensor extends AngularSensor{
    public GuySensor(Guy guy) {
        super(guy);
    }

    @Override
    public void filterAndClean() {
        ArrayList<Entity> toRemove = new ArrayList<>();
        for (Entity entity : sensedEntities) {
            if ((!(entity instanceof Guy)) || entity==guy) {
                toRemove.add(entity);
            }
        }
        sensedEntities.removeAll(toRemove);
    }

}
