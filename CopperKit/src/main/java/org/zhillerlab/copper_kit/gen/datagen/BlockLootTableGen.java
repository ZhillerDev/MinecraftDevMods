package org.zhillerlab.copper_kit.gen.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import java.util.Set;

public class BlockLootTableGen extends BlockLootSubProvider {
  protected BlockLootTableGen(HolderLookup.Provider registries) {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
  }
  
  @Override
  protected void generate() {
  
  }
}
