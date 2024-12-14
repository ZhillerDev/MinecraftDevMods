package org.zhillerlab.copper_kit.entity.bomb;

public record BombParam(
    int bounceCount,
    float exploredRadius,
    int exploredDelay,
    float zAxis,
    float velocity,
    float inaccuracy,
    int cooldown
) {
}