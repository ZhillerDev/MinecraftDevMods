package org.zhillerlab.copper_kit.entity.bomb;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class CopperFirecrackerEntity extends BasicBombEntity {
  public CopperFirecrackerEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
    super(entityType, level, BombDefs.COPPER_FIRECRACKER);
  }
  
  @Override
  protected void onHitEntity(@NotNull EntityHitResult result) {
    this.explode();
    this.level().broadcastEntityEvent(this, (byte) 3);
    this.discard();
  }
}
