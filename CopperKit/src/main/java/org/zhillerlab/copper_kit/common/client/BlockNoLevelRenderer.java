package org.zhillerlab.copper_kit.common.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.zhillerlab.copper_kit.block.chest.CopperChestBlock;
import org.zhillerlab.copper_kit.entity.chest.CopperChestEntity;
import org.zhillerlab.copper_kit.register.EntitiesReg;

public class BlockNoLevelRenderer extends BlockEntityWithoutLevelRenderer {
  
  public static final BlockNoLevelRenderer INSTANCE = new BlockNoLevelRenderer();
  
  public BlockNoLevelRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
    super(blockEntityRenderDispatcher, entityModelSet);
  }
  
  public BlockNoLevelRenderer() {
    super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
  }
  
  private CopperChestEntity cce = new CopperChestEntity(BlockPos.ZERO, EntitiesReg.copperChestList.getFirst().defaultBlockState());
  
  @Override
  public void onResourceManagerReload(ResourceManager resourceManager) {
  }
  
  @Override
  public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
    Block block = Block.byItem(stack.getItem());
    if (block instanceof CopperChestBlock) {
      Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(cce, poseStack, buffer, packedLight, packedOverlay);
    }
    super.renderByItem(stack, displayContext, poseStack, buffer, packedLight, packedOverlay);
  }
}
