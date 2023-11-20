package com.evolution.game.obstacles;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.constants;

import java.util.ArrayList;

public abstract class Obstacle {
    protected ArrayList<ObstacleParticle> borderEntities = new ArrayList<>();
    public abstract void setBorderEntities();
    public ArrayList<ObstacleParticle> getBorderEntities() {
        return this.borderEntities;
    }

    public void drawHorizontalLine(int thickness, int length, Vector2 topLeftt) {
        int radius = constants.OBSTACLE_PARTICLE_RADIUS;
        for (int i = 0; i <= length; i += 1) {
            for (int j = 0; j<thickness; j++) {
                borderEntities.add(new ObstacleParticle(new Vector2(topLeftt.x+radius*i, topLeftt.y+radius*j),radius));
            }
        }
    }

    public void drawVerticalLine(int thickness, int length, Vector2 topLeftt) {
        int radius = constants.OBSTACLE_PARTICLE_RADIUS;
        for (int i = 0; i <= length; i += 1) {
            for (int j = 0; j<thickness; j++) {
                borderEntities.add(new ObstacleParticle(new Vector2(topLeftt.x+radius*j, topLeftt.y+radius*i),radius));
            }
        }
    }





    public abstract boolean contains(Vector2 position, int radius);
}
