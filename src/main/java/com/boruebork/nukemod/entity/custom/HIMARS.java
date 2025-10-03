package com.boruebork.nukemod.entity.custom;

import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

public class HIMARS extends LivingEntity implements PlayerRideable {
    protected HIMARS(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Override
    protected boolean canRide(Entity vehicle) {
        return true;
    }


    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }
}
