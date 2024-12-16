package org.zhillerlab.copper_kit;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;
import org.zhillerlab.copper_kit.register.MainRegistry;

@Mod(GlobalConfig.MOD_ID)
public class CooperKitMod {
  public CooperKitMod(IEventBus modEventBus, ModContainer modContainer) {
    modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    NeoForge.EVENT_BUS.register(this);
    
    MainRegistry.init(modEventBus);
  }
  
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    GlobalConfig.LOGGER.info("HELLO from server starting");
  }
}
