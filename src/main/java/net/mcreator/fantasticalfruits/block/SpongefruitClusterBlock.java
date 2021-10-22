
package net.mcreator.fantasticalfruits.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.fluid.FluidState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.fantasticalfruits.procedures.SpongefruitClusterDropProcedure;
import net.mcreator.fantasticalfruits.itemgroup.FantasticalBlocksItemGroup;
import net.mcreator.fantasticalfruits.item.SpongefruitItem;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

@FantasticalFruitsModElements.ModElement.Tag
public class SpongefruitClusterBlock extends FantasticalFruitsModElements.ModElement {
	@ObjectHolder("fantastical_fruits:spongefruit_cluster")
	public static final Block block = null;
	public SpongefruitClusterBlock(FantasticalFruitsModElements instance) {
		super(instance, 220);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(FantasticalBlocksItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.SPONGE).sound(SoundType.PLANT).hardnessAndResistance(0.6f, 0.6f).setLightLevel(s -> 0));
			setRegistryName("spongefruit_cluster");
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 15;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(SpongefruitItem.block, (int) (2)));
		}

		@Override
		public boolean removedByPlayer(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, boolean willHarvest, FluidState fluid) {
			boolean retval = super.removedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				SpongefruitClusterDropProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
