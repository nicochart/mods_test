package fr.factionbedrock;

import fr.factionbedrock.client.RenderRegistration;
import fr.factionbedrock.client.registry.TestKeyBinds;
import fr.factionbedrock.registry.TestBlocks;
import fr.factionbedrock.registry.TestComponents;
import fr.factionbedrock.registry.TestItems;
import fr.factionbedrock.registry.TestTrackedData;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricTest implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "test_mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override public void onInitialize()
	{
		TestBlocks.load();
		TestItems.load();
		TestComponents.load();
		TestTrackedData.load();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}

	@Override public void onInitializeClient()
	{
		TestKeyBinds.registerKeybinds();
		TestKeyBinds.registerPressedInteractions();

		LOGGER.info("Hello Client Fabric world!");
		RenderRegistration.makeGrassBlockRenderUpsideDownWithRandomRotation();
	}

	public static Identifier id(String path) {return Identifier.of(MOD_ID, path);}
}