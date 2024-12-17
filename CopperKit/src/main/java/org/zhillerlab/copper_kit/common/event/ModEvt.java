package org.zhillerlab.copper_kit.common.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

@EventBusSubscriber(modid = GlobalConfig.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvt {
  @SubscribeEvent
  public static void modifyComponents(ModifyDefaultComponentsEvent event) {
    // Sets the component on melon seeds
//    event.modify(ItemsReg.COPPER_BOWL_RABBIT_STEW, builder ->
//        builder.set(ItemDataComp.STEW_COOKED.get(), new StewRecord("cooked", 0))
//    );
  }
}
