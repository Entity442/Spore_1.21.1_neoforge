package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Models.*;
import com.Harbinger.Spore.Client.Special.BaseInfectedRenderer;
import com.Harbinger.Spore.Sentities.Hyper.Berserker;
import com.Harbinger.Spore.Spore;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.ClientHooks;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class BerserkerRenderer<Type extends Berserker> extends BaseInfectedRenderer<Type , EntityModel<Type>> {
    private final EntityModel<Type>  defModel = this.getModel();
    private final EntityModel<Type> chargeModel = new BerserkerChargingModel<>();
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/beserker.png");
    private static final ResourceLocation EYES_TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/empty.png");
    public BerserkerRenderer(EntityRendererProvider.Context context) {
        super(context, new BeserkerModel<>(context.bakeLayer(BeserkerModel.LAYER_LOCATION)), 0.5f);
        addLayer(new BeserkerArmorLayer<>(this, context.getModelManager()));
    }
    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation eyeLayerTexture() {
        return EYES_TEXTURE;
    }

    @Override
    protected boolean isShaking(Type type) {
        return super.isShaking(type) || (type.goesBerserk() && type.isAggressive());
    }

    @Override
    public void render(Type type, float value1, float value2, PoseStack stack, MultiBufferSource bufferSource, int light) {
        model =type.getCharge() || type.getCrouch() ? chargeModel : defModel;
        super.render(type, value1, value2, stack, bufferSource, light);
    }

    public static class BeserkerArmorLayer<T extends Berserker> extends RenderLayer<T, EntityModel<T>> {
        private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
        private final TextureAtlas armorTrimAtlas;
        private static final ResourceLocation BLOOD_LAYER1 = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
                "textures/overlay/blood_overlay.png");
        private static final ResourceLocation BLOOD_LAYER2 = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
                "textures/overlay/blood_overlay_2.png");
        public BeserkerArmorLayer(RenderLayerParent<T, EntityModel<T>> renderLayerParent, ModelManager manager) {
            super(renderLayerParent);
            armorTrimAtlas = manager.getAtlas(Sheets.ARMOR_TRIMS_SHEET);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
            if (!(getParentModel() instanceof BerserkerBits bits)){
                return;
            }
            renderArmorPart(entity,EquipmentSlot.HEAD,bits.HeadWear(),bits.HeadList(),poseStack,bufferSource, packedLight);
            renderArmorPart(entity,EquipmentSlot.CHEST,bits.Chest(),bits.ChestList(),poseStack,bufferSource, packedLight);
            renderArmorPart(entity,EquipmentSlot.LEGS,bits.RightLeg(),bits.RightLegList(),poseStack,bufferSource, packedLight);
            renderArmorPart(entity,EquipmentSlot.LEGS,bits.LeftLeg(),bits.LeftLegList(),poseStack,bufferSource, packedLight);
        }


        private void renderArmorPart(T entity, EquipmentSlot slot , ModelPart arm, List<ModelPart> path, PoseStack stack, MultiBufferSource bufferSource, int packedLight){
            ItemStack itemStack = entity.getItemBySlot(slot);
            boolean flag = itemStack.hasFoil();
            if (itemStack.getItem() instanceof ArmorItem armorItem){
                stack.pushPose();
                for (ModelPart modelPart : path){
                    modelPart.translateAndRotate(stack);
                }
                arm.visible = true;
                ArmorMaterial armormaterial = armorItem.getMaterial().value();
                renderArmor(arm,slot,stack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,-1,this.getArmorResource(entity, itemStack, armormaterial,slot),flag);
                ArmorTrim armortrim = (ArmorTrim)itemStack.get(DataComponents.TRIM);
                if (armortrim != null) {
                    this.renderTrim(armorItem.getMaterial(),arm, stack, bufferSource, packedLight, armortrim);
                }
                stack.popPose();
            }
        }
        private void renderArmor(ModelPart part,EquipmentSlot slot, PoseStack stack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, int alpha, ResourceLocation location, boolean glint){
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(location));
            part.render(stack, consumer, packedLight, packedOverlay,  alpha);
            if (glint){
                part.render(stack, bufferSource.getBuffer(RenderType.entityGlint()), packedLight, packedOverlay,  alpha);
            }
            renderBloodLayer(part,slot,stack,bufferSource,packedLight);
        }
        private void renderTrim(Holder<ArmorMaterial> armorMaterialHolder, ModelPart part, PoseStack stack, MultiBufferSource source, int light, ArmorTrim armorTrim) {
            TextureAtlasSprite textureatlassprite = this.armorTrimAtlas.getSprite(armorTrim.outerTexture(armorMaterialHolder));
            VertexConsumer vertexconsumer = textureatlassprite.wrap(source.getBuffer(Sheets.armorTrimsSheet(armorTrim.pattern().value().decal())));
            part.render(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, -1);
        }

        private void renderBloodLayer(ModelPart part,EquipmentSlot slot, PoseStack stack, MultiBufferSource bufferSource, int packedLight){
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(slot == EquipmentSlot.LEGS ? BLOOD_LAYER2 : BLOOD_LAYER1));
            part.render(stack,consumer,packedLight,OverlayTexture.NO_OVERLAY);
        }

        public ResourceLocation getArmorResource(Entity entity, ItemStack stack,ArmorMaterial material, EquipmentSlot slot) {
            if (material.layers().isEmpty()){
                return EYES_TEXTURE;
            }
            return ClientHooks.getArmorTexture(entity, stack, material.layers().getFirst(),slot == EquipmentSlot.LEGS, slot);
        }
    }


}