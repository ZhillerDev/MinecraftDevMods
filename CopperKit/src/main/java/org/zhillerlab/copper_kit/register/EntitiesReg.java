package org.zhillerlab.copper_kit.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;
import org.zhillerlab.copper_kit.entity.bomb.CopperFirecrackerEntity;
import org.zhillerlab.copper_kit.entity.bomb.CopperGrenadeEntity;

import java.util.function.Supplier;

public class EntitiesReg {
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
      DeferredRegister.create(Registries.ENTITY_TYPE, GlobalConfig.MOD_ID);
  
  public static final Supplier<EntityType<CopperFirecrackerEntity>> COPPER_FIRECRACKER_ENTITY = ENTITY_TYPES.register("copper_firecracker", () ->
      EntityType.Builder.of(CopperFirecrackerEntity::new, MobCategory.MISC)
          .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10)
          .build("copper_firecracker"));
  
  public static final Supplier<EntityType<CopperGrenadeEntity>> COPPER_GRENADE_ENTITY = ENTITY_TYPES.register("copper_grenade", () ->
      EntityType.Builder.of(CopperGrenadeEntity::new, MobCategory.MISC)
          .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10)
          .build("copper_grenade"));
  
  public static void init(IEventBus eventBus) {
    GlobalConfig.LOGGER.info("Registering Entities for " + GlobalConfig.MOD_ID);
    ENTITY_TYPES.register(eventBus);
  }
}
