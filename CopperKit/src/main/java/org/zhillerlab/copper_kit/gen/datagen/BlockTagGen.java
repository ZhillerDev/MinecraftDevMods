package org.zhillerlab.copper_kit.gen.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

import java.util.concurrent.CompletableFuture;

public class BlockTagGen extends BlockTagsProvider {
  public BlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, GlobalConfig.MOD_ID, existingFileHelper);
  }
  
  @Override
  protected void addTags(HolderLookup.Provider provider) {
  
  }
}
