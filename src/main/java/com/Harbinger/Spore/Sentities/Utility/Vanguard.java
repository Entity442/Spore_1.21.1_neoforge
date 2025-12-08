package com.Harbinger.Spore.Sentities.Utility;


import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.FloatDiveGoal;
import com.Harbinger.Spore.Sentities.ArmorPersentageBypass;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.core.*;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.enchantment.Enchantments;
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
    private static final EntityDataAccessor<Integer> KILLS = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> RAID_TIME_OUT = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.INT);
    private int attackAnimationTick;
    public Vanguard(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.navigation = new WallClimberNavigation(this,level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.vanguard_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.vanguard_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.vanguard_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(Attributes.ATTACK_KNOCKBACK, 2)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }

    @Override
    protected void registerGoals() {
        addTargettingGoals();
        this.goalSelector.addGoal(1,new VanguardRangedCrossbowAttackGoal<>(this,12));
        this.goalSelector.addGoal(2, new CustomMeleeAttackGoal(this, 1, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 6.0 + entity.getBbWidth() * entity.getBbWidth();}});
        this.goalSelector.addGoal(2,new VanguardFireGoal(this));
        this.goalSelector.addGoal(4,new VanguardCallRaid(this));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6,new FloatDiveGoal(this));
        super.registerGoals();
    }
    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return super.calculateFallDamage(p_21237_, p_21238_) - 15;
    }
    protected SoundEvent getAmbientSound() {
        return isInvisible() ? null : Ssounds.VANGUARD_AMBIENT.value();
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.EVOLVE_HURT.value();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.value();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
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
        builder.define(KILLS, 0);
        builder.define(RAID_TIME_OUT, 0);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(KILLS,tag.getInt("kills"));
        entityData.set(RAID_TIME_OUT,tag.getInt("raid"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("kills",entityData.get(KILLS));
        tag.putInt("raid",entityData.get(RAID_TIME_OUT));
    }
    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        if (entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(Seffects.MYCELIUM,600,0));
        }
        this.playSound(Ssounds.VANGUARD_SLASH.value());
        return super.doHurtTarget(entity);
    }

    @Override
    public void awardKillScore(Entity killed, int scoreValue, DamageSource source) {
        super.awardKillScore(killed, scoreValue, source);
        entityData.set(KILLS,entityData.get(KILLS)+1);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FIREWORKS)){
            return false;
        }
        if (source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.IN_FIRE)){
            amount = amount/2;
        }
        return super.hurt(source, amount);
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
        ItemStack itemstack = this.getMainHandItem();
        if (itemstack.is(Items.CROSSBOW)) {
            Senchantments.EnchantItem(level(),itemstack, Enchantments.MULTISHOT);
            Senchantments.EnchantItem(level(),itemstack, Enchantments.QUICK_CHARGE,3);
        }
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
        if (tickCount % 20 == 0 && this.getHealth() < this.getMaxHealth() && !hasEffect(MobEffects.REGENERATION) && entityData.get(KILLS) > 0){
            this.addEffect(new MobEffectInstance(MobEffects.REGENERATION,400,0));
            entityData.set(KILLS,entityData.get(KILLS)-1);
        }
        if (entityData.get(RAID_TIME_OUT) > 0){
            entityData.set(RAID_TIME_OUT,entityData.get(RAID_TIME_OUT)-1);
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
            boolean thereAreBurnAbleBlocks = false;
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
                if (block.isFlammable(blockstate,vanguard.level(),blockpos,Direction.UP)){
                    thereAreBurnAbleBlocks = true;
                }
            }

            if (!targetPositions.isEmpty() && thereAreBurnAbleBlocks) {
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

            vanguard.level().removeBlock(targetPos,true);
            vanguard.playSound(Ssounds.VANGUARD_GRIEF.value());
            targetPos = null;
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

    public static class VanguardRangedCrossbowAttackGoal<T extends Mob & CrossbowAttackMob> extends Goal {
        private final T mob;
        private VanguardRangedCrossbowAttackGoal.CrossbowState crossbowState;
        private final float attackRadiusSqr;
        private int attackDelay;
        private boolean fireworks;

        public VanguardRangedCrossbowAttackGoal(T mob, float attackRadiusSqr) {
            this.crossbowState = VanguardRangedCrossbowAttackGoal.CrossbowState.UNCHARGED;
            this.mob = mob;
            this.attackRadiusSqr = attackRadiusSqr * attackRadiusSqr;
        }

        public boolean canUse() {
            return this.isHoldingCrossbow();
        }

        private boolean isHoldingCrossbow() {
            return this.mob.isHolding((is) -> {
                return is.getItem() instanceof CrossbowItem;
            });
        }

        public boolean canContinueToUse() {
            return this.isHoldingCrossbow();
        }


        public void stop() {
            super.stop();
            if (this.mob.isUsingItem()) {
                this.mob.stopUsingItem();
                this.mob.setChargingCrossbow(false);
                this.mob.getUseItem().set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
            }

        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }
        private ItemStack createExplosiveRocket() {
            ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);
            this.fireworks = true;
            FireworkExplosion explosion = new FireworkExplosion(
                    FireworkExplosion.Shape.BURST,
                    IntList.of(3887386),
                    IntList.of(4312372),
                    true,
                    true
            );
            FireworkExplosion explosion2 = new FireworkExplosion(
                    FireworkExplosion.Shape.LARGE_BALL,
                    IntList.of(15435844),
                    IntList.of(14602026),
                    true,
                    true
            );
            FireworkExplosion explosion3 = new FireworkExplosion(
                    FireworkExplosion.Shape.STAR,
                    IntList.of(2437522),
                    IntList.of(2651799),
                    true,
                    true
            );
            Fireworks fireworks = new Fireworks(
                    1,
                    List.of(explosion,explosion2,explosion3)
            );
            rocket.set(DataComponents.FIREWORKS, fireworks);
            return rocket;
        }
        private ItemStack getArrow(){
            this.fireworks = false;
            return PotionContents.createItemStack(Items.TIPPED_ARROW, Spotion.MYCELIUM_POTION);
        }
        public void tick() {
            LivingEntity target = this.mob.getTarget();

            boolean hasLOS = target != null && this.mob.getSensing().hasLineOfSight(target);
            double dist = target == null ? 0 : this.mob.distanceToSqr(target);



            // ------------ CROSSBOW LOGIC ------------
            switch (this.crossbowState) {

                case UNCHARGED:
                    // BEGIN CHARGE
                    this.mob.startUsingItem(ProjectileUtil.getWeaponHoldingHand(
                            this.mob, i -> i instanceof CrossbowItem));
                    this.mob.setChargingCrossbow(true);
                    this.crossbowState = CrossbowState.CHARGING;
                    break;


                case CHARGING:
                    if (!this.mob.isUsingItem()) {
                        this.crossbowState = CrossbowState.UNCHARGED;
                        break;
                    }

                    int useTicks = this.mob.getTicksUsingItem();
                    ItemStack chargingStack = this.mob.getUseItem();

                    if (useTicks >= CrossbowItem.getChargeDuration(chargingStack, this.mob)) {

                        // ===== FINISH CHARGE =====
                        this.mob.releaseUsingItem();
                        this.mob.setChargingCrossbow(false);

                        // ===== LOAD FIREWORK ROCKETS HERE (NOW IT WORKS) =====
                        ItemStack bow = this.mob.getItemInHand(
                                ProjectileUtil.getWeaponHoldingHand(this.mob, i -> i instanceof CrossbowItem));
                        bow.set(DataComponents.CHARGED_PROJECTILES,Math.random() <= 0.2f ? ChargedProjectiles.of(createExplosiveRocket()) : ChargedProjectiles.of(getArrow()));

                        this.crossbowState = CrossbowState.CHARGED;
                        this.attackDelay = 10;
                    }
                    break;


                case CHARGED:
                    if (--this.attackDelay <= 0) {
                        this.crossbowState = CrossbowState.READY_TO_ATTACK;
                    }
                    break;


                case READY_TO_ATTACK:
                    if (hasLOS && dist != 0 && dist <= this.attackRadiusSqr) {
                        this.mob.performRangedAttack(target, 1.0F);
                        this.crossbowState = CrossbowState.UNCHARGED;
                        this.mob.playSound(fireworks ? Ssounds.VANGUARD_FIREWORKS.value() : Ssounds.VANGUARD_SHOOT.value());
                    }
                    break;
            }

        }

         enum CrossbowState {
            UNCHARGED,
            CHARGING,
            CHARGED,
            READY_TO_ATTACK;

        }
    }
    public int getVanguardRaid(){
        return entityData.get(RAID_TIME_OUT);
    }
    public void setVanguardRaid(int val){
        entityData.set(RAID_TIME_OUT,val);
    }


    private static class VanguardCallRaid extends Goal {
        private final Vanguard vanguard;
        private static final ItemStack stack = new ItemStack(Items.GOAT_HORN);

        private VanguardCallRaid(Vanguard vanguard) {
            this.vanguard = vanguard;
        }
        public boolean compareTarget(LivingEntity living){
            if (living == null){
                return false;
            }
            return SConfig.SERVER.proto_sapient_target.get().contains(living.getEncodeId()) || living.getHealth() >= 100 || living instanceof Player;
        }

        @Override
        public boolean canUse() {
            return vanguard.tickCount % 20 == 0 && vanguard.getVanguardRaid() <= 0 && compareTarget(vanguard.getTarget());
        }

        @Override
        public void start() {
            super.start();

            this.vanguard.setItemSlot(EquipmentSlot.OFFHAND,stack);
            callReinforcements();
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }


        @Override
        public void stop() {
            super.stop();
            this.vanguard.setItemSlot(EquipmentSlot.OFFHAND,ItemStack.EMPTY);
        }

        private void callReinforcements(){
            List<String> ids = new ArrayList<>();
            while (ids.size() < SConfig.SERVER.vanguard_raid_size.get()){
                for (String s : SConfig.SERVER.vanguard_members.get()){
                    String[] str = s.split("\\|");
                    if (Math.random() < (Integer.parseUnsignedInt(str[1])/100f)){
                        ids.add(str[0]);
                        break;
                    }
                }
            }

            for (String id : ids){
                Vec3 vec3 = Utilities.generatePositionAway(vanguard.position(),30);
                ResourceLocation entityId = ResourceLocation.parse(id);
                EntityType<?> entityType = Utilities.tryToCreateEntity(entityId);
                Entity entity = entityType.create(vanguard.level());
                if (entity instanceof Mob mob && vanguard.level() instanceof ServerLevelAccessor accessor) {
                    mob.randomTeleport(vec3.x, vanguard.getY(), vec3.z,false);
                    mob.finalizeSpawn(
                            accessor,
                            accessor.getCurrentDifficultyAt(BlockPos.containing(vanguard.position())),
                            MobSpawnType.NATURAL,
                            null
                    );
                    if (mob instanceof Infected infected){
                        infected.setSearchPos(vanguard.getOnPos());
                    }
                    accessor.addFreshEntity(mob);
                }
            }
            vanguard.playSound(Ssounds.VANGUARD_CALL.value());
            vanguard.setVanguardRaid(6000);
        }
    }
}
