package fr.factionbedrock.registry;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;

public class TestTrackedData
{
    public static final TrackedData<Integer> TOTAL_CLICK_COUNT = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> LIVES = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> LIVE_REGAIN_TIMER = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public static void load() {}
}
