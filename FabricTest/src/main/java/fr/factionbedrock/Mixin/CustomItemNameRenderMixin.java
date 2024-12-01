package fr.factionbedrock.Mixin;

import fr.factionbedrock.Registry.AerialHellItems;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class CustomItemNameRenderMixin
{
    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    private void modifyItemName(DrawContext context, CallbackInfo ci)
    {
        InGameHud hud = (InGameHud) (Object) this;
        ItemStack stack = hud.currentStack;

        if (stack.getItem() == AerialHellItems.EVIL_COW_SPAWN_EGG)
        {
            ci.cancel();

            //edited copy of InGameHud.renderHeldItemTooltip
            hud.client.getProfiler().push("selectedItemName");
            if (hud.heldItemTooltipFade > 0 && !stack.isEmpty())
            {
                //What is done in vanilla code
                //MutableText mutableText = Text.empty().append(hud.currentStack.getName()).formatted(hud.currentStack.getRarity().getFormatting());
                //if (hud.currentStack.contains(DataComponentTypes.CUSTOM_NAME)) {mutableText.formatted(Formatting.ITALIC);}

                MutableText mutableText = Text.empty().append(stack.getName()).formatted(Formatting.DARK_RED).formatted(Formatting.BOLD);

                int i = hud.getTextRenderer().getWidth(mutableText);
                int j = (context.getScaledWindowWidth() - i) / 2;
                int k = context.getScaledWindowHeight() - 59;
                if (!hud.client.interactionManager.hasStatusBars()) {k += 14;}

                int opacity = (int)((float)hud.heldItemTooltipFade * 256.0F / 10.0F);
                if (opacity > 255) {opacity = 255;}

                if (opacity > 0) {context.drawTextWithBackground(hud.getTextRenderer(), mutableText, j, k, i, ColorHelper.Argb.withAlpha(opacity, -1));}
            }

            hud.client.getProfiler().pop();
        }
    }
}
