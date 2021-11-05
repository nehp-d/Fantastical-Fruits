
package net.mcreator.fantasticalfruits;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.potion.Potions;
import net.minecraft.potion.PotionUtils;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

import net.mcreator.fantasticalfruits.item.SandsunsItem;
import net.mcreator.fantasticalfruits.item.AbsorptiveBrewItem;

@FantasticalFruitsModElements.ModElement.Tag
public class AbsorptiveBrewRecipeBrewingRecipe extends FantasticalFruitsModElements.ModElement {
	public AbsorptiveBrewRecipeBrewingRecipe(FantasticalFruitsModElements instance) {
		super(instance, 290);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}
	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION)
					&& PotionUtils.getPotionFromItem(input) == Potions.AWKWARD;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == SandsunsItem.block;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(AbsorptiveBrewItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
