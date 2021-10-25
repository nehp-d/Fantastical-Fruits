
package net.mcreator.fantasticalfruits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.fantasticalfruits.procedures.CrimberryFoodEatenProcedure;
import net.mcreator.fantasticalfruits.itemgroup.FantasticalFruitsItemGroup;
import net.mcreator.fantasticalfruits.FantasticalFruitsModElements;

import java.util.Map;
import java.util.HashMap;

@FantasticalFruitsModElements.ModElement.Tag
public class CrimberryItem extends FantasticalFruitsModElements.ModElement {
	@ObjectHolder("fantastical_fruits:crimberry")
	public static final Item block = null;
	public CrimberryItem(FantasticalFruitsModElements instance) {
		super(instance, 13);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(FantasticalFruitsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(2).saturation(0.8f).build()));
			setRegistryName("crimberry");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemstack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemstack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				CrimberryFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
