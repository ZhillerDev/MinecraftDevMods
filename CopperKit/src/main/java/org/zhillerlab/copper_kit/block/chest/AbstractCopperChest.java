package org.zhillerlab.copper_kit.block.chest;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCopperChest extends BaseEntityBlock implements SimpleWaterloggedBlock {
  protected AbstractCopperChest(Properties properties) {
    super(properties);
  }
  
  @Override
  protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
    return null;
  }
  
  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return null;
  }
}
