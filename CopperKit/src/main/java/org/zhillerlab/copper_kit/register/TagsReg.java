package org.zhillerlab.copper_kit.register;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

public class TagsReg {
  public static class Blocks {
    public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag("incorrect_for_copper_tool");
    
    private static TagKey<Block> createTag(String name) {
      return BlockTags.create(ResourceLocation.fromNamespaceAndPath(GlobalConfig.MOD_ID, name));
    }
  }
  
  public static class Items {
    public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
    
    private static TagKey<Item> createTag(String name) {
      return ItemTags.create(ResourceLocation.fromNamespaceAndPath(GlobalConfig.MOD_ID, name));
    }
  }
}
