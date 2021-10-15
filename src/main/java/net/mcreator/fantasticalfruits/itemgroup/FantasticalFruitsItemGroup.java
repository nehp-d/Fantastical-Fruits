
package net.mcreator.fantasticalfruits.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.fantasticalfruits.item.BlueberriesItem;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

@FantasticalFruitsModElements.ModElement.Tag
public class FantasticalFruitsItemGroup extends FantasticalFruitsModElements.ModElement {
	public FantasticalFruitsItemGroup(FantasticalFruitsModElements instance) {
		super(instance, 73);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabfantastical_fruits") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(BlueberriesItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
