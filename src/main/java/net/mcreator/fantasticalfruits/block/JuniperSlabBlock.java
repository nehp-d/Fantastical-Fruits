
package net.mcreator.fantasticalfruits.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvent;

@FantasticalFruitsModElements.ModElement.Tag
public class JuniperSlabBlock extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:juniper_slab")
	public static final Block block = null;

	public JuniperSlabBlock(FantasticalFruitsModElements instance) {
		super(instance, 226);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(FantasticalBlocksItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends SlabBlock {

		public CustomBlock() {
			super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0));

			setRegistryName("juniper_slab");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, state.get(TYPE) == SlabType.DOUBLE ? 2 : 1));
		}

	}

}
