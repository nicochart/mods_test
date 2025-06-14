package fr.factionbedrock.packet;

import fr.factionbedrock.util.PendingHandshakeTracker;
import fr.factionbedrock.util.TestHelper;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class TestNetworking
{
    public static final CustomData USE_ABILITY_PACKET = new CustomData("use_ability", 0);
    public static final CustomData RECEIVED_PACKET = new CustomData("received", 0);

    public static void registerData()
    {
        PayloadTypeRegistry.playC2S().register(CustomData.ID, CustomData.CODEC);
        PayloadTypeRegistry.playS2C().register(CustomData.ID, CustomData.CODEC);
    }

    public static void sendPacketFromServer(ServerPlayerEntity serverPlayer, CustomData payload)
    {
        ServerPlayNetworking.send(serverPlayer, payload);
    }

    public static void registerServerReceiver()
    {
        ServerPlayNetworking.registerGlobalReceiver(CustomData.ID, (payload, context) ->
        {
            if (payload.name().equals(USE_ABILITY_PACKET.name()))
            {
                context.player().sendMessage(Text.literal("Activated ability !"), false);
                PendingHandshakeTracker.unmark(context.player().getUuid());
                TestHelper.messageLoadedModsToPlayer(context.player());
                sendPacketFromServer(context.player(), RECEIVED_PACKET);
            }
        });
    }
}
