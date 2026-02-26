package com.Harbinger.Spore.Client.Models;

import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface TentacledModel {
    default void animateTentacleX(ModelPart part, float value){
        part.xRot = part.getInitialPose().xRot + value;
    }
    default void animateTentacleY(ModelPart part,float value){
        part.yRot = part.getInitialPose().yRot + value;
    }
    default void animateTentacleZ(ModelPart part,float value){
        part.zRot = part.getInitialPose().zRot + value;
    }
    default void animateTumor(ModelPart part,float value){
        part.xScale = 1 + value;
        part.yScale = 1 + value;
        part.zScale = 1 + value;
    }


    class InnerClassIkLeg{
        protected final RandomSource randomSource = RandomSource.create();
        protected final Vec3[] entities;
        protected final Vec3 defaultBodyOffset;
        protected final Vec3 defaultLimbOffset;
        protected final float maxDistance;
        protected final Vec3[] segmentVelocities;
        protected final ModelPart[] parts;
        protected final float[] segmetsL;
        protected Vec3 sitPosition =  null;
        protected Vec3 lastSitPosition = null;
        protected Vec3 lastOwnerPosition = Vec3.ZERO;
        protected Vec3 ownerMovementDelta = Vec3.ZERO;
        protected float lastYaw = 0;
        protected float yawDelta = 0;
        protected float stepAngle = 0f;
        protected final float stepSpeed;
        protected final float stepSize;

        public InnerClassIkLeg(int amount, ModelPart[] parts, int[] segmetsLength, Vec3 defaultBodyOffset,
                               Vec3 defaultLimbOffset,
                               float maxDistance, float stepSpeed, float stepSize) {
            this.entities = new Vec3[amount];
            this.segmentVelocities = new Vec3[amount];
            this.stepSpeed = stepSpeed;
            this.stepSize = stepSize;
            for(int i = 0;i<amount;i++){
                entities[i] = new Vec3(0,0,0);
                segmentVelocities[i] = new Vec3(0, 0, 0);
            }
            this.parts = parts;
            this.segmetsL = new float[segmetsLength.length];
            for(int e = 0;e<segmetsLength.length;e++){
                segmetsL[e] = segmetsLength[e]/16f;
            }
            this.defaultBodyOffset = defaultBodyOffset;
            this.defaultLimbOffset = defaultLimbOffset;
            this.maxDistance = maxDistance;
        }

        public Vec3[] getEntities() {
            return entities;
        }


        public Vec3 applyYaw(LivingEntity owner, Vec3 offset) {
            float yawRad = owner.getYRot() * Mth.DEG_TO_RAD;
            return offset.yRot(-yawRad - Mth.HALF_PI);
        }

        public Vec3 getLegBasePos(LivingEntity owner) {
            Vec3 pivot = owner.position();
            Vec3 extend = isOwnerMoving(owner) ? new Vec3(1,0,0) : Vec3.ZERO;
            return pivot.add(applyYaw(owner,defaultLimbOffset.add(extend)));
        }

        public Vec3 getBodyOffset(LivingEntity owner) {
            Vec3 pivot = owner.position();
            return pivot.add(applyYaw(owner,defaultBodyOffset));
        }

        protected void moveSegmentTowards(int index, Vec3 target,boolean far) {
            Vec3 currentPos = entities[index];
            Vec3 newPos = currentPos.lerp(target,0.35f);
            entities[index] = (far ? target : newPos);
        }
        protected void moveTipTowards(Vec3 target) {
            int tip = entities.length - 1;
            Vec3 currentPos = entities[tip];
            entities[tip] = currentPos.lerp(target, 0.45f);
        }
        protected boolean isOwnerMoving(LivingEntity owner){
            return owner.getDeltaMovement().lengthSqr() > 0.005;
        }

        protected void updateOwnerMovementDelta(LivingEntity owner) {
            Vec3 currentOwnerPos = owner.position();
            ownerMovementDelta = currentOwnerPos.subtract(lastOwnerPosition);
            lastOwnerPosition = currentOwnerPos;
            float currentYaw = owner.getYRot();
            yawDelta = Mth.wrapDegrees(currentYaw - lastYaw);
            lastYaw = currentYaw;
        }
        protected Vec3 rotateAroundYaw(Vec3 pos, Vec3 pivot, float degrees) {
            double rad = degrees * Mth.DEG_TO_RAD;
            Vec3 rel = pos.subtract(pivot);
            Vec3 rotated = rel.yRot((float)-rad);
            return pivot.add(rotated);
        }
        protected void applyBodySpin(LivingEntity owner) {
            if (Math.abs(yawDelta) < 0.001f) return;
            Vec3 pivot = owner.position();

            for (int i = 0; i < entities.length; i++) {
                entities[i] = rotateAroundYaw(entities[i], pivot, yawDelta);
            }

            if (sitPosition != null) {
                sitPosition = rotateAroundYaw(sitPosition, pivot, yawDelta);
            }

            if (lastSitPosition != null) {
                lastSitPosition = rotateAroundYaw(lastSitPosition, pivot, yawDelta);
            }

            for (int i = 0; i < segmentVelocities.length; i++) {
                segmentVelocities[i] =
                        segmentVelocities[i].yRot((float)(-yawDelta * Mth.DEG_TO_RAD));
            }
        }

        protected void applyEntityMovementToLegs(LivingEntity owner) {
            if (ownerMovementDelta.lengthSqr() < 0.00001){
                Vec3 defaultTipPos = getLegBasePos(owner);
                int tip = entities.length - 1;
                Vec3 currentPos = entities[tip];
                entities[tip] = currentPos.lerp(defaultTipPos, 0.1f);
                return;
            }

            int last = entities.length - 1;

            for (int i = 0; i < entities.length-1; i++) {
                float t = (float) i / last;
                float followStrength = Mth.lerp(t, 0.5f, 0.05f);
                float drag = Mth.lerp(t, 0.90f, 0.65f);

                segmentVelocities[i] = segmentVelocities[i]
                        .add(ownerMovementDelta.scale(followStrength));

                segmentVelocities[i] = segmentVelocities[i].scale(drag);

                entities[i] = entities[i].add(segmentVelocities[i]);
            }

            if (sitPosition != null) sitPosition = sitPosition.add(ownerMovementDelta);
            if (lastSitPosition != null) lastSitPosition = lastSitPosition.add(ownerMovementDelta);
        }
        protected Vec3 applyStep(LivingEntity owner,Vec3 baseTipPos) {
            if (!isOwnerMoving(owner)) return baseTipPos;
            stepAngle += stepSpeed;
            stepAngle = Mth.wrapDegrees(stepAngle);

            float rad = stepAngle * Mth.DEG_TO_RAD;
            double x = Math.cos(rad) * stepSize;
            double y = Math.cos(rad) * stepSize * 0.5;
            y = y > 0 ? y : 0;
            Vec3 circularOffset = new Vec3(-x, y, 0);
            circularOffset = applyYaw(owner,circularOffset);
            return baseTipPos.add(circularOffset);
        }

        public void applyIK(Calamity owner, PoseStack stack,float partialTicks,VertexConsumer consumer,int light) {
            if (entities.length < 3) return;

            Vec3 basePos = getBodyOffset(owner);
            Vec3 defaultTipPos = sitPosition == null ? getLegBasePos(owner) : sitPosition;
            updateOwnerMovementDelta(owner);
            applyEntityMovementToLegs(owner);
            applyBodySpin(owner);
            defaultTipPos = applyStep(owner,defaultTipPos);
            moveTipTowards(defaultTipPos);
            for (int i = entities.length - 2; i >= 0; i--) {
                Vec3 nextPos = entities[i + 1];
                Vec3 dir = entities[i].subtract(nextPos);

                float segmentLength = segmetsL[i];
                if (dir.lengthSqr() > 0.0001f) {
                    dir = dir.normalize().scale(segmentLength);
                } else {
                    dir = new Vec3(segmentLength, 0, 0);
                }

                Vec3 solvedPos = nextPos.add(dir);
                moveSegmentTowards(i, solvedPos, entities[i+1].distanceTo(entities[i]) > 5);
            }
            entities[0] = basePos;

            for (int i = 1; i < entities.length; i++) {
                Vec3 prevPos = entities[i - 1];
                Vec3 dir = entities[i].subtract(prevPos);

                float segmentLength = segmetsL[i - 1];
                if (dir.lengthSqr() > 0.0001f) {
                    dir = dir.normalize().scale(segmentLength);
                } else {
                    dir = new Vec3(segmentLength, 0, 0);
                }

                Vec3 solvedPos = prevPos.add(dir);
                moveSegmentTowards(i, solvedPos, entities[i-1].distanceTo(entities[i]) > 5);
            }
            refreshLegStandingPoint(owner);
            handleSegmentUpdating(owner,stack,consumer,light,partialTicks);
            for (Vec3 entity : entities) {
                owner.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, entity.x, entity.y, entity.z, 0, 0.1, 0);
            }
        }
        public static int packColorARGB(float a, float r, float g, float b) {
            return ((int)(a * 255) << 24) |
                    ((int)(r * 255) << 16) |
                    ((int)(g * 255) << 8)  |
                    (int)(b * 255);
        }
        public void handleSegmentUpdating(Calamity owner, PoseStack stack,VertexConsumer consumer,int light, float partialTicks){
            Vec3 origin = null;
            float hurtTime = owner.hurtTime - partialTicks;
            float flashIntensity = 0.0F;
            int mutationColor = owner.getMutationColor() == 0 ? -1 : owner.getMutationColor();
            if (hurtTime > 0) {
                flashIntensity = Math.min(hurtTime / 10.0F, 1.0F);
            }
            float baseR = ((mutationColor >> 16) & 0xFF) / 255f;
            float baseG = ((mutationColor >> 8) & 0xFF) / 255f;
            float baseB = (mutationColor & 0xFF) / 255f;
            float flash = flashIntensity * 0.5f;
            float g = Mth.lerp(flash, baseG, 0.2f);
            float b = Mth.lerp(flash, baseB, 0.2f);
            Vec3 entityPos = owner.getPosition(partialTicks);
            int color = packColorARGB(1.0F, baseR, g, b);
            stack.pushPose();
            stack.translate(-entityPos.x, -entityPos.y, -entityPos.z);
            for (int i = 0;i<entities.length;i++) {
                Vec3 currentPos = entities[i];
                calculateRotations(origin, currentPos,i,owner,stack,consumer,light,color);
                origin = currentPos;
            }
            stack.popPose();
        }
        public void calculateRotations(Vec3 from, Vec3 to, int i, LivingEntity owner, PoseStack stack, VertexConsumer consumer,int light, int mutationColor) {
            if (from == null || to == null) return;

            Vec3 direction = to.subtract(from);
            if (direction.lengthSqr() < 0.0001f) return;

            direction = direction.normalize();
            if (!owner.isInvisible()){
                float yaw = (float) Math.atan2(direction.x, direction.z);
                float pitch = (float) -Math.asin(direction.y);
                stack.pushPose();
                {
                    stack.translate(from.x, from.y, from.z);
                    stack.mulPose(Axis.YP.rotation(yaw));
                    stack.mulPose(Axis.XP.rotation(pitch));
                    if (i > 0 && i - 1 < parts.length) {
                        ModelPart part = parts[i - 1];
                        part.render(stack, consumer, light, OverlayTexture.NO_OVERLAY, mutationColor);
                    }
                }
                stack.popPose();
            }
        }


        public void refreshLegStandingPoint(LivingEntity owner){
            if (owner.isInWater()){
                return;
            }
            if (lastSitPosition != null && getLegBasePos(owner).distanceTo(lastSitPosition) < maxDistance){
                return;
            }
            sitPosition = findStableFooting(owner);
            if (!sitPosition.equals(lastSitPosition)){
                lastSitPosition = sitPosition;
            }
        }

        protected Vec3 findStableFooting(LivingEntity owner) {
            Level level = owner.level();

            Vec3 worldBasePos = getLegBasePos(owner);
            int searchRadius = 6;
            int maxSearchDown = 12;
            int maxSearchUp = 6;

            BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

            for (int y = 0; y >= -maxSearchDown; y--) {
                checkPos.set(worldBasePos.x, worldBasePos.y + y, worldBasePos.z);

                if (isSolidGround(level, checkPos)) {
                    return new Vec3(
                            checkPos.getX() + 0.5,
                            checkPos.getY() - 1.0,
                            checkPos.getZ() + 0.5
                    );
                }
            }

            for (int x = -searchRadius; x <= searchRadius; x++) {
                for (int z = -searchRadius; z <= searchRadius; z++) {
                    for (int y = maxSearchUp; y >= -maxSearchDown; y--) {
                        checkPos.set(
                                worldBasePos.x + x,
                                worldBasePos.y + y,
                                worldBasePos.z + z
                        );

                        if (isSolidGround(level, checkPos)) {
                            if (level.isEmptyBlock(checkPos.above())) {
                                return new Vec3(
                                        checkPos.getX() + 0.5,
                                        checkPos.getY() - 1.0,
                                        checkPos.getZ() + 0.5
                                );
                            }
                        }
                    }
                }
            }
            return worldBasePos;
        }

        private boolean isSolidGround(Level level, BlockPos pos) {
            return level.getBlockState(pos).isSolid() ||
                    !level.getBlockState(pos).getCollisionShape(level, pos).isEmpty();
        }
    }
}
