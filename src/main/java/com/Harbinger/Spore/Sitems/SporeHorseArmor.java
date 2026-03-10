package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Sitems.BaseWeapons.SporeArmorData;
import com.Harbinger.Spore.Sitems.BaseWeapons.SporeArmorMutations;
import com.Harbinger.Spore.core.Sitems;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.common.NeoForgeMod;

public abstract class SporeHorseArmor extends AnimalArmorItem implements SporeArmorData {
    protected static final ResourceLocation BASE_ARMOR_ID = ResourceLocation.withDefaultNamespace("horse_armor_mod");
    protected static final ResourceLocation BASE_TOUGHNESS_ID = ResourceLocation.withDefaultNamespace("horse_step_mod");
    protected static final ResourceLocation BASE_SPEED_ID = ResourceLocation.withDefaultNamespace("horse_speed_mod");
    protected static final ResourceLocation BASE_SWIM_ID = ResourceLocation.withDefaultNamespace("horse_swim_mod");
    private final int protection;
    public SporeHorseArmor(int armorValue) {
        super(ArmorMaterials.LEATHER, BodyType.EQUESTRIAN, false, new Properties().stacksTo(1));
        protection = armorValue;
        Sitems.TINTABLE_ITEMS.add(this);
        Sitems.BIOLOGICAL_ITEMS.add(this);
    }


    @Override
    public ResourceLocation getTexture() {
        return ResourceLocation.parse("spore:textures/entity/empty.png");
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return getDynamicAttributeModifiers(stack);
    }

    public ItemAttributeModifiers getDynamicAttributeModifiers(ItemStack stack) {
        double baseArmor = calculateTrueDefense(stack, protection) + modifyProtection(stack, protection);
        double baseToughness = calculateTrueToughness(stack, 0) + modifyToughness(stack, 0);

        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder()
                .add(Attributes.ARMOR,
                        new AttributeModifier(BASE_ARMOR_ID, baseArmor, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.ARMOR)
                .add(Attributes.ARMOR_TOUGHNESS,
                        new AttributeModifier(BASE_TOUGHNESS_ID, baseToughness, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.ARMOR);
        if (this.getVariant(stack) == SporeArmorMutations.DROWNED){
            builder.add(NeoForgeMod.SWIM_SPEED,
                    new AttributeModifier(BASE_SWIM_ID, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    EquipmentSlotGroup.ARMOR);
        }
        if (this.getVariant(stack) == SporeArmorMutations.REINFORCED || this.getVariant(stack) == SporeArmorMutations.SKELETAL) {
            double speedMod = (this.getVariant(stack) == SporeArmorMutations.REINFORCED) ? -0.01 : 0.01;
            builder.add(Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(BASE_SPEED_ID, speedMod, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    EquipmentSlotGroup.ARMOR);
        }

        return builder.build();
    }

    @Override
    public Holder<SoundEvent> getEquipSound() {
        return Holder.direct(Ssounds.INFECTED_GEAR_EQUIP.value());
    }


    public double modifyProtection(ItemStack stack, double value) {
        if (this.getVariant(stack) == SporeArmorMutations.REINFORCED) {
            return value * 0.2f;
        }
        if (this.getVariant(stack) == SporeArmorMutations.SKELETAL) {
            return value * -0.2f;
        }
        return 0;
    }

    public double modifyToughness(ItemStack stack, double value) {
        if (this.getVariant(stack) == SporeArmorMutations.SKELETAL) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack itemStack, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        boolean shouldOverride = clickAction == ClickAction.SECONDARY
                && itemStack.getItem() == Sitems.SYRINGE.get()
                && getVariant(stack) != SporeArmorMutations.DEFAULT;
        if (shouldOverride) {
            this.setVariant(SporeArmorMutations.DEFAULT, stack);
            itemStack.shrink(1);
            player.playNotifySound(Ssounds.SYRINGE_SUCK.value(), SoundSource.AMBIENT, 1f, 1f);
        }

        return shouldOverride;
    }
}
