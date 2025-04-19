package fr.factionbedrock.util;

import fr.factionbedrock.FabricTest;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
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

    public static void messageLoadedModsToPlayer(PlayerEntity player)
    {
        int numberOfMods = FabricLoader.getInstance().getAllMods().size();
        int numberOfNotFabricMobs = 0;
        String loadedMods = "";
        for (ModContainer mod : FabricLoader.getInstance().getAllMods())
        {
            String modId = mod.getMetadata().getId();
            String name = mod.getMetadata().getName();
            String version = mod.getMetadata().getVersion().getFriendlyString();

            if (!modId.contains("fabric") && !modId.equals("java") && !modId.equals("minecraft") && !modId.equals("mixinextras"))
            {
                numberOfNotFabricMobs++;
                loadedMods += "\""+name+"\" : "+modId+" version "+version+" | ";
            }
        }

        loadedMods = ((numberOfNotFabricMobs == 0) ? "0 loaded mods" : numberOfNotFabricMobs + " loaded mods : ") + loadedMods;
        if (loadedMods.endsWith(" | ")) {loadedMods = loadedMods.substring(0, loadedMods.length() - 3);}
        player.sendMessage(Text.literal(loadedMods), false);
    }
}
