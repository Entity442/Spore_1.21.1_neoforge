package com.Harbinger.Spore.Client.Models;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.EvolvedInfected.Conductor;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ConductorModel<T extends Conductor> extends EntityModel<T> implements TentacledModel{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "conductormodel"), "main");
	private final ModelPart Conductor;
	private final ModelPart Torso;
	private final ModelPart Body;
	private final ModelPart Chest;
	private final ModelPart Stomach;
	private final ModelPart Engine;
	private final ModelPart Thermostat;
	private final ModelPart Wires;
	private final ModelPart Gears;
	private final ModelPart Gear1;
	private final ModelPart Gear2;
	private final ModelPart Gear3;
	private final ModelPart enginevents;
	private final ModelPart event1;
	private final ModelPart event2;
	private final ModelPart Arms;
	private final ModelPart RightArm;
	private final ModelPart UpperRightArm;
	private final ModelPart LowerRightArm;
	private final ModelPart Taser;
	private final ModelPart body2;
	private final ModelPart end;
	private final ModelPart straps;
	private final ModelPart pokers;
	private final ModelPart electricity;
	private final ModelPart LeftArm;
	private final ModelPart UpperLeftArm;
	private final ModelPart LowerLeftArm;
	private final ModelPart Legs;
	private final ModelPart RightLeg;
	private final ModelPart UpperRightLeg;
	private final ModelPart LowerRightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart UpperLeftLeg;
	private final ModelPart LowerLeftLeg;
	private final ModelPart Head;
	private final ModelPart HeadDetailsTop;
	private final ModelPart HeadDetailsBack;
	private final ModelPart MouthDetails;
	private final ModelPart MouthTendrils;
	private final ModelPart Tendril1;
	private final ModelPart Seg2Tendril1;
	private final ModelPart Seg3Tendril1;
	private final ModelPart Tendril2;
	private final ModelPart Seg2Tendril2;
	private final ModelPart Seg3Tendril2;
	private final ModelPart Tendril3;
	private final ModelPart Seg2Tendril3;
	private final ModelPart Seg3Tendril3;
	private final ModelPart Teeth;
	private final ModelPart Foliage;
	public final ModelPart MainBrain;

	public ConductorModel(ModelPart root) {
		this.Conductor = root.getChild("Conductor");
		this.Torso = this.Conductor.getChild("Torso");
		this.Body = this.Torso.getChild("Body");
		this.Chest = this.Body.getChild("Chest");
		this.Stomach = this.Body.getChild("Stomach");
		this.Engine = this.Body.getChild("Engine");
		this.Thermostat = this.Engine.getChild("Thermostat");
		this.Wires = this.Engine.getChild("Wires");
		this.Gears = this.Engine.getChild("Gears");
		this.Gear1 = this.Gears.getChild("Gear1");
		this.Gear2 = this.Gears.getChild("Gear2");
		this.Gear3 = this.Gears.getChild("Gear3");
		this.enginevents = this.Engine.getChild("enginevents");
		this.event1 = this.enginevents.getChild("event1");
		this.event2 = this.enginevents.getChild("event2");
		this.Arms = this.Torso.getChild("Arms");
		this.RightArm = this.Arms.getChild("RightArm");
		this.UpperRightArm = this.RightArm.getChild("UpperRightArm");
		this.LowerRightArm = this.RightArm.getChild("LowerRightArm");
		this.Taser = this.LowerRightArm.getChild("Taser");
		this.body2 = this.Taser.getChild("body2");
		this.end = this.Taser.getChild("end");
		this.straps = this.Taser.getChild("straps");
		this.pokers = this.Taser.getChild("pokers");
		this.electricity = this.pokers.getChild("electricity");
		this.LeftArm = this.Arms.getChild("LeftArm");
		this.UpperLeftArm = this.LeftArm.getChild("UpperLeftArm");
		this.LowerLeftArm = this.LeftArm.getChild("LowerLeftArm");
		this.Legs = this.Conductor.getChild("Legs");
		this.RightLeg = this.Legs.getChild("RightLeg");
		this.UpperRightLeg = this.RightLeg.getChild("UpperRightLeg");
		this.LowerRightLeg = this.RightLeg.getChild("LowerRightLeg");
		this.LeftLeg = this.Legs.getChild("LeftLeg");
		this.UpperLeftLeg = this.LeftLeg.getChild("UpperLeftLeg");
		this.LowerLeftLeg = this.LeftLeg.getChild("LowerLeftLeg");
		this.Head = root.getChild("Head");
		this.HeadDetailsTop = this.Head.getChild("HeadDetailsTop");
		this.HeadDetailsBack = this.Head.getChild("HeadDetailsBack");
		this.MouthDetails = this.Head.getChild("MouthDetails");
		this.MouthTendrils = this.MouthDetails.getChild("MouthTendrils");
		this.Tendril1 = this.MouthTendrils.getChild("Tendril1");
		this.Seg2Tendril1 = this.Tendril1.getChild("Seg2Tendril1");
		this.Seg3Tendril1 = this.Seg2Tendril1.getChild("Seg3Tendril1");
		this.Tendril2 = this.MouthTendrils.getChild("Tendril2");
		this.Seg2Tendril2 = this.Tendril2.getChild("Seg2Tendril2");
		this.Seg3Tendril2 = this.Seg2Tendril2.getChild("Seg3Tendril2");
		this.Tendril3 = this.MouthTendrils.getChild("Tendril3");
		this.Seg2Tendril3 = this.Tendril3.getChild("Seg2Tendril3");
		this.Seg3Tendril3 = this.Seg2Tendril3.getChild("Seg3Tendril3");
		this.Teeth = this.MouthDetails.getChild("Teeth");
		this.Foliage = this.Head.getChild("Foliage");
		this.MainBrain = root.getChild("MainBrain");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Conductor = partdefinition.addOrReplaceChild("Conductor", CubeListBuilder.create(), PartPose.offset(0.5F, 24.5F, 0.0F));

		PartDefinition Torso = Conductor.addOrReplaceChild("Torso", CubeListBuilder.create(), PartPose.offset(-0.3775F, -20.7907F, -1.3885F));

		PartDefinition Body = Torso.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.3442F, 12.7574F, 1.6218F));

		PartDefinition Chest = Body.addOrReplaceChild("Chest", CubeListBuilder.create(), PartPose.offset(0.0333F, 8.0333F, -0.2333F));

		PartDefinition Petal_r1 = Chest.addOrReplaceChild("Petal_r1", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -28.0F, -0.5F, -0.0866F, 0.3128F, 0.0125F));

		PartDefinition Petal_r2 = Chest.addOrReplaceChild("Petal_r2", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4F, -24.0F, -4.5F, 0.5889F, 0.1153F, -0.1581F));

		PartDefinition ChestSuit_r1 = Chest.addOrReplaceChild("ChestSuit_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-4.5F, -3.5F, -3.5F, 9.0F, 6.0F, 7.0F, new CubeDeformation(0.1F))
		.texOffs(0, 0).addBox(-4.5F, -3.5F, -3.5F, 9.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -24.7F, -1.7F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Stomach = Body.addOrReplaceChild("Stomach", CubeListBuilder.create(), PartPose.offset(0.0333F, 8.0333F, -0.2333F));

		PartDefinition Petal_r3 = Stomach.addOrReplaceChild("Petal_r3", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, -19.5F, 2.5F, -0.1862F, -0.0819F, 0.5486F));

		PartDefinition Petal_r4 = Stomach.addOrReplaceChild("Petal_r4", CubeListBuilder.create().texOffs(-7, 99).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6F, -17.5F, 2.2F, -0.3466F, 0.0961F, -0.2544F));

		PartDefinition Petal_r5 = Stomach.addOrReplaceChild("Petal_r5", CubeListBuilder.create().texOffs(-7, 98).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -18.0F, -3.5F, 0.5911F, 0.1266F, 0.3156F));

		PartDefinition StomachSuit_r1 = Stomach.addOrReplaceChild("StomachSuit_r1", CubeListBuilder.create().texOffs(28, 27).addBox(-4.0F, -2.5F, -3.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.1F))
		.texOffs(0, 27).addBox(-4.0F, -3.5F, -3.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -18.9F, -0.2F, 0.1047F, 0.0F, 0.0F));

		PartDefinition Engine = Body.addOrReplaceChild("Engine", CubeListBuilder.create().texOffs(0, 46).addBox(-4.5F, -2.0F, 1.0F, 8.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0667F, -16.0667F, 0.4667F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Petal_r6 = Engine.addOrReplaceChild("Petal_r6", CubeListBuilder.create().texOffs(-7, 99).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, 2.6F, 3.2F, -0.5103F, 0.477F, -0.1332F));

		PartDefinition Bottom_r1 = Engine.addOrReplaceChild("Bottom_r1", CubeListBuilder.create().texOffs(72, 22).addBox(-3.49F, -1.5F, -1.5F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.2769F, 1.8968F, -0.6757F, 0.0585F, 0.0606F));

		PartDefinition Thermostat = Engine.addOrReplaceChild("Thermostat", CubeListBuilder.create().texOffs(24, 83).addBox(-1.4724F, -1.4448F, -0.6631F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.4F))
		.texOffs(28, 87).addBox(-0.9724F, -0.9448F, 0.0869F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(86, 56).addBox(0.5276F, -1.4448F, -0.4631F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.4F))
		.texOffs(0, 87).addBox(-1.4724F, -1.4448F, -0.4631F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.4F))
		.texOffs(76, 9).addBox(-1.4734F, -1.4438F, -0.4621F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
		.texOffs(84, 9).addBox(-1.4734F, 0.5562F, -0.4621F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(2.0724F, -0.7631F, 4.1948F, 0.0F, 0.0F, -3.1416F));

		PartDefinition Needle_r1 = Thermostat.addOrReplaceChild("Needle_r1", CubeListBuilder.create().texOffs(16, 87).addBox(-0.5F, -0.5F, -0.6F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(0.0276F, -0.7949F, 0.0269F, 0.0F, 0.0F, 0.3927F));

		PartDefinition Wires = Engine.addOrReplaceChild("Wires", CubeListBuilder.create(), PartPose.offset(1.5F, -2.3604F, 0.7713F));

		PartDefinition Wires_r1 = Wires.addOrReplaceChild("Wires_r1", CubeListBuilder.create().texOffs(20, 87).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.9599F, 0.0F, 0.0F));

		PartDefinition Wires_r2 = Wires.addOrReplaceChild("Wires_r2", CubeListBuilder.create().texOffs(8, 87).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.6104F, 1.2287F, 0.9599F, 0.0F, 0.0F));

		PartDefinition Wires_r3 = Wires.addOrReplaceChild("Wires_r3", CubeListBuilder.create().texOffs(4, 87).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.3604F, 1.7287F, 0.9599F, 0.0F, 0.0F));

		PartDefinition Gears = Engine.addOrReplaceChild("Gears", CubeListBuilder.create(), PartPose.offset(-6.0F, -0.75F, 2.0F));

		PartDefinition Gear1 = Gears.addOrReplaceChild("Gear1", CubeListBuilder.create().texOffs(86, 0).addBox(-0.4991F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(32, 87).addBox(-0.5001F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(36, 87).addBox(-0.5001F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(3.7501F, -1.0F, 1.75F, -0.3491F, 0.0F, 0.0F));

		PartDefinition Tooth_r1 = Gear1.addOrReplaceChild("Tooth_r1", CubeListBuilder.create().texOffs(72, 87).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(76, 87).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition Tooth_r2 = Gear1.addOrReplaceChild("Tooth_r2", CubeListBuilder.create().texOffs(64, 87).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(68, 87).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition Tooth_r3 = Gear1.addOrReplaceChild("Tooth_r3", CubeListBuilder.create().texOffs(40, 87).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 87).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition Gear2 = Gears.addOrReplaceChild("Gear2", CubeListBuilder.create().texOffs(86, 35).addBox(-0.4991F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(80, 87).addBox(-0.5001F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(84, 87).addBox(-0.5001F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(4.7501F, 0.75F, 2.25F, 0.3054F, 0.0F, 0.0F));

		PartDefinition Tooth_r4 = Gear2.addOrReplaceChild("Tooth_r4", CubeListBuilder.create().texOffs(88, 19).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(88, 17).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition Tooth_r5 = Gear2.addOrReplaceChild("Tooth_r5", CubeListBuilder.create().texOffs(88, 15).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(88, 13).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition Tooth_r6 = Gear2.addOrReplaceChild("Tooth_r6", CubeListBuilder.create().texOffs(88, 11).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(88, 6).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition Gear3 = Gears.addOrReplaceChild("Gear3", CubeListBuilder.create().texOffs(86, 39).addBox(-0.4991F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(88, 63).addBox(-0.5001F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(88, 65).addBox(-0.5001F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(3.2501F, 2.0F, 1.75F, -0.3491F, 0.0F, 0.0F));

		PartDefinition Tooth_r7 = Gear3.addOrReplaceChild("Tooth_r7", CubeListBuilder.create().texOffs(88, 77).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(88, 75).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition Tooth_r8 = Gear3.addOrReplaceChild("Tooth_r8", CubeListBuilder.create().texOffs(88, 73).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(88, 71).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition Tooth_r9 = Gear3.addOrReplaceChild("Tooth_r9", CubeListBuilder.create().texOffs(88, 69).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(88, 67).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.0001F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition enginevents = Engine.addOrReplaceChild("enginevents", CubeListBuilder.create(), PartPose.offset(0.1F, 2.4F, 3.5F));

		PartDefinition event1 = enginevents.addOrReplaceChild("event1", CubeListBuilder.create().texOffs(32, 82).addBox(0.0296F, -5.7259F, -0.99F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(10, 77).addBox(1.0296F, -7.2259F, -0.99F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(2.4194F, 0.5062F, -0.98F, 0.1068F, 0.3786F, 0.2823F));

		PartDefinition V1Seg1_r1 = event1.addOrReplaceChild("V1Seg1_r1", CubeListBuilder.create().texOffs(82, 29).addBox(-1.1071F, -3.0252F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.1909F, -0.5482F, -0.02F, 0.0F, 0.0F, 0.3491F));

		PartDefinition event2 = enginevents.addOrReplaceChild("event2", CubeListBuilder.create().texOffs(80, 82).addBox(0.5806F, -4.5997F, -1.3147F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.35F))
		.texOffs(86, 43).addBox(1.5816F, -5.8997F, -1.3147F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.35F)), PartPose.offsetAndRotation(2.1982F, -0.87F, -0.0731F, -0.0289F, -0.2163F, 0.134F));

		PartDefinition V2Seg2_r1 = event2.addOrReplaceChild("V2Seg2_r1", CubeListBuilder.create().texOffs(72, 82).addBox(-1.1071F, -3.0252F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.35F)), PartPose.offsetAndRotation(0.1919F, -0.122F, -0.3447F, 0.0F, 0.0F, 0.6545F));

		PartDefinition Arms = Torso.addOrReplaceChild("Arms", CubeListBuilder.create(), PartPose.offset(-0.1225F, -5.4093F, -0.7115F));

		PartDefinition RightArm = Arms.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-4.5F, 0.0F, 0.0F));

		PartDefinition UpperRightArm = RightArm.addOrReplaceChild("UpperRightArm", CubeListBuilder.create().texOffs(54, 51).addBox(-9.0F, -30.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(70, 35).addBox(-9.0F, -30.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(5.0F, 29.0F, 0.0F));

		PartDefinition Petal_r7 = UpperRightArm.addOrReplaceChild("Petal_r7", CubeListBuilder.create().texOffs(-7, 99).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.1F, -26.8F, 0.9F, -0.3319F, 0.1012F, -0.1996F));

		PartDefinition LowerRightArm = RightArm.addOrReplaceChild("LowerRightArm", CubeListBuilder.create().texOffs(56, 11).addBox(-2.0F, -0.25F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(72, 11).addBox(-2.0F, 0.75F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-2.0F, 6.75F, 0.0F));

		PartDefinition Petal_r8 = LowerRightArm.addOrReplaceChild("Petal_r8", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, 2.95F, -0.9F, 0.203F, -0.0367F, 0.0278F));

		PartDefinition Taser = LowerRightArm.addOrReplaceChild("Taser", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.2F, 10.95F, -0.9F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition body2 = Taser.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(98, 114).addBox(-3.0F, -3.0F, 2.0F, 4.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition end = Taser.addOrReplaceChild("end", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = end.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(92, 125).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.5F, 13.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition straps = Taser.addOrReplaceChild("straps", CubeListBuilder.create().texOffs(76, 117).addBox(-3.5F, -3.25F, 4.25F, 5.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(76, 117).addBox(-3.5F, -3.25F, 8.25F, 5.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pokers = Taser.addOrReplaceChild("pokers", CubeListBuilder.create().texOffs(69, 120).addBox(-2.8F, -2.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(70, 124).addBox(-0.2F, -2.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = pokers.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(63, 121).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.5F, -1.5F, 0.5F, 0.0F, -0.3316F, 0.0F));

		PartDefinition cube_r3 = pokers.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 125).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.5F, -1.5F, 0.5F, 0.0F, 0.3316F, 0.0F));

		PartDefinition electricity = pokers.addOrReplaceChild("electricity", CubeListBuilder.create().texOffs(54, 94).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -1.5F, 0.5F));

		PartDefinition LeftArm = Arms.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(4.5F, 0.0F, 0.0F));

		PartDefinition UpperLeftArm = LeftArm.addOrReplaceChild("UpperLeftArm", CubeListBuilder.create().texOffs(56, 23).addBox(-9.0F, -30.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(0, 66).addBox(-9.0F, -30.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(9.0F, 29.0F, 0.0F));

		PartDefinition Petal_r9 = UpperLeftArm.addOrReplaceChild("Petal_r9", CubeListBuilder.create().texOffs(-7, 99).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -24.3F, 1.0F, -0.2416F, 0.8991F, 0.1443F));

		PartDefinition Petal_r10 = UpperLeftArm.addOrReplaceChild("Petal_r10", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4F, -27.8F, -1.4F, -0.2816F, 0.1266F, 0.3156F));

		PartDefinition LowerLeftArm = LeftArm.addOrReplaceChild("LowerLeftArm", CubeListBuilder.create().texOffs(16, 61).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.5F, 0.0F));

		PartDefinition Legs = Conductor.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = Legs.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-3.0F, -15.0F, 0.0F));

		PartDefinition UpperRightLeg = RightLeg.addOrReplaceChild("UpperRightLeg", CubeListBuilder.create().texOffs(0, 54).addBox(-5.0F, -16.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 63).addBox(-5.0F, -16.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(3.0F, 15.0F, 0.0F));

		PartDefinition LowerRightLeg = RightLeg.addOrReplaceChild("LowerRightLeg", CubeListBuilder.create().texOffs(22, 49).addBox(-2.0F, -0.25F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(60, 0).addBox(-2.0F, 0.75F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 6.75F, 0.0F));

		PartDefinition Petal_r11 = LowerRightLeg.addOrReplaceChild("Petal_r11", CubeListBuilder.create().texOffs(-7, 98).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, 3.25F, -1.5F, 0.403F, 0.5713F, 0.0671F));

		PartDefinition LeftLeg = Legs.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.0F, -15.0F, 0.0F));

		PartDefinition UpperLeftLeg = LeftLeg.addOrReplaceChild("UpperLeftLeg", CubeListBuilder.create().texOffs(54, 39).addBox(0.0F, -16.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 63).addBox(0.0F, -16.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-2.0F, 15.0F, 0.0F));

		PartDefinition Petal_r12 = UpperLeftLeg.addOrReplaceChild("Petal_r12", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, -12.0F, 1.5F, -0.4643F, -0.1058F, -0.4363F));

		PartDefinition LowerLeftLeg = LeftLeg.addOrReplaceChild("LowerLeftLeg", CubeListBuilder.create().texOffs(38, 49).addBox(-2.0F, -0.25F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(32, 61).addBox(-2.0F, 0.75F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 6.75F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -6.1F, -7.8F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(32, 22).addBox(-4.0F, -1.1F, -2.8F, 8.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(32, 11).addBox(-3.0F, -7.1F, -6.8F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.5F))
		.texOffs(0, 122).addBox(-4.0F, -3.1F, -2.8F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, -3.0F, -1.5F));

		PartDefinition HeadDetailsTop = Head.addOrReplaceChild("HeadDetailsTop", CubeListBuilder.create().texOffs(80, 71).addBox(-4.0F, -35.6F, -2.3F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 81).addBox(1.8F, -35.4F, -2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 82).addBox(-4.0F, -35.6F, -5.3F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(22, 46).addBox(-4.0F, -34.6F, -7.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(88, 79).addBox(-1.0F, -34.6F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 87).addBox(2.8F, -34.4F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(72, 29).addBox(3.0F, -35.6F, -9.3F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 47).addBox(3.0F, -34.6F, -5.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 61).addBox(0.0F, -34.6F, -9.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(50, 46).addBox(2.0F, -35.6F, -9.3F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 81).addBox(-3.6F, -35.6F, -8.9F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(76, 7).addBox(-3.6F, -35.6F, -8.9F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 27.5F, 1.5F));

		PartDefinition HeadDetailsBack = Head.addOrReplaceChild("HeadDetailsBack", CubeListBuilder.create().texOffs(76, 0).addBox(-4.0F, -33.6F, -3.3F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 35).addBox(-1.0F, -29.6F, -3.3F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(86, 50).addBox(3.0F, -32.6F, -3.3F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(86, 60).addBox(3.0F, -31.6F, -2.3F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 87).addBox(-1.0F, -31.6F, -2.3F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(86, 54).addBox(1.0F, -30.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 77).addBox(-1.4F, -35.6F, -1.7F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(80, 63).addBox(3.6F, -35.6F, -5.7F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 27.5F, 1.5F));

		PartDefinition MouthDetails = Head.addOrReplaceChild("MouthDetails", CubeListBuilder.create().texOffs(70, 57).addBox(-4.0F, -28.7F, -7.5F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 27.5F, 1.5F));

		PartDefinition MouthTendrils = MouthDetails.addOrReplaceChild("MouthTendrils", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.5603F, -30.466F, -22.4541F, 0.0F, 3.1416F, 0.0F));

		PartDefinition Tendril1 = MouthTendrils.addOrReplaceChild("Tendril1", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.016F, 1.9366F, -17.6428F, 0.6264F, -1.0971F, 2.5207F));

		PartDefinition Seg2Tendril1 = Tendril1.addOrReplaceChild("Seg2Tendril1", CubeListBuilder.create().texOffs(48, 81).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition Seg3Tendril1 = Seg2Tendril1.addOrReplaceChild("Seg3Tendril1", CubeListBuilder.create().texOffs(50, 39).addBox(-0.476F, -5.4781F, -0.4211F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0816F, -4.8197F, -0.0142F, -0.0421F, 0.0113F, 0.9161F));

		PartDefinition Tendril2 = MouthTendrils.addOrReplaceChild("Tendril2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.484F, 2.0633F, -17.7572F, -1.1898F, -0.0237F, 1.6577F));

		PartDefinition Seg2Tendril2 = Tendril2.addOrReplaceChild("Seg2Tendril2", CubeListBuilder.create().texOffs(76, 74).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition Seg3Tendril2 = Seg2Tendril2.addOrReplaceChild("Seg3Tendril2", CubeListBuilder.create().texOffs(84, 74).addBox(-0.524F, -5.4781F, -0.6211F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0816F, -4.8197F, -0.0142F, -0.0421F, -0.0113F, -0.9161F));

		PartDefinition Tendril3 = MouthTendrils.addOrReplaceChild("Tendril3", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.384F, -0.2367F, -16.0572F, -1.5555F, 0.3254F, 1.6629F));

		PartDefinition Seg2Tendril3 = Tendril3.addOrReplaceChild("Seg2Tendril3", CubeListBuilder.create().texOffs(56, 81).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition Seg3Tendril3 = Seg2Tendril3.addOrReplaceChild("Seg3Tendril3", CubeListBuilder.create().texOffs(16, 54).addBox(-0.524F, -5.4781F, -0.6211F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0816F, -4.8197F, -0.0142F, -0.0421F, -0.0113F, -0.9161F));

		PartDefinition Teeth = MouthDetails.addOrReplaceChild("Teeth", CubeListBuilder.create().texOffs(0, 40).addBox(-4.2F, -4.7279F, 8.0288F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.2F, -23.8721F, -17.3288F));

		PartDefinition Toothie_r1 = Teeth.addOrReplaceChild("Toothie_r1", CubeListBuilder.create().texOffs(32, 26).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2228F, -4.3897F, 10.435F, -0.4F, -1.503F, 0.2527F));

		PartDefinition Toothie_r2 = Teeth.addOrReplaceChild("Toothie_r2", CubeListBuilder.create().texOffs(86, 4).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, -4.1F, 9.0F, 0.1396F, 0.1745F, 0.0F));

		PartDefinition Foliage = Head.addOrReplaceChild("Foliage", CubeListBuilder.create(), PartPose.offset(-0.0993F, -0.6388F, -0.7988F));

		PartDefinition Petal_r13 = Foliage.addOrReplaceChild("Petal_r13", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5007F, -5.4612F, -2.9012F, 0.1338F, -0.8214F, -0.3972F));

		PartDefinition Petal_r14 = Foliage.addOrReplaceChild("Petal_r14", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9993F, -4.8612F, -1.4012F, -2.9194F, 0.7971F, -2.6243F));

		PartDefinition Petal_r15 = Foliage.addOrReplaceChild("Petal_r15", CubeListBuilder.create().texOffs(-7, 99).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9993F, -1.8612F, -0.4012F, -0.1593F, 0.2738F, 0.0046F));

		PartDefinition Petal_r16 = Foliage.addOrReplaceChild("Petal_r16", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5993F, -3.6612F, 0.4988F, -0.3443F, 0.1509F, -0.2852F));

		PartDefinition Petal_r17 = Foliage.addOrReplaceChild("Petal_r17", CubeListBuilder.create().texOffs(-7, 98).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8007F, -2.8612F, -6.2012F, 0.2666F, 0.1509F, -0.2852F));

		PartDefinition Petal_r18 = Foliage.addOrReplaceChild("Petal_r18", CubeListBuilder.create().texOffs(-7, 91).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1993F, -3.8612F, -6.2012F, 0.3054F, 0.0F, 0.2182F));

		PartDefinition MainBrain = partdefinition.addOrReplaceChild("MainBrain", CubeListBuilder.create().texOffs(28, 39).addBox(-2.1945F, 5.3169F, 0.8882F, 5.0F, 4.0F, 6.0F, new CubeDeformation(-0.3F))
		.texOffs(32, 72).addBox(0.8055F, 5.3169F, 0.8882F, 2.0F, 4.0F, 6.0F, new CubeDeformation(-0.1F))
		.texOffs(48, 74).addBox(1.3055F, 6.8169F, 1.3882F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.1F))
		.texOffs(16, 73).addBox(-2.0945F, 5.3169F, 0.8882F, 2.0F, 4.0F, 6.0F, new CubeDeformation(-0.1F))
		.texOffs(62, 74).addBox(-2.6945F, 6.8169F, 1.3882F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.0F, -1.5F, 3.1392F, 0.0205F, -0.0203F));

		PartDefinition RightSpike2_r1 = MainBrain.addOrReplaceChild("RightSpike2_r1", CubeListBuilder.create().texOffs(16, 83).addBox(-2.5F, -1.0F, 0.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0945F, 9.0169F, 3.3882F, 0.0F, 0.0F, 0.2182F));

		PartDefinition LeftSpike_r1 = MainBrain.addOrReplaceChild("LeftSpike_r1", CubeListBuilder.create().texOffs(0, 83).addBox(-1.5F, -1.0F, 0.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0055F, 9.2169F, 4.8882F, 0.0F, 0.0F, -0.2182F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float headX = headPitch * ((float) Math.PI / 180F);
		float headY = netHeadYaw / (180F / (float) Math.PI);
		animateTentacleX(Head,headX);
		animateTentacleY(Head,headY);
		animateTentacleX(MainBrain,headX);
		animateTentacleY(MainBrain,headY);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		Conductor.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
		MainBrain.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}