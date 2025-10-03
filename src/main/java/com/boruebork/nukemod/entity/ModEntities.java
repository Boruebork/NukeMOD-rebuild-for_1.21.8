package com.boruebork.nukemod.entity;

import com.boruebork.nukemod.NukeModbyBoruebork;
import com.boruebork.nukemod.entity.custom.AH64;
import com.boruebork.nukemod.entity.custom.NukeEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(NukeModbyBoruebork.MODID);


    public static final Supplier<EntityType<NukeEntity>> NUKE =
            ENTITY_TYPES.register("nuke", () -> EntityType.Builder.of(NukeEntity::new, MobCategory.MISC)
                    .sized(1.6f, 1.6f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(NukeModbyBoruebork.MODID, "nuke"))));

    public static final Supplier<EntityType<AH64>> AH64 =
            ENTITY_TYPES.register("ah64", () -> EntityType.Builder.of(AH64::new, MobCategory.MISC)
                    .sized(3, 1)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(NukeModbyBoruebork.MODID, "ah64"))));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
