package org.zhillerlab.copper_kit.gen.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

public class BlockStateGen extends BlockStateProvider {
  public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
    super(output, GlobalConfig.MOD_ID, exFileHelper);
  }
  
  @Override
  protected void registerStatesAndModels() {
  
  }
}
