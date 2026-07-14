package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Layers.SporeRenderTypes;
import com.Harbinger.Spore.Client.Models.EchoModel;
import com.Harbinger.Spore.Sentities.Projectile.Echo;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class EchoRender extends EntityRenderer<Echo> {
    private final EchoModel<Echo> model = new EchoModel<>();

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/echolocation.png");

    public EchoRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(Echo adaptableProjectile, float p_114486_, float p_114487_, PoseStack stack, MultiBufferSource bufferSource, int value) {
        if (adaptableProjectile.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(adaptableProjectile) < 12.25)) {
            stack.pushPose();
            stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            stack.mulPose(Axis.YP.rotationDegrees(180.0F));
            stack.translate(0,-1,0);
            VertexConsumer vertexconsumer = bufferSource.getBuffer(SporeRenderTypes.glowingTranslucent(this.getTextureLocation(adaptableProjectile)));
            float alpha = 1.0f - ((float)adaptableProjectile.getLife() / Echo.LIFE);
            alpha = Math.max(0, Math.min(1, alpha));
            int color = (int)(alpha * 255) << 24 | 0xFFFFFF;
            this.model.setupAnim(adaptableProjectile,0,0,adaptableProjectile.tickCount+p_114486_,0,0);
            this.model.renderToBuffer(stack, vertexconsumer, value, OverlayTexture.NO_OVERLAY, color);
            stack.popPose();
            super.render(adaptableProjectile, p_114486_, p_114487_, stack, bufferSource, value);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Echo entity) {
        return TEXTURE;
    }
}
