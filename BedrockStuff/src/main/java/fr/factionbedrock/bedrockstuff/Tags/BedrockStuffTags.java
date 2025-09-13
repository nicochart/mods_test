package fr.factionbedrock.bedrockstuff.Tags;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BedrockStuffTags
{
    public static class Items
    {
        public static final TagKey<Item> REPAIRS_BEDROCK_MATERIAL = tag("repairs_bedrock_material");

        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(BedrockStuff.MODID, name));
        }
    }
}
