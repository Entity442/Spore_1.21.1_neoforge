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

public class KrakenTentacle4<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "krakententacle3"), "main");
	private final ModelPart body;
	private final ModelPart BBBody1;
	private final ModelPart FS2Body4;

	public KrakenTentacle4() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.body = root.getChild("body");
		this.BBBody1 = this.body.getChild("BBBody1");
		this.FS2Body4 = this.body.getChild("FS2Body4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(38, 0).addBox(-4.5F, -16.0F, -4.5F, 9.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition BBBody1 = body.addOrReplaceChild("BBBody1", CubeListBuilder.create(), PartPose.offset(-0.75F, -8.25F, 4.5F));

		PartDefinition TorsoBase_r1 = BBBody1.addOrReplaceChild("TorsoBase_r1", CubeListBuilder.create().texOffs(98, 102).addBox(-9.761F, -0.3912F, -3.9958F, 6.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 2.0F, 1.679F, -0.6499F, -0.7201F));

		PartDefinition TorsoTop_r1 = BBBody1.addOrReplaceChild("TorsoTop_r1", CubeListBuilder.create().texOffs(98, 102).addBox(-3.7609F, -1.1412F, -4.4958F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 1.5704F, 0.1321F, -0.6429F));

		PartDefinition Jaw_r1 = BBBody1.addOrReplaceChild("Jaw_r1", CubeListBuilder.create().texOffs(98, 102).addBox(0.0412F, -4.0001F, -1.4988F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -2.85F, -2.3F, 1.4172F, -0.2533F, 0.7375F));

		PartDefinition Head_r1 = BBBody1.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(98, 102).addBox(0.0412F, -4.0001F, -0.4988F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -2.85F, -2.3F, 0.9622F, 0.5848F, 0.1879F));

		PartDefinition FS2Body4 = body.addOrReplaceChild("FS2Body4", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, -4.25F, -3.0F, 3.0666F, -1.223F, -1.5377F));

		PartDefinition Leg_r1 = FS2Body4.addOrReplaceChild("Leg_r1", CubeListBuilder.create().texOffs(96, 103).addBox(-4.0F, -1.5F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3241F, 0.8525F, -2.1658F, 0.0088F, 0.4082F, 0.6342F));

		PartDefinition Head_r2 = FS2Body4.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(96, 103).addBox(-4.0F, -3.5F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.365F, 1.0059F, 4.1978F, -0.1344F, 0.0263F, 0.1684F));

		PartDefinition Arm_r1 = FS2Body4.addOrReplaceChild("Arm_r1", CubeListBuilder.create().texOffs(96, 103).addBox(-1.5F, -1.5F, -8.5F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3215F, -10.7301F, 0.7825F, 2.0066F, -0.6429F, 0.1325F));

		PartDefinition TorsoTop_r2 = FS2Body4.addOrReplaceChild("TorsoTop_r2", CubeListBuilder.create().texOffs(96, 103).addBox(-3.75F, -1.25F, -3.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.5F, 7.0F, 0.1558F, 0.2318F, 1.2644F));

		PartDefinition TorsoBase_r2 = FS2Body4.addOrReplaceChild("TorsoBase_r2", CubeListBuilder.create().texOffs(96, 103).addBox(-6.0F, -3.0F, -4.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0036F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay,  alpha);
	}
}