package com.Alganaut.Elementalis;

import com.Alganaut.Elementalis.registry.block.ModBlocks;
import com.Alganaut.Elementalis.registry.entity.ModEntities;
import com.Alganaut.Elementalis.registry.entity.client.DirtmanRenderer;
import com.Alganaut.Elementalis.registry.item.ModCreativeModeTabs;
import com.Alganaut.Elementalis.registry.item.ModItems;
import com.Alganaut.Elementalis.registry.sound.ModSounds;
import com.Alganaut.Elementalis.util.ModItemProperties;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Elementalis.MODID)
public class Elementalis {
    public static final String MODID = "elementalis";
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Elementalis(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        ModSounds.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModEntities.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.BLIGHT_ROD);
            event.accept(ModItems.BLIGHT_POWDER);
            event.accept(ModItems.UNDEAD_CHARGE);
            event.accept(ModItems.PERISH_SMITHING_TEMPLATE);

        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.SIX_MUSIC_DISC);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.TOMBSTONE);
            event.accept(ModBlocks.POLISHED_TOMBSTONE);
            event.accept(ModBlocks.TOMBSTONE_BRICKS);
            event.accept(ModBlocks.BLOOMING_TOMBSTONE_BRICKS);
            event.accept(ModBlocks.CHISELED_TOMBSTONE_BRICKS);
            event.accept(ModBlocks.CRACKED_TOMBSTONE_BRICKS);
            event.accept(ModBlocks.TOMBSTONE_PILLAR);
            event.accept(ModBlocks.MOROSE_BRANCH);
            event.accept(ModBlocks.STRIPPED_MOROSE_BRANCH);
            event.accept(ModBlocks.MOROSE_WOOD);
            event.accept(ModBlocks.STRIPPED_MOROSE_WOOD);
            event.accept(ModBlocks.MOROSE_PLANKS);

        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomItemProperties();

            EntityRenderers.register(ModEntities.DIRTMAN.get(), DirtmanRenderer::new);
        }
    }
}
