package azmalent.terraincognita.common.block.trees;

import azmalent.terraincognita.common.init.ModWoodTypes;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class AppleFruitBlock extends Block {
    private static VoxelShape SMALL_SHAPE = makeCuboidShape(6, 9, 6, 10, 13, 10);
    private static VoxelShape BIG_SHAPE = makeCuboidShape(5, 7, 5, 11, 13, 11);

    public static IntegerProperty AGE = BlockStateProperties.AGE_0_7;

    public AppleFruitBlock() {
        super(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.RED).hardnessAndResistance(0.2F).sound(SoundType.WOOD));

        setDefaultState(getStateContainer().getBaseState().with(AGE, 0));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
       builder.add(AGE);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vector3d offset = state.getOffset(worldIn, pos);
        return (state.get(AGE) < 2 ? SMALL_SHAPE : BIG_SHAPE).withOffset(offset.x, offset.y, offset.z);
    }

    @Nonnull
    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState up = worldIn.getBlockState(pos.up());
        return up.isIn(ModWoodTypes.APPLE.LEAVES.getBlock()) || up.isIn(ModWoodTypes.APPLE.BLOSSOMING_LEAVES.getBlock());
    }

    @Nonnull
    @Override
    public BlockState updatePostPlacement(@Nonnull BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Nonnull
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        }

        if (player.isSpectator()) {
            return ActionResultType.CONSUME;
        }

        if (state.get(AGE) == 7) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());

            ItemStack stack = new ItemStack(Items.APPLE);
            ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack);
            itemEntity.setDefaultPickupDelay();
            worldIn.addEntity(itemEntity);

            return ActionResultType.SUCCESS;
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return state.get(AGE) < 7;
    }

    @Override
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerWorld worldIn, @Nonnull BlockPos pos, Random random) {
        if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(25) == 0)) {
            worldIn.setBlockState(pos, state.with(AGE,state.get(AGE) + 1), 2);
            ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
    }

    @Nonnull
    @Override
    public List<ItemStack> getDrops(BlockState state, @Nonnull LootContext.Builder builder) {
        List<ItemStack> drops = Lists.newArrayList();
        if (state.get(AGE) == 7) {
            drops.add(new ItemStack(Items.APPLE));
        }

        return drops;
    }

    @Nonnull
    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(Items.APPLE);
    }

    @Nonnull
    @Override
    public String getTranslationKey() {
        return Items.APPLE.getTranslationKey();
    }
}
