package fr.factionbedrock.util;

import fr.factionbedrock.FabricTest;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public static void messageLoadedResourcePacksToPlayer(ResourcePackManager resourcePackManager, PlayerEntity player)
    {
        //temporary solution to avoid mod list to appear in loaded packs
        List<String> loadedModIds = new ArrayList<>();
        for (ModContainer mod : FabricLoader.getInstance().getAllMods())
        {
            String modId = mod.getMetadata().getId();
            if (!modId.contains("fabric") && !modId.equals("java") && !modId.equals("minecraft") && !modId.equals("mixinextras"))
            {
                loadedModIds.add(modId);
            }
        }

        String loadedPacks = "";
        int numberOfLoadedPacks = 0;
        Collection<ResourcePackProfile> enabledPacks = resourcePackManager.getEnabledProfiles();
        if (enabledPacks.isEmpty()) {loadedPacks = "0 loaded packs";}
        for (ResourcePackProfile profile : enabledPacks)
        {
            if (!profile.getId().contains("fabric") && !loadedModIds.contains(profile.getId()))
            {
                numberOfLoadedPacks++;

                loadedPacks += "\""+profile.getDisplayName().getString()+"\" : " + profile.getDescription().getString() + " | ";
            }
        }
        loadedPacks = ((numberOfLoadedPacks == 0) ? "0 loaded packs" : numberOfLoadedPacks + " loaded packs : ") + loadedPacks;
        if (loadedPacks.endsWith(" | ")) {loadedPacks = loadedPacks.substring(0, loadedPacks.length() - 3);}
        player.sendMessage(Text.literal(loadedPacks), false);
    }

    public static void fillNearbyStructureBlockEntitiesList(List<StructureBlockBlockEntity> listToFill, ServerWorld world, BlockPos center, int radius)
    {
        int chunkRadius = (radius >> 4) + 1;

        int centerChunkX = center.getX() >> 4;
        int centerChunkZ = center.getZ() >> 4;

        for (int cx = centerChunkX - chunkRadius; cx <= centerChunkX + chunkRadius; cx++)
        {
            for (int cz = centerChunkZ - chunkRadius; cz <= centerChunkZ + chunkRadius; cz++)
            {
                Chunk chunk = world.getChunk(cx, cz, ChunkStatus.FULL, false);
                if (chunk == null) continue;

                for (BlockPos pos : chunk.getBlockEntityPositions())
                {
                    if (pos.isWithinDistance(center, radius))
                    {
                        BlockEntity blockentity = chunk.getBlockEntity(pos);
                        if (blockentity instanceof StructureBlockBlockEntity structureBlockEntity)
                        {
                            listToFill.add(structureBlockEntity);
                        }
                    }
                }
            }
        }
    }

    public static boolean isPosInsideStructureBlockZone(BlockPos pos, List<StructureBlockBlockEntity> structureBlockEntities)
    {
        for (StructureBlockBlockEntity structureBlockEntity : structureBlockEntities)
        {
            BlockPos offset = structureBlockEntity.getOffset();
            BlockPos origin = structureBlockEntity.getPos().add(offset);
            Vec3i size = structureBlockEntity.getSize();
            StructureBlockMode mode = structureBlockEntity.getMode();

            //ignore current iteration if structure block is not in "save" mode
            if (mode != StructureBlockMode.SAVE) continue;

            BlockPos min = origin;
            BlockPos max = origin.add(size.getX() - 1, size.getY() - 1, size.getZ() - 1);

            int minX = Math.min(min.getX(), max.getX());
            int minY = Math.min(min.getY(), max.getY());
            int minZ = Math.min(min.getZ(), max.getZ());
            int maxX = Math.max(min.getX(), max.getX());
            int maxY = Math.max(min.getY(), max.getY());
            int maxZ = Math.max(min.getZ(), max.getZ());

            if (pos.getX() >= minX && pos.getX() <= maxX && pos.getY() >= minY && pos.getY() <= maxY && pos.getZ() >= minZ && pos.getZ() <= maxZ) {return true;}
        }
        return false;
    }
}
