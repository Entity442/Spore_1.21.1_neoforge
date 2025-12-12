package com.Harbinger.Spore.Client.Models;// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class KrakenModel<T extends Grakensenker> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "crackermodel"), "main");
	private final ModelPart Kraken;
	private final ModelPart body;
	private final ModelPart Innerteeth;
	private final ModelPart body2;
	private final ModelPart fin;
	private final ModelPart LeftArmConnectPoint;
	private final ModelPart RightArmConnectPoint;
	private final ModelPart Tumors;
	private final ModelPart Tumor;
	private final ModelPart Tumor2;
	private final ModelPart Tumor3;
	private final ModelPart Tumor4;
	private final ModelPart Tumor5;
	private final ModelPart Tumor6;
	private final ModelPart Tumor7;
	private final ModelPart Tumor8;
	private final ModelPart Tumor9;
	private final ModelPart Tumor10;
	private final ModelPart Tumor11;
	private final ModelPart BackJaw;
	private final ModelPart Outer;
	private final ModelPart OuterRingBase;
	private final ModelPart Internal;
	private final ModelPart OuterRingDetails;
	private final ModelPart Teeth;
	private final ModelPart BackBodyMouthProtection;
	private final ModelPart HindeRotation;
	private final ModelPart Hinge1;
	private final ModelPart LeftHinge;
	private final ModelPart HingeLowerMemebrane;
	private final ModelPart HingeMiddleHinge;
	private final ModelPart HingeMiddleMemebrane;
	private final ModelPart HingeTopHinge;
	private final ModelPart LeftTopMemebrane;
	private final ModelPart HindeRotation2;
	private final ModelPart Hinge2;
	private final ModelPart LeftHinge2;
	private final ModelPart HingeLowerMemebrane2;
	private final ModelPart HingeMiddleHinge2;
	private final ModelPart HingeMiddleMemebrane2;
	private final ModelPart HingeTopHinge2;
	private final ModelPart LeftTopMemebrane2;
	private final ModelPart HindeRotation3;
	private final ModelPart Hinge3;
	private final ModelPart LeftHinge3;
	private final ModelPart HingeLowerMemebrane3;
	private final ModelPart HingeMiddleHinge3;
	private final ModelPart HingeMiddleMemebrane3;
	private final ModelPart HingeTopHinge3;
	private final ModelPart LeftTopMemebrane3;
	private final ModelPart HindeRotation4;
	private final ModelPart Hinge4;
	private final ModelPart LeftHinge4;
	private final ModelPart HingeLowerMemebrane4;
	private final ModelPart HingeMiddleHinge4;
	private final ModelPart HingeMiddleMemebrane4;
	private final ModelPart HingeTopHinge4;
	private final ModelPart LeftTopMemebrane4;
	private final ModelPart HindeRotation5;
	private final ModelPart Hinge5;
	private final ModelPart LeftHinge5;
	private final ModelPart HingeLowerMemebrane5;
	private final ModelPart HingeMiddleHinge5;
	private final ModelPart HingeMiddleMemebrane5;
	private final ModelPart HingeTopHinge5;
	private final ModelPart LeftTopMemebrane5;
	private final ModelPart FrontJaw;
	private final ModelPart jawBottom;
	private final ModelPart head1;
	private final ModelPart head2;
	private final ModelPart head4;
	private final ModelPart head5;
	private final ModelPart head3;
	private final ModelPart head6;
	private final ModelPart head7;
	private final ModelPart jawTop;
	private final ModelPart head8;
	private final ModelPart head9;
	private final ModelPart head10;
	private final ModelPart head11;
	private final ModelPart head12;
	private final ModelPart head13;
	private final ModelPart head14;
	private final ModelPart rightJaw;
	private final ModelPart head15;
	private final ModelPart head17;
	private final ModelPart head19;
	private final ModelPart leftJaw;
	private final ModelPart head16;
	private final ModelPart head18;
	private final ModelPart head20;

	public KrakenModel(ModelPart root) {
		this.Kraken = root.getChild("Kraken");
		this.body = this.Kraken.getChild("body");
		this.Innerteeth = this.body.getChild("Innerteeth");
		this.body2 = this.body.getChild("body2");
		this.fin = this.body.getChild("fin");
		this.LeftArmConnectPoint = this.Kraken.getChild("LeftArmConnectPoint");
		this.RightArmConnectPoint = this.Kraken.getChild("RightArmConnectPoint");
		this.Tumors = this.Kraken.getChild("Tumors");
		this.Tumor = this.Tumors.getChild("Tumor");
		this.Tumor2 = this.Tumors.getChild("Tumor2");
		this.Tumor3 = this.Tumors.getChild("Tumor3");
		this.Tumor4 = this.Tumors.getChild("Tumor4");
		this.Tumor5 = this.Tumors.getChild("Tumor5");
		this.Tumor6 = this.Tumors.getChild("Tumor6");
		this.Tumor7 = this.Tumors.getChild("Tumor7");
		this.Tumor8 = this.Tumors.getChild("Tumor8");
		this.Tumor9 = this.Tumors.getChild("Tumor9");
		this.Tumor10 = this.Tumors.getChild("Tumor10");
		this.Tumor11 = this.Tumors.getChild("Tumor11");
		this.BackJaw = this.Kraken.getChild("BackJaw");
		this.Outer = this.BackJaw.getChild("Outer");
		this.OuterRingBase = this.Outer.getChild("OuterRingBase");
		this.Internal = this.OuterRingBase.getChild("Internal");
		this.OuterRingDetails = this.Outer.getChild("OuterRingDetails");
		this.Teeth = this.OuterRingDetails.getChild("Teeth");
		this.BackBodyMouthProtection = this.Kraken.getChild("BackBodyMouthProtection");
		this.HindeRotation = this.BackBodyMouthProtection.getChild("HindeRotation");
		this.Hinge1 = this.HindeRotation.getChild("Hinge1");
		this.LeftHinge = this.Hinge1.getChild("LeftHinge");
		this.HingeLowerMemebrane = this.LeftHinge.getChild("HingeLowerMemebrane");
		this.HingeMiddleHinge = this.LeftHinge.getChild("HingeMiddleHinge");
		this.HingeMiddleMemebrane = this.HingeMiddleHinge.getChild("HingeMiddleMemebrane");
		this.HingeTopHinge = this.HingeMiddleHinge.getChild("HingeTopHinge");
		this.LeftTopMemebrane = this.HingeTopHinge.getChild("LeftTopMemebrane");
		this.HindeRotation2 = this.BackBodyMouthProtection.getChild("HindeRotation2");
		this.Hinge2 = this.HindeRotation2.getChild("Hinge2");
		this.LeftHinge2 = this.Hinge2.getChild("LeftHinge2");
		this.HingeLowerMemebrane2 = this.LeftHinge2.getChild("HingeLowerMemebrane2");
		this.HingeMiddleHinge2 = this.LeftHinge2.getChild("HingeMiddleHinge2");
		this.HingeMiddleMemebrane2 = this.HingeMiddleHinge2.getChild("HingeMiddleMemebrane2");
		this.HingeTopHinge2 = this.HingeMiddleHinge2.getChild("HingeTopHinge2");
		this.LeftTopMemebrane2 = this.HingeTopHinge2.getChild("LeftTopMemebrane2");
		this.HindeRotation3 = this.BackBodyMouthProtection.getChild("HindeRotation3");
		this.Hinge3 = this.HindeRotation3.getChild("Hinge3");
		this.LeftHinge3 = this.Hinge3.getChild("LeftHinge3");
		this.HingeLowerMemebrane3 = this.LeftHinge3.getChild("HingeLowerMemebrane3");
		this.HingeMiddleHinge3 = this.LeftHinge3.getChild("HingeMiddleHinge3");
		this.HingeMiddleMemebrane3 = this.HingeMiddleHinge3.getChild("HingeMiddleMemebrane3");
		this.HingeTopHinge3 = this.HingeMiddleHinge3.getChild("HingeTopHinge3");
		this.LeftTopMemebrane3 = this.HingeTopHinge3.getChild("LeftTopMemebrane3");
		this.HindeRotation4 = this.BackBodyMouthProtection.getChild("HindeRotation4");
		this.Hinge4 = this.HindeRotation4.getChild("Hinge4");
		this.LeftHinge4 = this.Hinge4.getChild("LeftHinge4");
		this.HingeLowerMemebrane4 = this.LeftHinge4.getChild("HingeLowerMemebrane4");
		this.HingeMiddleHinge4 = this.LeftHinge4.getChild("HingeMiddleHinge4");
		this.HingeMiddleMemebrane4 = this.HingeMiddleHinge4.getChild("HingeMiddleMemebrane4");
		this.HingeTopHinge4 = this.HingeMiddleHinge4.getChild("HingeTopHinge4");
		this.LeftTopMemebrane4 = this.HingeTopHinge4.getChild("LeftTopMemebrane4");
		this.HindeRotation5 = this.BackBodyMouthProtection.getChild("HindeRotation5");
		this.Hinge5 = this.HindeRotation5.getChild("Hinge5");
		this.LeftHinge5 = this.Hinge5.getChild("LeftHinge5");
		this.HingeLowerMemebrane5 = this.LeftHinge5.getChild("HingeLowerMemebrane5");
		this.HingeMiddleHinge5 = this.LeftHinge5.getChild("HingeMiddleHinge5");
		this.HingeMiddleMemebrane5 = this.HingeMiddleHinge5.getChild("HingeMiddleMemebrane5");
		this.HingeTopHinge5 = this.HingeMiddleHinge5.getChild("HingeTopHinge5");
		this.LeftTopMemebrane5 = this.HingeTopHinge5.getChild("LeftTopMemebrane5");
		this.FrontJaw = this.Kraken.getChild("FrontJaw");
		this.jawBottom = this.FrontJaw.getChild("jawBottom");
		this.head1 = this.jawBottom.getChild("head1");
		this.head2 = this.jawBottom.getChild("head2");
		this.head4 = this.jawBottom.getChild("head4");
		this.head5 = this.jawBottom.getChild("head5");
		this.head3 = this.jawBottom.getChild("head3");
		this.head6 = this.jawBottom.getChild("head6");
		this.head7 = this.jawBottom.getChild("head7");
		this.jawTop = this.FrontJaw.getChild("jawTop");
		this.head8 = this.jawTop.getChild("head8");
		this.head9 = this.jawTop.getChild("head9");
		this.head10 = this.jawTop.getChild("head10");
		this.head11 = this.jawTop.getChild("head11");
		this.head12 = this.jawTop.getChild("head12");
		this.head13 = this.jawTop.getChild("head13");
		this.head14 = this.jawTop.getChild("head14");
		this.rightJaw = this.FrontJaw.getChild("rightJaw");
		this.head15 = this.rightJaw.getChild("head15");
		this.head17 = this.rightJaw.getChild("head17");
		this.head19 = this.rightJaw.getChild("head19");
		this.leftJaw = this.FrontJaw.getChild("leftJaw");
		this.head16 = this.leftJaw.getChild("head16");
		this.head18 = this.leftJaw.getChild("head18");
		this.head20 = this.leftJaw.getChild("head20");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Kraken = partdefinition.addOrReplaceChild("Kraken", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = Kraken.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 52).addBox(-15.0F, -13.0F, -14.0F, 30.0F, 13.0F, 38.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(-15.0F, 23.0F, -14.75F, 30.0F, 13.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -38.0F, -5.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-15.0F, 0.0F, -14.0F, 30.0F, 13.0F, 38.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.0F, 16.0F, 0.25F, 0.0F, 0.0F, 1.0472F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 52).addBox(-15.0F, 0.0F, -14.0F, 30.0F, 13.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 16.0F, 0.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 52).addBox(-15.0F, -13.0F, -14.0F, 30.0F, 13.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, 7.0F, 1.0F, 0.0F, 0.0F, 1.0472F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-15.0F, -13.0F, -14.0F, 30.0F, 13.0F, 38.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.0F, 7.0F, 1.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition Innerteeth = body.addOrReplaceChild("Innerteeth", CubeListBuilder.create().texOffs(-16, 2).addBox(-19.0F, -2.0F, -11.0F, 34.0F, 26.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(-16, 2).addBox(-19.0F, -2.0F, -2.0F, 34.0F, 26.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(-16, 2).addBox(-19.0F, -2.0F, 10.0F, 34.0F, 26.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(-16, 2).addBox(-19.0F, -2.0F, 19.0F, 34.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(-12, 40).addBox(-15.0F, -27.0F, 0.0F, 30.0F, 13.0F, 50.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(-15.0F, 9.0F, -0.75F, 30.0F, 13.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 18.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r5 = body2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(17, 69).mirror().addBox(-15.0F, 3.0F, 5.0F, 30.0F, 10.0F, 21.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.0F, 2.0F, 38.25F, -0.3524F, 0.1186F, 0.9916F));

		PartDefinition cube_r6 = body2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-2, 50).mirror().addBox(-15.0F, 0.0F, -14.0F, 30.0F, 13.0F, 40.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.0F, 2.0F, 14.25F, 0.0F, 0.0F, 1.0472F));

		PartDefinition cube_r7 = body2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(11, 63).addBox(-15.0F, 5.0F, 0.0F, 30.0F, 8.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 2.0F, 39.25F, -0.2618F, -0.2618F, -0.9599F));

		PartDefinition cube_r8 = body2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-3, 49).addBox(-15.0F, 0.0F, -14.0F, 30.0F, 13.0F, 41.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 2.0F, 14.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition cube_r9 = body2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(13, 65).addBox(-15.0F, 6.0F, 1.0F, 30.0F, 7.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 36.25F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r10 = body2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(17, 69).addBox(-15.0F, -13.0F, 11.0F, 30.0F, 13.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -4.75F, 36.0F, 0.3043F, 0.0262F, 0.964F));

		PartDefinition cube_r11 = body2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-8, 44).addBox(-15.0F, -13.0F, -14.0F, 30.0F, 13.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -7.0F, 15.0F, 0.0F, 0.0F, 1.0472F));

		PartDefinition cube_r12 = body2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(33, 67).mirror().addBox(3.0F, -15.0F, 6.0F, 16.0F, 11.0F, 23.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.0F, -7.0F, 40.0F, 0.2391F, -0.0745F, -0.7409F));

		PartDefinition cube_r13 = body2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(11, 65).mirror().addBox(-17.0F, -13.0F, 6.0F, 32.0F, 10.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.0F, -7.0F, 36.0F, 0.2502F, 0.0779F, -1.0374F));

		PartDefinition cube_r14 = body2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(-5, 47).mirror().addBox(-15.0F, -13.0F, -14.0F, 30.0F, 13.0F, 43.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.0F, -7.0F, 15.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition cube_r15 = body2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(17, 69).addBox(-15.0F, -13.0F, 6.0F, 30.0F, 13.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.75F, 42.25F, 0.3054F, 0.0F, 0.2182F));

		PartDefinition fin = body.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(-30, -31).addBox(0.0F, -26.0F, -14.0F, 0.0F, 26.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition cube_r16 = fin.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(-56, -57).addBox(-0.15F, -35.0F, -14.0F, 0.0F, 27.0F, 59.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 69.0F, 33.25F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r17 = fin.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(-44, -45).addBox(-0.15F, -26.0F, -14.0F, 0.0F, 26.0F, 47.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -2.0F, 33.25F, 0.1745F, 0.0F, 0.0F));

		PartDefinition LeftArmConnectPoint = Kraken.addOrReplaceChild("LeftArmConnectPoint", CubeListBuilder.create(), PartPose.offset(21.0F, -55.0F, 5.0F));

		PartDefinition Biomass_r1 = LeftArmConnectPoint.addOrReplaceChild("Biomass_r1", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.1007F, 9.144F, -0.3655F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r2 = LeftArmConnectPoint.addOrReplaceChild("Biomass_r2", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9835F, 0.4807F, 2.5226F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r3 = LeftArmConnectPoint.addOrReplaceChild("Biomass_r3", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5253F, 7.9019F, 5.4146F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r4 = LeftArmConnectPoint.addOrReplaceChild("Biomass_r4", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.609F, -3.5788F, -12.4651F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition RightArmConnectPoint = Kraken.addOrReplaceChild("RightArmConnectPoint", CubeListBuilder.create(), PartPose.offset(-17.0F, -55.0F, 5.0F));

		PartDefinition Biomass_r5 = RightArmConnectPoint.addOrReplaceChild("Biomass_r5", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8993F, 6.144F, -6.3655F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r6 = RightArmConnectPoint.addOrReplaceChild("Biomass_r6", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0165F, 10.4807F, -1.4774F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r7 = RightArmConnectPoint.addOrReplaceChild("Biomass_r7", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.4747F, 7.9019F, 5.4146F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r8 = RightArmConnectPoint.addOrReplaceChild("Biomass_r8", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.609F, -3.5788F, -12.4651F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition Tumors = Kraken.addOrReplaceChild("Tumors", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tumor = Tumors.addOrReplaceChild("Tumor", CubeListBuilder.create(), PartPose.offset(-20.0F, -49.0F, 36.0F));

		PartDefinition Biomass_r9 = Tumor.addOrReplaceChild("Biomass_r9", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8993F, 6.144F, -6.3655F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r10 = Tumor.addOrReplaceChild("Biomass_r10", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0165F, 10.4807F, -1.4774F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r11 = Tumor.addOrReplaceChild("Biomass_r11", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.4747F, 7.9019F, 5.4146F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r12 = Tumor.addOrReplaceChild("Biomass_r12", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.391F, -3.5788F, -12.4651F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition Tumor2 = Tumors.addOrReplaceChild("Tumor2", CubeListBuilder.create(), PartPose.offsetAndRotation(-16.0F, -24.0F, 33.0F, 0.0F, 0.8727F, -1.5272F));

		PartDefinition Biomass_r13 = Tumor2.addOrReplaceChild("Biomass_r13", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8993F, 6.144F, -6.3655F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r14 = Tumor2.addOrReplaceChild("Biomass_r14", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0165F, 10.4807F, -1.4774F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r15 = Tumor2.addOrReplaceChild("Biomass_r15", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.4747F, 7.9019F, 5.4146F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r16 = Tumor2.addOrReplaceChild("Biomass_r16", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.391F, -3.5788F, -12.4651F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition Tumor3 = Tumors.addOrReplaceChild("Tumor3", CubeListBuilder.create(), PartPose.offset(-16.0F, -17.0F, 15.0F));

		PartDefinition Biomass_r17 = Tumor3.addOrReplaceChild("Biomass_r17", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8993F, 6.144F, -6.3655F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r18 = Tumor3.addOrReplaceChild("Biomass_r18", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9835F, 10.4807F, -1.4774F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r19 = Tumor3.addOrReplaceChild("Biomass_r19", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.4747F, 5.9019F, 5.4146F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r20 = Tumor3.addOrReplaceChild("Biomass_r20", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.391F, -3.5788F, -12.4651F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition Tumor4 = Tumors.addOrReplaceChild("Tumor4", CubeListBuilder.create(), PartPose.offset(22.0F, -49.0F, 36.0F));

		PartDefinition Biomass_r21 = Tumor4.addOrReplaceChild("Biomass_r21", CubeListBuilder.create().texOffs(134, 251).mirror().addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.8993F, 6.144F, -3.3655F, 0.2392F, 0.0133F, 0.3783F));

		PartDefinition Biomass_r22 = Tumor4.addOrReplaceChild("Biomass_r22", CubeListBuilder.create().texOffs(136, 252).mirror().addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0165F, 10.4807F, -1.4774F, 1.361F, -0.0565F, 1.1111F));

		PartDefinition Biomass_r23 = Tumor4.addOrReplaceChild("Biomass_r23", CubeListBuilder.create().texOffs(137, 254).mirror().addBox(-11.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.4747F, 7.9019F, 5.4146F, 0.4094F, -0.4032F, 0.6728F));

		PartDefinition Biomass_r24 = Tumor4.addOrReplaceChild("Biomass_r24", CubeListBuilder.create().texOffs(137, 253).mirror().addBox(-1.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.391F, -3.5788F, -12.4651F, -0.5151F, -0.3039F, -0.5625F));

		PartDefinition Tumor5 = Tumors.addOrReplaceChild("Tumor5", CubeListBuilder.create(), PartPose.offsetAndRotation(16.0F, -27.0F, 33.0F, 0.0F, -0.8727F, 1.5272F));

		PartDefinition Biomass_r25 = Tumor5.addOrReplaceChild("Biomass_r25", CubeListBuilder.create().texOffs(134, 251).mirror().addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.8993F, 6.144F, -6.3655F, 0.2392F, 0.0133F, 0.3783F));

		PartDefinition Biomass_r26 = Tumor5.addOrReplaceChild("Biomass_r26", CubeListBuilder.create().texOffs(136, 252).mirror().addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0165F, 10.4807F, -1.4774F, 1.361F, -0.0565F, 1.1111F));

		PartDefinition Biomass_r27 = Tumor5.addOrReplaceChild("Biomass_r27", CubeListBuilder.create().texOffs(137, 254).mirror().addBox(-11.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.4747F, 7.9019F, 5.4146F, 0.4094F, -0.4032F, 0.6728F));

		PartDefinition Biomass_r28 = Tumor5.addOrReplaceChild("Biomass_r28", CubeListBuilder.create().texOffs(137, 253).mirror().addBox(-1.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.391F, -3.5788F, -12.4651F, -0.5151F, -0.3039F, -0.5625F));

		PartDefinition Tumor6 = Tumors.addOrReplaceChild("Tumor6", CubeListBuilder.create(), PartPose.offset(16.0F, -17.0F, 15.0F));

		PartDefinition Biomass_r29 = Tumor6.addOrReplaceChild("Biomass_r29", CubeListBuilder.create().texOffs(134, 251).mirror().addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.8993F, 6.144F, -6.3655F, 0.2392F, 0.0133F, 0.3783F));

		PartDefinition Biomass_r30 = Tumor6.addOrReplaceChild("Biomass_r30", CubeListBuilder.create().texOffs(136, 252).mirror().addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9835F, 10.4807F, -1.4774F, 1.361F, -0.0565F, 1.1111F));

		PartDefinition Biomass_r31 = Tumor6.addOrReplaceChild("Biomass_r31", CubeListBuilder.create().texOffs(137, 254).mirror().addBox(-11.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.4747F, 5.9019F, 5.4146F, 0.4094F, -0.4032F, 0.6728F));

		PartDefinition Biomass_r32 = Tumor6.addOrReplaceChild("Biomass_r32", CubeListBuilder.create().texOffs(137, 253).mirror().addBox(-1.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.391F, -3.5788F, -12.4651F, -0.5151F, -0.3039F, -0.5625F));

		PartDefinition Tumor7 = Tumors.addOrReplaceChild("Tumor7", CubeListBuilder.create(), PartPose.offsetAndRotation(-16.0F, -31.0F, 29.0F, -0.0859F, -0.0151F, -0.1739F));

		PartDefinition Biomass_r33 = Tumor7.addOrReplaceChild("Biomass_r33", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8993F, 6.144F, -6.3655F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r34 = Tumor7.addOrReplaceChild("Biomass_r34", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9835F, 10.4807F, -1.4774F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r35 = Tumor7.addOrReplaceChild("Biomass_r35", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.4747F, 5.9019F, 5.4146F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r36 = Tumor7.addOrReplaceChild("Biomass_r36", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.391F, -3.5788F, -12.4651F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition Tumor8 = Tumors.addOrReplaceChild("Tumor8", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, -17.0F, 15.0F, 2.7111F, 0.0735F, -2.0232F));

		PartDefinition Biomass_r37 = Tumor8.addOrReplaceChild("Biomass_r37", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8993F, 6.144F, -6.3655F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r38 = Tumor8.addOrReplaceChild("Biomass_r38", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9835F, 10.4807F, -1.4774F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r39 = Tumor8.addOrReplaceChild("Biomass_r39", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.4747F, 5.9019F, 5.4146F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r40 = Tumor8.addOrReplaceChild("Biomass_r40", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.391F, -3.5788F, -12.4651F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition Tumor9 = Tumors.addOrReplaceChild("Tumor9", CubeListBuilder.create(), PartPose.offset(16.0F, -29.0F, 27.0F));

		PartDefinition Biomass_r41 = Tumor9.addOrReplaceChild("Biomass_r41", CubeListBuilder.create().texOffs(134, 251).mirror().addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.8993F, 6.144F, -6.3655F, 0.2392F, 0.0133F, 0.3783F));

		PartDefinition Biomass_r42 = Tumor9.addOrReplaceChild("Biomass_r42", CubeListBuilder.create().texOffs(136, 252).mirror().addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9835F, 10.4807F, -1.4774F, 1.361F, -0.0565F, 1.1111F));

		PartDefinition Biomass_r43 = Tumor9.addOrReplaceChild("Biomass_r43", CubeListBuilder.create().texOffs(137, 254).mirror().addBox(-11.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.4747F, 5.9019F, 5.4146F, 0.4094F, -0.4032F, 0.6728F));

		PartDefinition Biomass_r44 = Tumor9.addOrReplaceChild("Biomass_r44", CubeListBuilder.create().texOffs(137, 253).mirror().addBox(-1.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.391F, -3.5788F, -12.4651F, -0.5151F, -0.3039F, -0.5625F));

		PartDefinition Tumor10 = Tumors.addOrReplaceChild("Tumor10", CubeListBuilder.create(), PartPose.offset(13.0F, -37.0F, 44.0F));

		PartDefinition Biomass_r45 = Tumor10.addOrReplaceChild("Biomass_r45", CubeListBuilder.create().texOffs(134, 251).mirror().addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.8993F, 6.144F, -6.3655F, 0.2392F, 0.0133F, 0.3783F));

		PartDefinition Biomass_r46 = Tumor10.addOrReplaceChild("Biomass_r46", CubeListBuilder.create().texOffs(136, 252).mirror().addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9835F, 10.4807F, -1.4774F, 1.361F, -0.0565F, 1.1111F));

		PartDefinition Biomass_r47 = Tumor10.addOrReplaceChild("Biomass_r47", CubeListBuilder.create().texOffs(137, 254).mirror().addBox(-11.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.4747F, 5.9019F, 5.4146F, 0.4094F, -0.4032F, 0.6728F));

		PartDefinition Biomass_r48 = Tumor10.addOrReplaceChild("Biomass_r48", CubeListBuilder.create().texOffs(137, 253).mirror().addBox(-1.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.391F, -3.5788F, -12.4651F, -0.5151F, -0.3039F, -0.5625F));

		PartDefinition Tumor11 = Tumors.addOrReplaceChild("Tumor11", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.0F, -35.0F, 37.0F, 2.0084F, -0.0151F, -0.1739F));

		PartDefinition Biomass_r49 = Tumor11.addOrReplaceChild("Biomass_r49", CubeListBuilder.create().texOffs(134, 251).addBox(-5.5F, -5.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8993F, 6.144F, -6.3655F, 0.2392F, -0.0133F, -0.3783F));

		PartDefinition Biomass_r50 = Tumor11.addOrReplaceChild("Biomass_r50", CubeListBuilder.create().texOffs(136, 252).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9835F, 10.4807F, -1.4774F, 1.361F, 0.0565F, -1.1111F));

		PartDefinition Biomass_r51 = Tumor11.addOrReplaceChild("Biomass_r51", CubeListBuilder.create().texOffs(137, 254).addBox(3.0F, -3.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.4747F, 5.9019F, 5.4146F, 0.4094F, 0.4032F, -0.6728F));

		PartDefinition Biomass_r52 = Tumor11.addOrReplaceChild("Biomass_r52", CubeListBuilder.create().texOffs(137, 253).addBox(-8.0F, -5.0F, 7.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.391F, -3.5788F, -12.4651F, -0.5151F, 0.3039F, 0.5625F));

		PartDefinition BackJaw = Kraken.addOrReplaceChild("BackJaw", CubeListBuilder.create().texOffs(-15, 2).addBox(-23.0F, -24.25F, 20.0F, 43.0F, 41.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -84.0F, 60.0F, -2.5037F, -0.3159F, 0.2742F));

		PartDefinition cube_r18 = BackJaw.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(-23, 2).addBox(-26.0F, -25.0F, 1.0F, 46.0F, 44.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -9.0F, 10.0F, 0.0F, 0.0F, -1.7453F));

		PartDefinition Outer = BackJaw.addOrReplaceChild("Outer", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition OuterRingBase = Outer.addOrReplaceChild("OuterRingBase", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition Internal = OuterRingBase.addOrReplaceChild("Internal", CubeListBuilder.create(), PartPose.offset(-17.3889F, -10.5693F, 0.2138F));

		PartDefinition In_r1 = Internal.addOrReplaceChild("In_r1", CubeListBuilder.create().texOffs(364, 272).addBox(-8.5F, -1.0F, 0.0F, 11.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1278F, -13.2359F, 2.8005F, -1.2813F, -0.044F, -1.2561F));

		PartDefinition In_r2 = Internal.addOrReplaceChild("In_r2", CubeListBuilder.create().texOffs(312, 165).addBox(-9.5F, -5.0F, -2.5F, 21.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9759F, 2.5706F, -0.4779F, -1.0525F, -0.0008F, -1.0912F));

		PartDefinition In_r3 = Internal.addOrReplaceChild("In_r3", CubeListBuilder.create().texOffs(164, 398).addBox(-3.0F, -11.0F, 0.0F, 3.0F, 22.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.8206F, 18.9982F, -1.0294F, -0.0068F, 0.8756F, -0.3546F));

		PartDefinition In_r4 = Internal.addOrReplaceChild("In_r4", CubeListBuilder.create().texOffs(370, 153).addBox(-15.0F, -1.0F, -0.75F, 17.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0235F, 32.4363F, -0.1189F, 0.7549F, -0.1543F, 0.2614F));

		PartDefinition In_r5 = Internal.addOrReplaceChild("In_r5", CubeListBuilder.create().texOffs(350, 0).addBox(-12.0F, 0.0F, 0.0F, 22.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.8346F, 32.2659F, -1.1743F, 0.6653F, 0.2264F, 0.1636F));

		PartDefinition In_r6 = Internal.addOrReplaceChild("In_r6", CubeListBuilder.create().texOffs(202, 362).addBox(0.0F, -11.25F, 0.0F, 4.0F, 24.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(35.8053F, 24.2715F, -1.2138F, 0.0F, -1.0472F, 0.6981F));

		PartDefinition In_r7 = Internal.addOrReplaceChild("In_r7", CubeListBuilder.create().texOffs(74, 396).addBox(0.0F, -8.0F, -1.0F, 3.0F, 27.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.3632F, -2.8223F, -0.2138F, 0.0F, -0.5672F, -0.1309F));

		PartDefinition In_r8 = Internal.addOrReplaceChild("In_r8", CubeListBuilder.create().texOffs(326, 330).addBox(-1.0F, -3.0F, -1.0F, 24.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.3889F, -18.4307F, -0.2138F, -0.7854F, 0.0F, 0.3491F));

		PartDefinition In_r9 = Internal.addOrReplaceChild("In_r9", CubeListBuilder.create().texOffs(353, 88).addBox(-4.0F, -2.0F, -2.0F, 21.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.521F, -9.9773F, 0.0163F, -0.6475F, -0.1293F, -0.392F));

		PartDefinition OuterRingDetails = Outer.addOrReplaceChild("OuterRingDetails", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Teeth = OuterRingDetails.addOrReplaceChild("Teeth", CubeListBuilder.create(), PartPose.offset(-17.0523F, -6.3894F, 2.3538F));

		PartDefinition t_r1 = Teeth.addOrReplaceChild("t_r1", CubeListBuilder.create().texOffs(0, -4).addBox(2.0F, -5.0F, -2.0F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0941F, -18.3761F, -1.664F, 0.7264F, -1.2636F, 0.4739F));

		PartDefinition t_r2 = Teeth.addOrReplaceChild("t_r2", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -6.0F, -2.0F, 0.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.312F, -13.9163F, 0.78F, 0.5253F, 0.5993F, -1.5362F));

		PartDefinition t_r3 = Teeth.addOrReplaceChild("t_r3", CubeListBuilder.create().texOffs(0, -2).addBox(1.0F, -6.0F, -2.0F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1966F, -9.8531F, 1.7807F, 0.3416F, 0.7103F, -1.836F));

		PartDefinition t_r4 = Teeth.addOrReplaceChild("t_r4", CubeListBuilder.create().texOffs(23, -5).addBox(0.0F, -7.5F, -2.5F, 0.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.1734F, -9.8606F, 3.8521F, -0.0483F, 0.1629F, -2.6431F));

		PartDefinition t_r5 = Teeth.addOrReplaceChild("t_r5", CubeListBuilder.create().texOffs(0, -4).addBox(3.0153F, -0.994F, -3.5092F, 0.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.2843F, 4.4467F, -3.2326F, 0.0282F, -1.391F, 3.084F));

		PartDefinition t_r6 = Teeth.addOrReplaceChild("t_r6", CubeListBuilder.create().texOffs(23, -5).addBox(0.5047F, -6.2786F, -3.0491F, 0.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(29.4971F, -15.0265F, 0.3015F, -0.2253F, -0.5651F, 2.1665F));

		PartDefinition t_r7 = Teeth.addOrReplaceChild("t_r7", CubeListBuilder.create().texOffs(0, -4).addBox(0.0F, -9.5F, -2.0F, 0.0F, 19.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(24.3905F, -15.0528F, 1.5427F, -0.0125F, -1.1606F, 1.7558F));

		PartDefinition t_r8 = Teeth.addOrReplaceChild("t_r8", CubeListBuilder.create().texOffs(0, -4).addBox(0.0F, -9.0F, -4.0F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(37.0178F, 12.6029F, 0.3954F, -0.2979F, -0.9583F, -2.2563F));

		PartDefinition t_r9 = Teeth.addOrReplaceChild("t_r9", CubeListBuilder.create().texOffs(0, -4).addBox(2.585F, -8.0883F, -3.9685F, 0.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(35.3906F, 12.8665F, 0.1298F, 0.4809F, -0.9945F, -3.0232F));

		PartDefinition t_r10 = Teeth.addOrReplaceChild("t_r10", CubeListBuilder.create().texOffs(23, -5).addBox(1.0F, -12.5F, -3.5F, 0.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(37.9907F, 14.6601F, -1.1661F, 0.6668F, -1.1623F, 3.1378F));

		PartDefinition t_r11 = Teeth.addOrReplaceChild("t_r11", CubeListBuilder.create().texOffs(0, -4).addBox(-1.2832F, 1.6868F, 0.3516F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0928F, 18.5957F, -0.9501F, 0.5084F, -1.2142F, -1.9009F));

		PartDefinition t_r12 = Teeth.addOrReplaceChild("t_r12", CubeListBuilder.create().texOffs(23, -5).addBox(-1.4437F, -2.127F, 0.9602F, 0.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0928F, 20.5957F, -0.9501F, -0.2115F, -1.4282F, -1.1357F));

		PartDefinition t_r13 = Teeth.addOrReplaceChild("t_r13", CubeListBuilder.create().texOffs(0, -2).addBox(3.3546F, -2.5132F, -1.3209F, 0.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0928F, 19.5957F, -0.9501F, 0.0282F, -1.391F, -1.4539F));

		PartDefinition t_r14 = Teeth.addOrReplaceChild("t_r14", CubeListBuilder.create().texOffs(0, -2).addBox(0.1506F, -6.8775F, -1.7368F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0037F, 21.1406F, -0.0792F, 1.0115F, -0.9532F, -2.5209F));

		PartDefinition t_r15 = Teeth.addOrReplaceChild("t_r15", CubeListBuilder.create().texOffs(23, -5).addBox(-3.0F, -5.5F, -1.5F, 0.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1931F, 20.7673F, -2.0586F, 0.5422F, -0.5946F, -1.8354F));

		PartDefinition t_r16 = Teeth.addOrReplaceChild("t_r16", CubeListBuilder.create().texOffs(0, -4).addBox(0.0F, -5.0F, -2.75F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1139F, 12.5348F, 2.0547F, -2.3755F, -1.4575F, 2.0867F));

		PartDefinition t_r17 = Teeth.addOrReplaceChild("t_r17", CubeListBuilder.create().texOffs(0, -1).addBox(-0.8494F, -3.8775F, -1.7368F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9963F, 12.1406F, -0.0792F, 0.5084F, -1.2142F, -0.8537F));

		PartDefinition t_r18 = Teeth.addOrReplaceChild("t_r18", CubeListBuilder.create().texOffs(0, 0).addBox(1.25F, -7.0F, -3.0F, 0.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9446F, -1.3693F, -0.7078F, -0.1205F, -1.2586F, 0.5948F));

		PartDefinition t_r19 = Teeth.addOrReplaceChild("t_r19", CubeListBuilder.create().texOffs(23, -5).addBox(0.0F, -6.5F, -4.5F, 0.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8788F, -2.8637F, -1.1866F, 0.3296F, -0.9814F, -0.0968F));

		PartDefinition t_r20 = Teeth.addOrReplaceChild("t_r20", CubeListBuilder.create().texOffs(0, -4).addBox(0.0F, -10.25F, -3.0F, 0.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0593F, 3.2013F, 2.1114F, -0.1942F, -0.9634F, 0.7791F));

		PartDefinition BackBodyMouthProtection = Kraken.addOrReplaceChild("BackBodyMouthProtection", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HindeRotation = BackBodyMouthProtection.addOrReplaceChild("HindeRotation", CubeListBuilder.create(), PartPose.offsetAndRotation(29.0F, -91.0F, 48.75F, -0.7813F, 0.286F, -0.2729F));

		PartDefinition Hinge1 = HindeRotation.addOrReplaceChild("Hinge1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftHinge = Hinge1.addOrReplaceChild("LeftHinge", CubeListBuilder.create().texOffs(44, 235).addBox(-1.317F, -19.5391F, -2.0F, 3.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition LowerRidgeRib3Tip_r1 = LeftHinge.addOrReplaceChild("LowerRidgeRib3Tip_r1", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 7.99F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -17.5391F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeRib3_r1 = LeftHinge.addOrReplaceChild("LowerRidgeRib3_r1", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 8.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -17.5391F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeRib2Tip_r1 = LeftHinge.addOrReplaceChild("LowerRidgeRib2Tip_r1", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -10.5391F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeRib2_r1 = LeftHinge.addOrReplaceChild("LowerRidgeRib2_r1", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -10.5391F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeLowerMemebrane = LeftHinge.addOrReplaceChild("HingeLowerMemebrane", CubeListBuilder.create(), PartPose.offset(-4.9227F, -7.5291F, 15.5254F));

		PartDefinition LowerRidgeFrontMembraneTip_r1 = HingeLowerMemebrane.addOrReplaceChild("LowerRidgeFrontMembraneTip_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -11.01F, -2.75F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r1 = HingeLowerMemebrane.addOrReplaceChild("LowerRidgeFrontMembrane_r1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -11.0F, 0.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -14.0254F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeFrontMembraneTip_r2 = HingeLowerMemebrane.addOrReplaceChild("LowerRidgeFrontMembraneTip_r2", CubeListBuilder.create().texOffs(170, 215).addBox(-1.0F, -11.01F, -5.25F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -31.0508F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r2 = HingeLowerMemebrane.addOrReplaceChild("LowerRidgeFrontMembrane_r2", CubeListBuilder.create().texOffs(28, 94).addBox(0.0F, -11.0F, -12.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -17.0254F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleHinge = LeftHinge.addOrReplaceChild("HingeMiddleHinge", CubeListBuilder.create().texOffs(268, 309).addBox(-1.5905F, -15.487F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -19.5391F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition MiddleRidgeRib2Tip_r1 = HingeMiddleHinge.addOrReplaceChild("MiddleRidgeRib2Tip_r1", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 5.99F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8454F, -13.0F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeRib2_r1 = HingeMiddleHinge.addOrReplaceChild("MiddleRidgeRib2_r1", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 6.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleMemebrane = HingeMiddleHinge.addOrReplaceChild("HingeMiddleMemebrane", CubeListBuilder.create(), PartPose.offset(-4.1925F, -6.99F, -15.6758F));

		PartDefinition MiddleRidgeFrontMembraneTip_r1 = HingeMiddleMemebrane.addOrReplaceChild("MiddleRidgeFrontMembraneTip_r1", CubeListBuilder.create().texOffs(110, 20).addBox(-1.5F, -8.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeFrontMembrane_r1 = HingeMiddleMemebrane.addOrReplaceChild("MiddleRidgeFrontMembrane_r1", CubeListBuilder.create().texOffs(144, 171).addBox(-0.5F, -8.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembrane_r1 = HingeMiddleMemebrane.addOrReplaceChild("MiddleRidgeBackMembrane_r1", CubeListBuilder.create().texOffs(0, 176).addBox(-0.5F, -8.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembraneTip_r1 = HingeMiddleMemebrane.addOrReplaceChild("MiddleRidgeBackMembraneTip_r1", CubeListBuilder.create().texOffs(0, 130).addBox(-1.5F, -8.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition HingeTopHinge = HingeMiddleHinge.addOrReplaceChild("HingeTopHinge", CubeListBuilder.create().texOffs(254, 309).addBox(-1.5F, -16.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(254, 309).addBox(-1.5F, -31.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 226).addBox(-1.9095F, -23.513F, -10.5F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0905F, -15.487F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition TopRidgeRib3Tip_r1 = HingeTopHinge.addOrReplaceChild("TopRidgeRib3Tip_r1", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, -23.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 17.487F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition TopRidgeRib3_r1 = HingeTopHinge.addOrReplaceChild("TopRidgeRib3_r1", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, -23.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 17.487F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition TopRidgeRib2Tip_r1 = HingeTopHinge.addOrReplaceChild("TopRidgeRib2Tip_r1", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 24.487F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition TopRidgeRib2_r1 = HingeTopHinge.addOrReplaceChild("TopRidgeRib2_r1", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 24.487F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftTopMemebrane = HingeTopHinge.addOrReplaceChild("LeftTopMemebrane", CubeListBuilder.create(), PartPose.offset(-4.1021F, 27.497F, -15.6758F));

		PartDefinition LeftRidgeTopMemebraneTip_r1 = LeftTopMemebrane.addOrReplaceChild("LeftRidgeTopMemebraneTip_r1", CubeListBuilder.create().texOffs(67, 43).addBox(-1.5F, -36.01F, -5.25F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(67, 43).addBox(-1.5F, -28.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r1 = LeftTopMemebrane.addOrReplaceChild("LeftRidgeTopMemebrane_r1", CubeListBuilder.create().texOffs(96, 168).addBox(-0.5F, -42.0F, -12.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(96, 168).addBox(-0.5F, -27.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftRidgeTopMemebraneTip_r2 = LeftTopMemebrane.addOrReplaceChild("LeftRidgeTopMemebraneTip_r2", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -36.01F, -2.75F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-1.5F, -28.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r2 = LeftTopMemebrane.addOrReplaceChild("LeftRidgeTopMemebrane_r2", CubeListBuilder.create().texOffs(120, 170).addBox(-0.5F, -42.0F, 0.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(120, 170).addBox(-0.5F, -27.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition HindeRotation2 = BackBodyMouthProtection.addOrReplaceChild("HindeRotation2", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -104.0F, 36.0F, -1.6656F, 0.8249F, -1.6996F));

		PartDefinition Hinge2 = HindeRotation2.addOrReplaceChild("Hinge2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftHinge2 = Hinge2.addOrReplaceChild("LeftHinge2", CubeListBuilder.create().texOffs(44, 235).addBox(-1.317F, -19.5391F, -2.0F, 3.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition LowerRidgeRib3Tip_r2 = LeftHinge2.addOrReplaceChild("LowerRidgeRib3Tip_r2", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 7.99F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -17.5391F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeRib3_r2 = LeftHinge2.addOrReplaceChild("LowerRidgeRib3_r2", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 8.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -17.5391F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeRib2Tip_r2 = LeftHinge2.addOrReplaceChild("LowerRidgeRib2Tip_r2", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -10.5391F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeRib2_r2 = LeftHinge2.addOrReplaceChild("LowerRidgeRib2_r2", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -10.5391F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeLowerMemebrane2 = LeftHinge2.addOrReplaceChild("HingeLowerMemebrane2", CubeListBuilder.create(), PartPose.offset(-4.9227F, -7.5291F, 15.5254F));

		PartDefinition LowerRidgeFrontMembraneTip_r3 = HingeLowerMemebrane2.addOrReplaceChild("LowerRidgeFrontMembraneTip_r3", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -11.01F, -2.75F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r3 = HingeLowerMemebrane2.addOrReplaceChild("LowerRidgeFrontMembrane_r3", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -11.0F, 0.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -14.0254F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeFrontMembraneTip_r4 = HingeLowerMemebrane2.addOrReplaceChild("LowerRidgeFrontMembraneTip_r4", CubeListBuilder.create().texOffs(170, 215).addBox(-1.0F, -11.01F, -5.25F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -31.0508F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r4 = HingeLowerMemebrane2.addOrReplaceChild("LowerRidgeFrontMembrane_r4", CubeListBuilder.create().texOffs(28, 94).addBox(0.0F, -11.0F, -12.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -17.0254F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleHinge2 = LeftHinge2.addOrReplaceChild("HingeMiddleHinge2", CubeListBuilder.create().texOffs(268, 309).addBox(-1.5905F, -15.487F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -19.5391F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition MiddleRidgeRib2Tip_r2 = HingeMiddleHinge2.addOrReplaceChild("MiddleRidgeRib2Tip_r2", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 5.99F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8454F, -13.0F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeRib2_r2 = HingeMiddleHinge2.addOrReplaceChild("MiddleRidgeRib2_r2", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 6.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleMemebrane2 = HingeMiddleHinge2.addOrReplaceChild("HingeMiddleMemebrane2", CubeListBuilder.create(), PartPose.offset(-4.1925F, -6.99F, -15.6758F));

		PartDefinition MiddleRidgeFrontMembraneTip_r2 = HingeMiddleMemebrane2.addOrReplaceChild("MiddleRidgeFrontMembraneTip_r2", CubeListBuilder.create().texOffs(110, 20).addBox(-1.5F, -8.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeFrontMembrane_r2 = HingeMiddleMemebrane2.addOrReplaceChild("MiddleRidgeFrontMembrane_r2", CubeListBuilder.create().texOffs(144, 171).addBox(-0.5F, -8.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembrane_r2 = HingeMiddleMemebrane2.addOrReplaceChild("MiddleRidgeBackMembrane_r2", CubeListBuilder.create().texOffs(0, 176).addBox(-0.5F, -8.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembraneTip_r2 = HingeMiddleMemebrane2.addOrReplaceChild("MiddleRidgeBackMembraneTip_r2", CubeListBuilder.create().texOffs(0, 130).addBox(-1.5F, -8.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition HingeTopHinge2 = HingeMiddleHinge2.addOrReplaceChild("HingeTopHinge2", CubeListBuilder.create().texOffs(254, 309).addBox(-1.5F, -16.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(254, 309).addBox(-1.5F, -31.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 226).addBox(-1.9095F, -23.513F, -10.5F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0905F, -15.487F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition TopRidgeRib3Tip_r2 = HingeTopHinge2.addOrReplaceChild("TopRidgeRib3Tip_r2", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, -23.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 17.487F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition TopRidgeRib3_r2 = HingeTopHinge2.addOrReplaceChild("TopRidgeRib3_r2", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, -23.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 17.487F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition TopRidgeRib2Tip_r2 = HingeTopHinge2.addOrReplaceChild("TopRidgeRib2Tip_r2", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 24.487F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition TopRidgeRib2_r2 = HingeTopHinge2.addOrReplaceChild("TopRidgeRib2_r2", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 24.487F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftTopMemebrane2 = HingeTopHinge2.addOrReplaceChild("LeftTopMemebrane2", CubeListBuilder.create(), PartPose.offset(-4.102F, 27.497F, -15.6758F));

		PartDefinition LeftRidgeTopMemebraneTip_r3 = LeftTopMemebrane2.addOrReplaceChild("LeftRidgeTopMemebraneTip_r3", CubeListBuilder.create().texOffs(67, 43).addBox(-1.5F, -36.01F, -5.25F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(67, 43).addBox(-1.5F, -28.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r3 = LeftTopMemebrane2.addOrReplaceChild("LeftRidgeTopMemebrane_r3", CubeListBuilder.create().texOffs(96, 168).addBox(-0.5F, -42.0F, -12.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(96, 168).addBox(-0.5F, -27.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftRidgeTopMemebraneTip_r4 = LeftTopMemebrane2.addOrReplaceChild("LeftRidgeTopMemebraneTip_r4", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -36.01F, -2.75F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-1.5F, -28.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r4 = LeftTopMemebrane2.addOrReplaceChild("LeftRidgeTopMemebrane_r4", CubeListBuilder.create().texOffs(120, 170).addBox(-0.5F, -42.0F, 0.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(120, 170).addBox(-0.5F, -27.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition HindeRotation3 = BackBodyMouthProtection.addOrReplaceChild("HindeRotation3", CubeListBuilder.create(), PartPose.offsetAndRotation(-34.0F, -81.0F, 57.0F, -2.3546F, -0.0562F, 3.0745F));

		PartDefinition Hinge3 = HindeRotation3.addOrReplaceChild("Hinge3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftHinge3 = Hinge3.addOrReplaceChild("LeftHinge3", CubeListBuilder.create().texOffs(44, 235).addBox(-1.317F, -19.5391F, -2.0F, 3.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition LowerRidgeRib3Tip_r3 = LeftHinge3.addOrReplaceChild("LowerRidgeRib3Tip_r3", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 7.99F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -17.5391F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeRib3_r3 = LeftHinge3.addOrReplaceChild("LowerRidgeRib3_r3", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 8.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -17.5391F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeRib2Tip_r3 = LeftHinge3.addOrReplaceChild("LowerRidgeRib2Tip_r3", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -10.5391F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeRib2_r3 = LeftHinge3.addOrReplaceChild("LowerRidgeRib2_r3", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -10.5391F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeLowerMemebrane3 = LeftHinge3.addOrReplaceChild("HingeLowerMemebrane3", CubeListBuilder.create(), PartPose.offset(-4.9227F, -7.5291F, 15.5254F));

		PartDefinition LowerRidgeFrontMembraneTip_r5 = HingeLowerMemebrane3.addOrReplaceChild("LowerRidgeFrontMembraneTip_r5", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -11.01F, -2.75F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r5 = HingeLowerMemebrane3.addOrReplaceChild("LowerRidgeFrontMembrane_r5", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -11.0F, 0.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -14.0254F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeFrontMembraneTip_r6 = HingeLowerMemebrane3.addOrReplaceChild("LowerRidgeFrontMembraneTip_r6", CubeListBuilder.create().texOffs(170, 215).addBox(-1.0F, -11.01F, -5.25F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -31.0508F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r6 = HingeLowerMemebrane3.addOrReplaceChild("LowerRidgeFrontMembrane_r6", CubeListBuilder.create().texOffs(28, 94).addBox(0.0F, -11.0F, -12.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -17.0254F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleHinge3 = LeftHinge3.addOrReplaceChild("HingeMiddleHinge3", CubeListBuilder.create().texOffs(268, 309).addBox(-1.5905F, -15.487F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -19.5391F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition MiddleRidgeRib2Tip_r3 = HingeMiddleHinge3.addOrReplaceChild("MiddleRidgeRib2Tip_r3", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 5.99F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8454F, -13.0F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeRib2_r3 = HingeMiddleHinge3.addOrReplaceChild("MiddleRidgeRib2_r3", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 6.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleMemebrane3 = HingeMiddleHinge3.addOrReplaceChild("HingeMiddleMemebrane3", CubeListBuilder.create(), PartPose.offset(-4.1925F, -6.99F, -15.6758F));

		PartDefinition MiddleRidgeFrontMembraneTip_r3 = HingeMiddleMemebrane3.addOrReplaceChild("MiddleRidgeFrontMembraneTip_r3", CubeListBuilder.create().texOffs(110, 20).addBox(-1.5F, -8.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeFrontMembrane_r3 = HingeMiddleMemebrane3.addOrReplaceChild("MiddleRidgeFrontMembrane_r3", CubeListBuilder.create().texOffs(144, 171).addBox(-0.5F, -8.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembrane_r3 = HingeMiddleMemebrane3.addOrReplaceChild("MiddleRidgeBackMembrane_r3", CubeListBuilder.create().texOffs(0, 176).addBox(-0.5F, -8.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembraneTip_r3 = HingeMiddleMemebrane3.addOrReplaceChild("MiddleRidgeBackMembraneTip_r3", CubeListBuilder.create().texOffs(0, 130).addBox(-1.5F, -8.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition HingeTopHinge3 = HingeMiddleHinge3.addOrReplaceChild("HingeTopHinge3", CubeListBuilder.create().texOffs(254, 309).addBox(-1.5F, -16.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(254, 309).addBox(-1.5F, -31.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 226).addBox(-1.9095F, -23.513F, -10.5F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0905F, -15.487F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition TopRidgeRib3Tip_r3 = HingeTopHinge3.addOrReplaceChild("TopRidgeRib3Tip_r3", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, -23.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 17.487F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition TopRidgeRib3_r3 = HingeTopHinge3.addOrReplaceChild("TopRidgeRib3_r3", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, -23.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 17.487F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition TopRidgeRib2Tip_r3 = HingeTopHinge3.addOrReplaceChild("TopRidgeRib2Tip_r3", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 24.487F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition TopRidgeRib2_r3 = HingeTopHinge3.addOrReplaceChild("TopRidgeRib2_r3", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 24.487F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftTopMemebrane3 = HingeTopHinge3.addOrReplaceChild("LeftTopMemebrane3", CubeListBuilder.create(), PartPose.offset(-4.102F, 27.497F, -15.6758F));

		PartDefinition LeftRidgeTopMemebraneTip_r5 = LeftTopMemebrane3.addOrReplaceChild("LeftRidgeTopMemebraneTip_r5", CubeListBuilder.create().texOffs(67, 43).addBox(-1.5F, -36.01F, -5.25F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(67, 43).addBox(-1.5F, -28.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r5 = LeftTopMemebrane3.addOrReplaceChild("LeftRidgeTopMemebrane_r5", CubeListBuilder.create().texOffs(96, 168).addBox(-0.5F, -42.0F, -12.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(96, 168).addBox(-0.5F, -27.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftRidgeTopMemebraneTip_r6 = LeftTopMemebrane3.addOrReplaceChild("LeftRidgeTopMemebraneTip_r6", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -36.01F, -2.75F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-1.5F, -28.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r6 = LeftTopMemebrane3.addOrReplaceChild("LeftRidgeTopMemebrane_r6", CubeListBuilder.create().texOffs(120, 170).addBox(-0.5F, -42.0F, 0.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(120, 170).addBox(-0.5F, -27.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition HindeRotation4 = BackBodyMouthProtection.addOrReplaceChild("HindeRotation4", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.0F, -58.0F, 76.0F, -1.9432F, -0.6576F, 2.1339F));

		PartDefinition Hinge4 = HindeRotation4.addOrReplaceChild("Hinge4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftHinge4 = Hinge4.addOrReplaceChild("LeftHinge4", CubeListBuilder.create().texOffs(44, 235).addBox(-1.317F, -19.5391F, -2.0F, 3.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition LowerRidgeRib3Tip_r4 = LeftHinge4.addOrReplaceChild("LowerRidgeRib3Tip_r4", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 7.99F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -17.5391F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeRib3_r4 = LeftHinge4.addOrReplaceChild("LowerRidgeRib3_r4", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 8.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -17.5391F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeRib2Tip_r4 = LeftHinge4.addOrReplaceChild("LowerRidgeRib2Tip_r4", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -10.5391F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeRib2_r4 = LeftHinge4.addOrReplaceChild("LowerRidgeRib2_r4", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -10.5391F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeLowerMemebrane4 = LeftHinge4.addOrReplaceChild("HingeLowerMemebrane4", CubeListBuilder.create(), PartPose.offset(-4.9227F, -7.5291F, 15.5254F));

		PartDefinition LowerRidgeFrontMembraneTip_r7 = HingeLowerMemebrane4.addOrReplaceChild("LowerRidgeFrontMembraneTip_r7", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -11.01F, -2.75F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r7 = HingeLowerMemebrane4.addOrReplaceChild("LowerRidgeFrontMembrane_r7", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -11.0F, 0.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -14.0254F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeFrontMembraneTip_r8 = HingeLowerMemebrane4.addOrReplaceChild("LowerRidgeFrontMembraneTip_r8", CubeListBuilder.create().texOffs(170, 215).addBox(-1.0F, -11.01F, -5.25F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -31.0508F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r8 = HingeLowerMemebrane4.addOrReplaceChild("LowerRidgeFrontMembrane_r8", CubeListBuilder.create().texOffs(28, 94).addBox(0.0F, -11.0F, -12.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -17.0254F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleHinge4 = LeftHinge4.addOrReplaceChild("HingeMiddleHinge4", CubeListBuilder.create().texOffs(268, 309).addBox(-1.5905F, -15.487F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -19.5391F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition MiddleRidgeRib2Tip_r4 = HingeMiddleHinge4.addOrReplaceChild("MiddleRidgeRib2Tip_r4", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 5.99F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8454F, -13.0F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeRib2_r4 = HingeMiddleHinge4.addOrReplaceChild("MiddleRidgeRib2_r4", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 6.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleMemebrane4 = HingeMiddleHinge4.addOrReplaceChild("HingeMiddleMemebrane4", CubeListBuilder.create(), PartPose.offset(-4.1925F, -6.99F, -15.6758F));

		PartDefinition MiddleRidgeFrontMembraneTip_r4 = HingeMiddleMemebrane4.addOrReplaceChild("MiddleRidgeFrontMembraneTip_r4", CubeListBuilder.create().texOffs(110, 20).addBox(-1.5F, -8.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeFrontMembrane_r4 = HingeMiddleMemebrane4.addOrReplaceChild("MiddleRidgeFrontMembrane_r4", CubeListBuilder.create().texOffs(144, 171).addBox(-0.5F, -8.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembrane_r4 = HingeMiddleMemebrane4.addOrReplaceChild("MiddleRidgeBackMembrane_r4", CubeListBuilder.create().texOffs(0, 176).addBox(-0.5F, -8.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembraneTip_r4 = HingeMiddleMemebrane4.addOrReplaceChild("MiddleRidgeBackMembraneTip_r4", CubeListBuilder.create().texOffs(0, 130).addBox(-1.5F, -8.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition HingeTopHinge4 = HingeMiddleHinge4.addOrReplaceChild("HingeTopHinge4", CubeListBuilder.create().texOffs(254, 309).addBox(-1.5F, -16.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(254, 309).addBox(-1.5F, -31.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 226).addBox(-1.9095F, -23.513F, -10.5F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0905F, -15.487F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition TopRidgeRib3Tip_r4 = HingeTopHinge4.addOrReplaceChild("TopRidgeRib3Tip_r4", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, -23.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 17.487F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition TopRidgeRib3_r4 = HingeTopHinge4.addOrReplaceChild("TopRidgeRib3_r4", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, -23.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 17.487F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition TopRidgeRib2Tip_r4 = HingeTopHinge4.addOrReplaceChild("TopRidgeRib2Tip_r4", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 24.487F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition TopRidgeRib2_r4 = HingeTopHinge4.addOrReplaceChild("TopRidgeRib2_r4", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 24.487F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftTopMemebrane4 = HingeTopHinge4.addOrReplaceChild("LeftTopMemebrane4", CubeListBuilder.create(), PartPose.offset(-4.1021F, 27.497F, -15.6758F));

		PartDefinition LeftRidgeTopMemebraneTip_r7 = LeftTopMemebrane4.addOrReplaceChild("LeftRidgeTopMemebraneTip_r7", CubeListBuilder.create().texOffs(67, 43).addBox(-1.5F, -36.01F, -5.25F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(67, 43).addBox(-1.5F, -28.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r7 = LeftTopMemebrane4.addOrReplaceChild("LeftRidgeTopMemebrane_r7", CubeListBuilder.create().texOffs(96, 168).addBox(-0.5F, -42.0F, -12.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(96, 168).addBox(-0.5F, -27.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftRidgeTopMemebraneTip_r8 = LeftTopMemebrane4.addOrReplaceChild("LeftRidgeTopMemebraneTip_r8", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -36.01F, -2.75F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-1.5F, -28.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r8 = LeftTopMemebrane4.addOrReplaceChild("LeftRidgeTopMemebrane_r8", CubeListBuilder.create().texOffs(120, 170).addBox(-0.5F, -42.0F, 0.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(120, 170).addBox(-0.5F, -27.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition HindeRotation5 = BackBodyMouthProtection.addOrReplaceChild("HindeRotation5", CubeListBuilder.create(), PartPose.offsetAndRotation(21.75F, -64.25F, 68.0F, -1.0179F, -0.3267F, 0.6416F));

		PartDefinition Hinge5 = HindeRotation5.addOrReplaceChild("Hinge5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftHinge5 = Hinge5.addOrReplaceChild("LeftHinge5", CubeListBuilder.create().texOffs(44, 235).addBox(-1.317F, -19.5391F, -2.0F, 3.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition LowerRidgeRib3Tip_r5 = LeftHinge5.addOrReplaceChild("LowerRidgeRib3Tip_r5", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 7.99F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -17.5391F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeRib3_r5 = LeftHinge5.addOrReplaceChild("LowerRidgeRib3_r5", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 8.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -17.5391F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeRib2Tip_r5 = LeftHinge5.addOrReplaceChild("LowerRidgeRib2Tip_r5", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6624F, -10.5391F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeRib2_r5 = LeftHinge5.addOrReplaceChild("LowerRidgeRib2_r5", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -10.5391F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeLowerMemebrane5 = LeftHinge5.addOrReplaceChild("HingeLowerMemebrane5", CubeListBuilder.create(), PartPose.offset(-4.9227F, -7.5291F, 15.5254F));

		PartDefinition LowerRidgeFrontMembraneTip_r9 = HingeLowerMemebrane5.addOrReplaceChild("LowerRidgeFrontMembraneTip_r9", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -11.01F, -2.75F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r9 = HingeLowerMemebrane5.addOrReplaceChild("LowerRidgeFrontMembrane_r9", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -11.0F, 0.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -14.0254F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LowerRidgeFrontMembraneTip_r10 = HingeLowerMemebrane5.addOrReplaceChild("LowerRidgeFrontMembraneTip_r10", CubeListBuilder.create().texOffs(170, 215).addBox(-1.0F, -11.01F, -5.25F, 0.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -31.0508F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LowerRidgeFrontMembrane_r10 = HingeLowerMemebrane5.addOrReplaceChild("LowerRidgeFrontMembrane_r10", CubeListBuilder.create().texOffs(28, 94).addBox(0.0F, -11.0F, -12.0F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1057F, -0.01F, -17.0254F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleHinge5 = LeftHinge5.addOrReplaceChild("HingeMiddleHinge5", CubeListBuilder.create().texOffs(268, 309).addBox(-1.5905F, -15.487F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.183F, -19.5391F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition MiddleRidgeRib2Tip_r5 = HingeMiddleHinge5.addOrReplaceChild("MiddleRidgeRib2Tip_r5", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -2.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, 5.99F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8454F, -13.0F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeRib2_r5 = HingeMiddleHinge5.addOrReplaceChild("MiddleRidgeRib2_r5", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, 6.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition HingeMiddleMemebrane5 = HingeMiddleHinge5.addOrReplaceChild("HingeMiddleMemebrane5", CubeListBuilder.create(), PartPose.offset(-4.1925F, -6.99F, -15.6758F));

		PartDefinition MiddleRidgeFrontMembraneTip_r5 = HingeMiddleMemebrane5.addOrReplaceChild("MiddleRidgeFrontMembraneTip_r5", CubeListBuilder.create().texOffs(110, 20).addBox(-1.5F, -8.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition MiddleRidgeFrontMembrane_r5 = HingeMiddleMemebrane5.addOrReplaceChild("MiddleRidgeFrontMembrane_r5", CubeListBuilder.create().texOffs(144, 171).addBox(-0.5F, -8.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembrane_r5 = HingeMiddleMemebrane5.addOrReplaceChild("MiddleRidgeBackMembrane_r5", CubeListBuilder.create().texOffs(0, 176).addBox(-0.5F, -8.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -0.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition MiddleRidgeBackMembraneTip_r5 = HingeMiddleMemebrane5.addOrReplaceChild("MiddleRidgeBackMembraneTip_r5", CubeListBuilder.create().texOffs(0, 130).addBox(-1.5F, -8.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition HingeTopHinge5 = HingeMiddleHinge5.addOrReplaceChild("HingeTopHinge5", CubeListBuilder.create().texOffs(254, 309).addBox(-1.5F, -16.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(254, 309).addBox(-1.5F, -31.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 226).addBox(-1.9095F, -23.513F, -10.5F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0905F, -15.487F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition TopRidgeRib3Tip_r5 = HingeTopHinge5.addOrReplaceChild("TopRidgeRib3Tip_r5", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(316, 313).addBox(-2.5F, -23.01F, -2.75F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 17.487F, 15.0746F, 0.0F, -0.6981F, 0.0F));

		PartDefinition TopRidgeRib3_r5 = HingeTopHinge5.addOrReplaceChild("TopRidgeRib3_r5", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(18, 223).addBox(-1.5F, -23.0F, 0.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 17.487F, 1.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition TopRidgeRib2Tip_r5 = HingeTopHinge5.addOrReplaceChild("TopRidgeRib2Tip_r5", CubeListBuilder.create().texOffs(316, 313).addBox(-2.5F, -33.01F, -5.25F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7549F, 24.487F, -15.0746F, 0.0F, 0.6981F, 0.0F));

		PartDefinition TopRidgeRib2_r5 = HingeTopHinge5.addOrReplaceChild("TopRidgeRib2_r5", CubeListBuilder.create().texOffs(18, 223).addBox(-1.5F, -33.0F, -12.0F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0905F, 24.487F, -1.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftTopMemebrane5 = HingeTopHinge5.addOrReplaceChild("LeftTopMemebrane5", CubeListBuilder.create(), PartPose.offset(-4.1021F, 27.497F, -15.6758F));

		PartDefinition LeftRidgeTopMemebraneTip_r9 = LeftTopMemebrane5.addOrReplaceChild("LeftRidgeTopMemebraneTip_r9", CubeListBuilder.create().texOffs(67, 43).addBox(-1.5F, -36.01F, -5.25F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(67, 43).addBox(-1.5F, -28.01F, -5.25F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r9 = LeftTopMemebrane5.addOrReplaceChild("LeftRidgeTopMemebrane_r9", CubeListBuilder.create().texOffs(96, 168).addBox(-0.5F, -42.0F, -12.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(96, 168).addBox(-0.5F, -27.0F, -12.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 14.1758F, 0.0F, 0.3491F, 0.0F));

		PartDefinition LeftRidgeTopMemebraneTip_r10 = LeftTopMemebrane5.addOrReplaceChild("LeftRidgeTopMemebraneTip_r10", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -36.01F, -2.75F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-1.5F, -28.01F, -2.75F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 31.3516F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LeftRidgeTopMemebrane_r10 = LeftTopMemebrane5.addOrReplaceChild("LeftRidgeTopMemebrane_r10", CubeListBuilder.create().texOffs(120, 170).addBox(-0.5F, -42.0F, 0.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(120, 170).addBox(-0.5F, -27.0F, 0.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1925F, -16.01F, 17.1758F, 0.0F, -0.3491F, 0.0F));

		PartDefinition FrontJaw = Kraken.addOrReplaceChild("FrontJaw", CubeListBuilder.create(), PartPose.offset(0.0F, -23.0F, -9.0F));

		PartDefinition jawBottom = FrontJaw.addOrReplaceChild("jawBottom", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 16.0F, 4.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r19 = jawBottom.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(260, 35).addBox(-3.0F, 10.0F, 54.0F, 21.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -37.1577F, -47.2909F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r20 = jawBottom.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(53, 264).addBox(-7.0F, -12.0F, -8.0F, 13.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 26.0F, -21.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r21 = jawBottom.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(48, 262).addBox(-10.0F, -13.0F, -8.0F, 20.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 26.0F, -15.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r22 = jawBottom.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(211, 119).addBox(-11.0F, -21.0F, -5.0F, 21.0F, 6.0F, 18.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 26.0F, 3.0F, 0.9163F, 0.0F, 0.0F));

		PartDefinition head1 = jawBottom.addOrReplaceChild("head1", CubeListBuilder.create(), PartPose.offset(9.0F, 10.0F, -27.0F));

		PartDefinition teeth_r1 = head1.addOrReplaceChild("teeth_r1", CubeListBuilder.create().texOffs(236, 35).addBox(-4.0F, -10.0F, -1.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(108, 301).addBox(-4.0F, -16.0F, -1.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 0.4363F, -3.1416F));

		PartDefinition head2 = jawBottom.addOrReplaceChild("head2", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 9.75F, -16.25F, 0.48F, -0.2618F, 0.0F));

		PartDefinition Head_r1 = head2.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(76, 301).addBox(-13.0F, -14.0F, -1.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(298, 223).addBox(-13.0F, -8.0F, -1.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -8.0F, -9.0F, 0.0F, 1.2654F, -3.1416F));

		PartDefinition head4 = jawBottom.addOrReplaceChild("head4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition teeth_r2 = head4.addOrReplaceChild("teeth_r2", CubeListBuilder.create().texOffs(295, 107).addBox(11.0F, -10.0F, -13.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(170, 300).addBox(11.0F, -16.0F, -13.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 3.0F, -27.0F, 0.0F, -0.7418F, -3.1416F));

		PartDefinition head5 = jawBottom.addOrReplaceChild("head5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r2 = head5.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(298, 209).addBox(15.0F, -13.0F, -20.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(72, 277).addBox(15.0F, -7.0F, -20.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 3.0F, -27.0F, 0.0F, -1.2654F, -3.1416F));

		PartDefinition head3 = jawBottom.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(108, 301).addBox(-4.25F, -8.0F, -28.25F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(236, 35).addBox(-4.25F, -9.0F, -28.25F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, -7.0F));

		PartDefinition head6 = jawBottom.addOrReplaceChild("head6", CubeListBuilder.create().texOffs(108, 301).addBox(-3.25F, -1.0F, -6.25F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(236, 35).addBox(-3.25F, -2.0F, -6.25F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 1.0F, -6.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition head7 = jawBottom.addOrReplaceChild("head7", CubeListBuilder.create().texOffs(108, 301).addBox(-3.25F, -1.0F, -6.25F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(236, 35).addBox(-3.25F, -2.0F, -6.25F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 1.0F, -6.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition jawTop = FrontJaw.addOrReplaceChild("jawTop", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.0F, -7.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r23 = jawTop.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(260, 35).addBox(-3.0F, -26.0F, 54.0F, 21.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 37.1577F, -47.2909F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r24 = jawTop.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(53, 264).addBox(-7.0F, 7.0F, -8.0F, 13.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, -21.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r25 = jawTop.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(48, 262).addBox(-10.0F, 7.0F, -8.0F, 20.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, -15.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r26 = jawTop.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(211, 119).addBox(-11.0F, 15.0F, -5.0F, 21.0F, 6.0F, 18.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -26.0F, 3.0F, -0.9163F, 0.0F, 0.0F));

		PartDefinition head8 = jawTop.addOrReplaceChild("head8", CubeListBuilder.create(), PartPose.offset(9.0F, -10.0F, -27.0F));

		PartDefinition teeth_r3 = head8.addOrReplaceChild("teeth_r3", CubeListBuilder.create().texOffs(236, 35).addBox(-4.0F, 9.0F, -1.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(108, 301).addBox(-4.0F, 10.0F, -1.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, 0.4363F, 3.1416F));

		PartDefinition head9 = jawTop.addOrReplaceChild("head9", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -9.75F, -16.25F, -0.48F, -0.2618F, 0.0F));

		PartDefinition Head_r3 = head9.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(76, 301).addBox(-13.0F, 8.0F, -1.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(298, 223).addBox(-13.0F, 6.0F, -1.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 8.0F, -9.0F, 0.0F, 1.2654F, 3.1416F));

		PartDefinition head10 = jawTop.addOrReplaceChild("head10", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition teeth_r4 = head10.addOrReplaceChild("teeth_r4", CubeListBuilder.create().texOffs(295, 107).addBox(11.0F, 8.0F, -13.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(170, 300).addBox(11.0F, 10.0F, -13.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -3.0F, -27.0F, 0.0F, -0.7418F, 3.1416F));

		PartDefinition head11 = jawTop.addOrReplaceChild("head11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r4 = head11.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(298, 209).addBox(15.0F, 7.0F, -20.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(72, 277).addBox(15.0F, 5.0F, -20.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -3.0F, -27.0F, 0.0F, -1.2654F, 3.1416F));

		PartDefinition head12 = jawTop.addOrReplaceChild("head12", CubeListBuilder.create().texOffs(108, 301).addBox(-4.25F, 2.0F, -28.25F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(236, 35).addBox(-4.25F, 8.0F, -28.25F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -7.0F));

		PartDefinition head13 = jawTop.addOrReplaceChild("head13", CubeListBuilder.create().texOffs(108, 301).addBox(-3.25F, -5.0F, -6.25F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(236, 35).addBox(-3.25F, 1.0F, -6.25F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, -1.0F, -6.0F, -0.6545F, 0.0F, 0.0F));

		PartDefinition head14 = jawTop.addOrReplaceChild("head14", CubeListBuilder.create().texOffs(108, 301).addBox(-3.25F, -5.0F, -6.25F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(236, 35).addBox(-3.25F, 1.0F, -6.25F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -1.0F, -6.0F, -0.6545F, 0.0F, 0.0F));

		PartDefinition rightJaw = FrontJaw.addOrReplaceChild("rightJaw", CubeListBuilder.create(), PartPose.offsetAndRotation(-21.0F, 0.0F, 3.0F, 0.3031F, 0.2725F, -1.6984F));

		PartDefinition cube_r27 = rightJaw.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(53, 262).addBox(-5.0F, 7.0F, -8.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7985F, -19.5391F, -11.0509F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r28 = rightJaw.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(216, 119).addBox(-6.0F, 15.0F, -5.0F, 12.0F, 6.0F, 18.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.7985F, -18.5391F, 5.9491F, -0.9163F, 0.0F, 0.0F));

		PartDefinition head15 = rightJaw.addOrReplaceChild("head15", CubeListBuilder.create(), PartPose.offset(8.7986F, -2.5391F, -17.0509F));

		PartDefinition teeth_r5 = head15.addOrReplaceChild("teeth_r5", CubeListBuilder.create().texOffs(236, 35).addBox(-4.0F, 9.0F, -1.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(108, 301).addBox(-4.0F, 10.0F, -1.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, 0.4363F, 3.1416F));

		PartDefinition head17 = rightJaw.addOrReplaceChild("head17", CubeListBuilder.create(), PartPose.offset(2.7985F, 7.4609F, 9.9491F));

		PartDefinition teeth_r6 = head17.addOrReplaceChild("teeth_r6", CubeListBuilder.create().texOffs(295, 107).addBox(11.0F, 8.0F, -13.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(170, 300).addBox(11.0F, 10.0F, -13.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -3.0F, -27.0F, 0.0F, -0.7418F, 3.1416F));

		PartDefinition head19 = rightJaw.addOrReplaceChild("head19", CubeListBuilder.create().texOffs(108, 301).addBox(-4.25F, 2.0F, -28.25F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(236, 35).addBox(-4.25F, 8.0F, -28.25F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.7985F, -12.5391F, 2.9491F));

		PartDefinition leftJaw = FrontJaw.addOrReplaceChild("leftJaw", CubeListBuilder.create(), PartPose.offsetAndRotation(21.0F, 2.0F, 3.0F, 0.3031F, -0.2725F, 1.6984F));

		PartDefinition cube_r29 = leftJaw.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(53, 262).mirror().addBox(-5.0F, 7.0F, -8.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.7985F, -19.5391F, -11.0509F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r30 = leftJaw.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(216, 119).mirror().addBox(-6.0F, 15.0F, -5.0F, 12.0F, 6.0F, 18.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-0.7985F, -18.5391F, 5.9491F, -0.9163F, 0.0F, 0.0F));

		PartDefinition head16 = leftJaw.addOrReplaceChild("head16", CubeListBuilder.create(), PartPose.offset(-8.7986F, -2.5391F, -17.0509F));

		PartDefinition teeth_r7 = head16.addOrReplaceChild("teeth_r7", CubeListBuilder.create().texOffs(236, 35).mirror().addBox(-4.0F, 9.0F, -1.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(108, 301).mirror().addBox(-4.0F, 10.0F, -1.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, -0.4363F, -3.1416F));

		PartDefinition head18 = leftJaw.addOrReplaceChild("head18", CubeListBuilder.create(), PartPose.offset(-2.7985F, 7.4609F, 9.9491F));

		PartDefinition teeth_r8 = head18.addOrReplaceChild("teeth_r8", CubeListBuilder.create().texOffs(295, 107).mirror().addBox(-19.0F, 8.0F, -13.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(170, 300).mirror().addBox(-19.0F, 10.0F, -13.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.0F, -3.0F, -27.0F, 0.0F, 0.7418F, -3.1416F));

		PartDefinition head20 = leftJaw.addOrReplaceChild("head20", CubeListBuilder.create().texOffs(108, 301).mirror().addBox(-3.75F, 2.0F, -28.25F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(236, 35).mirror().addBox(-3.75F, 8.0F, -28.25F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.7985F, -12.5391F, 2.9491F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		Kraken.render(poseStack, vertexConsumer, packedLight, packedOverlay, alpha);
	}
}