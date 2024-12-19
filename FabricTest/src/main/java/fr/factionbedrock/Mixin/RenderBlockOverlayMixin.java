package fr.factionbedrock.Mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.factionbedrock.Registry.AerialHellBlocks;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class RenderBlockOverlayMixin
{
    private static final Identifier ENCHANTED_GLINT = Identifier.ofVanilla("textures/misc/enchanted_glint_entity.png");

    @Inject(method = "renderOverlays", at = @At("HEAD"), cancellable = true)
    private static void renderOverlays(MinecraftClient client, MatrixStack matrices, CallbackInfo callbackInfo)
    {
        PlayerEntity player = client.player;
        BlockState blockState = getInWallBlockState(player);
        if (blockState != null && blockState.isOf(AerialHellBlocks.AERIAL_TREE_PLANKS) && player != null)
        {
            renderCustomOverlay(player, matrices, ENCHANTED_GLINT, true);
            callbackInfo.cancel();
        }
    }

    //copy of net.minecraft.client.gui.hud.InGameOverlayRenderer method of same name
    @Nullable private static BlockState getInWallBlockState(PlayerEntity player)
    {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int i = 0; i < 8; i++)
        {
            double eyeX = player.getX() + (double)(((float)((i >> 0) % 2) - 0.5F) * player.getWidth() * 0.8F);
            double eyeY = player.getEyeY() + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F * player.getScale());
            double eyeZ = player.getZ() + (double)(((float)((i >> 2) % 2) - 0.5F) * player.getWidth() * 0.8F);
            mutable.set(eyeX, eyeY, eyeZ);
            BlockState blockState = player.getWorld().getBlockState(mutable);
            if (blockState.getRenderType() != BlockRenderType.INVISIBLE && blockState.shouldBlockVision(player.getWorld(), mutable)) {return blockState;}
        }
        return null;
    }

    //copy of net.minecraft.client.gui.hud.InGameOverlayRenderer renderUnderwaterOverlay method, edited
    private static void renderCustomOverlay(PlayerEntity player, MatrixStack matrices, Identifier texture, boolean overrideBrightness)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, texture);
        BlockPos blockPos = BlockPos.ofFloored(player.getX(), player.getEyeY(), player.getZ());
        float brightness = overrideBrightness ? 1.0F : LightmapTextureManager.getBrightness(player.getWorld().getDimension(), player.getWorld().getLightLevel(blockPos));
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(brightness, brightness, brightness, 0.9F);
        float yaw = -player.getYaw() / 64.0F;
        float pitch = player.getPitch() / 64.0F;
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        BufferBuilder bufferBuilder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        bufferBuilder.vertex(matrix4f, -1.0F, -1.0F, -0.5F).texture(4.0F + yaw, 4.0F + pitch);
        bufferBuilder.vertex(matrix4f, 1.0F, -1.0F, -0.5F).texture(0.0F + yaw, 4.0F + pitch);
        bufferBuilder.vertex(matrix4f, 1.0F, 1.0F, -0.5F).texture(0.0F + yaw, 0.0F + pitch);
        bufferBuilder.vertex(matrix4f, -1.0F, 1.0F, -0.5F).texture(4.0F + yaw, 0.0F + pitch);
        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    }
}
