package org.zhillerlab.copper_kit.block.fluid;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Vector3f;

public class CopperWaterFluidType extends FluidType {
  public final ResourceLocation stillTexture;
  public final ResourceLocation flowingTexture;
  public final ResourceLocation overlayTexture;
  public final ResourceLocation renderOverlayTexture;
  private final int color;
  private final Vector3f fogColor;
  
  
  public CopperWaterFluidType(Properties properties, ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlayTexture, ResourceLocation renderOverlayTexture, int color, Vector3f fogColor) {
    super(properties);
    this.stillTexture = stillTexture;
    this.flowingTexture = flowingTexture;
    this.overlayTexture = overlayTexture;
    this.renderOverlayTexture = renderOverlayTexture;
    this.color = color;
    this.fogColor = fogColor;
  }

}
