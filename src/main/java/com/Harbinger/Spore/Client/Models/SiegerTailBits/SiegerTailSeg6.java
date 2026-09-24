package com.Harbinger.Spore.Client.Models.SiegerTailBits;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import com.Harbinger.Spore.Client.Models.TentacledModel;
import com.Harbinger.Spore.Sentities.Calamities.Sieger;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SiegerTailSeg6<T extends Sieger> extends EntityModel<T> implements TentacledModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart tail4;
	private final ModelPart tumor;
	private final ModelPart TumorBase;
	private final ModelPart TumorBase2;

	public SiegerTailSeg6() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.tail4 = root.getChild("tail4");
		this.tumor = this.tail4.getChild("tumor");
		this.TumorBase = this.tumor.getChild("TumorBase");
		this.TumorBase2 = this.tumor.getChild("TumorBase2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tail4 = partdefinition.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(62, 233).addBox(-3.5F, -4.7982F, -8.0229F, 7.0F, 10.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0877F, 0.0526F, -1.5708F, 0.0F, 0.0F));

		PartDefinition tumor = tail4.addOrReplaceChild("tumor", CubeListBuilder.create().texOffs(123, 243).addBox(-8.0F, -8.6187F, 5.0213F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, -7.6187F, -1.9787F, 12.0F, 14.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.1795F, 9.9558F));

		PartDefinition TumorBase = tumor.addOrReplaceChild("TumorBase", CubeListBuilder.create(), PartPose.offset(2.1562F, 7.2079F, 8.1882F));

		PartDefinition Biomass_r1 = TumorBase.addOrReplaceChild("Biomass_r1", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.8989F, -0.1307F, 0.8938F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r2 = TumorBase.addOrReplaceChild("Biomass_r2", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7817F, 0.206F, 8.7818F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r3 = TumorBase.addOrReplaceChild("Biomass_r3", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6765F, 1.6273F, -4.3262F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r4 = TumorBase.addOrReplaceChild("Biomass_r4", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5928F, -3.8534F, -6.2058F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition Biomass_r5 = TumorBase.addOrReplaceChild("Biomass_r5", CubeListBuilder.create().texOffs(132, 249).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0887F, 0.7285F, -4.7513F, -0.329F, -1.0983F, 1.1961F));

		PartDefinition TumorBase2 = tumor.addOrReplaceChild("TumorBase2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.1562F, -3.7921F, 5.1882F, 0.0F, 0.0F, 2.9671F));

		PartDefinition Biomass_r6 = TumorBase2.addOrReplaceChild("Biomass_r6", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.8989F, -0.1307F, 0.8938F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r7 = TumorBase2.addOrReplaceChild("Biomass_r7", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7817F, 0.206F, 8.7818F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r8 = TumorBase2.addOrReplaceChild("Biomass_r8", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6765F, 1.6273F, -4.3262F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r9 = TumorBase2.addOrReplaceChild("Biomass_r9", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5928F, -3.8534F, -6.2058F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition Biomass_r10 = TumorBase2.addOrReplaceChild("Biomass_r10", CubeListBuilder.create().texOffs(132, 249).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0887F, 0.7285F, -4.7513F, -0.329F, -1.0983F, 1.1961F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animateTumor(TumorBase, Mth.cos(ageInTicks/6)/9);
		animateTumor(TumorBase2,-Mth.sin(ageInTicks/7)/8);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		tail4.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}