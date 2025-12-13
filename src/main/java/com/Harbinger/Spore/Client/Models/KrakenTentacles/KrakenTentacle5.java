// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package com.Harbinger.Spore.Client.Models.KrakenTentacles;// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.Harbinger.Spore.Client.Models.TentacledModel;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class KrakenTentacle5<T extends Entity> extends EntityModel<T> implements TentacledModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "krakententacle1"), "main");
	private final ModelPart body;
	private final ModelPart TumorClump4;
	private final ModelPart TumorClump2;

	public KrakenTentacle5() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.body = root.getChild("body");
		this.TumorClump4 = this.body.getChild("TumorClump4");
		this.TumorClump2 = this.body.getChild("TumorClump2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(38, 0).addBox(-4.5F, -16.0F, -4.5F, 9.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition TumorClump4 = body.addOrReplaceChild("TumorClump4", CubeListBuilder.create(), PartPose.offsetAndRotation(3.3612F, -9.6776F, 4.7448F, -1.8055F, 0.7279F, -0.1578F));

		PartDefinition Tumor_r1 = TumorClump4.addOrReplaceChild("Tumor_r1", CubeListBuilder.create().texOffs(108, 4).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8814F, 1.4556F, -2.7298F, 0.1691F, -0.108F, 0.3931F));

		PartDefinition Tumor_r2 = TumorClump4.addOrReplaceChild("Tumor_r2", CubeListBuilder.create().texOffs(105, 16).addBox(-0.455F, -4.1425F, -1.5293F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0126F, 2.799F, 0.7806F, 0.3927F, 0.4363F, 0.0F));

		PartDefinition Tumor_r3 = TumorClump4.addOrReplaceChild("Tumor_r3", CubeListBuilder.create().texOffs(100, 1).addBox(-4.6894F, -4.6125F, -0.9F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0126F, 2.799F, 0.7806F, 0.6545F, 0.5236F, 0.0F));

		PartDefinition Tumor_r4 = TumorClump4.addOrReplaceChild("Tumor_r4", CubeListBuilder.create().texOffs(104, 16).addBox(-2.6576F, -2.0822F, -4.3946F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9874F, 2.799F, -1.2194F, 0.3927F, -0.4363F, 0.0F));

		PartDefinition TumorClump2 = body.addOrReplaceChild("TumorClump2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.6388F, -9.6776F, -2.2552F, 1.4518F, -0.1352F, -0.599F));

		PartDefinition Tumor_r5 = TumorClump2.addOrReplaceChild("Tumor_r5", CubeListBuilder.create().texOffs(108, 4).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0683F, -1.4672F, -3.9879F, 0.1691F, -0.108F, 0.3931F));

		PartDefinition Tumor_r6 = TumorClump2.addOrReplaceChild("Tumor_r6", CubeListBuilder.create().texOffs(105, 16).addBox(-0.455F, -4.1425F, -1.5293F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9372F, 1.8762F, -0.4775F, 0.3927F, 0.4363F, 0.0F));

		PartDefinition Tumor_r7 = TumorClump2.addOrReplaceChild("Tumor_r7", CubeListBuilder.create().texOffs(100, 1).addBox(-4.6894F, -4.6125F, -0.9F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0628F, 1.8762F, -0.4775F, 0.6545F, 0.5236F, 0.0F));

		PartDefinition Tumor_r8 = TumorClump2.addOrReplaceChild("Tumor_r8", CubeListBuilder.create().texOffs(104, 16).addBox(-2.6576F, -2.0822F, -4.3946F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9372F, -0.1238F, -2.4775F, 0.3927F, -0.4363F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animateTumor(TumorClump2, Mth.sin(ageInTicks/7)/6);
		animateTumor(TumorClump4, Mth.cos(ageInTicks/5)/7);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}