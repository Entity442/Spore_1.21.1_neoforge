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

public class KrakenTentacleFoot<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "krakententaclefoot"), "main");
	private final ModelPart body;
	private final ModelPart body2;
	private final ModelPart body3;

	public KrakenTentacleFoot() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.body = root.getChild("body");
		this.body2 = this.body.getChild("body2");
		this.body3 = this.body.getChild("body3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(38, 0).addBox(-4.5F, -16.0F, -4.5F, 9.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0788F, -6.8624F, -2.1762F, 2.5732F, -1.1572F, 0.0281F));

		PartDefinition Leg_r1 = body2.addOrReplaceChild("Leg_r1", CubeListBuilder.create().texOffs(98, 108).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8159F, 4.3674F, -1.5033F, -3.0472F, 1.3102F, -1.5912F));

		PartDefinition TorsoBase_r1 = body2.addOrReplaceChild("TorsoBase_r1", CubeListBuilder.create().texOffs(98, 108).addBox(-3.5F, -1.5F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.96F, 1.6167F, 1.4316F, -1.0752F, 0.4081F, 0.0114F));

		PartDefinition Arm_r1 = body2.addOrReplaceChild("Arm_r1", CubeListBuilder.create().texOffs(98, 108).addBox(-1.919F, -2.3303F, -0.9665F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3317F, -2.1134F, -1.0678F, -1.4984F, -0.8736F, -0.3897F));

		PartDefinition Arm_r2 = body2.addOrReplaceChild("Arm_r2", CubeListBuilder.create().texOffs(98, 108).addBox(-1.9496F, -0.9033F, -7.8252F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3079F, -3.3228F, -0.853F, -0.7985F, -0.6982F, 1.4766F));

		PartDefinition TorsoTop_r1 = body2.addOrReplaceChild("TorsoTop_r1", CubeListBuilder.create().texOffs(98, 108).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8747F, -1.1196F, -0.8638F, -0.4996F, 0.27F, -0.1446F));

		PartDefinition Head_r1 = body2.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(98, 108).addBox(-3.7022F, -0.41F, -3.6522F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.13F, -4.0992F, -2.9916F, -1.7252F, 0.4911F, 0.1184F));

		PartDefinition body3 = body.addOrReplaceChild("body3", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.9212F, -10.8624F, 1.8238F, 2.3091F, 1.9726F, -0.3063F));

		PartDefinition Leg_r2 = body3.addOrReplaceChild("Leg_r2", CubeListBuilder.create().texOffs(98, 108).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8159F, 2.3674F, -3.5033F, -2.981F, 0.613F, -1.6654F));

		PartDefinition TorsoBase_r2 = body3.addOrReplaceChild("TorsoBase_r2", CubeListBuilder.create().texOffs(98, 108).addBox(-3.5F, -1.5F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.96F, 1.6167F, 1.4316F, -1.0752F, 0.4081F, 0.0114F));

		PartDefinition Arm_r3 = body3.addOrReplaceChild("Arm_r3", CubeListBuilder.create().texOffs(98, 108).addBox(-1.919F, -2.3303F, -0.9665F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3317F, -2.1134F, -1.0678F, -1.4984F, -0.8736F, -0.3897F));

		PartDefinition Arm_r4 = body3.addOrReplaceChild("Arm_r4", CubeListBuilder.create().texOffs(98, 108).addBox(-1.9496F, -0.9033F, -7.8252F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3079F, -3.3228F, -2.853F, -0.7985F, -0.6982F, 1.4766F));

		PartDefinition TorsoTop_r2 = body3.addOrReplaceChild("TorsoTop_r2", CubeListBuilder.create().texOffs(98, 108).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8747F, -1.1196F, -0.8638F, -0.4996F, 0.27F, -0.1446F));

		PartDefinition Head_r2 = body3.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(98, 108).addBox(-3.7022F, -0.41F, -3.6522F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.13F, -4.0992F, -2.9916F, -1.7252F, 0.4911F, 0.1184F));

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