package fr.factionbedrock.Mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class CustomItemNameRenderMixin
{
    @Inject(method = "renderHeldItemTooltip", at = @At("RETURN"), cancellable = true)
    private void modifyItemName(DrawContext context, CallbackInfo ci)
    {
        //edited copy of renderHeldItemTooltip
        InGameHud hud = (InGameHud) (Object) this;
        Text text = Text.empty().append("Text above health bar");

        int i = hud.getTextRenderer().getWidth(text);
        int j = (context.getScaledWindowWidth() - i) / 2;
        int k = context.getScaledWindowHeight() - 59;

        int opacity = 255;

        if (opacity > 0) {context.drawTextWithBackground(hud.getTextRenderer(), text, j, k, i, ColorHelper.Argb.withAlpha(opacity, -1));}
    }
}
