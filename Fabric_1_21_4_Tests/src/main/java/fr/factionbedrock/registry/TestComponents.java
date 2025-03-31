package fr.factionbedrock.registry;

import com.mojang.serialization.Codec;
import fr.factionbedrock.FabricTest;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class TestComponents
{
    public static final ComponentType<Integer> CLICK_COUNT_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            FabricTest.id("click_count"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static void load() {}
}
