package org.zhillerlab.copper_kit.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

import java.util.function.Supplier;

public class CreativeTablesReg {
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GlobalConfig.MOD_ID);
  
  private static void addToCreativeTable(CreativeModeTab.Output output) {
    output.accept(ItemsReg.COPPER_NUGGET);
    output.accept(ItemsReg.COPPER_FRAGMENT);
    
    output.accept(ItemsReg.COPPER_FIRECRACKER);
    output.accept(ItemsReg.COPPER_GRENADE);
    
    output.accept(ItemsReg.COPPER_SHEARS);
    output.accept(ItemsReg.COPPER_SWORD);
    output.accept(ItemsReg.COPPER_PICKAXE);
    output.accept(ItemsReg.COPPER_AXE);
    output.accept(ItemsReg.COPPER_SHOVEL);
    output.accept(ItemsReg.COPPER_HOE);
    
    output.accept(ItemsReg.COPPER_HELMET);
    output.accept(ItemsReg.COPPER_CHESTPLATE);
    output.accept(ItemsReg.COPPER_LEGGINGS);
    output.accept(ItemsReg.COPPER_BOOTS);
    
  }
  
  public static final Supplier<CreativeModeTab> EXAMPLE_MOD_TAB = CREATIVE_MODE_TAB.register(
      GlobalConfig.MOD_ID + "_creative_table",
      () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemsReg.COPPER_FRAGMENT.get()))
          .title(Component.translatable("creativetab.copper_kit.copper_kit_creative_table"))
          .displayItems((itemDisplayParameters, output) -> {
            addToCreativeTable(output);
          })
          .build());
  
  public static void init(IEventBus eventBus) {
    GlobalConfig.LOGGER.info("Registering Creative Tables for " + GlobalConfig.MOD_ID);
    CREATIVE_MODE_TAB.register(eventBus);
  }
}
