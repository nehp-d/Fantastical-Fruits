
package net.mcreator.fantasticalfruits.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.minecraft.entity.LivingEntity;

import net.mcreator.fantasticalfruits.procedures.HoneyEffectTickProcedure;
import net.mcreator.fantasticalfruits.procedures.HoneyEffectConditionProcedure;

import java.util.Map;
import java.util.HashMap;

import com.google.common.collect.ImmutableMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HoneyEffectPotionEffect {
	@ObjectHolder("fantastical_fruits:honey_effect")
	public static final Effect potion = null;
	@SubscribeEvent
	public static void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}
	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.BENEFICIAL, -344279);
			setRegistryName("honey_effect");
			potionIcon = new ResourceLocation("fantastical_fruits:textures/honey.png");
		}

		@Override
		public String getName() {
			return "effect.honey_effect";
		}

		@Override
		public boolean isBeneficial() {
			return true;
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return true;
		}

		@Override
		public void performEffect(LivingEntity entity, int amplifier) {
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				HoneyEffectTickProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return HoneyEffectConditionProcedure.executeProcedure(ImmutableMap.of("amplifier", amplifier, "duration", duration));
		}
	}
}
