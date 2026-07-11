package com.Harbinger.Spore.Sentities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.gameevent.PositionSource;

public interface SporeVibrationParameters {
    void setTargetedLocation(ServerLevel serverLevel, BlockPos blockPos);
    BlockPos getTargetLocation();
    PositionSource getPositionSource();
}
