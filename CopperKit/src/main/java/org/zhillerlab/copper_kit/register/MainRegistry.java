package org.zhillerlab.copper_kit.register;

import net.neoforged.bus.api.IEventBus;

public class MainRegistry {
  public static void init(IEventBus bus) {
    BlocksReg.init(bus);
    ItemsReg.init(bus);
    CreativeTablesReg.init(bus);
    EntitiesReg.init(bus);
  }
}
