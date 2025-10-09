package com.boruebork.nukemod.networking;

import com.boruebork.nukemod.entity.ModEntities;
import com.boruebork.nukemod.entity.custom.AH64;
import com.boruebork.nukemod.entity.custom.NukeEntity;
import com.boruebork.nukemod.items.ModItems;
import com.boruebork.nukemod.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public class ClientPayloadHandler {

    public static void handleDataOnMain(final MyData data, final IPayloadContext context) {
        // Do something with the data, on the main thread
        if (data.key().equals("K")) {
            Player player = context.player();
            Inventory inv = player.getInventory();
            if (inv.contains(ModItems.NUKE.toStack())) {
                for (int i = 0; i < 36; i++) {
                    if (inv.getItem(i).is(ModItems.NUKE)) {
                        inv.removeItem(i, 1);
                        NukeEntity nuke = ModEntities.NUKE.get().spawn((ServerLevel) context.player().level(), context.player().getOnPos().above().above().above(), EntitySpawnReason.TRIGGERED);

                        assert nuke != null;
                        nuke.SetRotationNuke(player.xRotO, player.yRotO);
                        break;
                    }
                }
            }
        }if (data.key().equals("Z")){;
            ServerPlayer player = (ServerPlayer) context.player();
            Entity vehicle = player.getVehicle();
            player.sendSystemMessage(Component.literal("Packet recived on the server!"));
            if (vehicle != null){
                player.sendSystemMessage(Component.literal("Vehicle not null!"));
                if (vehicle.getClass() == AH64.class) {
                    player.sendSystemMessage(Component.literal("Vehicle is an AH64!"));
                    AH64 ah64 = (AH64) vehicle;
                    //WORKS
                    ah64.Up();
                    player.sendSystemMessage(Component.literal("Moved AH64 Up!"));
            }else{
                    player.sendSystemMessage(Component.literal("vehicle is null"));
                }
            }
        }if (data.key().equals("C")){
            Player player = context.player();
            Entity vehicle = player.getVehicle();
            if (vehicle != null) {
                if (vehicle.getClass() == AH64.class) {
                    AH64 ah64 = (AH64) vehicle;
                    ah64.Down();

                }
            }
        }
    }
}
