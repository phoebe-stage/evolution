package com.evolution.game;

public interface constants {
    int SCREENWIDTH = 800;
    int SCREENHEIGHT = 480;
    int GUY_RADIUS = 2;
    int NUMBER_GUYS = 1000;
    int NUM_HORIZONTAL_CHUNKS = 3;
    int NUM_VERTICAL_CHUNKS = 2;
    int overlap = 45;
    int DEFAULT_GUY_SPEED = 60;
    int OBSTACLE_PARTICLE_RADIUS = 1;
    int SENSING_RADIUS = 40;

    int SENSOR_CONFIG_NUM = 1;
    int READJUSTING_COOLDOWN = 500;
    ThreadRegistry THREAD_REGISTRY = new ThreadRegistry();
    int NUM_THREADS = 4;
    int STEPS_PER_GENERATION = 1000;
    int STEPS_SKIPS = 10;
    double Mutation_Chance = 0.1;
}
