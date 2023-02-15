package fr.factionbedrock.bedrockstuff.Basis;

import java.util.function.Supplier;

import fr.factionbedrock.bedrockstuff.Register.RegisterItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;

public class BasisToolMaterial
{
	public static final Tier bedrock =
			new ToolMaterial
			(
					5, //Niveau de minage
					2810, //durabilite
					10.0F, //efficacite
					6.0, //Degats d'attaque
					15, //Enchantabilite
					() -> Ingredient.of(RegisterItems.bedrockIngot.get()) //Ingredient de reparation (Enclume)
			);
	
	private static class ToolMaterial implements Tier
	{

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyLoadedValue<Ingredient> repair;

        public ToolMaterial(int harvestLevel, int maxUses, float efficiency, double attackDamage, int enchantability, Supplier<Ingredient> supplier)
        {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = (float)attackDamage;
            this.enchantability = enchantability;
            this.repair = new LazyLoadedValue<Ingredient>(supplier);
        }
        
        //getters
        @Override
        public int getUses() {return maxUses;}

        @Override
        public float getSpeed() {return efficiency;}

        @Override
        public float getAttackDamageBonus() {return attackDamage;}

        @Override
        public int getLevel() {return harvestLevel;}

        @Override
        public int getEnchantmentValue() {return enchantability;}

        @Override
        public Ingredient getRepairIngredient() {return repair.get();}
	}
}
