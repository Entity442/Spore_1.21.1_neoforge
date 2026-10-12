package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Models.KrakenTentacles.ChainModel;
import com.Harbinger.Spore.Sentities.Projectile.ChainProjectile;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ChainRenderer<T extends ChainProjectile>extends EntityRenderer<T> {
    private final EntityModel<T> metalChains = new ChainModel<>();
    protected List<Vec3> entities = new ArrayList<>();
    public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(Spore.MODID,"textures/entity/chains_rusted.png");
    public ChainRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    public ResourceLocation getTextureLocation(T t) {
        return LOCATION;
    }


    @Override
    public void render(T type, float va, float partial, PoseStack stack, MultiBufferSource bufferSource, int light) {
        super.render(type, va, partial, stack, bufferSource, light);
        Vec3 entityPos = type.getPosition(partial);
        stack.pushPose();
        stack.translate(-entityPos.x, -entityPos.y, -entityPos.z);
        Entity entity = type.level().getEntity(type.getAttackedId());
        if (entity == null){
            stack.popPose();
            return;
        }
        applyIK(partial,type,entity.getPosition(partial).add(0,1.5,0));
        renderChain(stack,light,bufferSource);
        stack.popPose();
    }

    protected void moveSegmentTowards(int index, Vec3 target) {
        entities.set(index,target);
    }
    public void applyIK(float partial, T t, Vec3 camera) {
        Vec3 basePos = t.getPosition(partial).add(0,0.2,0);
        rebuildChain(basePos, camera);
        if (entities == null || entities.size() < 3) return;
        moveSegmentTowards(entities.size()-1, camera);
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
            moveSegmentTowards(i, solvedPos);
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
            moveSegmentTowards(i, solvedPos);
        }
    }
    private void rebuildChain(Vec3 start, Vec3 end){

        double distance = start.distanceTo(end);

        int desiredSegments = Mth.clamp((int)(distance * 1.1), 4, 40);
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
                VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(LOCATION));
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
