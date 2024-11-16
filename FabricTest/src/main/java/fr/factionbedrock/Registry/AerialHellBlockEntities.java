package fr.factionbedrock.Registry;

import fr.factionbedrock.AerialHell;
import fr.factionbedrock.BlockEntity.AerialHellChestBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AerialHellBlockEntities
{
    public static final BlockEntityType<AerialHellChestBlockEntity> CHEST = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AerialHell.MOD_ID, "chest"),
        BlockEntityType.Builder.create(AerialHellChestBlockEntity::new,
                AerialHellBlocks.AERIAL_TREE_CHEST
        ).build());

    public static void load() {}
}
