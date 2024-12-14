package org.zhillerlab.copper_kit.register;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

public class BlocksReg {
  public static final DeferredRegister.Blocks BLOCKS =
      DeferredRegister.createBlocks(GlobalConfig.MOD_ID);
  
  public static void init(IEventBus eventBus) {
    GlobalConfig.LOGGER.info("Registering Blocks for " + GlobalConfig.MOD_ID);
    BLOCKS.register(eventBus);
  }
}
