package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Charger;
import com.Harbinger.Spore.Sentities.Projectile.Echo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import org.jetbrains.annotations.Nullable;

public class SporeVibrationUser implements VibrationSystem.User {
    private final SporeVibrationParameters living;
    private final Charger charger;
    private final PositionSource positionSource;

    public SporeVibrationUser(SporeVibrationParameters living, Charger charger) {
        this.living = living;
        positionSource = living.getPositionSource();
        this.charger = charger;
    }

    @Override
    public int getListenerRadius() {
        return 20;
    }
    public boolean canTriggerAvoidVibration() {
        return true;
    }
    @Override
    public PositionSource getPositionSource() {
        return positionSource;
    }


    @Override
    public boolean canReceiveVibration(ServerLevel serverLevel, BlockPos blockPos, Holder<GameEvent> holder, GameEvent.Context context) {
        if (charger.getMeleeTicks() > 0){
            return false;
        }
        if (context.sourceEntity() instanceof Echo){
            return false;
        }
        if (context.sourceEntity() instanceof Projectile){
            return true;
        }
        if (context.sourceEntity() instanceof LivingEntity living1) {
            return Utilities.TARGET_SELECTOR.Test(living1);
        }
        return charger.isAlive() && charger.getTargetLocation().equals(BlockPos.ZERO);
    }

    @Override
    public void onReceiveVibration(ServerLevel serverLevel, BlockPos blockPos, Holder<GameEvent> holder, @Nullable Entity entity, @Nullable Entity entity1, float v) {
        living.setTargetedLocation(serverLevel,blockPos);
    }

    public TagKey<GameEvent> getListenableEvents() {
        return GameEventTags.WARDEN_CAN_LISTEN;
    }
}
