package fr.factionbedrock.Mixin;

import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class CustomBrewingRecipeMixin
{
    @Inject(method = "registerDefaults", at = @At("HEAD"), cancellable = true)
    private static void addCustomBrewingRecipe(BrewingRecipeRegistry.Builder builder, CallbackInfo ci)
    {
        builder.registerPotionRecipe(Potions.AWKWARD, Items.DIAMOND_SWORD, Potions.STRONG_POISON);
    }
}
