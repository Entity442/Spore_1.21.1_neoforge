package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Models.ConductorModel;
import com.Harbinger.Spore.Client.Models.LeaperModel;
import com.Harbinger.Spore.Client.Special.BaseInfectedRenderer;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Conductor;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Leaper;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PowerableMob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConductorRenderer<Type extends Conductor> extends BaseInfectedRenderer<Type , ConductorModel<Type>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/conductor.png");
    private static final ResourceLocation EYES_TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/eyes/conductor.png");

    public ConductorRenderer(EntityRendererProvider.Context context) {
        super(context, new ConductorModel<>(context.bakeLayer(ConductorModel.LAYER_LOCATION)), 0.5f);
        addLayer(new ElectricalOverlayLayer<>(this,context.getModelSet()));
    }


    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }


    @Override
    public ResourceLocation eyeLayerTexture() {
        return EYES_TEXTURE;
    }

    private static class ElectricalOverlayLayer<T extends Conductor, M extends ConductorModel<T>> extends RenderLayer<T, M> {
        private final ConductorModel<T> model;
        private static final ResourceLocation POWER_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/creeper/creeper_armor.png");
        public ElectricalOverlayLayer(RenderLayerParent<T, M> renderer, EntityModelSet modelSet) {
            super(renderer);
            model = new ConductorModel<>(modelSet.bakeLayer(ConductorModel.LAYER_LOCATION));
        }

        public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            float f = (float)livingEntity.tickCount + partialTicks;
            this.getParentModel().copyPropertiesTo(model);
            model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            ModelPart part = model.MainBrain;
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.energySwirl(POWER_LOCATION, livingEntity.tickCount * 0.01F % 1.0F, f * 0.01F % 1.0F));
            part.render(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, -8355712);
        }
    }
}