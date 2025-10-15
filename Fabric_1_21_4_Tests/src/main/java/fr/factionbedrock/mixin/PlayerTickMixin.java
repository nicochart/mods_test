package fr.factionbedrock.mixin;

import fr.factionbedrock.FabricTest;
import fr.factionbedrock.registry.TestTags;
import fr.factionbedrock.registry.TestTrackedData;
import fr.factionbedrock.util.TestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mixin(ServerPlayerEntity.class)
public class PlayerTickMixin
{
    private static final Identifier CLICK_SPEED_MODIFIER = FabricTest.id("click_speed_modifier");
    private static final Identifier CLICK_SCALE_MODIFIER = FabricTest.id("click_scale_modifier");
    private static final Identifier CLICK_JUMP_MODIFIER = FabricTest.id("click_jump_modifier");
    private static final Identifier CLICK_STEP_HEIGHT_MODIFIER = FabricTest.id("click_step_height_modifier");

    private static final List<StructureBlockBlockEntity> REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST = new ArrayList<>();
    private static final BlockPos.Mutable MUTABLE_POS = new BlockPos.Mutable();

    @Inject(at = @At("RETURN"), method = "tick")
    private void onTick(CallbackInfo info)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        BlockPos playerPos = player.getBlockPos();
        World world = player.getWorld();
        updatePlayerAttributes(player);

        int lives = player.getDataTracker().get(TestTrackedData.LIVES);
        long liveRegainTimeMarker = player.getDataTracker().get(TestTrackedData.LIVE_REGAIN_TIME_MARKER);
        if (player.isCreative())
        {
            if (lives != 3)
            {
                player.getDataTracker().set(TestTrackedData.LIVES, 3);
            }
        }
        else if (lives != 3)
        {
            long currentTime = player.getServerWorld().getTime();
            if (currentTime - liveRegainTimeMarker < 1000)
            {
                if (lives == 0 && !player.isSpectator())
                {
                    player.changeGameMode(GameMode.SPECTATOR);
                }
            }
            else
            {
                int livePlayerCanRegain = (int) ((currentTime - liveRegainTimeMarker) / 1000);
                if (lives == 0)
                {
                    BlockPos spawnPos = player.getServerWorld().getSpawnPos();
                    player.teleport(player.getServerWorld(), spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), Set.of(), player.getYaw(), player.getPitch(), true);
                    player.changeGameMode(GameMode.SURVIVAL);
                }
                player.getDataTracker().set(TestTrackedData.LIVES, Math.min(3, lives + livePlayerCanRegain));
                player.getDataTracker().set(TestTrackedData.LIVE_REGAIN_TIME_MARKER, currentTime);
            }
        }

        //structure block detection every 2 seconds
        if (player.age % 40 == 0)
        {
            REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST.clear();
            TestHelper.fillNearbyStructureBlockEntitiesList(REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST, player.getServerWorld(), playerPos, 64);
        }
        //block transformation around player if block is air in a structure block bounding box
        if (!REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST.isEmpty())
        {
            int dx,dy,dz;
            int radius = 5;
            for (dx = -radius; dx <= radius; dx++)
            {
                for (dy = -radius; dy <= radius; dy++)
                {
                    for (dz = -radius; dz <= radius; dz++)
                    {
                        if (dx * dx + dy * dy + dz * dz > radius * radius) continue;

                        MUTABLE_POS.set(playerPos.getX() + dx, playerPos.getY() + dy, playerPos.getZ() + dz);

                        //ignore current iteration if block is not air
                        if (!world.isAir(MUTABLE_POS)) continue;

                        if (TestHelper.isPosInsideStructureBlockZone(MUTABLE_POS, REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST))
                        {
                            world.setBlockState(MUTABLE_POS, Blocks.TINTED_GLASS.getDefaultState(), Block.NOTIFY_ALL);
                        }
                    }
                }
            }
        }
    }

    private void updatePlayerAttributes(ServerPlayerEntity player)
    {
        int customValue = player.getDataTracker().get(TestTrackedData.TOTAL_CLICK_COUNT);
        if (!player.getInventory().contains(TestTags.Items.COUNTER_ITEMS) && player.getInventory().getEmptySlot() != -1)
        {
            TestHelper.runFunction(player, "give_counter_item");
        }

        EntityAttributeInstance speedAttribute = player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        if (speedAttribute != null)
        {
            speedAttribute.removeModifier(CLICK_SPEED_MODIFIER);

            speedAttribute.addTemporaryModifier(new EntityAttributeModifier(
                    CLICK_SPEED_MODIFIER,
                    customValue * 0.001,
                    EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }

        EntityAttributeInstance scaleAttribute = player.getAttributeInstance(EntityAttributes.SCALE);
        if (scaleAttribute != null)
        {
            scaleAttribute.removeModifier(CLICK_SCALE_MODIFIER);

            scaleAttribute.addTemporaryModifier(new EntityAttributeModifier(
                    CLICK_SCALE_MODIFIER,
                    customValue * 0.01,
                    EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }

        EntityAttributeInstance jumpAttribute = player.getAttributeInstance(EntityAttributes.JUMP_STRENGTH);
        if (jumpAttribute != null)
        {
            jumpAttribute.removeModifier(CLICK_JUMP_MODIFIER);

            jumpAttribute.addTemporaryModifier(new EntityAttributeModifier(
                    CLICK_JUMP_MODIFIER,
                    customValue * 0.0025,
                    EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }

        EntityAttributeInstance stepHeightAttribute = player.getAttributeInstance(EntityAttributes.STEP_HEIGHT);
        if (stepHeightAttribute != null)
        {
            stepHeightAttribute.removeModifier(CLICK_STEP_HEIGHT_MODIFIER);

            stepHeightAttribute.addTemporaryModifier(new EntityAttributeModifier(
                    CLICK_STEP_HEIGHT_MODIFIER,
                    customValue * 0.01,
                    EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }
    }
}
