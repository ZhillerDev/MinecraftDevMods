package org.zhillerlab.copper_kit.register;

import net.neoforged.bus.api.IEventBus;
import org.zhillerlab.copper_kit.common.component.ItemDataComp;

public class MainRegistry {
  public static void init(IEventBus bus) {
    BlocksReg.init(bus);
    ItemsReg.init(bus);
    CreativeTablesReg.init(bus);
    EntitiesReg.init(bus);
    
    ItemDataComp.init(bus);
  }
}
