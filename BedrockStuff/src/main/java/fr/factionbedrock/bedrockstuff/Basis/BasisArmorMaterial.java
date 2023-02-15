package fr.factionbedrock.bedrockstuff.Basis;

import java.util.function.Supplier;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Register.RegisterItems;
import net.minecraft.world.entity.EquipmentSlot;
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
					new int[]
						{
							4, //Protection des Bottes
							7, //Pantalon
							10, //Plastron
							3 //Casque
						},
					15, //Enchantabilite
					SoundEvents.ARMOR_EQUIP_NETHERITE, //Son lorsqu'on equipe
					4, //Robustesse
					0.1F, //Resistance au recul
					() -> Ingredient.of(RegisterItems.bedrockIngot.get()) //Materiaux de reparation
			);
	
	private static class BedrockStuffArmorMaterial implements ArmorMaterial
	{
        private static final int[] Max_Damage_Array = new int[] {13,15,16,11};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final float knockbackResistance;
        private final LazyLoadedValue<Ingredient> repairMaterial;

        public BedrockStuffArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, double toughness, float knockbackResistance, Supplier<Ingredient> supplier)
        {
            this.name = name;
            this.maxDamageFactor = maxDamageFactor;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.toughness = (float)toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairMaterial = new LazyLoadedValue<Ingredient>(supplier);
        }

        //getters
        @Override
        public int getDurabilityForSlot(EquipmentSlot slotIn) {return Max_Damage_Array[slotIn.getIndex()] * maxDamageFactor;}

        @Override
        public int getDefenseForSlot(EquipmentSlot slotIn) {return damageReductionAmountArray[slotIn.getIndex()];}

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
