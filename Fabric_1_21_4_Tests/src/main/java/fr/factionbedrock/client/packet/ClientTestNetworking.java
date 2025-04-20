package fr.factionbedrock.client.packet;

import fr.factionbedrock.packet.CustomData;
import fr.factionbedrock.packet.TestNetworking;
import fr.factionbedrock.util.TestHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;

public class ClientTestNetworking
{
    public static void sendPacketFromClient(CustomData payload)
    {
        ClientPlayNetworking.send(payload);
    }

    public static void registerClientReceiver()
    {
        ClientPlayNetworking.registerGlobalReceiver(CustomData.ID, (payload, context) ->
        {
            if (payload.name().equals(TestNetworking.RECEIVED_PACKET.name()))
            {
                context.player().sendMessage(Text.literal("Received packet from server !"), false);
                TestHelper.messageLoadedModsToPlayer(context.player());
            }
        });
    }
}
