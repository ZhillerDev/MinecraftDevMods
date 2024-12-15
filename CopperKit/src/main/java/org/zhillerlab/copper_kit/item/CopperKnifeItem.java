package org.zhillerlab.copper_kit.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.zhillerlab.copper_kit.common.component.ToolDataComp;

import java.util.List;
import java.util.Objects;

public class CopperKnifeItem extends SwordItem {
  public CopperKnifeItem(Tier tier, Properties properties) {
    super(tier, properties.durability(162));
  }
  
  @Override
  public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    if (stack.get(ToolDataComp.COPPER_KNIFE) != null) {
      tooltipComponents.add(Component.literal(Objects.requireNonNull(stack.get(ToolDataComp.COPPER_KNIFE))));
    }
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
  }
  
  @Override
  public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
    ItemStack itemstack = player.getItemInHand(usedHand);
    if (!level.isClientSide) {
      itemstack.set(
          ToolDataComp.COPPER_KNIFE,
          "Ready!!!"
      );
      player.resetCurrentImpulseContext();
      player.getCooldowns().addCooldown(this, 40);
    }
    return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide);
  }

//  @Override
//  public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
//    ItemStack itemstack = super.finishUsingItem(stack, level, entityLiving);
//    itemstack.set(
//        ToolDataComp.COPPER_KNIFE,
//        "Ready!!!"
//    );
//    if (entityLiving instanceof Player player) {
//      player.resetCurrentImpulseContext();
//      player.getCooldowns().addCooldown(this, 40);
//    }
//    return stack;
//  }
  
  private int normalize(float value) {
    if (value > 100) {
      return -1;
    } else if (value >= 0) {
      // Calculate the normalized value
      int normalized = (int) ((value / 100f) * 15 + 5);
      // Ensure the result is within the range [5, 20]
      return Math.max(5, Math.min(20, normalized));
    } else {
      // If the value is less than 0, you might want to handle it differently
      // For now, it returns 5 as a default value
      return 5;
    }
  }
  
  @Override
  public void postHurtEnemy(@NotNull ItemStack stack, LivingEntity target, LivingEntity attacker) {
    if (attacker instanceof Player player) {
      if (stack.get(ToolDataComp.COPPER_KNIFE) != null) {
        float health = target.getHealth();
        if (health <= (target.getMaxHealth() * 0.8)) {
          target.hurt(
              attacker.damageSources().magic(),
              (float) (target.getMaxHealth())
          );
        }
        stack.remove(ToolDataComp.COPPER_KNIFE);
        int normalize = normalize(health);
        if (normalize != -1) {
          stack.hurtAndBreak(normalize, attacker, EquipmentSlot.MAINHAND);
        } else {
          stack.hurtAndBreak(stack.getMaxDamage() / 2, attacker, EquipmentSlot.MAINHAND);
        }
      } else {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
      }
    }
    
  }
}
