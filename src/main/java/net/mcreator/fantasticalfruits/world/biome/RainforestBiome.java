
package net.mcreator.fantasticalfruits.world.biome;

import net.minecraft.block.material.Material;
import java.util.ArrayList;
import java.util.HashMap;

@FantasticalFruitsModElements.ModElement.Tag
public class RainforestBiome extends FantasticalFruitsModElements.ModElement {

	public static Biome biome;

	public RainforestBiome(FantasticalFruitsModElements instance) {
		super(instance, 293);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BiomeRegisterHandler());
	}

	private static class BiomeRegisterHandler {

		@SubscribeEvent
		public void registerBiomes(RegistryEvent.Register<Biome> event) {
			if (biome == null) {
				BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(12638463).setWaterColor(-12325696).setWaterFogColor(-11996475)
						.withSkyColor(7972607).withFoliageColor(-5313715).withGrassColor(-5510328).build();

				BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder()
						.withSurfaceBuilder(SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(),
								Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState())));

				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.RANDOM_PATCH.withConfiguration(Features.Configs.GRASS_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT)
								.withPlacement(Placement.COUNT_NOISE.configure(new NoiseDependant(-0.8D, 5, 9))));

				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.FLOWER.withConfiguration(Features.Configs.NORMAL_FLOWER_CONFIG)
								.withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
								.func_242731_b(5));

				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.RANDOM_PATCH.withConfiguration(
								(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.BROWN_MUSHROOM.getDefaultState()),
										SimpleBlockPlacer.PLACER)).tries(4).func_227317_b_().build()));
				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.RANDOM_PATCH.withConfiguration(
								(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.RED_MUSHROOM.getDefaultState()),
										SimpleBlockPlacer.PLACER)).tries(4).func_227317_b_().build()));

				DefaultBiomeFeatures.withCavesAndCanyons(biomeGenerationSettings);
				DefaultBiomeFeatures.withOverworldOres(biomeGenerationSettings);
				DefaultBiomeFeatures.withFrozenTopLayer(biomeGenerationSettings);

				MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();

				biome = new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.JUNGLE).depth(0.1f).scale(0.2f)
						.temperature(0.7999999999999999f).downfall(1f).setEffects(effects).withMobSpawnSettings(mobSpawnInfo.copy())
						.withGenerationSettings(biomeGenerationSettings.build()).build();

				event.getRegistry().register(biome.setRegistryName("fantastical_fruits:rainforest"));
			}
		}

	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM,
				new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, WorldGenRegistries.BIOME.getKey(biome)), 7));
	}

}
