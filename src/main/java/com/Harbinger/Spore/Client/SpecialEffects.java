package com.Harbinger.Spore.Client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SpecialEffects {
    private static Ring buildRing(
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

    public static void renderFunnel(
            PoseStack stack,
            int light,
            MultiBufferSource buffer,
            Vec3[] segments,
            float partial, int packedColor, float sizeA, ResourceLocation location
    ) {
        if (segments == null || segments.length < 2) return;

        Ring previousRing = null;
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(location));


        for (int i = 1; i < segments.length; i++) {
            Vec3 from = segments[i - 1];
            Vec3 to   = segments[i];
            Vec3 dir  = to.subtract(from).normalize();
            float size = calculateSize(i, segments.length,sizeA);

            // KEY: Different rotation for each segment!
            float segmentProgress = (float)i / segments.length;
            float rotation = partial + (segmentProgress * 4f); // More rotation at the end

            Ring currentRing = buildRing(to, dir, size, rotation);

            if (previousRing != null) {
                // No rotation in stitchRings - rotation already applied in buildRing!
                stitchRings(
                        previousRing,
                        currentRing,
                        consumer,
                        stack,
                        packedColor,
                        light,
                        OverlayTexture.NO_OVERLAY
                );
            }

            previousRing = currentRing;
        }
    }
    public static void renderFunnel(
            PoseStack stack,
            int light,
            MultiBufferSource buffer,
            List<Vec3> segments,
            float partial, int packedColor, float sizeA, float sizeB, ResourceLocation location
    ) {
        if (segments == null || segments.size() < 2) return;

        Ring previousRing = null;
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(location));


        for (int i = 1; i < segments.size(); i++) {
            Vec3 from = segments.get(i - 1);
            Vec3 to   = segments.get(i);
            Vec3 dir  = to.subtract(from).normalize();
            float size = calculateSize(i, segments.size(),sizeA,sizeA,sizeB);

            float segmentProgress = (float)i / segments.size();
            float rotation = partial + (segmentProgress * 4f);
            float alpha = calculateAlpha(segmentProgress);

            int colorWithAlpha = modifyAlpha(packedColor, alpha);
            Ring currentRing = buildRing(to, dir, size, rotation);

            if (previousRing != null) {
                stitchRings(
                        previousRing,
                        currentRing,
                        consumer,
                        stack,
                        colorWithAlpha,
                        light,
                        OverlayTexture.NO_OVERLAY
                );
            }

            previousRing = currentRing;
        }
    }
    private static int modifyAlpha(int packedColor, float alpha) {
        int alphaValue = (int)(alpha * 255);
        // Preserve RGB components, replace alpha
        return (alphaValue << 24) | (packedColor & 0x00FFFFFF);
    }
    private static float calculateAlpha(float progress) {
        return 1.0f - (progress * 0.9f);
    }
    public static class Ring {
        Vector3f[] vertices = new Vector3f[8];
        Vector3f[] normals = new Vector3f[8];
        Vector2f[] uvs = new Vector2f[8];  // ADD THIS
    }

    private static void stitchRings(
            Ring a,
            Ring b,
            VertexConsumer consumer,
            PoseStack stack,
            int color,
            int light,
            int overlay
    ) {
        PoseStack.Pose pose = stack.last();
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

    private static float calculateSize(int segmentIndex, int totalSegments,float inflation) {
        float progress = Mth.clamp(
                (float) segmentIndex / (totalSegments - 1),
                0.0f, 1.0f
        );

        float startSize = 0.5f;
        float endSize = 3f;

        return (startSize + (endSize - startSize) * progress
                + 0.3f * Mth.sin(progress * Mth.PI)) * inflation;
    }
    private static float calculateSize(int segmentIndex, int totalSegments,float inflation,float minInflation,float maxInflation) {
        float progress = Mth.clamp(
                (float) segmentIndex / (totalSegments - 1),
                0.0f, 1.0f
        );


        return (minInflation + (maxInflation - minInflation) * progress
                + 0.3f * Mth.sin(progress * Mth.PI)) * inflation;
    }
}
