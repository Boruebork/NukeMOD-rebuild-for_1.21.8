package com.boruebork.nukemod.entity.client.ah64;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.boruebork.nukemod.NukeModbyBoruebork;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Quaternionf;

public class AH64Model extends EntityModel<AH64RenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NukeModbyBoruebork.MODID, "ah64"), "main");
	private final ModelPart cabin;
	private final ModelPart blades;
	private final ModelPart tail;
	private final ModelPart LP;
	private final ModelPart wheels;
	private final ModelPart bb_main;

	public AH64Model(ModelPart root) {
        super(root);
        this.cabin = root.getChild("cabin");
		this.blades = root.getChild("blades");
		this.tail = root.getChild("tail");
		this.LP = root.getChild("LP");
		this.wheels = root.getChild("wheels");
		this.bb_main = root.getChild("bb_main");
	}

	@Override
	public void setupAnim(AH64RenderState renderState) {
		this.blades.setRotation(0, renderState.ageInTicks%360- 180, 0);
		super.setupAnim(renderState);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition cabin = partdefinition.addOrReplaceChild("cabin", CubeListBuilder.create().texOffs(40, 21).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 22.5F, -5.0F));

		PartDefinition cube_r1 = cabin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 48).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.25F, -1.25F, -0.3927F, 0.0F, 0.0F));

		PartDefinition blades = partdefinition.addOrReplaceChild("blades", CubeListBuilder.create().texOffs(0, 2).addBox(-13.0F, -2.0F, -1.0F, 32.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 19.0F, 0.5F));

		PartDefinition cube_r2 = blades.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -2.0F, -1.0F, 32.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, 0.0F, -3.25F, 0.0F, -1.5708F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(26, 40).addBox(0.0F, -9.0F, 17.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(18, 40).addBox(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.25F, 15.5F, -0.4363F, 0.0F, 0.0F));

		PartDefinition LP = partdefinition.addOrReplaceChild("LP", CubeListBuilder.create().texOffs(34, 46).addBox(4.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(42, 46).addBox(6.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(8, 48).addBox(-4.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-6.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition wheels = partdefinition.addOrReplaceChild("wheels", CubeListBuilder.create().texOffs(46, 21).addBox(-2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 49).addBox(2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 50).addBox(0.0F, 0.0F, -6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r4 = wheels.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(50, 41).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 50).addBox(-5.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 1.5F, 2.75F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r5 = wheels.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(50, 39).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.5F, -4.25F, 0.7854F, 0.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -2.0F, -8.0F, 3.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(34, 24).addBox(-2.0F, -4.0F, -5.0F, 5.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-6.0F, -1.0F, -1.0F, 6.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(40, 4).addBox(1.0F, -1.0F, -1.0F, 6.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-0.5F, -4.0F, 2.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(22, 49).addBox(0.0F, -7.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 7).addBox(1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(40, 14).addBox(-1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r6 = bb_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(8, 43).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, -5.75F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r7 = bb_main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 0.0F, -5.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r8 = bb_main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 39).addBox(-2.0F, -4.0F, -2.0F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 5.0F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

}