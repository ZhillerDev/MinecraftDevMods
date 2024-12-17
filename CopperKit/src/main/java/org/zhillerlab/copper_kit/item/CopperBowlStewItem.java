package org.zhillerlab.copper_kit.item;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CopperBowlStewItem extends Item {
  public CopperBowlStewItem(Properties properties) {
    super(properties.stacksTo(1));
  }
  
  @Override
  public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
    if (livingEntity instanceof Player) {
      if (!level.isClientSide && (getStewRecord(stack, null) != null)) {
        livingEntity.heal(4);
      }
    }
    return super.finishUsingItem(stack, level, livingEntity);
  }
  
  @Override
  public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    Boolean record = getStewRecord(stack, null);
    if (record) tooltipComponents.add(Component.literal("Cooked"));
    else tooltipComponents.add(Component.literal("UnCooked"));
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
  }
  
  public static Boolean getStewRecord(ItemStack stack, @Nullable List<Component> tooltipComponents) {
    boolean result = false;
    DataComponentMap components = stack.getComponents();
    for (TypedDataComponent<?> component : components) {
      if (component.type().equals(DataComponents.CUSTOM_DATA)) {
        result = true;
        if (tooltipComponents != null) {
          tooltipComponents.add(Component.literal(component.value().toString()));
        }
        break;
      }
    }
    return result;
  }
}
