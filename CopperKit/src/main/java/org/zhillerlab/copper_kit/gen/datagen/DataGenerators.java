package org.zhillerlab.copper_kit.gen.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = GlobalConfig.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
    
    // 配方与战利品表
//    generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
//        List.of(new LootTableProvider.SubProviderEntry(BlockLootTableGen::new, LootContextParamSets.BLOCK)), lookupProvider));
    generator.addProvider(event.includeServer(), new RecipeGen(packOutput, lookupProvider));
    
    // 方块
    BlockTagsProvider blockTagsProvider = new BlockTagGen(packOutput, lookupProvider, existingFileHelper);
    generator.addProvider(event.includeServer(), blockTagsProvider);
    generator.addProvider(event.includeClient(), new BlockStateGen(packOutput, existingFileHelper));
    
    // 物品
    generator.addProvider(event.includeClient(), new ItemModelGen(packOutput, existingFileHelper));
    generator.addProvider(event.includeServer(), new ItemTagGen(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
    
    // 数据包
    generator.addProvider(event.includeServer(), new DataMapGen(packOutput, lookupProvider));
    generator.addProvider(event.includeServer(), new DataPackGen(packOutput, lookupProvider));
  }
}
