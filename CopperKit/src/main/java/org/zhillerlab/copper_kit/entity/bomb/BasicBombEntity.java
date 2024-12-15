package org.zhillerlab.copper_kit.entity.bomb;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.zhillerlab.copper_kit.register.ItemsReg;

public class BasicBombEntity extends ThrowableItemProjectile {
  private BombParam param = BombDefs.DEFAULT_BOMB;
  private int bounceCount = BombDefs.DEFAULT_BOMB.bounceCount();
  private float exploredRadius = param.exploredRadius();
  private int exploredDelay = param.exploredDelay();
  
  public static final ParticleOptions FIRECRACKER_PARTICLE =
      (ParticleOptions) ParticleTypes.SMOKE;
  
  public BasicBombEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level, BombParam param) {
    super(entityType, level);
    if (param != null) {
      this.param = param;
      this.exploredRadius = param.exploredRadius();
      this.exploredDelay = param.exploredDelay();
    }
  }
  
  @Override
  protected @NotNull Item getDefaultItem() {
    return ItemsReg.COPPER_FIRECRACKER.get();
  }
  
  @Override
  public void handleEntityEvent(byte id) {
    if (id == 3) {
      for (int i = 0; i < 8; ++i) {
        this.level().addParticle(FIRECRACKER_PARTICLE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
      }
    }
  }
  
  @Override
  public void tick() {
    super.tick();
    if (this.level().isClientSide) {
      this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
    }
    
    if (tickCount >= exploredDelay) {
      if (!this.level().isClientSide) {
        this.explode();
        this.level().broadcastEntityEvent(this, (byte) 3);
        this.discard();
      }
    }
  }
  
  @Override
  protected void onHit(@NotNull HitResult result) {
    if (bounceCount >= 20) {
      this.setNoGravity(true);
      this.setDeltaMovement(new Vec3(0, 0.0001F, 0));
    } else {
      this.bounceCount++;
      super.onHit(result);
    }
  }
  
  @Override
  protected void onHitEntity(@NotNull EntityHitResult result) {
    double velX = this.getDeltaMovement().x * (bounceCount < 6 ? (1.0F - Math.min(1.0F, 0.1F * bounceCount)) : 0.7F);
    double velY = this.getDeltaMovement().y * (bounceCount < 6 ? (0.625F / bounceCount) : 0F);
    double velZ = this.getDeltaMovement().z * (bounceCount < 6 ? (1.0F - Math.min(1.0F, 0.1F * bounceCount)) : 0.7F);
    double absVelX = Math.abs(velX);
    double absVelY = Math.abs(velY);
    double absVelZ = Math.abs(velZ);
    
    if (absVelX >= absVelY && absVelX >= absVelZ)
      this.setDeltaMovement(-velX, velY, velZ);
    if (absVelY >= absVelX && absVelY >= absVelZ)
      this.setDeltaMovement(velX, -velY, velZ);
    if (absVelZ >= absVelY && absVelZ >= absVelX)
      this.setDeltaMovement(velX, velY, -velZ);
  }
  
  @Override
  protected void onHitBlock(@NotNull BlockHitResult hitBlock) {
    double velX = this.getDeltaMovement().x * (bounceCount < 6 ? (1.0F - Math.min(1.0F, 0.1F * bounceCount)) : 0.7F);
    double velY = this.getDeltaMovement().y * (bounceCount < 6 ? (0.625F / bounceCount) : 0F);
    double velZ = this.getDeltaMovement().z * (bounceCount < 6 ? (1.0F - Math.min(1.0F, 0.1F * bounceCount)) : 0.7F);
    
    Direction direction = hitBlock.getDirection();
    BlockPos blockPos = hitBlock.getBlockPos();
    BlockState blockstate = this.level().getBlockState(blockPos);
    
    if (blockstate.blocksMotion()) {
      if (!level().isClientSide && bounceCount < 6) {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.METAL_HIT, SoundSource.NEUTRAL, 1.0F, 4.0F);
      }
      
      if (direction == Direction.EAST || direction == Direction.WEST)
        this.setDeltaMovement(-velX, velY, velZ);
      if (direction == Direction.DOWN || direction == Direction.UP)
        this.setDeltaMovement(velX, -velY, velZ);
      if (direction == Direction.NORTH || direction == Direction.SOUTH)
        this.setDeltaMovement(velX, velY, -velZ);
    }
  }
  
  /**
   * 根据射击者的旋转角度发射射弹
   * 此方法计算射弹的初始速度方向，考虑射击者的旋转角度，以确保射弹沿正确的轨迹飞行
   *
   * @param shooter    发射射弹的实体，不能为空
   * @param x          俯仰角度
   * @param y          水平旋转角度
   * @param z          未知（在此上下文中未使用）
   * @param velocity   射弹的速度
   * @param inaccuracy 射弹的不准确性，用于模拟射击的随机偏差
   */
  @Override
  public void shootFromRotation(@NotNull Entity shooter, float x, float y, float z, float velocity, float inaccuracy) {
    // 计算射弹的初始速度方向
    float f = -Mth.sin(y * ((float) Math.PI / 180F)) * Mth.cos(x * ((float) Math.PI / 180F));
    float f1 = -Mth.sin((x + z) * ((float) Math.PI / 180F));
    float f2 = Mth.cos(y * ((float) Math.PI / 180F)) * Mth.cos(x * ((float) Math.PI / 180F));
    
    // 调用基类的shoot方法来发射射弹
    this.shoot((double) f, (double) f1, (double) f2, velocity, inaccuracy);
    
    // 获取射击者当前的运动增量（速度）
    Vec3 vec3 = shooter.getDeltaMovement();
    
    // 根据射击者的运动增量调整射弹的运动增量
    // 如果射击者在地面上，则不增加垂直速度，以模拟地面射击的稳定性
    this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, shooter.onGround() ? 0.0D : vec3.y, vec3.z));
  }
  
  /**
   * 引爆当前对象，创建一个爆炸效果
   * <p>
   * 此方法调用游戏等级（level）的explode方法，以当前对象的位置为中心，引发一个爆炸
   * 爆炸的强度由exploredRadius变量决定，适用于TNT爆炸的场景
   */
  public void explode() {
    // 调用游戏等级的explode方法，参数包括：爆炸的源头对象、爆炸位置的x、y、z坐标、爆炸半径和爆炸类型
    this.level().explode(this, this.getX(), this.getY(0.0625D) + 0.5F, this.getZ(), exploredRadius, Level.ExplosionInteraction.TNT);
  }
}
