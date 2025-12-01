package fr.factionbedrock.client.entityrender.state;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class CubeRenderState extends LivingEntityRenderState
{
    public Identifier texture;
    public boolean isLeftArm;
    public boolean isRightArm;
    public boolean isHead;
    public int packedLight;
}
