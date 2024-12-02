package fr.factionbedrock.Mixin;

import com.google.common.collect.Lists;
import fr.factionbedrock.Registry.AerialHellItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class CustomItemNameRenderMixin
{
    private static final Text DISABLED_TEXT = Text.translatable("item.disabled").formatted(Formatting.RED);

    @Inject(method = "getTooltip", at = @At("HEAD"), cancellable = true)
    private void modifyItemName(Item.TooltipContext context, @Nullable PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir)
    {
        //edited copy of ItemStack.getTooltip
        ItemStack itemstack = (ItemStack) (Object) this;
        if (!type.isCreative() && itemstack.contains(DataComponentTypes.HIDE_TOOLTIP)) {cir.setReturnValue(List.of()); return;}
        else
        {
            List<Text> list = Lists.<Text>newArrayList();
            //What is done in vanilla code
            //MutableText mutableText = Text.empty().append(itemstack.getName()).formatted(itemstack.getRarity().getFormatting());
            //if (itemstack.contains(DataComponentTypes.CUSTOM_NAME)) {mutableText.formatted(Formatting.ITALIC);}

            MutableText mutableText = itemstack.getItem() == AerialHellItems.EVIL_COW_SPAWN_EGG ? Text.empty().append(itemstack.getName()).formatted(Formatting.DARK_RED).formatted(Formatting.BOLD) : Text.empty().append(itemstack.getName()).formatted(Formatting.DARK_GREEN);

            list.add(mutableText);
            if (!type.isAdvanced() && !itemstack.contains(DataComponentTypes.CUSTOM_NAME) && itemstack.isOf(Items.FILLED_MAP))
            {
                MapIdComponent mapIdComponent = itemstack.get(DataComponentTypes.MAP_ID);
                if (mapIdComponent != null) {list.add(FilledMapItem.getIdText(mapIdComponent));}
            }

            Consumer<Text> consumer = list::add;
            if (!itemstack.contains(DataComponentTypes.HIDE_ADDITIONAL_TOOLTIP)) {itemstack.getItem().appendTooltip(itemstack, context, list, type);}

            itemstack.appendTooltip(DataComponentTypes.JUKEBOX_PLAYABLE, context, consumer, type);
            itemstack.appendTooltip(DataComponentTypes.TRIM, context, consumer, type);
            itemstack.appendTooltip(DataComponentTypes.STORED_ENCHANTMENTS, context, consumer, type);
            itemstack.appendTooltip(DataComponentTypes.ENCHANTMENTS, context, consumer, type);
            itemstack.appendTooltip(DataComponentTypes.DYED_COLOR, context, consumer, type);
            itemstack.appendTooltip(DataComponentTypes.LORE, context, consumer, type);
            itemstack.appendAttributeModifiersTooltip(consumer, player);
            itemstack.appendTooltip(DataComponentTypes.UNBREAKABLE, context, consumer, type);
            BlockPredicatesChecker blockPredicatesChecker = itemstack.get(DataComponentTypes.CAN_BREAK);
            if (blockPredicatesChecker != null && blockPredicatesChecker.showInTooltip())
            {
                consumer.accept(ScreenTexts.EMPTY);
                consumer.accept(BlockPredicatesChecker.CAN_BREAK_TEXT);
                blockPredicatesChecker.addTooltips(consumer);
            }

            BlockPredicatesChecker blockPredicatesChecker2 = itemstack.get(DataComponentTypes.CAN_PLACE_ON);
            if (blockPredicatesChecker2 != null && blockPredicatesChecker2.showInTooltip())
            {
                consumer.accept(ScreenTexts.EMPTY);
                consumer.accept(BlockPredicatesChecker.CAN_PLACE_TEXT);
                blockPredicatesChecker2.addTooltips(consumer);
            }

            if (type.isAdvanced())
            {
                if (itemstack.isDamaged())
                {
                    list.add(Text.translatable("item.durability", itemstack.getMaxDamage() - itemstack.getDamage(), itemstack.getMaxDamage()));
                }

                list.add(Text.literal(Registries.ITEM.getId(itemstack.getItem()).toString()).formatted(Formatting.DARK_GRAY));
                int i = itemstack.components.size();
                if (i > 0) {list.add(Text.translatable("item.components", i).formatted(Formatting.DARK_GRAY));}
            }

            if (player != null && !itemstack.getItem().isEnabled(player.getWorld().getEnabledFeatures())) {list.add(DISABLED_TEXT);}
            cir.setReturnValue(list);
        }
    }
}
