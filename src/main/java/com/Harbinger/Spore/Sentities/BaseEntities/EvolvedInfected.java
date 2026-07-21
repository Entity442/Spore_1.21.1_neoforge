package com.Harbinger.Spore.Sentities.BaseEntities;


import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.ColdEndurance;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EvolvedInfected extends Infected {
    public EvolvedInfected(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean blockBreakingParameter(BlockState blockstate, BlockPos blockpos) {
        return super.blockBreakingParameter(blockstate, blockpos) || blockstate.is(Utilities.biomass);
    }

    @Override
    protected boolean canRide(Entity entity) {
        if (entity instanceof UtilityEntity){
            return super.canRide(entity);
        }
        return false;
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(this.level().getDifficulty() == Difficulty.HARD && amount < 10000 && amount > getDamageCap() && SConfig.SERVER.damagecap.get()){
            return super.hurt(source, (float) getDamageCap());
        }
        return super.hurt(source, amount);
    }
    public double getDamageCap(){
        return getMaxHealth()/3;
    }

    @Override
    public boolean canStarve() {
        return false;
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.EVOLVE_HURT.value();
    }
    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return this.getLinked() && !(this instanceof com.Harbinger.Spore.Sentities.EvolvedInfected.Scamper);
    }
    @Override
    public ColdEndurance getEndurance() {
        return ColdEndurance.EVOLVED;
    }
}
