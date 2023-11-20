package com.evolution.game.chunks;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Entity;

import java.util.ArrayList;

public class Chunk {
    private int number;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Chunk> neighbours = new ArrayList<>();
    private Vector2 upperLeft;
    private Vector2 lowerRight;

    public Chunk(int number, Vector2 upperLeft, Vector2 lowerRight) {
        this.number = number;
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
    }

    public Vector2 getUpperLeft() {
        return upperLeft;
    }

    public Vector2 getLowerRight() {
        return lowerRight;
    }

    public void addEntity(Entity entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
