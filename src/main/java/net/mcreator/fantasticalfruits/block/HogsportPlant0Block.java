
package net.mcreator.fantasticalfruits.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvent;

@FantasticalFruitsModElements.ModElement.Tag
public class HogsportPlant0Block extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:hogsport_plant_0")
	public static final Block block = null;

	public HogsportPlant0Block(FantasticalFruitsModElements instance) {
		super(instance, 255);

		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(new FeatureRegisterHandler());
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

	private static Feature<BlockClusterFeatureConfig> feature = null;
	private static ConfiguredFeature<?, ?> configuredFeature = null;

	private static class FeatureRegisterHandler {

		@SubscribeEvent
		public void registerFeature(RegistryEvent.Register<Feature<?>> event) {
			feature = new RandomPatchFeature(BlockClusterFeatureConfig.field_236587_a_) {
				@Override
				public boolean generate(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
					RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
					boolean dimensionCriteria = false;

					if (dimensionType == World.THE_NETHER)
						dimensionCriteria = true;

					if (!dimensionCriteria)
						return false;

					return super.generate(world, generator, random, pos, config);
				}
			};

			configuredFeature = feature.withConfiguration(
					(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(block.getDefaultState()), new DoublePlantBlockPlacer()))
							.tries(64).func_227317_b_().build())
					.withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(5);

			event.getRegistry().register(feature.setRegistryName("hogsport_plant_0"));
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation("fantastical_fruits:hogsport_plant_0"), configuredFeature);
		}

	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {

		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> configuredFeature);
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
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			if (state.get(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.LOWER)
				return Collections.emptyList();

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@Override
		public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {

			Block ground = state.getBlock();
			return (ground == Blocks.CRIMSON_NYLIUM

			)

			;
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
