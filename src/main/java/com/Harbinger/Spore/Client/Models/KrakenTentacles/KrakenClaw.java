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

public class KrakenClaw<T extends Entity> extends EntityModel<T> implements TentacledModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "krakenclaw"), "main");
	private final ModelPart body;
	private final ModelPart RightArmSplit1;
	private final ModelPart RASplit1Claw;
	private final ModelPart RASplit1Joint;
	private final ModelPart RASplit1Plane;
	private final ModelPart RASplit1Claw2;
	private final ModelPart RASplit1Joint2;
	private final ModelPart RASplit1Plane2;
	private final ModelPart LeftArm2;
	private final ModelPart LAClaw2;
	private final ModelPart LAJoint2;
	private final ModelPart LAPlane2;
	private final ModelPart LeftArmSplit1;
	private final ModelPart LASplit1Claw;
	private final ModelPart LASplit1Joint;
	private final ModelPart LASplit1Plane;
	private final ModelPart LeftArmSplit2;
	private final ModelPart LASplit2Claw;
	private final ModelPart LASplit2Joint;
	private final ModelPart LASplit2Plane;
	private final ModelPart RightArmSplit2;
	private final ModelPart RightArmSplit2Seg1;
	private final ModelPart RASplit2Claw1;
	private final ModelPart RASplit2Joint1;
	private final ModelPart RASplit2Plane1;
	private final ModelPart RASplit2Claw2;
	private final ModelPart RASplit2Joint2;
	private final ModelPart RASplit2Plane2;

	public KrakenClaw() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.body = root.getChild("body");
		this.RightArmSplit1 = this.body.getChild("RightArmSplit1");
		this.RASplit1Claw = this.RightArmSplit1.getChild("RASplit1Claw");
		this.RASplit1Joint = this.RASplit1Claw.getChild("RASplit1Joint");
		this.RASplit1Plane = this.RASplit1Joint.getChild("RASplit1Plane");
		this.RASplit1Claw2 = this.RightArmSplit1.getChild("RASplit1Claw2");
		this.RASplit1Joint2 = this.RASplit1Claw2.getChild("RASplit1Joint2");
		this.RASplit1Plane2 = this.RASplit1Joint2.getChild("RASplit1Plane2");
		this.LeftArm2 = this.body.getChild("LeftArm2");
		this.LAClaw2 = this.LeftArm2.getChild("LAClaw2");
		this.LAJoint2 = this.LAClaw2.getChild("LAJoint2");
		this.LAPlane2 = this.LAJoint2.getChild("LAPlane2");
		this.LeftArmSplit1 = this.LeftArm2.getChild("LeftArmSplit1");
		this.LASplit1Claw = this.LeftArmSplit1.getChild("LASplit1Claw");
		this.LASplit1Joint = this.LASplit1Claw.getChild("LASplit1Joint");
		this.LASplit1Plane = this.LASplit1Joint.getChild("LASplit1Plane");
		this.LeftArmSplit2 = this.LeftArm2.getChild("LeftArmSplit2");
		this.LASplit2Claw = this.LeftArmSplit2.getChild("LASplit2Claw");
		this.LASplit2Joint = this.LASplit2Claw.getChild("LASplit2Joint");
		this.LASplit2Plane = this.LASplit2Joint.getChild("LASplit2Plane");
		this.RightArmSplit2 = this.body.getChild("RightArmSplit2");
		this.RightArmSplit2Seg1 = this.RightArmSplit2.getChild("RightArmSplit2Seg1");
		this.RASplit2Claw1 = this.RightArmSplit2Seg1.getChild("RASplit2Claw1");
		this.RASplit2Joint1 = this.RASplit2Claw1.getChild("RASplit2Joint1");
		this.RASplit2Plane1 = this.RASplit2Joint1.getChild("RASplit2Plane1");
		this.RASplit2Claw2 = this.RightArmSplit2Seg1.getChild("RASplit2Claw2");
		this.RASplit2Joint2 = this.RASplit2Claw2.getChild("RASplit2Joint2");
		this.RASplit2Plane2 = this.RASplit2Joint2.getChild("RASplit2Plane2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition RightArmSplit1 = body.addOrReplaceChild("RightArmSplit1", CubeListBuilder.create().texOffs(0, 1).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.172F, -2.3967F, 3.3721F, 2.7925F, -0.2505F, -0.092F));

		PartDefinition RASplit1Claw = RightArmSplit1.addOrReplaceChild("RASplit1Claw", CubeListBuilder.create().texOffs(26, 11).addBox(-0.5F, -6.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.3312F, 6.8287F, -0.5515F, 3.1128F, 1.0357F, 0.7329F));

		PartDefinition RASplit1Joint = RASplit1Claw.addOrReplaceChild("RASplit1Joint", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, -7.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, 1.0036F, 0.0F, 0.0F));

		PartDefinition RASplit1Plane = RASplit1Joint.addOrReplaceChild("RASplit1Plane", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition Plane_r1 = RASplit1Plane.addOrReplaceChild("Plane_r1", CubeListBuilder.create().texOffs(42, 26).addBox(0.0F, -3.5F, -1.5F, 0.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition RASplit1Claw2 = RightArmSplit1.addOrReplaceChild("RASplit1Claw2", CubeListBuilder.create().texOffs(20, 15).addBox(-0.5F, -6.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0952F, 4.0477F, -0.092F, -0.9259F, 0.9971F, 1.0769F));

		PartDefinition RASplit1Joint2 = RASplit1Claw2.addOrReplaceChild("RASplit1Joint2", CubeListBuilder.create().texOffs(8, 14).addBox(-0.5F, -7.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, -1.0036F, 0.0F, 0.0F));

		PartDefinition RASplit1Plane2 = RASplit1Joint2.addOrReplaceChild("RASplit1Plane2", CubeListBuilder.create().texOffs(42, 26).addBox(0.0F, -6.0F, -2.0F, 0.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.829F, 0.0F, 0.0F));

		PartDefinition LeftArm2 = body.addOrReplaceChild("LeftArm2", CubeListBuilder.create().texOffs(0, 17).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 6.0F, 5.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-2.3883F, -1.6386F, 0.5217F, -2.9234F, 0.0F, -0.2182F));

		PartDefinition LAClaw2 = LeftArm2.addOrReplaceChild("LAClaw2", CubeListBuilder.create().texOffs(15, 15).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.427F, 2.6611F, -0.2101F, -1.6484F, -1.0923F, 2.5916F));

		PartDefinition LAJoint2 = LAClaw2.addOrReplaceChild("LAJoint2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition ClawBase_r1 = LAJoint2.addOrReplaceChild("ClawBase_r1", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition LAPlane2 = LAJoint2.addOrReplaceChild("LAPlane2", CubeListBuilder.create().texOffs(34, 27).addBox(0.0F, -0.5F, -2.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.1506F, 0.3904F, -0.4363F, 0.0F, 0.0F));

		PartDefinition LeftArmSplit1 = LeftArm2.addOrReplaceChild("LeftArmSplit1", CubeListBuilder.create().texOffs(20, 11).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1993F, 5.0463F, 1.0F, 0.0F, 0.5236F, 0.3054F));

		PartDefinition LASplit1Claw = LeftArmSplit1.addOrReplaceChild("LASplit1Claw", CubeListBuilder.create().texOffs(19, 22).addBox(-0.5F, -6.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0338F, 5.5856F, -0.1049F, 2.7067F, 0.9739F, 0.0364F));

		PartDefinition LASplit1Joint = LASplit1Claw.addOrReplaceChild("LASplit1Joint", CubeListBuilder.create().texOffs(12, 0).addBox(-0.5F, -7.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, 1.0036F, 0.0F, 0.0F));

		PartDefinition LASplit1Plane = LASplit1Joint.addOrReplaceChild("LASplit1Plane", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition Plane_r2 = LASplit1Plane.addOrReplaceChild("Plane_r2", CubeListBuilder.create().texOffs(42, 26).addBox(0.0F, -3.5F, -1.5F, 0.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition LeftArmSplit2 = LeftArm2.addOrReplaceChild("LeftArmSplit2", CubeListBuilder.create().texOffs(18, 17).addBox(-0.75F, -1.0F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.1993F, 5.0463F, -2.0083F, -0.3491F, -0.5236F, 0.3054F));

		PartDefinition LASplit2Claw = LeftArmSplit2.addOrReplaceChild("LASplit2Claw", CubeListBuilder.create().texOffs(0, 1).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.5953F, 7.3483F, 0.1218F, 2.4945F, 1.5138F, 2.9258F));

		PartDefinition LASplit2Joint = LASplit2Claw.addOrReplaceChild("LASplit2Joint", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition ClawBase_r2 = LASplit2Joint.addOrReplaceChild("ClawBase_r2", CubeListBuilder.create().texOffs(6, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition LASplit2Plane = LASplit2Joint.addOrReplaceChild("LASplit2Plane", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.1506F, -0.3904F, 0.4363F, 0.0F, 0.0F));

		PartDefinition Plane_r3 = LASplit2Plane.addOrReplaceChild("Plane_r3", CubeListBuilder.create().texOffs(32, 0).addBox(0.0F, -3.5F, -2.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition RightArmSplit2 = body.addOrReplaceChild("RightArmSplit2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6235F, -0.9457F, -2.8598F, -2.8201F, 0.6092F, 0.3918F));

		PartDefinition RightArmSplit2Seg1 = RightArmSplit2.addOrReplaceChild("RightArmSplit2Seg1", CubeListBuilder.create().texOffs(22, 3).addBox(-1.0F, -0.25F, -1.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1471F, 6.3773F, 0.171F, 0.0F, 0.3491F, 0.3927F));

		PartDefinition RASplit2Claw1 = RightArmSplit2Seg1.addOrReplaceChild("RASplit2Claw1", CubeListBuilder.create().texOffs(13, 6).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.177F, 1.5284F, 0.003F, -2.415F, -1.0724F, 1.5044F));

		PartDefinition RASplit2Joint1 = RASplit2Claw1.addOrReplaceChild("RASplit2Joint1", CubeListBuilder.create().texOffs(15, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition RASplit2Plane1 = RASplit2Joint1.addOrReplaceChild("RASplit2Plane1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.1506F, -0.3904F, 0.4363F, 0.0F, 0.0F));

		PartDefinition Plane_r4 = RASplit2Plane1.addOrReplaceChild("Plane_r4", CubeListBuilder.create().texOffs(34, 27).addBox(0.0F, -3.5F, -2.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -0.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition RASplit2Claw2 = RightArmSplit2Seg1.addOrReplaceChild("RASplit2Claw2", CubeListBuilder.create().texOffs(19, 22).addBox(-0.5F, -6.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.2485F, 5.0629F, -0.1709F, -1.9428F, 1.0657F, 0.1813F));

		PartDefinition RASplit2Joint2 = RASplit2Claw2.addOrReplaceChild("RASplit2Joint2", CubeListBuilder.create().texOffs(14, 0).addBox(-0.5F, -7.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, -1.0036F, 0.0F, 0.0F));

		PartDefinition RASplit2Plane2 = RASplit2Joint2.addOrReplaceChild("RASplit2Plane2", CubeListBuilder.create().texOffs(42, 26).addBox(0.0F, -6.0F, -2.0F, 0.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.829F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float v1 = Mth.sin(ageInTicks/6)/7;
		float v2 = Mth.cos(ageInTicks/6)/8;
		float v3 = Mth.cos(ageInTicks/7)/6;
		float v4 = Mth.sin(ageInTicks/8)/6;
		animateTentacleX(RightArmSplit1,v1);
		animateTentacleZ(LeftArm2,v2);
		animateTentacleX(RightArmSplit2,v4);
		animateTentacleX(RASplit1Claw,v3);
		animateTentacleX(RASplit1Claw2,v4);
		animateTentacleX(LAClaw2,v3);
		animateTentacleZ(LeftArmSplit1,v1);
		animateTentacleZ(LeftArmSplit2,v2);
		animateTentacleX(RASplit2Claw1,-v3);
		animateTentacleX(RASplit2Claw2,-v2);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}