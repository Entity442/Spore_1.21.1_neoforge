package com.Harbinger.Spore.Client.Layers;

import com.Harbinger.Spore.Client.Models.GrakensenkerModel;
import com.Harbinger.Spore.Client.Models.GrakensenkerShipModel;
import com.Harbinger.Spore.Client.Models.SantaModel;
import com.Harbinger.Spore.Client.Models.SiegerModel;
import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import com.Harbinger.Spore.Sentities.Calamities.Sieger;
import com.Harbinger.Spore.Spore;
import com.Harbinger.Spore.core.SConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.time.LocalDate;

public class GrakenShipLayer<T extends Grakensenker> extends RenderLayer<T, GrakensenkerModel<T>> {
    private static final ResourceLocation SHIP = ResourceLocation.fromNamespaceAndPath(Spore.MODID,"textures/entity/graken_ship.png");
    private final GrakensenkerShipModel<T> model = new GrakensenkerShipModel<>();
    public GrakenShipLayer(RenderLayerParent<T, GrakensenkerModel<T>> layerParent) {
        super(layerParent);
    }


    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T t, float v, float v1, float v2, float v3, float v4, float v5) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SHIP, poseStack, multiBufferSource, i, t, v, v1, v2, v3, v4, v5, -1);
    }
}
