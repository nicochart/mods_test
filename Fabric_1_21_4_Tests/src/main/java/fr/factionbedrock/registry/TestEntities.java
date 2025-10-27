package fr.factionbedrock.registry;

import fr.factionbedrock.FabricTest;
import fr.factionbedrock.entity.CubeEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class TestEntities
{
	public static final EntityType<CubeEntity> CUBE = register(Keys.CUBE, CubeEntity::new, 0.9F,2.1F, SpawnGroup.MONSTER);

	public static <E extends Entity> EntityType<E> register(RegistryKey<EntityType<?>> key, EntityType.EntityFactory<E> factory, float width, float height, SpawnGroup group)
	{
		return register(key.getValue().getPath(), EntityType.Builder.create(factory, group).dimensions(width, height).build(key));
	}

	public static <T extends EntityType<? extends Entity>> T register(String id, T entityType) {return Registry.register(Registries.ENTITY_TYPE, FabricTest.id(id), entityType);}

	public static class Keys
	{
		public static RegistryKey<EntityType<?>> CUBE = key("cube");

		private static RegistryKey<EntityType<?>> key(String name)
		{
			return RegistryKey.of(RegistryKeys.ENTITY_TYPE, FabricTest.id(name));
		}
	}

	public static void load()
	{
		FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.registerAttributes());
	}
}
