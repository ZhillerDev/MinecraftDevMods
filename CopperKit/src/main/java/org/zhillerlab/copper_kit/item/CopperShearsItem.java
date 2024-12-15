package org.zhillerlab.copper_kit.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class CopperShearsItem extends ShearsItem {
  public CopperShearsItem(Properties properties) {
    super(properties
        .durability(196)
        .component(DataComponents.TOOL, createToolProperties())
        
    );
  }
  
  public static Tool createToolProperties() {
    return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.LEAVES, 15.0F), Tool.Rule.overrideSpeed(BlockTags.WOOL, 5.0F), Tool.Rule.overrideSpeed(List.of(Blocks.VINE, Blocks.GLOW_LICHEN), 2.0F)), 1.0F, 1);
  }
}
