package fr.factionbedrock.mixin;

import fr.factionbedrock.FabricTest;
import fr.factionbedrock.registry.TestItems;
import fr.factionbedrock.registry.TestTags;
import fr.factionbedrock.registry.TestTrackedData;
import fr.factionbedrock.util.TestHelper;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class PlayerTickMixin
{
    private static final Identifier CLICK_SPEED_MODIFIER = FabricTest.id("click_speed_modifier");
    private static final Identifier CLICK_SCALE_MODIFIER = FabricTest.id("click_scale_modifier");
    private static final Identifier CLICK_JUMP_MODIFIER = FabricTest.id("click_jump_modifier");
    private static final Identifier CLICK_STEP_HEIGHT_MODIFIER = FabricTest.id("click_step_height_modifier");

    @Inject(at = @At("RETURN"), method = "tick")
    private void onTick(CallbackInfo info)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        updatePlayerAttributes(player);

        if (player.isCreative())
        {
            if (player.getDataTracker().get(TestTrackedData.LIVES) != 3)
            {
                player.getDataTracker().set(TestTrackedData.LIVES, 3);
            }
        }
        else if (player.getDataTracker().get(TestTrackedData.LIVES) == 0 && !player.isSpectator())
        {
            player.interactionManager.changeGameMode(GameMode.SPECTATOR);
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
