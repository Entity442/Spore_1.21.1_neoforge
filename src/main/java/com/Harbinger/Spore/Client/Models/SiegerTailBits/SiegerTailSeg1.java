package com.Harbinger.Spore.Client.Models.SiegerTailBits;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Calamities.Sieger;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SiegerTailSeg1<T extends Sieger> extends EntityModel<T> {
	private final ModelPart tail;
	private final ModelPart flower27;

	public SiegerTailSeg1() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.tail = root.getChild("tail");
		this.flower27 = this.tail.getChild("flower27");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(108, 11).addBox(-5.0F, -5.2627F, -8.0F, 10.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.5708F, 0.0F, -3.1416F));

		PartDefinition flower27 = tail.addOrReplaceChild("flower27", CubeListBuilder.create().texOffs(152, 8).addBox(-3.5F, -3.0F, -2.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(92, 141).addBox(-3.5F, 3.0F, -3.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 206).addBox(-7.0F, -1.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.5856F, 0.827F, -1.6144F, 0.0F, 0.0F));

		PartDefinition cube_r1 = flower27.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(205, 143).addBox(0.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.1745F, -0.2182F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}