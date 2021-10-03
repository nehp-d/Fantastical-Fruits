// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelcustom_model extends EntityModel<Entity> {
	private final ModelRenderer root;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer body;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer head;
	private final ModelRenderer mushroom;
	private final ModelRenderer backpack;

	public Modelcustom_model() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(0.0F, -4.0F, 0.0F);
		root.addChild(rightleg);
		rightleg.setTextureOffset(0, 26).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(0.0F, -4.0F, 0.0F);
		root.addChild(leftleg);
		leftleg.setTextureOffset(22, 0).addBox(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -4.0F, 0.0F);
		root.addChild(body);
		body.setTextureOffset(0, 18).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 2.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(2.0F, -10.0F, 0.0F);
		root.addChild(leftarm);
		leftarm.setTextureOffset(20, 20).addBox(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-2.0F, -10.0F, 0.0F);
		root.addChild(rightarm);
		rightarm.setTextureOffset(12, 20).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -10.0F, 0.0F);
		root.addChild(head);
		head.setTextureOffset(12, 29).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);

		mushroom = new ModelRenderer(this);
		mushroom.setRotationPoint(0.0F, -13.0F, 0.0F);
		root.addChild(mushroom);
		mushroom.setTextureOffset(0, 0).addBox(-4.0F, -2.0F, -3.0F, 8.0F, 2.0F, 6.0F, 0.0F, false);
		mushroom.setTextureOffset(14, 14).addBox(-3.0F, -4.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, false);

		backpack = new ModelRenderer(this);
		backpack.setRotationPoint(0.0F, -10.0F, 1.0F);
		root.addChild(backpack);
		backpack.setTextureOffset(46, 0).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 7.0F, 3.0F, 0.0F, false);
		backpack.setTextureOffset(56, 12).addBox(-3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		backpack.setTextureOffset(46, 12).addBox(-3.0F, 0.0F, -3.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		backpack.setTextureOffset(56, 12).addBox(2.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		backpack.setTextureOffset(46, 12).addBox(2.0F, 0.0F, -3.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		backpack.setTextureOffset(46, 11).addBox(-2.0F, 2.0F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		root.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.rightleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.leftleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.mushroom.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.mushroom.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}