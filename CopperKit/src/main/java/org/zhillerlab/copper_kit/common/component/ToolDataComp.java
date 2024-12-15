package org.zhillerlab.copper_kit.common.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

import java.util.function.UnaryOperator;

public class ToolDataComp {
  public static final DeferredRegister<DataComponentType<?>> TOOL_DATA_COMPONENT =
      DeferredRegister.createDataComponents(
          Registries.DATA_COMPONENT_TYPE,
          GlobalConfig.MOD_ID
      );
  
  public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> COPPER_KNIFE = register(
      "copper_knife",
      builder -> builder.persistent(Codec.STRING)
  );
  
  private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                         UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
    return TOOL_DATA_COMPONENT.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
  }
  
  public static void init(IEventBus eventBus) {
    TOOL_DATA_COMPONENT.register(eventBus);
  }
}
