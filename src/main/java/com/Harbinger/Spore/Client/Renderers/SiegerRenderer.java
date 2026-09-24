package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Layers.SiegerArrowLayer;
import com.Harbinger.Spore.Client.Layers.SiegerHatLayer;
import com.Harbinger.Spore.Client.Models.SiegerModel;
import com.Harbinger.Spore.Client.Models.SiegerTailBits.*;
import com.Harbinger.Spore.Client.Special.CalamityRenderer;
import com.Harbinger.Spore.Sentities.Calamities.Sieger;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SiegerRenderer<Type extends Sieger> extends CalamityRenderer<Type , SiegerModel<Type>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/sieger.png");
    private static final ResourceLocation WAR = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/war_sieger.png");
    private static final ResourceLocation EYES_TEXTURE = ResourceLocation.fromNamespaceAndPath(Spore.MODID,
            "textures/entity/eyes/sieger.png");
    private final SiegerTailSeg1<Type> tail1 = new SiegerTailSeg1<>();
    private final SiegerTailSeg2<Type> tail2 = new SiegerTailSeg2<>();
    private final SiegerTailSeg3<Type> tail3 = new SiegerTailSeg3<>();
    private final SiegerTailSeg4<Type> tail4 = new SiegerTailSeg4<>();
    private final SiegerTailSeg5<Type> tail5 = new SiegerTailSeg5<>();
    private final SiegerTailSeg6<Type> tail6 = new SiegerTailSeg6<>();

    public SiegerRenderer(EntityRendererProvider.Context context) {
        super(context, new SiegerModel<>(context.bakeLayer(SiegerModel.LAYER_LOCATION)), 4f);
        this.addLayer(new SiegerHatLayer<>(this,context.getModelSet()));
        this.addLayer(new SiegerArrowLayer(this,context.getModelSet()));
    }
    protected EntityModel<Type> getModel(int i){
        if (i == 1){
            return tail1;
        }
        if (i == 2){
            return tail2;
        }
        if (i == 3){
            return tail3;
        }
        if (i == 4){
            return tail4;
        }
        if (i == 5){
            return tail5;
        }
        if (i == 6){
            return tail6;
        }
        return null;
    }
    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return entity.isAdapted() ? WAR:TEXTURE;
    }
    @Override
    public ResourceLocation eyeLayerTexture() {
        return EYES_TEXTURE;
    }

    @Override
    public void render(Type type, float value1, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int light) {
        super.render(type, value1, partialTicks, stack, bufferSource, light);
        if (type.getTailHp() <= 0){
            return;
        }
        Vec3 entityPos = type.getPosition(partialTicks);
        stack.pushPose();
        {
            stack.translate(-entityPos.x, -entityPos.y, -entityPos.z);
            if (!type.isInvisible()){
                  renderTail(stack,type,light, bufferSource, type.getSiegerTail().getEntities(),partialTicks);
            }
        }
        stack.popPose();
    }

    private void renderTail(PoseStack stack, Type type, int light, MultiBufferSource buffer, Vec3[] segments, float partial) {
        if (segments == null || segments.length < 2) return;
        float hurtTime = type.hurtTime - partial;
        float flashIntensity = 0.0F;
        int mutationColor = type.getMutationColor() == 0 ? -1 : type.getMutationColor();
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
            renderConnectionTail(origin, currentPos,type,light, stack, buffer, i,partial, color);
            origin = currentPos;
        }
    }
    public static int packColorARGB(float a, float r, float g, float b) {
        return ((int)(a * 255) << 24) |
                ((int)(r * 255) << 16) |
                ((int)(g * 255) << 8)  |
                (int)(b * 255);
    }
    private void renderConnectionTail(Vec3 from, Vec3 to,Type parent,int light, PoseStack stack, MultiBufferSource buffer,int index, float partial
            ,int color) {
        if (from == null || to == null) return;
        Vec3 direction = to.subtract(from);
        float length = (float) direction.length();
        if (length < 0.0001f) return;

        direction = direction.normalize();

        float yaw = (float) Math.atan2(direction.x, direction.z);
        float pitch = (float) -Math.asin(direction.y);
        float size = index % 2 == 0 ? 1.1f : 1;
        EntityModel<Type> typeEntityModel = getModel(index);
        if (typeEntityModel == null){
            return;
        }
        stack.pushPose();
        {
            stack.translate(from.x, from.y, from.z);
            stack.mulPose(Axis.YP.rotation(yaw));
            stack.mulPose(Axis.XP.rotation(pitch));
            stack.pushPose();
            {
                VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(getTextureLocation(parent)));
                stack.mulPose(Axis.XP.rotationDegrees(90));
                stack.translate(0,-length/2,0);
                stack.scale(size,1,size);
                typeEntityModel.setupAnim(parent,0,0,parent.tickCount + partial,0,0);
                typeEntityModel.renderToBuffer(stack,consumer,light, OverlayTexture.NO_OVERLAY, color);
            }
            stack.popPose();
        }
        stack.popPose();
    }
}
