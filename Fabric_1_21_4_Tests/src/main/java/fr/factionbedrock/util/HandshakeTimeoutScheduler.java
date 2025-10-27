package fr.factionbedrock.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HandshakeTimeoutScheduler
{
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void schedule(MinecraftServer server, UUID playerId, ServerPlayerEntity player)
    {
        scheduler.schedule(() ->
        {
            server.execute(() ->
            {
                if (!player.isDisconnected() && PendingHandshakeTracker.isStillWaiting(playerId))
                {
                    PendingHandshakeTracker.unmark(player.getUuid());
                    player.sendMessage(Text.literal("Server did not receive mod list."));
                    //player.networkHandler.disconnect(Text.literal("Kicked for not receiving mod list. Contact server administrator."));
                }
            });
        }, 5, TimeUnit.SECONDS);
    }

    public static void shutdownScheduler() {scheduler.shutdownNow();}
}