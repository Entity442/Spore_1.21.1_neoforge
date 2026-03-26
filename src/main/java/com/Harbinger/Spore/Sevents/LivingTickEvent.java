package com.Harbinger.Spore.Sevents;

import com.Harbinger.Spore.Effect.SporeEffectsHandler;
import com.Harbinger.Spore.ExtremelySusThings.Package.SongInitializingPacket;
import com.Harbinger.Spore.ExtremelySusThings.SporePacketHandler;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.Sentities.Organoids.Vigil;
import com.Harbinger.Spore.Sentities.TrueCalamity;
import com.Harbinger.Spore.Sentities.Utility.Vanguard;
import com.Harbinger.Spore.Sitems.BaseWeapons.SporeArmorData;
import com.Harbinger.Spore.Sitems.BaseWeapons.SporeToolsBaseItem;
import com.Harbinger.Spore.core.Seffects;
import com.Harbinger.Spore.core.Senchantments;
import com.Harbinger.Spore.core.Sfluids;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.List;

import static com.Harbinger.Spore.Fluids.BileLiquid.bileEffects;

public class LivingTickEvent {
    private static final TagKey<Block> tag = BlockTags.create(ResourceLocation.parse("spore:fungal_blocks"));
    public static void TickEvents(EntityTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LivingEntity living)) {
            return;
        }
        if (living.isInFluidType(Sfluids.BILE_FLUID_TYPE)) {

            if ((living instanceof UtilityEntity || living instanceof TrueCalamity)) {
                living.setDeltaMovement(living.getDeltaMovement().scale(1.2).add(0, 0.01, 0));
                if (!living.hasEffect(MobEffects.REGENERATION)){
                    living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
                }
            } else {
                living.setDeltaMovement(living.getDeltaMovement().scale(0.8));

                if (living.tickCount % 40 == 0) {
                    for (MobEffectInstance effect : bileEffects()) {
                        living.addEffect(effect);
                    }
                    living.hurt(living.damageSources().generic(), 1f);
                }
            }
        }
        List<MobEffectInstance> instances = living.getActiveEffects().stream()
                .filter(instance -> instance.getEffect().value() instanceof SporeEffectsHandler)
                .toList();
        for (MobEffectInstance instance : instances){
            int amp = instance.getAmplifier();
            MobEffect effect = instance.getEffect().value();
            if (effect instanceof SporeEffectsHandler handler) {
                if (handler.isDurationEffectTick(instance.getDuration(), amp)) {
                    handler.triggerEffects(living, amp);
                }
            }
        }

        if (living instanceof Player player) {
            MobEffectInstance effectInstance = player.getEffect(Seffects.MADNESS);
            if (effectInstance != null && effectInstance.getDuration() == 1) {
                int level = effectInstance.getAmplifier();
                if (level > 0) {
                    player.addEffect(new MobEffectInstance(
                            Seffects.MADNESS,
                            12000,
                            level - 1
                    ));
                }
            }

            if (player.tickCount % 400 == 0 && player.level().isClientSide) {
                AABB aabb = player.getBoundingBox().inflate(5);

                List<BlockPos> list = new ArrayList<>();

                BlockPos.betweenClosedStream(aabb)
                        .forEach(blockPos -> {
                            BlockPos immutablePos = blockPos.immutable();
                            if (player.level().getBlockState(immutablePos).is(tag)) {
                                list.add(immutablePos);
                            }
                        });

                if (list.size() > 4) {
                    player.playSound(Ssounds.AREA_AMBIENT.value());
                }
            }


        }
        if (!(event.getEntity() instanceof Mob mob))
            return;

        if (!(mob.getTarget() instanceof ServerPlayer player))
            return;

        if (mob.tickCount % 20 != 0)
            return;

        switch (mob) {
            case Calamity ignored ->
                    SporePacketHandler.sendToClient(new SongInitializingPacket(0, true, true), player);
            case Vanguard ignored ->
                    SporePacketHandler.sendToClient(new SongInitializingPacket(1, true, true), player);
            case Vigil ignored -> SporePacketHandler.sendToClient(new SongInitializingPacket(2, true, true), player);
            default -> {
            }
        }
    }
    public static void TickEffects(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();

        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        // Corrosion - Manual damage application
        if (player.tickCount % 60 == 0) {
            MobEffectInstance corrosion = player.getEffect(Seffects.CORROSION);
            if (corrosion != null) {
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                        ItemStack stack = player.getItemBySlot(slot);
                        if (!stack.isEmpty() && stack.isDamageableItem()) {
                            if (stack.getItem() instanceof SporeArmorData data && !data.tooHurt(stack)){
                                return;
                            }
                            int newDamage = stack.getDamageValue() + corrosion.getAmplifier() + 1;
                            if (newDamage < stack.getMaxDamage()) {
                                if (newDamage != stack.getDamageValue()) {
                                    stack.setDamageValue(newDamage);
                                }
                            }
                        }
                    }
                }
            }
        }

        // Symbiosis - Manual healing
        if (player.tickCount % 200 == 0) {
            MobEffectInstance symbiosis = player.getEffect(Seffects.SYMBIOSIS);
            if (symbiosis != null) {
                int healAmount = (symbiosis.getAmplifier() + 1)  * 2;

                // Heal inventory
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack stack = player.getInventory().getItem(i);
                    if (shouldHealStack(player, stack)) {
                        if (stack.getItem() instanceof SporeToolsBaseItem base) {
                            base.healTool(stack, healAmount);
                        } else if (stack.getItem() instanceof SporeArmorData base) {
                            base.healTool(stack, healAmount);
                        }
                        int newDamage = Math.max(0, stack.getDamageValue() - healAmount);
                        stack.setDamageValue(newDamage);
                    }
                }
            }
        }
    }

    private static boolean shouldHealStack(Player player, ItemStack stack) {
        return !stack.isEmpty() &&
                stack.isDamaged() &&
                Senchantments.hasEnchant(player.level(), stack, Senchantments.SYMBIOTIC_RECONSTITUTION);
    }

}