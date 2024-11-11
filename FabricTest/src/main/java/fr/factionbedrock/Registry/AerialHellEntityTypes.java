package fr.factionbedrock.Registry;

import fr.factionbedrock.AerialHell;
import fr.factionbedrock.Entity.EvilCowEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AerialHellEntityTypes
{
    public static final EntityType<EvilCowEntity> EVIL_COW = register("evil_cow", EntityType.Builder.<EvilCowEntity>create(EvilCowEntity::new, SpawnGroup.MONSTER).dimensions(0.99F, 1.4F).build("evil_cow"));

    public static <T extends EntityType<? extends Entity>> T register(String name, T entityType) {return Registry.register(Registries.ENTITY_TYPE, AerialHell.id(name), entityType);}

    public static void load()
    {
        FabricDefaultAttributeRegistry.register(EVIL_COW, EvilCowEntity.createAttributes());
    }
}
