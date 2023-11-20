package com.evolution.game.obstacles;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.constants;

public class RectObstacle extends Obstacle{
    private int height;
    private int width;


    private int area;
    private Vector2 topLeft;
    private Vector2 bottomRight;
    private int radius = constants.OBSTACLE_PARTICLE_RADIUS;
    public RectObstacle(int height, int width, Vector2 topLeft) {
        this.height = height;
        this.width = width;
        this.topLeft = topLeft;
        this.area = height*width;
        this.bottomRight = new Vector2(topLeft.x + width, topLeft.y+height);
        setBorderEntities();
    }
    @Override
    public void setBorderEntities() {
//        topLeft.x += radius;
//        topLeft.y += radius;
        System.out.println(this.width);
//        for (int i = 0; i < width; i+=radius*2) {
//            for (int j = 0; j < height; j+=radius*2) {
//                if (i<radius*6 || i>width - radius*6) {
//                    if (j<radius*6 || j>height - radius*6){
//                        borderEntities.add(new ObstacleParticle(new Vector2(topLeft.x+radius+i,topLeft.y+radius+j),radius));
//                    }
//                }
//            }
//        }
        drawHorizontalLine(3,width,topLeft);
        drawVerticalLine(3,height,topLeft);
        drawHorizontalLine(3,width,new Vector2(topLeft.x,bottomRight.y-radius));
        drawVerticalLine(3,height,new Vector2(bottomRight.x-radius,topLeft.y));
//        for (int i = 0; i < width; i += 1) {
//            borderEntities.add(new ObstacleParticle(new Vector2(topLeft.x+radius*i, topLeft.y),radius));
//            borderEntities.add(new ObstacleParticle(new Vector2(topLeft.x+radius*i, topLeft.y+height-radius),radius));
//        }
//
//        for (int j = 0; j < height; j += 1) {
//            borderEntities.add(new ObstacleParticle(new Vector2(topLeft.x, topLeft.y+radius*j),radius));
//            borderEntities.add(new ObstacleParticle(new Vector2(topLeft.x+width-radius, topLeft.y+radius*j),radius));
//        }
    }

    @Override
    public boolean contains(Vector2 position, int radius) {
        if (position.x+radius>topLeft.x && position.x-radius<bottomRight.x) {
            if (position.y+radius > topLeft.y && position.y -radius < bottomRight.y ) {
                return true;
            }
        }
        return false;
    }

    public int getArea() {
        return area;
    }

}
