package com.Harbinger.Spore.Sblocks;

import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class WallVocalsBlock extends LadderBlock {
    protected static final VoxelShape MOD_EAST_AABB = Block.box(0.0, 0.0, 0.0, 6.0, 16.0, 16.0);
    protected static final VoxelShape MOD_WEST_AABB = Block.box(10.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape MOD_SOUTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 6.0);
    protected static final VoxelShape  MOD_NORTH_AABB = Block.box(0.0, 0.0, 10.0, 16.0, 16.0, 16.0);
    private final static List<SoundEvent> LIST = new ArrayList<>(){{add(Ssounds.INF_GROWL.value());add(Ssounds.INF_VILLAGER_AMBIENT.value());add(Ssounds.INF_PILLAGER_AMBIENT.value());add(Ssounds.WITCH_AMBIENT.value());add(Ssounds.HUSK_AMBIENT.value());}};
    public WallVocalsBlock() {
        super(Properties.of().sound(SoundType.SLIME_BLOCK).noOcclusion().strength(4f,2f).noCollission().randomTicks());
    }
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> MOD_NORTH_AABB;
            case SOUTH -> MOD_SOUTH_AABB;
            case WEST -> MOD_WEST_AABB;
            default -> MOD_EAST_AABB;
        };
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if (Math.random() < 0.1){
            int listSize = LIST.size();
            level.playSound(
                    null,
                    pos,
                    LIST.get(random.nextInt(listSize)),
                    SoundSource.HOSTILE,
                    1f,
                    1f
            );
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }
}
