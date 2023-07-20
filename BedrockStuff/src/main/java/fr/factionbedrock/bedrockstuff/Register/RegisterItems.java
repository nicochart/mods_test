package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BedrockStuff.MODID);

    public static final RegistryObject<Item> bedrockScrap = ITEMS.register("bedrock_scrap", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> bedrockIngot = ITEMS.register("bedrock_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> bedrockOre = ITEMS.register("bedrock_ore", () -> new BlockItem(RegisterBlocks.bedrockOre.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> deepslateBedrockOre = ITEMS.register("deepslate_bedrock_ore", () -> new BlockItem(RegisterBlocks.deepslateBedrockOre.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> bedrockUpgradeSmithingTemplate = ITEMS.register("bedrock_upgrade_smithing_template", RegisterItems::createBedrockUpgradeTemplate);

    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE; private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final Component BEDROCK_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(BedrockStuff.MODID,"bedrock_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component BEDROCK_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(BedrockStuff.MODID, "smithing_template.bedrock_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BEDROCK_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(BedrockStuff.MODID,"smithing_template.bedrock_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BEDROCK_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(BedrockStuff.MODID,"smithing_template.bedrock_upgrade.base_slot_description")));
    private static final Component BEDROCK_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(BedrockStuff.MODID,"smithing_template.bedrock_upgrade.additions_slot_description")));

    public static SmithingTemplateItem createBedrockUpgradeTemplate()
    {
        return new SmithingTemplateItem(BEDROCK_UPGRADE_APPLIES_TO, BEDROCK_UPGRADE_INGREDIENTS, BEDROCK_UPGRADE, BEDROCK_UPGRADE_BASE_SLOT_DESCRIPTION, BEDROCK_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, SmithingTemplateItem.createNetheriteUpgradeIconList(), SmithingTemplateItem.createNetheriteUpgradeMaterialList());
    }
}
