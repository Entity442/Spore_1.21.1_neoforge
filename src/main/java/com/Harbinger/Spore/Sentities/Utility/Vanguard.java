package com.Harbinger.Spore.Sentities.Utility;


import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.FloatDiveGoal;
import com.Harbinger.Spore.Sentities.ArmorPersentageBypass;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Sblocks;
import com.Harbinger.Spore.core.Seffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.Harbinger.Spore.ExtremelySusThings.Utilities.biomass;

public class Vanguard extends UtilityEntity implements CrossbowAttackMob, Enemy , ArmorPersentageBypass {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.BOOLEAN);
    private int attackAnimationTick;
    public Vanguard(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.specter_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.specter_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.specter_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(Attributes.ATTACK_KNOCKBACK, 3);

    }

    @Override
    protected void registerGoals() {
        addTargettingGoals();
        this.goalSelector.addGoal(2, new CustomMeleeAttackGoal(this, 1, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 6.0 + entity.getBbWidth() * entity.getBbWidth();}});
        this.goalSelector.addGoal(3,new VanguardFireGoal(this));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6,new FloatDiveGoal(this));
        super.registerGoals();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
    }
    public boolean canFireProjectileWeapon(ProjectileWeaponItem projectileWeapon) {
        return projectileWeapon == Items.CROSSBOW;
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_CHARGING_CROSSBOW, false);
    }
    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        if (entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(Seffects.MYCELIUM,600,0));
        }
        return super.doHurtTarget(entity);
    }

    public void handleEntityEvent(byte value) {
        if (value == 4) {
            this.attackAnimationTick = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleEntityEvent(value);
        }
    }

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        this.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(Items.CROSSBOW));
    }
    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        this.populateDefaultEquipmentSlots(this.random, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
    private void griefBlocks(LivingEntity livingEntity){
        AABB aabb = (livingEntity != null && livingEntity.getY() > this.getY()) ? this.getBoundingBox().inflate(-0.2D,0.5D,-0.2D).move(0,0.5,0) : this.getBoundingBox().inflate(0.5D).move(0,0.5,0);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (blockBreakingParameter(blockstate,blockpos)) {
                interactBlock(blockpos,this.level());
            }
        }
    }
    public boolean blockBreakingParameter(BlockState blockstate, BlockPos blockpos) {
        float value = blockstate.getDestroySpeed(this.level(),blockpos);
        return this.tickCount % 20 == 0 && value > 0 && value <=getBreaking();
    }
    public int getBreaking(){
        return SConfig.SERVER.hyper_bd.get();
    }

    public boolean interactBlock(BlockPos blockPos, Level level) {
        BlockState state = level.getBlockState(blockPos);
        if (biomass().contains(state)){
            return level.setBlock(blockPos, Sblocks.MEMBRANE_BLOCK.get().defaultBlockState(), 3);
        }
        return level.destroyBlock(blockPos, false, this);
    }
    public boolean isChargingCrossbow() {
        return this.entityData.get(IS_CHARGING_CROSSBOW);
    }

    public void setChargingCrossbow(boolean p_33302_) {
        this.entityData.set(IS_CHARGING_CROSSBOW, p_33302_);
    }

    @Override
    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }


    @Override
    public void performRangedAttack(LivingEntity livingEntity, float v) {
        this.performCrossbowAttack(this, 3.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if (tickCount % 40 == 0 && horizontalCollision && EventHooks.canEntityGrief(this.level(), this)){
            griefBlocks(this.getTarget());
        }
    }

    @Override
    public float amountOfDamage(float value) {
        return value * 0.25f;
    }

    public static class VanguardFireGoal extends Goal {
        private final Vanguard vanguard;
        protected BlockPos targetPos;
        protected List<BlockPos> targetPositions = new ArrayList<>();
        protected List<BlockPos> firePositions = new ArrayList<>();
        private static final ItemStack FLINT = new ItemStack(Items.FLINT_AND_STEEL);
        private int fireCooldown = 0;

        public VanguardFireGoal(Vanguard vanguard) {
            this.vanguard = vanguard;
        }

        boolean searchForFunnyBlocks(){
            targetPositions.clear();
            firePositions.clear();
            AABB aabb = this.vanguard.getBoundingBox().inflate(10, 6, 10);

            for(BlockPos blockpos : BlockPos.betweenClosed(
                    Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ),
                    Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {

                BlockState blockstate = vanguard.level().getBlockState(blockpos);
                Block block = blockstate.getBlock();

                if (block instanceof BedBlock || block instanceof FurnaceBlock){
                    targetPositions.add(blockpos.immutable());
                }
                if (block instanceof FireBlock){
                    firePositions.add(blockpos.immutable());
                }
            }

            if (!targetPositions.isEmpty()) {
                this.targetPos = targetPositions.get(vanguard.getRandom().nextInt(targetPositions.size()));
                boolean hasFireNearby = firePositions.stream()
                        .anyMatch(pos -> pos.distSqr(targetPos) < 36);
                return !hasFireNearby;
            }
            return false;
        }

        @Override
        public boolean canUse() {
            // Check every few seconds, not on exact tick counts
            if (vanguard.tickCount % 40 == 0) {
                return searchForFunnyBlocks();
            }
            return false;
        }

        @Override
        public void start() {
            super.start();
            if (targetPos != null){
                // Try to create a path to the target
                Path path = this.vanguard.navigation.createPath(targetPos, 1);
                if (path != null) {
                    this.vanguard.navigation.moveTo(path, 1.0);
                }
                vanguard.setItemSlot(EquipmentSlot.OFFHAND, FLINT);
            }
            fireCooldown = 0;
        }

        @Override
        public void tick() {
            super.tick();
            fireCooldown++;

            if (targetPos != null){
                // Check distance to target
                double distanceSqr = targetPos.distToCenterSqr(vanguard.position());

                if (distanceSqr < 25) { // Within 5 blocks
                    // Set fire and move away
                    if (fireCooldown >= 20) { // Every second
                        setFire();
                        fireCooldown = 0;

                        // Move away from the fire
                        Vec3 awayDirection = vanguard.position().subtract(Vec3.atCenterOf(targetPos)).normalize();
                        Vec3 awayPos = vanguard.position().add(awayDirection.scale(10));

                        Path awayPath = this.vanguard.navigation.createPath(
                                BlockPos.containing(awayPos), 1);
                        if (awayPath != null) {
                            this.vanguard.navigation.moveTo(awayPath, 1.5);
                        }
                    }
                } else {
                    // Keep moving toward target
                    if (this.vanguard.navigation.isDone()) {
                        Path path = this.vanguard.navigation.createPath(targetPos, 1);
                        if (path != null) {
                            this.vanguard.navigation.moveTo(path, 1.0);
                        }
                    }
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            // Continue if we have a target and haven't reached timeout
            return targetPos != null && vanguard.tickCount % 400 != 0; // Timeout after 20 seconds
        }

        public void setFire(){
            if (targetPos == null) return;

            AABB aabb = new AABB(targetPos).inflate(3, 2, 3);
            for(BlockPos blockpos : BlockPos.betweenClosed(
                    Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ),
                    Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {

                BlockState blockstate = vanguard.level().getBlockState(blockpos);

                // Random chance to set fire
                if (vanguard.getRandom().nextFloat() < 0.3f) {
                    for(Direction direction : Direction.values()) {
                        BlockPos adjacentPos = blockpos.relative(direction);
                        BlockState adjacentState = vanguard.level().getBlockState(adjacentPos);

                        // Check if block is flammable and adjacent position is air
                        if (blockstate.isFlammable(vanguard.level(), blockpos, direction) &&
                                adjacentState.isAir()) {

                            // Try to set fire
                            BlockState fireState = Blocks.FIRE.defaultBlockState();
                            if (FireBlock.canBePlacedAt(vanguard.level(), adjacentPos, direction)) {
                                vanguard.level().setBlock(adjacentPos, fireState, 3);
                                break; // Set fire in one direction only
                            }
                        }
                    }
                }
            }

            // Also play sound effect
            vanguard.playSound(SoundEvents.FLINTANDSTEEL_USE);
        }

        @Override
        public void stop() {
            super.stop();
            targetPos = null;
            targetPositions.clear();
            firePositions.clear();
            vanguard.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            this.vanguard.navigation.stop();
        }
    }
}
