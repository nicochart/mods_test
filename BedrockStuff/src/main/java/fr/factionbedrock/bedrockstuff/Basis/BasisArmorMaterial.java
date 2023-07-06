package fr.factionbedrock.bedrockstuff.Basis;

import java.util.EnumMap;
import java.util.function.Supplier;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Register.RegisterItems;
import net.minecraft.Util;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BasisArmorMaterial
{
	public final static ArmorMaterial bedrock = new BedrockStuffArmorMaterial
			(
					BedrockStuff.MODID + ":bedrock", //Nom du materiau
					42, //Facteur de degats, permet de calculer la durabilite avec le Max_Damage_Array
                    Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
                        map.put(ArmorItem.Type.BOOTS, 4);
                        map.put(ArmorItem.Type.LEGGINGS, 7);
                        map.put(ArmorItem.Type.CHESTPLATE, 10);
                        map.put(ArmorItem.Type.HELMET, 3);
                    }),
					15, //Enchantabilite
					SoundEvents.ARMOR_EQUIP_NETHERITE, //Son lorsqu'on equipe
					4, //Robustesse
					0.1F, //Resistance au recul
					() -> Ingredient.of(RegisterItems.bedrockIngot.get()) //Materiaux de reparation
			);
	
	private static class BedrockStuffArmorMaterial implements ArmorMaterial
	{
        private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
            p_266653_.put(ArmorItem.Type.BOOTS, 13);
            p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
            p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
            p_266653_.put(ArmorItem.Type.HELMET, 11);
        });
        private final String name;
        private final int maxDamageFactor;
        private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final float knockbackResistance;
        private final LazyLoadedValue<Ingredient> repairMaterial;

        public BedrockStuffArmorMaterial(String name, int maxDamageFactor, EnumMap<ArmorItem.Type, Integer> protectionFunctionForType, int enchantability, SoundEvent soundEvent, double toughness, float knockbackResistance, Supplier<Ingredient> supplier)
        {
            this.name = name;
            this.maxDamageFactor = maxDamageFactor;
            this.protectionFunctionForType = protectionFunctionForType;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.toughness = (float)toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairMaterial = new LazyLoadedValue<Ingredient>(supplier);
        }

        //getters
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.maxDamageFactor;}

        @Override
        public int getDefenseForType(ArmorItem.Type type) {return this.protectionFunctionForType.get(type);}

        @Override
        public int getEnchantmentValue() {return enchantability;}

        @Override
        public SoundEvent getEquipSound(){return soundEvent;}

        @Override
        public Ingredient getRepairIngredient() {return repairMaterial.get();}

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {return name;}

        @Override
        public float getToughness() {return toughness;}

        @Override
        public float getKnockbackResistance() {return this.knockbackResistance;}
    }
}
