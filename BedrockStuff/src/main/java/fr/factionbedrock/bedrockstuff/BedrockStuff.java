package fr.factionbedrock.bedrockstuff;

import fr.factionbedrock.bedrockstuff.Event.CreativeTabEvent;
import fr.factionbedrock.bedrockstuff.Register.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BedrockStuff.MODID)
public class BedrockStuff
{
	public static final String MODID = "bedrockstuff";
	public static final String NAME = "Bedrock Sword";
	public static final String VERSION = "1.0";
	
	private static Logger LOGGER = LogManager.getLogger();
	
    public BedrockStuff(IEventBus modEventBus, ModContainer modContainer, Dist dist)
    {
        RegisterBlocks.BLOCKS.register(modEventBus);
        RegisterItems.ITEMS.register(modEventBus);
        RegisterCreativeModeTabs.TABS.register(modEventBus);

        if (dist == Dist.CLIENT)
        {
            modEventBus.addListener(CreativeTabEvent::buildContents);
        }
    }
}