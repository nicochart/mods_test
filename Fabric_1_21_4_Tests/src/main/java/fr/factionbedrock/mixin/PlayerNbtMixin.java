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
    private static String live_regain_time_marker = "live_regain_time_marker";

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
        if (nbt.contains(live_regain_time_marker, NbtElement.LONG_TYPE))
        {
            player.getDataTracker().set(TestTrackedData.LIVE_REGAIN_TIME_MARKER, nbt.getLong(live_regain_time_marker));
        }
    }

    @Inject(at = @At("RETURN"), method = "writeCustomDataToNbt")
    private void write(NbtCompound nbt, CallbackInfo info)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;
        nbt.putInt(total_click_count, player.getDataTracker().get(TestTrackedData.TOTAL_CLICK_COUNT));
        nbt.putInt(lives, player.getDataTracker().get(TestTrackedData.LIVES));
        nbt.putLong(live_regain_time_marker, player.getDataTracker().get(TestTrackedData.LIVE_REGAIN_TIME_MARKER));
    }
}
