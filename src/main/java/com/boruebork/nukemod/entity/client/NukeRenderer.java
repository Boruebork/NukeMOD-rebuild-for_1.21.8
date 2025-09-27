package com.boruebork.nukemod.entity.client;

import com.boruebork.nukemod.entity.custom.NukeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class NukeRenderer extends EntityRenderer<NukeEntity, NukeRenderState>{
    public NukeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRender(NukeEntity livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public void extractRenderState(NukeEntity entity, NukeRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
    }

    @Override
    public NukeRenderState createRenderState() {
        return new NukeRenderState();
    }

    @Override
    public void render(NukeRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(renderState, poseStack, bufferSource, packedLight);
    }
}
