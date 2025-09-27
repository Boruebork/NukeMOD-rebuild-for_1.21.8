package com.boruebork.nukemod.networking;

import com.boruebork.nukemod.entity.ModEntities;
import com.boruebork.nukemod.entity.custom.NukeEntity;
import com.boruebork.nukemod.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public class ClientPayloadHandler {

    public static void handleDataOnMain(final MyData data, final IPayloadContext context) {
        // Do something with the data, on the main thread
        Player player = context.player();
        Inventory inv = player.getInventory();
        if (inv.contains(ModItems.NUKE.toStack())){
            for (int i = 0; i < 36; i++){
                if (inv.getItem(i).is(ModItems.NUKE)){
                    inv.removeItem(i, 1);
                    NukeEntity nuke = ModEntities.NUKE.get().spawn((ServerLevel) context.player().level(), context.player().getOnPos(), EntitySpawnReason.TRIGGERED);
                    assert nuke != null;
                    nuke.SetRotationNuke(player.xRotO, player.yHeadRot);
                    break;
                }
            }
        }
        //EntityType.COW.spawn((ServerLevel) context.player().level(), context.player().getOnPos(), EntitySpawnReason.TRIGGERED);

    }
}
