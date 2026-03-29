package com.Harbinger.Spore.Sitems.Agents;

import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.EvolvedInfected;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Scamper;
import com.Harbinger.Spore.Sentities.EvolvingInfected;
import com.Harbinger.Spore.Sentities.Organoids.Mound;
import com.Harbinger.Spore.Sitems.BaseItem2;
import com.Harbinger.Spore.core.SConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EvolutionSyringe extends BaseItem2 {
    public EvolutionSyringe() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.literal("CREATIVE ONLY").withStyle(ChatFormatting.RED));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity living, InteractionHand hand) {
        switch (living) {
            case Infected infected when infected instanceof EvolvingInfected -> {
                if (infected instanceof EvolvedInfected) {
                    infected.setEvoPoints(infected.getEvoPoints() + SConfig.SERVER.min_kills_hyper.get());
                } else {
                    infected.setEvoPoints(infected.getEvoPoints() + SConfig.SERVER.min_kills.get());
                }
                infected.setEvolution(SConfig.SERVER.evolution_age_human.get());
                return InteractionResult.SUCCESS;
            }
            case Mound mound -> {
                mound.setAge(mound.getAge() + 1);
                return InteractionResult.SUCCESS;
            }
            case Calamity calamity when !calamity.getAdaptation() -> {
                calamity.ActivateAdaptation();
                return InteractionResult.SUCCESS;
            }
            case Scamper scamper -> {
                scamper.setAge(SConfig.SERVER.scamper_age.get());
                return InteractionResult.SUCCESS;
            }
            default -> {
            }
        }
        return super.interactLivingEntity(stack, player, living, hand);
    }
}
