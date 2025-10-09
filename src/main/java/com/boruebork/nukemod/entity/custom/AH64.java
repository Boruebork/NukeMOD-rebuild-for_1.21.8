package com.boruebork.nukemod.entity.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

public class AH64 extends LivingEntity implements PlayerRideable {
    public Vec3 movementVector;
    public float speedUp;
    public AH64(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
        this.movementVector = Vec3.ZERO;
    }

    @Override
    public void tick() {
        //this.level().getEntitiesOfClass(NukeEntity.class, this.getBoundingBox().inflate(20), Entity::hasControllingPassenger);


        super.tick();
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 120)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.GRAVITY, 0);
    }
    public void Up(){
        this.speedUp += 0.1f;
    }
    public void Down(){
        this.speedUp -= 0.1f;
    }

    @Override
    public boolean canBeRiddenUnderFluidType(FluidType type, Entity rider) {
        return false;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        getRided(player);
        return InteractionResult.SUCCESS;
    }
    private void getRided(Player player) {
        player.startRiding(this);
    }

    @Override
    protected boolean canRide(Entity vehicle) {
        return true;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }


    @Override
    public void travel(Vec3 travelVector) {
        LivingEntity controllingPassenger = this.getControllingPassenger();
        if (controllingPassenger != null) {
            // Copy passenger rotation
            this.setYRot(controllingPassenger.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(controllingPassenger.getXRot() * 0.5f);

            // Simple movement calculation
            Vec3 moveVec = new Vec3(
                    controllingPassenger.xxa * 0.05f,
                    speedUp,
                    controllingPassenger.zza * 0.05f
            );//.yRot(-this.getYRot() * ((float)Math.PI / 180F));
            this.setDeltaMovement(moveVec);
            super.travel(Vec3.ZERO);
        } else {
            super.travel(Vec3.ZERO);
        }
    }


    @Override
    protected float getRiddenSpeed(Player player) {
        return 5;
    }

    @Override
    protected Vec3 getRiddenInput(Player player, Vec3 travelVector) {
        return new Vec3(0, 0, 1);
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }
}
