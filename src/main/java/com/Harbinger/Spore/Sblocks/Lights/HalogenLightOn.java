package com.Harbinger.Spore.Sblocks.Lights;

import com.Harbinger.Spore.core.Sblocks;
import com.Harbinger.Spore.core.Sitems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.ItemAbilities;

public class HalogenLightOn extends BaseHalogenLight{
    public HalogenLightOn() {
        super(Properties.of().strength(6, 4).sound(SoundType.GLASS).lightLevel(s -> 16).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(Sitems.HALOGEN_LIGHT.get());
    }
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem().canPerformAction(stack, ItemAbilities.PICKAXE_DIG)){
            if (!level.isClientSide()){
                player.playNotifySound(SoundEvents.GLASS_BREAK, SoundSource.BLOCKS,1,1);
                level.setBlock(pos, Sblocks.BROKEN_HALOGEN_LIGHT.get().defaultBlockState(),3);
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 15;
    }
}
