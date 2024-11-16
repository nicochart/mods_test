package fr.factionbedrock.Client.Registry;

import fr.factionbedrock.AerialHell;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

import static net.minecraft.client.render.TexturedRenderLayers.CHEST_ATLAS_TEXTURE;

public class AerialHellChestMaterials
{
    public static final SpriteIdentifier AERIAL_TREE_SINGLE = makeChestMaterial("aerial_tree", ChestType.SINGLE);
    public static final SpriteIdentifier AERIAL_TREE_LEFT = makeChestMaterial("aerial_tree", ChestType.LEFT);
    public static final SpriteIdentifier AERIAL_TREE_RIGHT = makeChestMaterial("aerial_tree", ChestType.RIGHT);

    private static SpriteIdentifier makeChestMaterial(String name, ChestType type)
    {
        return switch (type)
        {
            case LEFT -> new SpriteIdentifier(CHEST_ATLAS_TEXTURE, Identifier.of(AerialHell.MOD_ID, "entity/chest/" + name + "/" + name + "_left"));
            case RIGHT -> new SpriteIdentifier(CHEST_ATLAS_TEXTURE, Identifier.of(AerialHell.MOD_ID, "entity/chest/" + name + "/" + name + "_right"));
            default -> new SpriteIdentifier(CHEST_ATLAS_TEXTURE, Identifier.of(AerialHell.MOD_ID, "entity/chest/" + name + "/" + name));
        };
    }
}
