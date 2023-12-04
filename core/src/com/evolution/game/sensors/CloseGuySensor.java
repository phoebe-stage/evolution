package com.evolution.game.sensors;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Entity;
import com.evolution.game.Guy;

import java.util.ArrayList;

public class CloseGuySensor extends AngularSensor{
    public CloseGuySensor(Guy guy) {
        super(guy);
    }

    @Override
    public void filterAndClean() {
        ArrayList<Entity> toRemove = new ArrayList<>();
        for (Entity entity : sensedEntities) {
            if ((!(entity instanceof Guy)) || entity==guy) {
                toRemove.add(entity);
            } else if (guy.getPosition().dst(entity.getPosition())>=10){
                toRemove.add(entity);
            }
        }
        sensedEntities.removeAll(toRemove);
    }




}
