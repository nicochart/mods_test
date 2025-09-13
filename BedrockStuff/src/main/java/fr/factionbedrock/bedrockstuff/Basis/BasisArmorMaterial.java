package fr.factionbedrock.bedrockstuff.Basis;

import java.util.EnumMap;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Tags.BedrockStuffTags;
import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class BasisArmorMaterial
{
    public final static ArmorMaterial BEDROCK = new ArmorMaterial(
            42,
            createProtectionMap(4, 7, 10, 3, 7),
            15,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0F,
            0.1F,
            BedrockStuffTags.Items.REPAIRS_BEDROCK_MATERIAL,
            EquipmentAssets.BEDROCK
    );

    private static EnumMap<ArmorType, Integer> createProtectionMap(int boots, int leggings, int chestplate, int helmet, int body)
    {
        return Util.make(new EnumMap<>(ArmorType.class), map -> {
            map.put(ArmorType.BOOTS, boots);
            map.put(ArmorType.LEGGINGS, leggings);
            map.put(ArmorType.CHESTPLATE, chestplate);
            map.put(ArmorType.HELMET, helmet);
            map.put(ArmorType.BODY, body);
        });
    }

    public static class EquipmentAssets
    {
        public static ResourceKey<EquipmentAsset> BEDROCK = createId("bedrock");

        static ResourceKey<EquipmentAsset> createId(String name)
        {
            return ResourceKey.create(net.minecraft.world.item.equipment.EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(BedrockStuff.MODID, name));
        }
    }
}
