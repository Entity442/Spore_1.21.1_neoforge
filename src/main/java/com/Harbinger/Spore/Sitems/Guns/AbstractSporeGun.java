package com.Harbinger.Spore.Sitems.Guns;

import com.Harbinger.Spore.ExtremelySusThings.Package.SporeGunFirePacket;
import com.Harbinger.Spore.ExtremelySusThings.Package.SporeGunFireSyncPacket;
import com.Harbinger.Spore.ExtremelySusThings.SporePacketHandler;
import com.Harbinger.Spore.Sitems.BaseItem;
import com.Harbinger.Spore.Sitems.BaseWeapons.SporeToolsMutations;
import com.Harbinger.Spore.Sitems.BaseWeapons.SporeWeaponData;
import com.Harbinger.Spore.Sitems.GunHeldItem;
import com.Harbinger.Spore.core.SdataComponents;
import com.Harbinger.Spore.core.Sitems;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public abstract class AbstractSporeGun extends BaseItem implements GunHeldItem, SporeWeaponData {
    public AbstractSporeGun(int durability) {
        super(new Properties().stacksTo(1).durability(durability));
        Sitems.TINTABLE_ITEMS.add(this);
    }
    public abstract boolean needsToReload();
    public abstract int getDefaultTimeBeforeReload();
    public abstract int getTimeBeforeChangingClip();
    public abstract int timeBeforeStomachContentsConvertIntoAmmo();
    public abstract int getClipSize();
    public abstract Item getAmmoItem();
    public int getAmmoUsage(){return 1;}
    public int getBaseAmmoShotRequirement(){return 1;}

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        if (entity instanceof Player player && player.level().isClientSide()) {
            SporePacketHandler.sendToServer(new SporeGunFirePacket(player.getId(), hand == InteractionHand.MAIN_HAND ? 0 : 1));
        }
        return true;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack gun = player.getItemInHand(hand);
        int ammo = gun.getOrDefault(SdataComponents.FLESH_AMMO.get(), 0);
        if (needsToReload() && ammo < getClipSize()) {
            if (ammo <= getClipSize()) {
                for (ItemStack invStack : player.getInventory().items) {
                    if (getAmmoItem().equals(invStack.getItem())) {
                        if (level.isClientSide){
                            triggerReloadAnimation(player);
                            player.playNotifySound(Ssounds.BIOGUN_RELOAD.value(),SoundSource.MASTER,1,1);
                        }else {
                            player.getCooldowns().addCooldown(this,getDefaultTimeBeforeReload());
                            invStack.shrink(1);
                            gun.set(SdataComponents.FLESH_AMMO.get(), getClipSize());
                            gun.set(SdataComponents.RELOAD_DELAY.get(), getDefaultTimeBeforeReload());
                        }
                        break;
                    }
                }
            }
        }
        return super.use(level, player, hand);
    }

    public void triggerReloadAnimation(Player player){

    }

    public void playEmptyFireSounds(ServerPlayer player){
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                Ssounds.BIOGUN_NO_AMMO, SoundSource.PLAYERS, 1.0f, 1.0f);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (level.isClientSide){
            return;
        }
        int shootDelay = stack.getOrDefault(SdataComponents.SHOOT_DELAY.get(), 0);
        if (shootDelay > 0) {
            stack.set(SdataComponents.SHOOT_DELAY.get(), shootDelay - 1);
        }

        int reloadDelay = stack.getOrDefault(SdataComponents.RELOAD_DELAY.get(), 0);
        if (reloadDelay > 0) {
            stack.set(SdataComponents.RELOAD_DELAY.get(), reloadDelay - 1);
        }
        if (needsToReload() && stack.getOrDefault(SdataComponents.FLESH_AMMO.get(), 0) < getClipSize()) {
            int tick = (int)(level.getGameTime() % timeBeforeStomachContentsConvertIntoAmmo());

            if (tick == 0) {
                int stomach = stack.getOrDefault(SdataComponents.STOMACH_CONTENTS.get(), 0);
                int ammo = stack.getOrDefault(SdataComponents.FLESH_AMMO.get(), 0);

                if (stomach > 0) {
                    int conversionRate = 5;

                    int consumed = Math.min(stomach, conversionRate);

                    stack.set(SdataComponents.STOMACH_CONTENTS.get(), stomach - consumed);
                    stack.set(SdataComponents.FLESH_AMMO.get(), ammo + 1);
                }
            }
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack itemStack, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        boolean shouldOverride = clickAction == ClickAction.SECONDARY
                && itemStack.getItem() == Sitems.SYRINGE.get()
                && getVariant(stack) != SporeToolsMutations.DEFAULT;

        if (shouldOverride) {
            this.setVariant(SporeToolsMutations.DEFAULT, stack);
            itemStack.shrink(1);
            player.playNotifySound(Ssounds.SYRINGE_SUCK.value(), SoundSource.AMBIENT, 1f, 1f);
        }
        if (clickAction == ClickAction.SECONDARY){
            int current = stack.getOrDefault(SdataComponents.STOMACH_CONTENTS.get(), 0);
            if (!needsToReload() && current >= getClipSize()){
                return false;
            }
            if (itemStack.getFoodProperties(player) == null){
                return false;
            }
            FoodProperties food = itemStack.getFoodProperties(player);

            if (food != null) {
                int nutrition = food.nutrition();
                float saturation = food.saturation();

                int value = (int)(nutrition + saturation);

                stack.set(SdataComponents.STOMACH_CONTENTS.get(), current + value);
                itemStack.shrink(1);
            }
            player.playNotifySound(SoundEvents.GENERIC_EAT, SoundSource.AMBIENT, 1f, 1f);
            return true;
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {

        int stomach = stack.getOrDefault(SdataComponents.STOMACH_CONTENTS.get(), 0);
        int clip = stack.getOrDefault(SdataComponents.FLESH_AMMO.get(), 0);

        tooltip.add(Component.literal(""));

        if (needsToReload()) {
            tooltip.add(Component.literal("Stomach: " + stomach).withStyle(ChatFormatting.DARK_GREEN));
            tooltip.add(Component.literal("Clip: " + clip + "/" + getClipSize()).withStyle(ChatFormatting.GOLD));
        } else {
            tooltip.add(Component.literal("Biomass: " + stomach + "/" + getClipSize())
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
        }

        int shootDelay = stack.getOrDefault(SdataComponents.SHOOT_DELAY.get(), 0);
        int reloadDelay = stack.getOrDefault(SdataComponents.RELOAD_DELAY.get(), 0);

        if (shootDelay > 0) {
            tooltip.add(Component.literal("Cooling: " + shootDelay + " ticks")
                    .withStyle(ChatFormatting.RED));
        }

        if (reloadDelay > 0) {
            tooltip.add(Component.literal("Reloading: " + reloadDelay + " ticks")
                    .withStyle(ChatFormatting.YELLOW));
        }
        if (getVariant(stack) != SporeToolsMutations.DEFAULT) {
            tooltip.add(Component.literal(Component.translatable("spore.item.mutation").getString() + Component.translatable(getVariant(stack).getName()).getString()));
        }
    }

    public void serverShoot(ItemStack stack, ServerPlayer player, InteractionHand hand, Vec3 vec3) {
        SporePacketHandler.sendToClient(new SporeGunFireSyncPacket(player.getId(), hand == InteractionHand.MAIN_HAND ? 0 : 1), player);
    }

    public void clientShoot(Player player, InteractionHand interactionHand) {

    }
}
