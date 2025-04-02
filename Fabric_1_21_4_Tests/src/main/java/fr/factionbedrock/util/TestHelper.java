package fr.factionbedrock.util;

import fr.factionbedrock.FabricTest;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class TestHelper
{
    public static void runFunction(ServerPlayerEntity user, String functionName)
    {
        MinecraftServer server = user.server;
        ServerWorld world = user.getServerWorld();
        Identifier functionId = FabricTest.id(functionName);

        Optional<CommandFunction<ServerCommandSource>> mcFunction = server.getCommandFunctionManager().getFunction(functionId);

        mcFunction.ifPresent(function -> {
            ServerCommandSource source = server.getCommandSource().withEntity(user).withWorld(world).withPosition(user.getPos()).withSilent();
            server.getCommandFunctionManager().execute(function, source);
        });
    }
}
