
package net.mcreator.fantasticalfruits.potion;

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
		public boolean isReady(int duration, int amplifier) {
			return true;
		}

	}

}