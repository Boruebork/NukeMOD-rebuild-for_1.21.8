package com.boruebork.nukemod.items;

import com.boruebork.nukemod.NukeModbyBoruebork;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NukeModbyBoruebork.MODID);

    public static final DeferredItem<Item> NUKE = ITEMS.registerSimpleItem("nuke");
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
