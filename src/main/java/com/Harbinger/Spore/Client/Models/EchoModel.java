package com.Harbinger.Spore.Client.Models;// Made with Blockbench 5.1.4
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

public class EchoModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "echomodel"), "main");
	private final ModelPart tip;
	private final ModelPart back;
	private final ModelPart mid;
	private final ModelPart front;

	public EchoModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.tip = root.getChild("tip");
		this.back = root.getChild("back");
		this.mid = root.getChild("mid");
		this.front = root.getChild("front");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tip = partdefinition.addOrReplaceChild("tip", CubeListBuilder.create().texOffs(2, 18).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 18.5F, 0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(12, 8).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 18.5F, 0.5F));

		PartDefinition mid = partdefinition.addOrReplaceChild("mid", CubeListBuilder.create(), PartPose.offset(-0.5F, 18.5F, -0.5F));

		PartDefinition mid_r1 = mid.addOrReplaceChild("mid_r1", CubeListBuilder.create().texOffs(0, 8).addBox(-2.5F, -2.5F, -0.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition front = partdefinition.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.5F, 3.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 18.5F, -1.5F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        front.zRot = ageInTicks * 0.25f;
		mid.zRot = ageInTicks * -0.1f;
		back.zRot = ageInTicks * 0.05f;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		tip.render(poseStack, vertexConsumer, packedLight, packedOverlay,  alpha);
		back.render(poseStack, vertexConsumer, packedLight, packedOverlay,  alpha);
		mid.render(poseStack, vertexConsumer, packedLight, packedOverlay,  alpha);
		front.render(poseStack, vertexConsumer, packedLight, packedOverlay,  alpha);
	}
}