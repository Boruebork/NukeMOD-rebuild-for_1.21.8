package com.boruebork.nukemod.entity.client.ah64;

import com.boruebork.nukemod.NukeModbyBoruebork;
import com.boruebork.nukemod.entity.client.nuke.NukeModel;
import com.boruebork.nukemod.entity.custom.AH64;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AH64Renderer extends LivingEntityRenderer<AH64, AH64RenderState, AH64Model> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NukeModbyBoruebork.MODID, "textures/entity/ah64/ah64.png");
    public AH64Renderer(EntityRendererProvider.Context context) {
        super(context, new AH64Model(context.bakeLayer(AH64Model.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(AH64RenderState ah64RenderState) {
        return TEXTURE;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public AH64RenderState createRenderState() {
        return new AH64RenderState();
    }

    @Override
    public void render(AH64RenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(7, 7, 7);


        super.render(renderState, poseStack, bufferSource, packedLight);
    }
}
