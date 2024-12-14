package org.zhillerlab.copper_kit.gen.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;

import java.util.concurrent.CompletableFuture;

public class DataMapGen extends DataMapProvider {
  protected DataMapGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
    super(packOutput, lookupProvider);
  }
  
  @Override
  protected void gather() {
  
  }
}
