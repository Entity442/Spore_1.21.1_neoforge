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

public class SiegerTailSeg4<T extends Sieger> extends EntityModel<T>  implements TentacledModel {
	private final ModelPart tail3;
	private final ModelPart TumorBase3;
	private final ModelPart flower28;
	private final ModelPart flower29;

	public SiegerTailSeg4() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.tail3 = root.getChild("tail3");
		this.TumorBase3 = this.tail3.getChild("TumorBase3");
		this.flower28 = this.tail3.getChild("flower28");
		this.flower29 = this.tail3.getChild("flower29");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tail3 = partdefinition.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(201, 216).addBox(-4.0F, -5.2655F, -8.4768F, 8.0F, 10.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.9144F, 0.077F, -1.5708F, 0.0F, 0.0F));

		PartDefinition TumorBase3 = tail3.addOrReplaceChild("TumorBase3", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.8438F, -3.1188F, -5.0863F, 0.0F, 0.0F, 2.9671F));

		PartDefinition Biomass_r1 = TumorBase3.addOrReplaceChild("Biomass_r1", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9444F, -0.6516F, -4.1062F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r2 = TumorBase3.addOrReplaceChild("Biomass_r2", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8273F, -0.3149F, 3.7818F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r3 = TumorBase3.addOrReplaceChild("Biomass_r3", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6309F, 1.1063F, 6.6738F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r4 = TumorBase3.addOrReplaceChild("Biomass_r4", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5473F, -4.3744F, -11.2058F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition flower28 = tail3.addOrReplaceChild("flower28", CubeListBuilder.create().texOffs(152, 8).addBox(-3.5F, -3.0F, -2.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(92, 141).addBox(-3.5F, 3.0F, -3.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 206).addBox(-7.0F, -1.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -3.8268F, 0.4756F, 1.3326F, -0.1096F, -0.4232F));

		PartDefinition cube_r1 = flower28.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(205, 143).addBox(0.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.1745F, -0.2182F, 0.0F));

		PartDefinition flower29 = tail3.addOrReplaceChild("flower29", CubeListBuilder.create().texOffs(152, 8).addBox(-3.5F, -3.0F, -2.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(92, 141).addBox(-3.5F, 3.0F, -3.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 206).addBox(-7.0F, -1.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 5.1732F, 3.4756F, 1.3326F, -0.1096F, -0.4232F));

		PartDefinition cube_r2 = flower29.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(205, 143).addBox(0.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.1745F, -0.2182F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animateTumor(TumorBase3, Mth.sin(ageInTicks/7)/6);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		tail3.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}