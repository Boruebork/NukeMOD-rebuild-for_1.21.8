package com.boruebork.nukemod.entity.client.nuke;

import com.boruebork.nukemod.NukeModbyBoruebork;
import com.boruebork.nukemod.entity.custom.NukeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;


public class NukeRenderer extends LivingEntityRenderer<NukeEntity, NukeRenderState, NukeModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NukeModbyBoruebork.MODID, "textures/entity/nuke/nuke.png");

    public NukeRenderer(EntityRendererProvider.Context context) {
        super(context, new NukeModel(context.bakeLayer(NukeModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(NukeRenderState entity) {
        return TEXTURE;
    }

    @Override
    public void render(NukeRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight){
        super.render(renderState, poseStack, bufferSource, packedLight);
        poseStack.pushPose();
        Vec3 root = new Vec3(0, 1.3f, 0);
        poseStack.scale(4, 4, 4);
        poseStack.rotateAround(Axis.YP.rotationDegrees(renderState.yRot), (float) -root.x, (float) -root.y, (float) -root.z);
        poseStack.rotateAround(Axis.XP.rotationDegrees(renderState.xRot), (float) -root.x, (float) -root.y, (float) -root.z);
        poseStack.popPose();
    }

    @Override
    public NukeRenderState createRenderState() {
        return new NukeRenderState();
    }

    @Override
    public void extractRenderState(NukeEntity entity, NukeRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
    }

}