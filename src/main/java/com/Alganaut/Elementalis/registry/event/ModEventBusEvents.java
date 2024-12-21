package com.Alganaut.Elementalis.registry.event;

import com.Alganaut.Elementalis.Elementalis;
import com.Alganaut.Elementalis.registry.entity.ModEntities;
import com.Alganaut.Elementalis.registry.entity.client.DirtmanModel;
import com.Alganaut.Elementalis.registry.entity.custom.Dirtman;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Elementalis.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
@SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    event.registerLayerDefinition(DirtmanModel.LAYER_LOCATION, DirtmanModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
    event.put(ModEntities.DIRTMAN.get(), Dirtman.createAttributes().build());
    }

}
