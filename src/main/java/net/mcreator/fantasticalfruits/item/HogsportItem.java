
package net.mcreator.fantasticalfruits.item;

@FantasticalFruitsModElements.ModElement.Tag
public class HogsportItem extends FantasticalFruitsModElements.ModElement {

	@ObjectHolder("fantastical_fruits:hogsport")
	public static final Item block = null;

	public HogsportItem(FantasticalFruitsModElements instance) {
		super(instance, 254);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(6).saturation(5f)

							.meat().build()));
			setRegistryName("hogsport");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

	}

}
