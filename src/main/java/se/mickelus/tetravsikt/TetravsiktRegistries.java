package se.mickelus.tetravsikt;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TetravsiktRegistries {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, TetravsiktMod.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> creativeTabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TetravsiktMod.MOD_ID);
    private static RegistryObject<CreativeModeTab> defaultCreativeTabs;

    static RegistryObject<Item> ironCoalMix;

    public static void init(IEventBus bus) {
        bus.register(TetravsiktRegistries.class);
        items.register(bus);
        creativeTabs.register(bus);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CREATIVE TABS
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        defaultCreativeTabs = creativeTabs.register("default", () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(ironCoalMix.get()))
                .title(Component.translatable("itemGroup.tetravsikt"))
                .build());

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // ITEMS
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ironCoalMix = items.register("iron_coal_mix", () -> new Item(new Item.Properties()));
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == defaultCreativeTabs.getKey()) {
            event.accept(ironCoalMix);
        }
    }
}
