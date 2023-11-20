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
import com.evolution.game.obstacles.RectObstacle;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
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
        rects.add(new RectObstacle(200,200,new Vector2(30,30)));
        for (RectObstacle rect : rects) {
            this.entities.addAll(rect.getBorderEntities());
        }
        objectPlacer = new RandomAlmostSquareObjectPlacer(constants.SCREENWIDTH,constants.SCREENHEIGHT,numGuys,constants.GUY_RADIUS,rects);
        for (int i = 0; i<numGuys;i++) {
            Guy guy = new Guy(objectPlacer.getNextCoords(),constants.GUY_RADIUS);
            this.addThinker(guy);
        }

        this.setScreen(new World(this));

    }



    public void render() {
        for (Entity entity : entities) {
            chunkboss.registerFellow(entity);
            Vector2 currPosition = new Vector2(0,0);
            if (entity instanceof Mover) {
                currPosition.add(entity.getPosition());
                entity.think();
                if (collisionCheck((Mover) entity)) {
                    entity.setX(currPosition.x);
                    entity.setY(currPosition.y);
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
