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

public class KrakenTentacle1<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "krakententacle1"), "main");
	private final ModelPart body;
	private final ModelPart RightTail;
	private final ModelPart RTailTumor;

	public KrakenTentacle1() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.body = root.getChild("body");
		this.RightTail = this.body.getChild("RightTail");
		this.RTailTumor = this.RightTail.getChild("RTailTumor");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(38, 0).addBox(-4.5F, -16.0F, -4.5F, 9.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition TailMidFin_r1 = body.addOrReplaceChild("TailMidFin_r1", CubeListBuilder.create().texOffs(10, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -2.3F, -0.6F, 0.1302F, -0.5067F, -0.3114F));

		PartDefinition TailMidFin_r2 = body.addOrReplaceChild("TailMidFin_r2", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -7.3F, 1.4F, 0.5666F, -0.5067F, -0.3114F));

		PartDefinition Head_r1 = body.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(38, 45).addBox(-0.6223F, 2.9159F, -10.3419F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2258F, -17.4365F, 1.1237F, 0.3193F, -0.2909F, -0.0945F));

		PartDefinition RightTail = body.addOrReplaceChild("RightTail", CubeListBuilder.create().texOffs(0, 0).addBox(0.5F, -11.0F, 2.0F, 0.0F, 22.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.8324F, 0.4889F, -0.1921F, -0.0164F, 0.0045F));

		PartDefinition BottomBase_r1 = RightTail.addOrReplaceChild("BottomBase_r1", CubeListBuilder.create().texOffs(38, 25).addBox(-0.5F, -2.0F, -2.4142F, 2.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5718F, 1.3687F, -0.4363F, 0.0F, 0.0F));

		PartDefinition TopBase_r1 = RightTail.addOrReplaceChild("TopBase_r1", CubeListBuilder.create().texOffs(0, 41).addBox(-0.5F, 0.2456F, -2.3927F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.4071F, 1.3687F, 0.4363F, 0.0F, 0.0F));

		PartDefinition RTailTumor = RightTail.addOrReplaceChild("RTailTumor", CubeListBuilder.create(), PartPose.offset(1.1121F, 8.4468F, 11.8977F));

		PartDefinition Tumor_r1 = RTailTumor.addOrReplaceChild("Tumor_r1", CubeListBuilder.create().texOffs(20, 60).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1121F, 0.125F, 0.471F, -0.3383F, -0.0543F, 0.5776F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}