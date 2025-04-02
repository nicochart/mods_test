package fr.factionbedrock;

import fr.factionbedrock.client.RenderRegistration;
import fr.factionbedrock.client.registry.TestKeyBinds;
import fr.factionbedrock.packet.CustomData;
import fr.factionbedrock.packet.TestNetworking;
import fr.factionbedrock.registry.TestBlocks;
import fr.factionbedrock.registry.TestComponents;
import fr.factionbedrock.registry.TestItems;
import fr.factionbedrock.registry.TestTrackedData;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
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
		TestComponents.load();
		TestTrackedData.load();
		PayloadTypeRegistry.playC2S().register(CustomData.ID, CustomData.CODEC);
		TestNetworking.registerServerReceiver();

		LOGGER.info("Hello Fabric world!");
	}

	@Override public void onInitializeClient()
	{
		TestKeyBinds.registerKeybinds();
		TestKeyBinds.registerPressedInteractions();
		PayloadTypeRegistry.playS2C().register(CustomData.ID, CustomData.CODEC);
		TestNetworking.registerClientReceiver();
		RenderRegistration.makeGrassBlockRenderUpsideDownWithRandomRotation();

		LOGGER.info("Hello Client Fabric world!");
	}

	public static Identifier id(String path) {return Identifier.of(MOD_ID, path);}
}