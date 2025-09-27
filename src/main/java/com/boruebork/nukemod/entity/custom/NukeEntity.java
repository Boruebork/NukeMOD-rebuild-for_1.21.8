package com.boruebork.nukemod.entity.custom;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class NukeEntity extends Entity {
    private final float maxSpeed = 10f;
    private float speed = 0;
    private float speedX;
    private float speedY;
    private float speedZ;
    private float surfaceAngle;
    private float angle = 60;
    private float g = (float) 9.81;
    public NukeEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        float temp = (float) Math.cos(Math.toRadians(angle))*speed;
        speedX = (float) Math.cos(Math.toRadians(surfaceAngle)) * temp;
        speedZ = (float) Math.sin(Math.toRadians(surfaceAngle)) * temp;
        speedY = (float) Math.sin(Math.toRadians(angle))*speed;

    }
    public void SetRotationNuke(float x, float y){
        this.angle = y;
        this.surfaceAngle = x;
    }

    @Override
    public void tick() {
        super.tick();
        this.move(MoverType.SELF, new Vec3(speedX, speedY, speedZ));
        speedY -= g * 0.2f;
        if (this.verticalCollision || this.horizontalCollision){
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 25.0f, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {

    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {

    }
}
