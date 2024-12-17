package org.zhillerlab.copper_kit.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.zhillerlab.copper_kit.block.chest.CopperChestBlock;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;
import org.zhillerlab.copper_kit.entity.bomb.CopperFirecrackerEntity;
import org.zhillerlab.copper_kit.entity.bomb.CopperGrenadeEntity;
import org.zhillerlab.copper_kit.entity.chest.CopperChestEntity;

import java.util.List;
import java.util.function.Supplier;

public class EntitiesReg {
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
      DeferredRegister.create(Registries.ENTITY_TYPE, GlobalConfig.MOD_ID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
      DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GlobalConfig.MOD_ID);
  
  public static final Supplier<EntityType<CopperFirecrackerEntity>> COPPER_FIRECRACKER_ENTITY = ENTITY_TYPES.register("copper_firecracker", () ->
      EntityType.Builder.of(CopperFirecrackerEntity::new, MobCategory.MISC)
          .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10)
          .build("copper_firecracker"));
  public static final Supplier<EntityType<CopperGrenadeEntity>> COPPER_GRENADE_ENTITY = ENTITY_TYPES.register("copper_grenade", () ->
      EntityType.Builder.of(CopperGrenadeEntity::new, MobCategory.MISC)
          .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10)
          .build("copper_grenade"));
  
  
  public static List<CopperChestBlock> copperChestList = List.of(
      BlocksReg.COPPER_CHEST.get()
  );
  public static final Supplier<BlockEntityType<CopperChestEntity>> COPPER_CHEST_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("copper_chest", () ->
      BlockEntityType.Builder.of(CopperChestEntity::new, copperChestList.toArray(Block[]::new)).build(null));
  
  
  public static void init(IEventBus eventBus) {
    GlobalConfig.LOGGER.info("Registering Entities for " + GlobalConfig.MOD_ID);
    ENTITY_TYPES.register(eventBus);
    BLOCK_ENTITY_TYPES.register(eventBus);
  }
}
