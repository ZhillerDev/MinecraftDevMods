package org.zhillerlab.copper_kit.entity.bomb;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;

public class CopperGrenadeEntity extends BasicBombItem {
  public CopperGrenadeEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
    super(entityType, level, BombDefs.COPPER_GRENADE);
  }
}
