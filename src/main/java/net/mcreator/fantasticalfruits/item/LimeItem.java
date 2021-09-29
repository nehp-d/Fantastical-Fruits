
package net.mcreator.fantasticalfruits.item;

@FantasticalFruitsModElements.ModElement.Tag
public class LimeItem extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:lime")
	public static final Item block = null;

	public LimeItem(FantasticalFruitsModElements instance) {
		super(instance, 13);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(FantasticalFruitsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(4).saturation(0.3f)

							.build()));
			setRegistryName("lime");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

	}

}
