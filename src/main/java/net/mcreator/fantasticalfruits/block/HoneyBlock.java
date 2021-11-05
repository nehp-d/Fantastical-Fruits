
package net.mcreator.fantasticalfruits.block;

import net.minecraft.block.material.Material;

@FantasticalFruitsModElements.ModElement.Tag
public class HoneyBlock extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:honey")
	public static final FlowingFluidBlock block = null;

	@ObjectHolder("fantastical_fruits:honey_bucket")
	public static final Item bucket = null;

	public static FlowingFluid flowing = null;
	public static FlowingFluid still = null;

	private ForgeFlowingFluid.Properties fluidproperties = null;

	public HoneyBlock(FantasticalFruitsModElements instance) {
		super(instance, 293);

		FMLJavaModLoadingContext.get().getModEventBus().register(new FluidRegisterHandler());

	}

	private static class FluidRegisterHandler {

		@SubscribeEvent
		public void registerFluids(RegistryEvent.Register<Fluid> event) {
			event.getRegistry().register(still);
			event.getRegistry().register(flowing);
		}

	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(still, RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(flowing, RenderType.getTranslucent());
	}

	@Override
	public void initElements() {
		fluidproperties = new ForgeFlowingFluid.Properties(() -> still, () -> flowing, FluidAttributes
				.builder(new ResourceLocation("fantastical_fruits:blocks/honey_still"), new ResourceLocation("fantastical_fruits:blocks/honey_flow"))
				.luminosity(0).density(1420).viscosity(1420).temperature(300)

				.rarity(Rarity.COMMON).sound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.honey_block.place"))))
						.explosionResistance(10f)

						.tickRate(25).levelDecreasePerBlock(2).slopeFindDistance(3).bucket(() -> bucket).block(() -> block);

		still = (FlowingFluid) new CustomFlowingFluid.Source(fluidproperties).setRegistryName("honey");
		flowing = (FlowingFluid) new CustomFlowingFluid.Flowing(fluidproperties).setRegistryName("honey_flowing");

		elements.blocks.add(() -> new FlowingFluidBlock(still,
				Block.Properties.create(Material.WATER, MaterialColor.GOLD).hardnessAndResistance(10f).setLightLevel(s -> 0)) {

			@Override
			public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
				return true;
			}

		}.setRegistryName("honey"));

		elements.items.add(() -> new BucketItem(still,
				new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(FantasticalItemsItemGroup.tab).rarity(Rarity.COMMON))
						.setRegistryName("honey_bucket"));
	}

	public static abstract class CustomFlowingFluid extends ForgeFlowingFluid {
		public CustomFlowingFluid(Properties properties) {
			super(properties);
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public IParticleData getDripParticleData() {
			return ParticleTypes.DRIPPING_HONEY;
		}

		@Override
		public Vector3d getFlow(IBlockReader world, BlockPos pos, FluidState fluidstate) {
			return super.getFlow(world, pos, fluidstate).scale(0.1);
		}

		public static class Source extends CustomFlowingFluid {
			public Source(Properties properties) {
				super(properties);
			}

			public int getLevel(FluidState state) {
				return 8;
			}

			public boolean isSource(FluidState state) {
				return true;
			}
		}

		public static class Flowing extends CustomFlowingFluid {
			public Flowing(Properties properties) {
				super(properties);
			}

			protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
				super.fillStateContainer(builder);
				builder.add(LEVEL_1_8);
			}

			public int getLevel(FluidState state) {
				return state.get(LEVEL_1_8);
			}

			public boolean isSource(FluidState state) {
				return false;
			}
		}
	}

}
