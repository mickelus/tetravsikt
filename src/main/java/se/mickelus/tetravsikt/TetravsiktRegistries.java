package se.mickelus.tetravsikt;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TetravsiktRegistries {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, TetravsiktMod.MOD_ID);

    public static void init(IEventBus bus) {
        bus.register(TetravsiktRegistries.class);
        items.register(bus);

//        items.register(DummyKnifeBladeItem.identifier, DummyKnifeBladeItem::new);
    }
}
