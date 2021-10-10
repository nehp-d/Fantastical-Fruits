
package net.mcreator.fantasticalfruits.item;

@FantasticalFruitsModElements.ModElement.Tag
public class IconItem extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:icon")
	public static final Item block = null;

	public IconItem(FantasticalFruitsModElements instance) {
		super(instance, 151);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(null).maxStackSize(1).isImmuneToFire().rarity(Rarity.EPIC));
			setRegistryName("icon");
		}

		@Override
		public boolean hasContainerItem() {
			return true;
		}

		@Override
		public ItemStack getContainerItem(ItemStack itemstack) {
			return new ItemStack(this);
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
