package fr.factionbedrock.bedrockstuff.Basis;

import fr.factionbedrock.bedrockstuff.Tags.BedrockStuffTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class BasisToolMaterial
{
    public static final ToolMaterial BEDROCK = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2810,
            10.0F,
            6.0F,
            15,
            BedrockStuffTags.Items.REPAIRS_BEDROCK_MATERIAL
    );
}
