package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Models.*;
import com.Harbinger.Spore.Client.Models.KrakenTentacles.ChainModel;
import com.Harbinger.Spore.Client.Special.BaseInfectedRenderer;
import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.AmbientSparks;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Conductor;
import com.Harbinger.Spore.Sentities.Variants.ConductorVariants;
import com.Harbinger.Spore.Spore;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ConductorRenderer<Type extends Conductor> extends BaseInfectedRenderer<Type , EntityModel<Type>> {
    private final EntityModel<Type> metalChains = new ChainModel<>();
    private final ItemInHandRenderer itemInHandRenderer;
    protected List<Vec3> entities = new ArrayList<>();
    public static final Map<ConductorVariants, ResourceLocation> TEXTURE =
            Util.make(Maps.newEnumMap(ConductorVariants.class), (p_114874_) -> {
                p_114874_.put(ConductorVariants.DEFAULT,
                         ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/conductor.png"));
                p_114874_.put(ConductorVariants.CHAINED,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/chained_conductor.png"));
                p_114874_.put(ConductorVariants.SURGE,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/surge_conductor.png"));
                p_114874_.put(ConductorVariants.CHAINER,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/chainer_conductor.png"));
                p_114874_.put(ConductorVariants.MAGNETO,
                        ResourceLocation.fromNamespaceAndPath(Spore.MODID, "textures/entity/magnetic_conductor.png"));
            });
    public static final ResourceLocation CHAIN_LOCATION =ResourceLocation.fromNamespaceAndPath(Spore.MODID,"textures/entity/chains_rusted.png");
    private final EntityModel<Type> defModel = getModel();
    private final EntityModel<Type> chained;
    private final EntityModel<Type> surge;
    private final EntityModel<Type> chainer;
    private final EntityModel<Type> magneto;
    private int chainHeight = 0;

    public EntityModel<Type> getModel(int i){
        return switch (i) {
            case 1 -> chained;
            case 2 -> surge;
            case 3 -> chainer;
            case 4 -> magneto;
            default -> defModel;
        };
    }

    private static final ResourceLocation EYES_TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/eyes/conductor.png");
    private final EntityModel<Type> chains = new BoltBit<>();
    private AmbientSparks attackSpark = null;
    public ConductorRenderer(EntityRendererProvider.Context context) {
        super(context, new ConductorModel<>(context.bakeLayer(ConductorModel.LAYER_LOCATION)), 0.5f);
        addLayer(new ElectricalOverlayLayer<>(this));
        chained = new ChainedConductorModel<>(context.bakeLayer(ChainedConductorModel.LAYER_LOCATION));
        surge = new SurgeConductorModel<>(context.bakeLayer(SurgeConductorModel.LAYER_LOCATION));
        chainer = new ChainerConductorModel<>(context.bakeLayer(ChainerConductorModel.LAYER_LOCATION));
        magneto = new MagnetoConductorModel<>(context.bakeLayer(MagnetoConductorModel.LAYER_LOCATION));
        itemInHandRenderer = context.getItemInHandRenderer();
    }



    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE.get(entity.getVariant());
    }


    @Override
    public ResourceLocation eyeLayerTexture() {
        return EYES_TEXTURE;
    }

    private static class ElectricalOverlayLayer<T extends Conductor, M extends EntityModel<T>> extends RenderLayer<T, M> {
        private static final ResourceLocation POWER_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/creeper/creeper_armor.png");
        public ElectricalOverlayLayer(RenderLayerParent<T, M> renderer) {
            super(renderer);
        }

        public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            EntityModel<T> entityModel = getParentModel();
            if (!(entityModel instanceof ConductorBrain brain)){
                return;
            }
            float charge = livingEntity.getCharge();
            if (charge <= 0){
                return;
            }
            float f = ((float)livingEntity.tickCount + partialTicks);
            ModelPart part = brain.getBrain();
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.energySwirl(POWER_LOCATION, livingEntity.tickCount * 0.01F % 1.0F, f * 0.01F % 1.0F));
            part.render(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY,-1);
        }
    }

    @Override
    public void render(Type type, float value1, float partial, PoseStack stack, MultiBufferSource bufferSource, int light) {
        model = getModel(type.getTypeVariant());
        super.render(type, value1, partial, stack, bufferSource, light);
        Vec3 entityPos = type.getPosition(partial);
        stack.pushPose();
        stack.translate(-entityPos.x, -entityPos.y, -entityPos.z);
        if (type.getTypeVariant() == 4){
            stack.pushPose();
            for (Conductor.FloatingItem item : type.getItemVectors()){
                Vec3 vec3 = item.getPosition();
                if (vec3 == null) {
                    continue;
                }

                stack.pushPose();
                stack.translate(vec3.x, vec3.y, vec3.z);
                if (item.isAttacking()){
                    float t = (type.tickCount + partial) * 0.2f;
                    t = t % (float)(Math.PI * 2);
                    Vec3 to = item.getPosition();
                    Vec3 from = item.getPrevPosition();
                    if (to == null || from == null){
                        stack.popPose();
                        continue;
                    }
                    Vec3 direction = to.subtract(from);
                    direction = direction.normalize();
                    float yaw = (float) Math.atan2(direction.x, direction.z);
                    float pitch = (float) -Math.asin(direction.y);
                    stack.mulPose(Axis.YP.rotation(yaw + 180));
                    stack.mulPose(Axis.XP.rotation(pitch + t));
                }else {
                    float t = (type.tickCount + partial) * 0.05f;
                    t = t % (float)(Math.PI * 2);
                    stack.mulPose(Axis.YP.rotation(t));
                }

                itemInHandRenderer.renderItem(type,item.getStack(), ItemDisplayContext.THIRD_PERSON_LEFT_HAND,true,stack,bufferSource,light);
                stack.popPose();
            }
            stack.popPose();
            stack.popPose();
            return;
        }
        if (type.getTypeVariant() == 1){
            Entity entity = type.level().getEntity(type.getAttackedId());
            if (entity == null){
                stack.popPose();
                chainHeight = 0;
                return;
            }
            applyIK(partial,type,entity.getPosition(partial).add(0,1,0));
            stack.pushPose();
            {
                stack.translate(0, 0.2, 0);
                renderChain(stack,light,bufferSource);
            }
            stack.popPose();
            stack.popPose();
            return;
        }


        if (type.getSparks().isEmpty()){
            stack.popPose();
            return;
        }

        {
            for (AmbientSparks sparks : type.getSparks()){
                renderChain(sparks.getConnections(),stack,light,bufferSource,false);
            }
            int targetId = type.getAttackedId();
            Entity e = type.level().getEntity(targetId);
            if(e != null && type.getBeamTicks() >= 19){
                Vec3 vec3 = Utilities.generatePositionAway(type.getPosition(partial),4);
                attackSpark = new AmbientSparks(vec3, e, type,20);
            }
            if (attackSpark != null && attackSpark.life < attackSpark.maxLife){
                attackSpark.TickSpark();
                renderChain(attackSpark.getConnections(),stack,light,bufferSource,true);
            }else {
                attackSpark = null;
            }
        }
        stack.popPose();
    }

    private void renderChain(List<Vec3> entities, PoseStack stack, int light, MultiBufferSource buffer,boolean extra) {
        if (entities == null || entities.size() < 2) return;
        Vec3 origin = null;

        for (Vec3 currentPos : entities) {
            renderConnection(origin, currentPos, light, stack, buffer,extra);
            origin = currentPos;
        }
    }
    private void renderConnection(Vec3 from, Vec3 to,int light, PoseStack stack, MultiBufferSource buffer,boolean extra) {
        if (from == null || to == null) return;
        Vec3 direction = to.subtract(from);
        float length = (float) direction.length();
        if (length < 0.0001f) return;
        float thickness = extra ? 2 : 1;
        direction = direction.normalize();

        float yaw = (float) Math.atan2(direction.x, direction.z);
        float pitch = (float) -Math.asin(direction.y);
        stack.pushPose();
        {
            stack.translate(from.x, from.y, from.z);
            stack.mulPose(Axis.YP.rotation(yaw));
            stack.mulPose(Axis.XP.rotation(pitch));
            stack.pushPose();
            {
                stack.mulPose(Axis.XP.rotationDegrees(90));
                stack.translate(0,-length/2,0);
                stack.scale(thickness,length*1.05f,thickness);
                VertexConsumer consumer = buffer.getBuffer(RenderType.lightning());
                renderBit(stack,light,consumer);
            }
            stack.popPose();
        }
        stack.popPose();
    }

    public void renderBit(PoseStack stack,int light,VertexConsumer consumer){
        chains.renderToBuffer(stack,consumer,light, OverlayTexture.NO_OVERLAY, -1);
    }

    public static class BoltBit<T extends Entity> extends EntityModel<T> {
        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "boltbit"), "main");
        private final ModelPart bone;

        public BoltBit() {
            ModelPart root = createBodyLayer().bakeRoot();
            this.bone = root.getChild("bone");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -16.0F, -0.5F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

            return LayerDefinition.create(meshdefinition, 32, 32);
        }

        @Override
        public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
            bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
        }
    }



    protected void moveSegmentTowards(int index, Vec3 target,Type t) {
        Vec3 vec3 = target;
        if (t.level().getBlockState(BlockPos.containing(vec3)).isAir()){
            vec3 = vec3.lerp(vec3.add(0,-1,0),0.1);
        }
        entities.set(index,vec3);
    }
    public void applyIK(float partial, Type t, Vec3 camera) {
        Vec3 basePos = t.getPosition(partial).add(0,1.5,0);
        rebuildChain(basePos, camera);
        if (entities == null || entities.size() < 3) return;
        moveSegmentTowards(entities.size()-1, camera,t);
        for (int i = entities.size() - 2; i >= 0; i--) {
            Vec3 nextPos = entities.get(i + 1);
            Vec3 dir = entities.get(i).subtract(nextPos);

            float segmentLength = 1.0f;
            if (dir.lengthSqr() > 0.0001f) {
                dir = dir.normalize().scale(segmentLength);
            } else {
                dir = new Vec3(segmentLength, 0, 0);
            }

            Vec3 solvedPos = nextPos.add(dir);
            moveSegmentTowards(i, solvedPos,t);
        }
        entities.set(0,basePos);

        for (int i = 1; i < entities.size(); i++) {
            Vec3 prevPos = entities.get(i - 1);
            Vec3 dir = entities.get(i).subtract(prevPos);

            float segmentLength = 1.0f;
            if (dir.lengthSqr() > 0.0001f) {
                dir = dir.normalize().scale(segmentLength);
            } else {
                dir = new Vec3(segmentLength, 0, 0);
            }

            Vec3 solvedPos = prevPos.add(dir);
            moveSegmentTowards(i, solvedPos,t);
        }
    }
    private void rebuildChain(Vec3 start, Vec3 end){

        double distance = start.distanceTo(end);

        int desiredSegments = Mth.clamp((int)(distance * 1.5), 4, 40);
        if (desiredSegments > chainHeight){
            chainHeight = desiredSegments;
        } else if (desiredSegments < chainHeight) {
            return;
        }
        if(desiredSegments == entities.size()) return;

        entities.clear();

        for(int i = 0; i < desiredSegments; i++){

            double t = (double)i/(desiredSegments-1);

            entities.add(new Vec3(
                    Mth.lerp(t,start.x,end.x),
                    Mth.lerp(t,start.y,end.y),
                    Mth.lerp(t,start.z,end.z)
            ));
        }
    }
    private void renderChain(PoseStack stack, int light, MultiBufferSource buffer) {
        if (entities == null || entities.size() < 2) return;
        Vec3 origin = null;

        for (int i = 0; i < entities.size(); i++) {
            Vec3 currentPos = entities.get(i);
            renderConnection(origin, currentPos,light, stack, buffer, i);
            origin = currentPos;
        }
    }
    private void renderConnection(Vec3 from, Vec3 to,int light, PoseStack stack, MultiBufferSource buffer,int index) {
        if (from == null || to == null) return;
        Vec3 direction = to.subtract(from);
        float length = (float) direction.length();
        if (length < 0.0001f) return;

        direction = direction.normalize();

        float yaw = (float) Math.atan2(direction.x, direction.z);
        float pitch = (float) -Math.asin(direction.y);
        float size = index % 2 == 0 ? 1.2f : 1;
        stack.pushPose();
        {
            stack.translate(from.x, from.y, from.z);
            stack.mulPose(Axis.YP.rotation(yaw));
            stack.mulPose(Axis.XP.rotation(pitch));
            stack.pushPose();
            {
                VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(CHAIN_LOCATION));
                stack.mulPose(Axis.XP.rotationDegrees(90));
                stack.translate(0,-length/2,0);
                stack.scale(size,length*1.05f,size);
                metalChains.renderToBuffer(stack,consumer,light, OverlayTexture.NO_OVERLAY,-1);
            }
            stack.popPose();
        }
        stack.popPose();
    }
}