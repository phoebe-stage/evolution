package com.evolution.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class Mover extends Entity{
    protected float speed;

    protected double angle;

    protected Vector2 previousPosition;
    protected float lastMoveDistance = 0;

    protected boolean readjusting = false;
    protected int adjustCooldown = 0;

    protected Vector2 direction = new Vector2(0,0);

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

    public void move() {
        this.previousPosition = this.position;
        this.direction.setLength(speed);
        this.position.x += direction.x * Gdx.graphics.getDeltaTime();
        this.position.y += direction.y * Gdx.graphics.getDeltaTime();
    }





    @Override
    public void hit() {
        this.angle+=180;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public boolean isReadjusting() {
        return readjusting;
    }

    public void reAdjusting() {
        adjustCooldown = constants.READJUSTING_COOLDOWN;
        this.readjusting = true;
    }

    public void unAdjust() {
        if (adjustCooldown!=0) {
            adjustCooldown --;
        } else {
            readjusting = false;
        }
    }
}
