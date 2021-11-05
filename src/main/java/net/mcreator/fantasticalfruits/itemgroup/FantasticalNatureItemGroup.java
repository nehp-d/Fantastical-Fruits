
package net.mcreator.fantasticalfruits.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.fantasticalfruits.block.AspenLeavesBlock;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

@FantasticalFruitsModElements.ModElement.Tag
public class FantasticalNatureItemGroup extends FantasticalFruitsModElements.ModElement {
	public FantasticalNatureItemGroup(FantasticalFruitsModElements instance) {
		super(instance, 117);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabfantastical_nature") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(AspenLeavesBlock.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
