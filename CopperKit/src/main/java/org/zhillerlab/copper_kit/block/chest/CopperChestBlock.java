package org.zhillerlab.copper_kit.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.zhillerlab.copper_kit.entity.chest.CopperChestEntity;
import org.zhillerlab.copper_kit.register.EntitiesReg;

public class CopperChestBlock extends ChestBlock {
  public CopperChestBlock() {
    super(
        Properties.of().strength(2.5F).sound(SoundType.COPPER),
        EntitiesReg.COPPER_CHEST_BLOCK_ENTITY::get
    );
  }
  
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new CopperChestEntity(pos, state);
  }
}
