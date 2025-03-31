package fr.factionbedrock.item;

import fr.factionbedrock.registry.TestComponents;
import fr.factionbedrock.registry.TestTrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class CounterItem extends Item
{
    public CounterItem(Settings settings) {super(settings);}

    @Override public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack stack = user.getStackInHand(hand);
        int previousCount = stack.getOrDefault(TestComponents.CLICK_COUNT_COMPONENT, 0);
        stack.set(TestComponents.CLICK_COUNT_COMPONENT, previousCount+1);

        int previousTotalCount = user.getDataTracker().get(TestTrackedData.TOTAL_CLICK_COUNT);
        user.getDataTracker().set(TestTrackedData.TOTAL_CLICK_COUNT, previousTotalCount+1);
        System.out.println(user+"total count = "+(previousTotalCount+1));

        return ActionResult.SUCCESS;
    }

    @Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        int count = stack.getOrDefault(TestComponents.CLICK_COUNT_COMPONENT, 0);
        tooltip.add(Text.translatable("item.test_mod.counter_item.desc", count).formatted(Formatting.GOLD));
    }
}