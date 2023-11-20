package com.evolution.game.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Entity;

public class ObstacleParticle extends Entity {
    public ObstacleParticle(Vector2 position, int radius) {
        super(position, radius);
        this.color.set(Color.TEAL);
    }

    @Override
    public boolean cantCollide() {
        return true;
    }
}
