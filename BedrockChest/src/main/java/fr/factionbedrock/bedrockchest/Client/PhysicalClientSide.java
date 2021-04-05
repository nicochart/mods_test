package fr.factionbedrock.bedrockchest.Client;

import fr.factionbedrock.bedrockchest.IPhysicalSide;
import fr.factionbedrock.bedrockchest.Client.Renderer.BedrockChestTileEntityRenderer;
import fr.factionbedrock.bedrockchest.Registry.RegisterTileEntityTypes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class PhysicalClientSide implements IPhysicalSide
{
	@Override
	public void setup(IEventBus modEventBus, IEventBus forgeEventBus) 
	{
		modEventBus.addListener(this::clientSetup);
	}
	
	private void clientSetup(FMLClientSetupEvent event)
	{
		registerRenderers();
		registerGUIs();
		setRenderLayers();
	}
		
	private void registerRenderers()
	{
		// Tile entity renderers
		ClientRegistry.bindTileEntityRenderer(RegisterTileEntityTypes.CHEST.get(), BedrockChestTileEntityRenderer::new);
	}
	
	private void registerGUIs()
	{	
		
	}
	
	private void setRenderLayers()
	{
		
	}
}
