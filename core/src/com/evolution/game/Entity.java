package com.evolution.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.evolution.game.chunks.Chunk;

import java.util.ArrayList;

public abstract  class Entity {
    protected Color color;
    protected Vector2 position;
    protected int radius;


    protected boolean successfulThink = true;


    public Entity (Vector2 position, int radius) {
        this.position = position;
        this.radius = radius;
        this.color = new Color(Color.BLACK);
    }
    protected ArrayList<Chunk> chunks = new ArrayList<>();

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }
    public int getRadius() {
        return radius;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Color getColor() {
        return color;
    }

    public void addChunk(Chunk chunk) {
        if (!chunks.contains(chunk)){
            chunks.add(chunk);
        }
    }

    public void removeChunk(Chunk chunk) {
        chunks.remove(chunk);
    }


    public void think() {
        return;
    }

    public boolean inChunk(Chunk chunk) {
        if (chunks.contains(chunk)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Chunk> getChunks() {
        return chunks;
    }

    public abstract boolean cantCollide();

    public void hit() {
        return;
    }

    public void setSuccessfulThink(boolean successfulThink) {
        this.successfulThink = successfulThink;
    }

    public boolean isSuccessfulThink() {
        return successfulThink;
    }


}
