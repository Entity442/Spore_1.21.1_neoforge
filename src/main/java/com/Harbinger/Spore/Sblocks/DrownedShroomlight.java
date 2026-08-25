package com.Harbinger.Spore.Sblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class DrownedShroomlight extends GenericFoliageBlock{
    public DrownedShroomlight() {
        super(Properties.of().sound(SoundType.CROP)
                .strength(0f, 0f).noCollission().noOcclusion().sound(SoundType.SLIME_BLOCK)
                .lightLevel(s -> 3).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.TRUE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());

        if (fluid.getType() == Fluids.WATER) {
            return this.defaultBlockState()
                    .setValue(WATERLOGGED, true);
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        FluidState fluid = level.getFluidState(pos);
        return fluid.getType() == Fluids.WATER;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }
}
