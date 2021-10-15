
package net.mcreator.fantasticalfruits.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.block.Blocks;

import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

@FantasticalFruitsModElements.ModElement.Tag
public class FantasticalBlocksItemGroup extends FantasticalFruitsModElements.ModElement {
	public FantasticalBlocksItemGroup(FantasticalFruitsModElements instance) {
		super(instance, 75);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabfantastical_blocks") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Blocks.BARREL);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
