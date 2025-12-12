// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package com.Harbinger.Spore.Client.Models.KrakenTentacles;// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class KrakenTentacle3<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "krakententacle2"), "main");
	private final ModelPart body;

	public KrakenTentacle3() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(38, 0).addBox(-4.5F, -16.0F, -4.5F, 9.0F, 16.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 117).addBox(-0.5F, -16.0F, -4.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition TailMidFin_r1 = body.addOrReplaceChild("TailMidFin_r1", CubeListBuilder.create().texOffs(10, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.3F, 5.4F, 0.0676F, -0.149F, 0.0894F));

		PartDefinition TailMidFin_r2 = body.addOrReplaceChild("TailMidFin_r2", CubeListBuilder.create().texOffs(10, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -2.3F, -0.6F, 0.1302F, -0.5067F, -0.3114F));

		PartDefinition TailMidFin_r3 = body.addOrReplaceChild("TailMidFin_r3", CubeListBuilder.create().texOffs(10, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -12.3F, 6.4F, -0.0184F, -0.5215F, -0.0109F));

		PartDefinition TailMidFin_r4 = body.addOrReplaceChild("TailMidFin_r4", CubeListBuilder.create().texOffs(10, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -2.3F, -0.6F, 0.2115F, 0.5696F, 0.1693F));

		PartDefinition TailMidFin_r5 = body.addOrReplaceChild("TailMidFin_r5", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -7.3F, 1.4F, 0.5666F, -0.5067F, -0.3114F));

		PartDefinition TailMidFin_r6 = body.addOrReplaceChild("TailMidFin_r6", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -9.3F, -1.6F, 0.5709F, 0.5188F, 0.2963F));

		PartDefinition TailMidFin_r7 = body.addOrReplaceChild("TailMidFin_r7", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -10.3F, 6.4F, 0.4887F, 0.0256F, 0.0016F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}