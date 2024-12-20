package com.Alganaut.Elementalis.datagen;

import com.Alganaut.Elementalis.Elementalis;
import com.Alganaut.Elementalis.registry.block.ModBlocks;
import com.Alganaut.Elementalis.registry.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Elementalis.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.PERISH_SMITHING_TEMPLATE.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.MOROSE_BRANCH.get().asItem())
                .add(ModBlocks.STRIPPED_MOROSE_BRANCH.get().asItem())
                .add(ModBlocks.MOROSE_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_MOROSE_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.MOROSE_PLANKS.asItem());

    }
}
