package org.zhillerlab.copper_kit.gen.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;
import org.zhillerlab.copper_kit.register.ItemsReg;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends ItemTagsProvider {
  public ItemTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, blockTags, GlobalConfig.MOD_ID, existingFileHelper);
  }
  
  @Override
  protected void addTags(HolderLookup.Provider provider) {
    tag(ItemTags.SWORDS)
        .add(ItemsReg.COPPER_SWORD.get())
        .add(ItemsReg.COPPER_KNIFE.get());
    tag(ItemTags.PICKAXES)
        .add(ItemsReg.COPPER_PICKAXE.get());
    tag(ItemTags.AXES)
        .add(ItemsReg.COPPER_AXE.get());
    tag(ItemTags.SHOVELS)
        .add(ItemsReg.COPPER_SHOVEL.get());
    tag(ItemTags.HOES)
        .add(ItemsReg.COPPER_HOE.get());
    
    tag(ItemTags.TRIMMABLE_ARMOR)
        .add(ItemsReg.COPPER_HELMET.get())
        .add(ItemsReg.COPPER_CHESTPLATE.get())
        .add(ItemsReg.COPPER_LEGGINGS.get())
        .add(ItemsReg.COPPER_BOOTS.get());
    
  }
}
