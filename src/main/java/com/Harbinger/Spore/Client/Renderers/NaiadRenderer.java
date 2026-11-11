package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Models.*;
import com.Harbinger.Spore.Client.Special.BaseInfectedRenderer;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Naiad;
import com.Harbinger.Spore.Sentities.Variants.NaiadVariants;
import com.Harbinger.Spore.Spore;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class NaiadRenderer<Type extends Naiad> extends BaseInfectedRenderer<Type , EntityModel<Type>> {
    private final EntityModel<Type> defaultModel = this.getModel();
    private final EntityModel<Type> triton;
    private final EntityModel<Type> hippo;
    private final EntityModel<Type> turtle;
    private final EntityModel<Type> blowing;
    private static final ResourceLocation EYES_TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/eyes/naiad.png");
    public static final Map<NaiadVariants, ResourceLocation> TEXTURE =
            Util.make(Maps.newEnumMap(NaiadVariants.class), (p_114874_) -> {
                p_114874_.put(NaiadVariants.DEFAULT,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/naiad.png"));
                p_114874_.put(NaiadVariants.TRITON,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/naiad_triton.png"));
                p_114874_.put(NaiadVariants.TURTLE,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/turtle_naiad.png"));
                p_114874_.put(NaiadVariants.HIPPO,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/hippo_naiad.png"));
                p_114874_.put(NaiadVariants.BLOWING,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/naiad_sing.png"));
            });


    public NaiadRenderer(EntityRendererProvider.Context context) {
        super(context, new NaiadModel<>(context.bakeLayer(NaiadModel.LAYER_LOCATION)), 1f);
        triton = new NaiadTritonModel<>(context.bakeLayer(NaiadTritonModel.LAYER_LOCATION));
        hippo = new HippocampusNaiadModel<>(context.bakeLayer(HippocampusNaiadModel.LAYER_LOCATION));
        turtle = new TurtleNaiadModel<>(context.bakeLayer(TurtleNaiadModel.LAYER_LOCATION));
        blowing = new BlowingNaiadModel<>(context.bakeLayer(BlowingNaiadModel.LAYER_LOCATION));
        addLayer(new NaiadChargeRenderer<>(this));
    }

    public EntityModel<Type> getModel(int var){
        return switch (var) {
            case 1 -> triton;
            case 2 -> turtle;
            case 3 -> hippo;
            case 4 -> blowing;
            default -> defaultModel;
        };
    }

    @Override
    protected void scale(Type type, PoseStack poseStack, float p_115316_) {
        float value =  type.getVariant() == NaiadVariants.TRITON ? 1.2f : 1;
        poseStack.scale(value,value,value);
        super.scale(type, poseStack, p_115316_);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE.get(entity.getVariant());
    }

    @Override
    public ResourceLocation eyeLayerTexture() {
        return EYES_TEXTURE;
    }

    @Override
    public void render(Type type, float value1, float value2, PoseStack stack, MultiBufferSource bufferSource, int light) {
        this.model = getModel(type.getTypeVariant());
        super.render(type, value1, value2, stack, bufferSource, light);
    }

    public static class NaiadChargeRenderer<T extends Naiad> extends RenderLayer<T,EntityModel<T>> {
        private final TridentNaiadCharge<T> model;
        public static final ResourceLocation TEXTURE = ResourceLocation.parse("minecraft:textures/entity/trident_riptide.png");
        public NaiadChargeRenderer(RenderLayerParent<T, EntityModel<T>> renderer) {
            super(renderer);
            model = new TridentNaiadCharge<>();
        }

        @Override
        public void render(PoseStack stack, MultiBufferSource multiBufferSource, int value, T type, float v1, float v2, float v3, float v4, float v5, float v6) {
            if (type.isCharging() && type.getVariant() == NaiadVariants.TRITON){
                stack.pushPose();
                stack.translate(0,0.5,0);
                VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
                this.model.prepareMobModel(type, v1, v2, v3);
                this.model.setupAnim(type, v1, v2, v4, v5, v6);
                this.model.renderToBuffer(stack, vertexconsumer, value, OverlayTexture.NO_OVERLAY,-1);
                stack.popPose();
            }
        }
    }
}
