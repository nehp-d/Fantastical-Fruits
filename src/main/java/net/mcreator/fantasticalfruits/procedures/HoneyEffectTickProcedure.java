package net.mcreator.fantasticalfruits.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.fantasticalfruits.FantasticalFruitsMod;

import java.util.Map;

public class HoneyEffectTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency entity for procedure HoneyEffectTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).setHealth((float) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) + 1));
	}
}
