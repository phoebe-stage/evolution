package com.evolution.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.evolution.game.chunks.Chunk;

import java.util.Random;

public class Guy extends Mover{
    private int angle;
    private Color color;

    private int sensingRadius;


    private Random random = new Random();

    public Guy(Vector2 position, int radius) {
        super(position, radius, constants.DEFAULT_GUY_SPEED);
        this.setColor();
        this.angle = random.nextInt(360);
        sensingRadius = 20;
    }

    public Color getColor() {
        this.setColor();
        return this.color;
    }


    public void setColor() {
        int chunkNum = 0;

        for (Chunk chunk : chunks) {
            chunkNum += chunk.getNumber();
        }
        if (chunkNum != 0) {
            chunkNum = chunkNum/chunks.size();
        }

        chunkNum ++;


        float red = (float) ((0.1*(chunkNum*4 % 5))+0.1);
        float blue = (float) ((0.1*(chunkNum*4 % 6))+0.1);
        float green = (float) ((0.1*(chunkNum*4 % 7))+0.1);
        this.color = new Color(red,green,blue,1);
        if (chunks.size()>1) {
            this.color.set(1,1,1,1);
        }
//        this.color = new Color((float) (1-(0.1*(chunkNum+1))), (float) (0.02*(chunkNum+1)), (float) (0.01*(chunkNum+1)),1);
    }


    @Override
    public void think() {
//        System.out.println(this.chunks);
//        int direction = random.nextInt(4);
//        if (direction == 1) {
//            this.moveDown();
//        } else if (direction == 2) {
//            this.moveRight();
//        }
//        else if (direction == 3) {
//            this.moveLeft();
//        } else if (direction == 0) {
//            this.moveUp();
//        }

//        int direction = random.nextInt(8);
//        if (direction == 1) {
//            this.moveDown();
//        } else if (direction == 2) {
//            this.moveUp();
//        }
//        else if (direction == 3) {
//            this.moveLeft();
//        } else {
//            this.moveRight();
//        }

        int direction = random.nextInt(8);
        if (direction == 1) {
            angle+=10;
        } else if (direction == 2) {
            angle-=10;
        }

        for (Chunk chunk : chunks) {
            for (Entity entity : chunk.getEntities()) {
                System.out.println("trst");
            }
        }
        moveAtAngle(angle);
    }

    @Override
    public boolean cantCollide() {
        return true;
    }


}
