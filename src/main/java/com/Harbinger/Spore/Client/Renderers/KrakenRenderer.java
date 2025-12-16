package com.Harbinger.Spore.Client.Renderers;


import com.Harbinger.Spore.Client.Layers.GrakenMembraneLayer;
import com.Harbinger.Spore.Client.Models.GrakensenkerModel;
import com.Harbinger.Spore.Client.Models.KrakenTentacles.*;
import com.Harbinger.Spore.Client.Special.CalamityRenderer;
import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

@OnlyIn(Dist.CLIENT)
public class KrakenRenderer<Type extends Grakensenker> extends CalamityRenderer<Type , EntityModel<Type>> {
    private final KrakenTentacle1<Type> tentacleSegmentModel = new KrakenTentacle1<>();
    private final KrakenTentacle2<Type> tentacleSegmentModel1 = new KrakenTentacle2<>();
    private final KrakenTentacle3<Type> tentacleSegmentModel2 = new KrakenTentacle3<>();
    private final KrakenTentacle4<Type> tentacleSegmentModel3 = new KrakenTentacle4<>();
    private final KrakenTentacle5<Type> tentacleSegmentModel4 = new KrakenTentacle5<>();
    private final KrakenTentacleFoot<Type> foot = new KrakenTentacleFoot<>();
    private final KrakenClaw<Type> armModel = new KrakenClaw<>();
    private static final ResourceLocation TEXTURE =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/graken.png");
    private static final ResourceLocation TENTACLES =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/kraken/kraken_t1.png");
    private static final ResourceLocation KRAKEN_HAND =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/kraken/hand.png");
    private static final ResourceLocation EYES_TEXTURE =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/eyes/graken.png");
    private static final ResourceLocation WATER =  ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/water_vortex.png");

    public KrakenRenderer(EntityRendererProvider.Context context) {
        super(context, new GrakensenkerModel<>(context.bakeLayer(GrakensenkerModel.LAYER_LOCATION)), 4f);
        this.addLayer(new GrakenMembraneLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
    public EntityModel<Type> getTentacleModel(int i){
        return switch (i) {
            case 0 -> tentacleSegmentModel;
            case 1 -> tentacleSegmentModel2;
            case 2 -> tentacleSegmentModel3;
            case 3 -> tentacleSegmentModel4;
            default -> tentacleSegmentModel1;
        };
    }

    @Override
    public ResourceLocation eyeLayerTexture() {
        return EYES_TEXTURE;
    }

    @Override
    public void render(Type entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int light) {
        stack.pushPose();
        stack.translate(0,entity.getExtendedHeight(),0);
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, light);
        stack.popPose();
        Vec3 entityPos = entity.getPosition(partialTicks);
        stack.pushPose();
        {
            stack.translate(-entityPos.x, -entityPos.y, -entityPos.z);
            renderTentacle(stack,entity,light, bufferSource, entity.getBackRightTentacle().getEntities(),entity.getBackRightTentacle().getSegmentVar(), entity,partialTicks,false);
            renderTentacle(stack,entity,light, bufferSource, entity.getBackLeftTentacle().getEntities(),entity.getBackLeftTentacle().getSegmentVar(), entity,partialTicks,false);
            renderTentacle(stack,entity,light, bufferSource, entity.getMiddleLeftTentacle().getEntities(),entity.getMiddleLeftTentacle().getSegmentVar(), entity,partialTicks,false);
            renderTentacle(stack,entity,light, bufferSource, entity.getMiddleRightTentacle().getEntities(),entity.getMiddleRightTentacle().getSegmentVar(), entity,partialTicks,false);
            renderTentacle(stack,entity,light, bufferSource, entity.getFrontLeftTentacle().getEntities(),entity.getFrontLeftTentacle().getSegmentVar(), entity,partialTicks,false);
            renderTentacle(stack,entity,light, bufferSource, entity.getFrontRightTentacle().getEntities(),entity.getFrontRightTentacle().getSegmentVar(), entity,partialTicks,false);
            renderTentacle(stack,entity,light, bufferSource, entity.getRightArmTentacle().getEntities(),entity.getRightArmTentacle().getSegmentVar(), entity,partialTicks,true);
            renderTentacle(stack,entity,light, bufferSource, entity.getLeftArmTentacle().getEntities(),entity.getLeftArmTentacle().getSegmentVar(), entity, partialTicks,true);
            renderFunnel(stack,entity,light, bufferSource, entity.getVortexFunnel().getEntities(),partialTicks);
        }
        stack.popPose();
    }

    private void renderTentacle(PoseStack stack,Type type,int light, MultiBufferSource buffer, Vec3[] segments,int[] var, LivingEntity parent, float partial,boolean arm) {
        if (segments == null || segments.length < 2) return;
        float hurtTime = parent.hurtTime - partial;
        float flashIntensity = 0.0F;
        Vec3 origin = null;
        if (hurtTime > 0) {
            flashIntensity = Math.min(hurtTime / 10.0F, 1.0F);
        }
        float red = 1.0F;
        float green = 1.0F - flashIntensity * 0.5F;
        float blue = 1.0F - flashIntensity * 0.5F;
        int color = packColorARGB(1.0F, red, green, blue);
        for (int i = 0; i < segments.length; i++) {
            Vec3 currentPos = segments[i];
            renderConnection(origin, currentPos,type,light, stack, buffer, i,var[i],partial,color,i == segments.length-1, arm);
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
            ,int color,boolean last,boolean arm) {
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
                EntityModel<Type> typeEntityModel = last && !arm ? foot : getTentacleModel(var);
                stack.mulPose(Axis.XP.rotationDegrees(90));
                stack.translate(0,-length/2,0);
                stack.scale(size,length*1.05f,size);
                typeEntityModel.setupAnim(parent,0,0,parent.tickCount + partial,0,0);
                typeEntityModel.renderToBuffer(stack,consumer,light, OverlayTexture.NO_OVERLAY, color);
            }
            stack.popPose();
            if (arm && last){
                VertexConsumer consumerArm = buffer.getBuffer(RenderType.entityCutoutNoCull(KRAKEN_HAND));
                stack.pushPose();
                stack.mulPose(Axis.XP.rotationDegrees(-90));
                stack.translate(0,-length * 2,0);
                stack.scale(1,length,1);
                armModel.setupAnim(parent,0,0,parent.tickCount + partial,0,0);
                armModel.renderToBuffer(stack,consumerArm,light, OverlayTexture.NO_OVERLAY, color);
                stack.popPose();
            }
        }
        stack.popPose();
    }

    private Ring buildRing(
            Vec3 center,
            Vec3 direction,
            float radius,
            float rotationOffset
    ) {
        Ring ring = new Ring();

        Vec3 up = Math.abs(direction.y) > 0.99
                ? new Vec3(1, 0, 0)
                : new Vec3(0, 1, 0);

        Vec3 right = direction.cross(up).normalize();
        Vec3 forward = right.cross(direction).normalize();

        for (int i = 0; i < 8; i++) {
            // ADD ROTATION OFFSET HERE - this creates the spiral!
            float angle = (i * Mth.TWO_PI / 8f) + rotationOffset;
            float x = Mth.cos(angle);
            float y = Mth.sin(angle);

            Vec3 offset = right.scale(x * radius)
                    .add(forward.scale(y * radius));

            ring.vertices[i] = new Vector3f(
                    (float)(center.x + offset.x),
                    (float)(center.y + offset.y),
                    (float)(center.z + offset.z)
            );

            ring.normals[i] = new Vector3f(
                    (float)offset.x,
                    (float)offset.y,
                    (float)offset.z
            ).normalize();

            ring.uvs[i] = new Vector2f(
                    (float)i / 8f,
                    0
            );
        }

        return ring;
    }

    private void renderFunnel(
            PoseStack stack,
            Type type,
            int light,
            MultiBufferSource buffer,
            Vec3[] segments,
            float partial
    ) {
        if (segments == null || segments.length < 2) return;

        int color = type.level().getBiome(type.getOnPos()).value().getWaterColor();
        int packedColor = color | 0xFF000000;

        Ring previousRing = null;
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(WATER));

        float time = (type.tickCount + partial) * 0.05f;

        for (int i = 1; i < segments.length; i++) {
            Vec3 from = segments[i - 1];
            Vec3 to   = segments[i];
            Vec3 dir  = to.subtract(from).normalize();
            float size = calculateSize(i, segments.length);

            // KEY: Different rotation for each segment!
            float segmentProgress = (float)i / segments.length;
            float rotation = time + (segmentProgress * 4f); // More rotation at the end

            Ring currentRing = buildRing(to, dir, size, rotation);

            if (previousRing != null) {
                // No rotation in stitchRings - rotation already applied in buildRing!
                stitchRings(
                        previousRing,
                        currentRing,
                        consumer,
                        stack.last(),
                        packedColor,
                        light,
                        OverlayTexture.NO_OVERLAY
                );
            }

            previousRing = currentRing;
        }
    }

    private void stitchRings(
            Ring a,
            Ring b,
            VertexConsumer consumer,
            PoseStack.Pose pose,
            int color,
            int light,
            int overlay
    ) {
        for (int i = 0; i < 8; i++) {
            int next = (i + 1) % 8;
            float vA = 0f;
            float vB = 1f;

            // Simple stitching - rotation already in vertices
            consumer.addVertex(pose, a.vertices[i])
                    .setColor(color)
                    .setUv(a.uvs[i].x, vA)
                    .setOverlay(overlay)
                    .setLight(light)
                    .setNormal(pose, a.normals[i].x(), a.normals[i].y(), a.normals[i].z());

            consumer.addVertex(pose, a.vertices[next])
                    .setColor(color)
                    .setUv(a.uvs[next].x, vA)
                    .setOverlay(overlay)
                    .setLight(light)
                    .setNormal(pose, a.normals[next].x(), a.normals[next].y(), a.normals[next].z());

            consumer.addVertex(pose, b.vertices[next])
                    .setColor(color)
                    .setUv(b.uvs[next].x, vB)
                    .setOverlay(overlay)
                    .setLight(light)
                    .setNormal(pose, b.normals[next].x(), b.normals[next].y(), b.normals[next].z());

            consumer.addVertex(pose, b.vertices[i])
                    .setColor(color)
                    .setUv(b.uvs[i].x, vB)
                    .setOverlay(overlay)
                    .setLight(light)
                    .setNormal(pose, b.normals[i].x(), b.normals[i].y(), b.normals[i].z());
        }
    }

    private static class Ring {
        Vector3f[] vertices = new Vector3f[8];
        Vector3f[] normals = new Vector3f[8];
        Vector2f[] uvs = new Vector2f[8];  // ADD THIS
    }

    private float calculateSize(int segmentIndex, int totalSegments) {
        float progress = Mth.clamp(
                (float) segmentIndex / (totalSegments - 1),
                0.0f, 1.0f
        );

        float startSize = 0.5f;
        float endSize = 3f;

        return startSize + (endSize - startSize) * progress
                + 0.3f * Mth.sin(progress * Mth.PI);
    }
}
