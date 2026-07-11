package com.Harbinger.Spore.Client.Models;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import com.Harbinger.Spore.Client.Models.ChargerBits;
import com.Harbinger.Spore.Client.Models.TentacledModel;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Charger;
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

public class ChargerCrawlingModel<T extends Charger> extends EntityModel<T> implements TentacledModel, ChargerBits {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "chargercrawlingmodel"), "main");
	private final ModelPart Charger;
	private final ModelPart Legs;
	private final ModelPart RightLeg;
	private final ModelPart RightLegMain;
	private final ModelPart RightLegMainUpper;
	private final ModelPart RightLegMainLower;
	private final ModelPart RightLegFoliageCrown;
	private final ModelPart RightLegWear;
	private final ModelPart RightLegFoot;
	private final ModelPart RightLegTumorsCluster;
	private final ModelPart LeftLeg;
	private final ModelPart LeftLegMain;
	private final ModelPart LeftLegMainUpper;
	private final ModelPart LeftLegTumorsCluster1;
	private final ModelPart LeftLegMainLower;
	private final ModelPart LeftLegTumorsCluster2;
	private final ModelPart LeftLegWear;
	private final ModelPart LeftLegFoot;
	private final ModelPart Head;
	private final ModelPart MainHead;
	private final ModelPart RightHead;
	private final ModelPart LeftHead;
	private final ModelPart TopHead;
	private final ModelPart HeadFoliage;
	private final ModelPart ClickerFoliage;
	private final ModelPart HeadFoliageCrown;
	private final ModelPart SparseHeadFoliage;
	private final ModelPart RightEar;
	private final ModelPart LeftEar;
	private final ModelPart HeadWear;
	private final ModelPart LowerJaw;
	private final ModelPart RightSplitJaw;
	private final ModelPart LeftSplitJaw;
	private final ModelPart HeadTumors;
	private final ModelPart HeadTumorsCluster1;
	private final ModelPart HeadTumorsCluster2;
	private final ModelPart Torso;
	private final ModelPart Chest;
	private final ModelPart ChestFoliage;
	private final ModelPart BackFoliageCrown;
	private final ModelPart SparseChestFoliage;
	private final ModelPart ChestTumorsCluster;
	private final ModelPart BackTumorsCluster;
	private final ModelPart Stomach;
	private final ModelPart Armor;
	private final ModelPart Arms;
	private final ModelPart RightArm;
	private final ModelPart RightArmMain;
	private final ModelPart RightArmWear;
	private final ModelPart RightHand;
	private final ModelPart RightArmClaws;
	private final ModelPart LeftArm;
	private final ModelPart LeftArmMain;
	private final ModelPart LeftArmWear;
	private final ModelPart LeftHand;
	private final ModelPart LeftArmClaws;
	private final List<ModelPart> ArmorList;
	public final List<ModelPart> HeadList;
	public final List<ModelPart> ChestList;
	public final List<ModelPart> RightArmList;
	public final List<ModelPart> LeftArmList;
	public final List<ModelPart> RightLegList;
	public final List<ModelPart> LeftLegList;

	public ChargerCrawlingModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.Charger = root.getChild("Charger");
		this.Legs = this.Charger.getChild("Legs");
		this.RightLeg = this.Legs.getChild("RightLeg");
		this.RightLegMain = this.RightLeg.getChild("RightLegMain");
		this.RightLegMainUpper = this.RightLegMain.getChild("RightLegMainUpper");
		this.RightLegMainLower = this.RightLegMain.getChild("RightLegMainLower");
		this.RightLegFoliageCrown = this.RightLegMainLower.getChild("RightLegFoliageCrown");
		this.RightLegWear = this.RightLegMain.getChild("RightLegWear");
		this.RightLegFoot = this.RightLegMain.getChild("RightLegFoot");
		this.RightLegTumorsCluster = this.RightLegFoot.getChild("RightLegTumorsCluster");
		this.LeftLeg = this.Legs.getChild("LeftLeg");
		this.LeftLegMain = this.LeftLeg.getChild("LeftLegMain");
		this.LeftLegMainUpper = this.LeftLegMain.getChild("LeftLegMainUpper");
		this.LeftLegTumorsCluster1 = this.LeftLegMainUpper.getChild("LeftLegTumorsCluster1");
		this.LeftLegMainLower = this.LeftLegMain.getChild("LeftLegMainLower");
		this.LeftLegTumorsCluster2 = this.LeftLegMainLower.getChild("LeftLegTumorsCluster2");
		this.LeftLegWear = this.LeftLegMain.getChild("LeftLegWear");
		this.LeftLegFoot = this.LeftLegMain.getChild("LeftLegFoot");
		this.Head = this.Charger.getChild("Head");
		this.MainHead = this.Head.getChild("MainHead");
		this.RightHead = this.MainHead.getChild("RightHead");
		this.LeftHead = this.MainHead.getChild("LeftHead");
		this.TopHead = this.MainHead.getChild("TopHead");
		this.HeadFoliage = this.MainHead.getChild("HeadFoliage");
		this.ClickerFoliage = this.HeadFoliage.getChild("ClickerFoliage");
		this.HeadFoliageCrown = this.HeadFoliage.getChild("HeadFoliageCrown");
		this.SparseHeadFoliage = this.HeadFoliage.getChild("SparseHeadFoliage");
		this.RightEar = this.MainHead.getChild("RightEar");
		this.LeftEar = this.MainHead.getChild("LeftEar");
		this.HeadWear = this.MainHead.getChild("HeadWear");
		this.LowerJaw = this.Head.getChild("LowerJaw");
		this.RightSplitJaw = this.LowerJaw.getChild("RightSplitJaw");
		this.LeftSplitJaw = this.LowerJaw.getChild("LeftSplitJaw");
		this.HeadTumors = this.Head.getChild("HeadTumors");
		this.HeadTumorsCluster1 = this.HeadTumors.getChild("HeadTumorsCluster1");
		this.HeadTumorsCluster2 = this.HeadTumors.getChild("HeadTumorsCluster2");
		this.Torso = this.Charger.getChild("Torso");
		this.Chest = this.Torso.getChild("Chest");
		this.ChestFoliage = this.Chest.getChild("ChestFoliage");
		this.BackFoliageCrown = this.ChestFoliage.getChild("BackFoliageCrown");
		this.SparseChestFoliage = this.ChestFoliage.getChild("SparseChestFoliage");
		this.ChestTumorsCluster = this.Chest.getChild("ChestTumorsCluster");
		this.BackTumorsCluster = this.Chest.getChild("BackTumorsCluster");
		this.Stomach = this.Torso.getChild("Stomach");
		this.Armor = this.Torso.getChild("Armor");
		this.Arms = this.Charger.getChild("Arms");
		this.RightArm = this.Arms.getChild("RightArm");
		this.RightArmMain = this.RightArm.getChild("RightArmMain");
		this.RightArmWear = this.RightArmMain.getChild("RightArmWear");
		this.RightHand = this.RightArm.getChild("RightHand");
		this.RightArmClaws = this.RightHand.getChild("RightArmClaws");
		this.LeftArm = this.Arms.getChild("LeftArm");
		this.LeftArmMain = this.LeftArm.getChild("LeftArmMain");
		this.LeftArmWear = this.LeftArmMain.getChild("LeftArmWear");
		this.LeftHand = this.LeftArm.getChild("LeftHand");
		this.LeftArmClaws = this.LeftHand.getChild("LeftArmClaws");
		this.ArmorList = List.of(HeadWear,LeftArmWear,RightArmWear,RightLegWear,LeftLegWear,Armor);
		this.HeadList = List.of(Charger,Head,MainHead);
		this.ChestList = List.of(Charger,Torso);
		this.RightArmList = List.of(Charger,Arms,RightArm,RightArmMain);
		this.LeftArmList = List.of(Charger,Arms,LeftArm,LeftArmMain);
		this.RightLegList = List.of(Charger,Legs,RightLeg,RightLegMain);
		this.LeftLegList = List.of(Charger,Legs,LeftLeg,LeftLegMain);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Charger = partdefinition.addOrReplaceChild("Charger", CubeListBuilder.create(), PartPose.offsetAndRotation(1.2805F, 23.0888F, 6.2361F, 1.3963F, 0.0F, 0.0F));

		PartDefinition Legs = Charger.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(1.5695F, 12.9112F, 2.0639F));

		PartDefinition RightLeg = Legs.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.65F, -17.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition RightLegMain = RightLeg.addOrReplaceChild("RightLegMain", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition RightLegMainUpper = RightLegMain.addOrReplaceChild("RightLegMainUpper", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition SparseRightLegPetal3_r1 = RightLegMainUpper.addOrReplaceChild("SparseRightLegPetal3_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, 3.9F, -0.4F, -0.2523F, -0.0548F, -0.1599F));

		PartDefinition SparseRightLegPetal2_r1 = RightLegMainUpper.addOrReplaceChild("SparseRightLegPetal2_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 3.9F, -4.9F, 0.4419F, -0.1815F, -0.1268F));

		PartDefinition RightLegUpper_r1 = RightLegMainUpper.addOrReplaceChild("RightLegUpper_r1", CubeListBuilder.create().texOffs(40, 76).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.2F, 2.5024F, -0.591F, -0.1308F, 0.0046F, 0.0346F));

		PartDefinition RightLegMainLower = RightLegMain.addOrReplaceChild("RightLegMainLower", CubeListBuilder.create(), PartPose.offset(0.0F, 5.5F, -0.5F));

		PartDefinition SparseRightLegPetal1_r1 = RightLegMainLower.addOrReplaceChild("SparseRightLegPetal1_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, 4.8F, -3.3F, 0.3183F, -0.1002F, 0.1239F));

		PartDefinition RightLegLower_r1 = RightLegMainLower.addOrReplaceChild("RightLegLower_r1", CubeListBuilder.create().texOffs(24, 76).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 3.3214F, 0.0737F, 0.1314F, 0.0865F, 0.0114F));

		PartDefinition RightLegFoliageCrown = RightLegMainLower.addOrReplaceChild("RightLegFoliageCrown", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.3218F, 3.2575F, 2.9942F, -2.6878F, -1.0901F, 0.7476F));

		PartDefinition RightLegCrownPetal4_r1 = RightLegFoliageCrown.addOrReplaceChild("RightLegCrownPetal4_r1", CubeListBuilder.create().texOffs(48, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.65F, -0.025F, -1.775F, 0.5635F, 0.4364F, 1.3434F));

		PartDefinition RightLegCrownPetal3_r1 = RightLegFoliageCrown.addOrReplaceChild("RightLegCrownPetal3_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.35F, 1.975F, -0.975F, -2.6409F, -0.7028F, 0.2744F));

		PartDefinition RightLegCrownPetal2_r1 = RightLegFoliageCrown.addOrReplaceChild("RightLegCrownPetal2_r1", CubeListBuilder.create().texOffs(56, 37).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.85F, -0.025F, 1.325F, 1.5078F, 0.4065F, 1.3905F));

		PartDefinition RightLegCrownPetal1_r1 = RightLegFoliageCrown.addOrReplaceChild("RightLegCrownPetal1_r1", CubeListBuilder.create().texOffs(48, 64).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.85F, -1.925F, 1.425F, 1.2151F, -0.5845F, 0.0447F));

		PartDefinition RightLegWear = RightLegMain.addOrReplaceChild("RightLegWear", CubeListBuilder.create().texOffs(0, 64).addBox(-8.0F, -12.0F, -8.0F, 16.0F, 24.0F, 16.0F, new CubeDeformation(-5.4F)), PartPose.offset(0.1F, 5.4F, -0.6F));

		PartDefinition RightLegFoot = RightLegMain.addOrReplaceChild("RightLegFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 12.2F, 0.2F));

		PartDefinition SparseRightLegPetal4_r1 = RightLegFoot.addOrReplaceChild("SparseRightLegPetal4_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9342F, 0.6119F, 0.9087F, -0.1057F, -0.524F, -0.416F));

		PartDefinition RightFoot_r1 = RightLegFoot.addOrReplaceChild("RightFoot_r1", CubeListBuilder.create().texOffs(32, 87).addBox(-2.0F, -1.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.1F, 3.8F, -0.9F, 0.0F, 0.0524F, 0.0F));

		PartDefinition RightLegAnkle_r1 = RightLegFoot.addOrReplaceChild("RightLegAnkle_r1", CubeListBuilder.create().texOffs(88, 79).addBox(-2.0F, -2.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(0.1F, 1.5385F, -0.1874F, 0.0441F, 0.0865F, 0.0114F));

		PartDefinition RightLegTumorsCluster = RightLegFoot.addOrReplaceChild("RightLegTumorsCluster", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5189F, 2.0278F, -2.0705F, 0.0F, 0.0F, 1.1868F));

		PartDefinition RightLegTumor2_r1 = RightLegTumorsCluster.addOrReplaceChild("RightLegTumor2_r1", CubeListBuilder.create().texOffs(16, 97).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.8F)), PartPose.offsetAndRotation(-0.3F, 0.55F, 0.3F, 1.7483F, 0.5063F, 2.7817F));

		PartDefinition RightLegTumor1_r1 = RightLegTumorsCluster.addOrReplaceChild("RightLegTumor1_r1", CubeListBuilder.create().texOffs(96, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(0.3F, -0.55F, -0.3F, 0.056F, -0.3614F, 0.7137F));

		PartDefinition LeftLeg = Legs.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.35F, -17.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition LeftLegMain = LeftLeg.addOrReplaceChild("LeftLegMain", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 0.7F));

		PartDefinition LeftLegMainUpper = LeftLegMain.addOrReplaceChild("LeftLegMainUpper", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition SparseLeftLegPetal3_r1 = LeftLegMainUpper.addOrReplaceChild("SparseLeftLegPetal3_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9F, 1.4F, 2.6F, -2.6871F, -0.3345F, 2.8635F));

		PartDefinition LeftLegUpper_r1 = LeftLegMainUpper.addOrReplaceChild("LeftLegUpper_r1", CubeListBuilder.create().texOffs(56, 76).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.3F, 2.5024F, -0.191F, -0.1285F, -0.0504F, -0.0501F));

		PartDefinition LeftLegTumorsCluster1 = LeftLegMainUpper.addOrReplaceChild("LeftLegTumorsCluster1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.6144F, 5.7445F, -2.6372F, 2.691F, -0.1603F, -0.1487F));

		PartDefinition LeftLegTumor3_r1 = LeftLegTumorsCluster1.addOrReplaceChild("LeftLegTumor3_r1", CubeListBuilder.create().texOffs(16, 97).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.8F)), PartPose.offsetAndRotation(-0.0333F, 0.7333F, 0.4667F, 1.7483F, 0.5063F, 2.7817F));

		PartDefinition LeftLegTumor2_r1 = LeftLegTumorsCluster1.addOrReplaceChild("LeftLegTumor2_r1", CubeListBuilder.create().texOffs(96, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(0.5667F, -0.3667F, -0.1333F, 0.056F, -0.3614F, 0.7137F));

		PartDefinition LeftLegTumor1_r1 = LeftLegTumorsCluster1.addOrReplaceChild("LeftLegTumor1_r1", CubeListBuilder.create().texOffs(96, 71).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.6856F, 0.7555F, -0.2628F, 0.4553F, 0.2682F, -0.496F));

		PartDefinition LeftLegMainLower = LeftLegMain.addOrReplaceChild("LeftLegMainLower", CubeListBuilder.create(), PartPose.offset(0.4F, 5.5F, -0.1F));

		PartDefinition SparseLeftLegPetal2_r1 = LeftLegMainLower.addOrReplaceChild("SparseLeftLegPetal2_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7F, 3.6F, 2.3F, -3.0052F, -0.5141F, -2.7555F));

		PartDefinition LeftLegLower_r1 = LeftLegMainLower.addOrReplaceChild("LeftLegLower_r1", CubeListBuilder.create().texOffs(72, 79).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.3214F, -0.0263F, 0.1314F, -0.0865F, -0.0114F));

		PartDefinition LeftLegTumorsCluster2 = LeftLegMainLower.addOrReplaceChild("LeftLegTumorsCluster2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.1694F, 6.6834F, 1.9063F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LeftLegTumor5_r1 = LeftLegTumorsCluster2.addOrReplaceChild("LeftLegTumor5_r1", CubeListBuilder.create().texOffs(88, 0).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(0.3518F, 0.2519F, 0.2236F, 0.4155F, 0.6997F, -0.7174F));

		PartDefinition LeftLegTumor4_r1 = LeftLegTumorsCluster2.addOrReplaceChild("LeftLegTumor4_r1", CubeListBuilder.create().texOffs(96, 59).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.8694F, 0.5166F, 0.5937F, 1.0097F, 0.2924F, -0.4477F));

		PartDefinition LeftLegWear = LeftLegMain.addOrReplaceChild("LeftLegWear", CubeListBuilder.create().texOffs(0, 64).addBox(-8.0F, -12.0F, -8.0F, 16.0F, 24.0F, 16.0F, new CubeDeformation(-5.4F)), PartPose.offset(0.5F, 5.4F, -0.3F));

		PartDefinition LeftLegFoot = LeftLegMain.addOrReplaceChild("LeftLegFoot", CubeListBuilder.create(), PartPose.offset(0.5F, 12.2F, 0.5F));

		PartDefinition SparseLeftLegPetal1_r1 = LeftLegFoot.addOrReplaceChild("SparseLeftLegPetal1_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7F, 0.8F, -1.0F, 2.6322F, -0.5141F, -2.7555F));

		PartDefinition LeftFoot_r1 = LeftLegFoot.addOrReplaceChild("LeftFoot_r1", CubeListBuilder.create().texOffs(50, 87).addBox(-2.0F, -1.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.1F, 3.8F, -0.9F, 0.0F, -0.0524F, 0.0F));

		PartDefinition LeftLegAnkle_r1 = LeftLegFoot.addOrReplaceChild("LeftLegAnkle_r1", CubeListBuilder.create().texOffs(88, 87).addBox(-2.0F, -2.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(-0.1F, 1.5385F, -0.1874F, 0.0441F, -0.0865F, -0.0114F));

		PartDefinition Head = Charger.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.2805F, -19.3575F, 2.6636F, -1.6581F, 0.0F, 0.0F));

		PartDefinition MainHead = Head.addOrReplaceChild("MainHead", CubeListBuilder.create(), PartPose.offset(0.0F, 1.3F, 3.2F));

		PartDefinition Skull_r1 = MainHead.addOrReplaceChild("Skull_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.5F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(-0.2F))
				.texOffs(32, 0).addBox(-4.0F, 3.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(64, 98).addBox(-1.0F, -3.5F, 3.0F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1435F, -3.4843F, -3.306F, -0.0873F, 0.0F, 0.0436F));

		PartDefinition RightHead = MainHead.addOrReplaceChild("RightHead", CubeListBuilder.create(), PartPose.offset(0.0F, -1.3F, -3.2F));

		PartDefinition RightHeadBottomHalfBack_r1 = RightHead.addOrReplaceChild("RightHeadBottomHalfBack_r1", CubeListBuilder.create().texOffs(94, 49).addBox(-4.0F, -3.5F, 1.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(96, 27).addBox(-4.0F, -3.5F, -2.0F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(64, 6).addBox(-4.0F, 2.5F, -4.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(94, 95).addBox(-4.0F, -3.5F, -4.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1435F, -2.1843F, -0.106F, -0.0873F, 0.0F, 0.0436F));

		PartDefinition LeftHead = MainHead.addOrReplaceChild("LeftHead", CubeListBuilder.create(), PartPose.offset(0.0F, -1.3F, -3.2F));

		PartDefinition LeftHeadBottomHalfBack_r1 = LeftHead.addOrReplaceChild("LeftHeadBottomHalfBack_r1", CubeListBuilder.create().texOffs(84, 95).addBox(-1.0F, -3.5F, 0.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(74, 98).addBox(-2.0F, -3.5F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 27).addBox(-3.0F, 1.5F, -4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(96, 37).addBox(-4.0F, -3.5F, -4.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.1435F, -1.9843F, -0.106F, -0.0873F, 0.0F, 0.0436F));

		PartDefinition TopHead = MainHead.addOrReplaceChild("TopHead", CubeListBuilder.create(), PartPose.offset(0.0F, -1.3F, -3.2F));

		PartDefinition TopHead3_r1 = TopHead.addOrReplaceChild("TopHead3_r1", CubeListBuilder.create().texOffs(70, 27).addBox(-1.0F, -3.5F, 1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(14, 59).addBox(2.0F, -3.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 59).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1435F, -2.1843F, -0.106F, -0.0873F, 0.0F, 0.0436F));

		PartDefinition HeadFoliage = MainHead.addOrReplaceChild("HeadFoliage", CubeListBuilder.create(), PartPose.offset(0.0F, -1.3F, -3.2F));

		PartDefinition ClickerFoliage = HeadFoliage.addOrReplaceChild("ClickerFoliage", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ClickerPetal5_r1 = ClickerFoliage.addOrReplaceChild("ClickerPetal5_r1", CubeListBuilder.create().texOffs(60, 15).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, 0.4687F, -3.1997F, -2.2571F, 1.1063F, -2.0097F));

		PartDefinition ClickerPetal4_r1 = ClickerFoliage.addOrReplaceChild("ClickerPetal4_r1", CubeListBuilder.create().texOffs(60, 15).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.6313F, -3.7997F, -0.9802F, 1.4097F, -0.4631F));

		PartDefinition ClickerPetal3_r1 = ClickerFoliage.addOrReplaceChild("ClickerPetal3_r1", CubeListBuilder.create().texOffs(60, 15).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.1313F, -3.3997F, -1.9606F, 1.0223F, -2.0013F));

		PartDefinition ClickerPetal2_r1 = ClickerFoliage.addOrReplaceChild("ClickerPetal2_r1", CubeListBuilder.create().texOffs(60, 15).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2F, -1.6313F, -4.6997F, 1.7552F, 0.0711F, 1.3305F));

		PartDefinition ClickerPetal1_r1 = ClickerFoliage.addOrReplaceChild("ClickerPetal1_r1", CubeListBuilder.create().texOffs(60, 15).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.7313F, -4.1997F, 1.6437F, -0.0691F, -0.7522F));

		PartDefinition HeadFoliageCrown = HeadFoliage.addOrReplaceChild("HeadFoliageCrown", CubeListBuilder.create(), PartPose.offset(2.9782F, -5.5738F, -3.5054F));

		PartDefinition HeadCrownPetal4_r1 = HeadFoliageCrown.addOrReplaceChild("HeadCrownPetal4_r1", CubeListBuilder.create().texOffs(48, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.65F, -0.025F, -1.775F, 0.5635F, 0.4364F, 1.3434F));

		PartDefinition HeadCrownPetal3_r1 = HeadFoliageCrown.addOrReplaceChild("HeadCrownPetal3_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.35F, 1.975F, -0.975F, 2.4227F, 0.4746F, 3.029F));

		PartDefinition HeadCrownPetal2_r1 = HeadFoliageCrown.addOrReplaceChild("HeadCrownPetal2_r1", CubeListBuilder.create().texOffs(56, 37).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.85F, -0.025F, 1.325F, 1.5078F, 0.4065F, 1.3905F));

		PartDefinition HeadCrownPetal1_r1 = HeadFoliageCrown.addOrReplaceChild("HeadCrownPetal1_r1", CubeListBuilder.create().texOffs(48, 64).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.85F, -1.925F, 1.425F, 1.2151F, -0.5845F, 0.0447F));

		PartDefinition SparseHeadFoliage = HeadFoliage.addOrReplaceChild("SparseHeadFoliage", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition SparseHeadPetal4_r1 = SparseHeadFoliage.addOrReplaceChild("SparseHeadPetal4_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2F, -1.7313F, 1.5003F, -0.5369F, -0.0903F, 0.431F));

		PartDefinition SparseHeadPetal3_r1 = SparseHeadFoliage.addOrReplaceChild("SparseHeadPetal3_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.5313F, 2.1003F, 0.8201F, 0.1906F, -0.4246F));

		PartDefinition SparseHeadPetal2_r1 = SparseHeadFoliage.addOrReplaceChild("SparseHeadPetal2_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8F, -0.7313F, -0.6997F, 0.0612F, 0.1455F, -0.3706F));

		PartDefinition SparseHeadPetal1_r1 = SparseHeadFoliage.addOrReplaceChild("SparseHeadPetal1_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, -2.7313F, 2.9003F, -0.122F, -0.0064F, -0.1916F));

		PartDefinition RightEar = MainHead.addOrReplaceChild("RightEar", CubeListBuilder.create(), PartPose.offset(-0.7F, -5.7313F, -2.2997F));

		PartDefinition RightEar_r1 = RightEar.addOrReplaceChild("RightEar_r1", CubeListBuilder.create().texOffs(48, 49).addBox(-10.7956F, -8.9985F, -0.9164F, 11.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0175F, -0.1047F, 0.0F));

		PartDefinition LeftEar = MainHead.addOrReplaceChild("LeftEar", CubeListBuilder.create(), PartPose.offset(3.0F, -6.3F, -3.2F));

		PartDefinition LeftEar_r1 = LeftEar.addOrReplaceChild("LeftEar_r1", CubeListBuilder.create().texOffs(37, 49).addBox(-0.4841F, -8.9007F, -1.0F, 11.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, -0.0313F, 1.2003F, 0.0F, 0.0F, 0.2618F));

		PartDefinition HeadWear = MainHead.addOrReplaceChild("HeadWear", CubeListBuilder.create(), PartPose.offset(0.2F, -2.7313F, -3.0997F));

		PartDefinition Helmet_r1 = HeadWear.addOrReplaceChild("Helmet_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -16.0F, -16.0F, 32.0F, 32.0F, 32.0F, new CubeDeformation(-11.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0436F));

		PartDefinition LowerJaw = Head.addOrReplaceChild("LowerJaw", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightSplitJaw = LowerJaw.addOrReplaceChild("RightSplitJaw", CubeListBuilder.create(), PartPose.offset(-2.3F, 1.0F, 3.1F));

		PartDefinition RightJawTeeth_r1 = RightSplitJaw.addOrReplaceChild("RightJawTeeth_r1", CubeListBuilder.create().texOffs(24, 49).addBox(-2.0F, -1.5F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 31).addBox(-2.0F, -0.5F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2814F, 1.9951F, -3.4302F, 0.3876F, 0.1355F, 0.0183F));

		PartDefinition LeftSplitJaw = LowerJaw.addOrReplaceChild("LeftSplitJaw", CubeListBuilder.create(), PartPose.offset(1.9F, 1.3F, 2.4F));

		PartDefinition LeftJawTeeth_r1 = LeftSplitJaw.addOrReplaceChild("LeftJawTeeth_r1", CubeListBuilder.create().texOffs(0, 41).addBox(-4.0F, -1.5F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 40).addBox(-4.0F, -0.5F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 2.5313F, -2.6003F, 0.6611F, -0.1322F, -0.1412F));

		PartDefinition HeadTumors = Head.addOrReplaceChild("HeadTumors", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HeadTumorsCluster1 = HeadTumors.addOrReplaceChild("HeadTumorsCluster1", CubeListBuilder.create(), PartPose.offset(-1.0484F, -0.1019F, 4.27F));

		PartDefinition HeadTumor4_r1 = HeadTumorsCluster1.addOrReplaceChild("HeadTumor4_r1", CubeListBuilder.create().texOffs(0, 101).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(-1.3516F, -1.6294F, -1.4697F, -0.802F, 0.296F, 1.3836F));

		PartDefinition HeadTumor3_r1 = HeadTumorsCluster1.addOrReplaceChild("HeadTumor3_r1", CubeListBuilder.create().texOffs(100, 18).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(-1.5516F, -0.3294F, -1.7697F, -0.8824F, 0.1326F, 1.8527F));

		PartDefinition HeadTumor2_r1 = HeadTumorsCluster1.addOrReplaceChild("HeadTumor2_r1", CubeListBuilder.create().texOffs(28, 102).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-1.0516F, 0.6706F, -0.5697F, -0.7116F, -0.1886F, 1.1313F));

		PartDefinition HeadTumor1_r1 = HeadTumorsCluster1.addOrReplaceChild("HeadTumor1_r1", CubeListBuilder.create().texOffs(100, 12).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0484F, 0.6706F, -1.3697F, -0.48F, 0.0F, 0.3927F));

		PartDefinition HeadTumorsCluster2 = HeadTumors.addOrReplaceChild("HeadTumorsCluster2", CubeListBuilder.create(), PartPose.offset(2.9845F, -4.5074F, 3.2826F));

		PartDefinition HeadTumor6_r1 = HeadTumorsCluster2.addOrReplaceChild("HeadTumor6_r1", CubeListBuilder.create().texOffs(100, 6).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(-1.0845F, 0.2761F, -0.4823F, 0.0689F, 0.181F, 1.2155F));

		PartDefinition HeadTumor5_r1 = HeadTumorsCluster2.addOrReplaceChild("HeadTumor5_r1", CubeListBuilder.create().texOffs(100, 0).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.7845F, 0.6761F, -0.3823F, -0.2368F, 0.4147F, 0.3821F));

		PartDefinition Torso = Charger.addOrReplaceChild("Torso", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0714F, -7.1962F, 1.0421F, -0.3054F, 0.0F, 0.0F));

		PartDefinition Chest = Torso.addOrReplaceChild("Chest", CubeListBuilder.create(), PartPose.offset(-0.6303F, -0.0029F, -0.9793F));

		PartDefinition ChestArmor_r1 = Chest.addOrReplaceChild("ChestArmor_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-5.0F, -3.5F, -3.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.3F))
				.texOffs(0, 15).addBox(-5.0F, -3.5F, -3.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(1.3458F, -5.4294F, -0.5363F, 0.5067F, -0.0183F, 0.0093F));

		PartDefinition ChestFoliage = Chest.addOrReplaceChild("ChestFoliage", CubeListBuilder.create(), PartPose.offset(1.4211F, 20.3103F, 1.7012F));

		PartDefinition BackFoliageCrown = ChestFoliage.addOrReplaceChild("BackFoliageCrown", CubeListBuilder.create(), PartPose.offsetAndRotation(1.7782F, -27.8425F, 0.9942F, -1.9487F, -0.5531F, 0.009F));

		PartDefinition BaclCrownPetal4_r1 = BackFoliageCrown.addOrReplaceChild("BaclCrownPetal4_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.65F, -0.025F, -1.775F, 0.5635F, 0.4364F, 1.3434F));

		PartDefinition BackCrownPetal3_r1 = BackFoliageCrown.addOrReplaceChild("BackCrownPetal3_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, 1.975F, -0.975F, 2.4594F, 0.5309F, 3.1051F));

		PartDefinition BackCrownPetal2_r1 = BackFoliageCrown.addOrReplaceChild("BackCrownPetal2_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.85F, -0.025F, 1.325F, 1.5078F, 0.4065F, 1.3905F));

		PartDefinition BackCrownPetal1_r1 = BackFoliageCrown.addOrReplaceChild("BackCrownPetal1_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.85F, -1.925F, 1.425F, 1.2151F, -0.5845F, 0.0447F));

		PartDefinition SparseChestFoliage = ChestFoliage.addOrReplaceChild("SparseChestFoliage", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition SparseBackPetal2_r1 = SparseChestFoliage.addOrReplaceChild("SparseBackPetal2_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, -26.3F, -0.2F, -0.2592F, 0.0907F, -0.0741F));

		PartDefinition SparseBackPetal1_r1 = SparseChestFoliage.addOrReplaceChild("SparseBackPetal1_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1F, -29.1F, -1.2F, -0.219F, -0.0852F, 0.0189F));

		PartDefinition SparseChestPetal2_r1 = SparseChestFoliage.addOrReplaceChild("SparseChestPetal2_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -22.5F, -5.4F, 0.9002F, -0.1508F, 0.1876F));

		PartDefinition SparseChestPetal1_r1 = SparseChestFoliage.addOrReplaceChild("SparseChestPetal1_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, -23.1F, -6.0F, 0.956F, 0.3316F, -0.1319F));

		PartDefinition ChestTumorsCluster = Chest.addOrReplaceChild("ChestTumorsCluster", CubeListBuilder.create(), PartPose.offset(-1.2644F, -4.8452F, -3.9361F));

		PartDefinition ChestTumor3_r1 = ChestTumorsCluster.addOrReplaceChild("ChestTumor3_r1", CubeListBuilder.create().texOffs(16, 97).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.8F)), PartPose.offsetAndRotation(-0.0333F, 0.7333F, 0.4667F, 1.7483F, 0.5063F, 2.7817F));

		PartDefinition ChestTumor2_r1 = ChestTumorsCluster.addOrReplaceChild("ChestTumor2_r1", CubeListBuilder.create().texOffs(96, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(0.5667F, -0.3667F, -0.1333F, 0.056F, -0.3614F, 0.7137F));

		PartDefinition ChestTumor1_r1 = ChestTumorsCluster.addOrReplaceChild("ChestTumor1_r1", CubeListBuilder.create().texOffs(96, 71).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.6856F, 0.7555F, -0.2628F, 0.4553F, 0.2682F, -0.496F));

		PartDefinition BackTumorsCluster = Chest.addOrReplaceChild("BackTumorsCluster", CubeListBuilder.create(), PartPose.offset(-2.8483F, -4.6063F, 3.3075F));

		PartDefinition BackTumor2_r1 = BackTumorsCluster.addOrReplaceChild("BackTumor2_r1", CubeListBuilder.create().texOffs(88, 0).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(0.3518F, 0.2519F, 0.2236F, 0.4155F, 0.6997F, -0.7174F));

		PartDefinition BackTumor1_r1 = BackTumorsCluster.addOrReplaceChild("BackTumor1_r1", CubeListBuilder.create().texOffs(96, 59).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.8694F, 0.5166F, 0.5937F, 1.0097F, 0.2924F, -0.4477F));

		PartDefinition Stomach = Torso.addOrReplaceChild("Stomach", CubeListBuilder.create(), PartPose.offset(0.6303F, 0.0029F, 0.9793F));

		PartDefinition StomachArmor_r1 = Stomach.addOrReplaceChild("StomachArmor_r1", CubeListBuilder.create().texOffs(32, 20).addBox(-5.0F, -3.0F, -2.0F, 9.0F, 6.0F, 5.0F, new CubeDeformation(0.1F))
				.texOffs(32, 9).addBox(-5.0F, -3.0F, -2.0F, 9.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5083F, 0.1421F, -0.4706F, 0.2879F, -0.0176F, 0.0003F));

		PartDefinition Armor = Torso.addOrReplaceChild("Armor", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = Armor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 60).addBox(-11.0F, -20.0F, -10.0F, 28.0F, 28.0F, 20.0F, new CubeDeformation(-9.25F)), PartPose.offsetAndRotation(-2.25F, 2.0F, -0.5F, 0.5236F, 0.0F, 0.0F));

		PartDefinition Arms = Charger.addOrReplaceChild("Arms", CubeListBuilder.create(), PartPose.offset(-1.3565F, -14.1798F, -1.8974F));

		PartDefinition RightArm = Arms.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.2177F, 0.8844F, 0.9373F, -2.2766F, 1.039F, 0.6477F));

		PartDefinition RightArmMain = RightArm.addOrReplaceChild("RightArmMain", CubeListBuilder.create(), PartPose.offset(-0.0063F, 0.0705F, -0.0465F));

		PartDefinition SparseRightArmPetal1_r1 = RightArmMain.addOrReplaceChild("SparseRightArmPetal1_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, 5.6F, -3.9F, 0.2715F, -0.0392F, -0.1498F));

		PartDefinition RightArmUpper_r1 = RightArmMain.addOrReplaceChild("RightArmUpper_r1", CubeListBuilder.create().texOffs(80, 27).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-0.9F, 3.6F, -1.3F, -0.0698F, -0.0024F, -0.0348F));

		PartDefinition RightShoulderArmor_r1 = RightArmMain.addOrReplaceChild("RightShoulderArmor_r1", CubeListBuilder.create().texOffs(32, 94).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F))
				.texOffs(68, 90).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-0.9F, 0.0F, -0.9F, -0.1396F, 0.0F, 0.0F));

		PartDefinition RightArmWear = RightArmMain.addOrReplaceChild("RightArmWear", CubeListBuilder.create(), PartPose.offset(-1.9F, 0.0793F, 0.0155F));

		PartDefinition ChestplateRightSleeve_r1 = RightArmWear.addOrReplaceChild("ChestplateRightSleeve_r1", CubeListBuilder.create().texOffs(160, 64).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(-5.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1396F, 0.0F, 0.0F));

		PartDefinition RightHand = RightArm.addOrReplaceChild("RightHand", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.7202F, 8.3877F, -0.7349F, 0.0F, 0.0F, -0.0873F));

		PartDefinition SparseRightArmPetal2_r1 = RightHand.addOrReplaceChild("SparseRightArmPetal2_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3271F, 1.9346F, -2.2116F, -0.0703F, -0.0955F, -0.5128F));

		PartDefinition RightArmLower_r1 = RightHand.addOrReplaceChild("RightArmLower_r1", CubeListBuilder.create().texOffs(84, 17).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(1.0729F, 1.7346F, -1.1116F, -0.0696F, -0.0049F, -0.0696F));

		PartDefinition RightArmClaws = RightHand.addOrReplaceChild("RightArmClaws", CubeListBuilder.create(), PartPose.offset(6.9729F, 19.0346F, 4.1884F));

		PartDefinition RightClaw4_r1 = RightArmClaws.addOrReplaceChild("RightClaw4_r1", CubeListBuilder.create().texOffs(106, 110).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1F, -11.6F, -5.1F, -0.0695F, -0.0067F, -0.0958F));

		PartDefinition RightClaw3_r1 = RightArmClaws.addOrReplaceChild("RightClaw3_r1", CubeListBuilder.create().texOffs(98, 103).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -11.6F, -5.7F, -0.0692F, -0.0091F, -0.1306F));

		PartDefinition RightClaw2_r1 = RightArmClaws.addOrReplaceChild("RightClaw2_r1", CubeListBuilder.create().texOffs(98, 103).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -11.6F, -4.7F, -0.0692F, -0.0091F, -0.1306F));

		PartDefinition RightClaw1_r1 = RightArmClaws.addOrReplaceChild("RightClaw1_r1", CubeListBuilder.create().texOffs(98, 103).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -11.6F, -3.6F, -0.0692F, -0.0091F, -0.1306F));

		PartDefinition LeftArm = Arms.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(6.2177F, 0.1156F, 1.0627F, -2.1213F, -1.0886F, -0.6271F));

		PartDefinition LeftArmMain = LeftArm.addOrReplaceChild("LeftArmMain", CubeListBuilder.create(), PartPose.offset(-0.0417F, 0.0623F, 0.222F));

		PartDefinition SparseLeftArmPetal2_r1 = LeftArmMain.addOrReplaceChild("SparseLeftArmPetal2_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 5.8002F, -3.893F, 0.2976F, -0.04F, -0.0772F));

		PartDefinition SparseLeftArmPetal1_r1 = LeftArmMain.addOrReplaceChild("SparseLeftArmPetal1_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 3.4002F, -3.893F, 0.2336F, -0.1903F, 0.4649F));

		PartDefinition LeftArmUpper_r1 = LeftArmMain.addOrReplaceChild("LeftArmUpper_r1", CubeListBuilder.create().texOffs(80, 38).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(3.0F, 3.3002F, -1.493F, -0.0698F, 0.0024F, 0.0348F));

		PartDefinition LeftShoulderArmor_r1 = LeftArmMain.addOrReplaceChild("LeftShoulderArmor_r1", CubeListBuilder.create().texOffs(48, 94).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F))
				.texOffs(0, 93).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(2.1F, -0.1606F, -0.1028F, -0.1396F, 0.0F, 0.0F));

		PartDefinition LeftArmWear = LeftArmMain.addOrReplaceChild("LeftArmWear", CubeListBuilder.create().texOffs(160, 64).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(-5.5F)), PartPose.offsetAndRotation(2.1F, -0.2106F, -0.0526F, -0.1396F, 0.0F, 0.0F));

		PartDefinition LeftHand = LeftArm.addOrReplaceChild("LeftHand", CubeListBuilder.create(), PartPose.offsetAndRotation(1.8661F, 8.0285F, -0.7434F, 0.0F, 0.0F, 0.0873F));

		PartDefinition SparseLeftArmPetal3_r1 = LeftHand.addOrReplaceChild("SparseLeftArmPetal3_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0482F, 2.1616F, -3.0276F, 0.3183F, -0.1002F, 0.1239F));

		PartDefinition LeftArmLower_r1 = LeftHand.addOrReplaceChild("LeftArmLower_r1", CubeListBuilder.create().texOffs(16, 87).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.1825F, 2.8935F, -0.0974F, -0.0698F, 0.0024F, 0.0348F));

		PartDefinition LeftArmClaws = LeftHand.addOrReplaceChild("LeftArmClaws", CubeListBuilder.create(), PartPose.offset(2.3018F, 7.5616F, -0.5026F));

		PartDefinition LeftClaw4_r1 = LeftArmClaws.addOrReplaceChild("LeftClaw4_r1", CubeListBuilder.create().texOffs(102, 110).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.15F, 0.0F, -0.325F, -0.0695F, 0.0067F, 0.0958F));

		PartDefinition LeftClaw3_r1 = LeftArmClaws.addOrReplaceChild("LeftClaw3_r1", CubeListBuilder.create().texOffs(102, 103).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 0.0F, -0.925F, -0.0692F, 0.0091F, 0.1306F));

		PartDefinition LeftClaw2_r1 = LeftArmClaws.addOrReplaceChild("LeftClaw2_r1", CubeListBuilder.create().texOffs(102, 103).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 0.0F, 0.075F, -0.0692F, 0.0091F, 0.1306F));

		PartDefinition LeftClaw1_r1 = LeftArmClaws.addOrReplaceChild("LeftClaw1_r1", CubeListBuilder.create().texOffs(102, 103).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 0.0F, 1.175F, -0.0692F, 0.0091F, 0.1306F));

		return LayerDefinition.create(meshdefinition, 256, 128);
	}
	void animateArmsY(ModelPart part,float val){
		part.y = part.getInitialPose().y + val;
	}
	void animateArmsZ(ModelPart part,float val){
		part.z = part.getInitialPose().z + val;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Charger.getAllParts().forEach(ModelPart::resetPose);
		float val = Mth.sin(ageInTicks/8)/8;
		float val1 = Mth.cos(ageInTicks/9)/7;
		float val2 = Mth.sin(ageInTicks/8)/9;
		float val3 = Mth.cos(ageInTicks/7)/6;
		float val4 = Mth.sin(ageInTicks/8)/7;
		float val5 = Mth.cos(ageInTicks/6)/7;
		float val6 = Mth.sin(ageInTicks/5)/6;
		float val7 = Mth.cos(ageInTicks/6)/8;
		float moveVal = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
		animateArmsY(RightArm,moveVal * 5);
		animateArmsY(LeftArm,moveVal * -5);
		animateArmsZ(RightArm,moveVal * 3);
		animateArmsZ(LeftArm,moveVal * -3);

		float ear = entity.getEarAnimationTick();
		if (ear > 0){
			float swing = 0.5F - 0.35F * Mth.triangleWave(ear, 10.0F);
			animateTentacleZ(LeftEar,swing);
			animateTentacleZ(RightEar,-swing);
		}else {
			animateTentacleZ(LeftEar,val);
			animateTentacleZ(RightEar,-val);
		}
		animateTentacleX(RightSplitJaw,val1);
		animateTentacleX(LeftSplitJaw,val2);
		animateTentacleY(RightSplitJaw,val3);
		animateTentacleY(LeftSplitJaw,-val3);
		animateTumor(HeadTumorsCluster1,val5);
		animateTumor(HeadTumorsCluster2,val7);
		animateTumor(ChestTumorsCluster,val4);
		animateTumor(BackTumorsCluster,val6);
		animateTumor(LeftLegTumorsCluster1,val1);
		animateTumor(RightLegTumorsCluster,val);
		animateTumor(LeftLegTumorsCluster2,val2);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		Charger.getAllParts().forEach(this::setDraw);
		Charger.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
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
	public List<ModelPart> RightArmList() {
		return RightArmList;
	}

	@Override
	public List<ModelPart> LeftArmList() {
		return LeftArmList;
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
	public ModelPart RightArm() {
		return RightArmWear;
	}

	@Override
	public ModelPart LeftArm() {
		return LeftArmWear;
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