package com.Harbinger.Spore.Client.ArmorParts;

import com.Harbinger.Spore.Client.Models.MistmakerModel;
import com.Harbinger.Spore.Client.Models.SyringeGunModel;
import com.Harbinger.Spore.Sitems.SyringeGun;
import com.Harbinger.Spore.core.Sitems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class MistMakerPart extends ComplexHandModelItem{
    public MistMakerPart(InteractionHand slot, MistmakerModel<LivingEntity> model, ModelPart part, float x, float y, float z, float expand, float xspin, float yspin, float zspin) {
        super(slot, Sitems.MISTMAKER.get(), model, part, x, y, z, expand, xspin, yspin, zspin);
    }

    @Override
    public RenderType type(ResourceLocation location) {
        return RenderType.entityTranslucent(location);
    }
}
