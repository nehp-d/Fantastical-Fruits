
package net.mcreator.fantasticalfruits.item;

@FantasticalFruitsModElements.ModElement.Tag
public class ViciataItem extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:viciata")
	public static final Item block = null;

	public ViciataItem(FantasticalFruitsModElements instance) {
		super(instance, 14);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(FantasticalFruitsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(4).saturation(3f)

							.build()));
			setRegistryName("viciata");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

	}

}
