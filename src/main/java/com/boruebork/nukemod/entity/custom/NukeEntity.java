package com.boruebork.nukemod.entity.custom;

import com.boruebork.nukemod.sound.ModSounds;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class NukeEntity extends LivingEntity {
    private final float maxSpeed = 10;
    private float speed = maxSpeed;
    private float speedX;
    private float speedY;
    private float speedZ;
    private float surfaceAngle;
    private float angle;
    private float g = 0.5f;
    private boolean valuesSet = false;
    public NukeEntity(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);

    }
    public void SetRotationNuke(float x, float y){
        this.angle = x;
        this.surfaceAngle = y;
        Vec3 vec3 = Vec3.directionFromRotation(x, y);
        this.setRot(surfaceAngle, angle);
        float temp = (float) Math.cos(Math.toRadians(angle))*speed;
        speedX = (float) vec3.x * speed;
        speedZ = (float) vec3.z * speed;
        speedY = (float) vec3.y * speed;
        valuesSet = true;
        ServerLevel serverLevel = (ServerLevel) this.level();
        serverLevel.playSound(this, this.getOnPos(), ModSounds.NUKE_LAUNCH.get(), SoundSource.HOSTILE, 100, 0);
    }



    @Override
    public void tick() {
        super.tick();
        if (valuesSet){
            this.setRot(180, - (float) Math.toDegrees(Math.atan(speedY/Math.sqrt(speedX*speedX + speedZ*speedZ))));
            this.move(MoverType.SELF, new Vec3(speedX*0.2f, speedY*0.2f, speedZ*0.2f));
            speedY -= g * 0.2f;
            if (this.verticalCollision || this.horizontalCollision){
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), 25.0f, Level.ExplosionInteraction.TNT);
                this.discard();
            }
        }

    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
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

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }
}
