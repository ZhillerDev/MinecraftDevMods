package org.zhillerlab.copper_kit.entity.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.zhillerlab.copper_kit.register.EntitiesReg;

public class CopperChestEntity extends ChestBlockEntity {
  
  
  protected CopperChestEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
    super(EntitiesReg.COPPER_CHEST_BLOCK_ENTITY.get(), pos, blockState);
  }
  
  public CopperChestEntity(BlockPos blockPos, BlockState blockState) {
    super(blockPos, blockState);
  }
}
