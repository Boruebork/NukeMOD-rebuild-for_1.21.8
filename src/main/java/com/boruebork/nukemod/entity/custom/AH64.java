package com.boruebork.nukemod.entity.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
    public AH64(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
        this.movementVector = Vec3.ZERO;
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 120)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean canBeRiddenUnderFluidType(FluidType type, Entity rider) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

    }
    public void moveForward(){
        movementVector.add(1, 0, 0);
    }
    public void moveBack(){
        movementVector.add(1, 0, 0);
    }
    public void moveRight(){
        movementVector.add(0, 0, 1);
    }
    public void moveLeft(){
        movementVector.add(0, 0, -1);
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

        Player controllingPassenger = (Player) this.getControllingPassenger();
        if (controllingPassenger != null){
            Vec3 moveVec = new Vec3(controllingPassenger.xxa, controllingPassenger.yya, controllingPassenger.zza);
            travelVector.add(moveVec);
        }

        super.travel(travelVector);
    }

    @Override
    protected float getRiddenSpeed(Player player) {
        return 5;
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }
}
