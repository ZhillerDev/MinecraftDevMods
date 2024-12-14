package org.zhillerlab.copper_kit.entity.bomb;

public class BombDefs {
  public static final BombParam DEFAULT_BOMB =
      new BombParam(10, 1.5f, 60, -10.0f, 2.0f, 1.0f, 10);
  public static final BombParam COPPER_FIRECRACKER =
      new BombParam(20, 2f, 30, -10.0f, 1.2f, 1.0f, 10);
  public static final BombParam COPPER_GRENADE =
      new BombParam(20, 4f, 60, -8.0f, .7f, 1.0f, 16);
}
