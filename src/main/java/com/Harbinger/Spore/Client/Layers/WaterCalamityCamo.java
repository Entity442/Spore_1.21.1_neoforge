package com.Harbinger.Spore.Client.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.Fluids;

public class WaterCalamityCamo<T extends LivingEntity, M extends EntityModel<T>>
        extends RenderLayer<T, M> {

    private RenderType cachedRenderType;
    private ResourceLocation cachedTexture;

    private int cachedWaterColor = 0x80000000;
    private long lastColorUpdateTick = -1;

    private static final int COLOR_UPDATE_INTERVAL = 40;

    public WaterCalamityCamo(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack stack,
                       MultiBufferSource bufferSource,
                       int packedLight,
                       T entity,
                       float limbSwing,
                       float limbSwingAmount,
                       float partialTicks,
                       float ageInTicks,
                       float netHeadYaw,
                       float headPitch) {

        Minecraft mc = Minecraft.getInstance();
        Entity camera = mc.getCameraEntity();
        if (camera == null) return;

        // Fast early-outs FIRST
        if (entity.isInvisible()) return;
        if (!entity.isEyeInFluidType(Fluids.WATER.getFluidType())) return;
        if (camera.isEyeInFluidType(Fluids.WATER.getFluidType())) return;

        // --------- WATER COLOR CACHE ---------
        long gameTime = entity.level().getGameTime();
        if (gameTime - lastColorUpdateTick >= COLOR_UPDATE_INTERVAL) {
            lastColorUpdateTick = gameTime;

            int biomeColor = entity.level()
                    .getBiome(entity.blockPosition())
                    .value()
                    .getWaterColor();

            cachedWaterColor = 0x80000000 | (biomeColor & 0x00FFFFFF);
        }

        // --------- RENDER TYPE CACHE ---------
        ResourceLocation texture = getTextureLocation(entity);
        if (cachedRenderType == null || cachedTexture != texture) {
            cachedTexture = texture;
            cachedRenderType = RenderType.entityTranslucent(texture);
        }

        VertexConsumer consumer = bufferSource.getBuffer(cachedRenderType);

        getParentModel().renderToBuffer(
                stack,
                consumer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                cachedWaterColor
        );
    }
}