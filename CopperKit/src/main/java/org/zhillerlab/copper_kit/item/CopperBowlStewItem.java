package org.zhillerlab.copper_kit.item;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CopperBowlStewItem extends Item {
  public CopperBowlStewItem(Properties properties) {
    super(properties.stacksTo(1));
  }
  
  @Override
  public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
    if (livingEntity instanceof Player) {
      if (!level.isClientSide && (getStewRecord(stack) != null)) {
        livingEntity.heal(4);
      }
    }
    return super.finishUsingItem(stack, level, livingEntity);
  }
  
  @Override
  public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    Boolean record = getStewRecord(stack);
    if (record) tooltipComponents.add(Component.literal("Cooked"));
    else tooltipComponents.add(Component.literal("UnCooked"));
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
  }
  
  public static Boolean getStewRecord(ItemStack stack) {
    AtomicReference<Boolean> result = new AtomicReference<>(false);
    DataComponentMap components = stack.getComponents();
    components.forEach(component -> {
      if (component.type().equals(DataComponents.CUSTOM_DATA)) {
        result.set(true);
      }
    });
    return result.get();
  }
}
