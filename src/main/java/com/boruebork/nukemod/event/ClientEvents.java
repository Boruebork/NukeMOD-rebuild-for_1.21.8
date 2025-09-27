package com.boruebork.nukemod.event;

import com.boruebork.nukemod.NukeModbyBoruebork;
import com.boruebork.nukemod.entity.ModEntities;
import com.boruebork.nukemod.entity.client.NukeRenderer;
import com.boruebork.nukemod.items.ModItems;
import com.boruebork.nukemod.networking.ClientPayloadHandler;
import com.boruebork.nukemod.networking.MyData;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = NukeModbyBoruebork.MODID, value = Dist.CLIENT)
public class ClientEvents {
    private static final KeyMapping keyMapping_k = new KeyMapping(
        "key.nukemod.k", // Will be localized using this translation key
        InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
        GLFW.GLFW_KEY_K, // Default key is P
        "key.categories.misc" // Mapping will be in the misc category
    );
    // In some physical client only class

    // Key mapping is lazily initialized so it doesn't exist until it is registered
    public static final Lazy<KeyMapping> SPAWN_MAPPING = Lazy.of(() -> keyMapping_k/*...*/);

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(SPAWN_MAPPING.get());
    }
    @SubscribeEvent // on the game event bus only on the physical client
    public static void onClientTick(ClientTickEvent.Post event) {
        while (SPAWN_MAPPING.get().consumeClick()) {
            Player  player =   Minecraft.getInstance().player;
            assert player != null;
            Inventory inv = player.getInventory();
            Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Summoned Nuke"));
            ClientPacketDistributor.sendToServer(new MyData(Minecraft.getInstance().player.getName().getString(), 14));



        }
    }
    @SubscribeEvent // on the mod event bus only on the physical client
    public static void register(RegisterClientPayloadHandlersEvent event) {
        event.register(
                MyData.TYPE,
                ClientPayloadHandler::handleDataOnMain
        );
    }
    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.NUKE.get(), NukeRenderer::new);
    }
}
