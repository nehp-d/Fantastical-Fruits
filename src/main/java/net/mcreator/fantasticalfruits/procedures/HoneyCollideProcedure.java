package net.mcreator.fantasticalfruits.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.fantasticalfruits.potion.HoneyEffectPotionEffect;
import net.mcreator.fantasticalfruits.FantasticalFruitsMod;

import java.util.Map;

public class HoneyCollideProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency entity for procedure HoneyCollide!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(HoneyEffectPotionEffect.potion, (int) 600, (int) 1));
	}
}
