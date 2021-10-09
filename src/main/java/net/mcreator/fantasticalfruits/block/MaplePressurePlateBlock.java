
package net.mcreator.fantasticalfruits.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvent;

@FantasticalFruitsModElements.ModElement.Tag
public class MaplePressurePlateBlock extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:maple_pressure_plate")
	public static final Block block = null;

	public MaplePressurePlateBlock(FantasticalFruitsModElements instance) {
		super(instance, 139);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(FantasticalBlocksItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}

	public static class CustomBlock extends PressurePlateBlock {

		public CustomBlock() {
			super(Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 3f)
					.setLightLevel(s -> 0).harvestLevel(0).harvestTool(ToolType.AXE).setRequiresTool().notSolid().setOpaque((bs, br, bp) -> false));

			setRegistryName("maple_pressure_plate");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

	}

}
