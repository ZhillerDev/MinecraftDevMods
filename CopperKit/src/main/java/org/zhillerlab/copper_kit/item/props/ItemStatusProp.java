package org.zhillerlab.copper_kit.item.props;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.zhillerlab.copper_kit.register.ItemsReg;

public class ItemStatusProp {
  
  public static void init() {
    addCopperBowProps();
  }
  
  public static void addCopperBowProps() {
    Item item = ItemsReg.COPPER_BOW.get();
    ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), (p_344163_, p_344164_, p_344165_, p_344166_) -> {
      if (p_344165_ == null) {
        return 0.0F;
      } else {
        return p_344165_.getUseItem() != p_344163_ ? 0.0F : (float) (p_344163_.getUseDuration(p_344165_) - p_344165_.getUseItemRemainingTicks()) / 20.0F;
      }
    });
    ItemProperties.register(
        item,
        ResourceLocation.withDefaultNamespace("pulling"),
        (p_174630_, p_174631_, p_174632_, p_174633_) -> p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F
    );
  }
}
