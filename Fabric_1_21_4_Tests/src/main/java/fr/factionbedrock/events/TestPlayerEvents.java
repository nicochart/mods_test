package fr.factionbedrock.events;

import fr.factionbedrock.util.HandshakeTimeoutScheduler;
import fr.factionbedrock.util.PendingHandshakeTracker;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import java.util.UUID;

public class TestPlayerEvents
{
    public static void registerPlayerEvents()
    {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
        {
            UUID playerId = handler.player.getUuid();

            PendingHandshakeTracker.mark(playerId);

            HandshakeTimeoutScheduler.schedule(server, playerId, handler.player);
        });
    }
}
