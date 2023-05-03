package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AerialHellEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

	public static final RegistryObject<EntityType<MudGolemEntity>> MUD_GOLEM = ENTITIES.register("mud_golem", () -> EntityType.Builder.of(MudGolemEntity::new, MobCategory.MONSTER)
	            .sized(1.4F,2.3F).clientTrackingRange(10).build("mud_golem"));

	public static void entitySpawnPlacements()
	{
		//..
	}

	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event)
	{
		event.put(AerialHellEntities.MUD_GOLEM.get(), MudGolemEntity.registerAttributes().build());
	}
}
