
package net.mcreator.fantasticalfruits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.fantasticalfruits.itemgroup.FantasticalFruitsItemGroup;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

@FantasticalFruitsModElements.ModElement.Tag
public class BananaItem extends FantasticalFruitsModElements.ModElement {
	@ObjectHolder("fantastical_fruits:banana")
	public static final Item block = null;
	public BananaItem(FantasticalFruitsModElements instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(FantasticalFruitsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(5).saturation(3f).build()));
			setRegistryName("banana");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}
	}
}
