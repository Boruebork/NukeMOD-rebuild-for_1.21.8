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

public class AH64 extends LivingEntity implements PlayerRideable {
    public Vec3 movementVector;
    public float speedUp;
    public AH64(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
        this.movementVector = Vec3.ZERO;
    }

    @Override
    public void tick() {
        //this.move(MoverType.PLAYER, new Vec3(0,this.speedUp, 0));
        //this.level().getEntitiesOfClass(NukeEntity.class, this.getBoundingBox().inflate(20), Entity::hasControllingPassenger);


        super.tick();
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.getControllingPassenger() instanceof Player){
            Player player = (Player) this.getControllingPassenger();
            this.move(MoverType.SELF, new Vec3(player.xxa, this.speedUp, player.zza));
        }
        super.travel(new Vec3(0, this.speedUp, 0));
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
        this.speedUp += 1;
        //this.move(MoverType.PLAYER, new Vec3(0, this.speedUp, 0));
        System.out.println(speedUp);
    }
    public void Down(){
        this.speedUp -= 1;
        //this.move(MoverType.PLAYER, new Vec3(0, this.speedUp, 0));
        System.out.println(speedUp);
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
    protected float getRiddenSpeed(Player player) {
        return 5;
    }

    @Override
    protected Vec3 getRiddenInput(Player player, Vec3 travelVector) {
        //return new Vec3(0, 0, 1);
        return Vec3.ZERO;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }
}
