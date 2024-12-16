package org.zhillerlab.copper_kit.common.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

import java.util.function.UnaryOperator;

public class ItemDataComp {
  public static final DeferredRegister<DataComponentType<?>> ITEM_DATA_COMPONENT =
      DeferredRegister.createDataComponents(
          Registries.DATA_COMPONENT_TYPE,
          GlobalConfig.MOD_ID
      );
  
  // Basic codec
//  public static final Codec<StewRecord> STEW_RECORD_CODEC = RecordCodecBuilder.create(instance ->
//      instance.group(
//          Codec.STRING.fieldOf("name").forGetter(StewRecord::name),
//          Codec.INT.fieldOf("count").forGetter(StewRecord::count)
//      ).apply(instance, StewRecord::new)
//  );
  
  public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> COPPER_KNIFE = register(
      "copper_knife",
      builder -> builder.persistent(Codec.STRING)
  );
  
  // 食物tag
//  public static final DeferredHolder<DataComponentType<?>, DataComponentType<StewRecord>> STEW_COOKED = register(
//      "stew_cooked",
//      builder -> builder.persistent(STEW_RECORD_CODEC)
//  );
  
  private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                         UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
    return ITEM_DATA_COMPONENT.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
  }
  
  public static void init(IEventBus eventBus) {
    ITEM_DATA_COMPONENT.register(eventBus);
  }
}
