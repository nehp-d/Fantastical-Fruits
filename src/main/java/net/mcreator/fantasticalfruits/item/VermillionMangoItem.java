
package net.mcreator.fantasticalfruits.item;

@FantasticalFruitsModElements.ModElement.Tag
public class VermillionMangoItem extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:vermillion_mango")
	public static final Item block = null;

	public VermillionMangoItem(FantasticalFruitsModElements instance) {
		super(instance, 43);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(FantasticalFruitsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(6).saturation(3.4f)

							.build()));
			setRegistryName("vermillion_mango");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

	}

}
