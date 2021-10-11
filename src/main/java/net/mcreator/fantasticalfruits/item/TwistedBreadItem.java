
package net.mcreator.fantasticalfruits.item;

@FantasticalFruitsModElements.ModElement.Tag
public class TwistedBreadItem extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:twisted_bread")
	public static final Item block = null;

	public TwistedBreadItem(FantasticalFruitsModElements instance) {
		super(instance, 173);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(FantasticalFruitsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(5).saturation(6f)

							.build()));
			setRegistryName("twisted_bread");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

	}

}
