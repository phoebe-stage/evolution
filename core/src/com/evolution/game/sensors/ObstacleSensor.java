package com.evolution.game.sensors;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Entity;
import com.evolution.game.Guy;
import com.evolution.game.obstacles.ObstacleParticle;

import java.util.ArrayList;

public class ObstacleSensor extends AngularSensor {


    public ObstacleSensor(Vector2 position, Guy guy) {
        super(position, guy);
    }

    @Override
    public void filterAndClean() {
        ArrayList<Entity> toRemove = new ArrayList<>();
        for (Entity entity : sensedEntities) {
            if (!(entity instanceof ObstacleParticle)) {
                toRemove.add(entity);
            }
        }
        sensedEntities.removeAll(toRemove);
    }
}
