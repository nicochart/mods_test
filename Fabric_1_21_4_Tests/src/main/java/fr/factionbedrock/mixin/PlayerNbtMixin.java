package fr.factionbedrock.mixin;

import fr.factionbedrock.registry.TestTrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerNbtMixin
{
    private static String total_click_count = "total_click_count";
    private static String lives = "lives";

    @Inject(at = @At("RETURN"), method = "readCustomDataFromNbt")
    private void read(NbtCompound nbt, CallbackInfo info)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (nbt.contains(total_click_count, NbtElement.INT_TYPE))
        {
            player.getDataTracker().set(TestTrackedData.TOTAL_CLICK_COUNT, nbt.getInt(total_click_count));
        }
        if (nbt.contains(lives, NbtElement.INT_TYPE))
        {
            player.getDataTracker().set(TestTrackedData.LIVES, nbt.getInt(lives));
        }
    }

    @Inject(at = @At("RETURN"), method = "writeCustomDataToNbt")
    private void write(NbtCompound nbt, CallbackInfo info)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;
        nbt.putInt(total_click_count, player.getDataTracker().get(TestTrackedData.TOTAL_CLICK_COUNT));
        nbt.putInt(lives, player.getDataTracker().get(TestTrackedData.LIVES));
    }
}
