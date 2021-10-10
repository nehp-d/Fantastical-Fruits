package net.mcreator.fantasticalfruits.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.fantasticalfruits.item.MyceliteItem;
import net.mcreator.fantasticalfruits.FantasticalFruitsMod;

import java.util.Map;

public class BarteringProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency sourceentity for procedure Bartering!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (sourceentity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(MyceliteItem.block);
			((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
					((PlayerEntity) sourceentity).container.func_234641_j_());
		}
	}
}
