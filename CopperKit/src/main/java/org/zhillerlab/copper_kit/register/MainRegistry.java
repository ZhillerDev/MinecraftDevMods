package org.zhillerlab.copper_kit.register;

import net.neoforged.bus.api.IEventBus;
import org.zhillerlab.copper_kit.common.component.ToolDataComp;

public class MainRegistry {
  public static void init(IEventBus bus) {
    BlocksReg.init(bus);
    ItemsReg.init(bus);
    CreativeTablesReg.init(bus);
    EntitiesReg.init(bus);
    
    ToolDataComp.init(bus);
  }
}
