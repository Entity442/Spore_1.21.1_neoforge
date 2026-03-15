package com.Harbinger.Spore.Sentities.EvolvedInfected;

import com.Harbinger.Spore.Sentities.ArmedInfected;
import com.Harbinger.Spore.Sentities.BaseEntities.EvolvedInfected;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.FlyingInfected;
import com.Harbinger.Spore.Sentities.MovementControls.InfectedArialMovementControl;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Gargoyl extends EvolvedInfected implements FlyingInfected, ArmedInfected,HasUsableSlot {
    public Gargoyl(EntityType<? extends Infected> type, Level level) {
        super(type, level);
        this.moveControl = new InfectedArialMovementControl(this , 20,false);
        this.navigation = new FlyingPathNavigation(this,level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.scavenger_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.scavenger_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR,  SConfig.SERVER.scavenger_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(Attributes.FLYING_SPEED, 0.4);
    }

    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.scavenger_loot.get();
    }


    public boolean causeFallDamage(float p_147105_, float p_147106_, DamageSource p_147107_) {
        return false;
    }
    @Override
    public void travel(Vec3 vec) {
        if (this.isEffectiveAi() && !this.onGround()) {
            this.moveRelative(0.1F, vec);
            this.move(MoverType.SELF, this.getDeltaMovement().scale(isInWater() ? 0.2 : 1f));
            this.setDeltaMovement(this.getDeltaMovement().scale(0.85D));
        } else {
            super.travel(vec);
        }
        this.setDeltaMovement(getDeltaMovement().add(0,-0.01,0));
    }



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        super.registerGoals();
    }

    protected SoundEvent getAmbientSound() {
        return Ssounds.ADVENTURER_AMBIENT.value();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_VILLAGER_DEATH.value();
    }

    @Override
    public boolean hasUsableSlot(EquipmentSlot slot) {
        return slot == EquipmentSlot.HEAD;
    }
}
