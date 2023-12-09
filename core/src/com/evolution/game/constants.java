package com.evolution.game;

public interface constants {
    int SCREENWIDTH = 800;
    int SCREENHEIGHT = 480;
    int GUY_RADIUS = 2;
    int NUMBER_GUYS = 1000;
    int NUM_HORIZONTAL_CHUNKS = 3;
    int NUM_VERTICAL_CHUNKS = 2;
    int overlap = 45;
    int DEFAULT_GUY_SPEED = 40;
    int OBSTACLE_PARTICLE_RADIUS = 1;
    int SENSING_RADIUS = 40;

    int SENSORCONFIGNUM = 1;
    int READJUSTINGCOOLDOWN = 500;
    ThreadRegistry threadRegistry = new ThreadRegistry();
    int numThreads = 4;
}
