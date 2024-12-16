package org.zhillerlab.copper_kit.gen.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import org.zhillerlab.copper_kit.register.ItemsReg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGen extends RecipeProvider implements IConditionBuilder {
  public RecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    super(output, registries);
  }
  
  @Override
  protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
    
    // 原材料
    buildIngotAndNuggetRecipes(Items.COPPER_INGOT, ItemsReg.COPPER_NUGGET.get(), "has_copper", recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemsReg.COPPER_SHEET.get(), 2)
        .pattern("AA")
        .pattern("AA")
        .define('A', Items.COPPER_INGOT)
        .unlockedBy("has_copper", has(Items.COPPER_INGOT)).save(recipeOutput);
    
    // 食物
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemsReg.COPPER_BOWL.get())
        .pattern("A A")
        .pattern(" A ")
        .define('A', ItemsReg.COPPER_SHEET)
        .unlockedBy("has_copper_sheet", has(ItemsReg.COPPER_SHEET)).save(recipeOutput);
    ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ItemsReg.COPPER_BOWL_MUSHROOM_STEW.get())
        .requires(Items.BROWN_MUSHROOM)
        .requires(Items.RED_MUSHROOM)
        .requires(ItemsReg.COPPER_BOWL)
        .unlockedBy("has_copper_bowl", has(ItemsReg.COPPER_BOWL)).save(recipeOutput);
    ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ItemsReg.COPPER_BOWL_RABBIT_STEW.get())
        .requires(Items.RABBIT)
        .requires(Items.CARROT)
        .requires(Items.BAKED_POTATO)
        .requires(Items.RED_MUSHROOM)
        .requires(ItemsReg.COPPER_BOWL)
        .unlockedBy("has_copper_bowl", has(ItemsReg.COPPER_BOWL)).save(recipeOutput);
    
    
    // 全套工具武器
    ArrayList<Item> copperTools = new ArrayList<>() {{
      add(ItemsReg.COPPER_SWORD.get());
      add(ItemsReg.COPPER_PICKAXE.get());
      add(ItemsReg.COPPER_AXE.get());
      add(ItemsReg.COPPER_SHOVEL.get());
      add(ItemsReg.COPPER_HOE.get());
    }};
    buildToolsRecipes(copperTools, Items.COPPER_INGOT, "has_copper", recipeOutput);
    // 铜匕首
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemsReg.COPPER_KNIFE.get())
        .pattern("A")
        .pattern("B")
        .define('A', Items.COPPER_INGOT)
        .define('B', Items.STICK)
        .unlockedBy("has_copper", has(Items.COPPER_INGOT)).save(recipeOutput);
    // 铜弓
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemsReg.COPPER_BOW.get())
        .pattern(" AB")
        .pattern("A B")
        .pattern(" AB")
        .define('A', Items.COPPER_INGOT)
        .define('B', Items.STRING)
        .unlockedBy("has_copper", has(Items.COPPER_INGOT)).save(recipeOutput);
    
    // 桶
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemsReg.COPPER_BUCKET.get())
        .pattern("A A")
        .pattern(" A ")
        .define('A', Items.COPPER_INGOT)
        .unlockedBy("has_copper", has(Items.COPPER_INGOT)).save(recipeOutput);
    
    // 防具
    ArrayList<Item> copperArmors = new ArrayList<>() {{
      add(ItemsReg.COPPER_HELMET.get());
      add(ItemsReg.COPPER_CHESTPLATE.get());
      add(ItemsReg.COPPER_LEGGINGS.get());
      add(ItemsReg.COPPER_BOOTS.get());
    }};
    buildArmorRecipes(copperArmors, Items.COPPER_INGOT, "has_copper", recipeOutput);
    
    // 剪刀
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemsReg.COPPER_SHEARS.get())
        .pattern("B ")
        .pattern(" B")
        .define('B', Items.COPPER_INGOT)
        .unlockedBy("has_copper", has(Items.COPPER_INGOT)).save(recipeOutput);
    
    // 炸弹
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemsReg.COPPER_FIRECRACKER.get(), 6)
        .pattern(" A ")
        .pattern("BCB")
        .pattern(" B ")
        .define('A', Items.STRING)
        .define('B', Items.COPPER_INGOT)
        .define('C', Items.GUNPOWDER)
        .unlockedBy("has_copper", has(Items.COPPER_INGOT)).save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemsReg.COPPER_GRENADE.get(), 3)
        .pattern(" A ")
        .pattern("BCB")
        .pattern(" B ")
        .define('A', Items.IRON_INGOT)
        .define('B', ItemsReg.COPPER_SHEET)
        .define('C', Items.GUNPOWDER)
        .unlockedBy("has_copper", has(ItemsReg.COPPER_SHEET)).save(recipeOutput);
  }
  
  private static void buildArmorRecipes(List<Item> items, Item originIngot, String unlockName, RecipeOutput recipeOutput) {
    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, items.getFirst())
        .pattern("AAA")
        .pattern("A A")
        .define('A', originIngot)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, items.get(1))
        .pattern("A A")
        .pattern("AAA")
        .pattern("AAA")
        .define('A', originIngot)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, items.get(2))
        .pattern("AAA")
        .pattern("A A")
        .pattern("A A")
        .define('A', originIngot)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, items.get(3))
        .pattern("A A")
        .pattern("A A")
        .define('A', originIngot)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
  }
  
  // 注册顺序：剑、镐、斧、锹、锄
  private static void buildToolsRecipes(List<Item> items, Item originIngot, String unlockName, RecipeOutput recipeOutput) {
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, items.getFirst())
        .pattern("B")
        .pattern("B")
        .pattern("A")
        .define('B', originIngot)
        .define('A', Items.STICK)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, items.get(1))
        .pattern("BBB")
        .pattern(" A ")
        .pattern(" A ")
        .define('B', originIngot)
        .define('A', Items.STICK)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, items.get(2))
        .pattern("BB")
        .pattern("BA")
        .pattern(" A")
        .define('B', originIngot)
        .define('A', Items.STICK)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, items.get(3))
        .pattern("B")
        .pattern("A")
        .pattern("A")
        .define('B', originIngot)
        .define('A', Items.STICK)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, items.get(4))
        .pattern("BB")
        .pattern(" A")
        .pattern(" A")
        .define('B', originIngot)
        .define('A', Items.STICK)
        .unlockedBy(unlockName, has(originIngot)).save(recipeOutput);
  }
  
  private static void buildIngotAndBlockRecipes(Item item, Block block, String unlockName, RecipeOutput recipeOutput) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
        .pattern("BBB")
        .pattern("BBB")
        .pattern("BBB")
        .define('B', item)
        .unlockedBy(unlockName, has(item)).save(recipeOutput);
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 9)
        .requires(block)
        .unlockedBy(unlockName, has(block)).save(recipeOutput);
  }
  
  private static void buildIngotAndNuggetRecipes(Item item, Item nugget, String unlockName, RecipeOutput recipeOutput) {
    threeByThreePacker(
        recipeOutput,
        RecipeCategory.MISC,
        item,
        nugget,
        unlockName
    );
    oneToOneConversionRecipe(
        recipeOutput,
        nugget,
        item,
        null,
        9
    );
  }
}
