package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.Basis.BasisArmorMaterial;
import fr.factionbedrock.bedrockstuff.Basis.BasisToolMaterial;
import fr.factionbedrock.bedrockstuff.BedrockStuff;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegisterItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BedrockStuff.MODID);

    public static final DeferredItem<Item> BEDROCK_SCRAP = ITEMS.register(Keys.BEDROCK_SCRAP.location().getPath(), () -> new Item(new Item.Properties().setId(Keys.BEDROCK_SCRAP)));
    public static final DeferredItem<Item> BEDROCK_INGOT = ITEMS.register(Keys.BEDROCK_INGOT.location().getPath(), () -> new Item(new Item.Properties().setId(Keys.BEDROCK_INGOT)));
    public static final DeferredItem<Item> BEDROCK_ORE = ITEMS.register(Keys.BEDROCK_ORE.location().getPath(), () -> new BlockItem(RegisterBlocks.BEDROCK_ORE.get(), new Item.Properties().setId(Keys.BEDROCK_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DEEPSLATE_BEDROCK_ORE = ITEMS.register(Keys.DEEPSLATE_BEDROCK_ORE.location().getPath(), () -> new BlockItem(RegisterBlocks.DEEPSLATE_BEDROCK_ORE.get(), new Item.Properties().setId(Keys.DEEPSLATE_BEDROCK_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BEDROCK_UPGRADE_SMITHING_TEMPLATE = ITEMS.register(Keys.BEDROCK_UPGRADE_SMITHING_TEMPLATE.location().getPath(), () -> createBedrockUpgradeTemplate(new Item.Properties().setId(Keys.BEDROCK_UPGRADE_SMITHING_TEMPLATE)));

    public static final DeferredItem<Item> BEDROCK_HELMET = ITEMS.register(Keys.BEDROCK_HELMET.location().getPath(), () -> new Item(new Item.Properties().setId(Keys.BEDROCK_HELMET).humanoidArmor(BasisArmorMaterial.BEDROCK, ArmorType.HELMET)));
    public static final DeferredItem<Item> BEDROCK_CHESTPLATE = ITEMS.register(Keys.BEDROCK_CHESTPLATE.location().getPath(), () -> new Item(new Item.Properties().setId(Keys.BEDROCK_CHESTPLATE).humanoidArmor(BasisArmorMaterial.BEDROCK, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> BEDROCK_LEGGINGS = ITEMS.register(Keys.BEDROCK_LEGGINGS.location().getPath(), () -> new Item(new Item.Properties().setId(Keys.BEDROCK_LEGGINGS).humanoidArmor(BasisArmorMaterial.BEDROCK, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> BEDROCK_BOOTS = ITEMS.register(Keys.BEDROCK_BOOTS.location().getPath(), () -> new Item(new Item.Properties().setId(Keys.BEDROCK_BOOTS).humanoidArmor(BasisArmorMaterial.BEDROCK, ArmorType.BOOTS)));

    public static final DeferredItem<Item> BEDROCK_SWORD = ITEMS.register(Keys.BEDROCK_SWORD.location().getPath(), () -> new Item(new Item.Properties().setId(Keys.BEDROCK_SWORD).sword(BasisToolMaterial.BEDROCK, 3, -2.4F)));
    public static final DeferredItem<Item> BEDROCK_HOE = ITEMS.register(Keys.BEDROCK_HOE.location().getPath(), () -> new HoeItem(BasisToolMaterial.BEDROCK, 0.0F, -3.0F, new Item.Properties().setId(Keys.BEDROCK_HOE)));
    public static final DeferredItem<Item> BEDROCK_AXE = ITEMS.register(Keys.BEDROCK_AXE.location().getPath(), () -> new AxeItem(BasisToolMaterial.BEDROCK, 6.0F, -3.1F, new Item.Properties().setId(Keys.BEDROCK_AXE)));
    public static final DeferredItem<Item> BEDROCK_PICKAXE = ITEMS.register(Keys.BEDROCK_PICKAXE.location().getPath(), () -> new Item(new Item.Properties().setId(Keys.BEDROCK_PICKAXE).pickaxe(BasisToolMaterial.BEDROCK, 1.0F, -2.8F)));
    public static final DeferredItem<Item> BEDROCK_SHOVEL = ITEMS.register(Keys.BEDROCK_SHOVEL.location().getPath(), () -> new AxeItem(BasisToolMaterial.BEDROCK, 1.5F, -3.0F, new Item.Properties().setId(Keys.BEDROCK_SHOVEL)));

    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component BEDROCK_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(BedrockStuff.MODID, "smithing_template.bedrock_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BEDROCK_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(BedrockStuff.MODID,"smithing_template.bedrock_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BEDROCK_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(BedrockStuff.MODID,"smithing_template.bedrock_upgrade.base_slot_description")));
    private static final Component BEDROCK_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(BedrockStuff.MODID,"smithing_template.bedrock_upgrade.additions_slot_description")));

    public static SmithingTemplateItem createBedrockUpgradeTemplate(Item.Properties properties)
    {
        return new SmithingTemplateItem(BEDROCK_UPGRADE_APPLIES_TO, BEDROCK_UPGRADE_INGREDIENTS, BEDROCK_UPGRADE_BASE_SLOT_DESCRIPTION, BEDROCK_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, SmithingTemplateItem.createNetheriteUpgradeIconList(), SmithingTemplateItem.createNetheriteUpgradeMaterialList(), properties);
    }

    public static class Keys
    {
        public static final ResourceKey<Item> BEDROCK_SCRAP = createKey("bedrock_scrap");
        public static final ResourceKey<Item> BEDROCK_INGOT = createKey("bedrock_ingot");
        public static final ResourceKey<Item> BEDROCK_ORE = createKey("bedrock_ore");
        public static final ResourceKey<Item> DEEPSLATE_BEDROCK_ORE = createKey("deepslate_bedrock_ore");
        public static final ResourceKey<Item> BEDROCK_UPGRADE_SMITHING_TEMPLATE = createKey("bedrock_upgrade_smithing_template");

        public static final ResourceKey<Item> BEDROCK_HELMET = createKey("bedrock_helmet");
        public static final ResourceKey<Item> BEDROCK_CHESTPLATE = createKey("bedrock_chestplate");
        public static final ResourceKey<Item> BEDROCK_LEGGINGS = createKey("bedrock_leggings");
        public static final ResourceKey<Item> BEDROCK_BOOTS = createKey("bedrock_boots");

        public static final ResourceKey<Item> BEDROCK_SWORD = createKey("bedrock_sword");
        public static final ResourceKey<Item> BEDROCK_HOE = createKey("bedrock_hoe");
        public static final ResourceKey<Item> BEDROCK_AXE = createKey("bedrock_axe");
        public static final ResourceKey<Item> BEDROCK_PICKAXE = createKey("bedrock_pickaxe");
        public static final ResourceKey<Item> BEDROCK_SHOVEL = createKey("bedrock_shovel");

        private static ResourceKey<Item> createKey(String name)
        {
            return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BedrockStuff.MODID, name));
        }
    }
}
