package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Models.*;
import com.Harbinger.Spore.Client.Special.BaseInfectedRenderer;
import com.Harbinger.Spore.Client.Special.GargoyleBits;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Gargoyl;
import com.Harbinger.Spore.Sentities.Variants.GargoyleVariants;
import com.Harbinger.Spore.Sentities.Variants.ProtectorVariants;
import com.Harbinger.Spore.Spore;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
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
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.ClientHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class GargoyleRenderer<Type extends Gargoyl> extends BaseInfectedRenderer<Type , EntityModel<Type>> {
    private static final ResourceLocation EMPTY = ResourceLocation.parse("spore:textures/entity/empty.png");
    private static final ResourceLocation EYES_TEXTURE =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/eyes/gargoyle.png");
    public static final Map<GargoyleVariants, ResourceLocation> TEXTURE =
            Util.make(Maps.newEnumMap(GargoyleVariants.class), (p_114874_) -> {
                p_114874_.put(GargoyleVariants.DEFAULT,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/gargoyle.png"));
                p_114874_.put(GargoyleVariants.ICHOR,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/bile_gargoyle.png"));
                p_114874_.put(GargoyleVariants.BLOOMING,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/blooming_gargoyle.png"));
                p_114874_.put(GargoyleVariants.BOMBER,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/bomber_gargoyle.png"));
                p_114874_.put(GargoyleVariants.VALKYRIE,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/valk_gargoyle.png"));
            });
    private final EntityModel<Type> defaultModel = this.getModel();
    private final EntityModel<Type> ichor;
    private final EntityModel<Type> blooming;
    private final EntityModel<Type> bomber;
    private final EntityModel<Type> valk;
    public GargoyleRenderer(EntityRendererProvider.Context context) {
        super(context, new gargoyleModel<>(context.bakeLayer(gargoyleModel.LAYER_LOCATION),false), 0.5f);
        ichor = new IchorGargoyleModel<>(context.bakeLayer(IchorGargoyleModel.LAYER_LOCATION),false);
        blooming = new bloomingGargoyleModel<>(context.bakeLayer(bloomingGargoyleModel.LAYER_LOCATION),false);
        bomber = new bomberGargoyleModel<>(context.bakeLayer(bomberGargoyleModel.LAYER_LOCATION),false);
        valk = new valkyrieGargoyleModel<>(context.bakeLayer(valkyrieGargoyleModel.LAYER_LOCATION),false);
        this.addLayer(new ProtectorArmorRenderer<>(this,context.getModelManager()));
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
    protected @Nullable RenderType getRenderType(Type livingEntity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return super.getRenderType(livingEntity, bodyVisible, livingEntity.getVariant() == GargoyleVariants.ICHOR, glowing);
    }

    @Override
    protected void scale(Type livingEntity, PoseStack poseStack, float partialTickTime) {
        float val = livingEntity.getVariant() == GargoyleVariants.VALKYRIE ? 1.2f : 1;
        poseStack.scale(val,val,val);
        super.scale(livingEntity, poseStack, partialTickTime);
    }
    public EntityModel<Type> getVariantModel(GargoyleVariants gargoyleVariants){
        switch (gargoyleVariants){
            case ICHOR -> {
                return ichor;
            }
            case BLOOMING -> {
                return blooming;
            }
            case BOMBER -> {
                return bomber;
            }
            case VALKYRIE -> {
                return valk;
            }
            case DEFAULT -> {
                return defaultModel;
            }
        }
        return defaultModel;
    }

    @Override
    public void render(Type type, float value1, float value2, PoseStack stack, MultiBufferSource bufferSource, int light) {
        this.model = getVariantModel(type.getVariant());
        super.render(type, value1, value2, stack, bufferSource, light);
    }

    private static class ProtectorArmorRenderer <T extends Gargoyl> extends RenderLayer<T, EntityModel<T>> {
        private final TextureAtlas armorTrimAtlas;
        private static final ResourceLocation BLOOD_LAYER1 = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
                "textures/overlay/blood_overlay.png");
        public ProtectorArmorRenderer(RenderLayerParent<T, EntityModel<T>> modelRenderLayerParent, ModelManager manager) {
            super(modelRenderLayerParent);
            armorTrimAtlas = manager.getAtlas(Sheets.ARMOR_TRIMS_SHEET);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T t, float v, float v1, float v2, float v3, float v4, float v5) {
            if (getParentModel() instanceof GargoyleBits gargoyleBits){
                renderArmorPart(t,gargoyleBits,EquipmentSlot.HEAD, gargoyleBits.Helmet(),poseStack,multiBufferSource, i);
            }
        }
        private void renderArmorPart(T entity, GargoyleBits bits, EquipmentSlot slot , List<ModelPart> parts, PoseStack stack, MultiBufferSource bufferSource, int packedLight){
            ItemStack itemStack = entity.getItemBySlot(slot);
            boolean flag = itemStack.hasFoil();
            if (itemStack.getItem() instanceof ArmorItem armorItem){
                ArmorMaterial armormaterial = armorItem.getMaterial().value();
                renderArmor(parts,bits,stack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,-1,this.getArmorResource(entity, itemStack,armormaterial, slot),flag);
                ArmorTrim armortrim = (ArmorTrim)itemStack.get(DataComponents.TRIM);
                if (armortrim != null) {
                    this.renderTrim(armorItem.getMaterial(), bits, stack, bufferSource, packedLight, armortrim, parts);
                }
            }
        }
        private void renderArmor(List<ModelPart> parts,GargoyleBits bits, PoseStack stack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, int alpha,ResourceLocation location,boolean glint){
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(location));
            ModelPart root = bits.root();
            root.getAllParts().forEach(modelPart -> {setInvisible(modelPart,parts);});
            root.render(stack, consumer, packedLight, packedOverlay,  alpha);
            if (glint){
                root.render(stack, bufferSource.getBuffer(RenderType.entityGlint()), packedLight, packedOverlay,  alpha);
            }
            renderBloodLayer(root,stack,bufferSource,packedLight);
        }

        private void setInvisible(ModelPart part,List<ModelPart> parts){
            part.skipDraw = !parts.contains(part);
        }


        private void renderTrim(Holder<ArmorMaterial> armorMaterialHolder,GargoyleBits bits, PoseStack stack, MultiBufferSource source, int light, ArmorTrim armorTrim, List<ModelPart> parts) {
            TextureAtlasSprite textureatlassprite = this.armorTrimAtlas.getSprite(armorTrim.outerTexture(armorMaterialHolder));
            VertexConsumer vertexconsumer = textureatlassprite.wrap(source.getBuffer(Sheets.armorTrimsSheet(armorTrim.pattern().value().decal())));
            ModelPart root = bits.root();
            root.getAllParts().forEach(modelPart -> {setInvisible(modelPart,parts);});
            root.render(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, -1);
        }
        public ResourceLocation getArmorResource(Entity entity, ItemStack stack,ArmorMaterial material, EquipmentSlot slot) {
            if (material.layers().isEmpty()){
                return EMPTY;
            }
            return  ClientHooks.getArmorTexture(entity, stack, material.layers().getFirst(),false, slot);
        }
        private void renderBloodLayer(ModelPart part, PoseStack stack, MultiBufferSource bufferSource, int packedLight){
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(BLOOD_LAYER1));
            part.render(stack,consumer,packedLight,OverlayTexture.NO_OVERLAY);
        }
    }

}