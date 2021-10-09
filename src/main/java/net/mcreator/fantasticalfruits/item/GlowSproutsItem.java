
package net.mcreator.fantasticalfruits.item;

@FantasticalFruitsModElements.ModElement.Tag
public class GlowSproutsItem extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:glow_sprouts")
	public static final Item block = null;

	public GlowSproutsItem(FantasticalFruitsModElements instance) {
		super(instance, 129);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(FantasticalItemsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("glow_sprouts");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

	}

}
