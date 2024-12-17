package org.zhillerlab.copper_kit.common.client;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.zhillerlab.copper_kit.block.chest.ChestTypes;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;
import org.zhillerlab.copper_kit.entity.chest.CopperChestEntity;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class BlockChestRenderer extends ChestRenderer<CopperChestEntity> {
  public static Material[] single = new Material[2];
  public static Material[] left = new Material[2];
  public static Material[] right = new Material[2];
  
  static {
    for (ChestTypes type : ChestTypes.VALUES) {
      single[type.ordinal()] = getChestMaterial(type.name().toLowerCase(Locale.ENGLISH));
      left[type.ordinal()] = getChestMaterial(type.name().toLowerCase(Locale.ENGLISH) + "_left");
      right[type.ordinal()] = getChestMaterial(type.name().toLowerCase(Locale.ENGLISH) + "_right");
    }
  }
  
  public BlockChestRenderer(BlockEntityRendererProvider.Context context) {
    super(context);
  }
  
  private static Material getChestMaterial(String path) {
    return new Material(Sheets.CHEST_SHEET, ResourceLocation.fromNamespaceAndPath(GlobalConfig.MOD_ID, "entity/chest/" + path));
  }
  
  @Override
  protected @NotNull Material getMaterial(@NotNull CopperChestEntity blockEntity, ChestType chestType) {
    return switch (chestType) {
      case LEFT -> left[0];
      case RIGHT -> right[0];
      default -> single[0];
    };
  }
}
