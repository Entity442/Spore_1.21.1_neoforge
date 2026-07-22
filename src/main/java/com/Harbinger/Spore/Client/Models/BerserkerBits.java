package com.Harbinger.Spore.Client.Models;

import net.minecraft.client.model.geom.ModelPart;

import java.util.List;

public interface BerserkerBits {
    List<ModelPart> HeadList();
    List<ModelPart> ChestList();
    List<ModelPart> RightLegList();
    List<ModelPart> LeftLegList();
    ModelPart HeadWear();
    ModelPart Chest();
    ModelPart RightLeg();
    ModelPart LeftLeg();
}
