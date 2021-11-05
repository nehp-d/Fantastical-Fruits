
package net.mcreator.fantasticalfruits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.fantasticalfruits.itemgroup.FantasticalItemsItemGroup;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

@FantasticalFruitsModElements.ModElement.Tag
public class RoughMyceliteItem extends FantasticalFruitsModElements.ModElement {
	@ObjectHolder("fantastical_fruits:rough_mycelite")
	public static final Item block = null;
	public RoughMyceliteItem(FantasticalFruitsModElements instance) {
		super(instance, 80);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(FantasticalItemsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("rough_mycelite");
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
