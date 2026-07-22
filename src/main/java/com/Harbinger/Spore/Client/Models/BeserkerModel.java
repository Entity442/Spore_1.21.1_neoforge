package com.Harbinger.Spore.Client.Models;

import com.Harbinger.Spore.Sentities.Hyper.Berserker;
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

import java.util.List;

public class BeserkerModel<T extends Berserker> extends EntityModel<T> implements TentacledModel, BerserkerBits {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "beserkermodel"), "main");
	private final ModelPart berserk;
	private final ModelPart upper_body;
	private final ModelPart head;
	private final ModelPart head_foliage;
	private final ModelPart FoliageCrown;
	private final ModelPart FoliageCrown2;
	private final ModelPart clicker_foliage;
	private final ModelPart head_tumors;
	private final ModelPart bolbous_tumors;
	private final ModelPart EyeTendrils;
	private final ModelPart LT1;
	private final ModelPart LT1Seg2;
	private final ModelPart LT2;
	private final ModelPart LT2Seg2;
	private final ModelPart LT3;
	private final ModelPart LT3Seg2;
	private final ModelPart RightEar;
	private final ModelPart LeftEar;
	private final ModelPart right_jaw;
	private final ModelPart left_jaw;
	private final ModelPart HeadWear;
	private final ModelPart torso;
	private final ModelPart torso_foliage;
	private final ModelPart BackFoliage;
	private final ModelPart BackFoliage2;
	private final ModelPart neck_tumors;
	private final ModelPart tumor;
	private final ModelPart tumor2;
	private final ModelPart spine;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart hand;
	private final ModelPart spikyparts2;
	private final ModelPart claws2;
	private final ModelPart claw;
	private final ModelPart claw2;
	private final ModelPart claw3;
	private final ModelPart claw4;
	private final ModelPart claw5;
	private final ModelPart arm_foliage;
	private final ModelPart left_arm;
	private final ModelPart hand2;
	private final ModelPart spikyparts;
	private final ModelPart claws;
	private final ModelPart claw6;
	private final ModelPart claw7;
	private final ModelPart claw8;
	private final ModelPart claw9;
	private final ModelPart claw10;
	private final ModelPart arm_foliage2;
	private final ModelPart ass_tumors;
	private final ModelPart ass_tumor;
	private final ModelPart lower_body;
	private final ModelPart left_leg;
	private final ModelPart leg_foliage2;
	private final ModelPart knee_tumors;
	private final ModelPart leg_tumors;
	private final ModelPart lower_lleg;
	private final ModelPart feet2;
	private final ModelPart foot_tumors2;
	private final ModelPart LeftLegWear;
	private final ModelPart leg_tumors2;
	private final ModelPart leg_tumors3;
	private final ModelPart right_leg;
	private final ModelPart RightLegWear;
	private final ModelPart knee_tumor;
	private final ModelPart leg_foliage;
	private final ModelPart BackFoliage3;
	private final ModelPart lower_leg;
	private final ModelPart feet;
	private final ModelPart foot_tumors;
	private final ModelPart Armor;
	private final List<ModelPart> ArmorList;
	public final List<ModelPart> HeadList;
	public final List<ModelPart> ChestList;
	public final List<ModelPart> RightLegList;
	public final List<ModelPart> LeftLegList;

	public BeserkerModel(ModelPart root) {
		this.berserk = root.getChild("berserk");
		this.upper_body = this.berserk.getChild("upper_body");
		this.head = this.upper_body.getChild("head");
		this.head_foliage = this.head.getChild("head_foliage");
		this.FoliageCrown = this.head_foliage.getChild("FoliageCrown");
		this.FoliageCrown2 = this.head_foliage.getChild("FoliageCrown2");
		this.clicker_foliage = this.head_foliage.getChild("clicker_foliage");
		this.head_tumors = this.head.getChild("head_tumors");
		this.bolbous_tumors = this.head.getChild("bolbous_tumors");
		this.EyeTendrils = this.head.getChild("EyeTendrils");
		this.LT1 = this.EyeTendrils.getChild("LT1");
		this.LT1Seg2 = this.LT1.getChild("LT1Seg2");
		this.LT2 = this.EyeTendrils.getChild("LT2");
		this.LT2Seg2 = this.LT2.getChild("LT2Seg2");
		this.LT3 = this.EyeTendrils.getChild("LT3");
		this.LT3Seg2 = this.LT3.getChild("LT3Seg2");
		this.RightEar = this.head.getChild("RightEar");
		this.LeftEar = this.head.getChild("LeftEar");
		this.right_jaw = this.head.getChild("right_jaw");
		this.left_jaw = this.head.getChild("left_jaw");
		this.HeadWear = this.head.getChild("HeadWear");
		this.torso = this.upper_body.getChild("torso");
		this.torso_foliage = this.torso.getChild("torso_foliage");
		this.BackFoliage = this.torso_foliage.getChild("BackFoliage");
		this.BackFoliage2 = this.torso_foliage.getChild("BackFoliage2");
		this.neck_tumors = this.torso.getChild("neck_tumors");
		this.tumor = this.neck_tumors.getChild("tumor");
		this.tumor2 = this.neck_tumors.getChild("tumor2");
		this.spine = this.torso.getChild("spine");
		this.arms = this.upper_body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.hand = this.right_arm.getChild("hand");
		this.spikyparts2 = this.hand.getChild("spikyparts2");
		this.claws2 = this.hand.getChild("claws2");
		this.claw = this.claws2.getChild("claw");
		this.claw2 = this.claws2.getChild("claw2");
		this.claw3 = this.claws2.getChild("claw3");
		this.claw4 = this.claws2.getChild("claw4");
		this.claw5 = this.claws2.getChild("claw5");
		this.arm_foliage = this.right_arm.getChild("arm_foliage");
		this.left_arm = this.arms.getChild("left_arm");
		this.hand2 = this.left_arm.getChild("hand2");
		this.spikyparts = this.hand2.getChild("spikyparts");
		this.claws = this.hand2.getChild("claws");
		this.claw6 = this.claws.getChild("claw6");
		this.claw7 = this.claws.getChild("claw7");
		this.claw8 = this.claws.getChild("claw8");
		this.claw9 = this.claws.getChild("claw9");
		this.claw10 = this.claws.getChild("claw10");
		this.arm_foliage2 = this.left_arm.getChild("arm_foliage2");
		this.ass_tumors = this.berserk.getChild("ass_tumors");
		this.ass_tumor = this.berserk.getChild("ass_tumor");
		this.lower_body = this.berserk.getChild("lower_body");
		this.left_leg = this.lower_body.getChild("left_leg");
		this.leg_foliage2 = this.left_leg.getChild("leg_foliage2");
		this.knee_tumors = this.left_leg.getChild("knee_tumors");
		this.leg_tumors = this.left_leg.getChild("leg_tumors");
		this.lower_lleg = this.left_leg.getChild("lower_lleg");
		this.feet2 = this.lower_lleg.getChild("feet2");
		this.foot_tumors2 = this.feet2.getChild("foot_tumors2");
		this.LeftLegWear = this.lower_lleg.getChild("LeftLegWear");
		this.leg_tumors2 = this.lower_lleg.getChild("leg_tumors2");
		this.leg_tumors3 = this.lower_lleg.getChild("leg_tumors3");
		this.right_leg = this.lower_body.getChild("right_leg");
		this.RightLegWear = this.right_leg.getChild("RightLegWear");
		this.knee_tumor = this.right_leg.getChild("knee_tumor");
		this.leg_foliage = this.right_leg.getChild("leg_foliage");
		this.BackFoliage3 = this.leg_foliage.getChild("BackFoliage3");
		this.lower_leg = this.right_leg.getChild("lower_leg");
		this.feet = this.lower_leg.getChild("feet");
		this.foot_tumors = this.feet.getChild("foot_tumors");
		this.Armor = this.berserk.getChild("Armor");
		this.ArmorList = List.of(Armor,HeadWear,RightLegWear,LeftLegWear);
		this.HeadList = List.of(berserk,upper_body,head);
		this.ChestList = List.of(berserk);
		this.RightLegList = List.of(berserk,lower_body,right_leg);
		this.LeftLegList = List.of(berserk,lower_body,left_leg,lower_lleg);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition berserk = partdefinition.addOrReplaceChild("berserk", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 3.0F));

		PartDefinition elvis_the_pelvis_r1 = berserk.addOrReplaceChild("elvis_the_pelvis_r1", CubeListBuilder.create().texOffs(44, 55).addBox(-7.5F, -4.9F, -3.5F, 15.0F, 5.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -21.5F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition upper_body = berserk.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(0.0F, -26.0015F, 1.7706F));

		PartDefinition head = upper_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -9.7625F, -10.573F, 12.0F, 10.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(138, 5).addBox(-6.0F, -9.7625F, -10.573F, 12.0F, 10.0F, 11.0F, new CubeDeformation(-0.2F))
		.texOffs(44, 21).addBox(-6.0F, 0.2375F, -10.573F, 12.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.236F, -8.1977F, -0.1309F, 0.0F, 0.0F));

		PartDefinition head_foliage = head.addOrReplaceChild("head_foliage", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 41.0373F, 11.8851F, 0.1309F, 0.0F, 0.0F));

		PartDefinition foliage_petal_r1 = head_foliage.addOrReplaceChild("foliage_petal_r1", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9218F, -47.987F, -8.0222F, 3.0745F, 0.065F, 3.1F));

		PartDefinition foliage_petal_r2 = head_foliage.addOrReplaceChild("foliage_petal_r2", CubeListBuilder.create().texOffs(0, 105).addBox(-2.2897F, 0.0082F, -3.8123F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0782F, -49.987F, -4.0222F, -2.6833F, 0.2245F, 2.6147F));

		PartDefinition foliage_petal_r3 = head_foliage.addOrReplaceChild("foliage_petal_r3", CubeListBuilder.create().texOffs(0, 105).addBox(-3.9985F, 0.0267F, -3.4722F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4218F, -46.987F, -11.0222F, -2.9468F, 0.065F, 3.1F));

		PartDefinition foliage_petal_r4 = head_foliage.addOrReplaceChild("foliage_petal_r4", CubeListBuilder.create().texOffs(0, 105).addBox(7.8835F, -4.8113F, 0.6327F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 105).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5782F, -49.237F, -11.5222F, -2.962F, -0.3169F, 2.7655F));

		PartDefinition foliage_petal_r5 = head_foliage.addOrReplaceChild("foliage_petal_r5", CubeListBuilder.create().texOffs(0, 105).addBox(-2.8986F, 1.9074F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5782F, -48.237F, -11.5222F, 0.0F, 0.0F, 0.3054F));

		PartDefinition FoliageCrown = head_foliage.addOrReplaceChild("FoliageCrown", CubeListBuilder.create(), PartPose.offsetAndRotation(5.9782F, -52.9413F, -15.0125F, -0.1372F, -0.3027F, 0.0411F));

		PartDefinition HeadCrownPetal4_r1 = FoliageCrown.addOrReplaceChild("HeadCrownPetal4_r1", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.65F, -0.025F, -1.775F, 0.5635F, 0.4364F, 1.3434F));

		PartDefinition HeadCrownPetal3_r1 = FoliageCrown.addOrReplaceChild("HeadCrownPetal3_r1", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.35F, 1.975F, -0.975F, 2.4227F, 0.4746F, 3.029F));

		PartDefinition HeadCrownPetal2_r1 = FoliageCrown.addOrReplaceChild("HeadCrownPetal2_r1", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.85F, -0.025F, 1.325F, 1.5078F, 0.4065F, 1.3905F));

		PartDefinition HeadCrownPetal1_r1 = FoliageCrown.addOrReplaceChild("HeadCrownPetal1_r1", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.85F, -1.925F, 1.425F, 1.2151F, -0.5845F, 0.0447F));

		PartDefinition FoliageCrown2 = head_foliage.addOrReplaceChild("FoliageCrown2", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.8434F, -51.9442F, -5.0349F, 3.104F, 0.001F, 3.1023F));

		PartDefinition HeadCrownPetal5_r1 = FoliageCrown2.addOrReplaceChild("HeadCrownPetal5_r1", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.575F, -0.025F, -1.775F, 0.5635F, 0.4364F, 1.3434F));

		PartDefinition HeadCrownPetal4_r2 = FoliageCrown2.addOrReplaceChild("HeadCrownPetal4_r2", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.425F, 1.975F, -0.975F, 2.4227F, 0.4746F, 3.029F));

		PartDefinition HeadCrownPetal3_r2 = FoliageCrown2.addOrReplaceChild("HeadCrownPetal3_r2", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.925F, -0.025F, 1.325F, 1.5078F, 0.4065F, 1.3905F));

		PartDefinition HeadCrownPetal2_r2 = FoliageCrown2.addOrReplaceChild("HeadCrownPetal2_r2", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.775F, -1.925F, 1.425F, 1.2151F, -0.5845F, 0.0447F));

		PartDefinition clicker_foliage = head_foliage.addOrReplaceChild("clicker_foliage", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.4775F, -5.5681F, -0.1309F, 0.0F, 0.0F));

		PartDefinition ClickerPetal4_r1 = clicker_foliage.addOrReplaceChild("ClickerPetal4_r1", CubeListBuilder.create().texOffs(90, 17).addBox(-3.972F, -0.2713F, -5.1009F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9331F, -43.9315F, -16.1078F, -2.7871F, -0.4884F, -3.0837F));

		PartDefinition ClickerPetal4_r2 = clicker_foliage.addOrReplaceChild("ClickerPetal4_r2", CubeListBuilder.create().texOffs(90, 17).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0669F, -49.8801F, -16.8909F, -3.0445F, 0.3119F, 2.9312F));

		PartDefinition ClickerPetal4_r3 = clicker_foliage.addOrReplaceChild("ClickerPetal4_r3", CubeListBuilder.create().texOffs(90, 17).addBox(-2.7386F, 1.3985F, -2.8577F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0678F, -44.9988F, -15.6896F, -2.6221F, -0.4094F, 2.9502F));

		PartDefinition ClickerPetal3_r1 = clicker_foliage.addOrReplaceChild("ClickerPetal3_r1", CubeListBuilder.create().texOffs(90, 17).addBox(-4.9238F, -0.112F, -5.0649F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9331F, -43.9315F, -16.1078F, 2.7402F, 0.0614F, -2.7306F));

		PartDefinition ClickerPetal2_r1 = clicker_foliage.addOrReplaceChild("ClickerPetal2_r1", CubeListBuilder.create().texOffs(90, 17).addBox(-2.3394F, -2.002F, -7.4081F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4158F, -44.818F, -17.3765F, -1.2263F, 0.2037F, 0.9216F));

		PartDefinition ClickerPetal1_r1 = clicker_foliage.addOrReplaceChild("ClickerPetal1_r1", CubeListBuilder.create().texOffs(90, 17).addBox(-7.2823F, 0.1492F, -4.1352F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.4582F, -47.0411F, -16.736F, 1.5154F, -0.2583F, 0.5586F));

		PartDefinition head_tumors = head.addOrReplaceChild("head_tumors", CubeListBuilder.create(), PartPose.offset(5.0F, -8.7625F, -0.573F));

		PartDefinition tumor_r1 = head_tumors.addOrReplaceChild("tumor_r1", CubeListBuilder.create().texOffs(118, 41).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.0156F, -0.0733F, -1.6914F, -0.058F, -1.2393F));

		PartDefinition tumor_r2 = head_tumors.addOrReplaceChild("tumor_r2", CubeListBuilder.create().texOffs(123, 43).addBox(-1.3266F, -0.8729F, -0.9537F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.25F, -1.0156F, 0.6767F, -1.0902F, -0.4815F, -0.7732F));

		PartDefinition bolbous_tumors = head.addOrReplaceChild("bolbous_tumors", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -9.4406F, -10.0806F, 0.1309F, 0.0F, 0.0F));

		PartDefinition tumor_small_r1 = bolbous_tumors.addOrReplaceChild("tumor_small_r1", CubeListBuilder.create().texOffs(123, 43).addBox(-1.565F, -0.7654F, 0.6396F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(2.5F, -0.0868F, -0.8108F, 0.1421F, 0.3288F, -0.9467F));

		PartDefinition tumor_small_r2 = bolbous_tumors.addOrReplaceChild("tumor_small_r2", CubeListBuilder.create().texOffs(123, 43).addBox(0.0729F, -1.1487F, 0.3745F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -0.0868F, -0.8108F, -0.1078F, -0.66F, 0.3376F));

		PartDefinition tumor_r3 = bolbous_tumors.addOrReplaceChild("tumor_r3", CubeListBuilder.create().texOffs(118, 41).addBox(-1.2289F, -2.2275F, 0.0684F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.5868F, -1.3108F, -0.4343F, -0.1555F, -1.0671F));

		PartDefinition EyeTendrils = head.addOrReplaceChild("EyeTendrils", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5852F, -4.3709F, -11.6484F, 1.6144F, 0.0F, 0.0F));

		PartDefinition LT1 = EyeTendrils.addOrReplaceChild("LT1", CubeListBuilder.create().texOffs(147, 26).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0816F, 1.2335F, -0.2848F, -0.1767F, -0.2866F, -0.2167F));

		PartDefinition LT1Seg2 = LT1.addOrReplaceChild("LT1Seg2", CubeListBuilder.create().texOffs(155, 26).addBox(-1.0F, -4.2F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.65F)), PartPose.offsetAndRotation(-0.0522F, -2.9848F, 0.1656F, -0.5236F, -0.3054F, 0.0F));

		PartDefinition LT2 = EyeTendrils.addOrReplaceChild("LT2", CubeListBuilder.create(), PartPose.offsetAndRotation(1.1997F, 1.2933F, -0.1967F, -2.8328F, -0.7938F, 3.0875F));

		PartDefinition LT2Seg1_r1 = LT2.addOrReplaceChild("LT2Seg1_r1", CubeListBuilder.create().texOffs(147, 26).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, -0.0018F, 0.0424F, -0.0101F));

		PartDefinition LT2Seg2 = LT2.addOrReplaceChild("LT2Seg2", CubeListBuilder.create().texOffs(155, 26).addBox(-1.0F, -4.15F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.65F)), PartPose.offsetAndRotation(-0.0389F, -3.2663F, 0.0449F, -0.5236F, -0.2618F, 0.0873F));

		PartDefinition LT3 = EyeTendrils.addOrReplaceChild("LT3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.2603F, 0.7576F, 1.4462F, -3.1362F, -1.1548F, -3.0867F));

		PartDefinition LT3Seg1_r1 = LT3.addOrReplaceChild("LT3Seg1_r1", CubeListBuilder.create().texOffs(147, 26).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.1463F, -1.7324F, -0.075F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LT3Seg2 = LT3.addOrReplaceChild("LT3Seg2", CubeListBuilder.create().texOffs(155, 26).addBox(-1.0F, -4.3F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.65F)), PartPose.offsetAndRotation(0.1899F, -2.636F, -0.1056F, 0.5293F, -0.4571F, -0.0516F));

		PartDefinition RightEar = head.addOrReplaceChild("RightEar", CubeListBuilder.create(), PartPose.offset(-2.7F, -8.4625F, -4.773F));

		PartDefinition RightEar_r1 = RightEar.addOrReplaceChild("RightEar_r1", CubeListBuilder.create().texOffs(0, 85).addBox(-15.8127F, -12.99F, -1.0458F, 16.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0131F, 0.0416F, -0.3057F));

		PartDefinition LeftEar = head.addOrReplaceChild("LeftEar", CubeListBuilder.create(), PartPose.offset(3.0F, -9.7625F, -5.573F));

		PartDefinition LeftEar_r1 = LeftEar.addOrReplaceChild("LeftEar_r1", CubeListBuilder.create().texOffs(86, 48).addBox(0.0641F, -12.9815F, -1.0351F, 16.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 1.3F, 0.8F, 0.0F, -0.0873F, 0.1309F));

		PartDefinition right_jaw = head.addOrReplaceChild("right_jaw", CubeListBuilder.create(), PartPose.offset(-5.5F, 0.7375F, -0.573F));

		PartDefinition right_jaw_r1 = right_jaw.addOrReplaceChild("right_jaw_r1", CubeListBuilder.create().texOffs(72, 66).addBox(-0.5F, -0.5F, -11.0F, 6.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 71).addBox(-0.5F, -2.5F, -11.0F, 6.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5253F, 0.0756F, 0.0437F));

		PartDefinition left_jaw = head.addOrReplaceChild("left_jaw", CubeListBuilder.create(), PartPose.offsetAndRotation(5.5F, 0.7375F, -0.573F, 0.2193F, -0.1298F, -0.0172F));

		PartDefinition left_teeth_r1 = left_jaw.addOrReplaceChild("left_teeth_r1", CubeListBuilder.create().texOffs(80, 34).addBox(-5.5F, -2.5F, -11.0F, 6.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(36, 66).addBox(-5.5F, -0.5F, -11.0F, 6.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4456F, -0.1974F, -0.0934F));

		PartDefinition HeadWear = head.addOrReplaceChild("HeadWear", CubeListBuilder.create(), PartPose.offsetAndRotation(2.95F, -8.4625F, -1.573F, -0.8354F, 0.5114F, -0.2475F));

		PartDefinition Helmet_r1 = HeadWear.addOrReplaceChild("Helmet_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -16.0F, -16.0F, 32.0F, 32.0F, 32.0F, new CubeDeformation(-11.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0436F));

		PartDefinition torso = upper_body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 57).addBox(-6.0F, -7.2974F, -2.5671F, 12.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(36, 80).addBox(-6.0F, -7.2974F, -2.5671F, 12.0F, 8.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 0.2989F, -2.2035F, 0.2182F, 0.0F, 0.0F));

		PartDefinition chest_calcified_r1 = torso.addOrReplaceChild("chest_calcified_r1", CubeListBuilder.create().texOffs(0, 39).addBox(-7.0F, -10.0F, -6.0F, 14.0F, 10.0F, 8.0F, new CubeDeformation(0.3F))
		.texOffs(0, 21).addBox(-7.0F, -10.0F, -6.0F, 14.0F, 10.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -7.2974F, 2.4329F, 0.0873F, 0.0F, 0.0F));

		PartDefinition torso_foliage = torso.addOrReplaceChild("torso_foliage", CubeListBuilder.create(), PartPose.offset(0.0F, 25.7026F, 0.4329F));

		PartDefinition foliage_petal_bigger_r1 = torso_foliage.addOrReplaceChild("foliage_petal_bigger_r1", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9681F, -23.3325F, 2.331F, -0.663F, -0.8774F, 1.3486F));

		PartDefinition foliage_petal_bigger_r2 = torso_foliage.addOrReplaceChild("foliage_petal_bigger_r2", CubeListBuilder.create().texOffs(32, 94).addBox(-6.9381F, 5.3791F, -4.6579F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 94).addBox(-2.7662F, 0.6428F, -4.2542F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0319F, -27.7535F, 4.3354F, -0.5968F, 0.3671F, -0.6029F));

		PartDefinition foliage_petal_bigger_r3 = torso_foliage.addOrReplaceChild("foliage_petal_bigger_r3", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0555F, 0.4524F, -4.2056F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7819F, -25.3634F, -3.3644F, 0.0699F, 0.6253F, -0.3051F));

		PartDefinition foliage_petal_bigger_r4 = torso_foliage.addOrReplaceChild("foliage_petal_bigger_r4", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2181F, -25.3634F, -3.3644F, 0.0653F, 0.7464F, 0.2465F));

		PartDefinition foliage_petal_bigger_r5 = torso_foliage.addOrReplaceChild("foliage_petal_bigger_r5", CubeListBuilder.create().texOffs(32, 94).addBox(-17.5233F, -26.8702F, -1.9571F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 94).addBox(-17.7376F, -23.5343F, -3.3083F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9681F, -11.4788F, -5.4183F, 0.0727F, 0.7645F, 0.3368F));

		PartDefinition BackFoliage = torso_foliage.addOrReplaceChild("BackFoliage", CubeListBuilder.create(), PartPose.offsetAndRotation(6.4066F, -36.0505F, 4.8296F, -2.3198F, -0.856F, 2.8272F));

		PartDefinition HeadCrownPetal6_r1 = BackFoliage.addOrReplaceChild("HeadCrownPetal6_r1", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.575F, -0.025F, -1.775F, 0.5635F, 0.4364F, 1.3434F));

		PartDefinition HeadCrownPetal5_r2 = BackFoliage.addOrReplaceChild("HeadCrownPetal5_r2", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.425F, 1.975F, -0.975F, 2.4227F, 0.4746F, 3.029F));

		PartDefinition HeadCrownPetal4_r3 = BackFoliage.addOrReplaceChild("HeadCrownPetal4_r3", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.925F, -0.025F, 1.325F, 1.5078F, 0.4065F, 1.3905F));

		PartDefinition HeadCrownPetal3_r3 = BackFoliage.addOrReplaceChild("HeadCrownPetal3_r3", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.775F, -1.925F, 1.425F, 1.2151F, -0.5845F, 0.0447F));

		PartDefinition BackFoliage2 = torso_foliage.addOrReplaceChild("BackFoliage2", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.5934F, -28.9459F, 4.5349F, -2.8304F, 0.0144F, 3.105F));

		PartDefinition HeadCrownPetal7_r1 = BackFoliage2.addOrReplaceChild("HeadCrownPetal7_r1", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.575F, -0.025F, -1.775F, 0.5635F, 0.4364F, 1.3434F));

		PartDefinition HeadCrownPetal6_r2 = BackFoliage2.addOrReplaceChild("HeadCrownPetal6_r2", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.425F, 1.975F, -0.975F, 2.4227F, 0.4746F, 3.029F));

		PartDefinition HeadCrownPetal5_r3 = BackFoliage2.addOrReplaceChild("HeadCrownPetal5_r3", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.925F, -0.025F, 1.325F, 1.5078F, 0.4065F, 1.3905F));

		PartDefinition HeadCrownPetal4_r4 = BackFoliage2.addOrReplaceChild("HeadCrownPetal4_r4", CubeListBuilder.create().texOffs(116, 33).addBox(-3.5579F, -0.8054F, -3.2003F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.775F, -1.925F, 1.425F, 1.5794F, -0.5674F, -0.1552F));

		PartDefinition neck_tumors = torso.addOrReplaceChild("neck_tumors", CubeListBuilder.create(), PartPose.offset(0.0F, 25.7026F, 0.4329F));

		PartDefinition tumor = neck_tumors.addOrReplaceChild("tumor", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -45.5156F, -0.5733F, -0.2182F, 0.0F, 0.0F));

		PartDefinition tumor_r4 = tumor.addOrReplaceChild("tumor_r4", CubeListBuilder.create().texOffs(118, 41).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 1.5155F, 0.3583F, 0.3917F, -0.2849F, 0.1613F));

		PartDefinition tumor2 = neck_tumors.addOrReplaceChild("tumor2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -43.5156F, -0.5733F, -0.2182F, 0.0F, 0.0F));

		PartDefinition tumor_large_r1 = tumor2.addOrReplaceChild("tumor_large_r1", CubeListBuilder.create().texOffs(116, 39).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.9371F, 0.4254F, 0.0F, -0.2182F, -0.5672F));

		PartDefinition tumor_r5 = tumor2.addOrReplaceChild("tumor_r5", CubeListBuilder.create().texOffs(123, 43).addBox(-1.3266F, -0.8729F, -0.9537F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-3.5F, -0.4371F, -0.5746F, -0.3491F, 0.0F, -0.3927F));

		PartDefinition spine = torso.addOrReplaceChild("spine", CubeListBuilder.create(), PartPose.offset(0.0F, 25.7026F, 0.4329F));

		PartDefinition spike_r1 = spine.addOrReplaceChild("spike_r1", CubeListBuilder.create().texOffs(64, 94).addBox(0.01F, -1.0F, 1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(98, 60).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.6789F, 3.8119F, -0.6109F, 0.0F, 0.0F));

		PartDefinition spike_r2 = spine.addOrReplaceChild("spike_r2", CubeListBuilder.create().texOffs(32, 85).addBox(0.0F, 0.0F, 1.25F, 0.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(127, 88).addBox(-1.0F, 0.0F, -0.75F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(0.0F, -33.619F, 4.7256F, -0.1309F, 0.0F, 0.0F));

		PartDefinition spike_r3 = spine.addOrReplaceChild("spike_r3", CubeListBuilder.create().texOffs(32, 82).addBox(0.0F, -11.0F, 4.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(127, 88).addBox(-1.0F, -10.0F, 2.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(122, 25).addBox(0.0F, -13.0F, 1.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(50, 102).addBox(-1.0F, -11.0F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -33.0F, 2.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition arms = upper_body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -16.2076F, -5.7458F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 112).addBox(-4.5F, -1.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.3F))
		.texOffs(92, 117).addBox(-4.5F, -1.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.4F))
		.texOffs(72, 111).addBox(-4.5F, 3.5F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.1F))
		.texOffs(82, 0).addBox(-5.5F, 10.5F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(23, 97).addBox(-10.5F, 10.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, -0.1301F, -0.0355F, 0.1804F));

		PartDefinition hand = right_arm.addOrReplaceChild("hand", CubeListBuilder.create().texOffs(46, 0).addBox(-4.2239F, -0.401F, -4.3482F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.2761F, 20.901F, -0.1518F));

		PartDefinition spikyparts2 = hand.addOrReplaceChild("spikyparts2", CubeListBuilder.create(), PartPose.offsetAndRotation(15.612F, 19.4505F, 7.0491F, 0.0788F, 0.0503F, -0.1332F));

		PartDefinition spikypartsback_r1 = spikyparts2.addOrReplaceChild("spikypartsback_r1", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.258F, -19.1133F, -1.9198F, -0.089F, 0.3085F, 0.1065F));

		PartDefinition spikypartsback_r2 = spikyparts2.addOrReplaceChild("spikypartsback_r2", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9216F, -18.1092F, -2.1841F, -0.0867F, -0.2132F, 0.1519F));

		PartDefinition spikypartsback_r3 = spikyparts2.addOrReplaceChild("spikypartsback_r3", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.8044F, -15.3512F, -2.4969F, -0.0848F, 0.0477F, 0.1295F));

		PartDefinition spikypartsback_r4 = spikyparts2.addOrReplaceChild("spikypartsback_r4", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.2636F, -19.4377F, -2.1976F, -0.0867F, -0.2132F, 0.1519F));

		PartDefinition spikypartsback_r5 = spikyparts2.addOrReplaceChild("spikypartsback_r5", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.3601F, -14.6012F, -2.2811F, -0.0876F, -0.2566F, 0.1559F));

		PartDefinition spikypartsback_r6 = spikyparts2.addOrReplaceChild("spikypartsback_r6", CubeListBuilder.create().texOffs(147, 35).addBox(0.75F, -6.25F, 0.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.8348F, -14.9421F, -2.3792F, -0.103F, -0.6041F, 0.1922F));

		PartDefinition spikypartsback_r7 = spikyparts2.addOrReplaceChild("spikypartsback_r7", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.8348F, -14.9421F, -2.3792F, -0.0848F, 0.0477F, 0.1295F));

		PartDefinition spines_r1 = spikyparts2.addOrReplaceChild("spines_r1", CubeListBuilder.create().texOffs(120, 78).addBox(-11.5F, 14.5F, 0.0F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.2961F, -36.2839F, -4.483F, -0.0848F, -0.0393F, 0.1369F));

		PartDefinition spikyparts_r1 = spikyparts2.addOrReplaceChild("spikyparts_r1", CubeListBuilder.create().texOffs(147, 30).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5602F, -14.3498F, -10.8482F, -0.086F, -0.1697F, 0.1481F));

		PartDefinition spikyparts_r2 = spikyparts2.addOrReplaceChild("spikyparts_r2", CubeListBuilder.create().texOffs(147, 30).addBox(1.0146F, -2.1327F, -3.056F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4994F, -15.1679F, -11.0837F, -0.0933F, -0.4304F, 0.1726F));

		PartDefinition spikyparts_r3 = spikyparts2.addOrReplaceChild("spikyparts_r3", CubeListBuilder.create().texOffs(147, 30).addBox(0.5877F, -1.3257F, -2.8886F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.3971F, -15.7464F, -10.8813F, -0.0851F, 0.0912F, 0.1258F));

		PartDefinition spikyparts_r4 = spikyparts2.addOrReplaceChild("spikyparts_r4", CubeListBuilder.create().texOffs(147, 30).addBox(1.5604F, -2.2597F, -2.1353F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5041F, -20.0305F, -11.8726F, -0.0953F, -0.4739F, 0.1771F));

		PartDefinition spikyparts_r5 = spikyparts2.addOrReplaceChild("spikyparts_r5", CubeListBuilder.create().texOffs(147, 30).addBox(1.1807F, -0.6514F, -3.1926F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.7438F, -20.5477F, -10.4189F, -0.0876F, -0.2566F, 0.1559F));

		PartDefinition spikyparts_r6 = spikyparts2.addOrReplaceChild("spikyparts_r6", CubeListBuilder.create().texOffs(147, 30).addBox(-1.0F, -2.0F, -3.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.7843F, -20.0023F, -10.2619F, -0.0867F, -0.2132F, 0.1519F));

		PartDefinition spikyparts_r7 = spikyparts2.addOrReplaceChild("spikyparts_r7", CubeListBuilder.create().texOffs(147, 30).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.8461F, -19.9692F, -10.6213F, -0.0903F, 0.352F, 0.1024F));

		PartDefinition claws2 = hand.addOrReplaceChild("claws2", CubeListBuilder.create(), PartPose.offsetAndRotation(15.612F, 19.4505F, 7.0491F, 0.0788F, 0.0503F, -0.1332F));

		PartDefinition claw = claws2.addOrReplaceChild("claw", CubeListBuilder.create(), PartPose.offset(-16.9682F, -14.0455F, -3.161F));

		PartDefinition claw_r1 = claw.addOrReplaceChild("claw_r1", CubeListBuilder.create().texOffs(130, 16).addBox(-3.0F, -0.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0891F, 0.0626F, -0.2216F));

		PartDefinition claw2 = claws2.addOrReplaceChild("claw2", CubeListBuilder.create(), PartPose.offset(-16.7959F, -14.3146F, -6.5709F));

		PartDefinition claw_r2 = claw2.addOrReplaceChild("claw_r2", CubeListBuilder.create().texOffs(130, 16).addBox(-3.0F, -0.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0657F, -0.0954F, -0.0342F));

		PartDefinition claw3 = claws2.addOrReplaceChild("claw3", CubeListBuilder.create(), PartPose.offset(-16.6161F, -14.5956F, -10.1304F));

		PartDefinition claw_r3 = claw3.addOrReplaceChild("claw_r3", CubeListBuilder.create().texOffs(130, 16).addBox(-3.0F, -0.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0669F, -0.153F, -0.2058F));

		PartDefinition claw4 = claws2.addOrReplaceChild("claw4", CubeListBuilder.create(), PartPose.offset(-9.9384F, -13.2481F, -4.8775F));

		PartDefinition claw_r4 = claw4.addOrReplaceChild("claw_r4", CubeListBuilder.create().texOffs(122, 16).addBox(0.0F, -1.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0702F, -0.1249F, 0.1436F));

		PartDefinition claw5 = claws2.addOrReplaceChild("claw5", CubeListBuilder.create(), PartPose.offset(-9.7875F, -13.4839F, -7.8644F));

		PartDefinition claw_r5 = claw5.addOrReplaceChild("claw_r5", CubeListBuilder.create().texOffs(122, 16).addBox(0.0F, -1.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.233F, 0.1543F, 0.0036F));

		PartDefinition arm_foliage = right_arm.addOrReplaceChild("arm_foliage", CubeListBuilder.create(), PartPose.offsetAndRotation(13.3358F, 40.3515F, 6.8973F, 0.0788F, 0.0503F, -0.1332F));

		PartDefinition foliage_petal_bigger_r6 = arm_foliage.addOrReplaceChild("foliage_petal_bigger_r6", CubeListBuilder.create().texOffs(32, 94).addBox(-3.5025F, 0.0681F, -5.3221F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9382F, -29.1272F, -9.0473F, -2.8744F, 0.3615F, -2.7139F));

		PartDefinition foliage_petal_bigger_r7 = arm_foliage.addOrReplaceChild("foliage_petal_bigger_r7", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.1718F, -34.987F, -2.2722F, -0.0926F, 0.0393F, 0.4973F));

		PartDefinition foliage_petal_bigger_r8 = arm_foliage.addOrReplaceChild("foliage_petal_bigger_r8", CubeListBuilder.create().texOffs(32, 94).addBox(-2.3052F, -0.4227F, -4.1078F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.1337F, -29.9199F, -2.1518F, 0.0941F, -0.0865F, 0.2369F));

		PartDefinition foliage_petal_bigger_r9 = arm_foliage.addOrReplaceChild("foliage_petal_bigger_r9", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.1718F, -26.987F, -2.2722F, -0.0949F, -0.0334F, -0.2411F));

		PartDefinition foliage_petal_bigger_r10 = arm_foliage.addOrReplaceChild("foliage_petal_bigger_r10", CubeListBuilder.create().texOffs(32, 94).addBox(-3.3222F, 0.9389F, -3.6012F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.6718F, -27.987F, -8.5222F, 0.1181F, 0.0097F, 0.1932F));

		PartDefinition foliage_petal_bigger_r11 = arm_foliage.addOrReplaceChild("foliage_petal_bigger_r11", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.1718F, -33.987F, -5.2722F, 0.1122F, -0.4677F, -0.2449F));

		PartDefinition foliage_petal_bigger_r12 = arm_foliage.addOrReplaceChild("foliage_petal_bigger_r12", CubeListBuilder.create().texOffs(32, 94).addBox(-3.799F, -0.0937F, -4.1154F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.1718F, -37.737F, -5.2722F, -0.2166F, -0.4318F, 0.484F));

		PartDefinition foliage_petal_bigger_r13 = arm_foliage.addOrReplaceChild("foliage_petal_bigger_r13", CubeListBuilder.create().texOffs(32, 94).addBox(-4.4284F, 0.1148F, -3.7691F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.1718F, -35.737F, -5.2722F, -0.1339F, -0.4623F, 0.2934F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(112, 95).addBox(-4.5F, -1.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.3F))
		.texOffs(112, 112).addBox(-4.5F, -1.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.4F))
		.texOffs(20, 115).addBox(-4.5F, 3.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.1F))
		.texOffs(72, 80).addBox(-5.5F, 7.5F, -3.5F, 7.0F, 11.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(40, 115).addBox(1.5F, 7.5F, 0.25F, 5.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5F, 0.0F, 0.0F, -0.0848F, -0.0393F, -0.1369F));

		PartDefinition hand2 = left_arm.addOrReplaceChild("hand2", CubeListBuilder.create().texOffs(44, 34).addBox(-4.2611F, -0.6378F, -3.8585F, 9.0F, 12.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.2389F, 19.1378F, -0.6415F));

		PartDefinition spikyparts = hand2.addOrReplaceChild("spikyparts", CubeListBuilder.create(), PartPose.offsetAndRotation(-14.7444F, 20.5689F, 8.6792F, 0.0894F, 0.0272F, 0.1398F));

		PartDefinition spikyparts_r8 = spikyparts.addOrReplaceChild("spikyparts_r8", CubeListBuilder.create().texOffs(147, 30).addBox(0.0F, -2.0F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.2487F, -17.1795F, -12.303F, -0.09F, -0.3435F, -0.1098F));

		PartDefinition spikyparts_r9 = spikyparts.addOrReplaceChild("spikyparts_r9", CubeListBuilder.create().texOffs(147, 30).addBox(17.8695F, -4.635F, 4.534F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.3671F, -16.2205F, -11.367F, -0.0903F, 0.352F, -0.1714F));

		PartDefinition spikyparts_r10 = spikyparts.addOrReplaceChild("spikyparts_r10", CubeListBuilder.create().texOffs(147, 30).addBox(0.2115F, -2.0846F, -2.5263F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.3534F, -21.9871F, -10.7323F, -0.0867F, -0.2132F, -0.1218F));

		PartDefinition spikyparts_r11 = spikyparts.addOrReplaceChild("spikyparts_r11", CubeListBuilder.create().texOffs(147, 30).addBox(0.2538F, -2.0846F, -2.5365F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.3429F, -21.5579F, -10.6829F, -0.0876F, -0.2566F, -0.1179F));

		PartDefinition spikyparts_r12 = spikyparts.addOrReplaceChild("spikyparts_r12", CubeListBuilder.create().texOffs(147, 30).addBox(1.9124F, -2.8012F, -1.95F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.7241F, -20.3027F, -12.1754F, -0.0953F, -0.4739F, -0.0966F));

		PartDefinition spikyparts_r13 = spikyparts.addOrReplaceChild("spikyparts_r13", CubeListBuilder.create().texOffs(147, 30).addBox(-1.25F, -3.0F, -3.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.1908F, -15.805F, -10.3131F, -0.086F, -0.1697F, -0.1257F));

		PartDefinition spikyparts_r14 = spikyparts.addOrReplaceChild("spikyparts_r14", CubeListBuilder.create().texOffs(147, 30).addBox(19.0637F, -1.3984F, -0.277F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8302F, -12.5467F, -11.6271F, -0.0851F, 0.0912F, -0.148F));

		PartDefinition spikyparts_r15 = spikyparts.addOrReplaceChild("spikyparts_r15", CubeListBuilder.create().texOffs(147, 30).addBox(19.2425F, -2.385F, -5.4101F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.6606F, -11.151F, -11.8295F, -0.086F, -0.1697F, -0.1257F));

		PartDefinition spikyparts_r16 = spikyparts.addOrReplaceChild("spikyparts_r16", CubeListBuilder.create().texOffs(147, 30).addBox(19.2425F, -3.3851F, -5.4101F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7213F, -11.9691F, -11.594F, -0.086F, -0.1697F, -0.1257F));

		PartDefinition spines_r2 = spikyparts.addOrReplaceChild("spines_r2", CubeListBuilder.create().texOffs(118, 47).addBox(2.5F, 12.5F, 0.25F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.3356F, -36.2894F, -4.483F, -0.0848F, -0.0393F, -0.1369F));

		PartDefinition spikypartsback_r8 = spikyparts.addOrReplaceChild("spikypartsback_r8", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0332F, -14.3287F, -2.3332F, -0.09F, -0.3435F, -0.1098F));

		PartDefinition spikypartsback_r9 = spikyparts.addOrReplaceChild("spikypartsback_r9", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.3754F, -20.7759F, -1.7732F, -0.0851F, 0.0912F, -0.148F));

		PartDefinition spikypartsback_r10 = spikyparts.addOrReplaceChild("spikypartsback_r10", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.6686F, -12.9491F, -2.6306F, -0.0851F, 0.0912F, -0.148F));

		PartDefinition spikypartsback_r11 = spikyparts.addOrReplaceChild("spikypartsback_r11", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.7465F, -14.3789F, -2.4184F, -0.0876F, -0.2566F, -0.1179F));

		PartDefinition spikypartsback_r12 = spikyparts.addOrReplaceChild("spikypartsback_r12", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3811F, -20.4772F, -1.909F, -0.0878F, 0.2651F, -0.1633F));

		PartDefinition spikypartsback_r13 = spikyparts.addOrReplaceChild("spikypartsback_r13", CubeListBuilder.create().texOffs(147, 35).addBox(0.0F, -2.0F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.1014F, -17.032F, -2.2528F, -0.0855F, 0.1346F, -0.1517F));

		PartDefinition spikypartsback_r14 = spikyparts.addOrReplaceChild("spikypartsback_r14", CubeListBuilder.create().texOffs(147, 35).addBox(1.5F, -1.25F, 0.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.1793F, -18.4618F, -2.0406F, -0.0867F, -0.2132F, -0.1218F));

		PartDefinition claws = hand2.addOrReplaceChild("claws", CubeListBuilder.create(), PartPose.offsetAndRotation(-14.7444F, 20.5689F, 8.6792F, 0.0894F, 0.0272F, 0.1398F));

		PartDefinition claw6 = claws.addOrReplaceChild("claw6", CubeListBuilder.create(), PartPose.offset(10.1352F, -12.9808F, -8.1391F));

		PartDefinition claw_r6 = claw6.addOrReplaceChild("claw_r6", CubeListBuilder.create().texOffs(130, 16).addBox(-3.0F, -1.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1089F, -0.1676F, -0.1244F));

		PartDefinition claw7 = claws.addOrReplaceChild("claw7", CubeListBuilder.create(), PartPose.offset(10.0537F, -12.713F, -5.1522F));

		PartDefinition claw_r7 = claw7.addOrReplaceChild("claw_r7", CubeListBuilder.create().texOffs(130, 16).addBox(-3.0F, -1.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1296F, -0.1743F, -0.2531F));

		PartDefinition claw8 = claws.addOrReplaceChild("claw8", CubeListBuilder.create(), PartPose.offset(17.1188F, -14.1138F, -9.8557F));

		PartDefinition claw_r8 = claw8.addOrReplaceChild("claw_r8", CubeListBuilder.create().texOffs(122, 16).addBox(0.0F, -0.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.096F, 0.0191F, 0.033F));

		PartDefinition claw9 = claws.addOrReplaceChild("claw9", CubeListBuilder.create(), PartPose.offset(17.0216F, -13.7947F, -6.2961F));

		PartDefinition claw_r9 = claw9.addOrReplaceChild("claw_r9", CubeListBuilder.create().texOffs(122, 16).addBox(0.0F, -0.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0848F, 0.0477F, -0.1443F));

		PartDefinition claw10 = claws.addOrReplaceChild("claw10", CubeListBuilder.create(), PartPose.offset(16.9285F, -13.489F, -2.8862F));

		PartDefinition claw_r10 = claw10.addOrReplaceChild("claw_r10", CubeListBuilder.create().texOffs(122, 16).addBox(0.0F, -0.5F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0461F, -0.0915F, 0.12F));

		PartDefinition arm_foliage2 = left_arm.addOrReplaceChild("arm_foliage2", CubeListBuilder.create(), PartPose.offsetAndRotation(-16.9833F, 39.7067F, 8.0377F, 0.0894F, 0.0272F, 0.1398F));

		PartDefinition foliage_petal_bigger_r14 = arm_foliage2.addOrReplaceChild("foliage_petal_bigger_r14", CubeListBuilder.create().texOffs(32, 94).addBox(-3.657F, 0.0856F, -4.0027F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5782F, -26.737F, -8.7722F, 2.8457F, -0.733F, -2.8113F));

		PartDefinition foliage_petal_bigger_r15 = arm_foliage2.addOrReplaceChild("foliage_petal_bigger_r15", CubeListBuilder.create().texOffs(32, 94).addBox(-4.2294F, 0.2134F, -3.6103F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.5782F, -26.237F, -0.7722F, -2.6406F, -0.4765F, 2.621F));

		PartDefinition foliage_petal_bigger_r16 = arm_foliage2.addOrReplaceChild("foliage_petal_bigger_r16", CubeListBuilder.create().texOffs(32, 94).addBox(-2.4466F, 0.1505F, -5.2755F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5782F, -36.237F, -0.7722F, -0.1269F, 0.3608F, 0.0121F));

		PartDefinition foliage_petal_bigger_r17 = arm_foliage2.addOrReplaceChild("foliage_petal_bigger_r17", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5782F, -30.237F, -0.7722F, -1.0169F, -1.2154F, 0.8566F));

		PartDefinition foliage_petal_bigger_r18 = arm_foliage2.addOrReplaceChild("foliage_petal_bigger_r18", CubeListBuilder.create().texOffs(32, 94).addBox(-3.0566F, 0.2172F, -3.7492F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5782F, -30.237F, -8.7722F, 0.7138F, -1.2327F, -0.8133F));

		PartDefinition foliage_petal_bigger_r19 = arm_foliage2.addOrReplaceChild("foliage_petal_bigger_r19", CubeListBuilder.create().texOffs(32, 94).addBox(-5.0974F, -2.1947F, -6.4706F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.3282F, -29.737F, -9.0222F, -2.7761F, -0.8389F, 2.3327F));

		PartDefinition foliage_petal_bigger_r20 = arm_foliage2.addOrReplaceChild("foliage_petal_bigger_r20", CubeListBuilder.create().texOffs(32, 94).addBox(-3.9303F, 0.4853F, -3.902F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.3282F, -37.737F, -7.0222F, 2.783F, 0.8451F, 2.9301F));

		PartDefinition ass_tumors = berserk.addOrReplaceChild("ass_tumors", CubeListBuilder.create(), PartPose.offset(0.625F, -24.25F, 3.625F));

		PartDefinition tumor_small_r3 = ass_tumors.addOrReplaceChild("tumor_small_r3", CubeListBuilder.create().texOffs(123, 43).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.875F, 0.25F, -0.375F, 0.631F, -0.4579F, -0.4656F));

		PartDefinition tumor_r6 = ass_tumors.addOrReplaceChild("tumor_r6", CubeListBuilder.create().texOffs(118, 41).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.125F, -0.25F, -0.625F, -0.6169F, 0.2407F, -1.2512F));

		PartDefinition ass_tumor = berserk.addOrReplaceChild("ass_tumor", CubeListBuilder.create(), PartPose.offset(-2.25F, -24.0F, 3.25F));

		PartDefinition tumor_small_r4 = ass_tumor.addOrReplaceChild("tumor_small_r4", CubeListBuilder.create().texOffs(123, 43).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.25F, 0.0F, -0.25F, 1.7411F, -0.2074F, -0.7868F));

		PartDefinition lower_body = berserk.addOrReplaceChild("lower_body", CubeListBuilder.create(), PartPose.offset(0.0F, -22.781F, -1.6441F));

		PartDefinition left_leg = lower_body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5F, 1.1731F, 0.8412F, 0.0F, 0.0F, -0.0436F));

		PartDefinition thigh_r1 = left_leg.addOrReplaceChild("thigh_r1", CubeListBuilder.create().texOffs(100, 80).addBox(-3.5F, -1.0342F, -0.9779F, 6.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.6516F, 0.0942F, -0.1309F, 0.0F, 0.0F));

		PartDefinition leg_foliage2 = left_leg.addOrReplaceChild("leg_foliage2", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.4392F, 21.4347F, 0.8029F, 0.0F, 0.0F, 0.0436F));

		PartDefinition foliage_petal_bigger_r21 = leg_foliage2.addOrReplaceChild("foliage_petal_bigger_r21", CubeListBuilder.create().texOffs(32, 94).addBox(-4.7486F, -0.0327F, -4.0327F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6282F, -15.6854F, 2.3722F, -1.5863F, -1.3909F, 1.3283F));

		PartDefinition foliage_petal_bigger_r22 = leg_foliage2.addOrReplaceChild("foliage_petal_bigger_r22", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0319F, -12.987F, 1.0285F, 0.0F, -0.3927F, 0.0F));

		PartDefinition foliage_petal_bigger_r23 = leg_foliage2.addOrReplaceChild("foliage_petal_bigger_r23", CubeListBuilder.create().texOffs(32, 94).addBox(-2.4294F, -0.7024F, -5.2902F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0319F, -17.737F, 2.7785F, -0.3373F, 0.6741F, -0.5119F));

		PartDefinition knee_tumors = left_leg.addOrReplaceChild("knee_tumors", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5608F, 8.4347F, -3.1971F, 0.0F, 0.0F, 0.0436F));

		PartDefinition tumor_large_r2 = knee_tumors.addOrReplaceChild("tumor_large_r2", CubeListBuilder.create().texOffs(116, 39).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4282F, -1.2943F, 1.25F, 0.0F, -0.48F, 0.6109F));

		PartDefinition tumor_r7 = knee_tumors.addOrReplaceChild("tumor_r7", CubeListBuilder.create().texOffs(118, 41).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0718F, 0.2057F, 0.0F, -0.4184F, -0.9399F, 0.9215F));

		PartDefinition leg_tumors = left_leg.addOrReplaceChild("leg_tumors", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5608F, 3.4347F, 0.8029F, 0.0F, 0.0F, 0.0436F));

		PartDefinition tumor_r8 = leg_tumors.addOrReplaceChild("tumor_r8", CubeListBuilder.create().texOffs(118, 41).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2072F, 0.0818F, -1.5F, -0.3558F, -0.8946F, 1.4961F));

		PartDefinition lower_lleg = left_leg.addOrReplaceChild("lower_lleg", CubeListBuilder.create(), PartPose.offset(0.5F, 8.6325F, -1.6637F));

		PartDefinition calf_r1 = lower_lleg.addOrReplaceChild("calf_r1", CubeListBuilder.create().texOffs(50, 107).addBox(-3.0F, -0.0257F, -2.8916F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.2947F, 2.0943F, -0.2618F, 0.0F, 0.0F));

		PartDefinition feet2 = lower_lleg.addOrReplaceChild("feet2", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.9392F, 12.8021F, 2.4666F, 0.0F, 0.0F, 0.0436F));

		PartDefinition foliage_petal_bigger_r24 = feet2.addOrReplaceChild("foliage_petal_bigger_r24", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7819F, -4.737F, -4.2215F, 0.2489F, 0.6791F, -0.1554F));

		PartDefinition foot_r1 = feet2.addOrReplaceChild("foot_r1", CubeListBuilder.create().texOffs(108, 60).addBox(-3.0F, 11.0F, -4.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.375F, -13.03F, -2.25F, 0.0F, 0.0F, -0.0436F));

		PartDefinition ankle_r1 = feet2.addOrReplaceChild("ankle_r1", CubeListBuilder.create().texOffs(110, 0).addBox(-3.5F, -2.5F, -2.25F, 6.0F, 5.0F, 5.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(5.2453F, -4.5599F, -2.75F, 0.0F, 0.0F, -0.0436F));

		PartDefinition foot_tumors2 = feet2.addOrReplaceChild("foot_tumors2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.75F, -5.5F, 0.25F, 0.0F, 0.3491F, 0.6109F));

		PartDefinition tumor_large_r3 = foot_tumors2.addOrReplaceChild("tumor_large_r3", CubeListBuilder.create().texOffs(116, 39).addBox(-1.165F, -1.9823F, -2.0253F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(-0.75F, -0.75F, -0.5F, -0.6109F, 0.8727F, 0.0F));

		PartDefinition tumor_r9 = foot_tumors2.addOrReplaceChild("tumor_r9", CubeListBuilder.create().texOffs(118, 41).addBox(-2.1815F, -0.998F, -0.9655F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(1.0F, 0.5F, -0.25F, 3.0752F, -1.3881F, 3.1167F));

		PartDefinition LeftLegWear = lower_lleg.addOrReplaceChild("LeftLegWear", CubeListBuilder.create().texOffs(0, 64).addBox(-8.0F, -4.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(-4.25F)), PartPose.offsetAndRotation(0.0F, -0.3746F, 1.9166F, -0.3054F, 0.0F, 0.0F));

		PartDefinition leg_tumors2 = lower_lleg.addOrReplaceChild("leg_tumors2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0608F, 4.8021F, -0.5334F, 0.0F, 0.0F, 0.0436F));

		PartDefinition tumor_r10 = leg_tumors2.addOrReplaceChild("tumor_r10", CubeListBuilder.create().texOffs(118, 41).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6444F, -0.4523F, -0.75F, -2.4044F, 0.2304F, -0.8748F));

		PartDefinition leg_tumors3 = lower_lleg.addOrReplaceChild("leg_tumors3", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.9392F, 1.8021F, -2.5334F, 0.0F, 0.0F, 0.0436F));

		PartDefinition tumor_r11 = leg_tumors3.addOrReplaceChild("tumor_r11", CubeListBuilder.create().texOffs(118, 41).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.2327F, 0.3704F, 1.25F, -2.749F, 0.4622F, -0.5487F));

		PartDefinition right_leg = lower_body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.5F, 1.1731F, 0.8412F, 0.0F, 0.0F, 0.0436F));

		PartDefinition thigh_r2 = right_leg.addOrReplaceChild("thigh_r2", CubeListBuilder.create().texOffs(28, 102).addBox(-3.5F, -0.0342F, -1.9779F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -0.6516F, 0.0942F, -0.1309F, 0.0F, 0.0F));

		PartDefinition RightLegWear = right_leg.addOrReplaceChild("RightLegWear", CubeListBuilder.create(), PartPose.offset(-0.45F, 0.0079F, 0.2529F));

		PartDefinition LeggingsRight_r1 = RightLegWear.addOrReplaceChild("LeggingsRight_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-8.0F, -4.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(-4.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition knee_tumor = right_leg.addOrReplaceChild("knee_tumor", CubeListBuilder.create(), PartPose.offset(-3.5F, 7.6079F, -1.1971F));

		PartDefinition tumor_large_r4 = knee_tumor.addOrReplaceChild("tumor_large_r4", CubeListBuilder.create().texOffs(116, 39).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.8418F, -0.4408F, -1.1325F, -2.7722F, -0.5234F, -0.8095F));

		PartDefinition tumor_r12 = knee_tumor.addOrReplaceChild("tumor_r12", CubeListBuilder.create().texOffs(118, 41).addBox(-1.3753F, -1.2618F, -1.2704F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(3.1892F, -1.4232F, -1.0F, 2.7238F, -0.1867F, -2.8765F));

		PartDefinition leg_foliage = right_leg.addOrReplaceChild("leg_foliage", CubeListBuilder.create(), PartPose.offsetAndRotation(4.4392F, 21.4347F, 0.8029F, 0.0F, 0.0F, -0.0436F));

		PartDefinition foliage_petal_bigger_r25 = leg_foliage.addOrReplaceChild("foliage_petal_bigger_r25", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9681F, -16.487F, -2.4715F, -2.5688F, 1.0439F, -2.5006F));

		PartDefinition foliage_petal_r6 = leg_foliage.addOrReplaceChild("foliage_petal_r6", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2669F, -18.987F, -0.7174F, -3.1401F, -0.5826F, -2.8872F));

		PartDefinition foliage_petal_r7 = leg_foliage.addOrReplaceChild("foliage_petal_r7", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2669F, -11.987F, -0.7174F, -3.1401F, -0.5826F, 2.8287F));

		PartDefinition foliage_petal_r8 = leg_foliage.addOrReplaceChild("foliage_petal_r8", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2669F, -20.737F, -3.7174F, -0.3196F, -0.5178F, 0.1019F));

		PartDefinition foliage_petal_r9 = leg_foliage.addOrReplaceChild("foliage_petal_r9", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2669F, -17.737F, 1.2826F, 0.3166F, 1.1369F, -0.0355F));

		PartDefinition BackFoliage3 = leg_foliage.addOrReplaceChild("BackFoliage3", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.5904F, -11.3691F, 2.4536F, -2.8681F, -0.2042F, 3.0442F));

		PartDefinition HeadCrownPetal8_r1 = BackFoliage3.addOrReplaceChild("HeadCrownPetal8_r1", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5735F, -0.0777F, -1.5306F, 0.5635F, 0.4364F, 1.3434F));

		PartDefinition HeadCrownPetal7_r2 = BackFoliage3.addOrReplaceChild("HeadCrownPetal7_r2", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4265F, 1.9223F, -0.7306F, 2.4227F, 0.4746F, 3.029F));

		PartDefinition HeadCrownPetal5_r4 = BackFoliage3.addOrReplaceChild("HeadCrownPetal5_r4", CubeListBuilder.create().texOffs(116, 33).addBox(-3.5579F, -0.8054F, -3.2003F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7735F, -1.9777F, 1.6694F, 1.5794F, -0.5674F, -0.1552F));

		PartDefinition lower_leg = right_leg.addOrReplaceChild("lower_leg", CubeListBuilder.create(), PartPose.offset(-0.5F, 7.8579F, -2.0721F));

		PartDefinition foliage_petal_r10 = lower_leg.addOrReplaceChild("foliage_petal_r10", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6723F, 0.5898F, -0.0924F, 2.9348F, -0.6117F, 3.0334F));

		PartDefinition HeadCrownPetal6_r3 = lower_leg.addOrReplaceChild("HeadCrownPetal6_r3", CubeListBuilder.create().texOffs(116, 33).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7247F, 5.13F, 4.898F, 1.5078F, 0.4065F, 1.3905F));

		PartDefinition foliage_petal_bigger_r26 = lower_leg.addOrReplaceChild("foliage_petal_bigger_r26", CubeListBuilder.create().texOffs(32, 94).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9711F, 2.8398F, 0.6535F, 0.3373F, 0.6741F, 0.5119F));

		PartDefinition foliage_petal_r11 = lower_leg.addOrReplaceChild("foliage_petal_r11", CubeListBuilder.create().texOffs(0, 105).addBox(-1.6604F, 1.9557F, -3.7028F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7104F, 8.8398F, 4.5815F, 0.1207F, 0.0186F, -0.438F));

		PartDefinition foliage_petal_r12 = lower_leg.addOrReplaceChild("foliage_petal_r12", CubeListBuilder.create().texOffs(0, 105).addBox(-1.0765F, 8.6922F, -1.4664F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7104F, -1.1602F, 5.0815F, 2.6264F, 0.8036F, 2.7545F));

		PartDefinition calf_r2 = lower_leg.addOrReplaceChild("calf_r2", CubeListBuilder.create().texOffs(90, 98).addBox(-2.5F, -8.0F, -4.75F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.5F, 6.75F, 4.875F, 0.1309F, 0.0F, 0.0F));

		PartDefinition feet = lower_leg.addOrReplaceChild("feet", CubeListBuilder.create(), PartPose.offsetAndRotation(4.9392F, 13.5768F, 2.875F, 0.0F, 0.0F, -0.0436F));

		PartDefinition foot_r2 = feet.addOrReplaceChild("foot_r2", CubeListBuilder.create().texOffs(64, 98).addBox(-4.0F, -2.0F, -2.0F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-3.943F, 0.0012F, -3.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition ankle_r2 = feet.addOrReplaceChild("ankle_r2", CubeListBuilder.create().texOffs(108, 68).addBox(-2.5F, -2.5F, -2.25F, 6.0F, 5.0F, 5.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(-5.2453F, -4.5599F, -0.5F, 0.0F, 0.0F, 0.0436F));

		PartDefinition foot_tumors = feet.addOrReplaceChild("foot_tumors", CubeListBuilder.create(), PartPose.offset(-6.0F, -5.0F, -2.0F));

		PartDefinition tumor_large_r5 = foot_tumors.addOrReplaceChild("tumor_large_r5", CubeListBuilder.create().texOffs(116, 39).addBox(-2.5745F, -2.2765F, -1.6051F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -1.25F, -0.6109F, 0.8727F, 0.0F));

		PartDefinition tumor_r13 = foot_tumors.addOrReplaceChild("tumor_r13", CubeListBuilder.create().texOffs(118, 41).addBox(-1.3753F, -1.2618F, -1.2704F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.75F, 1.25F, -1.0F, 2.6852F, -0.6816F, -2.8276F));

		PartDefinition Armor = berserk.addOrReplaceChild("Armor", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.7909F, -23.1074F, 0.0282F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r1 = Armor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 84).addBox(-11.0F, -12.0F, -10.0F, 28.0F, 20.0F, 20.0F, new CubeDeformation(-6.0F)), PartPose.offsetAndRotation(-2.25F, 0.75F, 0.5F, 0.2618F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		berserk.getAllParts().forEach(ModelPart::resetPose);
		float val = Mth.sin(ageInTicks/8)/8;
		float val1 = Mth.cos(ageInTicks/9)/7;
		float val2 = Mth.sin(ageInTicks/8)/9;
		float val3 = Mth.cos(ageInTicks/7)/6;
		float val4 = Mth.sin(ageInTicks/8)/7;
		float val5 = Mth.cos(ageInTicks/6)/7;
		float val6 = Mth.sin(ageInTicks/5)/6;
		float val7 = Mth.cos(ageInTicks/6)/8;
		float moveVal = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
		float ear = entity.getEarAnimationTick();
		if (ear > 0){
			float swing = 0.5F - 0.35F * Mth.triangleWave(ear, 10.0F);
			animateTentacleZ(LeftEar,swing);
			animateTentacleZ(RightEar,-swing);
		}else {
			animateTentacleZ(LeftEar,val);
			animateTentacleZ(RightEar,-val);
		}
		animateTentacleX(LT1,val1);
		animateTentacleZ(LT1,val4);
		animateTentacleX(LT1Seg2,-val2);
		animateTentacleZ(LT1Seg2,val3);
		animateTentacleX(LT2,-val5);
		animateTentacleZ(LT2,val3);
		animateTentacleX(LT2Seg2,-val4);
		animateTentacleZ(LT2Seg2,val2);
		animateTentacleX(LT3,-val4);
		animateTentacleZ(LT3,val1);
		animateTentacleX(LT3Seg2,-val2);
		animateTentacleZ(LT3Seg2,val3);

		animateTentacleZ(claw,val1);
		animateTentacleZ(claw2,val2);
		animateTentacleZ(claw3,-val3);
		animateTentacleZ(claw4,val4);
		animateTentacleZ(claw5,val5);
		animateTentacleZ(claw6,-val4);
		animateTentacleZ(claw7,val3);
		animateTentacleZ(claw8,val2);
		animateTentacleZ(claw9,-val7);
		animateTentacleZ(claw10,val6);
		animateTentacleX(right_jaw,val1);
		animateTentacleX(left_jaw,val2);
		animateTentacleY(right_jaw,val3);
		animateTentacleY(left_jaw,-val3);

		animateTumor(bolbous_tumors,val1);
		animateTumor(head_tumors,val2);
		animateTumor(tumor,val4);
		animateTumor(tumor2,val3);
		animateTumor(ass_tumors,val5);
		animateTumor(knee_tumor,val6);
		animateTumor(foot_tumors,val7);
		animateTumor(leg_tumors,val1);
		animateTumor(leg_tumors2,val2);
		animateTumor(leg_tumors3,val3);
		animateTumor(foot_tumors2,val4);
		animateTumor(knee_tumors,val5);
		animateTentacleZ(right_arm,val);
		animateTentacleZ(left_arm,-val);
		int attackAnimationTick = entity.getMeleeAttackTicks();
		this.right_leg.xRot = moveVal;
		this.left_leg.xRot = -moveVal;
		this.right_arm.xRot = - moveVal;
		this.left_arm.xRot = moveVal;
		this.lower_lleg.xRot = this.left_leg.xRot < 0 ? -this.left_leg.xRot : 0;
		this.lower_leg.xRot = this.right_leg.xRot < 0 ? -this.right_leg.xRot : 0;
		if (attackAnimationTick > 0) {
			float swing = 1.5F * Mth.triangleWave((float)attackAnimationTick, 20.0F);
			this.animateTentacleX(upper_body,swing*0.25f);
			this.animateTentacleX(left_arm,-1.75F +swing);
			this.animateTentacleX(right_arm,-2.0F +swing);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		berserk.getAllParts().forEach(this::setDraw);
		berserk.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
	private void setDraw(ModelPart part){
		if (ArmorList.contains(part)){
			part.visible = false;
		}
	}

	@Override
	public List<ModelPart> HeadList() {
		return HeadList;
	}

	@Override
	public List<ModelPart> ChestList() {
		return ChestList;
	}

	@Override
	public List<ModelPart> RightLegList() {
		return RightLegList;
	}

	@Override
	public List<ModelPart> LeftLegList() {
		return LeftLegList;
	}

	@Override
	public ModelPart HeadWear() {
		return HeadWear;
	}

	@Override
	public ModelPart Chest() {
		return Armor;
	}

	@Override
	public ModelPart RightLeg() {
		return RightLegWear;
	}

	@Override
	public ModelPart LeftLeg() {
		return LeftLegWear;
	}
}