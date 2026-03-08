package com.Harbinger.Spore.Client.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class CustomHorseArmorLayer<E extends AbstractHorse,M extends HorseModel<E>> extends RenderLayer<E, M> {
    private final HorseHandlerModel<E> origin;
    public CustomHorseArmorLayer(RenderLayerParent<E, M> parent, ModelPart root) {
        super(parent);
        origin = new HorseHandlerModel<>(root);
    }
    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int light, E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float v5) {
        getParentModel().copyPropertiesTo(origin);
        handleArmorPartsRendering(entity,poseStack,light,buffer,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
    }
    protected void handleArmorPartsRendering(E entity,PoseStack poseStack,int light ,MultiBufferSource buffer,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){

    }


    public static class HorseHandlerModel<T extends AbstractHorse> extends HorseModel<T>{
        private final ModelPart rightFrontLeg;
        private final ModelPart leftFrontLeg;
        private final ModelPart rightHindLeg;
        private final ModelPart leftHindLeg;
        public HorseHandlerModel(ModelPart root) {
            super(root);
            this.rightFrontLeg = root.getChild("right_front_leg");
            this.leftFrontLeg = root.getChild("left_front_leg");
            this.rightHindLeg = root.getChild("right_hind_leg");
            this.leftHindLeg = root.getChild("left_hind_leg");
        }

        public ModelPart getHead(){
            return headParts;
        }
        public ModelPart getBody(){
            return body;
        }
        public ModelPart getRightFrontLeg() {
            return this.rightFrontLeg;
        }

        public ModelPart getLeftFrontLeg() {
            return this.leftFrontLeg;
        }

        public ModelPart getRightHindLeg() {
            return this.rightHindLeg;
        }

        public ModelPart getLeftHindLeg() {
            return this.leftHindLeg;
        }
    }

    public abstract class HorseArmorBit{
        public final Item item;
        public final Supplier<EntityModel<LivingEntity>> model;
        public final float x;
        public final float y;
        public final float z;
        public final float expand;
        public final float Xspin;
        public final float Yspin;
        public final float Zspin;

        protected HorseArmorBit(Item item, Supplier<EntityModel<LivingEntity>> model, float x, float y, float z, float expand, float xspin, float yspin, float zspin) {
            this.item = item;
            this.model = model;
            this.x = x;
            this.y = y;
            this.z = z;
            this.expand = expand;
            Xspin = xspin;
            Yspin = yspin;
            Zspin = zspin;
        }

        public void tickMovement(AbstractHorse livingEntity, PoseStack poseStack, HorseHandlerModel<AbstractHorse> model, int light, MultiBufferSource buffer){

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
}