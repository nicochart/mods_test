package fr.factionbedrock.bedrockstuff.Basis;

import java.util.function.Supplier;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Register.RegisterItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BasisArmorMaterial
{
	public final static IArmorMaterial bedrock = new ArmorMaterial
			(
					BedrockStuff.MODID + ":bedrock", //Nom du matériau
					42, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
					new int[]
						{
							4, //Protection des Bottes
							7, //Pantalon
							10, //Plastron
							3 //Casque
						},
					15, //Enchantabilité
					SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, //Son lorsqu'on équipe
					4, //Robustesse
					0.1F, //Resistance au recul
					() -> Ingredient.fromItems(RegisterItems.bedrockIngot) //Matériaux de réparation
			);
	
	private static class ArmorMaterial implements IArmorMaterial
	{
        private static final int[] Max_Damage_Array = new int[] {13,15,16,11};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final float knockbackResistance;
        private final LazyValue<Ingredient> repairMaterial;

        public ArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, double toughness, float knockbackResistance, Supplier<Ingredient> supplier)
        {
            this.name = name;
            this.maxDamageFactor = maxDamageFactor;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.toughness = (float)toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairMaterial = new LazyValue<Ingredient>(supplier);
        }

        //getters
        @Override
        public int getDurability(EquipmentSlotType slotIn) {return Max_Damage_Array[slotIn.getIndex()] * maxDamageFactor;}

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {return damageReductionAmountArray[slotIn.getIndex()];}

        @Override
        public int getEnchantability() {return enchantability;}

        @Override
        public SoundEvent getSoundEvent() {return soundEvent;}

        @Override
        public Ingredient getRepairMaterial() {return repairMaterial.getValue();}

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {return name;}

        @Override
        public float getToughness() {return toughness;}

        @Override
        public float getKnockbackResistance() {return this.knockbackResistance;}
    }
}
