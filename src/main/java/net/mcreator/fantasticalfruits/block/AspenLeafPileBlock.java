
package net.mcreator.fantasticalfruits.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvent;

@FantasticalFruitsModElements.ModElement.Tag
public class AspenLeafPileBlock extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:aspen_leaf_pile")
	public static final Block block = null;

	public AspenLeafPileBlock(FantasticalFruitsModElements instance) {
		super(instance, 154);

		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(new FeatureRegisterHandler());
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(FantasticalNatureItemGroup.tab)).setRegistryName(block.getRegistryName()));
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

					if (dimensionType == World.OVERWORLD)
						dimensionCriteria = true;

					if (!dimensionCriteria)
						return false;

					return super.generate(world, generator, random, pos, config);
				}
			};

			configuredFeature = feature.withConfiguration(
					(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(block.getDefaultState()), new SimpleBlockPlacer())).tries(5)
							.build())
					.withPlacement(Placement.COUNT_NOISE.configure(new NoiseDependant(-0.8, 0, 9)));

			event.getRegistry().register(feature.setRegistryName("aspen_leaf_pile"));
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation("fantastical_fruits:aspen_leaf_pile"), configuredFeature);
		}

	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("fantastical_fruits:aspen_forest").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;

		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> configuredFeature);
	}

	public static class BlockCustomFlower extends FlowerBlock {

		public BlockCustomFlower() {
			super(Effects.SPEED, 5, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT)
					.hardnessAndResistance(0f, 0f).setLightLevel(s -> 0));
			setRegistryName("aspen_leaf_pile");
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			Vector3d offset = state.getOffset(world, pos);
			return VoxelShapes.or(makeCuboidShape(0, 0, 0, 16, 0.1, 16)

			)

					.withOffset(offset.x, offset.y, offset.z);
		}

		@Override
		public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
			return useContext.getItem().getItem() != this.asItem();
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
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(Blocks.AIR, (int) (0)));
		}

		@Override
		public PlantType getPlantType(IBlockReader world, BlockPos pos) {
			return PlantType.PLAINS;
		}

	}

}
