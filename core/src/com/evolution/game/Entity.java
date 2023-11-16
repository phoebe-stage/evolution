package com.evolution.game;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract  class Entity {

    protected float x;
    protected float y;
    protected int radius;


    protected ArrayList<Chunk> chunks = new ArrayList<>();

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2 getPosition() {
        return new Vector2(x,y);
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

    public int getRadius() {
        return radius;
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

}
