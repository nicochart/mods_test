package fr.factionbedrock;

import fr.factionbedrock.client.RenderRegistration;
import fr.factionbedrock.client.packet.ClientTestNetworking;
import fr.factionbedrock.client.registry.TestKeyBinds;
import fr.factionbedrock.events.TestPlayerEvents;
import fr.factionbedrock.events.TestServerEvents;
import fr.factionbedrock.packet.TestNetworking;
import fr.factionbedrock.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricTest implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "test_mod";
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override public void onInitialize()
	{
		TestBlocks.load();
		TestItems.load();
		TestEntities.load();
		TestComponents.load();
		TestTrackedData.load();
		TestNetworking.registerData();
		TestNetworking.registerServerReceiver();
		TestPlayerEvents.registerPlayerEvents();
		TestServerEvents.registerPlayerEvents();

		LOGGER.info("Hello Fabric world!");
	}

	@Override public void onInitializeClient()
	{
		TestKeyBinds.registerKeybinds();
		TestKeyBinds.registerPressedInteractions();
		ClientTestNetworking.registerClientReceiver();
		RenderRegistration.makeGrassBlockRenderUpsideDownWithRandomRotation();
		RenderRegistration.registerLayerDefinitions();
		RenderRegistration.registerRenderers();

		LOGGER.info("Hello Client Fabric world!");
	}

	public static Identifier id(String path) {return Identifier.of(MOD_ID, path);}
}