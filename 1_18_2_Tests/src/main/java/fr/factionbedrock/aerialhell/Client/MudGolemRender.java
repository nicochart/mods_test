package fr.factionbedrock.aerialhell.Client;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.MudGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MudGolemRender extends MobRenderer<MudGolemEntity, MudGolemModel<MudGolemEntity>>
{
	private static String name = "mud_golem";
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public MudGolemRender(EntityRendererProvider.Context context)
    {
        super(context, new MudGolemModel<>(context.bakeLayer(MudGolemModel.MUD_GOLEM)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(MudGolemEntity entity) {return TEXTURE;}
}