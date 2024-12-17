package org.zhillerlab.copper_kit.common.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.zhillerlab.copper_kit.common.client.BlockChestRenderer;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;
import org.zhillerlab.copper_kit.item.props.ItemStatusProp;
import org.zhillerlab.copper_kit.register.EntitiesReg;

@EventBusSubscriber(modid = GlobalConfig.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvt {
  @SubscribeEvent
  public static void onClientSetup(FMLClientSetupEvent event) {
    GlobalConfig.LOGGER.info("HELLO FROM CLIENT SETUP");
    GlobalConfig.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    ItemStatusProp.init();
    BlockEntityRenderers.register(EntitiesReg.COPPER_CHEST_BLOCK_ENTITY.get(), BlockChestRenderer::new);
  }
}
