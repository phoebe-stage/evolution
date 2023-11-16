package com.evolution.game;

import com.badlogic.gdx.Gdx;

public abstract class Mover extends Entity{
    protected float speed;

    protected double angle;

    public float getSpeed() {
        return speed;
    }


    public void moveRight() {
        x = this.x + this.speed * Gdx.graphics.getDeltaTime();
    }

    public void moveLeft() {
        x = this.x - this.speed * Gdx.graphics.getDeltaTime();
    }

    public void moveDown() {
        y = this.y + this.speed * Gdx.graphics.getDeltaTime();
    }

    public void moveUp() {
        y = this.y - this.speed * Gdx.graphics.getDeltaTime();
    }

    public void moveAtAngle(double angle) {
        angle = angle % 360;
        angle = angle * Math.PI/180;
        x = (float) (this.x + this.speed * Math.sin(angle) * Gdx.graphics.getDeltaTime());
        y = (float) (this.y - this.speed * Math.cos(angle) * Gdx.graphics.getDeltaTime());
    }

    @Override
    public void hit() {
        this.angle+=180;
    }
}
