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

import net.mcreator.fantasticalfruits.entity.WanderingfunglinEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class WanderingfunglinRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(WanderingfunglinEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelfunglin(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("fantastical_fruits:textures/funglintexture.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelfunglin extends EntityModel<Entity> {
		private final ModelRenderer bb_main;
		private final ModelRenderer right_leg;
		private final ModelRenderer left_leg;
		private final ModelRenderer torso;
		private final ModelRenderer left_arm;
		private final ModelRenderer right_arm;
		private final ModelRenderer head;
		private final ModelRenderer cap;
		private final ModelRenderer backpack;
		public Modelfunglin() {
			textureWidth = 64;
			textureHeight = 64;
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			right_leg = new ModelRenderer(this);
			right_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(right_leg);
			right_leg.setTextureOffset(0, 26).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			left_leg = new ModelRenderer(this);
			left_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(left_leg);
			left_leg.setTextureOffset(22, 0).addBox(0.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			torso = new ModelRenderer(this);
			torso.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(torso);
			torso.setTextureOffset(0, 18).addBox(-2.0F, -10.0F, -1.0F, 4.0F, 6.0F, 2.0F, 0.0F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(left_arm);
			left_arm.setTextureOffset(20, 20).addBox(2.0F, -10.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(right_arm);
			right_arm.setTextureOffset(12, 20).addBox(-4.0F, -10.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(head);
			head.setTextureOffset(12, 29).addBox(-2.0F, -13.0F, -2.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);
			cap = new ModelRenderer(this);
			cap.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cap);
			cap.setTextureOffset(0, 0).addBox(-4.0F, -15.0F, -3.0F, 8.0F, 2.0F, 6.0F, 0.0F, false);
			cap.setTextureOffset(14, 14).addBox(-3.0F, -17.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, false);
			backpack = new ModelRenderer(this);
			backpack.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(backpack);
			backpack.setTextureOffset(46, 0).addBox(-3.0F, -12.0F, 1.0F, 6.0F, 7.0F, 3.0F, 0.0F, false);
			backpack.setTextureOffset(56, 12).addBox(-3.0F, -11.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			backpack.setTextureOffset(46, 12).addBox(-3.0F, -10.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
			backpack.setTextureOffset(56, 12).addBox(2.0F, -11.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			backpack.setTextureOffset(46, 12).addBox(2.0F, -10.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
			backpack.setTextureOffset(46, 11).addBox(-2.0F, -8.0F, -2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.cap.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.cap.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
