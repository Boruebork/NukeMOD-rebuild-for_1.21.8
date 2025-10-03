package com.boruebork.nukemod.event;

import com.boruebork.nukemod.NukeModbyBoruebork;
import com.boruebork.nukemod.entity.ModEntities;
import com.boruebork.nukemod.entity.custom.AH64;
import com.boruebork.nukemod.entity.custom.NukeEntity;
import com.boruebork.nukemod.networking.ClientPayloadHandler;
import com.boruebork.nukemod.networking.MyData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
@EventBusSubscriber(modid = NukeModbyBoruebork.MODID)
public class ModEvents {
    // In some common event class
    //REGISTRATION OF A PAYLOADHANDLER
    @SubscribeEvent // on the mod event bus
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1")
                .executesOn(HandlerThread.MAIN);
        registrar.playBidirectional(
                MyData.TYPE,
                MyData.STREAM_CODEC,
                ClientPayloadHandler::handleDataOnMain
        );

    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NUKE.get(), NukeEntity.createAttributes().build());
        event.put(ModEntities.AH64.get(), AH64.createAttributes().build());
    }
}
