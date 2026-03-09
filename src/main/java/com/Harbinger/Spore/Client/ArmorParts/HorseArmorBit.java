package com.Harbinger.Spore.Client.ArmorParts;

import com.Harbinger.Spore.Client.Layers.CustomHorseArmorLayer;
import com.Harbinger.Spore.Client.Models.FleshHorseArmorModel;
import com.Harbinger.Spore.Sitems.BaseWeapons.SporeArmorData;
import com.Harbinger.Spore.Sitems.CustomModelArmorData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HorseArmorBit{
    public final Item item;
    private  final FleshHorseArmorModel<AbstractHorse> fleshHorseArmorModel = new FleshHorseArmorModel<>();
    public HorseArmorBit(Item item) {
        this.item = item;
    }
    public EntityModel<AbstractHorse> getModel(){
        return fleshHorseArmorModel;
    }
    protected VertexConsumer consumer(MultiBufferSource source, CustomModelArmorData data, CustomHorseArmorLayer.HorseHandlerModel<AbstractHorse> model, AbstractHorse livingEntity){
        return ItemRenderer.getFoilBufferDirect(source, model.renderType(data.getTextureLocation()), false, livingEntity.getBodyArmorItem().hasFoil());
    }
    public void tickMovement(AbstractHorse livingEntity, PoseStack poseStack, CustomHorseArmorLayer.HorseHandlerModel<AbstractHorse> model, int light, MultiBufferSource buffer){
        ItemStack itemStack = livingEntity.getBodyArmorItem();
        if (itemStack.isEmpty()) {
            return;
        }
        int color;
        if (itemStack.getItem() instanceof SporeArmorData armorData){
            color = armorData.getVariant(itemStack).getColor();
        } else {
            color = -1;
        }
        if (itemStack.getItem() instanceof CustomModelArmorData armorData && itemStack.getItem().equals(item)){
            VertexConsumer consumer = consumer(buffer,armorData,model,livingEntity);
            ModelPart head = fleshHorseArmorModel.Neck;
            ModelPart body = fleshHorseArmorModel.Body;
            ModelPart rightFront = fleshHorseArmorModel.FrontRightLeg;
            ModelPart leftFront = fleshHorseArmorModel.FrontLeftLeg;
            ModelPart rightBack = fleshHorseArmorModel.BackRightLeg;
            ModelPart leftBack = fleshHorseArmorModel.BackLeftLeg;
            applyTransformEx(poseStack,model.getHead(),0,-0.5f,0.65f,1.1f,0,0,0,() -> {
                head.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
            });
            applyTransformEx(poseStack,model.getBody(),0,-0.71f,-0.35f,1.05f,0,0,0,() -> {
                body.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
            });
            applyTransformEx(poseStack,model.getRightFrontLeg(),0,-1f,0.45f,1.05f,0,0,0,() -> {
                rightFront.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
            });
            applyTransformEx(poseStack,model.getLeftFrontLeg(),0,-1f,0.45f,1.05f,0,0,0,() -> {
                leftFront.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
            });
            applyTransformEx(poseStack,model.getRightHindLeg(),0,-1f,-0.45f,1.05f,0,0,0,() -> {
                rightBack.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
            });
            applyTransformEx(poseStack,model.getLeftHindLeg(),0,-1f,-0.45f,1.05f,0,0,0,() -> {
                leftBack.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
            });
        }
    }

    protected void applyTransformEx(PoseStack poseStack, ModelPart origin, float x, float y, float z, float scale, float xSpin, float ySpin, float ZSpin, Runnable render) {
        poseStack.pushPose();
        origin.translateAndRotate(poseStack);
        poseStack.translate(x, y, z);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Axis.XP.rotationDegrees(xSpin));
        poseStack.mulPose(Axis.YP.rotationDegrees(ySpin));
        poseStack.mulPose(Axis.ZP.rotationDegrees(ZSpin));
        render.run();
        poseStack.popPose();
    }

}