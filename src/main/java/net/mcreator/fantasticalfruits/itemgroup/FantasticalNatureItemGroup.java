
package net.mcreator.fantasticalfruits.itemgroup;

@FantasticalFruitsModElements.ModElement.Tag
public class FantasticalNatureItemGroup extends FantasticalFruitsModElements.ModElement {

	public FantasticalNatureItemGroup(FantasticalFruitsModElements instance) {
		super(instance, 19);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabfantastical_nature") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(AspenleavesItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;

}
