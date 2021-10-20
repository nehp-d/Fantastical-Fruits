package net.mcreator.fantasticalfruits.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.fantasticalfruits.entity.StoneGolemEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class StoneGolemRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(StoneGolemEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelstonegolem(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("fantastical_fruits:textures/stone_golem.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.0.1
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelstonegolem extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer head;
		private final ModelRenderer torso;
		private final ModelRenderer torso_r1;
		private final ModelRenderer right_arm;
		private final ModelRenderer left_arm;
		private final ModelRenderer left_leg;
		private final ModelRenderer right_leg;
		public Modelstonegolem() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, -1.0F);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -13.0F, 0.0F);
			body.addChild(head);
			head.setTextureOffset(0, 10).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
			torso = new ModelRenderer(this);
			torso.setRotationPoint(0.0F, -8.7995F, 1.3783F);
			body.addChild(torso);
			torso.setTextureOffset(20, 18).addBox(-5.0F, -0.3005F, -1.7033F, 10.0F, 4.0F, 4.0F, 0.0F, false);
			torso_r1 = new ModelRenderer(this);
			torso_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
			torso.addChild(torso_r1);
			setRotationAngle(torso_r1, 0.1745F, 0.0F, 0.0F);
			torso_r1.setTextureOffset(0, 0).addBox(-6.0F, -3.7252F, -2.9971F, 12.0F, 4.0F, 6.0F, 0.0F, false);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(-5.5F, -10.5F, 1.5F);
			body.addChild(right_arm);
			right_arm.setTextureOffset(14, 26).addBox(-4.0F, -0.5F, -1.5F, 4.0F, 9.0F, 3.0F, 0.0F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(5.5F, -10.5F, 1.5F);
			body.addChild(left_arm);
			left_arm.setTextureOffset(0, 22).addBox(0.0F, -0.5F, -1.5F, 4.0F, 9.0F, 3.0F, 0.0F, false);
			left_leg = new ModelRenderer(this);
			left_leg.setRotationPoint(3.0F, -5.35F, 1.675F);
			body.addChild(left_leg);
			left_leg.setTextureOffset(24, 10).addBox(-2.0F, 2.25F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
			left_leg.setTextureOffset(30, 0).addBox(-1.0F, -1.75F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			right_leg = new ModelRenderer(this);
			right_leg.setRotationPoint(-3.0F, -5.85F, 1.675F);
			body.addChild(right_leg);
			right_leg.setTextureOffset(28, 26).addBox(-2.0F, 2.75F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
			right_leg.setTextureOffset(28, 33).addBox(-1.0F, 0.75F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
