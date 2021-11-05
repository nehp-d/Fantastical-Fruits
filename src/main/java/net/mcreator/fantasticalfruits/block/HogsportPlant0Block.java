
package net.mcreator.fantasticalfruits.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IWorldReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.loot.LootContext;
import net.minecraft.item.TallBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.fantasticalfruits.item.HogsportItem;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

import java.util.List;
import java.util.Collections;

@FantasticalFruitsModElements.ModElement.Tag
public class HogsportPlant0Block extends FantasticalFruitsModElements.ModElement {
	@ObjectHolder("fantastical_fruits:hogsport_plant_0")
	public static final Block block = null;
	public HogsportPlant0Block(FantasticalFruitsModElements instance) {
		super(instance, 264);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items.add(() -> new TallBlockItem(block, new Item.Properties().group(null)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class BlockCustomFlower extends DoublePlantBlock {
		public BlockCustomFlower() {
			super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.NETHER_VINE).hardnessAndResistance(0f, 0f)
					.setLightLevel(s -> 0));
			setRegistryName("hogsport_plant_0");
		}

		@Override
		public Block.OffsetType getOffsetType() {
			return Block.OffsetType.NONE;
		}

		@Override
		public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
			return new ItemStack(HogsportItem.block);
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			if (state.get(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.LOWER)
				return Collections.emptyList();
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(Blocks.AIR, (int) (0)));
		}

		@Override
		public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
			Block ground = state.getBlock();
			return (ground == Blocks.CRIMSON_NYLIUM);
		}

		@Override
		public boolean isValidPosition(BlockState blockstate, IWorldReader worldIn, BlockPos pos) {
			BlockPos blockpos = pos.down();
			BlockState groundState = worldIn.getBlockState(blockpos);
			Block ground = groundState.getBlock();
			if (blockstate.get(HALF) == DoubleBlockHalf.UPPER)
				return groundState.isIn(this) && groundState.get(HALF) == DoubleBlockHalf.LOWER;
			else
				return this.isValidGround(groundState, worldIn, blockpos);
		}

		@Override
		public PlantType getPlantType(IBlockReader world, BlockPos pos) {
			return PlantType.NETHER;
		}
	}
}
