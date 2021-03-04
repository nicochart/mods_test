package fr.factionbedrock.bedrockstuff.Basis;

import java.util.function.Supplier;

import fr.factionbedrock.bedrockstuff.Register.RegisterItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public class BasisToolMaterial
{
	public static final IItemTier bedrock =
			new ToolMaterial
			(
					5, //Niveau de minage
					2810, //durabilité
					10.0F, //efficacité
					6.0, //Dégats d'attaque
					15, //Enchantabilité
					() -> Ingredient.fromItems(RegisterItems.bedrockIngot) //Ingrédient de réparation (Enclume)
			);
	
	private static class ToolMaterial implements IItemTier
	{

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repair;

        public ToolMaterial(int harvestLevel, int maxUses, float efficiency, double attackDamage, int enchantability, Supplier<Ingredient> supplier)
        {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = (float)attackDamage;
            this.enchantability = enchantability;
            this.repair = new LazyValue<Ingredient>(supplier);
        }
        
        //getters
        @Override
        public int getMaxUses() {return maxUses;}

        @Override
        public float getEfficiency() {return efficiency;}

        @Override
        public float getAttackDamage() {return attackDamage;}

        @Override
        public int getHarvestLevel() {return harvestLevel;}

        @Override
        public int getEnchantability() {return enchantability;}

        @Override
        public Ingredient getRepairMaterial() {return repair.getValue();}
	}
}
