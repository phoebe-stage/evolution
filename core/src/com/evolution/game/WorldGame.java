package com.evolution.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.evolution.game.chunks.Chunk;
import com.evolution.game.chunks.ChunkBoss;
import com.evolution.game.objectPlacers.ObjectPlacer;
import com.evolution.game.objectPlacers.RandomAlmostSquareObjectPlacer;
import com.evolution.game.obstacles.ObstacleParticle;
import com.evolution.game.obstacles.RectObstacle;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WorldGame extends Game {
    public SpriteBatch batch;
//    public ArrayList<Guy> guys = new ArrayList<>();
    public ArrayList<Entity> entities = new ArrayList<>();
    public ArrayList<Entity> thinkers = new ArrayList<>();
    public ArrayList<RectObstacle> rects = new ArrayList<>();
    public BitmapFont font;

    public Texture texture;
    public ShapeDrawer shapeDrawer;

    public ObjectPlacer objectPlacer;
    public ChunkBoss chunkboss;
    Random random;

    public int numGuys;


    @Override
    public void create() {
        this.random = new Random();
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        numGuys = constants.NUMBER_GUYS;

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        this.chunkboss = new ChunkBoss(constants.SCREENWIDTH,constants.SCREENHEIGHT);
        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
        shapeDrawer=new ShapeDrawer(batch,region);
//        rects.add(new RectObstacle(200,200,new Vector2(30,30)));
//        rects.add(new RectObstacle(100,20,new Vector2(400,300)));
//        rects.add(new RectObstacle(20,400,new Vector2(60,60)));
//        rects.add(new RectObstacle(20,400,new Vector2(60,200)));
//        rects.add(new RectObstacle(20,400,new Vector2(400,160)));
//        rects.add(new RectObstacle(constants.SCREENHEIGHT,10, new Vector2(-11,0)));
//        rects.add(new RectObstacle(constants.SCREENHEIGHT,10, new Vector2(constants.SCREENWIDTH+10,0)));
//        rects.add(new RectObstacle(constants.SCREENHEIGHT,10, new Vector2(-11,0)));
//        rects.add(new RectObstacle(10,constants.SCREENWIDTH, new Vector2(0,-10)));
        for (RectObstacle rect : rects) {
            this.entities.addAll(rect.getBorderEntities());
        }
        objectPlacer = new RandomAlmostSquareObjectPlacer(constants.SCREENWIDTH,constants.SCREENHEIGHT,numGuys,constants.GUY_RADIUS,rects);
        for (int i = 0; i<numGuys;i++) {
            Guy guy = new Guy(objectPlacer.getNextCoords(),constants.GUY_RADIUS);
            this.addThinker(guy);
        }
        Guy guy = new Guy(new Vector2(20,400),constants.GUY_RADIUS);
//        ObstacleParticle op = new ObstacleParticle(new Vector2(360,160), 1);
////        ObstacleParticle op2 = new ObstacleParticle(new Vector2(440,400), 1);
////        addNonThinker(op2);
//        addNonThinker(op);
//        addThinker(guy);



        this.setScreen(new World(this));

    }



    public void render() {
        for (int i = 0; i<constants.stepsPerGeneration; i++) {
            for (Entity entity : entities) {
                chunkboss.registerFellow(entity);
                Vector2 currPosition = new Vector2(0,0);
                if (entity instanceof Mover) {
                    currPosition.add(entity.getPosition());
                    entity.think();
                    if (collisionCheck((Mover) entity)) {
                        entity.setX(currPosition.x);
                        entity.setY(currPosition.y);
                        entity.setSuccessfulThink(false);
                    } else {
                        entity.setSuccessfulThink(true);
                    }

                } else {
                    entity.think();
                }

                if (entity.getX() < entity.getRadius()) {
                    entity.setX(entity.getRadius());
                } else if (entity.getX() > constants.SCREENWIDTH - entity.getRadius()) {
                    entity.setX(constants.SCREENWIDTH - entity.getRadius());
                }
                if (entity.getY() < entity.getRadius()) {
                    entity.setY(entity.getRadius());
                } else if (entity.getY() > constants.SCREENHEIGHT - entity.getRadius()) {
                    entity.setY(constants.SCREENHEIGHT - entity.getRadius());
                }
            }
            super.render();

        }

    }

    public boolean collisionCheck(Mover entity) {
        for (Chunk chunk : entity.getChunks()) {
            for (Entity entity1 : chunk.getEntities()) {
                if (entity != entity1) {
                    if (entity.getPosition().dst(entity1.getPosition()) <= entity.getRadius()+ entity1.getRadius()) {
                        entity.hit();
                        return entity1.cantCollide();
                    }
                }
            }

        }
        return false;
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void addThinker(Entity entity) {
        this.thinkers.add(entity);
        this.entities.add(entity);
    }

    public void addNonThinker(Entity entity) {
        this.entities.add(entity);
    }

}
