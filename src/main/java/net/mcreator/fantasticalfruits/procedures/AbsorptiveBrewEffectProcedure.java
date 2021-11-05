package net.mcreator.fantasticalfruits.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.fantasticalfruits.FantasticalFruitsMod;

import java.util.Map;

public class AbsorptiveBrewEffectProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency entity for procedure AbsorptiveBrewEffect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.ABSORPTION, (int) 3600, (int) 1, (false), (true)));
	}
}
