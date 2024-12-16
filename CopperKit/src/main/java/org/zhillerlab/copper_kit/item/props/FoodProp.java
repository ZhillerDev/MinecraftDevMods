package org.zhillerlab.copper_kit.item.props;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import org.zhillerlab.copper_kit.register.ItemsReg;

public class FoodProp {
  public static final FoodProperties COPPER_BOWL_MUSHROOM_STEW = new FoodProperties.Builder()
      .nutrition(6)
      .saturationModifier(0.6f)
      .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1), 0.3f)
      .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 120, 2), 0.3f)
      .effect(() -> new MobEffectInstance(MobEffects.UNLUCK, 200, 2), 0.7f)
      .usingConvertsTo(ItemsReg.COPPER_BOWL.get())
      .alwaysEdible()
      .build();
  public static final FoodProperties COPPER_BOWL_RABBIT_STEW = new FoodProperties.Builder()
      .nutrition(10)
      .saturationModifier(0.6f)
      .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1), 0.3f)
      .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 120, 2), 0.3f)
      .effect(() -> new MobEffectInstance(MobEffects.UNLUCK, 200, 2), 0.7f)
      .usingConvertsTo(ItemsReg.COPPER_BOWL.get())
      .alwaysEdible()
      .build();
}
