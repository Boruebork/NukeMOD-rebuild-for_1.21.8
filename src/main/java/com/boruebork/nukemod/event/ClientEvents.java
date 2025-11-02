package com.boruebork.nukemod.event;

import com.boruebork.nukemod.NukeModbyBoruebork;
import com.boruebork.nukemod.entity.ModEntities;
import com.boruebork.nukemod.entity.client.ah64.AH64Model;
import com.boruebork.nukemod.entity.client.ah64.AH64Renderer;
import com.boruebork.nukemod.entity.client.nuke.NukeModel;
import com.boruebork.nukemod.entity.client.nuke.NukeRenderer;
import com.boruebork.nukemod.networking.ClientPayloadHandler;
import com.boruebork.nukemod.networking.MyData;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = NukeModbyBoruebork.MODID, value = Dist.CLIENT)
public class ClientEvents {
    private static final KeyMapping keyMapping_k = new KeyMapping(
        "key.nukemod.k", // Will be localized using this translation key
        InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
        GLFW.GLFW_KEY_N, // Default key is P
        "key.categories.misc" // Mapping will be in the misc category
    );
    private static final KeyMapping keyMapping_z = new KeyMapping(
            "key.nukemod.z", // Will be localized using this translation key
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_Z, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    );
    private static final KeyMapping keyMapping_c = new KeyMapping(
            "key.nukemod.c", // Will be localized using this translation key
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_C, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    );
    // In some physical client only class

    // Key mapping is lazily initialized so it doesn't exist until it is registered
    public static final Lazy<KeyMapping> SPAWN_MAPPING = Lazy.of(() -> keyMapping_k/*...*/);
    public static final Lazy<KeyMapping> DOWN_MAPPING = Lazy.of(() -> keyMapping_c);
    public static final Lazy<KeyMapping> UP_MAPPING = Lazy.of(() -> keyMapping_z/*...*/);
    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(SPAWN_MAPPING.get());
        event.register(UP_MAPPING.get());
        event.register(DOWN_MAPPING.get());
    }
    @SubscribeEvent // on the game event bus only on the physical client
    public static void onClientTick(ClientTickEvent.Post event) {
        Options Input = Minecraft.getInstance().options;
        while (SPAWN_MAPPING.get().consumeClick()) {
            sendPacketWithData("K", true);
        }

    }
    @SubscribeEvent // on the game event bus only on the physical client
    public static void onClientTickC(ClientTickEvent.Post event) {
        while (DOWN_MAPPING.get().consumeClick()) {
            Minecraft.getInstance().gui.getChat().addMessage(Component.literal("C|"));
            sendPacketWithData("C", true);
        }
    }
    @SubscribeEvent // on the game event bus only on the physical client
    public static void onClientTickZ(ClientTickEvent.Post event) {
        while (UP_MAPPING.get().consumeClick()) {
            Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Z"));
            sendPacketWithData("Z", true);
        }
    }
    public static void sendPacketWithData(String key, boolean type){
        Player  player =   Minecraft.getInstance().player;
        assert player != null;
        Inventory inv = player.getInventory();
        ClientPacketDistributor.sendToServer(new MyData(key, type));

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
        event.registerEntityRenderer(ModEntities.AH64.get(), AH64Renderer::new);
    }
    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Add our layer here.
        event.registerLayerDefinition(NukeModel.LAYER_LOCATION, NukeModel::createBodyLayer);
        event.registerLayerDefinition(AH64Model.LAYER_LOCATION, AH64Model::createBodyLayer);
    }


}
