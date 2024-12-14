package org.zhillerlab.copper_kit.item.define;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ThrowableItem extends Item {
  public float getInaccuracy() {
    return inaccuracy;
  }
  
  public float getVelocity() {
    return velocity;
  }
  
  public float getZ() {
    return z;
  }
  
  public EntityType<?> getEntityType() {
    return entityTypeSupplier.get();
  }
  
  private final Supplier<EntityType<?>> entityTypeSupplier;
  private final int cooldown;
  private final float z, velocity, inaccuracy;
  
  /**
   * 构造函数用于初始化ThrowableItem对象
   *
   * @param properties         物品的属性，用于设置物品的特性
   * @param entityTypeSupplier 实体类型的供应商，用于生成实体对象
   * @param cooldown           使用物品后的冷却时间，防止连续使用过快
   * @param z                  投掷物品时的初始Z轴位置，影响物品的起点
   * @param velocity           物品投掷出的速度，决定了物品飞行的快慢
   * @param inaccuracy         投掷物品时的不准确性，增加了游戏的随机性和挑战性
   */
  public ThrowableItem(Properties properties, Supplier<EntityType<?>> entityTypeSupplier, int cooldown, float z, float velocity, float inaccuracy) {
    super(properties); // 调用父类构造函数初始化物品属性
    this.entityTypeSupplier = entityTypeSupplier; // 设置实体类型供应商
    this.cooldown = cooldown; // 设置冷却时间
    this.z = z; // 设置初始Z轴位置
    this.velocity = velocity; // 设置速度
    this.inaccuracy = inaccuracy; // 设置不准确性
  }
  
  @Override
  public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
    // 获取玩家手中使用的物品堆
    ItemStack itemstack = player.getItemInHand(usedHand);
    
    // 播放TNT点燃的声音效果
    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TNT_PRIMED, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
    
    // 为该物品添加冷却时间
    player.getCooldowns().addCooldown(this, cooldown);
    
    // 在服务器端且实体类型不为空时，创建并发射实体
    if (!level.isClientSide && getEntityType() != null) {
      // 创建实体并检查是否为ThrowableItemProjectile类型
      if (getEntityType().create(level) instanceof ThrowableItemProjectile projectile) {
        // 设置实体的初始位置、物品、所有者，并使其朝向玩家的视线方向射出
        projectile.setPosRaw(player.getX(), player.getEyeY() - (double) 0.1F, player.getZ());
        projectile.setItem(itemstack);
        projectile.setOwner(player);
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), getZ(), getVelocity(), getInaccuracy());
        
        // 将实体添加到世界中
        level.addFreshEntity(projectile);
      }
    }
    
    // 增加玩家使用该物品的统计
    player.awardStat(Stats.ITEM_USED.get(this));
    
    // 如果玩家没有创造模式下的无限物品能力，则减少物品堆的数量
    if (!player.getAbilities().instabuild) {
      itemstack.shrink(1);
    }
    
    // 返回表示物品使用成功的结果
    return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
  }
}
