package com.Harbinger.Spore.Sentities.Projectile;

import com.Harbinger.Spore.core.Sentities;
import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Conductor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ChainProjectile extends AbstractArrow {
    private static final EntityDataAccessor<Integer> DATA_ID = SynchedEntityData.defineId(ChainProjectile.class, EntityDataSerializers.INT);
    public ChainProjectile(Level level) {
        super(Sentities.CHAIN_PROJECTILE.get(),level);
    }
    public ChainProjectile(Level level,LivingEntity living) {
        super(Sentities.CHAIN_PROJECTILE.get(),level);
        setOwner(living);
        setAttackId(living);
    }
    public ChainProjectile(EntityType<ChainProjectile> acidBallEntityType, Level level) {
        super(acidBallEntityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID,-1);
    }

    public int getAttackedId(){
        return entityData.get(DATA_ID);
    }
    public void setAttackId(int i){
        entityData.set(DATA_ID,i);
    }
    public void setAttackId(LivingEntity i){
        entityData.set(DATA_ID,i == null ? -1 : i.getId());
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.CHAIN);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGround || this.isInFluidType())
            this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        playSound(SoundEvents.CHAIN_BREAK);
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        if (hitResult.getEntity() instanceof LivingEntity living && Utilities.TARGET_SELECTOR.Test(living)){
            if (getOwner() instanceof Conductor conductor){
                conductor.setAttackId(living);
                this.discard();
            }
        }
    }
}
