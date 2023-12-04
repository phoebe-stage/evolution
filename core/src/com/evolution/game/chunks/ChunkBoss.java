package com.evolution.game.chunks;

import com.badlogic.gdx.math.Vector2;
import com.evolution.game.Entity;
import com.evolution.game.constants;

import java.util.ArrayList;
import java.util.HashMap;

public class ChunkBoss implements constants {

    private float chunkWidth;
    private float chunkHeight;

    private int numHorizontal = constants.NUM_HORIZONTAL_CHUNKS;
    private int numVertical = constants.NUM_VERTICAL_CHUNKS;

    private float overlap =constants.overlap;


    private ArrayList<Chunk> chunks = new ArrayList<>();

    private  HashMap<Integer, Chunk> chunksMap = new HashMap<>();

    public ChunkBoss(int maxX, int maxY){
        chunkWidth = (float) maxX /numHorizontal;
        chunkHeight = (float) maxY/numVertical;
        generateChunks();
    }

    public void generateChunks() {
        int chunkCount =0;
        for (int i= 0; i < numHorizontal; i++) {
            for (int j= 0; j < numVertical; j++) {
                chunks.add(new Chunk(chunkCount, new Vector2(chunkWidth*i-overlap, chunkHeight*j-overlap), new Vector2(chunkWidth*(i+1)+overlap, chunkHeight*(j+1)+overlap)));
                chunkCount ++;
            }
        }
    }

    public void registerFellows(ArrayList<Entity> fellows) {
        for (Entity fellow : fellows) {
            registerFellow(fellow);
        }
    }
    public void registerFellow(Entity fellow) {
        for (Chunk chunk : chunks) {
            if (checkInChunk(chunk,fellow.getPosition())) {
                fellow.addChunk(chunk);
                chunk.addEntity(fellow);
            } else {
                fellow.removeChunk(chunk);
                chunk.removeEntity(fellow);
            }
        }
    }

    public boolean checkInChunk(Chunk chunk, Vector2 position) {
        if (chunk.getUpperLeft().x <= position.x & chunk.getLowerRight().x > position.x) {
            if (chunk.getUpperLeft().y <= position.y & chunk.getLowerRight().y > position.y) {
                return true;
            }
        }
        return false;
    }


}
