package com.evolution.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public abstract class Mover extends Entity{
    protected float speed;

    protected double angle;

    public Mover(Vector2 position, int radius, float speed) {
        super(position, radius);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }


    public void moveRight() {
        this.position.x = getX() + this.speed * Gdx.graphics.getDeltaTime();
    }

    public void moveLeft() {
        this.position.x = this.getX() - this.speed * Gdx.graphics.getDeltaTime();
    }

    public void moveDown() {
        this.position.y = getY() + this.speed * Gdx.graphics.getDeltaTime();
    }

    public void moveUp() {
        this.position.y = getY() - this.speed * Gdx.graphics.getDeltaTime();
    }

    public void moveAtAngle(double angle) {
        angle = angle % 360;
        angle = angle * Math.PI/180;
        this.position.x = (float) (getX() + this.speed * Math.sin(angle) * Gdx.graphics.getDeltaTime());
        this.position.y = (float) (getY() - this.speed * Math.cos(angle) * Gdx.graphics.getDeltaTime());
    }

    @Override
    public void hit() {
        this.angle+=180;
    }

}
