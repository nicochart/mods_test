package fr.factionbedrock.events;

import fr.factionbedrock.util.HandshakeTimeoutScheduler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class TestServerEvents
{
    public static void registerPlayerEvents()
    {
        ServerLifecycleEvents.SERVER_STOPPING.register(server ->
        {
            HandshakeTimeoutScheduler.shutdownScheduler();
        });
    }
}
