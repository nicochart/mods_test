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
    public static final EntityType<EvilCowEntity> EVIL_COW = register("evil_cow", EvilCowEntity::new, 0.99F, 1.4F, SpawnGroup.MONSTER);

    public static <E extends Entity> EntityType<E> register(String id, EntityType.EntityFactory<E> factory, float width, float height, SpawnGroup group)
    {
        return register(id, EntityType.Builder.create(factory, group).dimensions(width, height).build(id));
    }

    public static <T extends EntityType<? extends Entity>> T register(String name, T entityType) {return Registry.register(Registries.ENTITY_TYPE, AerialHell.id(name), entityType);}

    public static void load()
    {
        FabricDefaultAttributeRegistry.register(EVIL_COW, EvilCowEntity.createAttributes());
    }
}
