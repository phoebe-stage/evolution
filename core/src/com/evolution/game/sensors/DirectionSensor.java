package com.evolution.game.sensors;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.*;

import java.util.ArrayList;

public class DirectionSensor extends AngularSensor{
    public DirectionSensor(Guy guy) {
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

    @Override
    public void calculate() {
        this.vectorSum.set(0,0);
        for (Entity entity : sensedEntities) {
            Vector2 entityPosition = new Vector2(entity.getPosition());
            if (entity instanceof Mover) {
                if (!((Mover) entity).isReadjusting()) {
                    vectorSum.add(((Mover) entity).getDirection());
                }
            }
        }
    }
}
