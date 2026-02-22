package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Layers.WaterCalamityCamo;
import com.Harbinger.Spore.Client.Models.*;
import com.Harbinger.Spore.Client.Models.KrakenTentacles.*;
import com.Harbinger.Spore.Sentities.BaseEntities.HohlMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.IkUtil.IkLeviLeg;
import com.Harbinger.Spore.Sentities.BaseEntities.LeviathanMultipart;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class LeviathanSegRenderer<Type extends LeviathanMultipart> extends LivingEntityRenderer<Type , EntityModel<Type>> {
    private final EntityModel<Type> mainModel = this.getModel();
    private final EntityModel<Type> middleSeg;
    private final Seg1<Type> tentacleSegmentModel1 = new Seg1<>();
    private final Seg2<Type> tentacleSegmentModel2 = new Seg2<>();
    private final Seg3<Type> tentacleSegmentModel3 = new Seg3<>();
    private final Seg4<Type> tentacleSegmentModel4 = new Seg4<>();
    private final Seg5<Type> tentacleSegmentModel5 = new Seg5<>();
    private final Seg6<Type> tentacleSegmentModel6 = new Seg6<>();
    private final FootSegLevi<Type> foot = new FootSegLevi<>();
    private static final ResourceLocation INNARDS =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/leviathan_insides.png");
    private static final ResourceLocation TEXTURE =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/leviathan_tail.png");
    private static final ResourceLocation TENTACLES =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/kraken/kraken_t1.png");
    public LeviathanSegRenderer(EntityRendererProvider.Context context) {
        super(context, new LeviathanTailModel<>(), 4f);
        middleSeg = new LeviathanMiddleSegment<>();
        this.addLayer(new HohlColors<>(this));
        this.addLayer(new WaterCalamityCamo<>(this));
    }
    public EntityModel<Type> getTentacleModel(int i){
        return switch (i) {
            case 1 -> tentacleSegmentModel2;
            case 2 -> tentacleSegmentModel3;
            case 3 -> tentacleSegmentModel4;
            case 4 -> tentacleSegmentModel5;
            case 5 -> tentacleSegmentModel6;
            default -> tentacleSegmentModel1;
        };
    }
    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }

    @Override
    public void render(Type type, float val1, float val2, PoseStack stack, MultiBufferSource source, int light) {
        model = type.isTail() ? mainModel : middleSeg;
        Vec3 direction = null;
        ClientLevel level = Minecraft.getInstance().level;
        int i = type.getParentIntId();
        if (level != null && i != -1 && !type.isInvisible()){
            Entity parent = level.getEntity(i);
            if (parent != null){
                renderConnection(type,parent,stack,source,val2);
                direction = parent.getPosition(val2).subtract(type.getPosition(val2));
                direction = direction.normalize();
            }
        }
        stack.pushPose();
        if (direction != null){
            float pitch = (float) -Math.asin(direction.y);
            stack.mulPose(Axis.XP.rotation(pitch));
        }
        super.render(type, val1, val2, stack, source, light);
        stack.popPose();
        Vec3 entityPos = type.getPosition(val2);
        stack.pushPose();
        {
            stack.translate(-entityPos.x, -entityPos.y, -entityPos.z);
            if (!type.isInvisible()){
                for (IkLeviLeg leg : type.getLegs()){
                    renderTentacle(stack,type,light, source, leg.getEntities(),leg.getSegmentVar(), type,val2);

                }
            }
        }
        stack.popPose();
    }

    @Override
    protected void scale(Type livingEntity, PoseStack poseStack, float partialTickTime) {
        float size = livingEntity.isTail() ? 1 : 1.3f;
        poseStack.scale(size,size,size);
        super.scale(livingEntity, poseStack, partialTickTime);
    }

    @Override
    protected boolean shouldShowName(Type p_115333_) {
        return false;
    }

    public class HohlColors<T extends LeviathanMultipart, M extends EntityModel<T>> extends RenderLayer<T, M> {
        public HohlColors(RenderLayerParent<T, M> p_117346_) {
            super(p_117346_);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int val, T type, float v, float v1, float v2, float v3, float v4, float v5) {
            if (!type.isInvisible()){
                if (type.getColor() == 0){return;}
                int i = type.getColor();
                int r = (i >> 16) & 0xFF;
                int g = (i >> 8) & 0xFF;
                int b = i & 0xFF;
                int halfColor = (0x80 << 24) | (r << 16) | (g << 8) | b;
                VertexConsumer consumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(getTextureLocation(type)));
                getParentModel().renderToBuffer(poseStack,consumer,val, OverlayTexture.NO_OVERLAY,halfColor);
            }
        }
    }
    private void renderConnection(Type parent, Entity to, PoseStack stack,
                                  MultiBufferSource buffer, float partialTick) {
        boolean adapted = parent.isAdapted();
        Vec3 start = parent.getPosition(partialTick).add(0, (parent.getBbHeight() * 0.3f),0);
        Vec3 end = to.getPosition(partialTick).add(0, (to.getBbHeight() * (adapted ? 0.7 : 0.45f)),0);

        Vec3 direction = end.subtract(start);
        float length = (float)direction.length();
        direction = direction.normalize();

        float yaw = (float)Math.atan2(direction.x, direction.z);
        float pitch = (float)-Math.asin(direction.y);
        int color = parent.getColor() == 0 ? -1 : parent.getColor();
        stack.pushPose();
        {
            Vec3 vec3 = parent.position().subtract(parent.position()).scale(-1);
            stack.translate(vec3.x, vec3.y+1, vec3.z);
            stack.mulPose(Axis.YP.rotation(yaw));
            stack.mulPose(Axis.XP.rotation(pitch));
            float inf = 0.3f;
            float startWidth = parent.getBbWidth()*inf;
            float startHeight = parent.getBbHeight()*inf;
            float endWidth = to.getBbWidth()*inf;
            float endHeight = to.getBbHeight()*inf;

            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(INNARDS));
            PoseStack.Pose pose = stack.last();
            Matrix4f matrix = pose.pose();
            drawTaperedConnection(vertexConsumer, matrix, pose,
                    startWidth, startHeight,  // Start dimensions
                    endWidth, endHeight,      // End dimensions
                    length,                   // Distance between segments
                    OverlayTexture.NO_OVERLAY, 15728880,
                    color);              // RGBA color
        }
        stack.popPose();
    }

    private void drawTaperedConnection(VertexConsumer consumer, Matrix4f pose, PoseStack.Pose realPose,
                                       float startWidth, float startHeight,
                                       float endWidth, float endHeight,
                                       float length,
                                       int overlay, int lightmap,
                                       int packedColor) {

        // Half dimensions
        float hwStart = startWidth / 2f;
        float hhStart = startHeight / 2f;
        float hwEnd   = endWidth / 2f;
        float hhEnd   = endHeight / 2f;

        // Front face
        consumer.addVertex(pose, -hwStart, -hhStart, 0)
                .setColor(packedColor).setUv(0, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,0, 0, -1);
        consumer.addVertex(pose, hwStart, -hhStart, 0)
                .setColor(packedColor).setUv(1, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,0, 0, -1);
        consumer.addVertex(pose, hwStart, hhStart, 0)
                .setColor(packedColor).setUv(1, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 0, 0, -1);
        consumer.addVertex(pose, -hwStart, hhStart, 0)
                .setColor(packedColor).setUv(0, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,0, 0, -1);

        // Back face
        consumer.addVertex(pose, -hwEnd, -hhEnd, length)
                .setColor(packedColor).setUv(0, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,0, 0, 1);
        consumer.addVertex(pose, hwEnd, -hhEnd, length)
                .setColor(packedColor).setUv(1, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,0, 0, 1);
        consumer.addVertex(pose, hwEnd, hhEnd, length)
                .setColor(packedColor).setUv(1, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 0, 0, 1);
        consumer.addVertex(pose, -hwEnd, hhEnd, length)
                .setColor(packedColor).setUv(0, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,0, 0, 1);

        // Top side
        consumer.addVertex(pose, -hwStart, hhStart, 0)
                .setColor(packedColor).setUv(0, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,0, 1, 0);
        consumer.addVertex(pose, hwStart, hhStart, 0)
                .setColor(packedColor).setUv(1, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 0, 1, 0);
        consumer.addVertex(pose, hwEnd, hhEnd, length)
                .setColor(packedColor).setUv(1, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 0, 1, 0);
        consumer.addVertex(pose, -hwEnd, hhEnd, length)
                .setColor(packedColor).setUv(0, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 0, 1, 0);

        // Bottom side
        consumer.addVertex(pose, -hwStart, -hhStart, 0)
                .setColor(packedColor).setUv(0, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,0, -1, 0);
        consumer.addVertex(pose, hwStart, -hhStart, 0)
                .setColor(packedColor).setUv(1, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 0, -1, 0);
        consumer.addVertex(pose, hwEnd, -hhEnd, length)
                .setColor(packedColor).setUv(1, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 0, -1, 0);
        consumer.addVertex(pose, -hwEnd, -hhEnd, length)
                .setColor(packedColor).setUv(0, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 0, -1, 0);

        // Left side
        consumer.addVertex(pose, -hwStart, -hhStart, 0)
                .setColor(packedColor).setUv(0, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,-1, 0, 0);
        consumer.addVertex(pose, -hwStart, hhStart, 0)
                .setColor(packedColor).setUv(1, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,-1, 0, 0);
        consumer.addVertex(pose, -hwEnd, hhEnd, length)
                .setColor(packedColor).setUv(1, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,-1, 0, 0);
        consumer.addVertex(pose, -hwEnd, -hhEnd, length)
                .setColor(packedColor).setUv(0, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, -1, 0, 0);

        // Right side
        consumer.addVertex(pose, hwStart, -hhStart, 0)
                .setColor(packedColor).setUv(0, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 1, 0, 0);
        consumer.addVertex(pose, hwStart, hhStart, 0)
                .setColor(packedColor).setUv(1, 0)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal(realPose, 1, 0, 0);
        consumer.addVertex(pose, hwEnd, hhEnd, length)
                .setColor(packedColor).setUv(1, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,1, 0, 0);
        consumer.addVertex(pose, hwEnd, -hhEnd, length)
                .setColor(packedColor).setUv(0, 1)
                .setOverlay(overlay).setLight(lightmap)
                .setNormal( realPose,1, 0, 0);

    }
    private void renderTentacle(PoseStack stack, Type type, int light, MultiBufferSource buffer, Vec3[] segments, int[] var, LivingEntity parent, float partial) {
        if (segments == null || segments.length < 2) return;
        float hurtTime = parent.hurtTime - partial;
        float flashIntensity = 0.0F;
        int mutationColor = type.getColor() == 0 ? -1 : type.getColor();
        Vec3 origin = null;
        if (hurtTime > 0) {
            flashIntensity = Math.min(hurtTime / 10.0F, 1.0F);
        }
        float baseR = ((mutationColor >> 16) & 0xFF) / 255f;
        float baseG = ((mutationColor >> 8) & 0xFF) / 255f;
        float baseB = (mutationColor & 0xFF) / 255f;
        float flash = flashIntensity * 0.5f;
        float g = Mth.lerp(flash, baseG, 0.2f);
        float b = Mth.lerp(flash, baseB, 0.2f);

        int color = packColorARGB(1.0F, baseR, g, b);
        for (int i = 0; i < segments.length; i++) {
            Vec3 currentPos = segments[i];
            renderConnection(origin, currentPos,type,light, stack, buffer, i,var[i],partial,color,i == segments.length-1);
            origin = currentPos;
        }
    }
    public static int packColorARGB(float a, float r, float g, float b) {
        return ((int)(a * 255) << 24) |
                ((int)(r * 255) << 16) |
                ((int)(g * 255) << 8)  |
                (int)(b * 255);
    }
    private void renderConnection(Vec3 from, Vec3 to,Type parent,int light, PoseStack stack, MultiBufferSource buffer,int index,int var , float partial
            ,int color,boolean last) {
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
                VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(TENTACLES));
                EntityModel<Type> typeEntityModel = last ? foot : getTentacleModel(var);
                stack.mulPose(Axis.XP.rotationDegrees(90));
                stack.translate(0,-length/2,0);
                stack.scale(size,length*1.05f,size);
                typeEntityModel.setupAnim(parent,0,0,parent.tickCount + partial,0,0);
                typeEntityModel.renderToBuffer(stack,consumer,light, OverlayTexture.NO_OVERLAY, color);
            }
            stack.popPose();
        }
        stack.popPose();
    }

}
