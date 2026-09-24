package com.Harbinger.Spore.Sentities.Calamities;

import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.CalamityInfectedCommand;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.ScatterShotRangedGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SporeBurstSupport;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SummonScentInCombat;
import com.Harbinger.Spore.Sentities.AI.FloatDiveGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.IkUtil.IkSiegerTail;
import com.Harbinger.Spore.Sentities.FallenMultipart.SiegerTail;
import com.Harbinger.Spore.Sentities.HitboxesForParts;
import com.Harbinger.Spore.Sentities.Projectile.ThrownTumor;
import com.Harbinger.Spore.Sentities.TrueCalamity;
import com.Harbinger.Spore.core.SAttributes;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Sentities;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Sieger extends Calamity implements RangedAttackMob, TrueCalamity {
    public static final EntityDataAccessor<Float> TAIL_HP = SynchedEntityData.defineId(Sieger.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Integer> ADAPTATION = SynchedEntityData.defineId(Sieger.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> TARGET = SynchedEntityData.defineId(Sieger.class, EntityDataSerializers.INT);
    private final CalamityMultipart[] subEntities;
    public final CalamityMultipart lowerbody;
    public final CalamityMultipart head;
    public final IkSiegerTail siegerTail;
    public final List<CalamityMultipart> parts = new ArrayList<>();
    public final List<CalamityMultipart> tail = new ArrayList<>();
    public Sieger(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        siegerTail = new IkSiegerTail(this,8,new Vec3(-2.5,1.1,0),new Vec3(0,5,0));
        this.lowerbody = new CalamityMultipart(this, "lowerbody", 3.0F, 3.0F);
        this.head = new CalamityMultipart(this, "head", 1.4F, 1.4F);
        parts.add(lowerbody);
        parts.add(head);
        this.subEntities = parts.toArray(new CalamityMultipart[0]);
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }

    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.sieger_loot.get();
    }
    @Override
    public void setId(int p_20235_) {
        super.setId(p_20235_);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(p_20235_ + i + 1);
    }
    public IkSiegerTail getSiegerTail(){
        return siegerTail;
    }

    @Override
    public double setInflation() {
        return 1.0;
    }

    @Override
    public void tick() {
        super.tick();
        siegerTail.applyIK();
        if (this.getHealth() >= this.getMaxHealth() && this.getTailHp() < this.getMaxTailHp()){
            if (this.tickCount % 40 == 0){
                this.setTailHp(this.getTailHp() +1);
            }
        }
        if (tickCount % 20 == 0 && this.getHealth() < this.getMaxHealth()){
            this.entityData.set(ADAPTATION,this.entityData.get(ADAPTATION)+1);
        }

    }

    @Override
    public void aiStep() {
        float f14 = this.getYRot() * ((float)Math.PI / 180F);
        float f2 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        Vec3[] avec3 = new Vec3[this.subEntities.length];
        for(int j = 0; j < this.subEntities.length; ++j) {
            avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
        }
        this.tickPart(this.head, (double)(f2 * -2.5F), 1.4D, (double)(-f15 * -2.5F));
        this.tickPart(this.lowerbody, (double)(f2 * 3.0F), 0.0D, (double)(-f15 * 3.0F));
        for(int l = 0; l < this.subEntities.length; ++l) {
            this.subEntities[l].xo = avec3[l].x;
            this.subEntities[l].yo = avec3[l].y;
            this.subEntities[l].zo = avec3[l].z;
            this.subEntities[l].xOld = avec3[l].x;
            this.subEntities[l].yOld = avec3[l].y;
            this.subEntities[l].zOld = avec3[l].z;
        }
        super.aiStep();
    }
    public CalamityMultipart[] getSubEntities() {
        return this.subEntities;
    }


    public boolean calculateHeight(){
        Entity living = getEntity();
        return living != null && ((living.getY() > this.getY() && Math.abs(Math.abs(living.getY()) - Math.abs(this.getY())) > 5) || this.distanceToSqr(living) > 200.0D);
    }

    public Entity getEntity(){
        return level().getEntity(entityData.get(TARGET));
    }

    @Override
    public boolean hasLineOfSight(Entity entity) {
        if (calculateHeight()){
            return true;
        }
        return super.hasLineOfSight(entity);
    }
    private int[] ammoAmount(){
        int[] values = new int[2];
        if (this.isAdapted()){
            values[0] = 5;
            values[1] = 8;
        }else{
            values[0] = 3;
            values[1] = 6;
        }
        return values;
    }

    @Override
    public void registerGoals() {

        this.goalSelector.addGoal(3, new ScatterShotRangedGoal(this,1.5,80,48,1,3){
            @Override
            public boolean canUse() {
                if (Sieger.this.getTailHp() <= 0){
                    return false;
                }
                return super.canUse() && calculateHeight();
            }

            @Override
            public void tick() {
                if (target == null){
                    return;
                }
                double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
                boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
                if (flag) {
                    ++this.seeTime;
                } else {
                    this.seeTime = 0;
                }

                if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
                    this.mob.getNavigation().stop();
                } else {
                    this.mob.getNavigation().moveTo(this.target, this.speedModifier);
                }

                this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
                if (--this.attackTime == 0) {
                    if (!flag) {
                        return;
                    }
                    RandomSource randomSource = RandomSource.create();
                    int shot = randomSource.nextInt(Sieger.this.ammoAmount()[0],Sieger.this.ammoAmount()[1] + getExtraShots());

                    float f = (float)Math.sqrt(d0) / this.attackRadius;
                    float f1 = Mth.clamp(f, 0.1F, 1.0F);
                    for (int i = 0; i<shot;++i){
                        this.rangedAttackMob.performRangedAttack(this.target, f1);
                    }
                    this.attackTime = Mth.floor(f * (float)(attackInterval) + (float)this.attackInterval);
                } else if (this.attackTime < 0) {
                    this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double)this.attackRadius, (double)this.attackInterval, (double)this.attackInterval));
                }
            }
        });
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this,0.4F));
        this.goalSelector.addGoal(4, new AOEMeleeAttackGoal(this, 1.5, false,2.5 ,6,livingEntity -> {return TARGET_SELECTOR.test(livingEntity);}){
            protected double getAttackReachSqr(LivingEntity entity) {
                float f = Sieger.this.getBbWidth();
                return (double)(f * 3.0F * f * 3.0F + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.2));
        this.goalSelector.addGoal(6, new FloatDiveGoal(this));
        this.goalSelector.addGoal(6,new CalamityInfectedCommand(this));
        this.goalSelector.addGoal(7,new SummonScentInCombat(this));
        this.goalSelector.addGoal(8,new SporeBurstSupport(this));
        this.goalSelector.addGoal(9,new RandomStrollGoal(this , 1));
        super.registerGoals();
    }

    public boolean canDisableShield() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.sieger_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.sieger_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.sieger_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.STEP_HEIGHT, 1.5)
                .add(Attributes.ATTACK_KNOCKBACK, 2)
                .add(SAttributes.TOXICITY, 0.0D)
                .add(SAttributes.REJUVENATION, 0.0D)
                .add(SAttributes.LOCALIZATION, 0.0D)
                .add(SAttributes.LACERATION, 0.0D)
                .add(SAttributes.CORROSIVES, 0.0D)
                .add(SAttributes.BALLISTIC, 0.0D)
                .add(SAttributes.GRINDING, 0.0D);

    }


    protected SoundEvent getAmbientSound() {
        if (this.getTarget() != null && this.distanceToSqr(this.getTarget()) > 200){
            return null;
        }
        return Ssounds.SIEGER_AMBIENT.value();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.RAVAGER_STEP;
    }

    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public @Nullable PartEntity<?>[] getParts() {
        return subEntities;
    }


    @Override
    public boolean hurt(DamageSource source, float amount) {
        return super.hurt(source,this.isAdapted() ? amount * 0.7f : amount);
    }
    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
        entityData.set(TARGET,living == null ? -1 : living.getId());
    }
    @Override
    public double getDamageCap() {
        return SConfig.SERVER.sieger_dpsr.get();
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity livingEntity, float p_33318_) {
        if(!level().isClientSide){
            ThrownTumor tumor = new ThrownTumor(level(), this);
            double dx = livingEntity.getX() - this.getX();
            double dy = livingEntity.getY() + livingEntity.getEyeHeight() + 5;
            double dz = livingEntity.getZ() - this.getZ();
            if (SConfig.SERVER.sieger_explosive_effects != null){
                List<? extends String> ev = SConfig.SERVER.sieger_explosive_effects.get();
                for (int i = 0; i < 1; ++i) {
                    int randomIndex = random.nextInt(ev.size());
                    ResourceLocation randomElement1 = ResourceLocation.parse(ev.get(randomIndex));
                    Holder<MobEffect> randomElement = Utilities.tryToCreateEffect(randomElement1);
                    tumor.setMobEffect(randomElement);
                }
            }
            tumor.setExplode(Level.ExplosionInteraction.MOB);
            Vec3 vec3 = siegerTail.getEntities()[siegerTail.getEntities().length-1];
            tumor.moveTo(vec3);
            tumor.shoot(dx, dy - tumor.getY() + Math.hypot(dx, dz) * 0.05F, dz, 1f * 2, 12.0F);
            level().addFreshEntity(tumor);
        }
    }

    public boolean isAdapted(){
        return this.entityData.get(ADAPTATION) >= 900;
    }

    @Override
    public void ActivateAdaptation() {
        this.entityData.set(ADAPTATION,900);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.playSound(Ssounds.SIEGER_BITE.value());
        return super.doHurtTarget(entity);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TAIL_HP, this.getMaxTailHp());
        builder.define(ADAPTATION, 0);
        builder.define(TARGET, -1);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("tail_hp", entityData.get(TAIL_HP));
        tag.putInt("adaptation", entityData.get(ADAPTATION));
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(TAIL_HP, tag.getFloat("tail_hp"));
        entityData.set(ADAPTATION, tag.getInt("adaptation"));
    }
    public float getTailHp(){
        return entityData.get(TAIL_HP);
    }
    public void setTailHp(float i){
        entityData.set(TAIL_HP,i);
    }

    public float getMaxTailHp(){
        return (float) (SConfig.SERVER.sieger_hp.get()/4.0f);
    }

    public boolean hurt(CalamityMultipart calamityMultipart, DamageSource source, float value) {
        if (tail.contains(calamityMultipart)){
            if (this.getTailHp() > 0){
                float lostHealth = getTailHp()-this.getDamageAfterArmorAbsorb(source,value);
                this.setTailHp(lostHealth > 0 ? lostHealth : getTailHp() != 0 ? SummonDetashedTail() : 0f);
                this.hurt(source,value * 2);
            }else {
                return false;
            }
        }if (calamityMultipart == this.head){
            this.hurt(source,value * 0.75f);
        }else{
            this.hurt(source,value );
        }
        return true;
    }

    @Override
    public int chemicalRange() {
        return 16;
    }

    @Override
    public List<? extends String> buffs() {
        return SConfig.SERVER.sieger_buffs.get();
    }

    @Override
    public List<? extends String> debuffs() {
        return SConfig.SERVER.sieger_debuffs.get();
    }


    private float SummonDetashedTail(){
        SiegerTail siegerTail = new SiegerTail(Sentities.SIEGER_TAIL.get(),this.level());
        Vec3 vec3 = (new Vec3(-1.7D, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
        siegerTail.setWar(this.isAdapted());
        siegerTail.moveTo(this.getX() + vec3.x, this.getY() + 1.6,this.getZ()+ vec3.z);
        this.level().addFreshEntity(siegerTail);
        this.playSound(Ssounds.LIMB_SLASH.value());
        return 0;
    }


    @Override
    public String getMutation() {
        if (isAdapted()){
            return "spore.entity.variant.war_torn";
        }
        return super.getMutation();
    }


    @Override
    public boolean getAdaptation() {
        return isAdapted();
    }

    private final List<HitboxesForParts> innatePartList = List.of(HitboxesForParts.SIEGER_BODY,
            HitboxesForParts.SIEGER_JAW,
            HitboxesForParts.SIEGER_RIGHT_LEG,HitboxesForParts.SIEGER_LEFT_LEG,
            HitboxesForParts.SIEGER_BACK_RIGHT_LEG,HitboxesForParts.SIEGER_BACK_LEFT_LEG);
    @Override
    public List<HitboxesForParts> parts() {
        List<HitboxesForParts> values = new ArrayList<>();
        if (getTailHp() > 0){
            values.add(HitboxesForParts.SIEGER_TAIL);
        }
        for (HitboxesForParts hitboxes : innatePartList){
            HitboxesForParts part = calculateChance(hitboxes,0.75f);
            if (part != null){
                values.add(part);
            }
        }
        return values;
    }
}
