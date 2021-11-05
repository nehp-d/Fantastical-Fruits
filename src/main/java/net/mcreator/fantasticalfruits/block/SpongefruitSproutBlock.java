
package net.mcreator.fantasticalfruits.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.potion.Effects;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.fantasticalfruits.procedures.SpongefruitSproutGrowProcedure;
import net.mcreator.fantasticalfruits.item.SpongefruitItem;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

@FantasticalFruitsModElements.ModElement.Tag
public class SpongefruitSproutBlock extends FantasticalFruitsModElements.ModElement {
	@ObjectHolder("fantastical_fruits:spongefruit_sprout")
	public static final Block block = null;
	public SpongefruitSproutBlock(FantasticalFruitsModElements instance) {
		super(instance, 254);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(null)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class BlockCustomFlower extends FlowerBlock {
		public BlockCustomFlower() {
			super(Effects.SPEED, 5, Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.STEM)
					.hardnessAndResistance(0f, 0f).setLightLevel(s -> 0));
			setRegistryName("spongefruit_sprout");
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 100;
		}

		@Override
		public Block.OffsetType getOffsetType() {
			return Block.OffsetType.NONE;
		}

		@Override
		public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 60;
		}

		@Override
		public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
			return new ItemStack(SpongefruitItem.block);
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(SpongefruitItem.block));
		}

		@Override
		public PlantType getPlantType(IBlockReader world, BlockPos pos) {
			return PlantType.WATER;
		}

		@Override
		public void tick(BlockState blockstate, ServerWorld world, BlockPos pos, Random random) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				SpongefruitSproutGrowProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
