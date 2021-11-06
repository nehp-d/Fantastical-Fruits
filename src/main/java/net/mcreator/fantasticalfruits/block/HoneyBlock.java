
package net.mcreator.fantasticalfruits.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.state.StateContainer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.IParticleData;
import net.minecraft.item.Rarity;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.BucketItem;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.fantasticalfruits.procedures.HoneySolidifyProcedure;
import net.mcreator.fantasticalfruits.itemgroup.FantasticalItemsItemGroup;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

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
		super(instance, 86);
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
				.luminosity(0).density(1420).viscosity(2500).temperature(300).rarity(Rarity.COMMON)
				.sound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.honey_block.place")))).explosionResistance(10f).tickRate(25)
						.levelDecreasePerBlock(2).slopeFindDistance(3).bucket(() -> bucket).block(() -> block);
		still = (FlowingFluid) new CustomFlowingFluid.Source(fluidproperties).setRegistryName("honey");
		flowing = (FlowingFluid) new CustomFlowingFluid.Flowing(fluidproperties).setRegistryName("honey_flowing");
		elements.blocks.add(() -> new FlowingFluidBlock(still,
				Block.Properties.create(Material.WATER, MaterialColor.GOLD).hardnessAndResistance(10f).setLightLevel(s -> 0)) {
			@Override
			public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
				return true;
			}

			@Override
			public void onBlockAdded(BlockState blockstate, World world, BlockPos pos, BlockState oldState, boolean moving) {
				super.onBlockAdded(blockstate, world, pos, oldState, moving);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, 10);
			}

			@Override
			public void tick(BlockState blockstate, ServerWorld world, BlockPos pos, Random random) {
				super.tick(blockstate, world, pos, random);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					HoneySolidifyProcedure.executeProcedure($_dependencies);
				}
				world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, 10);
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
