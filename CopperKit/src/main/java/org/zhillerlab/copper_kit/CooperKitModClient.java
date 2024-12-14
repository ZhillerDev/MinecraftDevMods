package org.zhillerlab.copper_kit;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;
import org.zhillerlab.copper_kit.register.EntitiesReg;

@Mod(value = GlobalConfig.MOD_ID, dist = Dist.CLIENT)
public class CooperKitModClient {
  public CooperKitModClient(IEventBus modEventBus, ModContainer modContainer) {
    modEventBus.addListener(this::registerEntityRenders);
  }
  
  public void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
    for (var registryObject : EntitiesReg.ENTITY_TYPES.getEntries()) {
      event.registerEntityRenderer((EntityType<? extends ThrowableItemProjectile>) registryObject.get(), (context) ->
          new ThrownItemRenderer<>(context, 1.0F, true));
    }
  }

//  @EventBusSubscriber(modid = GlobalConfig.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//  public static class ClientModEvents {
//
//  }
}
