package fr.factionbedrock.client.registry;

import fr.factionbedrock.FabricTest;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class TestKeyBinds
{
    public static final KeyBinding TEST_ABILITY_KEY = new KeyBinding(
            "key."+FabricTest.MOD_ID+".ability",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "category."+FabricTest.MOD_ID
    );

    public static void registerKeybinds()
    {
        KeyBindingHelper.registerKeyBinding(TEST_ABILITY_KEY);
    }

    public static void registerPressedInteractions()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TEST_ABILITY_KEY.wasPressed()) {
                if (client.player != null)
                {
                    client.player.sendMessage(Text.literal("Activated ability !"), true);
                }
            }
        });
    }
}
