package com.Harbinger.Spore.Sentities.Utility;


import com.Harbinger.Spore.ExtremelySusThings.ChunkLoadRequest;
import com.Harbinger.Spore.ExtremelySusThings.ChunkLoaderHelper;
import com.Harbinger.Spore.ExtremelySusThings.SporeSavedData;
import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.FloatDiveGoal;
import com.Harbinger.Spore.Sentities.ArmorPersentageBypass;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.Sentities.ChunkLoaderMob;
import com.Harbinger.Spore.core.*;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.StructureTags;
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
import net.minecraft.world.entity.npc.Villager;
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
import net.minecraft.world.level.ChunkPos;
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

import java.util.*;

import static com.Harbinger.Spore.ExtremelySusThings.Utilities.biomass;

public class Vanguard extends UtilityEntity implements CrossbowAttackMob, Enemy , ArmorPersentageBypass, ChunkLoaderMob {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> KILLS = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> RAID_TIME_OUT = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> VILLAGE = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.BLOCK_POS);
    private int attackAnimationTick;
    public Vanguard(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.navigation = new WallClimberNavigation(this,level);
    }
    @Override
    protected boolean canRide(Entity entity) {
        if (entity instanceof UtilityEntity){
            return super.canRide(entity);
        }
        return false;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.vanguard_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.vanguard_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.vanguard_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(Attributes.ATTACK_KNOCKBACK, 2)
                .add(Attributes.STEP_HEIGHT, 1)
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
    public void setKills(int val){
        entityData.set(KILLS,val);
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_CHARGING_CROSSBOW, false);
        builder.define(KILLS, 0);
        builder.define(RAID_TIME_OUT, 0);
        builder.define(VILLAGE, BlockPos.ZERO);
    }
    public BlockPos getVillage(){
        return entityData.get(VILLAGE);
    }
    public void setVillage(BlockPos pos){
        entityData.set(VILLAGE,pos);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(KILLS,tag.getInt("kills"));
        entityData.set(RAID_TIME_OUT,tag.getInt("raid"));
        int x = tag.getInt("villageX");
        int y = tag.getInt("villageY");
        int z = tag.getInt("villageZ");
        setVillage(new BlockPos(x,y,z));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("kills",entityData.get(KILLS));
        tag.putInt("raid",entityData.get(RAID_TIME_OUT));
        tag.putInt("villageX",getVillage().getX());
        tag.putInt("villageY",getVillage().getY());
        tag.putInt("villageZ",getVillage().getZ());
    }
    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        if (entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(Seffects.MYCELIUM,600,0));
            livingEntity.hurtTime = 0;
            livingEntity.invulnerableTime = 0;
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
        if (level instanceof ServerLevel serverLevel){
            locateVillageOnSpawn(serverLevel);
            teleportToSurface(serverLevel,this);
        }
        this.populateDefaultEquipmentSlots(this.random, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
    private void griefBlocks(){
        AABB aabb = this.getBoundingBox().inflate(0.5D).move(0,0.5,0);
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
        if (state.is(Utilities.biomass)){
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
            griefBlocks();
        }
        if (tickCount % 20 == 0 && this.getHealth() < this.getMaxHealth() && !hasEffect(MobEffects.REGENERATION) && entityData.get(KILLS) > 0){
            this.addEffect(new MobEffectInstance(MobEffects.REGENERATION,400,0));
            entityData.set(KILLS,entityData.get(KILLS)-1);
        }
        if (entityData.get(RAID_TIME_OUT) > 0){
            entityData.set(RAID_TIME_OUT,entityData.get(RAID_TIME_OUT)-1);
        }
        if (tickCount % 40 == 0 && getVillage() != BlockPos.ZERO && getTarget() == null && level() instanceof ServerLevel serverLevel){
            tickMovement(serverLevel);
        }
        if (this.tickCount % 20 == 0 && this.getVanguardRaid() <= 0 && compareTarget(this.getTarget())){
            callReinforcements();
        }
    }

    @Override
    public float amountOfDamage(float value) {
        return value * 0.25f;
    }

    @Override
    public String getChunkId() {
        UUID uuid1 = this.getUUID();
        return "vanguard_"+uuid1+"_";
    }
    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.vanguard_loot.get();
    }
    @Override
    public boolean shouldLoadChunk() {
        return SConfig.SERVER.vanguard_chunk_load.get() && getVillage() != BlockPos.ZERO;
    }

    @Override
    public int chunkLifeTicks() {
        return 20*30;
    }

    public static class VanguardFireGoal extends Goal {
        private final Vanguard vanguard;
        protected BlockPos targetPos;
        protected List<BlockPos> targetPositions = new ArrayList<>();
        protected List<BlockPos> firePositions = new ArrayList<>();
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
                        stop();
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
            return targetPos != null && vanguard.tickCount % 400 != 0;
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
        }

        @Override
        public void stop() {
            super.stop();
            targetPos = null;
            targetPositions.clear();
            firePositions.clear();
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

    public boolean compareTarget(LivingEntity living){
        if (living == null){
            return false;
        }
        return SConfig.SERVER.proto_sapient_target.get().contains(living.getEncodeId()) || living.getHealth() >= 100 || living instanceof Player;
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
            Vec3 vec3 = Utilities.generatePositionAway(this.position(),15);
            ResourceLocation entityId = ResourceLocation.parse(id);
            EntityType<?> entityType = Utilities.tryToCreateEntity(entityId);
            Entity entity = entityType.create(this.level());
            if (entity instanceof Mob mob && this.level() instanceof ServerLevelAccessor accessor) {
                mob.randomTeleport(vec3.x, this.getY(), vec3.z,false);
                mob.finalizeSpawn(
                        accessor,
                        accessor.getCurrentDifficultyAt(BlockPos.containing(this.position())),
                        MobSpawnType.NATURAL,
                        null
                );
                if (mob instanceof Infected infected){
                    infected.setSearchPos(this.getOnPos());
                    infected.setFollowPartner(this);
                    infected.setTarget(this.getTarget());
                    infected.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0));
                }
                accessor.addFreshEntity(mob);
            }
        }
        this.playSound(Ssounds.VANGUARD_CALL.value());
        this.setVanguardRaid(6000);
    }
    private void tickMovement(ServerLevel serverLevel){
        tryTeleportIfFar(serverLevel);
        moveTowardVillage();
        if (this.distanceToSqr(Vec3.atCenterOf(new Vec3i(getVillage().getX(), (int) this.position().y(),getVillage().getZ()))) < (10 * 10)) {
            playArrivalSound();
            this.setVillage(BlockPos.ZERO);
            removeChunkLoad();
        }
    }

    private void tryTeleportIfFar(ServerLevel serverLevel) {
        if (!serverLevel.isLoaded(getVillage())) return;
        double distSqr = this.distanceToSqr(Vec3.atCenterOf(getVillage()));
        if (distSqr < (200 * 200)) return;

        BlockPos tp = findSafeGround(getVillage());

        if (tp != null) {
            this.teleportTo(tp.getX() + 0.5, tp.getY(), tp.getZ() + 0.5);
            addChunkLoad(tp,serverLevel);
        }
    }

    private void moveTowardVillage() {
        this.navigation.stop();
        Path path = this.navigation.createPath(getVillage(), 1);
        if (path != null){
            this.getNavigation().moveTo(path, 1.2);
        }
    }

    private void playArrivalSound() {
        List<Player> players = this.level().getEntitiesOfClass(
                Player.class,
                this.getBoundingBox().inflate(100)
        );

        for (Player p : players) {
            p.playNotifySound(Ssounds.VANGUARD_RAID.value(), SoundSource.MASTER, 1f, 1f);
        }
    }

    private BlockPos findSafeGround(BlockPos pos) {
        ServerLevel level = (ServerLevel) this.level();

        BlockPos.MutableBlockPos mutable = pos.mutable();

        for (int y = 0; y < 20; y++) {
            if (level.getBlockState(mutable).isAir() &&
                    level.getBlockState(mutable.below()).isSolid()) {
                return mutable.immutable();
            }
            mutable.move(Direction.UP);
        }
        return null;
    }

    private void addChunkLoad(BlockPos pos,ServerLevel serverLevel) {
        ChunkPos chunk = new ChunkPos(pos);

        String id = "vanguard_" + this.getUUID();

        ChunkLoaderHelper.addRequest(new ChunkLoadRequest(
                serverLevel.dimension(),
                new ChunkPos[]{chunk},
                0,
                id,
                this.chunkLifeTicks(),
                this.getUUID()
        ));
    }

    private void removeChunkLoad() {
        String id = "vanguard_" + this.getUUID();
        ChunkLoaderHelper.removeRequest(id);
    }
    @Override
    public boolean removeWhenFarAway(double value) {
        if (this.level() instanceof ServerLevel serverLevel){
            SporeSavedData data = SporeSavedData.getDataLocation(serverLevel);
            return data != null && data.getAmountOfHiveminds() >= SConfig.SERVER.proto_spawn_world_mod.get() && value > 256;
        }
        return false;
    }
    private void locateVillageOnSpawn(ServerLevel serverLevel) {

        List<Villager> villagers =
                serverLevel.getEntitiesOfClass(
                        Villager.class,
                        new AABB(this.blockPosition()).inflate(256)  // search 256 blocks
                );

        if (!villagers.isEmpty()) {
            Villager nearest = villagers.stream()
                    .min((a,b) -> Double.compare(this.distanceToSqr(a), this.distanceToSqr(b)))
                    .orElse(null);

            BlockPos villPos = nearest.blockPosition();
            this.setVillage(villPos);
            return;
        }
        int radius = 128;

        BlockPos foundVillage = serverLevel.findNearestMapStructure(
                StructureTags.VILLAGE,
                this.blockPosition(),
                radius,
                false
        );

        this.setVillage(Objects.requireNonNullElse(foundVillage, BlockPos.ZERO));
    }
    public void teleportToSurface(Level level, Mob entity) {
        if (level.canSeeSky(entity.blockPosition())){
            return;
        }
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(
                Mth.floor(entity.getX()),
                level.getMaxBuildHeight(),
                Mth.floor(entity.getZ())
        );

        while (pos.getY() > level.getMinBuildHeight()) {
            pos.move(Direction.DOWN);
            BlockState state = level.getBlockState(pos);
            BlockState stateAbove = level.getBlockState(pos.above());
            if (state.isSolidRender(level, pos) && stateAbove.isAir()) {
                entity.teleportTo(pos.getX() + 0.5D, pos.getY() + 1.01D, pos.getZ() + 0.5D);
                return;
            }
        }
    }
}
