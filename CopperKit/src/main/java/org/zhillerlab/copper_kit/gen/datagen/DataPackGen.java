package org.zhillerlab.copper_kit.gen.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DataPackGen extends DatapackBuiltinEntriesProvider {
  
  public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();
  
  public DataPackGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    super(output, registries,BUILDER, Set.of(GlobalConfig.MOD_ID));
  }
}
