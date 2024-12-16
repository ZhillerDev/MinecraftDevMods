package org.zhillerlab.copper_kit.common.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;
import org.zhillerlab.copper_kit.register.ItemsReg;

@EventBusSubscriber(modid = GlobalConfig.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class GlobalClientEvt {
  @SubscribeEvent
  public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
    if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ItemsReg.COPPER_BOW.get()) {
      float fovModifier = 1f;
      int ticksUsingItem = event.getPlayer().getTicksUsingItem();
      float deltaTicks = (float) ticksUsingItem / 20f;
      if (deltaTicks > 1f) {
        deltaTicks = 1f;
      } else {
        deltaTicks *= deltaTicks;
      }
      fovModifier *= 1f - deltaTicks * 0.15f;
      event.setNewFovModifier(fovModifier);
    }
  }
}
